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
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements ContextualSerializer {
    public static final IndexedStringListSerializer instance;
    protected final JsonSerializer<String> _serializer;

    static {
        instance = new IndexedStringListSerializer();
    }

    protected IndexedStringListSerializer() {
        this(null);
    }

    public IndexedStringListSerializer(JsonSerializer<?> jsonSerializer) {
        super(List.class);
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

    public void serialize(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int size = list.size();
        if (size == 1 && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            _serializeUnwrapped(list, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider, size);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider, size);
        }
        jsonGenerator.writeEndArray();
    }

    private final void _serializeUnwrapped(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider, 1);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider, 1);
        }
    }

    public void serializeWithType(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        int size = list.size();
        typeSerializer.writeTypePrefixForArray(list, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider, size);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider, size);
        }
        typeSerializer.writeTypeSuffixForArray(list, jsonGenerator);
    }

    private final void serializeContents(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) {
        int i2 = 0;
        while (i2 < i) {
            try {
                String str = (String) list.get(i2);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(str);
                }
                i2++;
            } catch (Throwable e) {
                wrapAndThrow(serializerProvider, e, (Object) list, i2);
                return;
            }
        }
    }

    private final void serializeUsingCustom(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) {
        int i2 = 0;
        try {
            JsonSerializer jsonSerializer = this._serializer;
            while (i2 < i) {
                String str = (String) list.get(i2);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
                }
                i2++;
            }
        } catch (Throwable e) {
            wrapAndThrow(serializerProvider, e, (Object) list, 0);
        }
    }
}
