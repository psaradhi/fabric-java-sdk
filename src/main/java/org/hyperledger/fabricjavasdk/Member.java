
class Member {

    private Chain chain;
    private String name;
    private ArrayList<String> roles;
    private String account;
    private String affiliation;
    private String enrollmentSecret;
    private String enrollment;
    private MemberServices memberServices;
    private KeyValStore keyValStore;
    private String keyValStoreName;
    private tcertGetterMap: {[s:string]:TCertGetter} = {};
    private int tcertBatchSize;

    /**
     * Constructor for a member.
     * @param cfg {string | RegistrationRequest} The member name or registration request.
     * @returns {Member} A member who is neither registered nor enrolled.
     */
    public Member(cfg:any, Chain chain) {
        if (util.isString(cfg)) {
            this.name = cfg;
        } else if (util.isObject(cfg)) {
            let req = cfg;
            this.name = req.enrollmentID || req.name;
            this.roles = req.roles || ['fabric.user'];
            this.account = req.account;
            this.affiliation = req.affiliation;
        }
        this.chain = chain;
        this.memberServices = chain.getMemberServices();
        this.keyValStore = chain.getKeyValStore();
        this.keyValStoreName = toKeyValStoreName(this.name);
        this.tcertBatchSize = chain.getTCertBatchSize();
    }

    /**
     * Get the member name.
     * @returns {string} The member name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the chain.
     * @returns {Chain} The chain.
     */
    public Chain getChain() {
        return this.chain;
    }

    /**
     * Get the member services.
     * @returns {MemberServices} The member services.
     */

    public MemberServices getMemberServices() {
       return this.memberServices;
    }

    /**
     * Get the roles.
     * @returns {string[]} The roles.
     */
    public ArrayList<String> getRoles() {
        return this.roles;
    }

    /**
     * Set the roles.
     * @param roles {string[]} The roles.
     */
    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    /**
     * Get the account.
     * @returns {string} The account.
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * Set the account.
     * @param account The account.
     */
    public setAccount(String account) {
        this.account = account;
    }

    /**
     * Get the affiliation.
     * @returns {string} The affiliation.
     */
    public String getAffiliation() {
        return this.affiliation;
    }

    /**
     * Set the affiliation.
     * @param affiliation The affiliation.
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * Get the transaction certificate (tcert) batch size, which is the number of tcerts retrieved
     * from member services each time (i.e. in a single batch).
     * @returns The tcert batch size.
     */
    public int getTCertBatchSize() {
        if (this.tcertBatchSize <= 0) {
            return this.chain.getTCertBatchSize();
        } else {
            return this.tcertBatchSize;
        }
    }

    /**
     * Set the transaction certificate (tcert) batch size.
     * @param batchSize
     */
    public void setTCertBatchSize(int batchSize) {
        this.tcertBatchSize = batchSize;
    }

    /**
     * Get the enrollment info.
     * @returns {Enrollment} The enrollment.
     */
    getEnrollment():any {
        return this.enrollment;
    };

    /**
     * Determine if this name has been registered.
     * @returns {boolean} True if registered; otherwise, false.
     */
    public boolean isRegistered() {
        return !enrollmentSecret.trim().empty();
    }

    /**
     * Determine if this name has been enrolled.
     * @returns {boolean} True if enrolled; otherwise, false.
     */
    public boolean isEnrolled() {
        return !enrollment.trim().empty();
    }

