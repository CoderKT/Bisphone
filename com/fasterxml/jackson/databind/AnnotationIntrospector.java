package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

public abstract class AnnotationIntrospector implements Serializable {

    public class ReferenceProperty {
        private final String _name;
        private final Type _type;

        public enum Type {
            MANAGED_REFERENCE,
            BACK_REFERENCE
        }

        public ReferenceProperty(Type type, String str) {
            this._type = type;
            this._name = str;
        }

        public static ReferenceProperty managed(String str) {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, str);
        }

        public static ReferenceProperty back(String str) {
            return new ReferenceProperty(Type.BACK_REFERENCE, str);
        }

        public String getName() {
            return this._name;
        }

        public boolean isManagedReference() {
            return this._type == Type.MANAGED_REFERENCE;
        }

        public boolean isBackReference() {
            return this._type == Type.BACK_REFERENCE;
        }
    }

    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return false;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        return null;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        return objectIdInfo;
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        return null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        return null;
    }

    public Object findFilterId(Annotated annotated) {
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        return null;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return visibilityChecker;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        return null;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return false;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        return null;
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        return null;
    }

    public Class<?>[] findViews(Annotated annotated) {
        return null;
    }

    public Value findFormat(Annotated annotated) {
        return null;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        return null;
    }

    public PropertyName findWrapperName(Annotated annotated) {
        return null;
    }

    public String findPropertyDescription(Annotated annotated) {
        return null;
    }

    public Integer findPropertyIndex(Annotated annotated) {
        return null;
    }

    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findSerializer(Annotated annotated) {
        return null;
    }

    public Object findKeySerializer(Annotated annotated) {
        return null;
    }

    public Object findContentSerializer(Annotated annotated) {
        return null;
    }

    public Object findNullSerializer(Annotated annotated) {
        return null;
    }

    public Include findSerializationInclusion(Annotated annotated, Include include) {
        return include;
    }

    public Class<?> findSerializationType(Annotated annotated) {
        return null;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    public Object findSerializationConverter(Annotated annotated) {
        return null;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        return null;
    }

    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        return null;
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        return null;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public String findEnumValue(Enum<?> enumR) {
        return enumR.name();
    }

    public Object findDeserializer(Annotated annotated) {
        return null;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        return null;
    }

    public Object findContentDeserializer(Annotated annotated) {
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        return null;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        return null;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        return null;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        return null;
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        return null;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return false;
    }
}
