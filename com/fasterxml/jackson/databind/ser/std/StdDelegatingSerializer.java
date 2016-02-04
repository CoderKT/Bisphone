package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;

public class StdDelegatingSerializer extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer {
    protected final Converter<Object, ?> _converter;
    protected final JsonSerializer<Object> _delegateSerializer;
    protected final JavaType _delegateType;

    public StdDelegatingSerializer(Converter<Object, ?> converter, JavaType javaType, JsonSerializer<?> jsonSerializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateSerializer = jsonSerializer;
    }

    protected StdDelegatingSerializer withDelegate(Converter<Object, ?> converter, JavaType javaType, JsonSerializer<?> jsonSerializer) {
        if (getClass() == StdDelegatingSerializer.class) {
            return new StdDelegatingSerializer(converter, javaType, jsonSerializer);
        }
        throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }

    public void resolve(SerializerProvider serializerProvider) {
        if (this._delegateSerializer != null && (this._delegateSerializer instanceof ResolvableSerializer)) {
            ((ResolvableSerializer) this._delegateSerializer).resolve(serializerProvider);
        }
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        if (this._delegateSerializer == null) {
            JavaType javaType = this._delegateType;
            if (javaType == null) {
                javaType = this._converter.getOutputType(serializerProvider.getTypeFactory());
            }
            return withDelegate(this._converter, javaType, serializerProvider.findValueSerializer(javaType, beanProperty));
        } else if (!(this._delegateSerializer instanceof ContextualSerializer)) {
            return this;
        } else {
            JsonSerializer handleSecondaryContextualization = serializerProvider.handleSecondaryContextualization(this._delegateSerializer, beanProperty);
            if (handleSecondaryContextualization == this._delegateSerializer) {
                return this;
            }
            return withDelegate(this._converter, this._delegateType, handleSecondaryContextualization);
        }
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object convertValue = convertValue(obj);
        if (convertValue == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else {
            this._delegateSerializer.serialize(convertValue, jsonGenerator, serializerProvider);
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        this._delegateSerializer.serializeWithType(convertValue(obj), jsonGenerator, serializerProvider, typeSerializer);
    }

    public boolean isEmpty(Object obj) {
        return this._delegateSerializer.isEmpty(convertValue(obj));
    }

    protected Object convertValue(Object obj) {
        return this._converter.convert(obj);
    }
}
