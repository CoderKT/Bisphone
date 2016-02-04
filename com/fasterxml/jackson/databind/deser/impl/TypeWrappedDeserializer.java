package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> {
    final JsonDeserializer<Object> _deserializer;
    final TypeDeserializer _typeDeserializer;

    public TypeWrappedDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer<Object> jsonDeserializer) {
        this._typeDeserializer = typeDeserializer;
        this._deserializer = jsonDeserializer;
    }

    public Class<?> handledType() {
        return this._deserializer.handledType();
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return this._deserializer.deserializeWithType(jsonParser, deserializationContext, this._typeDeserializer);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        return this._deserializer.deserialize(jsonParser, deserializationContext, obj);
    }
}
