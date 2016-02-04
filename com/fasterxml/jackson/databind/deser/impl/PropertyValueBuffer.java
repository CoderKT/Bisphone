package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

public final class PropertyValueBuffer {
    private PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    private Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    private int _paramsNeeded;
    protected final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i, ObjectIdReader objectIdReader) {
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i;
        this._objectIdReader = objectIdReader;
        this._creatorParameters = new Object[i];
    }

    public void inject(SettableBeanProperty[] settableBeanPropertyArr) {
        int length = settableBeanPropertyArr.length;
        for (int i = 0; i < length; i++) {
            BeanProperty beanProperty = settableBeanPropertyArr[i];
            if (beanProperty != null) {
                this._creatorParameters[i] = this._context.findInjectableValue(beanProperty.getInjectableValueId(), beanProperty, null);
            }
        }
    }

    protected final Object[] getParameters(Object[] objArr) {
        if (objArr != null) {
            int length = this._creatorParameters.length;
            for (int i = 0; i < length; i++) {
                if (this._creatorParameters[i] == null) {
                    Object obj = objArr[i];
                    if (obj != null) {
                        this._creatorParameters[i] = obj;
                    }
                }
            }
        }
        return this._creatorParameters;
    }

    public boolean readIdProperty(String str) {
        if (this._objectIdReader == null || !str.equals(this._objectIdReader.propertyName.getSimpleName())) {
            return false;
        }
        this._idValue = this._objectIdReader.readObjectReference(this._parser, this._context);
        return true;
    }

    public Object handleIdValue(DeserializationContext deserializationContext, Object obj) {
        if (this._objectIdReader == null || this._idValue == null) {
            return obj;
        }
        deserializationContext.findObjectId(this._idValue, this._objectIdReader.generator, this._objectIdReader.resolver).bindItem(obj);
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        if (settableBeanProperty != null) {
            return settableBeanProperty.setAndReturn(obj, this._idValue);
        }
        return obj;
    }

    protected PropertyValue buffered() {
        return this._buffered;
    }

    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        int i2 = this._paramsNeeded - 1;
        this._paramsNeeded = i2;
        return i2 <= 0;
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        this._buffered = new Regular(this._buffered, obj, settableBeanProperty);
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        this._buffered = new Any(this._buffered, obj, settableAnyProperty, str);
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        this._buffered = new Map(this._buffered, obj2, obj);
    }
}
