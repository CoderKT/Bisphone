package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.jivesoftware.smack.packet.Message;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS;
    private static final Class<?>[] NO_VIEWS;
    public static final BeanDeserializerFactory instance;

    static {
        INIT_CAUSE_PARAMS = new Class[]{Throwable.class};
        NO_VIEWS = new Class[0];
        instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    }

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        for (Deserializers findBeanDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<Object> findBeanDeserializer2 = findBeanDeserializer.findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            if (findBeanDeserializer2 != null) {
                return findBeanDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<Object> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract()) {
            JavaType materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription);
            if (materializeAbstractType != null) {
                return buildBeanDeserializer(deserializationContext, materializeAbstractType, config.introspect(materializeAbstractType));
            }
        }
        _findCustomBeanDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (isPotentialBeanType(javaType.getRawClass())) {
            return buildBeanDeserializer(deserializationContext, javaType, beanDescription);
        }
        return null;
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.constructType(cls)));
    }

    protected JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null) {
            return findDefaultDeserializer;
        }
        if (!AtomicReference.class.isAssignableFrom(javaType.getRawClass())) {
            return findOptionalStdDeserializer(deserializationContext, javaType, beanDescription);
        }
        JavaType unknownType;
        JavaType[] findTypeParameters = deserializationContext.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
        if (findTypeParameters == null || findTypeParameters.length < 1) {
            unknownType = TypeFactory.unknownType();
        } else {
            unknownType = findTypeParameters[0];
        }
        return new AtomicReferenceDeserializer(unknownType, findTypeDeserializer(deserializationContext.getConfig(), unknownType), findDeserializerFromAnnotation(deserializationContext, deserializationContext.getConfig().introspectClassAnnotations(unknownType).getClassInfo()));
    }

    protected JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }

    protected JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JavaType type = beanDescription.getType();
        for (AbstractTypeResolver resolveAbstractType : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType2 = resolveAbstractType.resolveAbstractType(deserializationContext.getConfig(), type);
            if (resolveAbstractType2 != null) {
                return resolveAbstractType2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        JsonDeserializer<Object> build;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        DeserializationConfig config = deserializationContext.getConfig();
        if (this._factoryConfig.hasDeserializerModifiers()) {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                beanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
        }
        if (!javaType.isAbstract() || findValueInstantiator.canInstantiate()) {
            build = beanDeserializerBuilder.build();
        } else {
            build = beanDeserializerBuilder.buildAbstract();
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return build;
        }
        JsonDeserializer<Object> jsonDeserializer = build;
        for (BeanDeserializerModifier updateBuilder2 : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer = updateBuilder2.modifyDeserializer(config, beanDescription, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
        String str = findPOJOBuilderConfig == null ? "build" : findPOJOBuilderConfig.buildMethodName;
        AnnotatedMethod findMethod = beanDescription.findMethod(str, null);
        if (findMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findMethod.getMember());
        }
        constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<Object> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return buildBuilderBased;
        }
        JsonDeserializer<Object> jsonDeserializer = buildBuilderBased;
        for (BeanDeserializerModifier updateBuilder2 : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer = updateBuilder2.modifyDeserializer(config, beanDescription, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo != null) {
            SettableBeanProperty findProperty;
            JavaType type;
            ObjectIdGenerator propertyBasedObjectIdGenerator;
            Class generatorType = objectIdInfo.getGeneratorType();
            ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
            if (generatorType == PropertyGenerator.class) {
                PropertyName propertyName = objectIdInfo.getPropertyName();
                findProperty = beanDeserializerBuilder.findProperty(propertyName);
                if (findProperty == null) {
                    throw new IllegalArgumentException("Invalid Object Id definition for " + beanDescription.getBeanClass().getName() + ": can not find property with name '" + propertyName + "'");
                }
                type = findProperty.getType();
                propertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                type = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), ObjectIdGenerator.class)[0];
                findProperty = null;
                propertyBasedObjectIdGenerator = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
            }
            beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(type, objectIdInfo.getPropertyName(), propertyBasedObjectIdGenerator, deserializationContext.findRootValueDeserializer(type), findProperty, objectIdResolverInstance));
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        AnnotatedMember findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null) {
            SettableBeanProperty constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), findMethod, "cause"), findMethod.getGenericParameterType(0));
            if (constructSettableProperty != null) {
                constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
            }
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        constructBeanDeserializerBuilder.addIgnorable(Message.ELEMENT);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                beanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
        }
        JsonDeserializer<Object> build = beanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = new ThrowableDeserializer((BeanDeserializer) build);
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return build;
        }
        JsonDeserializer<Object> jsonDeserializer = build;
        for (BeanDeserializerModifier updateBuilder2 : this._factoryConfig.deserializerModifiers()) {
            jsonDeserializer = updateBuilder2.modifyDeserializer(config, beanDescription, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext.getConfig());
    }

    protected void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        SettableBeanProperty[] fromObjectArguments = beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.getConfig());
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Boolean findIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(beanDescription.getClassInfo());
        if (findIgnoreUnknownProperties != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(findIgnoreUnknownProperties.booleanValue());
        }
        Set<String> arrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(beanDescription.getClassInfo()));
        for (String addIgnorable : arrayToSet) {
            beanDeserializerBuilder.addIgnorable(addIgnorable);
        }
        AnnotatedMethod findAnySetter = beanDescription.findAnySetter();
        if (findAnySetter != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationContext, beanDescription, findAnySetter));
        }
        if (findAnySetter == null) {
            Collection<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (ignoredPropertyNames != null) {
                for (String addIgnorable2 : ignoredPropertyNames) {
                    beanDeserializerBuilder.addIgnorable(addIgnorable2);
                }
            }
        }
        int i = (deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) ? 1 : 0;
        List filterBeanProps = filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), arrayToSet);
        List list;
        if (this._factoryConfig.hasDeserializerModifiers()) {
            list = filterBeanProps;
            for (BeanDeserializerModifier updateProperties : this._factoryConfig.deserializerModifiers()) {
                list = updateProperties.updateProperties(deserializationContext.getConfig(), beanDescription, list);
            }
        } else {
            list = filterBeanProps;
        }
        for (BeanPropertyDefinition beanPropertyDefinition : r1) {
            SettableBeanProperty constructSettableProperty;
            if (beanPropertyDefinition.hasSetter()) {
                constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getGenericParameterType(0));
            } else if (beanPropertyDefinition.hasField()) {
                constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getGenericType());
            } else {
                if (i != 0 && beanPropertyDefinition.hasGetter()) {
                    Class rawType = beanPropertyDefinition.getGetter().getRawType();
                    if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                        constructSettableProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                }
                constructSettableProperty = null;
            }
            if (beanPropertyDefinition.hasConstructorParameter()) {
                SettableBeanProperty settableBeanProperty;
                String name = beanPropertyDefinition.getName();
                if (fromObjectArguments != null) {
                    for (SettableBeanProperty settableBeanProperty2 : fromObjectArguments) {
                        if (name.equals(settableBeanProperty2.getName())) {
                            settableBeanProperty2 = (CreatorProperty) settableBeanProperty2;
                            break;
                        }
                    }
                }
                settableBeanProperty2 = null;
                if (settableBeanProperty2 == null) {
                    throw deserializationContext.mappingException("Could not find creator property with name '" + name + "' (in class " + beanDescription.getBeanClass().getName() + ")");
                }
                if (constructSettableProperty != null) {
                    settableBeanProperty2 = settableBeanProperty2.withFallbackSetter(constructSettableProperty);
                }
                beanDeserializerBuilder.addCreatorProperty(settableBeanProperty2);
            } else if (constructSettableProperty != null) {
                Class[] findViews = beanPropertyDefinition.findViews();
                if (findViews == null && !deserializationContext.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION)) {
                    findViews = NO_VIEWS;
                }
                constructSettableProperty.setViews(findViews);
                beanDeserializerBuilder.addProperty(constructSettableProperty);
            }
        }
    }

    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) {
        List arrayList = new ArrayList(Math.max(4, list.size()));
        Map hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : list) {
            String name = beanPropertyDefinition.getName();
            if (!set.contains(name)) {
                if (!beanPropertyDefinition.hasConstructorParameter()) {
                    Class cls = null;
                    if (beanPropertyDefinition.hasSetter()) {
                        cls = beanPropertyDefinition.getSetter().getRawParameterType(0);
                    } else if (beanPropertyDefinition.hasField()) {
                        cls = beanPropertyDefinition.getField().getRawType();
                    }
                    if (cls != null && isIgnorableType(deserializationContext.getConfig(), beanDescription, cls, hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    }
                }
                arrayList.add(beanPropertyDefinition);
            }
        }
        return arrayList;
    }

    protected void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map findBackReferenceProperties = beanDescription.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Entry entry : findBackReferenceProperties.entrySet()) {
                Type genericParameterType;
                String str = (String) entry.getKey();
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (annotatedMember instanceof AnnotatedMethod) {
                    genericParameterType = ((AnnotatedMethod) annotatedMember).getGenericParameterType(0);
                } else {
                    genericParameterType = annotatedMember.getRawType();
                }
                beanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), annotatedMember), genericParameterType));
            }
        }
    }

    protected void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            boolean canOverrideAccessModifiers = deserializationContext.canOverrideAccessModifiers();
            for (Entry entry : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (canOverrideAccessModifiers) {
                    annotatedMember.fixAccess();
                }
                beanDeserializerBuilder.addInjectable(new PropertyName(annotatedMember.getName()), beanDescription.resolveType(annotatedMember.getGenericType()), beanDescription.getClassAnnotations(), annotatedMember, entry.getKey());
            }
        }
    }

    protected SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMethod annotatedMethod) {
        if (deserializationContext.canOverrideAccessModifiers()) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = beanDescription.bindingsForBeanType().resolveType(annotatedMethod.getGenericParameterType(1));
        Std std = new Std(new PropertyName(annotatedMethod.getName()), resolveType, null, beanDescription.getClassAnnotations(), annotatedMethod, PropertyMetadata.STD_OPTIONAL);
        JavaType resolveType2 = resolveType(deserializationContext, beanDescription, resolveType, annotatedMethod);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, annotatedMethod);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, annotatedMethod, resolveType2);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) modifyTypeByAnnotation.getValueHandler();
        }
        return new SettableAnyProperty((BeanProperty) std, annotatedMethod, modifyTypeByAnnotation, findDeserializerFromAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler());
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, Type type) {
        SettableBeanProperty methodProperty;
        Annotated nonConstructorMutator = beanPropertyDefinition.getNonConstructorMutator();
        if (deserializationContext.canOverrideAccessModifiers()) {
            nonConstructorMutator.fixAccess();
        }
        JavaType resolveType = beanDescription.resolveType(type);
        Std std = new Std(beanPropertyDefinition.getFullName(), resolveType, beanPropertyDefinition.getWrapperName(), beanDescription.getClassAnnotations(), nonConstructorMutator, beanPropertyDefinition.getMetadata());
        JavaType resolveType2 = resolveType(deserializationContext, beanDescription, resolveType, nonConstructorMutator);
        if (resolveType2 != resolveType) {
            std.withType(resolveType2);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, nonConstructorMutator);
        resolveType = modifyTypeByAnnotation(deserializationContext, nonConstructorMutator, resolveType2);
        TypeDeserializer typeDeserializer = (TypeDeserializer) resolveType.getTypeHandler();
        if (nonConstructorMutator instanceof AnnotatedMethod) {
            methodProperty = new MethodProperty(beanPropertyDefinition, resolveType, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedMethod) nonConstructorMutator);
        } else {
            methodProperty = new FieldProperty(beanPropertyDefinition, resolveType, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedField) nonConstructorMutator);
        }
        if (findDeserializerFromAnnotation != null) {
            methodProperty = methodProperty.withValueDeserializer(findDeserializerFromAnnotation);
        }
        ReferenceProperty findReferenceType = beanPropertyDefinition.findReferenceType();
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            methodProperty.setManagedReferenceName(findReferenceType.getName());
        }
        ObjectIdInfo findObjectIdInfo = beanPropertyDefinition.findObjectIdInfo();
        if (findObjectIdInfo != null) {
            methodProperty.setObjectIdInfo(findObjectIdInfo);
        }
        return methodProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        Annotated getter = beanPropertyDefinition.getGetter();
        if (deserializationContext.canOverrideAccessModifiers()) {
            getter.fixAccess();
        }
        JavaType type = getter.getType(beanDescription.bindingsForBeanType());
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        JavaType resolveType = resolveType(deserializationContext, beanDescription, modifyTypeByAnnotation(deserializationContext, getter, type), getter);
        SettableBeanProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, resolveType, (TypeDeserializer) resolveType.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        if (findDeserializerFromAnnotation != null) {
            return setterlessProperty.withValueDeserializer(findDeserializerFromAnnotation);
        }
        return setterlessProperty;
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
        } else if (ClassUtil.isProxyType(cls)) {
            throw new IllegalArgumentException("Can not deserialize Proxy class " + cls.getName() + " as a Bean");
        } else {
            canBeABeanType = ClassUtil.isLocalType(cls, true);
            if (canBeABeanType == null) {
                return true;
            }
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
        }
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanDescription beanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool = (Boolean) map.get(cls);
        if (bool == null) {
            bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations((Class) cls).getClassInfo());
            if (bool == null) {
                bool = Boolean.FALSE;
            }
        }
        return bool.booleanValue();
    }
}
