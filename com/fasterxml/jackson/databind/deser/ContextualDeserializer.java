package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public interface ContextualDeserializer {
    JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty);
}
