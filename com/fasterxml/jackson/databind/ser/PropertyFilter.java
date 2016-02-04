package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface PropertyFilter {
    void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter);
}
