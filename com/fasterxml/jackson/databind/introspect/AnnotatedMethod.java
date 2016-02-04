package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    protected final transient Method _method;
    protected Class<?>[] _paramClasses;

    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        if (method == null) {
            throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
        }
        this._method = method;
    }

    public AnnotatedMethod withMethod(Method method) {
        return new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
    }

    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._method, annotationMap, this._paramAnnotations);
    }

    public Method getAnnotated() {
        return this._method;
    }

    public String getName() {
        return this._method.getName();
    }

    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    public final Object call() {
        return this._method.invoke(null, new Object[0]);
    }

    public final Object call(Object[] objArr) {
        return this._method.invoke(null, objArr);
    }

    public final Object call1(Object obj) {
        return this._method.invoke(null, new Object[]{obj});
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public Method getMember() {
        return this._method;
    }

    public void setValue(Object obj, Object obj2) {
        try {
            this._method.invoke(obj, new Object[]{obj2});
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }

    public Object getValue(Object obj) {
        try {
            return this._method.invoke(obj, new Object[0]);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }

    public int getParameterCount() {
        return getRawParameterTypes().length;
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
    }

    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }

    public Class<?> getRawParameterType(int i) {
        Class[] rawParameterTypes = getRawParameterTypes();
        return i >= rawParameterTypes.length ? null : rawParameterTypes[i];
    }

    public Type getGenericParameterType(int i) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        return i >= genericParameterTypes.length ? null : genericParameterTypes[i];
    }

    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }

    public boolean hasReturnType() {
        Class rawReturnType = getRawReturnType();
        return (rawReturnType == Void.TYPE || rawReturnType == Void.class) ? false : true;
    }

    public String toString() {
        return "[method " + getFullName() + "]";
    }
}
