/*
 *  Copyright 2016 DTCC, Fujitsu Australia Software Technology - All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 	  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.hyperledger.fabric.sdk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.hyperledger.fabric.sdk.util.Logger;
import org.hyperledger.protos.Fabric;
import org.hyperledger.protos.Fabric.Response;
import org.hyperledger.protos.PeerGrpc;
import org.hyperledger.protos.PeerGrpc.PeerBlockingStub;
import org.hyperledger.protos.PeerGrpc.PeerStub;

import java.util.concurrent.TimeUnit;

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

	public Response processTransaction(Fabric.Transaction transaction) {
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
