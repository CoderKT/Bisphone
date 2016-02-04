package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class JacksonAnnotationIntrospector extends AnnotationIntrospector implements Serializable {

    /* renamed from: com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector.1 */
    /* synthetic */ class C06271 {
        static final /* synthetic */ int[] f5624xfdbfedae;

        static {
            f5624xfdbfedae = new int[Inclusion.values().length];
            try {
                f5624xfdbfedae[Inclusion.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5624xfdbfedae[Inclusion.NON_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5624xfdbfedae[Inclusion.NON_DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5624xfdbfedae[Inclusion.NON_EMPTY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f5624xfdbfedae[Inclusion.DEFAULT_INCLUSION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null;
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        String str = null;
        JsonRootName jsonRootName = (JsonRootName) annotatedClass.getAnnotation(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        String namespace = jsonRootName.namespace();
        if (namespace == null || namespace.length() != 0) {
            str = namespace;
        }
        return PropertyName.construct(jsonRootName.value(), str);
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotated.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : jsonIgnoreProperties.value();
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        return jsonIgnoreProperties == null ? null : Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) annotatedClass.getAnnotation(JsonIgnoreType.class);
        return jsonIgnoreType == null ? null : Boolean.valueOf(jsonIgnoreType.value());
    }

    public Object findFilterId(Annotated annotated) {
        return _findFilterId(annotated);
    }

    protected final Object _findFilterId(Annotated annotated) {
        JsonFilter jsonFilter = (JsonFilter) annotated.getAnnotation(JsonFilter.class);
        if (jsonFilter != null) {
            String value = jsonFilter.value();
            if (value.length() > 0) {
                return value;
            }
        }
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        JsonNaming jsonNaming = (JsonNaming) annotatedClass.getAnnotation(JsonNaming.class);
        return jsonNaming == null ? null : jsonNaming.value();
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) annotatedClass.getAnnotation(JsonAutoDetect.class);
        return jsonAutoDetect == null ? visibilityChecker : visibilityChecker.with(jsonAutoDetect);
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        JsonManagedReference jsonManagedReference = (JsonManagedReference) annotatedMember.getAnnotation(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            return ReferenceProperty.managed(jsonManagedReference.value());
        }
        JsonBackReference jsonBackReference = (JsonBackReference) annotatedMember.getAnnotation(JsonBackReference.class);
        if (jsonBackReference != null) {
            return ReferenceProperty.back(jsonBackReference.value());
        }
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        JsonUnwrapped jsonUnwrapped = (JsonUnwrapped) annotatedMember.getAnnotation(JsonUnwrapped.class);
        if (jsonUnwrapped == null || !jsonUnwrapped.enabled()) {
            return null;
        }
        return NameTransformer.simpleTransformer(jsonUnwrapped.prefix(), jsonUnwrapped.suffix());
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return _isIgnorable(annotatedMember);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMember.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        JacksonInject jacksonInject = (JacksonInject) annotatedMember.getAnnotation(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        Object value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (!(annotatedMember instanceof AnnotatedMethod)) {
            return annotatedMember.getRawType().getName();
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
        if (annotatedMethod.getParameterCount() == 0) {
            return annotatedMember.getRawType().getName();
        }
        return annotatedMethod.getRawParameterType(0).getName();
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return _findTypeResolver(mapperConfig, annotatedClass, javaType);
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return null;
        }
        return _findTypeResolver(mapperConfig, annotatedMember, javaType);
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return _findTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + javaType + ")");
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) annotated.getAnnotation(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        Type[] value = jsonSubTypes.value();
        List<NamedType> arrayList = new ArrayList(value.length);
        for (Type type : value) {
            arrayList.add(new NamedType(type.value(), type.name()));
        }
        return arrayList;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        JsonTypeName jsonTypeName = (JsonTypeName) annotatedClass.getAnnotation(JsonTypeName.class);
        return jsonTypeName == null ? null : jsonTypeName.value();
    }

    public Object findSerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class using = jsonSerialize.using();
            if (using != None.class) {
                return using;
            }
        }
        JsonRawValue jsonRawValue = (JsonRawValue) annotated.getAnnotation(JsonRawValue.class);
        if (jsonRawValue == null || !jsonRawValue.value()) {
            return null;
        }
        return new RawSerializer(annotated.getRawType());
    }

    public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<? extends JsonSerializer<?>> keyUsing = jsonSerialize.keyUsing();
            if (keyUsing != None.class) {
                return keyUsing;
            }
        }
        return null;
    }

    public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class<? extends JsonSerializer<?>> contentUsing = jsonSerialize.contentUsing();
            if (contentUsing != None.class) {
                return contentUsing;
            }
        }
        return null;
    }

    public Object findNullSerializer(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            Class nullsUsing = jsonSerialize.nullsUsing();
            if (nullsUsing != None.class) {
                return nullsUsing;
            }
        }
        return null;
    }

    public Include findSerializationInclusion(Annotated annotated, Include include) {
        JsonInclude jsonInclude = (JsonInclude) annotated.getAnnotation(JsonInclude.class);
        if (jsonInclude != null) {
            return jsonInclude.value();
        }
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null) {
            return include;
        }
        switch (C06271.f5624xfdbfedae[jsonSerialize.include().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return Include.ALWAYS;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return Include.NON_NULL;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return Include.NON_DEFAULT;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return Include.NON_EMPTY;
            default:
                return include;
        }
    }

    public Class<?> findSerializationType(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : _classIfExplicit(jsonSerialize.as());
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : _classIfExplicit(jsonSerialize.keyAs());
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : _classIfExplicit(jsonSerialize.contentAs());
    }

    public Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : jsonSerialize.typing();
    }

    public Object findSerializationConverter(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : _classIfExplicit(jsonSerialize.converter(), Converter.None.class);
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotatedMember.getAnnotation(JsonSerialize.class);
        return jsonSerialize == null ? null : _classIfExplicit(jsonSerialize.contentConverter(), Converter.None.class);
    }

    public Class<?>[] findViews(Annotated annotated) {
        JsonView jsonView = (JsonView) annotated.getAnnotation(JsonView.class);
        return jsonView == null ? null : jsonView.value();
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        return Boolean.valueOf(annotatedMember.hasAnnotation(JsonTypeId.class));
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) annotated.getAnnotation(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == ObjectIdGenerators.None.class) {
            return null;
        }
        return new ObjectIdInfo(new PropertyName(jsonIdentityInfo.property()), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), jsonIdentityInfo.resolver());
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) annotated.getAnnotation(JsonIdentityReference.class);
        if (jsonIdentityReference != null) {
            return objectIdInfo.withAlwaysAsId(jsonIdentityReference.alwaysAsId());
        }
        return objectIdInfo;
    }

    public Value findFormat(Annotated annotated) {
        JsonFormat jsonFormat = (JsonFormat) annotated.getAnnotation(JsonFormat.class);
        return jsonFormat == null ? null : new Value(jsonFormat);
    }

    public String findPropertyDescription(Annotated annotated) {
        JsonPropertyDescription jsonPropertyDescription = (JsonPropertyDescription) annotated.getAnnotation(JsonPropertyDescription.class);
        return jsonPropertyDescription == null ? null : jsonPropertyDescription.value();
    }

    public Integer findPropertyIndex(Annotated annotated) {
        JsonProperty jsonProperty = (JsonProperty) annotated.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            int index = jsonProperty.index();
            if (index != -1) {
                return Integer.valueOf(index);
            }
        }
        return null;
    }

    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : jsonPropertyOrder.value();
    }

    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        return _findSortAlpha(annotated);
    }

    private final Boolean _findSortAlpha(Annotated annotated) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotated.getAnnotation(JsonPropertyOrder.class);
        return jsonPropertyOrder == null ? null : Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        String value;
        JsonGetter jsonGetter = (JsonGetter) annotated.getAnnotation(JsonGetter.class);
        if (jsonGetter != null) {
            value = jsonGetter.value();
        } else {
            JsonProperty jsonProperty = (JsonProperty) annotated.getAnnotation(JsonProperty.class);
            if (jsonProperty != null) {
                value = jsonProperty.value();
            } else if (!annotated.hasAnnotation(JsonSerialize.class) && !annotated.hasAnnotation(JsonView.class)) {
                return null;
            } else {
                value = "";
            }
        }
        if (value.length() == 0) {
            return PropertyName.USE_DEFAULT;
        }
        return new PropertyName(value);
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        JsonValue jsonValue = (JsonValue) annotatedMethod.getAnnotation(JsonValue.class);
        return jsonValue != null && jsonValue.value();
    }

    public Class<? extends JsonDeserializer<?>> findDeserializer(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends JsonDeserializer<?>> using = jsonDeserialize.using();
            if (using != JsonDeserializer.None.class) {
                return using;
            }
        }
        return null;
    }

    public Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends KeyDeserializer> keyUsing = jsonDeserialize.keyUsing();
            if (keyUsing != KeyDeserializer.None.class) {
                return keyUsing;
            }
        }
        return null;
    }

    public Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize != null) {
            Class<? extends JsonDeserializer<?>> contentUsing = jsonDeserialize.contentUsing();
            if (contentUsing != JsonDeserializer.None.class) {
                return contentUsing;
            }
        }
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.as());
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.keyAs());
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.contentAs());
    }

    public Object findDeserializationConverter(Annotated annotated) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.converter(), Converter.None.class);
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotatedMember.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.contentConverter(), Converter.None.class);
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) annotatedClass.getAnnotation(JsonValueInstantiator.class);
        return jsonValueInstantiator == null ? null : jsonValueInstantiator.value();
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotatedClass.getAnnotation(JsonDeserialize.class);
        return jsonDeserialize == null ? null : _classIfExplicit(jsonDeserialize.builder());
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) annotatedClass.getAnnotation(JsonPOJOBuilder.class);
        return jsonPOJOBuilder == null ? null : new JsonPOJOBuilder.Value(jsonPOJOBuilder);
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        String value;
        JsonSetter jsonSetter = (JsonSetter) annotated.getAnnotation(JsonSetter.class);
        if (jsonSetter != null) {
            value = jsonSetter.value();
        } else {
            JsonProperty jsonProperty = (JsonProperty) annotated.getAnnotation(JsonProperty.class);
            if (jsonProperty != null) {
                value = jsonProperty.value();
            } else if (!annotated.hasAnnotation(JsonDeserialize.class) && !annotated.hasAnnotation(JsonView.class) && !annotated.hasAnnotation(JsonUnwrapped.class) && !annotated.hasAnnotation(JsonBackReference.class) && !annotated.hasAnnotation(JsonManagedReference.class)) {
                return null;
            } else {
                value = "";
            }
        }
        if (value.length() == 0) {
            return PropertyName.USE_DEFAULT;
        }
        return new PropertyName(value);
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnySetter.class);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnyGetter.class);
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return annotated.hasAnnotation(JsonCreator.class);
    }

    protected boolean _isIgnorable(Annotated annotated) {
        JsonIgnore jsonIgnore = (JsonIgnore) annotated.getAnnotation(JsonIgnore.class);
        return jsonIgnore != null && jsonIgnore.value();
    }

    protected Class<?> _classIfExplicit(Class<?> cls) {
        if (cls == null || ClassUtil.isBogusClass(cls)) {
            return null;
        }
        return cls;
    }

    protected Class<?> _classIfExplicit(Class<?> cls, Class<?> cls2) {
        Class<?> _classIfExplicit = _classIfExplicit(cls);
        return (_classIfExplicit == null || _classIfExplicit == cls2) ? null : _classIfExplicit;
    }

    protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) {
        TypeResolverBuilder typeResolverBuilderInstance;
        TypeIdResolver typeIdResolver = null;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) annotated.getAnnotation(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) annotated.getAnnotation(JsonTypeResolver.class);
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo == null) {
                return null;
            }
            typeResolverBuilderInstance = mapperConfig.typeResolverBuilderInstance(annotated, jsonTypeResolver.value());
        } else if (jsonTypeInfo == null) {
            return null;
        } else {
            if (jsonTypeInfo.use() == Id.NONE) {
                return _constructNoTypeResolverBuilder();
            }
            Object _constructStdTypeResolverBuilder = _constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) annotated.getAnnotation(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            typeIdResolver = mapperConfig.typeIdResolverInstance(annotated, jsonTypeIdResolver.value());
        }
        if (typeIdResolver != null) {
            typeIdResolver.init(javaType);
        }
        typeResolverBuilderInstance = typeResolverBuilderInstance.init(jsonTypeInfo.use(), typeIdResolver);
        As include = jsonTypeInfo.include();
        if (include == As.EXTERNAL_PROPERTY && (annotated instanceof AnnotatedClass)) {
            include = As.PROPERTY;
        }
        TypeResolverBuilder typeProperty = typeResolverBuilderInstance.inclusion(include).typeProperty(jsonTypeInfo.property());
        Class defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != JsonTypeInfo.None.class) {
            typeProperty = typeProperty.defaultImpl(defaultImpl);
        }
        return typeProperty.typeIdVisibility(jsonTypeInfo.visible());
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }
}
