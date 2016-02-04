package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class NullNode extends ValueNode {
    public static final NullNode instance;

    static {
        instance = new NullNode();
    }

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }

    public String asText() {
        return "null";
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        serializerProvider.defaultSerializeNull(jsonGenerator);
    }

    public boolean equals(Object obj) {
        return obj == this;
    }
}
