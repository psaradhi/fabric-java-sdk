package org.hyperledger.fabricjavasdk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import protos.Fabric.Response;
import protos.PeerGrpc;
import protos.PeerGrpc.PeerBlockingStub;
import protos.PeerGrpc.PeerStub;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class PeerClient {
  private static final Logger logger = Logger.getLogger(PeerClient.class.getName());

  private final ManagedChannel channel;
  private final PeerBlockingStub blockingStub;
  private final PeerStub asyncStub;

  /** Construct client for accessing Peer server at {@code host:port}. */
  public PeerClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
  }

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
    info("query");

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
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
      info("Status: \"{0}\" at {1}, {2}",
          response.getStatusValue(),
          response.getStatus().name(),
          String.valueOf(response.getMsg().toStringUtf8()));
  }

  public Response processTransaction(protos.Fabric.Transaction transaction) {
	    Response response;
		try {
	      response = blockingStub.processTransaction(transaction);
	    } catch (StatusRuntimeException e) {
	      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
	      return null;
	    }
	      info("Status: \"{0}\" at {1}, {2}",
	          response.getStatusValue(),
	          response.getStatus().name(),
	          String.valueOf(response.getMsg().toStringUtf8()));
	      
	    return response;
	  
  }
  
  /**
   * Blocking unary call example.  Calls getFeature and prints the response.
   */
  public void deploy() {
    info("deploy	");

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
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
      info("Status: \"{0}\" at {1}, {2}",
          response.getStatusValue(),
          response.getStatus().name(),
          response.getMsg().toStringUtf8());
  }

  
  /** Issues several different requests and then exits. */
  public static void main(String[] args) throws InterruptedException {

    PeerClient client = new PeerClient("localhost", 7051);
    try {
      client.deploy();
      client.query();
    } finally {
      client.shutdown();
    }
  }

  private static void info(String msg, Object... params) {
    logger.log(Level.INFO, msg, params);
  }

}
