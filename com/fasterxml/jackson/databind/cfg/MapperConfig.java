package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class MapperConfig<T extends MapperConfig<T>> implements MixInResolver, Serializable {
    protected final BaseSettings _base;
    protected final int _mapperFeatures;

    public abstract BeanDescription introspectClassAnnotations(JavaType javaType);

    protected MapperConfig(BaseSettings baseSettings, int i) {
        this._base = baseSettings;
        this._mapperFeatures = i;
    }

    public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(Class<F> cls) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        int length = enumArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int mask;
            Enum enumR = enumArr[i];
            if (((ConfigFeature) enumR).enabledByDefault()) {
                mask = ((ConfigFeature) enumR).getMask() | i2;
            } else {
                mask = i2;
            }
            i++;
            i2 = mask;
        }
        return i2;
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return (this._mapperFeatures & mapperFeature.getMask()) != 0;
    }

    public final boolean isAnnotationProcessingEnabled() {
        return isEnabled(MapperFeature.USE_ANNOTATIONS);
    }

    public final boolean canOverrideAccessModifiers() {
        return isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public final boolean shouldSortPropertiesAlphabetically() {
        return isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }

    public SerializableString compileString(String str) {
        return new SerializedString(str);
    }

    public ClassIntrospector getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType(cls, (TypeBindings) null);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls);
    }

    public BeanDescription introspectClassAnnotations(Class<?> cls) {
        return introspectClassAnnotations(constructType(cls));
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public final Locale getLocale() {
        return this._base.getLocale();
    }

    public final TimeZone getTimeZone() {
        return this._base.getTimeZone();
    }

    public Base64Variant getBase64Variant() {
        return this._base.getBase64Variant();
    }

    public TypeResolverBuilder<?> typeResolverBuilderInstance(Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls) {
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        if (handlerInstantiator != null) {
            TypeResolverBuilder<?> typeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls);
            if (typeResolverBuilderInstance != null) {
                return typeResolverBuilderInstance;
            }
        }
        return (TypeResolverBuilder) ClassUtil.createInstance(cls, canOverrideAccessModifiers());
    }

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class<? extends TypeIdResolver> cls) {
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        if (handlerInstantiator != null) {
            TypeIdResolver typeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls);
            if (typeIdResolverInstance != null) {
                return typeIdResolverInstance;
            }
        }
        return (TypeIdResolver) ClassUtil.createInstance(cls, canOverrideAccessModifiers());
    }
}
