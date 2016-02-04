package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Date;

public class StdKeySerializer extends StdSerializer<Object> {
    public StdKeySerializer() {
        super(Object.class);
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (obj instanceof Date) {
            serializerProvider.defaultSerializeDateKey((Date) obj, jsonGenerator);
        } else {
            jsonGenerator.writeFieldName(obj.toString());
        }
    }
}
