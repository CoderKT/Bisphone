package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.util.Collection;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements ContextualSerializer {
    public static final StringCollectionSerializer instance;
    protected final JsonSerializer<String> _serializer;

    static {
        instance = new StringCollectionSerializer();
    }

    protected StringCollectionSerializer() {
        this(null);
    }

    protected StringCollectionSerializer(JsonSerializer<?> jsonSerializer) {
        super(Collection.class);
        this._serializer = jsonSerializer;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer serializerInstance;
        if (beanProperty != null) {
            Annotated member = beanProperty.getMember();
            if (member != null) {
                Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(member);
                if (findContentSerializer != null) {
                    serializerInstance = serializerProvider.serializerInstance(member, findContentSerializer);
                    if (serializerInstance == null) {
                        serializerInstance = this._serializer;
                    }
                    serializerInstance = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
                    if (serializerInstance != null) {
                        serializerInstance = serializerProvider.findValueSerializer(String.class, beanProperty);
                    } else {
                        serializerInstance = serializerProvider.handleSecondaryContextualization(serializerInstance, beanProperty);
                    }
                    if (isDefaultSerializer(serializerInstance)) {
                        serializerInstance = null;
                    }
                    if (serializerInstance == this._serializer) {
                        return this;
                    }
                    this(serializerInstance);
                    return this;
                }
            }
        }
        serializerInstance = null;
        if (serializerInstance == null) {
            serializerInstance = this._serializer;
        }
        serializerInstance = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
        if (serializerInstance != null) {
            serializerInstance = serializerProvider.handleSecondaryContextualization(serializerInstance, beanProperty);
        } else {
            serializerInstance = serializerProvider.findValueSerializer(String.class, beanProperty);
        }
        if (isDefaultSerializer(serializerInstance)) {
            serializerInstance = null;
        }
        if (serializerInstance == this._serializer) {
            return this;
        }
        this(serializerInstance);
        return this;
    }

    public void serialize(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (collection.size() == 1 && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            _serializeUnwrapped(collection, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    private final void _serializeUnwrapped(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
    }

    public void serializeWithType(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(collection, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(collection, jsonGenerator);
    }

    private final void serializeContents(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._serializer != null) {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        for (String str : collection) {
            int i2;
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) collection, i);
                    i2 = i;
                }
            } else {
                jsonGenerator.writeString(str);
            }
            i2 = i + 1;
            i = i2;
        }
    }

    private void serializeUsingCustom(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer = this._serializer;
        for (String str : collection) {
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) collection, 0);
                }
            } else {
                jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
            }
        }
    }
}
