package org.hyperledger.fabricjavasdk;

import java.util.concurrent.TimeUnit;

import org.hyperledger.fabricjavasdk.util.Logger;

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

	/**
	 * Construct client for accessing Peer server using the existing channel.
	 */
	public PeerClient(ManagedChannelBuilder<?> channelBuilder) {
		channel = channelBuilder.build();
		blockingStub = PeerGrpc.newBlockingStub(channel);
		asyncStub = PeerGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public Response processTransaction(protos.Fabric.Transaction transaction) {
		Response response;
		try {
			response = blockingStub.processTransaction(transaction);
		} catch (StatusRuntimeException e) {
			logger.warn("RPC failed: %s", e.getStatus());
			return null;
		}
		logger.info("Status: \"%s\" at %s, %s", response.getStatusValue(), response.getStatus().name(),
				String.valueOf(response.getMsg().toStringUtf8()));

		return response;

	}

	@Override
	public void finalize() {
		try {
			shutdown();
		} catch (InterruptedException e) {
			logger.debug("Failed to shutdown the PeerClient");
		}
	}
}
