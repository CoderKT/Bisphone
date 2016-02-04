package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_BUFFER;
    private static final Class<?> CLASS_ITERABLE;
    private static final Class<?> CLASS_OBJECT;
    private static final Class<?> CLASS_STRING;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    static final HashMap<String, Class<? extends Map>> _mapFallbacks;
    protected final DeserializerFactoryConfig _factoryConfig;

    static {
        CLASS_OBJECT = Object.class;
        CLASS_STRING = String.class;
        CLASS_CHAR_BUFFER = CharSequence.class;
        CLASS_ITERABLE = Iterable.class;
        UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
        _mapFallbacks = new HashMap();
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        } catch (Throwable th) {
            System.err.println("Problems with (optional) types: " + th);
        }
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }

    protected BasicDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        this._factoryConfig = deserializerFactoryConfig;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType mapAbstractType(com.fasterxml.jackson.databind.DeserializationConfig r5, com.fasterxml.jackson.databind.JavaType r6) {
        /*
        r4 = this;
    L_0x0000:
        r0 = r4._mapAbstractType2(r5, r6);
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return r6;
    L_0x0007:
        r1 = r6.getRawClass();
        r2 = r0.getRawClass();
        if (r1 == r2) goto L_0x0017;
    L_0x0011:
        r1 = r1.isAssignableFrom(r2);
        if (r1 != 0) goto L_0x0040;
    L_0x0017:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Invalid abstract type resolution from ";
        r2 = r2.append(r3);
        r2 = r2.append(r6);
        r3 = " to ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r2 = ": latter is not a subtype of former";
        r0 = r0.append(r2);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x0040:
        r6 = r0;
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.mapAbstractType(com.fasterxml.jackson.databind.DeserializationConfig, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JavaType");
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver findTypeMapping : this._factoryConfig.abstractTypeResolvers()) {
                JavaType findTypeMapping2 = findTypeMapping.findTypeMapping(deserializationConfig, javaType);
                if (findTypeMapping2 != null && findTypeMapping2.getRawClass() != rawClass) {
                    return findTypeMapping2;
                }
            }
        }
        return null;
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        DeserializationConfig config = deserializationContext.getConfig();
        ValueInstantiator valueInstantiator = null;
        Annotated classInfo = beanDescription.getClassInfo();
        Object findValueInstantiator = deserializationContext.getAnnotationIntrospector().findValueInstantiator(classInfo);
        if (findValueInstantiator != null) {
            valueInstantiator = _valueInstantiatorInstance(config, classInfo, findValueInstantiator);
        }
        if (valueInstantiator == null) {
            valueInstantiator = _findStdValueInstantiator(config, beanDescription);
            if (valueInstantiator == null) {
                valueInstantiator = _constructDefaultValueInstantiator(deserializationContext, beanDescription);
            }
        }
        ValueInstantiator valueInstantiator2;
        if (this._factoryConfig.hasValueInstantiators()) {
            valueInstantiator2 = valueInstantiator;
            for (ValueInstantiators valueInstantiators : this._factoryConfig.valueInstantiators()) {
                valueInstantiator2 = valueInstantiators.findValueInstantiator(config, beanDescription, valueInstantiator2);
                if (valueInstantiator2 == null) {
                    throw new JsonMappingException("Broken registered ValueInstantiators (of type " + valueInstantiators.getClass().getName() + "): returned null ValueInstantiator");
                }
            }
        }
        valueInstantiator2 = valueInstantiator;
        if (valueInstantiator2.getIncompleteParameter() == null) {
            return valueInstantiator2;
        }
        AnnotatedParameter incompleteParameter = valueInstantiator2.getIncompleteParameter();
        throw new IllegalArgumentException("Argument #" + incompleteParameter.getIndex() + " of constructor " + incompleteParameter.getOwner() + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        if (beanDescription.getBeanClass() == JsonLocation.class) {
            return new JsonLocationInstantiator();
        }
        return null;
    }

    protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        CreatorCollector creatorCollector = new CreatorCollector(beanDescription, deserializationContext.canOverrideAccessModifiers());
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        DeserializationConfig config = deserializationContext.getConfig();
        VisibilityChecker findAutoDetectVisibility = annotationIntrospector.findAutoDetectVisibility(beanDescription.getClassInfo(), config.getDefaultVisibilityChecker());
        _addDeserializerFactoryMethods(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        if (beanDescription.getType().isConcrete()) {
            _addDeserializerConstructors(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        }
        return creatorCollector.constructValueInstantiator(config);
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig deserializationConfig, Annotated annotated, Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ValueInstantiator) {
            return (ValueInstantiator) obj;
        }
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (ValueInstantiator.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = deserializationConfig.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    ValueInstantiator valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(deserializationConfig, annotated, cls);
                    if (valueInstantiatorInstance != null) {
                        return valueInstantiatorInstance;
                    }
                }
                return (ValueInstantiator) ClassUtil.createInstance(cls, deserializationConfig.canOverrideAccessModifiers());
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<ValueInstantiator>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
    }

    protected void _addDeserializerConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) {
        Annotated findDefaultConstructor = beanDescription.findDefaultConstructor();
        if (findDefaultConstructor != null && (!creatorCollector.hasDefaultCreator() || annotationIntrospector.hasCreatorAnnotation(findDefaultConstructor))) {
            creatorCollector.setDefaultCreator(findDefaultConstructor);
        }
        PropertyName[] propertyNameArr = null;
        AnnotatedConstructor annotatedConstructor = null;
        for (BeanPropertyDefinition beanPropertyDefinition : beanDescription.findProperties()) {
            if (beanPropertyDefinition.getConstructorParameter() != null) {
                AnnotatedParameter constructorParameter = beanPropertyDefinition.getConstructorParameter();
                AnnotatedWithParams owner = constructorParameter.getOwner();
                if (owner instanceof AnnotatedConstructor) {
                    AnnotatedConstructor annotatedConstructor2;
                    PropertyName[] propertyNameArr2;
                    if (annotatedConstructor == null) {
                        annotatedConstructor2 = (AnnotatedConstructor) owner;
                        propertyNameArr2 = new PropertyName[annotatedConstructor2.getParameterCount()];
                    } else {
                        annotatedConstructor2 = annotatedConstructor;
                        propertyNameArr2 = propertyNameArr;
                    }
                    propertyNameArr2[constructorParameter.getIndex()] = beanPropertyDefinition.getFullName();
                    annotatedConstructor = annotatedConstructor2;
                    propertyNameArr = propertyNameArr2;
                }
            }
        }
        for (AnnotatedConstructor annotatedConstructor3 : beanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor3.getParameterCount();
            boolean z = annotationIntrospector.hasCreatorAnnotation(annotatedConstructor3) || annotatedConstructor3 == annotatedConstructor;
            boolean isCreatorVisible = visibilityChecker.isCreatorVisible(annotatedConstructor3);
            if (parameterCount == 1) {
                _handleSingleArgumentConstructor(deserializationContext, beanDescription, visibilityChecker, annotationIntrospector, creatorCollector, annotatedConstructor3, z, isCreatorVisible, annotatedConstructor3 == annotatedConstructor ? propertyNameArr[0] : null);
            } else if (z || isCreatorVisible) {
                AnnotatedMember annotatedMember = null;
                int i = 0;
                int i2 = 0;
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                int i3 = 0;
                while (i3 < parameterCount) {
                    AnnotatedMember parameter = annotatedConstructor3.getParameter(i3);
                    PropertyName propertyName = null;
                    if (annotatedConstructor3 == annotatedConstructor) {
                        propertyName = propertyNameArr[i3];
                    }
                    if (propertyName == null) {
                        propertyName = _findParamName(parameter, annotationIntrospector);
                    }
                    Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter);
                    if (propertyName != null && propertyName.hasSimpleName()) {
                        i++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i3, parameter, findInjectableValueId);
                        parameter = annotatedMember;
                    } else if (findInjectableValueId != null) {
                        i2++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i3, parameter, findInjectableValueId);
                        parameter = annotatedMember;
                    } else if (annotationIntrospector.findUnwrappingNameTransformer(parameter) != null) {
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, UNWRAPPED_CREATOR_PARAM_NAME, i3, parameter, null);
                        i++;
                        parameter = annotatedMember;
                    } else if (annotatedMember != null) {
                        parameter = annotatedMember;
                    }
                    i3++;
                    annotatedMember = parameter;
                }
                if (z || i > 0 || i2 > 0) {
                    if (i + i2 == parameterCount) {
                        creatorCollector.addPropertyCreator(annotatedConstructor3, creatorPropertyArr);
                    } else if (i == 0 && i2 + 1 == parameterCount) {
                        creatorCollector.addDelegatingCreator(annotatedConstructor3, creatorPropertyArr);
                    } else {
                        creatorCollector.addIncompeteParameter(annotatedMember);
                    }
                }
            }
        }
    }

    protected boolean _handleSingleArgumentConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2, PropertyName propertyName) {
        PropertyName _findParamName;
        AnnotatedMember parameter = annotatedConstructor.getParameter(0);
        if (propertyName == null) {
            _findParamName = _findParamName(parameter, annotationIntrospector);
        } else {
            _findParamName = propertyName;
        }
        if (annotationIntrospector.findInjectableValueId(parameter) != null || (_findParamName != null && _findParamName.hasSimpleName())) {
            creatorCollector.addPropertyCreator(annotatedConstructor, new CreatorProperty[]{constructCreatorProperty(deserializationContext, beanDescription, _findParamName, 0, parameter, r6)});
            return true;
        }
        Class rawParameterType = annotatedConstructor.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (z || z2) {
                creatorCollector.addStringCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z || z2) {
                creatorCollector.addIntCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z || z2) {
                creatorCollector.addLongCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z || z2) {
                creatorCollector.addDoubleCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z || z2) {
                creatorCollector.addBooleanCreator(annotatedConstructor);
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            creatorCollector.addDelegatingCreator(annotatedConstructor, null);
            return true;
        }
    }

    protected void _addDeserializerFactoryMethods(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) {
        DeserializationConfig config = deserializationContext.getConfig();
        for (AnnotatedMethod annotatedMethod : beanDescription.getFactoryMethods()) {
            boolean hasCreatorAnnotation = annotationIntrospector.hasCreatorAnnotation(annotatedMethod);
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount != 0) {
                if (parameterCount == 1) {
                    AnnotatedMember parameter = annotatedMethod.getParameter(0);
                    PropertyName _findParamName = _findParamName(parameter, annotationIntrospector);
                    String simpleName = _findParamName == null ? null : _findParamName.getSimpleName();
                    if (annotationIntrospector.findInjectableValueId(parameter) == null && (simpleName == null || simpleName.length() == 0)) {
                        _handleSingleArgumentFactory(config, beanDescription, visibilityChecker, annotationIntrospector, creatorCollector, annotatedMethod, hasCreatorAnnotation);
                    }
                } else if (!annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                    continue;
                }
                AnnotatedMember annotatedMember = null;
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                while (i3 < parameterCount) {
                    AnnotatedMember parameter2 = annotatedMethod.getParameter(i3);
                    PropertyName _findParamName2 = _findParamName(parameter2, annotationIntrospector);
                    Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter2);
                    if (_findParamName2 != null && _findParamName2.hasSimpleName()) {
                        i++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, _findParamName2, i3, parameter2, findInjectableValueId);
                        parameter2 = annotatedMember;
                    } else if (findInjectableValueId != null) {
                        i2++;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, _findParamName2, i3, parameter2, findInjectableValueId);
                        parameter2 = annotatedMember;
                    } else if (annotationIntrospector.findUnwrappingNameTransformer(parameter2) != null) {
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, UNWRAPPED_CREATOR_PARAM_NAME, i3, parameter2, null);
                        i++;
                        parameter2 = annotatedMember;
                    } else if (annotatedMember != null) {
                        parameter2 = annotatedMember;
                    }
                    i3++;
                    annotatedMember = parameter2;
                }
                if (hasCreatorAnnotation || i > 0 || i2 > 0) {
                    if (i + i2 == parameterCount) {
                        creatorCollector.addPropertyCreator(annotatedMethod, creatorPropertyArr);
                    } else if (i == 0 && i2 + 1 == parameterCount) {
                        creatorCollector.addDelegatingCreator(annotatedMethod, creatorPropertyArr);
                    } else {
                        throw new IllegalArgumentException("Argument #" + annotatedMember.getIndex() + " of factory method " + annotatedMethod + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                    }
                }
            } else if (hasCreatorAnnotation) {
                creatorCollector.setDefaultCreator(annotatedMethod);
            }
        }
    }

    protected boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) {
        Class rawParameterType = annotatedMethod.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (!z && !visibilityChecker.isCreatorVisible(annotatedMethod)) {
                return true;
            }
            creatorCollector.addStringCreator(annotatedMethod);
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (!z && !visibilityChecker.isCreatorVisible(annotatedMethod)) {
                return true;
            }
            creatorCollector.addIntCreator(annotatedMethod);
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (!z && !visibilityChecker.isCreatorVisible(annotatedMethod)) {
                return true;
            }
            creatorCollector.addLongCreator(annotatedMethod);
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (!z && !visibilityChecker.isCreatorVisible(annotatedMethod)) {
                return true;
            }
            creatorCollector.addDoubleCreator(annotatedMethod);
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (!z && !visibilityChecker.isCreatorVisible(annotatedMethod)) {
                return true;
            }
            creatorCollector.addBooleanCreator(annotatedMethod);
            return true;
        } else if (!annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
            return false;
        } else {
            creatorCollector.addDelegatingCreator(annotatedMethod, null);
            return true;
        }
    }

    protected CreatorProperty constructCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, PropertyName propertyName, int i, AnnotatedParameter annotatedParameter, Object obj) {
        Std withType;
        TypeDeserializer findTypeDeserializer;
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Boolean hasRequiredMarker = annotationIntrospector == null ? null : annotationIntrospector.hasRequiredMarker(annotatedParameter);
        boolean z = hasRequiredMarker != null && hasRequiredMarker.booleanValue();
        PropertyMetadata construct = PropertyMetadata.construct(z, annotationIntrospector == null ? null : annotationIntrospector.findPropertyDescription(annotatedParameter), annotationIntrospector == null ? null : annotationIntrospector.findPropertyIndex(annotatedParameter));
        JavaType constructType = config.getTypeFactory().constructType(annotatedParameter.getParameterType(), beanDescription.bindingsForBeanType());
        Std std = new Std(propertyName, constructType, annotationIntrospector.findWrapperName(annotatedParameter), beanDescription.getClassAnnotations(), annotatedParameter, construct);
        JavaType resolveType = resolveType(deserializationContext, beanDescription, constructType, annotatedParameter);
        if (resolveType != constructType) {
            withType = std.withType(resolveType);
        } else {
            withType = std;
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, annotatedParameter);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, annotatedParameter, resolveType);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, modifyTypeByAnnotation);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        CreatorProperty creatorProperty = new CreatorProperty(propertyName, modifyTypeByAnnotation, withType.getWrapperName(), findTypeDeserializer, beanDescription.getClassAnnotations(), annotatedParameter, i, obj, construct);
        if (findDeserializerFromAnnotation != null) {
            return creatorProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, creatorProperty));
        }
        return creatorProperty;
    }

    protected PropertyName _findParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        if (!(annotatedParameter == null || annotationIntrospector == null)) {
            PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedParameter);
            if (findNameForDeserialization != null) {
                return findNameForDeserialization;
            }
            String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedParameter);
            if (!(findImplicitPropertyName == null || findImplicitPropertyName.isEmpty())) {
                return new PropertyName(findImplicitPropertyName);
            }
        }
        return null;
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext deserializationContext, ArrayType arrayType, BeanDescription beanDescription) {
        TypeDeserializer findTypeDeserializer;
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, contentType);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, config, beanDescription, findTypeDeserializer, jsonDeserializer);
        if (_findCustomArrayDeserializer == null) {
            if (jsonDeserializer == null) {
                Class rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (rawClass == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            _findCustomArrayDeserializer = new ObjectArrayDeserializer(arrayType, jsonDeserializer, findTypeDeserializer);
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return _findCustomArrayDeserializer;
        }
        JsonDeserializer<?> jsonDeserializer2 = _findCustomArrayDeserializer;
        for (BeanDeserializerModifier modifyArrayDeserializer : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer2 = modifyArrayDeserializer.modifyArrayDeserializer(config, arrayType, beanDescription, jsonDeserializer2);
        }
        return jsonDeserializer2;
    }

    protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        for (Deserializers findArrayDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer2 = findArrayDeserializer.findArrayDeserializer(arrayType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer2 != null) {
                return findArrayDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) {
        TypeDeserializer findTypeDeserializer;
        CollectionType _mapAbstractCollectionType;
        JsonDeserializer<?> jsonDeserializer;
        JavaType contentType = collectionType.getContentType();
        JsonDeserializer jsonDeserializer2 = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, contentType);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType, config, beanDescription, findTypeDeserializer, jsonDeserializer2);
        if (_findCustomCollectionDeserializer == null) {
            Class rawClass = collectionType.getRawClass();
            if (jsonDeserializer2 == null && EnumSet.class.isAssignableFrom(rawClass)) {
                _findCustomCollectionDeserializer = new EnumSetDeserializer(contentType, null);
            }
        }
        if (_findCustomCollectionDeserializer == null) {
            if (collectionType.isInterface() || collectionType.isAbstract()) {
                _mapAbstractCollectionType = _mapAbstractCollectionType(collectionType, config);
                if (_mapAbstractCollectionType != null) {
                    beanDescription = config.introspectForCreation(_mapAbstractCollectionType);
                } else if (collectionType.getTypeHandler() == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType);
                } else {
                    _findCustomCollectionDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                    _mapAbstractCollectionType = collectionType;
                }
            } else {
                _mapAbstractCollectionType = collectionType;
            }
            if (_findCustomCollectionDeserializer == null) {
                ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
                if (!findValueInstantiator.canCreateUsingDefault() && _mapAbstractCollectionType.getRawClass() == ArrayBlockingQueue.class) {
                    return new ArrayBlockingQueueDeserializer(_mapAbstractCollectionType, jsonDeserializer2, findTypeDeserializer, findValueInstantiator, null);
                }
                if (contentType.getRawClass() == String.class) {
                    _findCustomCollectionDeserializer = new StringCollectionDeserializer(_mapAbstractCollectionType, jsonDeserializer2, findValueInstantiator);
                } else {
                    _findCustomCollectionDeserializer = new CollectionDeserializer(_mapAbstractCollectionType, jsonDeserializer2, findTypeDeserializer, findValueInstantiator);
                }
            }
        } else {
            _mapAbstractCollectionType = collectionType;
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            jsonDeserializer = _findCustomCollectionDeserializer;
            for (BeanDeserializerModifier modifyCollectionDeserializer : this._factoryConfig.deserializerModifiers()) {
                jsonDeserializer = modifyCollectionDeserializer.modifyCollectionDeserializer(config, _mapAbstractCollectionType, beanDescription, jsonDeserializer);
            }
        } else {
            jsonDeserializer = _findCustomCollectionDeserializer;
        }
        return jsonDeserializer;
    }

    protected CollectionType _mapAbstractCollectionType(JavaType javaType, DeserializationConfig deserializationConfig) {
        Class cls = (Class) _collectionFallbacks.get(javaType.getRawClass().getName());
        if (cls == null) {
            return null;
        }
        return (CollectionType) deserializationConfig.constructSpecializedType(javaType, cls);
    }

    protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        for (Deserializers findCollectionDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer2 = findCollectionDeserializer.findCollectionDeserializer(collectionType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer2 != null) {
                return findCollectionDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext deserializationContext, CollectionLikeType collectionLikeType, BeanDescription beanDescription) {
        TypeDeserializer findTypeDeserializer;
        JavaType contentType = collectionLikeType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, contentType);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        JsonDeserializer<?> _findCustomCollectionLikeDeserializer = _findCustomCollectionLikeDeserializer(collectionLikeType, config, beanDescription, findTypeDeserializer, jsonDeserializer);
        if (_findCustomCollectionLikeDeserializer == null || !this._factoryConfig.hasDeserializerModifiers()) {
            return _findCustomCollectionLikeDeserializer;
        }
        JsonDeserializer<?> jsonDeserializer2 = _findCustomCollectionLikeDeserializer;
        for (BeanDeserializerModifier modifyCollectionLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer2 = modifyCollectionLikeDeserializer.modifyCollectionLikeDeserializer(config, collectionLikeType, beanDescription, jsonDeserializer2);
        }
        return jsonDeserializer2;
    }

    protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        for (Deserializers findCollectionLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer2 = findCollectionLikeDeserializer.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer2 != null) {
                return findCollectionLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createMapDeserializer(DeserializationContext deserializationContext, MapType mapType, BeanDescription beanDescription) {
        TypeDeserializer findTypeDeserializer;
        MapType mapType2;
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType keyType = mapType.getKeyType();
        JavaType contentType = mapType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, contentType);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        JsonDeserializer<?> _findCustomMapDeserializer = _findCustomMapDeserializer(mapType, config, beanDescription, keyDeserializer, findTypeDeserializer, jsonDeserializer);
        if (_findCustomMapDeserializer == null) {
            Class rawClass = mapType.getRawClass();
            if (EnumMap.class.isAssignableFrom(rawClass)) {
                Class rawClass2 = keyType.getRawClass();
                if (rawClass2 == null || !rawClass2.isEnum()) {
                    throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
                }
                _findCustomMapDeserializer = new EnumMapDeserializer(mapType, null, jsonDeserializer, findTypeDeserializer);
            }
            if (_findCustomMapDeserializer == null) {
                if (mapType.isInterface() || mapType.isAbstract()) {
                    rawClass = (Class) _mapFallbacks.get(rawClass.getName());
                    if (rawClass != null) {
                        MapType mapType3 = (MapType) config.constructSpecializedType(mapType, rawClass);
                        beanDescription = config.introspectForCreation(mapType3);
                        mapType2 = mapType3;
                    } else if (mapType.getTypeHandler() == null) {
                        throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType);
                    } else {
                        _findCustomMapDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                        mapType2 = mapType;
                    }
                } else {
                    mapType2 = mapType;
                }
                if (_findCustomMapDeserializer == null) {
                    _findCustomMapDeserializer = new MapDeserializer((JavaType) mapType2, findValueInstantiator(deserializationContext, beanDescription), keyDeserializer, jsonDeserializer, findTypeDeserializer);
                    _findCustomMapDeserializer.setIgnorableProperties(config.getAnnotationIntrospector().findPropertiesToIgnore(beanDescription.getClassInfo()));
                }
                if (this._factoryConfig.hasDeserializerModifiers()) {
                    for (BeanDeserializerModifier modifyMapDeserializer : this._factoryConfig.deserializerModifiers()) {
                        _findCustomMapDeserializer = modifyMapDeserializer.modifyMapDeserializer(config, mapType2, beanDescription, _findCustomMapDeserializer);
                    }
                }
                return _findCustomMapDeserializer;
            }
        }
        mapType2 = mapType;
        if (this._factoryConfig.hasDeserializerModifiers()) {
            while (r2.hasNext()) {
                _findCustomMapDeserializer = modifyMapDeserializer.modifyMapDeserializer(config, mapType2, beanDescription, _findCustomMapDeserializer);
            }
        }
        return _findCustomMapDeserializer;
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext deserializationContext, MapLikeType mapLikeType, BeanDescription beanDescription) {
        TypeDeserializer findTypeDeserializer;
        JavaType keyType = mapLikeType.getKeyType();
        JavaType contentType = mapLikeType.getContentType();
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            findTypeDeserializer = findTypeDeserializer(config, contentType);
        } else {
            findTypeDeserializer = typeDeserializer;
        }
        JsonDeserializer<?> _findCustomMapLikeDeserializer = _findCustomMapLikeDeserializer(mapLikeType, config, beanDescription, keyDeserializer, findTypeDeserializer, jsonDeserializer);
        if (_findCustomMapLikeDeserializer == null || !this._factoryConfig.hasDeserializerModifiers()) {
            return _findCustomMapLikeDeserializer;
        }
        JsonDeserializer<?> jsonDeserializer2 = _findCustomMapLikeDeserializer;
        for (BeanDeserializerModifier modifyMapLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer2 = modifyMapLikeDeserializer.modifyMapLikeDeserializer(config, mapLikeType, beanDescription, jsonDeserializer2);
        }
        return jsonDeserializer2;
    }

    protected JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        for (Deserializers findMapDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer2 = findMapDeserializer.findMapDeserializer(mapType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer2 != null) {
                return findMapDeserializer2;
            }
        }
        return null;
    }

    protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        for (Deserializers findMapLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer2 = findMapLikeDeserializer.findMapLikeDeserializer(mapLikeType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer2 != null) {
                return findMapLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JsonDeserializer<?> deserializerForCreator;
        DeserializationConfig config = deserializationContext.getConfig();
        Class rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, beanDescription);
        if (_findCustomEnumDeserializer == null) {
            for (AnnotatedMethod annotatedMethod : beanDescription.getFactoryMethods()) {
                if (deserializationContext.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                    if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawReturnType().isAssignableFrom(rawClass)) {
                        deserializerForCreator = EnumDeserializer.deserializerForCreator(config, rawClass, annotatedMethod);
                        if (deserializerForCreator == null) {
                            deserializerForCreator = new EnumDeserializer(constructEnumResolver(rawClass, config, beanDescription.findJsonValueMethod()));
                        }
                    } else {
                        throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
                    }
                }
            }
            deserializerForCreator = _findCustomEnumDeserializer;
            if (deserializerForCreator == null) {
                deserializerForCreator = new EnumDeserializer(constructEnumResolver(rawClass, config, beanDescription.findJsonValueMethod()));
            }
        } else {
            deserializerForCreator = _findCustomEnumDeserializer;
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return deserializerForCreator;
        }
        _findCustomEnumDeserializer = deserializerForCreator;
        for (BeanDeserializerModifier modifyEnumDeserializer : this._factoryConfig.deserializerModifiers()) {
            _findCustomEnumDeserializer = modifyEnumDeserializer.modifyEnumDeserializer(config, javaType, beanDescription, _findCustomEnumDeserializer);
        }
        return _findCustomEnumDeserializer;
    }

    protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        for (Deserializers findEnumDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer2 = findEnumDeserializer.findEnumDeserializer(cls, deserializationConfig, beanDescription);
            if (findEnumDeserializer2 != null) {
                return findEnumDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription) {
        Class rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanDescription);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : JsonNodeDeserializer.getDeserializer(rawClass);
    }

    protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        for (Deserializers findTreeNodeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer2 = findTreeNodeDeserializer.findTreeNodeDeserializer(cls, deserializationConfig, beanDescription);
            if (findTreeNodeDeserializer2 != null) {
                return findTreeNodeDeserializer2;
            }
        }
        return null;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        Collection collection = null;
        AnnotatedClass classInfo = deserializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        }
        collection = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, deserializationConfig, annotationIntrospector);
        if (findTypeResolver.getDefaultImpl() == null && javaType.isAbstract()) {
            JavaType mapAbstractType = mapAbstractType(deserializationConfig, javaType);
            if (!(mapAbstractType == null || mapAbstractType.getRawClass() == javaType.getRawClass())) {
                findTypeResolver = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
            }
        }
        return findTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, collection);
    }

    public KeyDeserializer createKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) {
        DeserializationConfig config = deserializationContext.getConfig();
        KeyDeserializer keyDeserializer = null;
        if (this._factoryConfig.hasKeyDeserializers()) {
            BeanDescription introspectClassAnnotations = config.introspectClassAnnotations(javaType.getRawClass());
            for (KeyDeserializers findKeyDeserializer : this._factoryConfig.keyDeserializers()) {
                keyDeserializer = findKeyDeserializer.findKeyDeserializer(javaType, config, introspectClassAnnotations);
                if (keyDeserializer != null) {
                    break;
                }
            }
        }
        if (keyDeserializer == null) {
            if (javaType.isEnumType()) {
                return _createEnumKeyDeserializer(deserializationContext, javaType);
            }
            keyDeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(config, javaType);
        }
        if (keyDeserializer == null || !this._factoryConfig.hasDeserializerModifiers()) {
            return keyDeserializer;
        }
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        for (BeanDeserializerModifier modifyKeyDeserializer : this._factoryConfig.deserializerModifiers()) {
            keyDeserializer2 = modifyKeyDeserializer.modifyKeyDeserializer(config, javaType, keyDeserializer2);
        }
        return keyDeserializer2;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) {
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDescription introspect = config.introspect(javaType);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, findDeserializerFromAnnotation);
        }
        Class rawClass = javaType.getRawClass();
        JsonDeserializer _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, introspect);
        if (_findCustomEnumDeserializer != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, _findCustomEnumDeserializer);
        }
        EnumResolver constructEnumResolver = constructEnumResolver(rawClass, config, introspect.findJsonValueMethod());
        for (AnnotatedMethod annotatedMethod : introspect.getFactoryMethods()) {
            if (config.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() != 1 || !annotatedMethod.getRawReturnType().isAssignableFrom(rawClass)) {
                    throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
                } else if (annotatedMethod.getGenericParameterType(0) != String.class) {
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String");
                } else {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, annotatedMethod);
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector, javaType));
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector, contentType));
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        Class rawClass = javaType.getRawClass();
        if (rawClass == CLASS_OBJECT) {
            return new UntypedObjectDeserializer();
        }
        if (rawClass == CLASS_STRING || rawClass == CLASS_CHAR_BUFFER) {
            return StringDeserializer.instance;
        }
        if (rawClass == CLASS_ITERABLE) {
            return createCollectionDeserializer(deserializationContext, deserializationContext.getTypeFactory().constructCollectionType(Collection.class, javaType.containedTypeCount() > 0 ? javaType.containedType(0) : TypeFactory.unknownType()), beanDescription);
        }
        String name = rawClass.getName();
        if (rawClass.isPrimitive() || name.startsWith("java.")) {
            JsonDeserializer<?> find = NumberDeserializers.find(rawClass, name);
            if (find == null) {
                find = DateDeserializers.find(rawClass, name);
            }
            if (find != null) {
                return find;
            }
        }
        if (rawClass == TokenBuffer.class) {
            return new TokenBufferDeserializer();
        }
        return JdkDeserializers.find(rawClass, name);
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) {
        Object findDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer == null) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, findDeserializer);
    }

    protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, t);
        if (findDeserializationType != null) {
            try {
                T narrowBy = t.narrowBy(findDeserializationType);
            } catch (Throwable e) {
                throw new JsonMappingException("Failed to narrow type " + t + " with concrete-type annotation (value " + findDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        }
        narrowBy = t;
        if (!narrowBy.isContainerType()) {
            return narrowBy;
        }
        T t2;
        Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, narrowBy.getKeyType());
        if (findDeserializationKeyType == null) {
            t2 = narrowBy;
        } else if (narrowBy instanceof MapLikeType) {
            try {
                t2 = ((MapLikeType) narrowBy).narrowKey(findDeserializationKeyType);
            } catch (Throwable e2) {
                throw new JsonMappingException("Failed to narrow key type " + narrowBy + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
            }
        } else {
            throw new JsonMappingException("Illegal key-type annotation: type " + narrowBy + " is not a Map(-like) type");
        }
        JavaType keyType = t2.getKeyType();
        if (keyType != null && keyType.getValueHandler() == null) {
            KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, annotationIntrospector.findKeyDeserializer(annotated));
            if (keyDeserializerInstance != null) {
                t2 = ((MapLikeType) t2).withKeyValueHandler(keyDeserializerInstance);
                t2.getKeyType();
            }
        }
        Class findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, t2.getContentType());
        if (findDeserializationContentType != null) {
            try {
                t2 = t2.narrowContentsBy(findDeserializationContentType);
            } catch (Throwable e3) {
                throw new JsonMappingException("Failed to narrow content type " + t2 + " with content-type annotation (" + findDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
            }
        }
        if (t2.getContentType().getValueHandler() != null) {
            return t2;
        }
        JsonDeserializer deserializerInstance = deserializationContext.deserializerInstance(annotated, annotationIntrospector.findContentDeserializer(annotated));
        if (deserializerInstance != null) {
            return t2.withContentValueHandler(deserializerInstance);
        }
        return t2;
    }

    protected JavaType resolveType(DeserializationContext deserializationContext, BeanDescription beanDescription, JavaType javaType, AnnotatedMember annotatedMember) {
        Object findPropertyTypeDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
            if (javaType.getKeyType() != null) {
                KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotatedMember, annotationIntrospector.findKeyDeserializer(annotatedMember));
                if (keyDeserializerInstance != null) {
                    javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerInstance);
                    javaType.getKeyType();
                }
            }
            JsonDeserializer deserializerInstance = deserializationContext.deserializerInstance(annotatedMember, annotationIntrospector.findContentDeserializer(annotatedMember));
            if (deserializerInstance != null) {
                javaType = javaType.withContentValueHandler(deserializerInstance);
            }
            if (annotatedMember instanceof AnnotatedMember) {
                TypeDeserializer findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
                if (findPropertyContentTypeDeserializer != null) {
                    javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
                }
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            findPropertyTypeDeserializer = findPropertyTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
        } else {
            findPropertyTypeDeserializer = findTypeDeserializer(deserializationContext.getConfig(), javaType);
        }
        if (findPropertyTypeDeserializer != null) {
            return javaType.withTypeHandler(findPropertyTypeDeserializer);
        }
        return javaType;
    }

    protected EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig, AnnotatedMethod annotatedMethod) {
        if (annotatedMethod != null) {
            Object annotated = annotatedMethod.getAnnotated();
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotated);
            }
            return EnumResolver.constructUnsafeUsingMethod(cls, annotated);
        } else if (deserializationConfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
            return EnumResolver.constructUnsafeUsingToString(cls);
        } else {
            return EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
        }
    }
}
