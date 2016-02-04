package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

@JacksonStdImpl
public class ToStringSerializer extends StdSerializer<Object> {
    public static final ToStringSerializer instance;

    static {
        instance = new ToStringSerializer();
    }

    public ToStringSerializer() {
        super(Object.class);
    }

    public boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        String obj2 = obj.toString();
        if (obj2 == null || obj2.length() == 0) {
            return true;
        }
        return false;
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(obj.toString());
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(obj, jsonGenerator);
        serialize(obj, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(obj, jsonGenerator);
    }
}
