package com.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedField extends AnnotatedMember implements Serializable {
    protected final transient Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    public AnnotatedField withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedField(this._field, annotationMap);
    }

    public Field getAnnotated() {
        return this._field;
    }

    public int getModifiers() {
        return this._field.getModifiers();
    }

    public String getName() {
        return this._field.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations == null ? null : this._annotations.get(cls);
    }

    public Type getGenericType() {
        return this._field.getGenericType();
    }

    public Class<?> getRawType() {
        return this._field.getType();
    }

    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public Member getMember() {
        return this._field;
    }

    public void setValue(Object obj, Object obj2) {
        try {
            this._field.set(obj, obj2);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to setValue() for field " + getFullName() + ": " + e.getMessage(), e);
        }
    }

    public Object getValue(Object obj) {
        try {
            return this._field.get(obj);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to getValue() for field " + getFullName() + ": " + e.getMessage(), e);
        }
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName();
    }

    public String toString() {
        return "[field " + getFullName() + "]";
    }
}
