package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache implements Serializable {
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers;
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers;

    public DeserializerCache() {
        this._cachedDeserializers = new ConcurrentHashMap(64, 0.75f, 2);
        this._incompleteDeserializers = new HashMap(8);
    }

    public JsonDeserializer<Object> findValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer;
        }
        _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        if (_findCachedDeserializer == null) {
            return _handleUnknownValueDeserializer(javaType);
        }
        return _findCachedDeserializer;
    }

    public KeyDeserializer findKeyDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        KeyDeserializer createKeyDeserializer = deserializerFactory.createKeyDeserializer(deserializationContext, javaType);
        if (createKeyDeserializer == null) {
            return _handleUnknownKeyDeserializer(javaType);
        }
        if (!(createKeyDeserializer instanceof ResolvableDeserializer)) {
            return createKeyDeserializer;
        }
        ((ResolvableDeserializer) createKeyDeserializer).resolve(deserializationContext);
        return createKeyDeserializer;
    }

    protected JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        if (javaType != null) {
            return (JsonDeserializer) this._cachedDeserializers.get(javaType);
        }
        throw new IllegalArgumentException("Null JavaType passed");
    }

    protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        JsonDeserializer<Object> _findCachedDeserializer;
        synchronized (this._incompleteDeserializers) {
            _findCachedDeserializer = _findCachedDeserializer(javaType);
            if (_findCachedDeserializer != null) {
            } else {
                r2 = this._incompleteDeserializers.size();
                if (r2 > 0) {
                    _findCachedDeserializer = (JsonDeserializer) this._incompleteDeserializers.get(javaType);
                    if (_findCachedDeserializer != null) {
                    }
                }
                try {
                    _findCachedDeserializer = _createAndCache2(deserializationContext, deserializerFactory, javaType);
                    if (r2 == 0) {
                        if (this._incompleteDeserializers.size() > 0) {
                            this._incompleteDeserializers.clear();
                        }
                    }
                } catch (Throwable th) {
                    int size;
                    if (size == 0 && this._incompleteDeserializers.size() > 0) {
                        this._incompleteDeserializers.clear();
                    }
                }
            }
        }
        return _findCachedDeserializer;
    }

    protected JsonDeserializer<Object> _createAndCache2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        try {
            JsonDeserializer<Object> _createDeserializer = _createDeserializer(deserializationContext, deserializerFactory, javaType);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean isCachable = _createDeserializer.isCachable();
            if (z) {
                this._incompleteDeserializers.put(javaType, _createDeserializer);
                ((ResolvableDeserializer) _createDeserializer).resolve(deserializationContext);
                this._incompleteDeserializers.remove(javaType);
            }
            if (!isCachable) {
                return _createDeserializer;
            }
            this._cachedDeserializers.put(javaType, _createDeserializer);
            return _createDeserializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonDeserializer<Object> _createDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isAbstract() || javaType.isMapLikeType() || javaType.isCollectionLikeType()) {
            javaType = deserializerFactory.mapAbstractType(config, javaType);
        }
        BeanDescription introspect = config.introspect(javaType);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, introspect.getClassInfo(), javaType);
        if (modifyTypeByAnnotation != javaType) {
            introspect = config.introspect(modifyTypeByAnnotation);
            javaType = modifyTypeByAnnotation;
        }
        Class findPOJOBuilder = introspect.findPOJOBuilder();
        if (findPOJOBuilder != null) {
            return deserializerFactory.createBuilderBasedDeserializer(deserializationContext, javaType, introspect, findPOJOBuilder);
        }
        Converter findDeserializationConverter = introspect.findDeserializationConverter();
        if (findDeserializationConverter == null) {
            return _createDeserializer2(deserializationContext, deserializerFactory, javaType, introspect);
        }
        JavaType inputType = findDeserializationConverter.getInputType(deserializationContext.getTypeFactory());
        if (!inputType.hasRawClass(javaType.getRawClass())) {
            introspect = config.introspect(inputType);
        }
        return new StdDelegatingDeserializer(findDeserializationConverter, inputType, _createDeserializer2(deserializationContext, deserializerFactory, inputType, introspect));
    }

    protected JsonDeserializer<?> _createDeserializer2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType, BeanDescription beanDescription) {
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isEnumType()) {
            return deserializerFactory.createEnumDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return deserializerFactory.createArrayDeserializer(deserializationContext, (ArrayType) javaType, beanDescription);
            }
            if (javaType.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                if (mapLikeType.isTrueMapType()) {
                    return deserializerFactory.createMapDeserializer(deserializationContext, (MapType) mapLikeType, beanDescription);
                }
                return deserializerFactory.createMapLikeDeserializer(deserializationContext, mapLikeType, beanDescription);
            } else if (javaType.isCollectionLikeType()) {
                Value findExpectedFormat = beanDescription.findExpectedFormat(null);
                if (findExpectedFormat == null || findExpectedFormat.getShape() != Shape.OBJECT) {
                    CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                    if (collectionLikeType.isTrueCollectionType()) {
                        return deserializerFactory.createCollectionDeserializer(deserializationContext, (CollectionType) collectionLikeType, beanDescription);
                    }
                    return deserializerFactory.createCollectionLikeDeserializer(deserializationContext, collectionLikeType, beanDescription);
                }
            }
        }
        if (JsonNode.class.isAssignableFrom(javaType.getRawClass())) {
            return deserializerFactory.createTreeDeserializer(config, javaType, beanDescription);
        }
        return deserializerFactory.createBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) {
        Object findDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer == null) {
            return null;
        }
        return findConvertingDeserializer(deserializationContext, annotated, deserializationContext.deserializerInstance(annotated, findDeserializer));
    }

    protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, Annotated annotated, JsonDeserializer<Object> jsonDeserializer) {
        Converter findConverter = findConverter(deserializationContext, annotated);
        return findConverter == null ? jsonDeserializer : new StdDelegatingDeserializer(findConverter, findConverter.getInputType(deserializationContext.getTypeFactory()), jsonDeserializer);
    }

    protected Converter<Object, Object> findConverter(DeserializationContext deserializationContext, Annotated annotated) {
        Object findDeserializationConverter = deserializationContext.getAnnotationIntrospector().findDeserializationConverter(annotated);
        if (findDeserializationConverter == null) {
            return null;
        }
        return deserializationContext.converterInstance(annotated, findDeserializationConverter);
    }

    private JavaType modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, JavaType javaType) {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, javaType);
        if (findDeserializationType != null) {
            try {
                JavaType narrowBy = javaType.narrowBy(findDeserializationType);
            } catch (Throwable e) {
                throw new JsonMappingException("Failed to narrow type " + javaType + " with concrete-type annotation (value " + findDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        }
        narrowBy = javaType;
        if (!narrowBy.isContainerType()) {
            return narrowBy;
        }
        JavaType javaType2;
        Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, narrowBy.getKeyType());
        if (findDeserializationKeyType == null) {
            javaType2 = narrowBy;
        } else if (narrowBy instanceof MapLikeType) {
            try {
                javaType2 = ((MapLikeType) narrowBy).narrowKey(findDeserializationKeyType);
            } catch (Throwable e2) {
                throw new JsonMappingException("Failed to narrow key type " + narrowBy + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
            }
        } else {
            throw new JsonMappingException("Illegal key-type annotation: type " + narrowBy + " is not a Map(-like) type");
        }
        narrowBy = javaType2.getKeyType();
        if (narrowBy != null && narrowBy.getValueHandler() == null) {
            Object findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated);
            if (findKeyDeserializer != null) {
                KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, findKeyDeserializer);
                if (keyDeserializerInstance != null) {
                    javaType2 = ((MapLikeType) javaType2).withKeyValueHandler(keyDeserializerInstance);
                    javaType2.getKeyType();
                }
            }
        }
        Class findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, javaType2.getContentType());
        if (findDeserializationContentType != null) {
            try {
                narrowBy = javaType2.narrowContentsBy(findDeserializationContentType);
            } catch (Throwable e3) {
                throw new JsonMappingException("Failed to narrow content type " + javaType2 + " with content-type annotation (" + findDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
            }
        }
        narrowBy = javaType2;
        if (narrowBy.getContentType().getValueHandler() != null) {
            return narrowBy;
        }
        Object findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated);
        if (findContentDeserializer == null) {
            return narrowBy;
        }
        if (findContentDeserializer instanceof JsonDeserializer) {
            JsonDeserializer jsonDeserializer = (JsonDeserializer) findContentDeserializer;
            findContentDeserializer = null;
        } else {
            findDeserializationType = _verifyAsClass(findContentDeserializer, "findContentDeserializer", None.class);
            findContentDeserializer = findDeserializationType != null ? deserializationContext.deserializerInstance(annotated, findDeserializationType) : null;
        }
        if (findContentDeserializer != null) {
            return narrowBy.withContentValueHandler(findContentDeserializer);
        }
        return narrowBy;
    }

    private Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Class) {
            Class<?> cls2 = (Class) obj;
            if (cls2 == cls || ClassUtil.isBogusClass(cls2)) {
                return null;
            }
            return cls2;
        }
        throw new IllegalStateException("AnnotationIntrospector." + str + "() returned value of type " + obj.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
    }

    protected JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType javaType) {
        if (ClassUtil.isConcrete(javaType.getRawClass())) {
            throw new JsonMappingException("Can not find a Value deserializer for type " + javaType);
        }
        throw new JsonMappingException("Can not find a Value deserializer for abstract type " + javaType);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) {
        throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + javaType);
    }
}
