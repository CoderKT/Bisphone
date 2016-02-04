package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter, PropertyFilter {

    /* renamed from: com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.1 */
    final class C06401 implements PropertyFilter {
        final /* synthetic */ BeanPropertyFilter val$src;

        C06401(BeanPropertyFilter beanPropertyFilter) {
            this.val$src = beanPropertyFilter;
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter) {
            this.val$src.serializeAsField(obj, jsonGenerator, serializerProvider, (BeanPropertyWriter) propertyWriter);
        }
    }

    protected abstract boolean include(BeanPropertyWriter beanPropertyWriter);

    protected abstract boolean include(PropertyWriter propertyWriter);

    public static PropertyFilter from(BeanPropertyFilter beanPropertyFilter) {
        return new C06401(beanPropertyFilter);
    }

    @Deprecated
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) {
        if (include(beanPropertyWriter)) {
            beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
        } else if (!jsonGenerator.canOmitFields()) {
            beanPropertyWriter.serializeAsOmittedField(obj, jsonGenerator, serializerProvider);
        }
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter) {
        if (include(propertyWriter)) {
            propertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
        } else if (!jsonGenerator.canOmitFields()) {
            propertyWriter.serializeAsOmittedField(obj, jsonGenerator, serializerProvider);
        }
    }
}
