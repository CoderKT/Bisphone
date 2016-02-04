package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;

public class ObjectIdReferenceProperty extends SettableBeanProperty {
    private SettableBeanProperty _forward;

    public final class PropertyReferring extends Referring {
        private final ObjectIdReferenceProperty _parent;
        public final Object _pojo;

        public PropertyReferring(ObjectIdReferenceProperty objectIdReferenceProperty, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls, Object obj) {
            super(unresolvedForwardReference, cls);
            this._parent = objectIdReferenceProperty;
            this._pojo = obj;
        }

        public void handleResolvedForwardReference(Object obj, Object obj2) {
            if (hasId(obj)) {
                this._parent.set(this._pojo, obj2);
                return;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }

    public ObjectIdReferenceProperty(SettableBeanProperty settableBeanProperty, ObjectIdInfo objectIdInfo) {
        super(settableBeanProperty);
        this._forward = settableBeanProperty;
        this._objectIdInfo = objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty objectIdReferenceProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) objectIdReferenceProperty, (JsonDeserializer) jsonDeserializer);
        this._forward = objectIdReferenceProperty._forward;
        this._objectIdInfo = objectIdReferenceProperty._objectIdInfo;
    }

    public ObjectIdReferenceProperty(ObjectIdReferenceProperty objectIdReferenceProperty, PropertyName propertyName) {
        super((SettableBeanProperty) objectIdReferenceProperty, propertyName);
        this._forward = objectIdReferenceProperty._forward;
        this._objectIdInfo = objectIdReferenceProperty._objectIdInfo;
    }

    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new ObjectIdReferenceProperty(this, (JsonDeserializer) jsonDeserializer);
    }

    public SettableBeanProperty withName(PropertyName propertyName) {
        return new ObjectIdReferenceProperty(this, propertyName);
    }

    public AnnotatedMember getMember() {
        return this._forward.getMember();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        deserializeSetAndReturn(jsonParser, deserializationContext, obj);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Object obj2 = (this._objectIdInfo == null && this._valueDeserializer.getObjectIdReader() == null) ? null : 1;
        try {
            return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
        } catch (Throwable e) {
            if (obj2 == null) {
                throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", e);
            }
            e.getRoid().appendReferring(new PropertyReferring(this, e, this._type.getRawClass(), obj));
            return null;
        }
    }

    public void set(Object obj, Object obj2) {
        this._forward.set(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) {
        return this._forward.setAndReturn(obj, obj2);
    }
}
