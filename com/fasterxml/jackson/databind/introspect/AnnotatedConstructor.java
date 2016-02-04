package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedConstructor extends AnnotatedWithParams {
    protected final Constructor<?> _constructor;

    public AnnotatedConstructor(Constructor<?> constructor, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        if (constructor == null) {
            throw new IllegalArgumentException("Null constructor not allowed");
        }
        this._constructor = constructor;
    }

    public Constructor<?> getAnnotated() {
        return this._constructor;
    }

    public String getName() {
        return this._constructor.getName();
    }

    public Type getGenericType() {
        return getRawType();
    }

    public Class<?> getRawType() {
        return this._constructor.getDeclaringClass();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._constructor.getTypeParameters());
    }

    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }

    public Class<?> getRawParameterType(int i) {
        Class[] parameterTypes = this._constructor.getParameterTypes();
        return i >= parameterTypes.length ? null : parameterTypes[i];
    }

    public Type getGenericParameterType(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        return i >= genericParameterTypes.length ? null : genericParameterTypes[i];
    }

    public final Object call() {
        return this._constructor.newInstance(new Object[0]);
    }

    public final Object call(Object[] objArr) {
        return this._constructor.newInstance(objArr);
    }

    public final Object call1(Object obj) {
        return this._constructor.newInstance(new Object[]{obj});
    }

    public Class<?> getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }

    public Member getMember() {
        return this._constructor;
    }

    public void setValue(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
    }

    public Object getValue(Object obj) {
        throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + getDeclaringClass().getName());
    }

    public String toString() {
        return "[constructor for " + getName() + ", annotations: " + this._annotations + "]";
    }
}
