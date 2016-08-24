package org.hyperledger.fabricjavasdk;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

import protos.Chaincode.ChaincodeDeploymentSpec;
import protos.Chaincode.ChaincodeInput;
import protos.Chaincode.ChaincodeSpec;
import protos.Chaincode.ConfidentialityLevel;

/**
 * A transaction context emits events 'submitted', 'complete', and 'error'.
 * Each transaction context uses exactly one tcert.
 */
class TransactionContext  {
	private static final Logger logger = Logger.getLogger(TransactionContext.class.getName());

    private Member member;
    private Chain chain;
    private MemberServices memberServices;
//    private nonce: any;
//    private binding: any;
    private TCert tcert;
    private ArrayList<String> attrs;

    public TransactionContext (Member member) {
    	this(member, null);
    }
    
    public TransactionContext (Member member, TCert tcert) {
        super();
        this.member = member;
        this.chain = member.getChain();
        this.memberServices = this.chain.getMemberServices();
        this.tcert = tcert;
  //      this.nonce = this.chain.cryptoPrimitives.generateNonce();
    }

    /**
     * Get the member with which this transaction context is associated.
     * @returns The member
     */
    public Member getMember() {
        return this.member;
    }

    /**
     * Get the chain with which this transaction context is associated.
     * @returns The chain
     */
    public Chain getChain() {
        return this.chain;
    }

    /**
     * Get the member services, or undefined if security is not enabled.
     * @returns The member services
     */
    public MemberServices getMemberServices() {
        return this.memberServices;
    }

    /**
     * Emit a specific event provided an event listener is already registered.
     */
    public void emitMyEvent(String name, Object event) {
       TransactionContext self = this;
/*
       setTimeout(function() {
         // Check if an event listener has been registered for the event
         let listeners = self.listeners(name);

         // If an event listener has been registered, emit the event
         if (listeners && listeners.length > 0) {
            self.emit(name, event);
         }
       }, 0);
*/
    }

    /**
     * Issue a deploy transaction.
     * @param deployRequest {Object} A deploy request of the form: { chaincodeID, payload, metadata, uuid, timestamp, confidentiality: { level, version, nonce }
   */
    public TransactionContext deploy(DeployRequest deployRequest) {
        debug("TransactionContext.deploy");
        debug("Received deploy request: %j", deployRequest);

        // Get a TCert to use in the deployment transaction
        /*TODO implement security
        this.tcert = getMyTCert();
        if (null == tcert) {
           debug("Failed getting a new TCert [%s]", err);
           self.emitMyEvent("error", new EventTransactionError(err));

           return self;
         }

         debug("Got a TCert successfully, continue...");
         */

         Transaction transaction = createTransaction(deployRequest, protos.Fabric.Transaction.Type.CHAINCODE_DEPLOY);

         /*TODO implement error checks
         if (err) {
                debug("Error in newBuildOrDeployTransaction [%s]", err);
                self.emitMyEvent("error", new EventTransactionError(err));

                return self;
          }

          debug("Calling TransactionContext.execute");
		*/
         
        execute(transaction);
        return this;
    }

    /**
     * Issue an invoke transaction.
     * @param invokeRequest {Object} An invoke request of the form: XXX
     */
    public TransactionContext invoke(InvokeRequest invokeRequest) {
        debug("TransactionContext.invoke");
        debug("Received invoke request: %j", invokeRequest);

        // Get a TCert to use in the invoke transaction
        setAttrs(invokeRequest.attrs);
        
        /*TODO add error check
        self.getMyTCert(function (err, tcert) {
            if (err) {
                debug('Failed getting a new TCert [%s]', err);
                self.emitMyEvent('error', new EventTransactionError(err));

                return self;
            }

            debug("Got a TCert successfully, continue...");
		*/
        Transaction transaction = invokeTransaction(invokeRequest);
        
        /*TODO add error check
              if (err) {
                debug("Error in newInvokeOrQueryTransaction [%s]", err);
                self.emitMyEvent('error', new EventTransactionError(err));

                return self;
              }

              debug("Calling TransactionContext.execute");

              return self.execute(invokeTx);
            });
        });
        return self;
        */
        return execute(transaction);
    }

