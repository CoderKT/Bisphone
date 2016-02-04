package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer<Object> {
    protected final String _message;

    public FailingDeserializer(String str) {
        super(Object.class);
        this._message = str;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        throw deserializationContext.mappingException(this._message);
    }
}
