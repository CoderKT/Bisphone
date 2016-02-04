package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.reflect.Field;

public final class FieldProperty extends SettableBeanProperty {
    protected final AnnotatedField _annotated;
    protected final transient Field _field;

    public FieldProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        this._annotated = annotatedField;
        this._field = annotatedField.getAnnotated();
    }

    protected FieldProperty(FieldProperty fieldProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) fieldProperty, (JsonDeserializer) jsonDeserializer);
        this._annotated = fieldProperty._annotated;
        this._field = fieldProperty._field;
    }

    protected FieldProperty(FieldProperty fieldProperty, PropertyName propertyName) {
        super((SettableBeanProperty) fieldProperty, propertyName);
        this._annotated = fieldProperty._annotated;
        this._field = fieldProperty._field;
    }

    public FieldProperty withName(PropertyName propertyName) {
        return new FieldProperty(this, propertyName);
    }

    public FieldProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new FieldProperty(this, (JsonDeserializer) jsonDeserializer);
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
            this._field.set(obj, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
        }
    }

    public Object setAndReturn(Object obj, Object obj2) {
        try {
            this._field.set(obj, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
        }
        return obj;
    }
}
