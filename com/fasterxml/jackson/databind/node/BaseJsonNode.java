package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;

public abstract class BaseJsonNode extends JsonNode implements JsonSerializable {
    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    protected BaseJsonNode() {
    }
}