    /**
     * Issue an query transaction.
     * @param queryRequest {Object} A query request of the form: XXX
     */
    public TransactionContext query(QueryRequest queryRequest) {
      debug("TransactionContext.query");
      debug("Received query request: %j", queryRequest);


      // Get a TCert to use in the query transaction
      setAttrs(queryRequest.attrs);
      
      /*TODO obtain certificates
      self.getMyTCert(function (err, tcert) {
          if (err) {
              debug('Failed getting a new TCert [%s]', err);
              self.emitMyEvent('error', new EventTransactionError(err));

              return self;
          }

          debug("Got a TCert successfully, continue...");

          self.newInvokeOrQueryTransaction(queryRequest, false, function(err, queryTx) {
            if (err) {
              debug("Error in newInvokeOrQueryTransaction [%s]", err);
              self.emitMyEvent('error', new EventTransactionError(err));

              return self;
            }

            debug("Calling TransactionContext.execute");

            return self.execute(queryTx);
          });
        });
      return self;
      */
      
      Transaction transaction = queryTransaction(queryRequest);
      return execute(transaction);
    }

   /**
    * Get the attribute names associated
    */
   public ArrayList<String> getAttrs() {
       return this.attrs;
   }

   /**
    * Set the attributes for this transaction context.
    */
   public void setAttrs(ArrayList<String> attrs) {
       this.attrs = attrs;
   }

    /**
     * Execute a transaction
     * @param tx {Transaction} The transaction.
     */
    private TransactionContext execute(Transaction tx) {
        debug("Executing transaction [%j]", tx);

        getChain().sendTransaction(tx);
        /*TODO implement security
        // Get the TCert
        self.getMyTCert();
        if (err) {
             debug("Failed getting a new TCert [%s]", err);
             return self.emit("error", new EventTransactionError(err));
        }

        if (!tcert) {
                debug("Missing TCert...");
                return self.emit("error", new EventTransactionError("Missing TCert."));
	}

        // Set nonce
        tx.pb.setNonce(self.nonce);

        // Process confidentiality
        debug("Process Confidentiality...");

        self.processConfidentiality(tx);

        debug("Sign transaction...");

        // Add the tcert
        tx.pb.setCert(tcert.publicKey);
        // sign the transaction bytes
        let txBytes = tx.pb.toBuffer();
        let derSignature = self.chain.cryptoPrimitives.ecdsaSign(tcert.privateKey.getPrivate("hex"), txBytes).toDER();
        // debug('signature: ', derSignature);
        tx.pb.setSignature(new Buffer(derSignature));

        debug("Send transaction...");
        debug("Confidentiality: ", tx.pb.getConfidentialityLevel());

        if (tx.pb.getConfidentialityLevel() == _fabricProto.ConfidentialityLevel.CONFIDENTIAL &&
               tx.pb.getType() == _fabricProto.Transaction.Type.CHAINCODE_QUERY) {
               // Need to send a different event emitter so we can catch the response
               // and perform decryption before sending the real complete response
               // to the caller
               var emitter = new events.EventEmitter();
               emitter.on("complete", function (event:EventQueryComplete) {
               debug("Encrypted: [%j]", event);
               event.result = self.decryptResult(event.result);
               debug("Decrypted: [%j]", event);
               self.emit("complete", event);
       });
                    emitter.on("error", function (event:EventTransactionError) {
                        self.emit("error", event);
                    });
                    self.getChain().sendTransaction(tx, emitter);
                } else {
                    self.getChain().sendTransaction(tx, self);
                }
            } else {
            }

        });
        return self;
    }

    TCert getMyTCert(cb:GetTCertCallback) {
    	TransactionContext self = this;
        if (!self.getChain().isSecurityEnabled() || self.tcert) {
            debug("[TransactionContext] TCert already cached.");
            return cb(null, self.tcert);
        }
        debug("[TransactionContext] No TCert cached. Retrieving one.");
        this.member.getNextTCert(self.attrs, function (err, tcert) {
            if (err) return cb(err);
            self.tcert = tcert;
            return cb(null, tcert);
        });
        */
        return null; //TODO return the correct certificate
    }

