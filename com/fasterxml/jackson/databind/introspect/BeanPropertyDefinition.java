package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;

public abstract class BeanPropertyDefinition {
    public abstract AnnotatedMember getAccessor();

    public abstract AnnotatedParameter getConstructorParameter();

    public abstract AnnotatedField getField();

    public abstract PropertyName getFullName();

    public abstract AnnotatedMethod getGetter();

    public abstract PropertyMetadata getMetadata();

    public abstract AnnotatedMember getMutator();

    public abstract String getName();

    public abstract AnnotatedMember getNonConstructorMutator();

    public abstract AnnotatedMethod getSetter();

    public abstract PropertyName getWrapperName();

    public abstract boolean hasConstructorParameter();

    public abstract boolean hasField();

    public abstract boolean hasGetter();

    public abstract boolean hasSetter();

    public abstract boolean isExplicitlyIncluded();

    public boolean isExplicitlyNamed() {
        return isExplicitlyIncluded();
    }

    public boolean couldDeserialize() {
        return getMutator() != null;
    }

    public AnnotatedMember getPrimaryMember() {
        return null;
    }

    public Class<?>[] findViews() {
        return null;
    }

    public ReferenceProperty findReferenceType() {
        return null;
    }

    public boolean isTypeId() {
        return false;
    }

    public ObjectIdInfo findObjectIdInfo() {
        return null;
    }
}
