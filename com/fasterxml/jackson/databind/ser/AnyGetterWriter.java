package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import java.util.Map;

public class AnyGetterWriter {
    protected final AnnotatedMember _accessor;
    protected final BeanProperty _property;
    protected MapSerializer _serializer;

    public AnyGetterWriter(BeanProperty beanProperty, AnnotatedMember annotatedMember, MapSerializer mapSerializer) {
        this._accessor = annotatedMember;
        this._property = beanProperty;
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object value = this._accessor.getValue(obj);
        if (value != null) {
            if (value instanceof Map) {
                this._serializer.serializeFields((Map) value, jsonGenerator, serializerProvider);
                return;
            }
            throw new JsonMappingException("Value returned by 'any-getter' (" + this._accessor.getName() + "()) not java.util.Map but " + value.getClass().getName());
        }
    }

    public void getAndFilter(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter) {
        Object value = this._accessor.getValue(obj);
        if (value != null) {
            if (value instanceof Map) {
                this._serializer.serializeFilteredFields((Map) value, jsonGenerator, serializerProvider, propertyFilter);
                return;
            }
            throw new JsonMappingException("Value returned by 'any-getter' (" + this._accessor.getName() + "()) not java.util.Map but " + value.getClass().getName());
        }
    }

    public void resolve(SerializerProvider serializerProvider) {
        this._serializer = (MapSerializer) serializerProvider.handlePrimaryContextualization(this._serializer, this._property);
    }
}
