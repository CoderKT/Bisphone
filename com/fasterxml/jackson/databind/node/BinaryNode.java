package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Arrays;

public class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE;
    protected final byte[] _data;

    static {
        EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    }

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public static BinaryNode valueOf(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BINARY_NODE;
        }
        return new BinaryNode(bArr);
    }

    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), this._data, 0, this._data.length);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof BinaryNode)) {
            return false;
        }
        return Arrays.equals(((BinaryNode) obj)._data, this._data);
    }

    public int hashCode() {
        return this._data == null ? -1 : this._data.length;
    }

    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
