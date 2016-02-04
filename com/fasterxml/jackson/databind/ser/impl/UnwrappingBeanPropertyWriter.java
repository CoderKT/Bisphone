package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
    protected final NameTransformer _nameTransformer;

    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, NameTransformer nameTransformer) {
        super(beanPropertyWriter);
        this._nameTransformer = nameTransformer;
    }

    private UnwrappingBeanPropertyWriter(UnwrappingBeanPropertyWriter unwrappingBeanPropertyWriter, NameTransformer nameTransformer, SerializedString serializedString) {
        super(unwrappingBeanPropertyWriter, serializedString);
        this._nameTransformer = nameTransformer;
    }

    public UnwrappingBeanPropertyWriter rename(NameTransformer nameTransformer) {
        return new UnwrappingBeanPropertyWriter(this, NameTransformer.chainedTransformer(nameTransformer, this._nameTransformer), new SerializedString(nameTransformer.transform(this._name.getValue())));
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object obj2 = get(obj);
        if (obj2 != null) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class cls = obj2.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(obj2)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(obj2)) {
                    return;
                }
            }
            if (obj2 != obj || !_handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer)) {
                if (!jsonSerializer.isUnwrappingSerializer()) {
                    jsonGenerator.writeFieldName(this._name);
                }
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        }
    }

    public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
        super.assignSerializer(jsonSerializer);
        if (this._serializer != null) {
            NameTransformer chainedTransformer;
            NameTransformer nameTransformer = this._nameTransformer;
            if (this._serializer.isUnwrappingSerializer()) {
                chainedTransformer = NameTransformer.chainedTransformer(nameTransformer, ((UnwrappingBeanSerializer) this._serializer)._nameTransformer);
            } else {
                chainedTransformer = nameTransformer;
            }
            this._serializer = this._serializer.unwrappingSerializer(chainedTransformer);
        }
    }

    protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        JsonSerializer findValueSerializer;
        NameTransformer chainedTransformer;
        if (this._nonTrivialBaseType != null) {
            findValueSerializer = serializerProvider.findValueSerializer(serializerProvider.constructSpecializedType(this._nonTrivialBaseType, cls), (BeanProperty) this);
        } else {
            findValueSerializer = serializerProvider.findValueSerializer((Class) cls, (BeanProperty) this);
        }
        NameTransformer nameTransformer = this._nameTransformer;
        if (findValueSerializer.isUnwrappingSerializer()) {
            chainedTransformer = NameTransformer.chainedTransformer(nameTransformer, ((UnwrappingBeanSerializer) findValueSerializer)._nameTransformer);
        } else {
            chainedTransformer = nameTransformer;
        }
        JsonSerializer<Object> unwrappingSerializer = findValueSerializer.unwrappingSerializer(chainedTransformer);
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls, unwrappingSerializer);
        return unwrappingSerializer;
    }
}
