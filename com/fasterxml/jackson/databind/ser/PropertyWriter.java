package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public abstract class PropertyWriter {
    public abstract void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public abstract void serializeAsOmittedField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);
}
