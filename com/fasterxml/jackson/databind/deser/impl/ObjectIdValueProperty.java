package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;

public final class ObjectIdValueProperty extends SettableBeanProperty {
    protected final ObjectIdReader _objectIdReader;

    public ObjectIdValueProperty(ObjectIdReader objectIdReader, PropertyMetadata propertyMetadata) {
        super(objectIdReader.propertyName, objectIdReader.getIdType(), propertyMetadata, objectIdReader.getDeserializer());
        this._objectIdReader = objectIdReader;
    }

    protected ObjectIdValueProperty(ObjectIdValueProperty objectIdValueProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) objectIdValueProperty, (JsonDeserializer) jsonDeserializer);
        this._objectIdReader = objectIdValueProperty._objectIdReader;
    }

    @Deprecated
    protected ObjectIdValueProperty(ObjectIdValueProperty objectIdValueProperty, PropertyName propertyName) {
        super((SettableBeanProperty) objectIdValueProperty, propertyName);
        this._objectIdReader = objectIdValueProperty._objectIdReader;
    }

    public ObjectIdValueProperty withName(PropertyName propertyName) {
        return new ObjectIdValueProperty(this, propertyName);
    }

    public ObjectIdValueProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new ObjectIdValueProperty(this, (JsonDeserializer) jsonDeserializer);
    }

    public AnnotatedMember getMember() {
        return null;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        deserializeSetAndReturn(jsonParser, deserializationContext, obj);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Object deserialize = this._valueDeserializer.deserialize(jsonParser, deserializationContext);
        deserializationContext.findObjectId(deserialize, this._objectIdReader.generator, this._objectIdReader.resolver).bindItem(obj);
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        if (settableBeanProperty != null) {
            return settableBeanProperty.setAndReturn(obj, deserialize);
        }
        return obj;
    }

    public void set(Object obj, Object obj2) {
        setAndReturn(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) {
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        if (settableBeanProperty != null) {
            return settableBeanProperty.setAndReturn(obj, obj2);
        }
        throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
    }
}
