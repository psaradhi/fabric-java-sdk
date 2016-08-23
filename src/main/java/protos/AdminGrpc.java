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
    comments = "Source: server_admin.proto")
public class AdminGrpc {

  private AdminGrpc() {}

  public static final String SERVICE_NAME = "protos.Admin";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.ServerAdmin.ServerStatus> METHOD_GET_STATUS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Admin", "GetStatus"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.ServerAdmin.ServerStatus.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.ServerAdmin.ServerStatus> METHOD_START_SERVER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Admin", "StartServer"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.ServerAdmin.ServerStatus.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      protos.ServerAdmin.ServerStatus> METHOD_STOP_SERVER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Admin", "StopServer"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.ServerAdmin.ServerStatus.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdminStub newStub(io.grpc.Channel channel) {
    return new AdminStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdminBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AdminBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static AdminFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AdminFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class AdminImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Return the serve status.
     * </pre>
     */
    public void getStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_STATUS, responseObserver);
    }

    /**
     */
    public void startServer(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_START_SERVER, responseObserver);
    }

    /**
     */
    public void stopServer(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STOP_SERVER, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_STATUS,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.ServerAdmin.ServerStatus>(
                  this, METHODID_GET_STATUS)))
          .addMethod(
            METHOD_START_SERVER,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.ServerAdmin.ServerStatus>(
                  this, METHODID_START_SERVER)))
          .addMethod(
            METHOD_STOP_SERVER,
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                protos.ServerAdmin.ServerStatus>(
                  this, METHODID_STOP_SERVER)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class AdminStub extends io.grpc.stub.AbstractStub<AdminStub> {
    private AdminStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the serve status.
     * </pre>
     */
    public void getStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startServer(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_START_SERVER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopServer(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STOP_SERVER, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class AdminBlockingStub extends io.grpc.stub.AbstractStub<AdminBlockingStub> {
    private AdminBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the serve status.
     * </pre>
     */
    public protos.ServerAdmin.ServerStatus getStatus(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_STATUS, getCallOptions(), request);
    }

    /**
     */
    public protos.ServerAdmin.ServerStatus startServer(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_START_SERVER, getCallOptions(), request);
    }

    /**
     */
    public protos.ServerAdmin.ServerStatus stopServer(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_STOP_SERVER, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class AdminFutureStub extends io.grpc.stub.AbstractStub<AdminFutureStub> {
    private AdminFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the serve status.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.ServerAdmin.ServerStatus> getStatus(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.ServerAdmin.ServerStatus> startServer(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_START_SERVER, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.ServerAdmin.ServerStatus> stopServer(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STOP_SERVER, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STATUS = 0;
  private static final int METHODID_START_SERVER = 1;
  private static final int METHODID_STOP_SERVER = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdminImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(AdminImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus>) responseObserver);
          break;
        case METHODID_START_SERVER:
          serviceImpl.startServer((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus>) responseObserver);
          break;
        case METHODID_STOP_SERVER:
          serviceImpl.stopServer((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<protos.ServerAdmin.ServerStatus>) responseObserver);
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
        METHOD_GET_STATUS,
        METHOD_START_SERVER,
        METHOD_STOP_SERVER);
  }

}
