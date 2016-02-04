package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;

public class CreatorProperty extends SettableBeanProperty {
    protected final AnnotatedParameter _annotated;
    protected final int _creatorIndex;
    protected final SettableBeanProperty _fallbackSetter;
    protected final Object _injectableValueId;

    public CreatorProperty(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj, PropertyMetadata propertyMetadata) {
        super(propertyName, javaType, propertyName2, typeDeserializer, annotations, propertyMetadata);
        this._annotated = annotatedParameter;
        this._creatorIndex = i;
        this._injectableValueId = obj;
        this._fallbackSetter = null;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, PropertyName propertyName) {
        super((SettableBeanProperty) creatorProperty, propertyName);
        this._annotated = creatorProperty._annotated;
        this._creatorIndex = creatorProperty._creatorIndex;
        this._injectableValueId = creatorProperty._injectableValueId;
        this._fallbackSetter = creatorProperty._fallbackSetter;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) creatorProperty, (JsonDeserializer) jsonDeserializer);
        this._annotated = creatorProperty._annotated;
        this._creatorIndex = creatorProperty._creatorIndex;
        this._injectableValueId = creatorProperty._injectableValueId;
        this._fallbackSetter = creatorProperty._fallbackSetter;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, SettableBeanProperty settableBeanProperty) {
        super(creatorProperty);
        this._annotated = creatorProperty._annotated;
        this._creatorIndex = creatorProperty._creatorIndex;
        this._injectableValueId = creatorProperty._injectableValueId;
        this._fallbackSetter = settableBeanProperty;
    }

    public CreatorProperty withName(PropertyName propertyName) {
        return new CreatorProperty(this, propertyName);
    }

    public CreatorProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new CreatorProperty(this, (JsonDeserializer) jsonDeserializer);
    }

    public CreatorProperty withFallbackSetter(SettableBeanProperty settableBeanProperty) {
        return new CreatorProperty(this, settableBeanProperty);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public int getCreatorIndex() {
        return this._creatorIndex;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public void set(Object obj, Object obj2) {
        if (this._fallbackSetter == null) {
            throw new IllegalStateException("No fallback setter/field defined: can not use creator property for " + getClass().getName());
        }
        this._fallbackSetter.set(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) {
        if (this._fallbackSetter != null) {
            return this._fallbackSetter.setAndReturn(obj, obj2);
        }
        throw new IllegalStateException("No fallback setter/field defined: can not use creator property for " + getClass().getName());
    }

    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    public String toString() {
        return "[creator property, name '" + getName() + "'; inject id '" + this._injectableValueId + "']";
    }
}
