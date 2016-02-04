package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;

public abstract class TypeSerializer {
    public abstract TypeSerializer forProperty(BeanProperty beanProperty);

    public abstract void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str);

    public abstract void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str);

    public abstract void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator);

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        writeTypePrefixForScalar(obj, jsonGenerator);
    }
}
