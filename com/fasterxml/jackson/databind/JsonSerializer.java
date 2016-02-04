package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;

public abstract class JsonSerializer<T> {

    public abstract class None extends JsonSerializer<Object> {
    }

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public JsonSerializer<T> unwrappingSerializer(NameTransformer nameTransformer) {
        return this;
    }

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        Class handledType = handledType();
        if (handledType == null) {
            handledType = t.getClass();
        }
        throw new UnsupportedOperationException("Type id handling not implemented for type " + handledType.getName());
    }

    public Class<T> handledType() {
        return null;
    }

    public boolean isEmpty(T t) {
        return t == null;
    }

    public boolean usesObjectId() {
        return false;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }
}
