package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;

public class MappingJsonFactory extends JsonFactory {
    public MappingJsonFactory() {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }
}
