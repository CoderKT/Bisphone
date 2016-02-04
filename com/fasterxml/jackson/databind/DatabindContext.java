package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Type;

public abstract class DatabindContext {
    public abstract MapperConfig<?> getConfig();

    public abstract TypeFactory getTypeFactory();

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return getConfig().isEnabled(mapperFeature);
    }

    public final boolean canOverrideAccessModifiers() {
        return getConfig().canOverrideAccessModifiers();
    }

    public JavaType constructType(Type type) {
        return getTypeFactory().constructType(type);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return javaType.getRawClass() == cls ? javaType : getConfig().constructSpecializedType(javaType, cls);
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Class generatorType = objectIdInfo.getGeneratorType();
        MapperConfig config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdGenerator objectIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.objectIdGeneratorInstance(config, annotated, generatorType);
        if (objectIdGeneratorInstance == null) {
            objectIdGeneratorInstance = (ObjectIdGenerator) ClassUtil.createInstance(generatorType, config.canOverrideAccessModifiers());
        }
        return objectIdGeneratorInstance.forScope(objectIdInfo.getScope());
    }

    public ObjectIdResolver objectIdResolverInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Class resolverType = objectIdInfo.getResolverType();
        MapperConfig config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdResolver resolverIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.resolverIdGeneratorInstance(config, annotated, resolverType);
        if (resolverIdGeneratorInstance == null) {
            return (ObjectIdResolver) ClassUtil.createInstance(resolverType, config.canOverrideAccessModifiers());
        }
        return resolverIdGeneratorInstance;
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object obj) {
        Converter converter = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls == None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (Converter.class.isAssignableFrom(cls)) {
                MapperConfig config = getConfig();
                HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    converter = handlerInstantiator.converterInstance(config, annotated, cls);
                }
                if (converter == null) {
                    converter = (Converter) ClassUtil.createInstance(cls, config.canOverrideAccessModifiers());
                }
                return converter;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }
}
