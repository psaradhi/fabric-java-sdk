package org.hyperledger.fabricjavasdk;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MemberServicesImpl is the default implementation of a member services client.
 */
class MemberServicesImpl implements MemberServices {
	private static final Logger logger = Logger.getLogger(MemberServices.class.getName());

    private Object ecaaClient;
    private Object ecapClient;
    private Object tcapClient;
    private Object tlscapClient;
    private Object cryptoPrimitives;

    /**
     * MemberServicesImpl constructor
     * @param config The config information required by this member services implementation.
     * @returns {MemberServices} A MemberServices object.
     */
    public MemberServicesImpl(String url, String pem) {

    	/*TODO implement MemberServicesImpl constructor
    	let ep = new Endpoint(url,pem);
        var options = {
              "grpc.ssl_target_name_override" : "tlsca",
              ""grpc.default_authority": ""tlsca"
        };
        this.ecaaClient = new _caProto.ECAA(ep.addr, ep.creds, options);
        this.ecapClient = new _caProto.ECAP(ep.addr, ep.creds, options);
        this.tcapClient = new _caProto.TCAP(ep.addr, ep.creds, options);
        this.tlscapClient = new _caProto.TLSCAP(ep.addr, ep.creds, options);
        this.cryptoPrimitives = new crypto.Crypto(DEFAULT_HASH_ALGORITHM, DEFAULT_SECURITY_LEVEL);
        
        */
    }

    /**
     * Get the security level
     * @returns The security level
     */
    public int getSecurityLevel() {
        return 0; //TODO return this.cryptoPrimitives.getSecurityLevel();
    }

    /**
     * Set the security level
     * @params securityLevel The security level
     */
    public void setSecurityLevel(int securityLevel) {
        //TODO this.cryptoPrimitives.setSecurityLevel(securityLevel);
    }

    /**
     * Get the hash algorithm
     * @returns {string} The hash algorithm
     */
    public String getHashAlgorithm() {
        //TODO return this.cryptoPrimitives.getHashAlgorithm();
    	return ""; //TODO return the correct hash algorithm
    }

    /**
     * Set the hash algorithm
     * @params hashAlgorithm The hash algorithm ('SHA2' or 'SHA3')
     */
    public void setHashAlgorithm(String hashAlgorithm) {
        //TODO this.cryptoPrimitives.setHashAlgorithm(hashAlgorithm);
    }

    public Object getCrypto() {
        return null; //TODO return this.cryptoPrimitives;
    }

    /**
     * Register the member and return an enrollment secret.
     * @param req Registration request with the following fields: name, role
     * @param registrar The identity of the registrar (i.e. who is performing the registration)
     * @param cb Callback of the form: {function(err,enrollmentSecret)}
     */
    public void register(RegistrationRequest req, Member registrar) {
    	info("TODO: implement registering with member services");
    }       
    	/* TODO implement register
    	let self = this;
        debug("MemberServicesImpl.register: req=%j", req);
        if (!req.enrollmentID) return cb(new Error("missing req.enrollmentID"));
        if (!registrar) return cb(new Error("chain registrar is not set"));
        let protoReq = new _caProto.RegisterUserReq();
        //TODO setId protoReq.setId({id:req.enrollmentID});
        protoReq.setRole(rolesToMask(req.roles));
        protoReq.setAffiliation(req.affiliation);
        // Create registrar info
        protoRegistrar = new _caProto.Registrar();
        //TODO setId protoRegistrar.setId({id:registrar.getName()});
        if (req.registrar) {
            if (req.registrar.roles) {
               protoRegistrar.setRoles(req.registrar.roles);
            }
            if (req.registrar.delegateRoles) {
               protoRegistrar.setDelegateRoles(req.registrar.delegateRoles);
            }
        }
        protoReq.setRegistrar(protoRegistrar);
        // Sign the registration request
        var buf = protoReq.toBuffer();
        var signKey = self.cryptoPrimitives.ecdsaKeyFromPrivate(registrar.getEnrollment().key, "hex");
        var sig = self.cryptoPrimitives.ecdsaSign(signKey, buf);
        protoReq.setSig( new _caProto.Signature(
            {
                type: _caProto.CryptoType.ECDSA,
                r: new Buffer(sig.r.toString()),
                s: new Buffer(sig.s.toString())
            }
        ));
        // Send the registration request
        self.ecaaClient.registerUser(protoReq, function (err, token) {
            debug("register %j: err=%j, token=%s", protoReq, err, token);
            if (cb) return cb(err, token ? token.tok.toString() : null);
        });
        */
 

