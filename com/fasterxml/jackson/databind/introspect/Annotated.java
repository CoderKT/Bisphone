package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

public abstract class Annotated {
    protected abstract AnnotationMap getAllAnnotations();

    public abstract AnnotatedElement getAnnotated();

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public abstract Type getGenericType();

    public abstract String getName();

    public abstract Class<?> getRawType();

    protected Annotated() {
    }

    public final <A extends Annotation> boolean hasAnnotation(Class<A> cls) {
        return getAnnotation(cls) != null;
    }

    public JavaType getType(TypeBindings typeBindings) {
        return typeBindings.resolveType(getGenericType());
    }
}
