package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class RawSerializer<T> extends StdSerializer<T> {
    public RawSerializer(Class<?> cls) {
        super(cls, false);
    }

    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeRawValue(t.toString());
    }

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(t, jsonGenerator);
        serialize(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(t, jsonGenerator);
    }
}
