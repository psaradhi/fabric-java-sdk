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
    comments = "Source: devops.proto")
public class DevopsGrpc {

  private DevopsGrpc() {}

  public static final String SERVICE_NAME = "protos.Devops";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.DevopsOuterClass.Secret,
      protos.Fabric.Response> METHOD_LOGIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "Login"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.DevopsOuterClass.Secret.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Chaincode.ChaincodeSpec,
      protos.Chaincode.ChaincodeDeploymentSpec> METHOD_BUILD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "Build"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeSpec.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeDeploymentSpec.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Chaincode.ChaincodeSpec,
      protos.Chaincode.ChaincodeDeploymentSpec> METHOD_DEPLOY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "Deploy"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeSpec.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeDeploymentSpec.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Chaincode.ChaincodeInvocationSpec,
      protos.Fabric.Response> METHOD_INVOKE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "Invoke"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeInvocationSpec.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.Chaincode.ChaincodeInvocationSpec,
      protos.Fabric.Response> METHOD_QUERY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "Query"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Chaincode.ChaincodeInvocationSpec.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.DevopsOuterClass.Secret,
      protos.Fabric.Response> METHOD_EXP_GET_APPLICATION_TCERT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "EXP_GetApplicationTCert"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.DevopsOuterClass.Secret.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.DevopsOuterClass.Secret,
      protos.Fabric.Response> METHOD_EXP_PREPARE_FOR_TX =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "EXP_PrepareForTx"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.DevopsOuterClass.Secret.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.DevopsOuterClass.SigmaInput,
      protos.Fabric.Response> METHOD_EXP_PRODUCE_SIGMA =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "EXP_ProduceSigma"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.DevopsOuterClass.SigmaInput.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<protos.DevopsOuterClass.ExecuteWithBinding,
      protos.Fabric.Response> METHOD_EXP_EXECUTE_WITH_BINDING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protos.Devops", "EXP_ExecuteWithBinding"),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.DevopsOuterClass.ExecuteWithBinding.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(protos.Fabric.Response.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DevopsStub newStub(io.grpc.Channel channel) {
    return new DevopsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DevopsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DevopsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static DevopsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DevopsFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class DevopsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Log in - passed Secret object and returns Response object, where
     * msg is the security context to be used in subsequent invocations
     * </pre>
     */
    public void login(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOGIN, responseObserver);
    }

    /**
     * <pre>
     * Build the chaincode package.
     * </pre>
     */
    public void build(protos.Chaincode.ChaincodeSpec request,
        io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_BUILD, responseObserver);
    }

    /**
     * <pre>
     * Deploy the chaincode package to the chain.
     * </pre>
     */
    public void deploy(protos.Chaincode.ChaincodeSpec request,
        io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DEPLOY, responseObserver);
    }

    /**
     * <pre>
     * Invoke chaincode.
     * </pre>
     */
    public void invoke(protos.Chaincode.ChaincodeInvocationSpec request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INVOKE, responseObserver);
    }

    /**
     * <pre>
     * Query chaincode.
     * </pre>
     */
    public void query(protos.Chaincode.ChaincodeInvocationSpec request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_QUERY, responseObserver);
    }

    /**
     * <pre>
     * Retrieve a TCert.
     * </pre>
     */
    public void eXPGetApplicationTCert(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXP_GET_APPLICATION_TCERT, responseObserver);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public void eXPPrepareForTx(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXP_PREPARE_FOR_TX, responseObserver);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public void eXPProduceSigma(protos.DevopsOuterClass.SigmaInput request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXP_PRODUCE_SIGMA, responseObserver);
    }

    /**
     * <pre>
     * Execute a transaction with a specific binding
     * </pre>
     */
    public void eXPExecuteWithBinding(protos.DevopsOuterClass.ExecuteWithBinding request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXP_EXECUTE_WITH_BINDING, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOGIN,
            asyncUnaryCall(
              new MethodHandlers<
                protos.DevopsOuterClass.Secret,
                protos.Fabric.Response>(
                  this, METHODID_LOGIN)))
          .addMethod(
            METHOD_BUILD,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Chaincode.ChaincodeSpec,
                protos.Chaincode.ChaincodeDeploymentSpec>(
                  this, METHODID_BUILD)))
          .addMethod(
            METHOD_DEPLOY,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Chaincode.ChaincodeSpec,
                protos.Chaincode.ChaincodeDeploymentSpec>(
                  this, METHODID_DEPLOY)))
          .addMethod(
            METHOD_INVOKE,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Chaincode.ChaincodeInvocationSpec,
                protos.Fabric.Response>(
                  this, METHODID_INVOKE)))
          .addMethod(
            METHOD_QUERY,
            asyncUnaryCall(
              new MethodHandlers<
                protos.Chaincode.ChaincodeInvocationSpec,
                protos.Fabric.Response>(
                  this, METHODID_QUERY)))
          .addMethod(
            METHOD_EXP_GET_APPLICATION_TCERT,
            asyncUnaryCall(
              new MethodHandlers<
                protos.DevopsOuterClass.Secret,
                protos.Fabric.Response>(
                  this, METHODID_EXP_GET_APPLICATION_TCERT)))
          .addMethod(
            METHOD_EXP_PREPARE_FOR_TX,
            asyncUnaryCall(
              new MethodHandlers<
                protos.DevopsOuterClass.Secret,
                protos.Fabric.Response>(
                  this, METHODID_EXP_PREPARE_FOR_TX)))
          .addMethod(
            METHOD_EXP_PRODUCE_SIGMA,
            asyncUnaryCall(
              new MethodHandlers<
                protos.DevopsOuterClass.SigmaInput,
                protos.Fabric.Response>(
                  this, METHODID_EXP_PRODUCE_SIGMA)))
          .addMethod(
            METHOD_EXP_EXECUTE_WITH_BINDING,
            asyncUnaryCall(
              new MethodHandlers<
                protos.DevopsOuterClass.ExecuteWithBinding,
                protos.Fabric.Response>(
                  this, METHODID_EXP_EXECUTE_WITH_BINDING)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class DevopsStub extends io.grpc.stub.AbstractStub<DevopsStub> {
    private DevopsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DevopsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevopsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DevopsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Log in - passed Secret object and returns Response object, where
     * msg is the security context to be used in subsequent invocations
     * </pre>
     */
    public void login(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Build the chaincode package.
     * </pre>
     */
    public void build(protos.Chaincode.ChaincodeSpec request,
        io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_BUILD, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deploy the chaincode package to the chain.
     * </pre>
     */
    public void deploy(protos.Chaincode.ChaincodeSpec request,
        io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DEPLOY, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Invoke chaincode.
     * </pre>
     */
    public void invoke(protos.Chaincode.ChaincodeInvocationSpec request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INVOKE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Query chaincode.
     * </pre>
     */
    public void query(protos.Chaincode.ChaincodeInvocationSpec request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_QUERY, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve a TCert.
     * </pre>
     */
    public void eXPGetApplicationTCert(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXP_GET_APPLICATION_TCERT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public void eXPPrepareForTx(protos.DevopsOuterClass.Secret request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXP_PREPARE_FOR_TX, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public void eXPProduceSigma(protos.DevopsOuterClass.SigmaInput request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXP_PRODUCE_SIGMA, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute a transaction with a specific binding
     * </pre>
     */
    public void eXPExecuteWithBinding(protos.DevopsOuterClass.ExecuteWithBinding request,
        io.grpc.stub.StreamObserver<protos.Fabric.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXP_EXECUTE_WITH_BINDING, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class DevopsBlockingStub extends io.grpc.stub.AbstractStub<DevopsBlockingStub> {
    private DevopsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DevopsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevopsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DevopsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Log in - passed Secret object and returns Response object, where
     * msg is the security context to be used in subsequent invocations
     * </pre>
     */
    public protos.Fabric.Response login(protos.DevopsOuterClass.Secret request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOGIN, getCallOptions(), request);
    }

    /**
     * <pre>
     * Build the chaincode package.
     * </pre>
     */
    public protos.Chaincode.ChaincodeDeploymentSpec build(protos.Chaincode.ChaincodeSpec request) {
      return blockingUnaryCall(
          getChannel(), METHOD_BUILD, getCallOptions(), request);
    }

    /**
     * <pre>
     * Deploy the chaincode package to the chain.
     * </pre>
     */
    public protos.Chaincode.ChaincodeDeploymentSpec deploy(protos.Chaincode.ChaincodeSpec request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DEPLOY, getCallOptions(), request);
    }

    /**
     * <pre>
     * Invoke chaincode.
     * </pre>
     */
    public protos.Fabric.Response invoke(protos.Chaincode.ChaincodeInvocationSpec request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INVOKE, getCallOptions(), request);
    }

    /**
     * <pre>
     * Query chaincode.
     * </pre>
     */
    public protos.Fabric.Response query(protos.Chaincode.ChaincodeInvocationSpec request) {
      return blockingUnaryCall(
          getChannel(), METHOD_QUERY, getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve a TCert.
     * </pre>
     */
    public protos.Fabric.Response eXPGetApplicationTCert(protos.DevopsOuterClass.Secret request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXP_GET_APPLICATION_TCERT, getCallOptions(), request);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public protos.Fabric.Response eXPPrepareForTx(protos.DevopsOuterClass.Secret request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXP_PREPARE_FOR_TX, getCallOptions(), request);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public protos.Fabric.Response eXPProduceSigma(protos.DevopsOuterClass.SigmaInput request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXP_PRODUCE_SIGMA, getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute a transaction with a specific binding
     * </pre>
     */
    public protos.Fabric.Response eXPExecuteWithBinding(protos.DevopsOuterClass.ExecuteWithBinding request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXP_EXECUTE_WITH_BINDING, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class DevopsFutureStub extends io.grpc.stub.AbstractStub<DevopsFutureStub> {
    private DevopsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DevopsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevopsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DevopsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Log in - passed Secret object and returns Response object, where
     * msg is the security context to be used in subsequent invocations
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> login(
        protos.DevopsOuterClass.Secret request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request);
    }

    /**
     * <pre>
     * Build the chaincode package.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Chaincode.ChaincodeDeploymentSpec> build(
        protos.Chaincode.ChaincodeSpec request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_BUILD, getCallOptions()), request);
    }

    /**
     * <pre>
     * Deploy the chaincode package to the chain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Chaincode.ChaincodeDeploymentSpec> deploy(
        protos.Chaincode.ChaincodeSpec request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DEPLOY, getCallOptions()), request);
    }

    /**
     * <pre>
     * Invoke chaincode.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> invoke(
        protos.Chaincode.ChaincodeInvocationSpec request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INVOKE, getCallOptions()), request);
    }

    /**
     * <pre>
     * Query chaincode.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> query(
        protos.Chaincode.ChaincodeInvocationSpec request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_QUERY, getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve a TCert.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> eXPGetApplicationTCert(
        protos.DevopsOuterClass.Secret request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXP_GET_APPLICATION_TCERT, getCallOptions()), request);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> eXPPrepareForTx(
        protos.DevopsOuterClass.Secret request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXP_PREPARE_FOR_TX, getCallOptions()), request);
    }

    /**
     * <pre>
     * Prepare for performing a TX, which will return a binding that can later be used to sign and then execute a transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> eXPProduceSigma(
        protos.DevopsOuterClass.SigmaInput request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXP_PRODUCE_SIGMA, getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute a transaction with a specific binding
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.Fabric.Response> eXPExecuteWithBinding(
        protos.DevopsOuterClass.ExecuteWithBinding request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXP_EXECUTE_WITH_BINDING, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_BUILD = 1;
  private static final int METHODID_DEPLOY = 2;
  private static final int METHODID_INVOKE = 3;
  private static final int METHODID_QUERY = 4;
  private static final int METHODID_EXP_GET_APPLICATION_TCERT = 5;
  private static final int METHODID_EXP_PREPARE_FOR_TX = 6;
  private static final int METHODID_EXP_PRODUCE_SIGMA = 7;
  private static final int METHODID_EXP_EXECUTE_WITH_BINDING = 8;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DevopsImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(DevopsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((protos.DevopsOuterClass.Secret) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_BUILD:
          serviceImpl.build((protos.Chaincode.ChaincodeSpec) request,
              (io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec>) responseObserver);
          break;
        case METHODID_DEPLOY:
          serviceImpl.deploy((protos.Chaincode.ChaincodeSpec) request,
              (io.grpc.stub.StreamObserver<protos.Chaincode.ChaincodeDeploymentSpec>) responseObserver);
          break;
        case METHODID_INVOKE:
          serviceImpl.invoke((protos.Chaincode.ChaincodeInvocationSpec) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_QUERY:
          serviceImpl.query((protos.Chaincode.ChaincodeInvocationSpec) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_EXP_GET_APPLICATION_TCERT:
          serviceImpl.eXPGetApplicationTCert((protos.DevopsOuterClass.Secret) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_EXP_PREPARE_FOR_TX:
          serviceImpl.eXPPrepareForTx((protos.DevopsOuterClass.Secret) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_EXP_PRODUCE_SIGMA:
          serviceImpl.eXPProduceSigma((protos.DevopsOuterClass.SigmaInput) request,
              (io.grpc.stub.StreamObserver<protos.Fabric.Response>) responseObserver);
          break;
        case METHODID_EXP_EXECUTE_WITH_BINDING:
          serviceImpl.eXPExecuteWithBinding((protos.DevopsOuterClass.ExecuteWithBinding) request,
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_LOGIN,
        METHOD_BUILD,
        METHOD_DEPLOY,
        METHOD_INVOKE,
        METHOD_QUERY,
        METHOD_EXP_GET_APPLICATION_TCERT,
        METHOD_EXP_PREPARE_FOR_TX,
        METHOD_EXP_PRODUCE_SIGMA,
        METHOD_EXP_EXECUTE_WITH_BINDING);
  }

}
