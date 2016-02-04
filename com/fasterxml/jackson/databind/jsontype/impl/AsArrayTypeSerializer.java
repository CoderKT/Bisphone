package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsArrayTypeSerializer extends TypeSerializerBase {
    public AsArrayTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public AsArrayTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsArrayTypeSerializer(this._idResolver, beanProperty);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
        } else {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeString(idFromValue);
        }
        jsonGenerator.writeStartObject();
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
        } else {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeString(idFromValue);
        }
        jsonGenerator.writeStartArray();
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
            return;
        }
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(idFromValue);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        String idFromValueAndType = idFromValueAndType(obj, cls);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValueAndType);
            return;
        }
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString(idFromValueAndType);
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndArray();
        }
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndArray();
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndArray();
        }
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndArray();
        }
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(str);
        } else {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeString(str);
        }
        jsonGenerator.writeStartObject();
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        if (!jsonGenerator.canWriteTypeId()) {
            writeTypeSuffixForObject(obj, jsonGenerator);
        }
    }
}