    private void processConfidentiality(Transaction transaction) {
        /* TODO implement processConfidentiality function 
    	// is confidentiality required?
        if (transaction.pb.getConfidentialityLevel() != _fabricProto.ConfidentialityLevel.CONFIDENTIAL) {
            // No confidentiality is required
            return
        }

        debug("Process Confidentiality ...");
        var self = this;

        // Set confidentiality level and protocol version
        transaction.pb.setConfidentialityProtocolVersion("1.2");

        // Generate transaction key. Common to all type of transactions
        var txKey = self.chain.cryptoPrimitives.eciesKeyGen();

        debug("txkey [%j]", txKey.pubKeyObj.pubKeyHex);
        debug("txKey.prvKeyObj %j", txKey.prvKeyObj.toString());

        var privBytes = self.chain.cryptoPrimitives.ecdsaPrivateKeyToASN1(txKey.prvKeyObj.prvKeyHex);
        debug("privBytes %s", privBytes.toString());

        // Generate stateKey. Transaction type dependent step.
        var stateKey;
        if (transaction.pb.getType() == _fabricProto.Transaction.Type.CHAINCODE_DEPLOY) {
            // The request is for a deploy
            stateKey = new Buffer(self.chain.cryptoPrimitives.aesKeyGen());
        } else if (transaction.pb.getType() == _fabricProto.Transaction.Type.CHAINCODE_INVOKE ) {
            // The request is for an execute
            // Empty state key
            stateKey = new Buffer([]);
        } else {
            // The request is for a query
            debug("Generate state key...");
            stateKey = new Buffer(self.chain.cryptoPrimitives.hmacAESTruncated(
                self.member.getEnrollment().queryStateKey,
                [CONFIDENTIALITY_1_2_STATE_KD_C6].concat(self.nonce)
            ));
        }

        // Prepare ciphertexts

        // Encrypts message to validators using self.enrollChainKey
        var chainCodeValidatorMessage1_2 = new asn1Builder.Ber.Writer();
        chainCodeValidatorMessage1_2.startSequence();
        chainCodeValidatorMessage1_2.writeBuffer(privBytes, 4);
        if (stateKey.length != 0) {
            debug("STATE KEY %j", stateKey);
            chainCodeValidatorMessage1_2.writeBuffer(stateKey, 4);
        } else {
            chainCodeValidatorMessage1_2.writeByte(4);
            chainCodeValidatorMessage1_2.writeLength(0);
        }
        chainCodeValidatorMessage1_2.endSequence();
        debug(chainCodeValidatorMessage1_2.buffer);

        debug("Using chain key [%j]", self.member.getEnrollment().chainKey);
        var ecdsaChainKey = self.chain.cryptoPrimitives.ecdsaPEMToPublicKey(
            self.member.getEnrollment().chainKey
        );

        let encMsgToValidators = self.chain.cryptoPrimitives.eciesEncryptECDSA(
            ecdsaChainKey,
            chainCodeValidatorMessage1_2.buffer
        );
        transaction.pb.setToValidators(encMsgToValidators);

        // Encrypts chaincodeID using txKey
        // debug('CHAINCODE ID %j', transaction.chaincodeID);

        let encryptedChaincodeID = self.chain.cryptoPrimitives.eciesEncrypt(
            txKey.pubKeyObj,
            transaction.pb.getChaincodeID().buffer
        );
        transaction.pb.setChaincodeID(encryptedChaincodeID);

        // Encrypts payload using txKey
        // debug('PAYLOAD ID %j', transaction.payload);
        let encryptedPayload = self.chain.cryptoPrimitives.eciesEncrypt(
            txKey.pubKeyObj,
            transaction.pb.getPayload().buffer
        );
        transaction.pb.setPayload(encryptedPayload);

        // Encrypt metadata using txKey
        if (transaction.pb.getMetadata() != null && transaction.pb.getMetadata().buffer != null) {
            debug("Metadata [%j]", transaction.pb.getMetadata().buffer);
            let encryptedMetadata = self.chain.cryptoPrimitives.eciesEncrypt(
                txKey.pubKeyObj,
                transaction.pb.getMetadata().buffer
            );
            transaction.pb.setMetadata(encryptedMetadata);
        }
        
        */
    }

    private void decryptResult(Buffer ct) {
    	/* TODO implement decryptResult function 
        let key = new Buffer(
            this.chain.cryptoPrimitives.hmacAESTruncated(
                this.member.getEnrollment().queryStateKey,
                [CONFIDENTIALITY_1_2_STATE_KD_C6].concat(this.nonce))
        );

        debug("Decrypt Result [%s]", ct.toString("hex"));
        return this.chain.cryptoPrimitives.aes256GCMDecrypt(key, ct);
        */
    }

