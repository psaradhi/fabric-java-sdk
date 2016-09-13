package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hyperledger.fabricjavasdk.exception.EnrollmentException;
import org.hyperledger.fabricjavasdk.exception.RegistrationException;

class Member {
	private static final Logger logger = Logger.getLogger(Member.class.getName());

    private Chain chain;
    private String name;
    private ArrayList<String> roles;
    private String account;
    private String affiliation;
    private String enrollmentSecret;
    private Enrollment enrollment = null;
    private MemberServices memberServices;
    private KeyValStore keyValStore;
    private String keyValStoreName;
    private Map<String, TCertGetter> tcertGetterMap;
    private int tcertBatchSize;

    /**
     * Constructor for a member.
     * @param cfg {string | RegistrationRequest} The member name or registration request.
     * @returns {Member} A member who is neither registered nor enrolled.
     */

    public Member(String name, Chain chain) {
    	this((Object) name, chain);
    }
    
    public Member(Object cfg, Chain chain) {
        if (cfg instanceof String) {
            this.name = (String) cfg;
        } else if (cfg instanceof Object) {

        	/* TODO implement this logic
        	let req = cfg;
            this.name = req.enrollmentID || req.name;
            this.roles = req.roles || ["fabric.user"];
            this.account = req.account;
            this.affiliation = req.affiliation;
            
            */
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
    public void setAccount(String account) {
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
    public Enrollment getEnrollment() {    	
        return this.enrollment;
    };

    /**
     * Determine if this name has been registered.
     * @returns {boolean} True if registered; otherwise, false.
     */
    public boolean isRegistered() {
        return !enrollmentSecret.trim().isEmpty();
    }

    /**
     * Determine if this name has been enrolled.
     * @returns {boolean} True if enrolled; otherwise, false.
     */
    public boolean isEnrolled() {
        return this.enrollment != null;
    }

    /**
     * Register the member.
     * @param cb Callback of the form: {function(err,enrollmentSecret)}
     * @throws RegistrationException 
     */
    public void register(RegistrationRequest registrationRequest) throws RegistrationException {
        if (!registrationRequest.enrollmentID.equals(getName())) {
            throw new RuntimeException("registration enrollment ID and member name are not equal");
        }

        if (null != enrollmentSecret) {
            debug("previously registered, enrollmentSecret=%s", enrollmentSecret);
            return;
        }

        memberServices.register(registrationRequest, chain.getRegistrar());

        /* TODO implement logic present in callback function
        debug("memberServices.register err=%s, secret=%s", err, enrollmentSecret);
            self.enrollmentSecret = enrollmentSecret;
            self.saveState(function (err) {
                if (err) return cb(err);
                cb(null, enrollmentSecret);
            });
        });
        */
    }

    /**
     * Enroll the member and return the enrollment results.
     * @param enrollmentSecret The password or enrollment secret as returned by register.
     * @param cb Callback to report an error if it occurs
     * @throws EnrollmentException 
     */
    public Enrollment enroll(String enrollmentSecret) throws EnrollmentException {
        if (null != enrollment) {
            debug("Previously enrolled, [enrollment=%j]", enrollment);
            return enrollment;
        }

        EnrollmentRequest req = new EnrollmentRequest();
        req.setEnrollmentID(getName());
        req.setEnrollmentSecret(enrollmentSecret);
        debug("Enrolling [req=%j]", req);
        
        this.enrollment = memberServices.enroll(req);
        return this.enrollment;
    }

    /**
     * Perform both registration and enrollment.
     * @param cb Callback of the form: {function(err,{key,cert,chainKey})}
     * @throws RegistrationException 
     */
    public void registerAndEnroll(RegistrationRequest registrationRequest) throws RegistrationException {
        if (null != enrollment) {
            debug("previously enrolled, enrollment=%j", enrollment);
            return ;
        }

        register(registrationRequest);
        
        /* TODO implement the callback logic
           function (err, enrollmentSecret) {
            if (err) return cb(err);
            self.enroll(enrollmentSecret, function (err, enrollment) {
                if (err) return cb(err);
                cb(null);
            });
        });
        */
    }

    /**
     * Issue a deploy request on behalf of this member.
     * @param deployRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public void deploy(DeployRequest deployRequest) {
        debug("Member.deploy");

        getChain().getPeers().get(0).deploy(deployRequest); //TODO add error checks
    }

    /**
     * Issue a invoke request on behalf of this member.
     * @param invokeRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public void invoke(InvokeRequest invokeRequest) {
        debug("Member.invoke");

        getChain().getPeers().get(0).invoke(invokeRequest); //TODO add error checks
    }

    /**
     * Issue a query request on behalf of this member.
     * @param queryRequest {Object}
     * @returns {TransactionContext} Emits 'submitted', 'complete', and 'error' events.
     */
    public void query(QueryRequest queryRequest) {
        debug("Member.query");

        getChain().getPeers().get(0).query(queryRequest); //TODO add error checks
    }

    /**
     * Create a transaction context with which to issue build, deploy, invoke, or query transactions.
     * Only call this if you want to use the same tcert for multiple transactions.
     * @param {Object} tcert A transaction certificate from member services.  This is optional.
     * @returns A transaction context.
     */
    public TransactionContext newTransactionContext(TCert tcert) {
        return new TransactionContext(this, tcert);
    }

    /**
     * Get a user certificate.
     * @param attrs The names of attributes to include in the user certificate.
     * @param cb A GetTCertCallback
     */
    public void getUserCert(String[] attrs) {
        this.getNextTCert(attrs);
    }

    /**
   * Get the next available transaction certificate with the appropriate attributes.
   * @param cb
   */
   public void getNextTCert(String[] attrs) {

	   /*TODO implement getNextTCert
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
        */
   }

   /**
    * Save the state of this member to the key value store.
    * @param cb Callback of the form: {function(err}
    */
   public void saveState() {
      keyValStore.setValue(keyValStoreName, this.toString());
   }

   /**
    * Restore the state of this member from the key value store (if found).  If not found, do nothing.
    * @param cb Callback of the form: function(err}
    */
   public void restoreState() {
      String memberStr = keyValStore.getValue(keyValStoreName);
      if(null != memberStr) {
             // The member was found in the key value store, so restore the state.
             fromString(memberStr);
         }
   }

    /**
     * Get the current state of this member as a string
     * @return {string} The state of this member as a string
     */
    public void fromString(String str) {
//        Member state = JSON.parse(str);
    	Member state = null; //TODO implement JSON.parse()
        if (state.name != this.getName()) throw new RuntimeException("name mismatch: '" + state.name + "' does not equal '" + this.getName() + "'");
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
    	/*TODO implement toString()
        let state = {
            name: self.name,
            roles: self.roles,
            account: self.account,
            affiliation: self.affiliation,
            enrollmentSecret: self.enrollmentSecret,
            enrollment: self.enrollment
        };
        return JSON.stringify(state);
        */
    	return "";
    }

    String toKeyValStoreName(String name) {
        return "member." + name;
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
      }
    private static void debug(String msg, Object... params) {
        logger.log(Level.FINE, msg, params);
      }

}
