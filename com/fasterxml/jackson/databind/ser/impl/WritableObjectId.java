package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;
    public Object id;
    protected boolean idWritten;

    public WritableObjectId(ObjectIdGenerator<?> objectIdGenerator) {
        this.idWritten = false;
        this.generator = objectIdGenerator;
    }

    public boolean writeAsId(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) {
        if (this.id == null || (!this.idWritten && !objectIdWriter.alwaysAsId)) {
            return false;
        }
        if (jsonGenerator.canWriteObjectId()) {
            jsonGenerator.writeObjectRef(String.valueOf(this.id));
        } else {
            objectIdWriter.serializer.serialize(this.id, jsonGenerator, serializerProvider);
        }
        return true;
    }

    public Object generateId(Object obj) {
        Object generateId = this.generator.generateId(obj);
        this.id = generateId;
        return generateId;
    }

    public void writeAsField(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) {
        this.idWritten = true;
        if (jsonGenerator.canWriteObjectId()) {
            jsonGenerator.writeObjectId(String.valueOf(this.id));
            return;
        }
        SerializableString serializableString = objectIdWriter.propertyName;
        if (serializableString != null) {
            jsonGenerator.writeFieldName(serializableString);
            objectIdWriter.serializer.serialize(this.id, jsonGenerator, serializerProvider);
        }
    }
}
