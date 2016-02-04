package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Constructor;

public final class InnerClassProperty extends SettableBeanProperty {
    protected final Constructor<?> _creator;
    protected final SettableBeanProperty _delegate;

    public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
        super(settableBeanProperty);
        this._delegate = settableBeanProperty;
        this._creator = constructor;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) innerClassProperty, (JsonDeserializer) jsonDeserializer);
        this._delegate = innerClassProperty._delegate.withValueDeserializer(jsonDeserializer);
        this._creator = innerClassProperty._creator;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, PropertyName propertyName) {
        super((SettableBeanProperty) innerClassProperty, propertyName);
        this._delegate = innerClassProperty._delegate.withName(propertyName);
        this._creator = innerClassProperty._creator;
    }

    public InnerClassProperty withName(PropertyName propertyName) {
        return new InnerClassProperty(this, propertyName);
    }

    public InnerClassProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new InnerClassProperty(this, (JsonDeserializer) jsonDeserializer);
    }

    public AnnotatedMember getMember() {
        return this._delegate.getMember();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Object obj2 = null;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            if (this._nullProvider != null) {
                obj2 = this._nullProvider.nullValue(deserializationContext);
            }
        } else if (this._valueTypeDeserializer != null) {
            obj2 = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
        } else {
            try {
                obj2 = this._creator.newInstance(new Object[]{obj});
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + e.getMessage());
            }
            this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj2);
        }
        set(obj, obj2);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) {
        this._delegate.set(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) {
        return this._delegate.setAndReturn(obj, obj2);
    }
}