    /**
     * Create a deploy transaction.
     * @param request {Object} A BuildRequest or DeployRequest
     */
    private void newBuildOrDeployTransaction(DeployRequest request, boolean isBuildRequest) {
      	debug("newBuildOrDeployTransaction");

        // Determine if deployment is for dev mode or net mode
        if (chain.isDevMode()) {
            // Deployment in developent mode. Build a dev mode transaction.
            newDevModeTransaction(request, isBuildRequest);
            
            /*TODO add logic implemented in callback function 
             function(err, tx) {
                if(err) {
                    return cb(err);
                } else {
                    return cb(null, tx);
                }
            });
            */
        } else {
            // Deployment in network mode. Build a net mode transaction.
//            newNetModeTransaction(request, isBuildRequest);
            
            /*TODO add logic implemented in callback function 
            function(err, tx) {
                if(err) {
                    return cb(err);
                } else {
                    return cb(null, tx);
                }
            });
            */
        }
    } // end newBuildOrDeployTransaction

    
    
    private Transaction createTransaction(TransactionRequest request, protos.Fabric.Transaction.Type transactionType) {

    	//Construct the chaincodeID
        protos.Chaincode.ChaincodeID chaincodeID =  protos.Chaincode.ChaincodeID.newBuilder()
        		.setName(request.chaincodeName)
        		.build();
//        debug("newDevModeTransaction: chaincodeID: " + JSON.stringify(chaincodeID));

        
        //convert all args to ByteString
        ArrayList<ByteString> args = new ArrayList<>(request.args.size());
        for(String arg : request.args) {
        	args.add(ByteString.copyFrom(arg.getBytes()));
        }
        
        // Set ctorMsg
        ChaincodeInput chaincodeInput = protos.Chaincode.ChaincodeInput.newBuilder()
        		.addAllArgs(args)
        		.build();
        				
        // Construct the ChaincodeSpec
        ChaincodeSpec chaincodeSpec = protos.Chaincode.ChaincodeSpec.newBuilder()
        		.setType(protos.Chaincode.ChaincodeSpec.Type.GOLANG)
        		.setChaincodeID(chaincodeID)
        		.setCtorMsg(chaincodeInput)
        		.build();
        

        // Construct the ChaincodeDeploymentSpec (i.e. the payload)
        
        ChaincodeDeploymentSpec chaincodeDeploymentSpec = protos.Chaincode.ChaincodeDeploymentSpec.newBuilder()
        		.setChaincodeSpec(chaincodeSpec)
        		.build();

        ConfidentialityLevel confidentialityLevel = request.confidential ? protos.Chaincode.ConfidentialityLevel.CONFIDENTIAL : protos.Chaincode.ConfidentialityLevel.PUBLIC;
        
        protos.Fabric.Transaction tx = protos.Fabric.Transaction.newBuilder()
        		.setType(transactionType)
        		.setChaincodeID(chaincodeID.toByteString())
        		.setPayload(chaincodeDeploymentSpec.toByteString())
        		.setTxid("aaa")
        		.setTimestamp(Timestamp.getDefaultInstance())
//TODO        		.setMetadata(request.metadata)
        		.build();


                /*TODO Set the user certificate data
                if (request.userCert) {
                	/*TODO implement user certificates
                    // cert based
                    let certRaw = new Buffer(self.tcert.publicKey);
                    // debug('========== Invoker Cert [%s]', certRaw.toString("hex"));
                    let nonceRaw = new Buffer(self.nonce);
                    let bindingMsg = Buffer.concat([certRaw, nonceRaw]);
                    // debug('========== Binding Msg [%s]', bindingMsg.toString("hex"));
                    this.binding = new Buffer(self.chain.cryptoPrimitives.hash(bindingMsg), "hex");
                    // debug('========== Binding [%s]', this.binding.toString("hex"));
                    let ctor = chaincodeSpec.getCtorMsg().toBuffer();
                    // debug('========== Ctor [%s]', ctor.toString("hex"));
                    let txmsg = Buffer.concat([ctor, this.binding]);
                    // debug('========== Payload||binding [%s]', txmsg.toString("hex"));
                    let mdsig = self.chain.cryptoPrimitives.ecdsaSign(request.userCert.privateKey.getPrivate("hex"), txmsg);
                    let sigma = new Buffer(mdsig.toDER());
                    // debug('========== Sigma [%s]', sigma.toString("hex"));
                    tx.setMetadata(sigma);
                }
                    */

        return new Transaction(tx, request.chaincodeName);

    }
    
    /**
     * Create a development mode deploy transaction.
     * @param request {Object} A development mode BuildRequest or DeployRequest
     */
    private Transaction newDevModeTransaction(DeployRequest request, boolean isBuildRequest) {
        debug("newDevModeTransaction");

        // Verify that chaincodeName is being passed
        if (null == request.chaincodeName || request.chaincodeName.equals("")) {
          throw new RuntimeException("missing chaincodeName in DeployRequest");
        }

        return createTransaction(request, protos.Fabric.Transaction.Type.CHAINCODE_DEPLOY);
    }

