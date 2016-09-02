package org.hyperledger.fabricjavasdk;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.CryptoPrimitive;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;

import org.bouncycastle.util.encoders.Hex;
import org.hyperledger.fabricjavasdk.security.CryptoPrimitives;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;

import io.netty.util.internal.StringUtil;
import protos.*;
import protos.Ca.CryptoType;
import protos.Ca.ECertCreateReq;
import protos.Ca.ECertCreateReqOrBuilder;
import protos.Ca.ECertCreateResp;
import protos.Ca.Identity;
import protos.Ca.PublicKey;
import protos.Ca.RegisterUserReq;
import protos.Ca.RegisterUserReqOrBuilder;
import protos.Ca.Registrar;
import protos.Ca.Signature;
import protos.Ca.Token;
import protos.ECAAGrpc.ECAABlockingStub;
import protos.ECAPGrpc.ECAPBlockingStub;
import protos.TCAPGrpc.TCAPBlockingStub;
import protos.TLSCAPGrpc.TLSCAPBlockingStub;

/**
 * MemberServicesImpl is the default implementation of a member services client.
 */
class MemberServicesImpl implements MemberServices {
	private static final Logger logger = Logger.getLogger(MemberServices.class.getName());

    private ECAABlockingStub ecaaClient;
    private ECAPBlockingStub ecapClient;
    private TCAPBlockingStub tcapClient;
    private TLSCAPBlockingStub tlscapClient;
    private CryptoPrimitives cryptoPrimitives;
    
    private int DEFAULT_SECURITY_LEVEL = 256;
	private String DEFAULT_HASH_ALGORITHM = "SHA3";

    /**
     * MemberServicesImpl constructor
     * @param config The config information required by this member services implementation.
     * @throws CertificateException 
     * @returns {MemberServices} A MemberServices object.
     */
    public MemberServicesImpl(String url, String pem) throws CertificateException {
    	Endpoint ep = new Endpoint(url, pem);
    	
    	//TODO: How to pass options to stubs?
//    	var options = {
//                "grpc.ssl_target_name_override" : "tlsca",
//                ""grpc.default_authority": ""tlsca"
//          };
    	this.ecaaClient = ECAAGrpc.newBlockingStub(ep.getChannelBuilder().build());
    	this.ecapClient = ECAPGrpc.newBlockingStub(ep.getChannelBuilder().build());
    	this.tcapClient = TCAPGrpc.newBlockingStub(ep.getChannelBuilder().build());
    	this.tlscapClient = TLSCAPGrpc.newBlockingStub(ep.getChannelBuilder().build());
    	this.cryptoPrimitives = new CryptoPrimitives(DEFAULT_HASH_ALGORITHM, DEFAULT_SECURITY_LEVEL);        
        
    }

    /**
     * Get the security level
     * @returns The security level
     */
    public int getSecurityLevel() {
        return cryptoPrimitives.getSecurityLevel();
    }

    /**
     * Set the security level
     * @params securityLevel The security level
     */
    public void setSecurityLevel(int securityLevel) {
        this.cryptoPrimitives.setSecurityLevel(securityLevel);
    }

    /**
     * Get the hash algorithm
     * @returns {string} The hash algorithm
     */
    public String getHashAlgorithm() {
        return this.cryptoPrimitives.getHashAlgorithm();    	
    }

    /**
     * Set the hash algorithm
     * @params hashAlgorithm The hash algorithm ('SHA2' or 'SHA3')
     */
    public void setHashAlgorithm(String hashAlgorithm) {
        this.cryptoPrimitives.setHashAlgorithm(hashAlgorithm);
    }

    public CryptoPrimitives getCrypto() {
        return this.cryptoPrimitives;
    }

