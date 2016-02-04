package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;

public interface ResolvableDeserializer {
    void resolve(DeserializationContext deserializationContext);
}
