package com.fasterxml.jackson.core;

public class JsonGenerationException extends JsonProcessingException {
    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }
}
