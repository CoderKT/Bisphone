package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class BeanAsArraySerializer extends BeanSerializerBase {
    protected final BeanSerializerBase _defaultSerializer;

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase, (ObjectIdWriter) null);
        this._defaultSerializer = beanSerializerBase;
    }

    protected BeanAsArraySerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
        this._defaultSerializer = beanSerializerBase;
    }

    protected BeanAsArraySerializer(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(beanSerializerBase, objectIdWriter, obj);
        this._defaultSerializer = beanSerializerBase;
    }

    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        return this._defaultSerializer.unwrappingSerializer(nameTransformer);
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return this._defaultSerializer.withObjectIdWriter(objectIdWriter);
    }

    protected BeanSerializerBase withFilterId(Object obj) {
        return new BeanAsArraySerializer(this, this._objectIdWriter, obj);
    }

    protected BeanAsArraySerializer withIgnorals(String[] strArr) {
        return new BeanAsArraySerializer(this, strArr);
    }

    protected BeanSerializerBase asArraySerializer() {
        return this;
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        this._defaultSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
    }

    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && hasSingleElement(serializerProvider)) {
            serializeAsArray(obj, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray();
        serializeAsArray(obj, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    private boolean hasSingleElement(SerializerProvider serializerProvider) {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        return beanPropertyWriterArr.length == 1;
    }

    protected final void serializeAsArray(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter == null) {
                    jsonGenerator.writeNull();
                } else {
                    beanPropertyWriter.serializeAsElement(obj, jsonGenerator, serializerProvider);
                }
                i++;
            }
        } catch (Throwable e) {
            wrapAndThrow(serializerProvider, e, obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (Throwable e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", e2);
            jsonMappingException.prependPath(new Reference(obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName()));
            throw jsonMappingException;
        }
    }

    public String toString() {
        return "BeanAsArraySerializer for " + handledType().getName();
    }
}
