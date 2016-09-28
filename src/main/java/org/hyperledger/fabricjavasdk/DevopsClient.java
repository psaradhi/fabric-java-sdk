package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.hyperledger.fabricjavasdk.util.Logger;

import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import protos.Chaincode.ChaincodeDeploymentSpec;
import protos.Chaincode.ChaincodeInvocationSpec;
import protos.Chaincode.ChaincodeSpec;
import protos.DevopsGrpc;
import protos.DevopsGrpc.DevopsBlockingStub;
import protos.DevopsGrpc.DevopsStub;
import protos.Fabric.Response;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class DevopsClient {
  private static final Logger logger = Logger.getLogger(DevopsClient.class);

  private final ManagedChannel channel;
  private final DevopsBlockingStub blockingStub;
  private final DevopsStub asyncStub;
  
  /** Construct client for accessing Peer server using the existing channel. */
  public DevopsClient(ManagedChannelBuilder<?> channelBuilder) {
    channel = channelBuilder.build();
    blockingStub = DevopsGrpc.newBlockingStub(channel);
    asyncStub = DevopsGrpc.newStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /**
   * Blocking unary call example.  Calls getFeature and prints the response.
   */
  public void query() {
    QueryRequest request = new QueryRequest();
    request.setArgs(new ArrayList<String>(Arrays.asList("query", "a")));
    query(request);
  }
  
  public void query(QueryRequest request) {
	    logger.info("query");

	    protos.Chaincode.ChaincodeInvocationSpec ispec = getInvocationSpec(request);

	    Response response;
		try {
	      response = blockingStub.query(ispec);
	    } catch (StatusRuntimeException e) {
	      logger.warn("RPC failed: %s", e.getStatus());
	      return;
	    }
	      logger.info("Status: \"%s\" at %s, %s",
	          response.getStatusValue(),
	          response.getStatus().name(),
	          String.valueOf(response.getMsg().toStringUtf8()));
	          
  }

  ChaincodeSpec getChaincodeSpec(TransactionRequest request) {
	  
	    protos.Chaincode.ChaincodeID cid = protos.Chaincode.ChaincodeID
				.newBuilder()
				.setName(chaincodeName)
				.setPath(request.getChaincodePath())
				.build();

	    ArrayList<ByteString> args = new ArrayList<>(request.getArgs().size());
	    for(String arg : request.getArgs()) {
	    	args.add(ByteString.copyFrom(arg.getBytes()));
	    }

	    protos.Chaincode.ChaincodeInput input = protos.Chaincode.ChaincodeInput
				.newBuilder()
//				.setFunction()
				.addAllArgs(args)
				.build();

	    protos.Chaincode.ChaincodeSpec spec = protos.Chaincode.ChaincodeSpec
				.newBuilder()
				.setType(protos.Chaincode.ChaincodeSpec.Type.GOLANG)
				.setChaincodeID(cid)
				.setCtorMsg(input)
				.build();

	    return spec;
  	}
  
  ChaincodeInvocationSpec getInvocationSpec(TransactionRequest request) {

	  return protos.Chaincode.ChaincodeInvocationSpec
	    		.newBuilder()
	    		.setChaincodeSpec(getChaincodeSpec(request))
	    		.build();
	    
  }
  

  public void invoke(InvokeRequest request) {
	    logger.info("invoke");
	  
	    protos.Chaincode.ChaincodeInvocationSpec ispec = getInvocationSpec(request);

	    Response response;
		try {
	      response = blockingStub.invoke(ispec);
	    } catch (StatusRuntimeException e) {
	      logger.warn("RPC failed: %s", e.getStatus());
	      return;
	    }
	      logger.info("Status: \"%s\" at %s, %s",
	          response.getStatusValue(),
	          response.getStatus().name(),
	          String.valueOf(response.getMsg().toStringUtf8()));
	          
}

  public void invoke() { //TODO: What is the purpose of this invoke()?
	    InvokeRequest request = new InvokeRequest();
	    request.setArgs(new ArrayList<String>(Arrays.asList("invoke", "a", "b", "20")));
	    invoke(request);
  }


  
  String chaincodeName = "";
  

  public void deploy() { //TODO: What is the purpose of this invoke()?
	    DeployRequest request = new DeployRequest();
	    request.setArgs(new ArrayList<String>(Arrays.asList("init", "a", "1000", "b", "2000")));
	    deploy(request);
  }
  /**
   * Blocking unary call example.  Calls getFeature and prints the response.
   */
  public void deploy(DeployRequest request) {
    logger.info("deploy	");

    ChaincodeSpec spec = getChaincodeSpec(request);
    ChaincodeDeploymentSpec response;
	try {
      response = blockingStub.deploy(spec);
    } catch (StatusRuntimeException e) {
      logger.warn("RPC failed: %s", e.getStatus());
      return;
    }
		chaincodeName =           response.getChaincodeSpec().getChaincodeID().getName();
      logger.info("Status: \"%s\" at %s, %s",
          response.getChaincodeSpec().getChaincodeID().getName(),
          response.getExecEnv().toString(),
          response.getExecEnv().toString());
  }

  
  /** Issues several different requests and then exits. */
  public static void main(String[] args) throws InterruptedException {

	Endpoint ep = new Endpoint("grpc://localhost:7051", null);
    DevopsClient client = new DevopsClient(ep.getChannelBuilder());
    try {
      client.deploy();
      client.query();
      client.invoke();
      client.query();
      client.query();
    } finally {
      client.shutdown();
    }
  }

}
