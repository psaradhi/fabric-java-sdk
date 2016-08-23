// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chaincodeevent.proto

package protos;

public final class Chaincodeevent {
  private Chaincodeevent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ChaincodeEventOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protos.ChaincodeEvent)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string chaincodeID = 1;</code>
     */
    java.lang.String getChaincodeID();
    /**
     * <code>optional string chaincodeID = 1;</code>
     */
    com.google.protobuf.ByteString
        getChaincodeIDBytes();

    /**
     * <code>optional string txID = 2;</code>
     */
    java.lang.String getTxID();
    /**
     * <code>optional string txID = 2;</code>
     */
    com.google.protobuf.ByteString
        getTxIDBytes();

    /**
     * <code>optional string eventName = 3;</code>
     */
    java.lang.String getEventName();
    /**
     * <code>optional string eventName = 3;</code>
     */
    com.google.protobuf.ByteString
        getEventNameBytes();

    /**
     * <code>optional bytes payload = 4;</code>
     */
    com.google.protobuf.ByteString getPayload();
  }
  /**
   * <pre>
   *ChaincodeEvent is used for events and registrations that are specific to chaincode
   *string type - "chaincode"
   * </pre>
   *
   * Protobuf type {@code protos.ChaincodeEvent}
   */
  public  static final class ChaincodeEvent extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protos.ChaincodeEvent)
      ChaincodeEventOrBuilder {
    // Use ChaincodeEvent.newBuilder() to construct.
    private ChaincodeEvent(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ChaincodeEvent() {
      chaincodeID_ = "";
      txID_ = "";
      eventName_ = "";
      payload_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private ChaincodeEvent(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              chaincodeID_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              txID_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              eventName_ = s;
              break;
            }
            case 34: {

              payload_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protos.Chaincodeevent.internal_static_protos_ChaincodeEvent_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protos.Chaincodeevent.internal_static_protos_ChaincodeEvent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protos.Chaincodeevent.ChaincodeEvent.class, protos.Chaincodeevent.ChaincodeEvent.Builder.class);
    }

    public static final int CHAINCODEID_FIELD_NUMBER = 1;
    private volatile java.lang.Object chaincodeID_;
    /**
     * <code>optional string chaincodeID = 1;</code>
     */
    public java.lang.String getChaincodeID() {
      java.lang.Object ref = chaincodeID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        chaincodeID_ = s;
        return s;
      }
    }
    /**
     * <code>optional string chaincodeID = 1;</code>
     */
    public com.google.protobuf.ByteString
        getChaincodeIDBytes() {
      java.lang.Object ref = chaincodeID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        chaincodeID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TXID_FIELD_NUMBER = 2;
    private volatile java.lang.Object txID_;
    /**
     * <code>optional string txID = 2;</code>
     */
    public java.lang.String getTxID() {
      java.lang.Object ref = txID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        txID_ = s;
        return s;
      }
    }
    /**
     * <code>optional string txID = 2;</code>
     */
    public com.google.protobuf.ByteString
        getTxIDBytes() {
      java.lang.Object ref = txID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        txID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EVENTNAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object eventName_;
    /**
     * <code>optional string eventName = 3;</code>
     */
    public java.lang.String getEventName() {
      java.lang.Object ref = eventName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        eventName_ = s;
        return s;
      }
    }
    /**
     * <code>optional string eventName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getEventNameBytes() {
      java.lang.Object ref = eventName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        eventName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PAYLOAD_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString payload_;
    /**
     * <code>optional bytes payload = 4;</code>
     */
    public com.google.protobuf.ByteString getPayload() {
      return payload_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getChaincodeIDBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, chaincodeID_);
      }
      if (!getTxIDBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, txID_);
      }
      if (!getEventNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, eventName_);
      }
      if (!payload_.isEmpty()) {
        output.writeBytes(4, payload_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getChaincodeIDBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, chaincodeID_);
      }
      if (!getTxIDBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, txID_);
      }
      if (!getEventNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, eventName_);
      }
      if (!payload_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, payload_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof protos.Chaincodeevent.ChaincodeEvent)) {
        return super.equals(obj);
      }
      protos.Chaincodeevent.ChaincodeEvent other = (protos.Chaincodeevent.ChaincodeEvent) obj;

      boolean result = true;
      result = result && getChaincodeID()
          .equals(other.getChaincodeID());
      result = result && getTxID()
          .equals(other.getTxID());
      result = result && getEventName()
          .equals(other.getEventName());
      result = result && getPayload()
          .equals(other.getPayload());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + CHAINCODEID_FIELD_NUMBER;
      hash = (53 * hash) + getChaincodeID().hashCode();
      hash = (37 * hash) + TXID_FIELD_NUMBER;
      hash = (53 * hash) + getTxID().hashCode();
      hash = (37 * hash) + EVENTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getEventName().hashCode();
      hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
      hash = (53 * hash) + getPayload().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.Chaincodeevent.ChaincodeEvent parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(protos.Chaincodeevent.ChaincodeEvent prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     *ChaincodeEvent is used for events and registrations that are specific to chaincode
     *string type - "chaincode"
     * </pre>
     *
     * Protobuf type {@code protos.ChaincodeEvent}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protos.ChaincodeEvent)
        protos.Chaincodeevent.ChaincodeEventOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protos.Chaincodeevent.internal_static_protos_ChaincodeEvent_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protos.Chaincodeevent.internal_static_protos_ChaincodeEvent_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protos.Chaincodeevent.ChaincodeEvent.class, protos.Chaincodeevent.ChaincodeEvent.Builder.class);
      }

      // Construct using protos.Chaincodeevent.ChaincodeEvent.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        chaincodeID_ = "";

        txID_ = "";

        eventName_ = "";

        payload_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protos.Chaincodeevent.internal_static_protos_ChaincodeEvent_descriptor;
      }

      public protos.Chaincodeevent.ChaincodeEvent getDefaultInstanceForType() {
        return protos.Chaincodeevent.ChaincodeEvent.getDefaultInstance();
      }

      public protos.Chaincodeevent.ChaincodeEvent build() {
        protos.Chaincodeevent.ChaincodeEvent result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protos.Chaincodeevent.ChaincodeEvent buildPartial() {
        protos.Chaincodeevent.ChaincodeEvent result = new protos.Chaincodeevent.ChaincodeEvent(this);
        result.chaincodeID_ = chaincodeID_;
        result.txID_ = txID_;
        result.eventName_ = eventName_;
        result.payload_ = payload_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protos.Chaincodeevent.ChaincodeEvent) {
          return mergeFrom((protos.Chaincodeevent.ChaincodeEvent)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protos.Chaincodeevent.ChaincodeEvent other) {
        if (other == protos.Chaincodeevent.ChaincodeEvent.getDefaultInstance()) return this;
        if (!other.getChaincodeID().isEmpty()) {
          chaincodeID_ = other.chaincodeID_;
          onChanged();
        }
        if (!other.getTxID().isEmpty()) {
          txID_ = other.txID_;
          onChanged();
        }
        if (!other.getEventName().isEmpty()) {
          eventName_ = other.eventName_;
          onChanged();
        }
        if (other.getPayload() != com.google.protobuf.ByteString.EMPTY) {
          setPayload(other.getPayload());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protos.Chaincodeevent.ChaincodeEvent parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protos.Chaincodeevent.ChaincodeEvent) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object chaincodeID_ = "";
      /**
       * <code>optional string chaincodeID = 1;</code>
       */
      public java.lang.String getChaincodeID() {
        java.lang.Object ref = chaincodeID_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          chaincodeID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string chaincodeID = 1;</code>
       */
      public com.google.protobuf.ByteString
          getChaincodeIDBytes() {
        java.lang.Object ref = chaincodeID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          chaincodeID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string chaincodeID = 1;</code>
       */
      public Builder setChaincodeID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        chaincodeID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string chaincodeID = 1;</code>
       */
      public Builder clearChaincodeID() {
        
        chaincodeID_ = getDefaultInstance().getChaincodeID();
        onChanged();
        return this;
      }
      /**
       * <code>optional string chaincodeID = 1;</code>
       */
      public Builder setChaincodeIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        chaincodeID_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object txID_ = "";
      /**
       * <code>optional string txID = 2;</code>
       */
      public java.lang.String getTxID() {
        java.lang.Object ref = txID_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          txID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string txID = 2;</code>
       */
      public com.google.protobuf.ByteString
          getTxIDBytes() {
        java.lang.Object ref = txID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          txID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string txID = 2;</code>
       */
      public Builder setTxID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        txID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string txID = 2;</code>
       */
      public Builder clearTxID() {
        
        txID_ = getDefaultInstance().getTxID();
        onChanged();
        return this;
      }
      /**
       * <code>optional string txID = 2;</code>
       */
      public Builder setTxIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        txID_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object eventName_ = "";
      /**
       * <code>optional string eventName = 3;</code>
       */
      public java.lang.String getEventName() {
        java.lang.Object ref = eventName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          eventName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string eventName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getEventNameBytes() {
        java.lang.Object ref = eventName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          eventName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string eventName = 3;</code>
       */
      public Builder setEventName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        eventName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string eventName = 3;</code>
       */
      public Builder clearEventName() {
        
        eventName_ = getDefaultInstance().getEventName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string eventName = 3;</code>
       */
      public Builder setEventNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        eventName_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes payload = 4;</code>
       */
      public com.google.protobuf.ByteString getPayload() {
        return payload_;
      }
      /**
       * <code>optional bytes payload = 4;</code>
       */
      public Builder setPayload(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        payload_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes payload = 4;</code>
       */
      public Builder clearPayload() {
        
        payload_ = getDefaultInstance().getPayload();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:protos.ChaincodeEvent)
    }

    // @@protoc_insertion_point(class_scope:protos.ChaincodeEvent)
    private static final protos.Chaincodeevent.ChaincodeEvent DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protos.Chaincodeevent.ChaincodeEvent();
    }

    public static protos.Chaincodeevent.ChaincodeEvent getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ChaincodeEvent>
        PARSER = new com.google.protobuf.AbstractParser<ChaincodeEvent>() {
      public ChaincodeEvent parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ChaincodeEvent(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ChaincodeEvent> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ChaincodeEvent> getParserForType() {
      return PARSER;
    }

    public protos.Chaincodeevent.ChaincodeEvent getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protos_ChaincodeEvent_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protos_ChaincodeEvent_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024chaincodeevent.proto\022\006protos\"W\n\016Chainc" +
      "odeEvent\022\023\n\013chaincodeID\030\001 \001(\t\022\014\n\004txID\030\002 " +
      "\001(\t\022\021\n\teventName\030\003 \001(\t\022\017\n\007payload\030\004 \001(\014b" +
      "\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_protos_ChaincodeEvent_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protos_ChaincodeEvent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protos_ChaincodeEvent_descriptor,
        new java.lang.String[] { "ChaincodeID", "TxID", "EventName", "Payload", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
