package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class BooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {
    protected final boolean _forPrimitive;

    public BooleanSerializer(boolean z) {
        super(Boolean.class);
        this._forPrimitive = z;
    }

    public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBoolean(bool.booleanValue());
    }
}
