package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.reflect.Method;

public final class MethodProperty extends SettableBeanProperty {
    protected final AnnotatedMethod _annotated;
    protected final transient Method _setter;

    public MethodProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        this._annotated = annotatedMethod;
        this._setter = annotatedMethod.getAnnotated();
    }

    protected MethodProperty(MethodProperty methodProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) methodProperty, (JsonDeserializer) jsonDeserializer);
        this._annotated = methodProperty._annotated;
        this._setter = methodProperty._setter;
    }

    protected MethodProperty(MethodProperty methodProperty, PropertyName propertyName) {
        super((SettableBeanProperty) methodProperty, propertyName);
        this._annotated = methodProperty._annotated;
        this._setter = methodProperty._setter;
    }

    public MethodProperty withName(PropertyName propertyName) {
        return new MethodProperty(this, propertyName);
    }

    public MethodProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new MethodProperty(this, (JsonDeserializer) jsonDeserializer);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) {
        try {
            this._setter.invoke(obj, new Object[]{obj2});
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
        }
    }

    public Object setAndReturn(Object obj, Object obj2) {
        try {
            Object invoke = this._setter.invoke(obj, new Object[]{obj2});
            return invoke == null ? obj : invoke;
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
            return null;
        }
    }
}
