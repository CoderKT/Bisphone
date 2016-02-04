package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    public abstract JsonDeserializer<Object> getContentDeserializer();

    protected ContainerDeserializerBase(JavaType javaType) {
        super(javaType);
    }

    public SettableBeanProperty findBackReference(String str) {
        JsonDeserializer contentDeserializer = getContentDeserializer();
        if (contentDeserializer != null) {
            return contentDeserializer.findBackReference(str);
        }
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': type: container deserializer of type " + getClass().getName() + " returned null for 'getContentDeserializer()'");
    }
}
