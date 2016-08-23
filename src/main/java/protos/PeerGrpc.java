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
    comments = "Source: fabric.proto")
public class PeerGrpc {

  private PeerGrpc() {}

  public static final String SERVICE_NAME = "protos.Peer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Fabric.Message,
      protos.Fabric.Message> METHOD_CHAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "protos.Peer", "Chat"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Message.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Message.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Fabric.Transaction,
      protos.Fabric.Response> METHOD_PROCESS_TRANSACTION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Peer", "ProcessTransaction"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Transaction.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PeerStub newStub(io.grpc.Channel channel) {
    return new PeerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PeerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PeerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static PeerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PeerFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class PeerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Accepts a stream of Message during chat session, while receiving
     * other Message (e.g. from other peers).
     * </pre>
     */
    public io.grpc.stub.StreamObserver<protos.Fabric.Message> chat(
        io.grpc.stub.StreamObserver<protos.Fabric.Message> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_CHAT, responseObserver);
    }

    /**
     * <pre>
     * Process a transaction from a remote source.
     * </pre>
     */
    public void processTransaction(protos.Fabric.Transaction request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PROCESS_TRANSACTION, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CHAT,
            asyncBidiStreamingCall(
              new MethodHandlers<
                protos.Fabric.Message,
                protos.Fabric.Message>(
                  this, METHODID_CHAT)))
          .addMethod(
            METHOD_PROCESS_TRANSACTION,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Fabric.Transaction,
                protos.Fabric.Response>(
                  this, METHODID_PROCESS_TRANSACTION)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class PeerStub extends io.grpc.stub.AbstractStub<PeerStub> {
    private PeerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerStub(channel, callOptions);
    }

    /**
     * <pre>
     * Accepts a stream of Message during chat session, while receiving
     * other Message (e.g. from other peers).
     * </pre>
     */
    public io.grpc.stub.StreamObserver<protos.Fabric.Message> chat(
        io.grpc.stub.StreamObserver<protos.Fabric.Message> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_CHAT, getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Process a transaction from a remote source.
     * </pre>
     */
    public void processTransaction(protos.Fabric.Transaction request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PROCESS_TRANSACTION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class PeerBlockingStub extends io.grpc.stub.AbstractStub<PeerBlockingStub> {
    private PeerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Process a transaction from a remote source.
     * </pre>
     */
    public protos.Fabric.Response processTransaction(protos.Fabric.Transaction request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PROCESS_TRANSACTION, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class PeerFutureStub extends io.grpc.stub.AbstractStub<PeerFutureStub> {
    private PeerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Process a transaction from a remote source.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> processTransaction(
        protos.Fabric.Transaction request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PROCESS_TRANSACTION, getCallOptions()), request);
    }
  }

  private static final int METHODID_PROCESS_TRANSACTION = 0;
  private static final int METHODID_CHAT = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PeerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(PeerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROCESS_TRANSACTION:
          serviceImpl.processTransaction((protos.Fabric.Transaction) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
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
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<protos.Fabric.Message>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CHAT,
        METHOD_PROCESS_TRANSACTION);
  }

}
