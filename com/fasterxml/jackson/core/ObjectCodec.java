package com.fasterxml.jackson.core;

public abstract class ObjectCodec extends TreeCodec {
    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj);

    protected ObjectCodec() {
    }
}