    /**
     * Create a network mode deploy transaction.
     * @param request {Object} A network mode BuildRequest or DeployRequest
     */

    /*TODO revisit newNetModeTransaction
    private void newNetModeTransaction(DeployRequest request, boolean isBuildRequest, cb:DeployTransactionCallback) {
        debug("newNetModeTransaction");

        let self = this;

        // Verify that chaincodePath is being passed
        if (!request.chaincodePath || request.chaincodePath === "") {
          return cb(Error("missing chaincodePath in DeployRequest"));
        }

        // Determine the user's $GOPATH
        let goPath =  process.env["GOPATH"];
        debug("$GOPATH: " + goPath);

        // Compose the path to the chaincode project directory
        let projDir = goPath + "/src/" + request.chaincodePath;
        debug("projDir: " + projDir);

        // Compute the hash of the chaincode deployment parameters
        let hash = sdk_util.GenerateParameterHash(request.chaincodePath, request.fcn, request.args);

        // Compute the hash of the project directory contents
        hash = sdk_util.GenerateDirectoryHash(goPath + "/src/", request.chaincodePath, hash);
        debug("hash: " + hash);

        // Compose the Dockerfile commands
     	  let dockerFileContents =
        "from hyperledger/fabric-baseimage" + "\n" +
     	  "COPY . $GOPATH/src/build-chaincode/" + "\n" +
     	  "WORKDIR $GOPATH" + "\n\n" +
     	  "RUN go install build-chaincode && cp src/build-chaincode/vendor/github.com/hyperledger/fabric/peer/core.yaml $GOPATH/bin && mv $GOPATH/bin/build-chaincode $GOPATH/bin/%s";

     	  // Substitute the hashStrHash for the image name
     	  dockerFileContents = util.format(dockerFileContents, hash);

     	  // Create a Docker file with dockerFileContents
     	  let dockerFilePath = projDir + "/Dockerfile";
     	  fs.writeFile(dockerFilePath, dockerFileContents, function(err) {
            if (err) {
                debug(util.format("Error writing file [%s]: %s", dockerFilePath, err));
                return cb(Error(util.format("Error writing file [%s]: %s", dockerFilePath, err)));
            }

            debug("Created Dockerfile at [%s]", dockerFilePath);

            // Create the .tar.gz file of the chaincode package
            let targzFilePath = "/tmp/deployment-package.tar.gz";
            // Create the compressed archive
            sdk_util.GenerateTarGz(projDir, targzFilePath, function(err) {
                if(err) {
                    debug(util.format("Error creating deployment archive [%s]: %s", targzFilePath, err));
                    return cb(Error(util.format("Error creating deployment archive [%s]: %s", targzFilePath, err)));
                }

                debug(util.format("Created deployment archive at [%s]", targzFilePath));

                //
                // Initialize a transaction structure
                //

                let tx = new _fabricProto.Transaction();

                //
                // Set the transaction type
                //

                if (isBuildRequest) {
                    tx.setType(_fabricProto.Transaction.Type.CHAINCODE_BUILD);
                } else {
                    tx.setType(_fabricProto.Transaction.Type.CHAINCODE_DEPLOY);
                }

                //
                // Set the chaincodeID
                //

                let chaincodeID = new _chaincodeProto.ChaincodeID();
                chaincodeID.setName(hash);
                debug("chaincodeID: " + JSON.stringify(chaincodeID));
                tx.setChaincodeID(chaincodeID.toBuffer());

                //
                // Set the payload
                //

                // Construct the ChaincodeSpec
                let chaincodeSpec = new _chaincodeProto.ChaincodeSpec();

                // Set Type -- GOLANG is the only chaincode language supported at this time
                chaincodeSpec.setType(_chaincodeProto.ChaincodeSpec.Type.GOLANG);
                // Set chaincodeID
                chaincodeSpec.setChaincodeID(chaincodeID);
                // Set ctorMsg
                let chaincodeInput = new _chaincodeProto.ChaincodeInput();
                chaincodeInput.setFunction(request.fcn);
                chaincodeInput.setArgs(request.args);
                chaincodeSpec.setCtorMsg(chaincodeInput);
                debug("chaincodeSpec: " + JSON.stringify(chaincodeSpec));

                // Construct the ChaincodeDeploymentSpec and set it as the Transaction payload
                let chaincodeDeploymentSpec = new _chaincodeProto.ChaincodeDeploymentSpec();
                chaincodeDeploymentSpec.setChaincodeSpec(chaincodeSpec);

                // Read in the .tar.zg and set it as the CodePackage in ChaincodeDeploymentSpec
                fs.readFile(targzFilePath, function(err, data) {
                    if(err) {
                        debug(util.format("Error reading deployment archive [%s]: %s", targzFilePath, err));
                        return cb(Error(util.format("Error reading deployment archive [%s]: %s", targzFilePath, err)));
                    }

                    debug(util.format("Read in deployment archive from [%s]", targzFilePath));

                    chaincodeDeploymentSpec.setCodePackage(data);
                    tx.setPayload(chaincodeDeploymentSpec.toBuffer());

                    //
                    // Set the transaction UUID
                    //

                    tx.setUuid(sdk_util.GenerateUUID());

                    //
                    // Set the transaction timestamp
                    //

                    tx.setTimestamp(sdk_util.GenerateTimestamp());

                    //
                    // Set confidentiality level
                    //

                    if (request.confidential) {
                        debug("Set confidentiality level to CONFIDENTIAL");
                        tx.setConfidentialityLevel(_fabricProto.ConfidentialityLevel.CONFIDENTIAL);
                    } else {
                        debug("Set confidentiality level to PUBLIC");
                        tx.setConfidentialityLevel(_fabricProto.ConfidentialityLevel.PUBLIC);
                    }

                    //
                    // Set request metadata
                    //

                    if (request.metadata) {
                        tx.setMetadata(request.metadata);
                    }

                    //
                    // Set the user certificate data
                    //

                    if (request.userCert) {
                        // cert based
                        let certRaw = new Buffer(self.tcert.publicKey);
                        // debug('========== Invoker Cert [%s]', certRaw.toString("hex"));
                        let nonceRaw = new Buffer(self.nonce);
                        let bindingMsg = Buffer.concat([certRaw, nonceRaw]);
                        // debug('========== Binding Msg [%s]', bindingMsg.toString("hex"));
                        self.binding = new Buffer(self.chain.cryptoPrimitives.hash(bindingMsg), "hex");
                        // debug('========== Binding [%s]', self.binding.toString("hex"));
                        let ctor = chaincodeSpec.getCtorMsg().toBuffer();
                        // debug('========== Ctor [%s]', ctor.toString("hex"));
                        let txmsg = Buffer.concat([ctor, self.binding]);
                        // debug('========== Payload||binding [%s]', txmsg.toString("hex"));
                        let mdsig = self.chain.cryptoPrimitives.ecdsaSign(request.userCert.privateKey.getPrivate("hex"), txmsg);
                        let sigma = new Buffer(mdsig.toDER());
                        // debug('========== Sigma [%s]', sigma.toString("hex"));
                        tx.setMetadata(sigma);
                    }

                    //
                    // Clean up temporary files
                    //

                    // Remove the temporary .tar.gz with the deployment contents and the Dockerfile
                    fs.unlink(targzFilePath, function(err) {
                        if(err) {
                            debug(util.format("Error deleting temporary archive [%s]: %s", targzFilePath, err));
                            return cb(Error(util.format("Error deleting temporary archive [%s]: %s", targzFilePath, err)));
                        }

                        debug("Temporary archive deleted successfully ---> " + targzFilePath);

                        fs.unlink(dockerFilePath, function(err) {
                            if(err) {
                                debug(util.format("Error deleting temporary file [%s]: %s", dockerFilePath, err));
                                return cb(Error(util.format("Error deleting temporary file [%s]: %s", dockerFilePath, err)));
                            }

                            debug("File deleted successfully ---> " + dockerFilePath);

                            //
                            // Return the deploy transaction structure
                            //

                            tx = new Transaction(tx, hash);

                            return cb(null, tx);
                        }); // end delete Dockerfile
                    }); // end delete .tar.gz
              }); // end reading .tar.zg and composing transaction
	         }); // end writing .tar.gz
	      }); // end writing Dockerfile
    }
    
    */

    
    private Transaction invokeTransaction(InvokeOrQueryRequest request) {
    	return createTransaction(request, protos.Fabric.Transaction.Type.CHAINCODE_INVOKE);
    }

    private Transaction queryTransaction(InvokeOrQueryRequest request) {
    	return createTransaction(request, protos.Fabric.Transaction.Type.CHAINCODE_QUERY);
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
      }
    private static void debug(String msg, Object... params) {
        logger.log(Level.FINE, msg, params);
      }


}  // end TransactionContext
