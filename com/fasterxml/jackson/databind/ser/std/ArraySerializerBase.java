package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;

public abstract class ArraySerializerBase<T> extends ContainerSerializer<T> {
    protected final BeanProperty _property;

    protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    protected ArraySerializerBase(Class<T> cls) {
        super((Class) cls);
        this._property = null;
    }

    protected ArraySerializerBase(Class<T> cls, BeanProperty beanProperty) {
        super((Class) cls);
        this._property = beanProperty;
    }

    protected ArraySerializerBase(ArraySerializerBase<?> arraySerializerBase, BeanProperty beanProperty) {
        super(arraySerializerBase._handledType, false);
        this._property = beanProperty;
    }

    public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && hasSingleElement(t)) {
            serializeContents(t, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray();
        serializeContents(t, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
        serializeContents(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
    }
}
