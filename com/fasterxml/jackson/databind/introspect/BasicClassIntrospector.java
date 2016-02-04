package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.type.SimpleType;
import java.io.Serializable;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    protected static final BasicBeanDescription BOOLEAN_DESC;
    protected static final BasicBeanDescription INT_DESC;
    protected static final BasicBeanDescription LONG_DESC;
    protected static final BasicBeanDescription STRING_DESC;
    public static final BasicClassIntrospector instance;

    static {
        STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, null, null));
        BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null, null));
        INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null, null));
        LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null, null));
        instance = new BasicClassIntrospector();
    }

    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        if (_findCachedDesc != null) {
            return _findCachedDesc;
        }
        return BasicBeanDescription.forSerialization(collectProperties(serializationConfig, javaType, mixInResolver, true, RSMSet.ELEMENT));
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        if (_findCachedDesc != null) {
            return _findCachedDesc;
        }
        return BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false, RSMSet.ELEMENT));
    }

    public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(deserializationConfig, javaType, mixInResolver, false));
    }

    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        if (_findCachedDesc != null) {
            return _findCachedDesc;
        }
        return BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false, RSMSet.ELEMENT));
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver) {
        return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClass.construct(javaType.getRawClass(), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mixInResolver));
    }

    protected POJOPropertiesCollector collectProperties(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver, boolean z, String str) {
        return constructPropertyCollector(mapperConfig, AnnotatedClass.construct(javaType.getRawClass(), mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null, mixInResolver), javaType, z, str).collect();
    }

    protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver, boolean z) {
        AnnotationIntrospector annotationIntrospector;
        Value value = null;
        if (mapperConfig.isAnnotationProcessingEnabled()) {
            annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        } else {
            annotationIntrospector = null;
        }
        AnnotatedClass construct = AnnotatedClass.construct(javaType.getRawClass(), annotationIntrospector, mixInResolver);
        if (annotationIntrospector != null) {
            value = annotationIntrospector.findPOJOBuilderConfig(construct);
        }
        return constructPropertyCollector(mapperConfig, construct, javaType, z, value == null ? "with" : value.withPrefix).collect();
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z, String str) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass, str);
    }

    protected BasicBeanDescription _findCachedDesc(JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return STRING_DESC;
        }
        if (rawClass == Boolean.TYPE) {
            return BOOLEAN_DESC;
        }
        if (rawClass == Integer.TYPE) {
            return INT_DESC;
        }
        if (rawClass == Long.TYPE) {
            return LONG_DESC;
        }
        return null;
    }
}
