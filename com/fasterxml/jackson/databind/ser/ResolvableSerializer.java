package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.SerializerProvider;

public interface ResolvableSerializer {
    void resolve(SerializerProvider serializerProvider);
}
