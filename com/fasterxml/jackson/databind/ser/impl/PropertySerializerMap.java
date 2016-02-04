package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public abstract class PropertySerializerMap {

    final class Double extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer1;
        private final JsonSerializer<Object> _serializer2;
        private final Class<?> _type1;
        private final Class<?> _type2;

        public Double(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type1) {
                return this._serializer1;
            }
            if (cls == this._type2) {
                return this._serializer2;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Multi(new TypeAndSerializer[]{new TypeAndSerializer(this._type1, this._serializer1), new TypeAndSerializer(this._type2, this._serializer2)});
        }
    }

    final class Empty extends PropertySerializerMap {
        protected static final Empty instance;

        private Empty() {
        }

        static {
            instance = new Empty();
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Single(cls, jsonSerializer);
        }
    }

    final class Multi extends PropertySerializerMap {
        private final TypeAndSerializer[] _entries;

        public Multi(TypeAndSerializer[] typeAndSerializerArr) {
            this._entries = typeAndSerializerArr;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            for (TypeAndSerializer typeAndSerializer : this._entries) {
                if (typeAndSerializer.type == cls) {
                    return typeAndSerializer.serializer;
                }
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            int length = this._entries.length;
            if (length == 8) {
                return this;
            }
            Object obj = new TypeAndSerializer[(length + 1)];
            System.arraycopy(this._entries, 0, obj, 0, length);
            obj[length] = new TypeAndSerializer(cls, jsonSerializer);
            this(obj);
            return this;
        }
    }

    public final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    final class Single extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer;
        private final Class<?> _type;

        public Single(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type) {
                return this._serializer;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Double(this._type, this._serializer, cls, jsonSerializer);
        }
    }

    final class TypeAndSerializer {
        public final JsonSerializer<Object> serializer;
        public final Class<?> type;

        public TypeAndSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    public abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);

    public final SerializerAndMapResult findAndAddPrimarySerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findPrimaryPropertySerializer = serializerProvider.findPrimaryPropertySerializer((Class) cls, beanProperty);
        return new SerializerAndMapResult(findPrimaryPropertySerializer, newWith(cls, findPrimaryPropertySerializer));
    }

    public final SerializerAndMapResult findAndAddPrimarySerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findPrimaryPropertySerializer = serializerProvider.findPrimaryPropertySerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findPrimaryPropertySerializer, newWith(javaType.getRawClass(), findPrimaryPropertySerializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer((Class) cls, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(cls, findValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(javaType.getRawClass(), findValueSerializer));
    }

    public static PropertySerializerMap emptyMap() {
        return Empty.instance;
    }
}