    /**
     * Register the member.
     * @param cb Callback of the form: {function(err,enrollmentSecret)}
     */
    public void register(RegistrationRequest registrationRequest, RegisterCallback cb) {
        cb = cb || nullCB;
        if (!registrationRequest.enrollmentID.equals(getName())) {
            throw new RuntimeException("registration enrollment ID and member name are not equal");
        }

        if (enrollmentSecret) {
            debug("previously registered, enrollmentSecret=%s", enrollmentSecret);
            return cb(null, enrollmentSecret);
        }

        memberServices.register(registrationRequest, self.chain.getRegistrar()); function (err, enrollmentSecret) {

        debug("memberServices.register err=%s, secret=%s", err, enrollmentSecret);
            self.enrollmentSecret = enrollmentSecret;
            self.saveState(function (err) {
                if (err) return cb(err);
                cb(null, enrollmentSecret);
            });
        });
    }

    /**
     * Enroll the member and return the enrollment results.
     * @param enrollmentSecret The password or enrollment secret as returned by register.
     * @param cb Callback to report an error if it occurs
     */
    public void enroll(String enrollmentSecret, EnrollCallback cb) {
        let self = this;
        cb = cb || nullCB;
        let enrollment = self.enrollment;
        if (enrollment) {
            debug("Previously enrolled, [enrollment=%j]", enrollment);
            return cb(null,enrollment);
        }
        let req = {enrollmentID: self.getName(), enrollmentSecret: enrollmentSecret};
        debug("Enrolling [req=%j]", req);
        self.memberServices.enroll(req, function (err:Error, enrollment:Enrollment) {
            debug("[memberServices.enroll] err=%s, enrollment=%j", err, enrollment);
            if (err) return cb(err);
            self.enrollment = enrollment;
            // Generate queryStateKey
            self.enrollment.queryStateKey = self.chain.cryptoPrimitives.generateNonce();

            // Save state
            self.saveState(function (err) {
                if (err) return cb(err);

                // Unmarshall chain key
                // TODO: during restore, unmarshall enrollment.chainKey
                debug("[memberServices.enroll] Unmarshalling chainKey");
                var ecdsaChainKey = self.chain.cryptoPrimitives.ecdsaPEMToPublicKey(self.enrollment.chainKey);
                self.enrollment.enrollChainKey = ecdsaChainKey;

                cb(null, enrollment);
            });
        });
    }

    /**
     * Perform both registration and enrollment.
     * @param cb Callback of the form: {function(err,{key,cert,chainKey})}
     */
    public void registerAndEnroll(RegistrationRequest registrationRequest, cb:ErrorCallback) {
        let self = this;
        cb = cb || nullCB;
        let enrollment = self.enrollment;
        if (enrollment) {
            debug("previously enrolled, enrollment=%j", enrollment);
            return cb(null);
        }
        self.register(registrationRequest, function (err, enrollmentSecret) {
            if (err) return cb(err);
            self.enroll(enrollmentSecret, function (err, enrollment) {
                if (err) return cb(err);
                cb(null);
            });
        });
    }

    /**
     * Issue a deploy request on behalf of this member.
     * @param deployRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public TransactionContext deploy(DeployRequest deployRequest) {
        debug("Member.deploy");

        let tx = this.newTransactionContext();
        tx.deploy(deployRequest);
        return tx;
    }

    /**
     * Issue a invoke request on behalf of this member.
     * @param invokeRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public TransactionContext invoke(InvokeRequest invokeRequest) {
        debug("Member.invoke");

        TransactionContext tx = newTransactionContext();
        tx.invoke(invokeRequest);
        return tx;
    }

    /**
     * Issue a query request on behalf of this member.
     * @param queryRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public TransactionContext query(QueryRequest queryRequest) {
        debug("Member.query");

        var tx = this.newTransactionContext();
        tx.query(queryRequest);
        return tx;
    }

    /**
     * Create a transaction context with which to issue build, deploy, invoke, or query transactions.
     * Only call this if you want to use the same tcert for multiple transactions.
     * @param {Object} tcert A transaction certificate from member services.  This is optional.
     * @returns A transaction context.
     */
    public TransactionContext newTransactionContext(tcert?:TCert) {
        return new TransactionContext(this, tcert);
    }

    /**
     * Get a user certificate.
     * @param attrs The names of attributes to include in the user certificate.
     * @param cb A GetTCertCallback
     */
    public void getUserCert(attrs:string[], cb:GetTCertCallback) {
        this.getNextTCert(attrs,cb);
    }

    /**
   * Get the next available transaction certificate with the appropriate attributes.
   * @param cb
   */
   public void getNextTCert(attrs:string[], cb:GetTCertCallback) {
        let self = this;
        if (!self.isEnrolled()) {
            return cb(Error(util.format("user '%s' is not enrolled",self.getName())));
        }
        let key = getAttrsKey(attrs);
        debug("Member.getNextTCert: key=%s",key);
        let tcertGetter = self.tcertGetterMap[key];
        if (!tcertGetter) {
            debug("Member.getNextTCert: key=%s, creating new getter",key);
            tcertGetter = new TCertGetter(self,attrs,key);
            self.tcertGetterMap[key] = tcertGetter;
        }
        return tcertGetter.getNextTCert(cb);
   }

   /**
    * Save the state of this member to the key value store.
    * @param cb Callback of the form: {function(err}
    */
   public void saveState(cb:ErrorCallback) {
      let self = this;
      self.keyValStore.setValue(self.keyValStoreName, self.toString(), cb);
   }

   /**
    * Restore the state of this member from the key value store (if found).  If not found, do nothing.
    * @param cb Callback of the form: function(err}
    */
   public void restoreState(cb:ErrorCallback) {
      keyValStore.getValue(self.keyValStoreName, function (err, memberStr) {
         if (err) return cb(err);
         // debug("restoreState: name=%s, memberStr=%s", self.getName(), memberStr);
         if (memberStr) {
             // The member was found in the key value store, so restore the state.
             self.fromString(memberStr);
         }
         cb(null);
      });
   }

    /**
     * Get the current state of this member as a string
     * @return {string} The state of this member as a string
     */
    public void fromString(String str) {
        let state = JSON.parse(str);
        if (state.name !== this.getName()) throw Error("name mismatch: '" + state.name + "' does not equal '" + this.getName() + "'");
        this.name = state.name;
        this.roles = state.roles;
        this.account = state.account;
        this.affiliation = state.affiliation;
        this.enrollmentSecret = state.enrollmentSecret;
        this.enrollment = state.enrollment;
    }

    /**
     * Save the current state of this member as a string
     * @return {string} The state of this member as a string
     */
    public String toString() {
        let state = {
            name: self.name,
            roles: self.roles,
            account: self.account,
            affiliation: self.affiliation,
            enrollmentSecret: self.enrollmentSecret,
            enrollment: self.enrollment
        };
        return JSON.stringify(state);
    }

}
