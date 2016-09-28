package org.hyperledger.fabricjavasdk;

import org.hyperledger.fabricjavasdk.util.Logger;

import protos.Fabric.Response;

/**
 * The Peer class represents a peer to which HFC sends deploy, invoke, or query requests.
 */
class Peer {
	private static final Logger logger = Logger.getLogger(Peer.class);

    private String url;
    private Chain chain;    
    private PeerClient peerClient;
    private DevopsClient devopsClient;

    /**
     * Constructor for a peer given the endpoint config for the peer.
     * @param {string} url The URL of
     * @param {Chain} The chain of which this peer is a member.
     * @returns {Peer} The new peer.
     */
    public Peer(String url, String pem, Chain chain) {
        this.url = url;
        this.chain = chain;   
        Endpoint ep = new Endpoint(url, pem);
        this.peerClient = new PeerClient(ep.getChannelBuilder());
        this.devopsClient = new DevopsClient(ep.getChannelBuilder());
    }

    /**
     * Get the chain of which this peer is a member.
     * @returns {Chain} The chain of which this peer is a member.
     */
    public Chain getChain() {
        return this.chain;
    }

    /**
     * Get the URL of the peer.
     * @returns {string} Get the URL associated with the peer.
     */
    public String getUrl() {
        return this.url;
    }

    
    public void query(QueryRequest request) {
    	devopsClient.query(request);
    }

    public void invoke(InvokeRequest request) {
    	devopsClient.invoke(request);
    }

    public void deploy(DeployRequest request) {
    	devopsClient.deploy(request);
    }

    /**
     * Send a transaction to this peer.
     * @param tx A transaction
     * @param eventEmitter The event emitter
     */
    public void sendTransaction(Transaction transaction) {

        logger.debug("peer.sendTransaction");

        // Send the transaction to the peer node via grpc
        // The rpc specification on the peer side is:
        //     rpc ProcessTransaction(Transaction) returns (Response) {}
        Response response = peerClient.processTransaction(transaction.getTransaction());

        /*TODO add error check
        if (err) {
                logger.debug("peer.sendTransaction: error=%s", err);
                return eventEmitter.emit('error', new EventTransactionError(err));
        }
        */

        logger.debug("peer.sendTransaction: received %s", response.getMsg().toStringUtf8());

        // Check transaction type here, as invoke is an asynchronous call,
        // whereas a deploy and a query are synchonous calls. As such,
        // invoke will emit 'submitted' and 'error', while a deploy/query
        // will emit 'complete' and 'error'.

        /* TODO handle response
        let txType = tx.pb.getType();
        switch (txType) {
           case protos.Fabric.Transaction.Type.CHAINCODE_DEPLOY: // async
                  if (response.status != "SUCCESS") {
			throw new RuntimeException(response);
		}
                     // Deploy transaction has been completed
                     if (!response.msg || response.msg === "") {
                        eventEmitter.emit("error", new EventTransactionError("the deploy response is missing the transaction UUID"));
                     } else {
                        let event = new EventDeploySubmitted(response.msg.toString(), tx.chaincodeID);
                        logger.debug("EventDeploySubmitted event: %s", event);
                        eventEmitter.emit("submitted", event);
                        self.waitForDeployComplete(eventEmitter,event);
                     }
                     // Deploy completed with status "FAILURE" or "UNDEFINED"
                     eventEmitter.emit("error", new EventTransactionError(response));
                  }
                  break;
               case protos.Fabric.Transaction.Type.CHAINCODE_INVOKE: // async
                  if (response.status === "SUCCESS") {
                     // Invoke transaction has been submitted
                     if (!response.msg || response.msg === "") {
                        eventEmitter.emit("error", new EventTransactionError("the invoke response is missing the transaction UUID"));
                     } else {
                        eventEmitter.emit("submitted", new EventInvokeSubmitted(response.msg.toString()));
                        self.waitForInvokeComplete(eventEmitter);
                     }
                  } else {
                     // Invoke completed with status "FAILURE" or "UNDEFINED"
                     eventEmitter.emit("error", new EventTransactionError(response));
                  }
                  break;
               case protos.Fabric.Transaction.Type.CHAINCODE_QUERY: // sync
                  if (response.status === "SUCCESS") {
                     // Query transaction has been completed
                     eventEmitter.emit("complete", new EventQueryComplete(response.msg));
                  } else {
                     // Query completed with status "FAILURE" or "UNDEFINED"
                     eventEmitter.emit("error", new EventTransactionError(response));
                  }
                  break;
               default: // not implemented
                  eventEmitter.emit("error", new EventTransactionError("processTransaction for this transaction type is not yet implemented!"));
            }
          });
          */
    }

    /**
     * TODO: Temporary hack to wait until the deploy event has hopefully completed.
     * This does not detect if an error occurs in the peer or chaincode when deploying.
     * When peer event listening is added to the SDK, this will be implemented correctly.
     */

    /*TODO check waitForDeployComplete
    private void waitForDeployComplete(events.EventEmitter eventEmitter, EventDeploySubmitted submitted) {
        let waitTime = this.chain.getDeployWaitTime();
        logger.debug("waiting %d seconds before emitting deploy complete event",waitTime);
        setTimeout(
           function() {
              let event = new EventDeployComplete(
                  submitted.uuid,
                  submitted.chaincodeID,
                  "TODO: get actual results; waited "+waitTime+" seconds and assumed deploy was successful"
              );
              eventEmitter.emit("complete",event);
           },
           waitTime * 1000
        );
    }
    */

    /**
     * TODO: Temporary hack to wait until the deploy event has hopefully completed.
     * This does not detect if an error occurs in the peer or chaincode when deploying.
     * When peer event listening is added to the SDK, this will be implemented correctly.
     */
    
    /*TODO check waitForInvokeComplete
    private void waitForInvokeComplete(events.EventEmitter eventEmitter) {
        let waitTime = this.chain.getInvokeWaitTime();
        logger.debug("waiting %d seconds before emitting invoke complete event",waitTime);
        setTimeout(
           function() {
              eventEmitter.emit("complete",new EventInvokeComplete("waited "+waitTime+" seconds and assumed invoke was successful"));
           },
           waitTime * 1000
        );
    }
    */

    /**
     * Remove the peer from the chain.
     */
    public void remove() {
        throw new RuntimeException("TODO: implement"); //TODO implement remove
    }

} // end Peer
