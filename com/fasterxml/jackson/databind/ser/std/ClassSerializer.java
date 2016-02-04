package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClassSerializer extends StdScalarSerializer<Class<?>> {
    public ClassSerializer() {
        super(Class.class, false);
    }

    public void serialize(Class<?> cls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(cls.getName());
    }
}