    /**
     * Register the member and return an enrollment secret.
     * @param req Registration request with the following fields: name, role
     * @param registrar The identity of the registrar (i.e. who is performing the registration)
     * @param cb Callback of the form: {function(err,enrollmentSecret)}
     */
    public void register(RegistrationRequest req, Member registrar) {
    	if (StringUtil.isNullOrEmpty(req.getEnrollmentID())) {
    		throw new IllegalArgumentException("EntrollmentID cannot be null or empty");
    	}
    	
    	if (registrar == null) {
    		throw new IllegalArgumentException("Registrar should be a valid member");
    	}
    	
    	    	
    	Registrar reg = Registrar.newBuilder()
    			.setId(
    					Identity.newBuilder()
    					.setId(registrar.getName())
    					.build())
    			.build(); //TODO: set Roles and Delegate Roles
    	
    	RegisterUserReq.Builder regReqBuilder = RegisterUserReq.newBuilder();
    	regReqBuilder.setId(
    					Identity.newBuilder()
    					.setId(req.getEnrollmentID())
    					.build());
    	regReqBuilder.setRoleValue(rolesToMask(req.getRoles()));
    	regReqBuilder.setAffiliation(req.getAffiliation());
    	regReqBuilder.setRegistrar(reg);
    	
    	RegisterUserReq registerReq = regReqBuilder.build();
    	byte[] buffer = registerReq.toByteArray();
    	
    	try {
			PrivateKey signKey = cryptoPrimitives.ecdsaKeyFromPrivate(Hex.decode(registrar.getEnrollment().getKey()));
	    	debug("Retreived private key");
			BigInteger[] signature = cryptoPrimitives.ecdsaSign(signKey, buffer);
	    	debug("Signed the request with key");
			Signature sig = Signature.newBuilder().setType(CryptoType.ECDSA).setR(ByteString.copyFrom(signature[0].toByteArray())).setS(ByteString.copyFrom(signature[1].toByteArray())).build();
			regReqBuilder.setSig(sig);
	    	debug("Now sendingt register request");
			Token token = this.ecaaClient.registerUser(regReqBuilder.build());
			
	    	debug("User registered with token:"+token.getTok());
	    	
	    	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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
    public Enrollment enroll(EnrollmentRequest req) {
    	
    	
        debug("[MemberServicesImpl.enroll] [%j]", req);
        if (StringUtil.isNullOrEmpty(req.getEnrollmentID())) { throw new RuntimeException("req.enrollmentID is not set");}
        if (StringUtil.isNullOrEmpty(req.getEnrollmentSecret())) { throw new RuntimeException("req.enrollmentSecret is not set");}

        debug("[MemberServicesImpl.enroll] Generating keys...");

        try {
	        // generate ECDSA keys: signing and encryption keys        
	        KeyPair signingKeyPair = cryptoPrimitives.ecdsaKeyGen();
	        KeyPair encryptionKeyPair = cryptoPrimitives.ecdsaKeyGen();
	
	        debug("[MemberServicesImpl.enroll] Generating keys...done!");
	
	        // create the proto message
	        ECertCreateReq.Builder eCertCreateRequestBuilder = ECertCreateReq.newBuilder()
	        		.setTs(Timestamp.newBuilder().setSeconds(new java.util.Date().getTime()))
	        		.setId(Identity.newBuilder()
	    					.setId(req.getEnrollmentID())
	    					.build())
	        		.setTok(Token.newBuilder().setTok(ByteString.copyFrom(req.getEnrollmentSecret(), "UTF8")))
	        		.setSign(PublicKey.newBuilder().setKey(ByteString.copyFrom(signingKeyPair.getPublic().getEncoded())).setType(CryptoType.ECDSA))
	        		.setEnc(PublicKey.newBuilder().setKey(ByteString.copyFrom(encryptionKeyPair.getPublic().getEncoded())).setType(CryptoType.ECDSA));
	        
	        ECertCreateResp eCertCreateResp = this.ecapClient.createCertificatePair(eCertCreateRequestBuilder.build());
	        
	        byte[] cipherText = eCertCreateResp.getTok().getTok().toByteArray();	        
	        debug("Cipher text = "+StringUtil.toHexString(cipherText));
            byte[] decryptedTokBytes = cryptoPrimitives.eciesDecrypt(encryptionKeyPair, cipherText);            
            byte[] buf = eCertCreateRequestBuilder.setTok(Token.newBuilder().setTok(ByteString.copyFrom(decryptedTokBytes))).build().toByteArray();
            java.math.BigInteger[] sig = cryptoPrimitives.ecdsaSign(signingKeyPair.getPrivate(), buf);            
            eCertCreateRequestBuilder.setSig(Signature.newBuilder().setType(CryptoType.ECDSA).setR(ByteString.copyFrom(sig[0].toByteArray())).setS(ByteString.copyFrom(sig[1].toByteArray())).build());

            eCertCreateResp = ecapClient.createCertificatePair(eCertCreateRequestBuilder.build());

            debug("[MemberServicesImpl.enroll] eCertCreateResp : [%j]" + eCertCreateResp.toByteString());
            
            Enrollment enrollment = new Enrollment();
            enrollment.setKey(Hex.toHexString(encryptionKeyPair.getPrivate().getEncoded()));
            enrollment.setCert(Hex.toHexString(eCertCreateResp.getCerts().getSign().toByteArray()));
            enrollment.setChainKey(Hex.toHexString(eCertCreateResp.getPkchain().toByteArray()));
            
            debug("Enrolled successfully: "+enrollment);
            return enrollment;
        
        } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SignatureException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidProtocolBufferException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
        
        return null;

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
    
    // Convert a list of member type names to the role mask currently used by the peer
    private int rolesToMask(ArrayList<String> roles) {
        int mask = 0;
        if (roles != null) {
            for (String role: roles) {
                switch (role) {
                    case "client":
                        mask |= 1;
                        break;       // Client mask
                    case "peer":
                        mask |= 2;
                        break;       // Peer mask
                    case "validator":
                        mask |= 4;
                        break;  // Validator mask
                    case "auditor":
                        mask |= 8;
                        break;    // Auditor mask
                }
            }
        }
        
        if (mask == 0) mask = 1;  // Client
        return mask;
    }
    
    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
      }
    private static void debug(String msg, Object... params) {
        //logger.log(Level.FINE, msg, params);
    	info(msg, params);
      }
    
    public static void main(String args[]) throws Exception {
//    	CryptoPrimitives crypto = new CryptoPrimitives("SHA3", 256);
//    	crypto.eciesDecrypt(null, "Hello World!".getBytes());
//    	
//    	debug("SUCCESS!", null);
//    	
//    	if (true)
//    		return;
    	Chain testChain = new Chain("chain1");

			testChain.setMemberServicesUrl("grpc://localhost:7054", null);
			testChain.setKeyValStore(new FileKeyValStore("/home/pardha/test.properties"));
			testChain.addPeer("grpc://localhost:7051", null);			
			testChain.enroll("admin", "Xurw3yU9zI0l");
			
		
    			
//    	MemberServicesImpl msi = new MemberServicesImpl("grpc://localhost:7054", "");
    	RegistrationRequest req = new RegistrationRequest();
    	req.setAffiliation("bank_a");
    	req.setEnrollmentID("myuser");
    	
    	Member registrar = testChain.getMember("admin");
    	testChain.setRegistrar(registrar);
//    	msi.register(req, registrar);    	
    	testChain.register(req);
    	
    }


} // end MemberServicesImpl

