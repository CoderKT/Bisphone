package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
    public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(indexedListSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public IndexedListSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        return new IndexedListSerializer(this, beanProperty, typeSerializer, jsonSerializer);
    }

    public boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public boolean hasSingleElement(List<?> list) {
        return list.size() == 1;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
    }

    public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._elementSerializer != null) {
            serializeContentsUsing(list, jsonGenerator, serializerProvider, this._elementSerializer);
        } else if (this._valueTypeSerializer != null) {
            serializeTypedContents(list, jsonGenerator, serializerProvider);
        } else {
            int size = list.size();
            if (size != 0) {
                int i = 0;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list.get(i);
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class cls = obj.getClass();
                            JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                JsonSerializer _findAndAddDynamic;
                                if (this._elementType.hasGenericTypes()) {
                                    _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                                } else {
                                    _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                                JsonSerializer jsonSerializer = _findAndAddDynamic;
                                propertySerializerMap = this._dynamicSerializers;
                                serializerFor = jsonSerializer;
                            }
                            serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                        }
                        i++;
                    }
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) list, i);
                }
            }
        }
    }

    public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        int size = list.size();
        if (size != 0) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                if (obj == null) {
                    try {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } catch (Throwable e) {
                        wrapAndThrow(serializerProvider, e, (Object) list, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }
    }

    public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int size = list.size();
        if (size != 0) {
            int i = 0;
            try {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                while (i < size) {
                    Object obj = list.get(i);
                    if (obj == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class cls = obj.getClass();
                        JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            JsonSerializer _findAndAddDynamic;
                            if (this._elementType.hasGenericTypes()) {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                            } else {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            }
                            JsonSerializer jsonSerializer = _findAndAddDynamic;
                            propertySerializerMap = this._dynamicSerializers;
                            serializerFor = jsonSerializer;
                        }
                        serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    i++;
                }
            } catch (Throwable e) {
                wrapAndThrow(serializerProvider, e, (Object) list, i);
            }
        }
    }
}
