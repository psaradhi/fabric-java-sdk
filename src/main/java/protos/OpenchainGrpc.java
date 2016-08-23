package protos;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0-pre2)",
    comments = "Source: api.proto")
public class OpenchainGrpc {

  private OpenchainGrpc() {}

  public static final String SERVICE_NAME = "protos.Openchain";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.Fabric.BlockchainInfo> METHOD_GET_BLOCKCHAIN_INFO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Openchain", "GetBlockchainInfo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.BlockchainInfo.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Api.BlockNumber,
      protos.Fabric.Block> METHOD_GET_BLOCK_BY_NUMBER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Openchain", "GetBlockByNumber"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Api.BlockNumber.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Block.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.Api.BlockCount> METHOD_GET_BLOCK_COUNT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Openchain", "GetBlockCount"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Api.BlockCount.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.Fabric.PeersMessage> METHOD_GET_PEERS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Openchain", "GetPeers"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.PeersMessage.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OpenchainStub newStub(io.grpc.Channel channel) {
    return new OpenchainStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OpenchainBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OpenchainBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static OpenchainFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OpenchainFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class OpenchainImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * GetBlockchainInfo returns information about the blockchain ledger such as
     * height, current block hash, and previous block hash.
     * </pre>
     */
    public void getBlockchainInfo(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Fabric.BlockchainInfo> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BLOCKCHAIN_INFO, responseObserver);
    }

    /**
     * <pre>
     * GetBlockByNumber returns the data contained within a specific block in the
     * blockchain. The genesis block is block zero.
     * </pre>
     */
    public void getBlockByNumber(protos.Api.BlockNumber request,
        io.grpc.stub.StreamObserver<protos.Fabric.Block> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BLOCK_BY_NUMBER, responseObserver);
    }

    /**
     * <pre>
     * GetBlockCount returns the current number of blocks in the blockchain data
     * structure.
     * </pre>
     */
    public void getBlockCount(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Api.BlockCount> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BLOCK_COUNT, responseObserver);
    }

    /**
     * <pre>
     * GetPeers returns a list of all peer nodes currently connected to the target
     * peer.
     * </pre>
     */
    public void getPeers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Fabric.PeersMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PEERS, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BLOCKCHAIN_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.Fabric.BlockchainInfo>(
                  this, METHODID_GET_BLOCKCHAIN_INFO)))
          .addMethod(
            METHOD_GET_BLOCK_BY_NUMBER,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Api.BlockNumber,
                protos.Fabric.Block>(
                  this, METHODID_GET_BLOCK_BY_NUMBER)))
          .addMethod(
            METHOD_GET_BLOCK_COUNT,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.Api.BlockCount>(
                  this, METHODID_GET_BLOCK_COUNT)))
          .addMethod(
            METHOD_GET_PEERS,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.Fabric.PeersMessage>(
                  this, METHODID_GET_PEERS)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class OpenchainStub extends io.grpc.stub.AbstractStub<OpenchainStub> {
    private OpenchainStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenchainStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenchainStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenchainStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetBlockchainInfo returns information about the blockchain ledger such as
     * height, current block hash, and previous block hash.
     * </pre>
     */
    public void getBlockchainInfo(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Fabric.BlockchainInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCKCHAIN_INFO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetBlockByNumber returns the data contained within a specific block in the
     * blockchain. The genesis block is block zero.
     * </pre>
     */
    public void getBlockByNumber(protos.Api.BlockNumber request,
        io.grpc.stub.StreamObserver<protos.Fabric.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCK_BY_NUMBER, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetBlockCount returns the current number of blocks in the blockchain data
     * structure.
     * </pre>
     */
    public void getBlockCount(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Api.BlockCount> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCK_COUNT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * GetPeers returns a list of all peer nodes currently connected to the target
     * peer.
     * </pre>
     */
    public void getPeers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.Fabric.PeersMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PEERS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class OpenchainBlockingStub extends io.grpc.stub.AbstractStub<OpenchainBlockingStub> {
    private OpenchainBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenchainBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenchainBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenchainBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetBlockchainInfo returns information about the blockchain ledger such as
     * height, current block hash, and previous block hash.
     * </pre>
     */
    public protos.Fabric.BlockchainInfo getBlockchainInfo(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BLOCKCHAIN_INFO, getCallOptions(), request);
    }

    /**
     * <pre>
     * GetBlockByNumber returns the data contained within a specific block in the
     * blockchain. The genesis block is block zero.
     * </pre>
     */
    public protos.Fabric.Block getBlockByNumber(protos.Api.BlockNumber request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BLOCK_BY_NUMBER, getCallOptions(), request);
    }

    /**
     * <pre>
     * GetBlockCount returns the current number of blocks in the blockchain data
     * structure.
     * </pre>
     */
    public protos.Api.BlockCount getBlockCount(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BLOCK_COUNT, getCallOptions(), request);
    }

    /**
     * <pre>
     * GetPeers returns a list of all peer nodes currently connected to the target
     * peer.
     * </pre>
     */
    public protos.Fabric.PeersMessage getPeers(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PEERS, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class OpenchainFutureStub extends io.grpc.stub.AbstractStub<OpenchainFutureStub> {
    private OpenchainFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenchainFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenchainFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenchainFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetBlockchainInfo returns information about the blockchain ledger such as
     * height, current block hash, and previous block hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.BlockchainInfo> getBlockchainInfo(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCKCHAIN_INFO, getCallOptions()), request);
    }

    /**
     * <pre>
     * GetBlockByNumber returns the data contained within a specific block in the
     * blockchain. The genesis block is block zero.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Block> getBlockByNumber(
        protos.Api.BlockNumber request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCK_BY_NUMBER, getCallOptions()), request);
    }

    /**
     * <pre>
     * GetBlockCount returns the current number of blocks in the blockchain data
     * structure.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Api.BlockCount> getBlockCount(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BLOCK_COUNT, getCallOptions()), request);
    }

    /**
     * <pre>
     * GetPeers returns a list of all peer nodes currently connected to the target
     * peer.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.PeersMessage> getPeers(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PEERS, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BLOCKCHAIN_INFO = 0;
  private static final int METHODID_GET_BLOCK_BY_NUMBER = 1;
  private static final int METHODID_GET_BLOCK_COUNT = 2;
  private static final int METHODID_GET_PEERS = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OpenchainImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(OpenchainImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BLOCKCHAIN_INFO:
          serviceImpl.getBlockchainInfo((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.BlockchainInfo>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_NUMBER:
          serviceImpl.getBlockByNumber((protos.Api.BlockNumber) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Block>) responseObserver);
          break;
        case METHODID_GET_BLOCK_COUNT:
          serviceImpl.getBlockCount((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.Api.BlockCount>) responseObserver);
          break;
        case METHODID_GET_PEERS:
          serviceImpl.getPeers((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.PeersMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_GET_BLOCKCHAIN_INFO,
        METHOD_GET_BLOCK_BY_NUMBER,
        METHOD_GET_BLOCK_COUNT,
        METHOD_GET_PEERS);
  }

}
