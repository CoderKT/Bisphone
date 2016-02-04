package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    protected abstract ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    public abstract boolean hasSingleElement(T t);

    protected ContainerSerializer(Class<T> cls) {
        super((Class) cls);
    }

    protected ContainerSerializer(Class<?> cls, boolean z) {
        super(cls, z);
    }

    protected ContainerSerializer(ContainerSerializer<?> containerSerializer) {
        super(containerSerializer._handledType, false);
    }

    public ContainerSerializer<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }

    protected boolean hasContentTypeAnnotation(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        if (beanProperty != null) {
            AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
            if (!(annotationIntrospector == null || annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) == null)) {
                return true;
            }
        }
        return false;
    }
}