    /**
     * Enroll the member and return an opaque member object
     * @param req Enrollment request with the following fields: name, enrollmentSecret
     * @param cb Callback of the form: {function(err,{key,cert,chainKey})}
     */
    public void enroll(EnrollmentRequest req) {
    	
    	/*TODO implement enroll
        let self = this;
        cb = cb || nullCB;

        debug("[MemberServicesImpl.enroll] [%j]", req);
        if (!req.enrollmentID) return cb(Error("req.enrollmentID is not set"));
        if (!req.enrollmentSecret) return cb(Error("req.enrollmentSecret is not set"));

        debug("[MemberServicesImpl.enroll] Generating keys...");

        // generate ECDSA keys: signing and encryption keys
        // 1) signing key
        var signingKeyPair = self.cryptoPrimitives.ecdsaKeyGen();
        var spki = new asn1.x509.SubjectPublicKeyInfo(signingKeyPair.pubKeyObj);
        // 2) encryption key
        var encryptionKeyPair = self.cryptoPrimitives.ecdsaKeyGen();
        var spki2 = new asn1.x509.SubjectPublicKeyInfo(encryptionKeyPair.pubKeyObj);

        debug("[MemberServicesImpl.enroll] Generating keys...done!");

        // create the proto message
        var eCertCreateRequest = new _caProto.ECertCreateReq();
        var timestamp = sdk_util.GenerateTimestamp();
        eCertCreateRequest.setTs(timestamp);
        //TODO setId eCertCreateRequest.setId({id: req.enrollmentID});
        //TODO setTok eCertCreateRequest.setTok({tok: new Buffer(req.enrollmentSecret)});

        debug("[MemberServicesImpl.enroll] Generating request! %j", spki.getASN1Object().getEncodedHex());

        // public signing key (ecdsa)
        var signPubKey = new _caProto.PublicKey(
            {
                type: _caProto.CryptoType.ECDSA,
                key: new Buffer(spki.getASN1Object().getEncodedHex(), "hex")
            });
        eCertCreateRequest.setSign(signPubKey);

        debug("[MemberServicesImpl.enroll] Adding signing key!");

        // public encryption key (ecdsa)
        var encPubKey = new _caProto.PublicKey(
            {
                type: _caProto.CryptoType.ECDSA,
                key: new Buffer(spki2.getASN1Object().getEncodedHex(), "hex")
            });
        eCertCreateRequest.setEnc(encPubKey);

        debug("[MemberServicesImpl.enroll] Assding encryption key!");

        debug("[MemberServicesImpl.enroll] [Contact ECA] %j ", eCertCreateRequest);
        self.ecapClient.createCertificatePair(eCertCreateRequest, function (err, eCertCreateResp) {
            if (err) {
                debug("[MemberServicesImpl.enroll] failed to create cert pair: err=%j", err);
                return cb(err);
            }
            let cipherText = eCertCreateResp.tok.tok;
            var decryptedTokBytes = self.cryptoPrimitives.eciesDecrypt(encryptionKeyPair.prvKeyObj, cipherText);

            //debug(decryptedTokBytes);
            // debug(decryptedTokBytes.toString());
            // debug('decryptedTokBytes [%s]', decryptedTokBytes.toString());
            //TODO setTok eCertCreateRequest.setTok({tok: decryptedTokBytes});
            eCertCreateRequest.setSig(null);

            var buf = eCertCreateRequest.toBuffer();

            var signKey = self.cryptoPrimitives.ecdsaKeyFromPrivate(signingKeyPair.prvKeyObj.prvKeyHex, "hex");
            //debug(new Buffer(sha3_384(buf),"hex"));
            var sig = self.cryptoPrimitives.ecdsaSign(signKey, buf);

            eCertCreateRequest.setSig(new _caProto.Signature(
                {
                    type: _caProto.CryptoType.ECDSA,
                    r: new Buffer(sig.r.toString()),
                    s: new Buffer(sig.s.toString())
                }
            ));
            self.ecapClient.createCertificatePair(eCertCreateRequest, function (err, eCertCreateResp) {
                if (err) return cb(err);
                debug("[MemberServicesImpl.enroll] eCertCreateResp : [%j]" + eCertCreateResp);

                let enrollment = {
                    key: signingKeyPair.prvKeyObj.prvKeyHex,
                    cert: eCertCreateResp.certs.sign.toString("hex"),
                    chainKey: eCertCreateResp.pkchain.toString("hex")
                };
                // debug('cert:\n\n',enrollment.cert)
                cb(null, enrollment);
            });
        });
        */

    } // end enroll

