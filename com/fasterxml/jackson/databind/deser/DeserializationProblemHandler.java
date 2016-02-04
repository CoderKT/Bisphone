package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public abstract class DeserializationProblemHandler {
    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) {
        return false;
    }
}
