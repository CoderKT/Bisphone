package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    protected StdScalarDeserializer(Class<?> cls) {
        super((Class) cls);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
    }
}
