package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    public AsExternalTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsExternalTypeSerializer(this._idResolver, beanProperty, this._typePropertyName);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        _writeArrayPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        _writeObjectSuffix(obj, jsonGenerator, idFromValue(obj));
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        _writeArraySuffix(obj, jsonGenerator, idFromValue(obj));
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        _writeScalarSuffix(obj, jsonGenerator, idFromValue(obj));
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        _writeObjectSuffix(obj, jsonGenerator, str);
    }

    protected final void _writeScalarPrefix(Object obj, JsonGenerator jsonGenerator) {
    }

    protected final void _writeObjectPrefix(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartObject();
    }

    protected final void _writeArrayPrefix(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartArray();
    }

    protected final void _writeScalarSuffix(Object obj, JsonGenerator jsonGenerator, String str) {
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }

    protected final void _writeObjectSuffix(Object obj, JsonGenerator jsonGenerator, String str) {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }

    protected final void _writeArraySuffix(Object obj, JsonGenerator jsonGenerator, String str) {
        jsonGenerator.writeEndArray();
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }
}
