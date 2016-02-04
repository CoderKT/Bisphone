package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;

public abstract class ValueInstantiator {
    public abstract String getValueTypeDesc();

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public boolean canCreateFromString() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public boolean canCreateUsingDelegate() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        return null;
    }

    public JavaType getDelegateType(DeserializationConfig deserializationConfig) {
        return null;
    }

    public Object createUsingDefault(DeserializationContext deserializationContext) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + "; no default creator found");
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " with arguments");
    }

    public Object createUsingDelegate(DeserializationContext deserializationContext, Object obj) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " using delegate");
    }

    public Object createFromString(DeserializationContext deserializationContext, String str) {
        return _createFromStringFallbacks(deserializationContext, str);
    }

    public Object createFromInt(DeserializationContext deserializationContext, int i) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Integer number (" + i + ", int)");
    }

    public Object createFromLong(DeserializationContext deserializationContext, long j) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Integer number (" + j + ", long)");
    }

    public Object createFromDouble(DeserializationContext deserializationContext, double d) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Floating-point number (" + d + ", double)");
    }

    public Object createFromBoolean(DeserializationContext deserializationContext, boolean z) {
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Boolean value (" + z + ")");
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public AnnotatedParameter getIncompleteParameter() {
        return null;
    }

    protected Object _createFromStringFallbacks(DeserializationContext deserializationContext, String str) {
        if (canCreateFromBoolean()) {
            String trim = str.trim();
            if ("true".equals(trim)) {
                return createFromBoolean(deserializationContext, true);
            }
            if ("false".equals(trim)) {
                return createFromBoolean(deserializationContext, false);
            }
        }
        if (str.length() == 0 && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return null;
        }
        throw deserializationContext.mappingException("Can not instantiate value of type " + getValueTypeDesc() + " from String value ('" + str + "'); no single-String constructor/factory method");
    }
}
