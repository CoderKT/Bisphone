package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class StdSerializer<T> extends JsonSerializer<T> {
    protected final Class<T> _handledType;

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    protected StdSerializer(Class<T> cls) {
        this._handledType = cls;
    }

    protected StdSerializer(JavaType javaType) {
        this._handledType = javaType.getRawClass();
    }

    protected StdSerializer(Class<?> cls, boolean z) {
        this._handledType = cls;
    }

    public Class<T> handledType() {
        return this._handledType;
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, String str) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        Object obj2 = (serializerProvider == null || serializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS)) ? 1 : null;
        if (th2 instanceof IOException) {
            if (obj2 == null || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (obj2 == null && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj, str);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, int i) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        Object obj2 = (serializerProvider == null || serializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS)) ? 1 : null;
        if (th2 instanceof IOException) {
            if (obj2 == null || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (obj2 == null && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj, i);
    }

    protected boolean isDefaultSerializer(JsonSerializer<?> jsonSerializer) {
        return ClassUtil.isJacksonStdImpl((Object) jsonSerializer);
    }

    protected JsonSerializer<?> findConvertingContentSerializer(SerializerProvider serializerProvider, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        if (annotationIntrospector == null || beanProperty == null) {
            return jsonSerializer;
        }
        Object findSerializationContentConverter = annotationIntrospector.findSerializationContentConverter(beanProperty.getMember());
        if (findSerializationContentConverter == null) {
            return jsonSerializer;
        }
        Converter converterInstance = serializerProvider.converterInstance(beanProperty.getMember(), findSerializationContentConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
        if (jsonSerializer == null) {
            jsonSerializer = serializerProvider.findValueSerializer(outputType, beanProperty);
        }
        return new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer);
    }

    protected PropertyFilter findPropertyFilter(SerializerProvider serializerProvider, Object obj, Object obj2) {
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider != null) {
            return filterProvider.findPropertyFilter(obj, obj2);
        }
        throw new JsonMappingException("Can not resolve PropertyFilter with id '" + obj + "'; no FilterProvider configured");
    }
}
