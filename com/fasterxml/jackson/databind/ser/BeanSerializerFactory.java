package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BeanSerializerFactory extends BasicSerializerFactory implements Serializable {
    public static final BeanSerializerFactory instance;

    static {
        instance = new BeanSerializerFactory(null);
    }

    protected BeanSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        super(serializerFactoryConfig);
    }

    protected Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    public JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) {
        SerializationConfig config = serializerProvider.getConfig();
        BeanDescription introspect = config.introspect(javaType);
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializerProvider, introspect.getClassInfo());
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        boolean z;
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(config, introspect.getClassInfo(), javaType);
        if (modifyTypeByAnnotation == javaType) {
            z = false;
        } else if (modifyTypeByAnnotation.hasRawClass(javaType.getRawClass())) {
            z = true;
        } else {
            introspect = config.introspect(modifyTypeByAnnotation);
            z = true;
        }
        Converter findSerializationConverter = introspect.findSerializationConverter();
        if (findSerializationConverter == null) {
            return _createSerializer2(serializerProvider, modifyTypeByAnnotation, introspect, z);
        }
        JsonSerializer findSerializerFromAnnotation2;
        JavaType outputType = findSerializationConverter.getOutputType(serializerProvider.getTypeFactory());
        if (outputType.hasRawClass(modifyTypeByAnnotation.getRawClass())) {
            JsonSerializer<Object> jsonSerializer = findSerializerFromAnnotation;
        } else {
            introspect = config.introspect(outputType);
            findSerializerFromAnnotation2 = findSerializerFromAnnotation(serializerProvider, introspect.getClassInfo());
        }
        if (findSerializerFromAnnotation2 == null) {
            findSerializerFromAnnotation2 = _createSerializer2(serializerProvider, outputType, introspect, true);
        }
        return new StdDelegatingSerializer(findSerializationConverter, outputType, findSerializerFromAnnotation2);
    }

    protected JsonSerializer<?> _createSerializer2(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) {
        JsonSerializer<?> findSerializerByAnnotations = findSerializerByAnnotations(serializerProvider, javaType, beanDescription);
        if (findSerializerByAnnotations != null) {
            return findSerializerByAnnotations;
        }
        SerializationConfig config = serializerProvider.getConfig();
        if (!javaType.isContainerType()) {
            for (Serializers findSerializer : customSerializers()) {
                findSerializerByAnnotations = findSerializer.findSerializer(config, javaType, beanDescription);
                if (findSerializerByAnnotations != null) {
                    break;
                }
            }
        }
        if (!z) {
            z = usesStaticTyping(config, beanDescription, null);
        }
        findSerializerByAnnotations = buildContainerSerializer(serializerProvider, javaType, beanDescription, z);
        if (findSerializerByAnnotations != null) {
            return findSerializerByAnnotations;
        }
        if (findSerializerByAnnotations == null) {
            findSerializerByAnnotations = findSerializerByLookup(javaType, config, beanDescription, z);
            if (findSerializerByAnnotations == null) {
                findSerializerByAnnotations = findSerializerByPrimaryType(serializerProvider, javaType, beanDescription, z);
                if (findSerializerByAnnotations == null) {
                    findSerializerByAnnotations = findBeanSerializer(serializerProvider, javaType, beanDescription);
                    if (findSerializerByAnnotations == null) {
                        findSerializerByAnnotations = findSerializerByAddonType(config, javaType, beanDescription, z);
                    }
                }
            }
        }
        if (findSerializerByAnnotations == null || !this._factoryConfig.hasSerializerModifiers()) {
            return findSerializerByAnnotations;
        }
        JsonSerializer<?> jsonSerializer = findSerializerByAnnotations;
        for (BeanSerializerModifier modifySerializer : this._factoryConfig.serializerModifiers()) {
            jsonSerializer = modifySerializer.modifySerializer(config, beanDescription, jsonSerializer);
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> findBeanSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) {
        if (isPotentialBeanType(javaType.getRawClass()) || javaType.isEnumType()) {
            return constructBeanSerializer(serializerProvider, beanDescription);
        }
        return null;
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return createTypeSerializer(serializationConfig, javaType);
        }
        return findPropertyTypeResolver.buildTypeSerializer(serializationConfig, javaType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector, javaType));
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember) {
        JavaType contentType = javaType.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig, annotatedMember, javaType);
        if (findPropertyContentTypeResolver == null) {
            return createTypeSerializer(serializationConfig, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeSerializer(serializationConfig, contentType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector, contentType));
    }

    protected JsonSerializer<Object> constructBeanSerializer(SerializerProvider serializerProvider, BeanDescription beanDescription) {
        if (beanDescription.getBeanClass() == Object.class) {
            return serializerProvider.getUnknownTypeSerializer(Object.class);
        }
        List list;
        BeanSerializerBuilder beanSerializerBuilder;
        SerializationConfig config = serializerProvider.getConfig();
        BeanSerializerBuilder constructBeanSerializerBuilder = constructBeanSerializerBuilder(beanDescription);
        constructBeanSerializerBuilder.setConfig(config);
        List findBeanProperties = findBeanProperties(serializerProvider, beanDescription, constructBeanSerializerBuilder);
        if (findBeanProperties == null) {
            findBeanProperties = new ArrayList();
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            list = findBeanProperties;
            for (BeanSerializerModifier changeProperties : this._factoryConfig.serializerModifiers()) {
                list = changeProperties.changeProperties(config, beanDescription, list);
            }
        } else {
            list = findBeanProperties;
        }
        findBeanProperties = filterBeanProperties(config, beanDescription, list);
        if (this._factoryConfig.hasSerializerModifiers()) {
            list = findBeanProperties;
            for (BeanSerializerModifier changeProperties2 : this._factoryConfig.serializerModifiers()) {
                list = changeProperties2.orderProperties(config, beanDescription, list);
            }
        } else {
            list = findBeanProperties;
        }
        constructBeanSerializerBuilder.setObjectIdWriter(constructObjectIdHandler(serializerProvider, beanDescription, list));
        constructBeanSerializerBuilder.setProperties(list);
        constructBeanSerializerBuilder.setFilterId(findFilterId(config, beanDescription));
        AnnotatedMember findAnyGetter = beanDescription.findAnyGetter();
        if (findAnyGetter != null) {
            if (config.canOverrideAccessModifiers()) {
                findAnyGetter.fixAccess();
            }
            JavaType type = findAnyGetter.getType(beanDescription.bindingsForBeanType());
            boolean isEnabled = config.isEnabled(MapperFeature.USE_STATIC_TYPING);
            JavaType contentType = type.getContentType();
            constructBeanSerializerBuilder.setAnyGetter(new AnyGetterWriter(new Std(new PropertyName(findAnyGetter.getName()), contentType, null, beanDescription.getClassAnnotations(), findAnyGetter, PropertyMetadata.STD_OPTIONAL), findAnyGetter, MapSerializer.construct(null, type, isEnabled, createTypeSerializer(config, contentType), null, null, null)));
        }
        processViews(config, constructBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            beanSerializerBuilder = constructBeanSerializerBuilder;
            for (BeanSerializerModifier updateBuilder : this._factoryConfig.serializerModifiers()) {
                beanSerializerBuilder = updateBuilder.updateBuilder(config, beanDescription, beanSerializerBuilder);
            }
        } else {
            beanSerializerBuilder = constructBeanSerializerBuilder;
        }
        JsonSerializer<Object> build = beanSerializerBuilder.build();
        if (build == null && beanDescription.hasKnownClassAnnotations()) {
            return beanSerializerBuilder.createDummy();
        }
        return build;
    }

    protected ObjectIdWriter constructObjectIdHandler(SerializerProvider serializerProvider, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo == null) {
            return null;
        }
        Type generatorType = objectIdInfo.getGeneratorType();
        if (generatorType == PropertyGenerator.class) {
            String simpleName = objectIdInfo.getPropertyName().getSimpleName();
            int size = list.size();
            for (int i = 0; i != size; i++) {
                BeanPropertyWriter beanPropertyWriter = (BeanPropertyWriter) list.get(i);
                if (simpleName.equals(beanPropertyWriter.getName())) {
                    if (i > 0) {
                        list.remove(i);
                        list.add(0, beanPropertyWriter);
                    }
                    return ObjectIdWriter.construct(beanPropertyWriter.getType(), (PropertyName) null, new PropertyBasedObjectIdGenerator(objectIdInfo, beanPropertyWriter), objectIdInfo.getAlwaysAsId());
                }
            }
            throw new IllegalArgumentException("Invalid Object Id definition for " + beanDescription.getBeanClass().getName() + ": can not find property with name '" + simpleName + "'");
        }
        return ObjectIdWriter.construct(serializerProvider.getTypeFactory().findTypeParameters(serializerProvider.constructType(generatorType), ObjectIdGenerator.class)[0], objectIdInfo.getPropertyName(), serializerProvider.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo), objectIdInfo.getAlwaysAsId());
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        return new PropertyBuilder(serializationConfig, beanDescription);
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription beanDescription) {
        return new BeanSerializerBuilder(beanDescription);
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        return ClassUtil.canBeABeanType(cls) == null && !ClassUtil.isProxyType(cls);
    }

    protected List<BeanPropertyWriter> findBeanProperties(SerializerProvider serializerProvider, BeanDescription beanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        List<BeanPropertyDefinition> findProperties = beanDescription.findProperties();
        SerializationConfig config = serializerProvider.getConfig();
        removeIgnorableTypes(config, beanDescription, findProperties);
        if (config.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS)) {
            removeSetterlessGetters(config, beanDescription, findProperties);
        }
        if (findProperties.isEmpty()) {
            return null;
        }
        boolean usesStaticTyping = usesStaticTyping(config, beanDescription, null);
        PropertyBuilder constructPropertyBuilder = constructPropertyBuilder(config, beanDescription);
        ArrayList arrayList = new ArrayList(findProperties.size());
        TypeBindings bindingsForBeanType = beanDescription.bindingsForBeanType();
        for (BeanPropertyDefinition beanPropertyDefinition : findProperties) {
            AnnotatedMember accessor = beanPropertyDefinition.getAccessor();
            if (!beanPropertyDefinition.isTypeId()) {
                ReferenceProperty findReferenceType = beanPropertyDefinition.findReferenceType();
                if (findReferenceType == null || !findReferenceType.isBackReference()) {
                    if (accessor instanceof AnnotatedMethod) {
                        arrayList.add(_constructWriter(serializerProvider, beanPropertyDefinition, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (AnnotatedMethod) accessor));
                    } else {
                        arrayList.add(_constructWriter(serializerProvider, beanPropertyDefinition, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (AnnotatedField) accessor));
                    }
                }
            } else if (accessor != null) {
                if (config.canOverrideAccessModifiers()) {
                    accessor.fixAccess();
                }
                beanSerializerBuilder.setTypeId(accessor);
            }
        }
        return arrayList;
    }

    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        String[] findPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(beanDescription.getClassInfo());
        if (findPropertiesToIgnore != null && findPropertiesToIgnore.length > 0) {
            HashSet arrayToSet = ArrayBuilders.arrayToSet(findPropertiesToIgnore);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (arrayToSet.contains(((BeanPropertyWriter) it.next()).getName())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    protected void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        List properties = beanSerializerBuilder.getProperties();
        boolean isEnabled = serializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            BeanPropertyWriter beanPropertyWriter = (BeanPropertyWriter) properties.get(i);
            Class[] views = beanPropertyWriter.getViews();
            if (views == null) {
                if (isEnabled) {
                    beanPropertyWriterArr[i] = beanPropertyWriter;
                    i3 = i2;
                }
                i3 = i2;
            } else {
                i2++;
                beanPropertyWriterArr[i] = constructFilteredBeanWriter(beanPropertyWriter, views);
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (!isEnabled || i2 != 0) {
            beanSerializerBuilder.setFilteredProperties(beanPropertyWriterArr);
        }
    }

    protected void removeIgnorableTypes(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AnnotatedMember accessor = ((BeanPropertyDefinition) it.next()).getAccessor();
            if (accessor == null) {
                it.remove();
            } else {
                Class rawType = accessor.getRawType();
                Boolean bool = (Boolean) hashMap.get(rawType);
                if (bool == null) {
                    bool = annotationIntrospector.isIgnorableType(serializationConfig.introspectClassAnnotations(rawType).getClassInfo());
                    if (bool == null) {
                        bool = Boolean.FALSE;
                    }
                    hashMap.put(rawType, bool);
                }
                if (bool.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    protected void removeSetterlessGetters(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BeanPropertyDefinition beanPropertyDefinition = (BeanPropertyDefinition) it.next();
            if (!(beanPropertyDefinition.couldDeserialize() || beanPropertyDefinition.isExplicitlyIncluded())) {
                it.remove();
            }
        }
    }

    protected BeanPropertyWriter _constructWriter(SerializerProvider serializerProvider, BeanPropertyDefinition beanPropertyDefinition, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, AnnotatedMember annotatedMember) {
        PropertyName fullName = beanPropertyDefinition.getFullName();
        if (serializerProvider.canOverrideAccessModifiers()) {
            annotatedMember.fixAccess();
        }
        JavaType type = annotatedMember.getType(typeBindings);
        BeanProperty std = new Std(fullName, type, beanPropertyDefinition.getWrapperName(), propertyBuilder.getClassAnnotations(), annotatedMember, beanPropertyDefinition.getMetadata());
        JsonSerializer findSerializerFromAnnotation = findSerializerFromAnnotation(serializerProvider, annotatedMember);
        if (findSerializerFromAnnotation instanceof ResolvableSerializer) {
            ((ResolvableSerializer) findSerializerFromAnnotation).resolve(serializerProvider);
        }
        JsonSerializer handlePrimaryContextualization = serializerProvider.handlePrimaryContextualization(findSerializerFromAnnotation, std);
        TypeSerializer typeSerializer = null;
        if (ClassUtil.isCollectionMapOrArray(type.getRawClass()) || type.isCollectionLikeType() || type.isMapLikeType()) {
            typeSerializer = findPropertyContentTypeSerializer(type, serializerProvider.getConfig(), annotatedMember);
        }
        return propertyBuilder.buildWriter(serializerProvider, beanPropertyDefinition, type, handlePrimaryContextualization, findPropertyTypeSerializer(type, serializerProvider.getConfig(), annotatedMember), typeSerializer, annotatedMember, z);
    }
}
