package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected final JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public abstract AsArraySerializerBase<T> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer);

    protected AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        boolean z2 = false;
        super(cls, false);
        this._elementType = javaType;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
    }

    protected AsArraySerializerBase(AsArraySerializerBase<?> asArraySerializerBase, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super((ContainerSerializer) asArraySerializerBase);
        this._elementType = asArraySerializerBase._elementType;
        this._staticTyping = asArraySerializerBase._staticTyping;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = asArraySerializerBase._dynamicSerializers;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        TypeSerializer forProperty;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        if (typeSerializer != null) {
            forProperty = typeSerializer.forProperty(beanProperty);
        } else {
            forProperty = typeSerializer;
        }
        JsonSerializer jsonSerializer = null;
        if (beanProperty != null) {
            Annotated member = beanProperty.getMember();
            if (member != null) {
                Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(member);
                if (findContentSerializer != null) {
                    jsonSerializer = serializerProvider.serializerInstance(member, findContentSerializer);
                }
            }
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._elementSerializer;
        }
        jsonSerializer = findConvertingContentSerializer(serializerProvider, beanProperty, jsonSerializer);
        if (jsonSerializer != null) {
            jsonSerializer = serializerProvider.handleSecondaryContextualization(jsonSerializer, beanProperty);
        } else if (this._elementType != null && ((this._staticTyping && this._elementType.getRawClass() != Object.class) || hasContentTypeAnnotation(serializerProvider, beanProperty))) {
            jsonSerializer = serializerProvider.findValueSerializer(this._elementType, beanProperty);
        }
        if (jsonSerializer == this._elementSerializer && beanProperty == this._property && this._valueTypeSerializer == forProperty) {
            return this;
        }
        return withResolved(beanProperty, forProperty, jsonSerializer);
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

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
        serializeContents(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer((Class) cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }
}
