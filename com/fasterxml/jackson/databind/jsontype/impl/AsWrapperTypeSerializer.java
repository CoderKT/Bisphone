package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsWrapperTypeSerializer extends TypeSerializerBase {
    public AsWrapperTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public AsWrapperTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsWrapperTypeSerializer(this._idResolver, beanProperty);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
            jsonGenerator.writeStartObject();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(idFromValue);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
            jsonGenerator.writeStartObject();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(idFromValue);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        String idFromValue = idFromValue(obj);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValue);
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(idFromValue);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        String idFromValueAndType = idFromValueAndType(obj, cls);
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(idFromValueAndType);
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(idFromValueAndType);
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndObject();
        }
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndArray();
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndObject();
        }
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        if (!jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeEndObject();
        }
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        if (jsonGenerator.canWriteTypeId()) {
            jsonGenerator.writeTypeId(str);
            jsonGenerator.writeStartObject();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) {
        if (!jsonGenerator.canWriteTypeId()) {
            writeTypeSuffixForObject(obj, jsonGenerator);
        }
    }
}
