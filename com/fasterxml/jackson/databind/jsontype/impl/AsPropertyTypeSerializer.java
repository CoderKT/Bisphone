package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer {
    protected final String _typePropertyName;

    public AsPropertyTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    public AsPropertyTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsPropertyTypeSerializer(this._idResolver, beanProperty, this._typePropertyName);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
            jsonGenerator.writeStartObject();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(this._typePropertyName, idFromValue);
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(str);
            jsonGenerator.writeStartObject();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        jsonGenerator.writeEndObject();
    }
}
