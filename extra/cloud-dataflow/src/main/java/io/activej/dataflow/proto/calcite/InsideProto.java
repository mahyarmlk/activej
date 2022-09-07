// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: inner/inside.proto

package io.activej.dataflow.proto.calcite;

public final class InsideProto {
  private InsideProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface InsideOrBuilder extends
      // @@protoc_insertion_point(interface_extends:test.inner.Inside)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 index = 1;</code>
     * @return The index.
     */
    int getIndex();
  }
  /**
   * Protobuf type {@code test.inner.Inside}
   */
  public static final class Inside extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:test.inner.Inside)
      InsideOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Inside.newBuilder() to construct.
    private Inside(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Inside() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Inside();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Inside(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              index_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.activej.dataflow.proto.calcite.InsideProto.internal_static_test_inner_Inside_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.activej.dataflow.proto.calcite.InsideProto.internal_static_test_inner_Inside_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.activej.dataflow.proto.calcite.InsideProto.Inside.class, io.activej.dataflow.proto.calcite.InsideProto.Inside.Builder.class);
    }

    public static final int INDEX_FIELD_NUMBER = 1;
    private int index_;
    /**
     * <code>int32 index = 1;</code>
     * @return The index.
     */
    @java.lang.Override
    public int getIndex() {
      return index_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (index_ != 0) {
        output.writeInt32(1, index_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (index_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, index_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof io.activej.dataflow.proto.calcite.InsideProto.Inside)) {
        return super.equals(obj);
      }
      io.activej.dataflow.proto.calcite.InsideProto.Inside other = (io.activej.dataflow.proto.calcite.InsideProto.Inside) obj;

      if (getIndex()
          != other.getIndex()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getIndex();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static io.activej.dataflow.proto.calcite.InsideProto.Inside parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(io.activej.dataflow.proto.calcite.InsideProto.Inside prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
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
     * Protobuf type {@code test.inner.Inside}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:test.inner.Inside)
        io.activej.dataflow.proto.calcite.InsideProto.InsideOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return io.activej.dataflow.proto.calcite.InsideProto.internal_static_test_inner_Inside_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return io.activej.dataflow.proto.calcite.InsideProto.internal_static_test_inner_Inside_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                io.activej.dataflow.proto.calcite.InsideProto.Inside.class, io.activej.dataflow.proto.calcite.InsideProto.Inside.Builder.class);
      }

      // Construct using io.activej.dataflow.proto.calcite.InsideProto.Inside.newBuilder()
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
      @java.lang.Override
      public Builder clear() {
        super.clear();
        index_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return io.activej.dataflow.proto.calcite.InsideProto.internal_static_test_inner_Inside_descriptor;
      }

      @java.lang.Override
      public io.activej.dataflow.proto.calcite.InsideProto.Inside getDefaultInstanceForType() {
        return io.activej.dataflow.proto.calcite.InsideProto.Inside.getDefaultInstance();
      }

      @java.lang.Override
      public io.activej.dataflow.proto.calcite.InsideProto.Inside build() {
        io.activej.dataflow.proto.calcite.InsideProto.Inside result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public io.activej.dataflow.proto.calcite.InsideProto.Inside buildPartial() {
        io.activej.dataflow.proto.calcite.InsideProto.Inside result = new io.activej.dataflow.proto.calcite.InsideProto.Inside(this);
        result.index_ = index_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof io.activej.dataflow.proto.calcite.InsideProto.Inside) {
          return mergeFrom((io.activej.dataflow.proto.calcite.InsideProto.Inside)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(io.activej.dataflow.proto.calcite.InsideProto.Inside other) {
        if (other == io.activej.dataflow.proto.calcite.InsideProto.Inside.getDefaultInstance()) return this;
        if (other.getIndex() != 0) {
          setIndex(other.getIndex());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        io.activej.dataflow.proto.calcite.InsideProto.Inside parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (io.activej.dataflow.proto.calcite.InsideProto.Inside) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int index_ ;
      /**
       * <code>int32 index = 1;</code>
       * @return The index.
       */
      @java.lang.Override
      public int getIndex() {
        return index_;
      }
      /**
       * <code>int32 index = 1;</code>
       * @param value The index to set.
       * @return This builder for chaining.
       */
      public Builder setIndex(int value) {
        
        index_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 index = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearIndex() {
        
        index_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:test.inner.Inside)
    }

    // @@protoc_insertion_point(class_scope:test.inner.Inside)
    private static final io.activej.dataflow.proto.calcite.InsideProto.Inside DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new io.activej.dataflow.proto.calcite.InsideProto.Inside();
    }

    public static io.activej.dataflow.proto.calcite.InsideProto.Inside getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Inside>
        PARSER = new com.google.protobuf.AbstractParser<Inside>() {
      @java.lang.Override
      public Inside parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Inside(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Inside> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Inside> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public io.activej.dataflow.proto.calcite.InsideProto.Inside getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_test_inner_Inside_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_test_inner_Inside_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022inner/inside.proto\022\ntest.inner\"\027\n\006Insi" +
      "de\022\r\n\005index\030\001 \001(\005B2\n!io.activej.dataflow" +
      ".proto.calciteB\013InsideProtoP\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_test_inner_Inside_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_test_inner_Inside_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_test_inner_Inside_descriptor,
        new java.lang.String[] { "Index", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
