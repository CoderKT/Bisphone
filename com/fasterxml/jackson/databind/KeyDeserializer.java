package com.fasterxml.jackson.databind;

public abstract class KeyDeserializer {

    public abstract class None extends KeyDeserializer {
    }

    public abstract Object deserializeKey(String str, DeserializationContext deserializationContext);
}
