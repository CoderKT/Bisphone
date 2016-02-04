package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public interface JsonSerializable {
    void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);
}
