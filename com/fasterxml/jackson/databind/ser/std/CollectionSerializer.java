package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer extends AsArraySerializerBase<Collection<?>> {
    public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(Collection.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public CollectionSerializer(CollectionSerializer collectionSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(collectionSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new CollectionSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
    }

    public CollectionSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        return new CollectionSerializer(this, beanProperty, typeSerializer, jsonSerializer);
    }

    public boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public boolean hasSingleElement(Collection<?> collection) {
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next();
        if (it.hasNext()) {
            return false;
        }
        return true;
    }

    public void serializeContents(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._elementSerializer != null) {
            serializeContentsUsing(collection, jsonGenerator, serializerProvider, this._elementSerializer);
            return;
        }
        Iterator it = collection.iterator();
        if (it.hasNext()) {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            do {
                Object next = it.next();
                if (next == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class cls = next.getClass();
                    JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        JsonSerializer _findAndAddDynamic;
                        if (this._elementType.hasGenericTypes()) {
                            _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                        } else {
                            try {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            } catch (Throwable e) {
                                wrapAndThrow(serializerProvider, e, (Object) collection, i);
                                return;
                            }
                        }
                        JsonSerializer jsonSerializer = _findAndAddDynamic;
                        propertySerializerMap = this._dynamicSerializers;
                        serializerFor = jsonSerializer;
                    }
                    if (typeSerializer == null) {
                        serializerFor.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        serializerFor.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
                i++;
            } while (it.hasNext());
        }
    }

    public void serializeContentsUsing(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        Iterator it = collection.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            do {
                Object next = it.next();
                if (next == null) {
                    try {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } catch (Throwable e) {
                        wrapAndThrow(serializerProvider, e, (Object) collection, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            } while (it.hasNext());
        }
    }
}
