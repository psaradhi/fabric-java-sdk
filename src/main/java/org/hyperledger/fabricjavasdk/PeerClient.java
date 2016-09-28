package org.hyperledger.fabricjavasdk;

import java.util.concurrent.TimeUnit;

import org.hyperledger.fabricjavasdk.util.Logger;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import protos.Fabric.Response;
import protos.PeerGrpc;
import protos.PeerGrpc.PeerBlockingStub;
import protos.PeerGrpc.PeerStub;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class PeerClient {
  private static final Logger logger = Logger.getLogger(PeerClient.class);

  private final ManagedChannel channel;
  private final PeerBlockingStub blockingStub;
  private final PeerStub asyncStub;

  
  /** Construct client for accessing Peer server using the existing channel. */
  public PeerClient(ManagedChannelBuilder<?> channelBuilder) {
    channel = channelBuilder.build();
    blockingStub = PeerGrpc.newBlockingStub(channel);
    asyncStub = PeerGrpc.newStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /**
   * Blocking unary call example.  Calls getFeature and prints the response.
   */
  public void query() {
    logger.info("query");

    protos.Chaincode.ChaincodeID cid = protos.Chaincode.ChaincodeID
			.newBuilder()
			.setName("mycc")
			.build();


    protos.Chaincode.ChaincodeInput input = protos.Chaincode.ChaincodeInput
			.newBuilder()
//			.setFunction()
			.addArgs(ByteString.copyFrom("query".getBytes()))
			.addArgs(ByteString.copyFrom("a".getBytes()))
			.build();

    protos.Chaincode.ChaincodeSpec spec = protos.Chaincode.ChaincodeSpec
			.newBuilder()
			.setType(protos.Chaincode.ChaincodeSpec.Type.GOLANG)
			.setChaincodeID(cid)
			.setCtorMsg(input)
			.build();

    protos.Chaincode.ChaincodeInvocationSpec ispec = protos.Chaincode.ChaincodeInvocationSpec
    		.newBuilder()
    		.setChaincodeSpec(spec)
    		.build();

    protos.Fabric.Transaction transaction = protos.Fabric.Transaction
			.newBuilder()
			.setType(protos.Fabric.Transaction.Type.CHAINCODE_QUERY)
			.setChaincodeID(cid.toByteString())
			.setPayload(ispec.toByteString())
//			.setMetadata()
			.setTxid("aaa")
			.setTimestamp(Timestamp.getDefaultInstance())
//			.setConfidentialityLevel()
//			.setConfidentialityProtocolVersion()
//			.setNonce()
//			.setToValidators()
//			.setCert()
//			.setSignature()
			.build();

    Response response;
	try {
      response = blockingStub.processTransaction(transaction);
    } catch (StatusRuntimeException e) {
      logger.warn("RPC failed: %s", e.getStatus());
      return;
    }
      logger.info("Status: \"%s\" at %s, %s",
          response.getStatusValue(),
          response.getStatus().name(),
          String.valueOf(response.getMsg().toStringUtf8()));
  }

  public Response processTransaction(protos.Fabric.Transaction transaction) {
	    Response response;
		try {
	      response = blockingStub.processTransaction(transaction);
	    } catch (StatusRuntimeException e) {
	      logger.warn("RPC failed: %s", e.getStatus());
	      return null;
	    }
	      logger.info("Status: \"%s\" at %s, %s",
	          response.getStatusValue(),
	          response.getStatus().name(),
	          String.valueOf(response.getMsg().toStringUtf8()));
	      
	    return response;
	  
  }
  
  /**
   * Blocking unary call example.  Calls getFeature and prints the response.
   */
  public void deploy() {
    logger.info("deploy	");

    protos.Chaincode.ChaincodeID cid = protos.Chaincode.ChaincodeID
			.newBuilder()
			.setName("mycc")
			.setPath("")
			.build();


    protos.Chaincode.ChaincodeInput input = protos.Chaincode.ChaincodeInput
			.newBuilder()
//			.setFunction()
			.addArgs(ByteString.copyFrom("init".getBytes()))
			.addArgs(ByteString.copyFrom("a".getBytes()))
			.addArgs(ByteString.copyFrom("100".getBytes()))
			.addArgs(ByteString.copyFrom("b".getBytes()))
			.addArgs(ByteString.copyFrom("200".getBytes()))
			.build();

    protos.Chaincode.ChaincodeSpec spec = protos.Chaincode.ChaincodeSpec
			.newBuilder()
			.setType(protos.Chaincode.ChaincodeSpec.Type.GOLANG)
			.setChaincodeID(cid)
			.setCtorMsg(input)
			.build();

    protos.Chaincode.ChaincodeDeploymentSpec dspec = protos.Chaincode.ChaincodeDeploymentSpec
    		.newBuilder()
    		.setChaincodeSpec(spec)
    		.build();

    protos.Fabric.Transaction transaction = protos.Fabric.Transaction
			.newBuilder()
			.setType(protos.Fabric.Transaction.Type.CHAINCODE_DEPLOY)
			.setChaincodeID(cid.toByteString())
			.setPayload(dspec.toByteString())
//			.setMetadata()
			.setTxid("aaa")
			.setTimestamp(Timestamp.getDefaultInstance())
//			.setConfidentialityLevel()
//			.setConfidentialityProtocolVersion()
//			.setNonce()
//			.setToValidators()
//			.setCert()
//			.setSignature()
			.build();

    Response response;
	try {
      response = blockingStub.processTransaction(transaction);
    } catch (StatusRuntimeException e) {
      logger.warn("RPC failed: %s", e.getStatus());
      return;
    }
      logger.info("Status: \"%s\" at %s, %s",
          response.getStatusValue(),
          response.getStatus().name(),
          response.getMsg().toStringUtf8());
  }

  
  /** Issues several different requests and then exits. */
  public static void main(String[] args) throws InterruptedException {

	Endpoint ep = new Endpoint("grpc://localhost:7051", null);
    PeerClient client = new PeerClient(ep.getChannelBuilder());
    try {
      client.deploy();
      client.query();
    } finally {
      client.shutdown();
    }
  }

}
