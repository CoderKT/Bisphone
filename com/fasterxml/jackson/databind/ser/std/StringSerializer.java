package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class StringSerializer extends NonTypedScalarSerializerBase<String> {
    public StringSerializer() {
        super(String.class);
    }

    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(str);
    }
}
