package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeSerializerBase(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
    }

    protected String idFromValue(Object obj) {
        String idFromValue = this._idResolver.idFromValue(obj);
        if (idFromValue != null) {
            return idFromValue;
        }
        throw new IllegalArgumentException("Can not resolve type id for " + (obj == null ? "NULL" : obj.getClass().getName()) + " (using " + this._idResolver.getClass().getName() + ")");
    }

    protected String idFromValueAndType(Object obj, Class<?> cls) {
        String idFromValueAndType = this._idResolver.idFromValueAndType(obj, cls);
        if (idFromValueAndType != null) {
            return idFromValueAndType;
        }
        throw new IllegalArgumentException("Can not resolve type id for " + (obj == null ? "NULL" : obj.getClass().getName()) + " (using " + this._idResolver.getClass().getName() + ")");
    }
}
