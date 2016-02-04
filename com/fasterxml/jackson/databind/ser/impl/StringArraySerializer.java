package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.type.TypeFactory;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements ContextualSerializer {
    private static final JavaType VALUE_TYPE;
    public static final StringArraySerializer instance;
    protected final JsonSerializer<Object> _elementSerializer;

    static {
        VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
        instance = new StringArraySerializer();
    }

    protected StringArraySerializer() {
        super(String[].class, null);
        this._elementSerializer = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super((ArraySerializerBase) stringArraySerializer, beanProperty);
        this._elementSerializer = jsonSerializer;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer serializerInstance;
        if (beanProperty != null) {
            Annotated member = beanProperty.getMember();
            if (member != null) {
                Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(member);
                if (findContentSerializer != null) {
                    serializerInstance = serializerProvider.serializerInstance(member, findContentSerializer);
                    if (serializerInstance == null) {
                        serializerInstance = this._elementSerializer;
                    }
                    serializerInstance = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
                    if (serializerInstance != null) {
                        serializerInstance = serializerProvider.findValueSerializer(String.class, beanProperty);
                    } else {
                        serializerInstance = serializerProvider.handleSecondaryContextualization(serializerInstance, beanProperty);
                    }
                    if (isDefaultSerializer(serializerInstance)) {
                        serializerInstance = null;
                    }
                    return serializerInstance != this._elementSerializer ? this : new StringArraySerializer(this, beanProperty, serializerInstance);
                }
            }
        }
        serializerInstance = null;
        if (serializerInstance == null) {
            serializerInstance = this._elementSerializer;
        }
        serializerInstance = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
        if (serializerInstance != null) {
            serializerInstance = serializerProvider.handleSecondaryContextualization(serializerInstance, beanProperty);
        } else {
            serializerInstance = serializerProvider.findValueSerializer(String.class, beanProperty);
        }
        if (isDefaultSerializer(serializerInstance)) {
            serializerInstance = null;
        }
        if (serializerInstance != this._elementSerializer) {
        }
    }

    public boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public boolean hasSingleElement(String[] strArr) {
        return strArr.length == 1;
    }

    public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int length = strArr.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }
    }

    private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
            }
        }
    }
}