    /**
     * Get an array of transaction certificates (tcerts).
     * @param {Object} req Request of the form: {name,enrollment,num} where
     * 'name' is the member name,
     * 'enrollment' is what was returned by enroll, and
     * 'num' is the number of transaction contexts to obtain.
     * @param {function(err,[Object])} cb The callback function which is called with an error as 1st arg and an array of tcerts as 2nd arg.
     */
    public void getTCertBatch(GetTCertBatchRequest req) {
    	
    	/*TODO implement getTCertBatch
        let self = this;
        cb = cb || nullCB;

        let timestamp = sdk_util.GenerateTimestamp();

        // create the proto
        let tCertCreateSetReq = new _caProto.TCertCreateSetReq();
        tCertCreateSetReq.setTs(timestamp);
        tCertCreateSetReq.setId({id: req.name});
        tCertCreateSetReq.setNum(req.num);
        if (req.attrs) {
            let attrs = [];
            for (let i = 0; i < req.attrs.length; i++) {
                attrs.push({attributeName:req.attrs[i]});
            }
            tCertCreateSetReq.setAttributes(attrs);
        }

        // serialize proto
        let buf = tCertCreateSetReq.toBuffer();

        // sign the transaction using enrollment key
        let signKey = self.cryptoPrimitives.ecdsaKeyFromPrivate(req.enrollment.key, "hex");
        let sig = self.cryptoPrimitives.ecdsaSign(signKey, buf);

        tCertCreateSetReq.setSig(new _caProto.Signature(
            {
                type: _caProto.CryptoType.ECDSA,
                r: new Buffer(sig.r.toString()),
                s: new Buffer(sig.s.toString())
            }
        ));

        // send the request
        self.tcapClient.createCertificateSet(tCertCreateSetReq, function (err, resp) {
            if (err) return cb(err);
            // debug('tCertCreateSetResp:\n', resp);
            cb(null, self.processTCertBatch(req, resp));
        });
        
        */
    }

    /**
     * Process a batch of tcerts after having retrieved them from the TCA.
     */
    private TCert[] processTCertBatch(GetTCertBatchRequest req, Object resp) {

    	return null;
    	
    	/* TODO implement processTCertBatch
        //
        // Derive secret keys for TCerts
        //

        let enrollKey = req.enrollment.key;
        let tCertOwnerKDFKey = resp.certs.key;
        let tCerts = resp.certs.certs;

        let byte1 = new Buffer(1);
        byte1.writeUInt8(0x1, 0);
        let byte2 = new Buffer(1);
        byte2.writeUInt8(0x2, 0);

        let tCertOwnerEncryptKey = self.cryptoPrimitives.hmac(tCertOwnerKDFKey, byte1).slice(0, 32);
        let expansionKey = self.cryptoPrimitives.hmac(tCertOwnerKDFKey, byte2);

        let tCertBatch:TCert[] = [];

        // Loop through certs and extract private keys
        for (var i = 0; i < tCerts.length; i++) {
            var tCert = tCerts[i];
            let x509Certificate;
            try {
                x509Certificate = new crypto.X509Certificate(tCert.cert);
            } catch (ex) {
                debug("Warning: problem parsing certificate bytes; retrying ... ", ex);
                continue;
            }

            // debug("HERE2: got x509 cert");
            // extract the encrypted bytes from extension attribute
            let tCertIndexCT = x509Certificate.criticalExtension(crypto.TCertEncTCertIndex);
            // debug('tCertIndexCT: ',JSON.stringify(tCertIndexCT));
            let tCertIndex = self.cryptoPrimitives.aesCBCPKCS7Decrypt(tCertOwnerEncryptKey, tCertIndexCT);
            // debug('tCertIndex: ',JSON.stringify(tCertIndex));

            let expansionValue = self.cryptoPrimitives.hmac(expansionKey, tCertIndex);
            // debug('expansionValue: ',expansionValue);

            // compute the private key
            let one = new BN(1);
            let k = new BN(expansionValue);
            let n = self.cryptoPrimitives.ecdsaKeyFromPrivate(enrollKey, "hex").ec.curve.n.sub(one);
            k = k.mod(n).add(one);

            let D = self.cryptoPrimitives.ecdsaKeyFromPrivate(enrollKey, "hex").getPrivate().add(k);
            let pubHex = self.cryptoPrimitives.ecdsaKeyFromPrivate(enrollKey, "hex").getPublic("hex");
            D = D.mod(self.cryptoPrimitives.ecdsaKeyFromPublic(pubHex, "hex").ec.curve.n);

            // Put private and public key in returned tcert
            let tcert = new TCert(tCert.cert, self.cryptoPrimitives.ecdsaKeyFromPrivate(D, "hex"));
            tCertBatch.push(tcert);
        }

        if (tCertBatch.length == 0) {
            throw new RuntimeException("Failed fetching TCertBatch. No valid TCert received.");
        }

        return tCertBatch;
        */

    } // end processTCertBatch
    
    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
      }
    private static void debug(String msg, Object... params) {
        logger.log(Level.FINE, msg, params);
      }


} // end MemberServicesImpl

