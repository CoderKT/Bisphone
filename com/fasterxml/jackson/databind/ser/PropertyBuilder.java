package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;

public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final Include _outputProps;

    /* renamed from: com.fasterxml.jackson.databind.ser.PropertyBuilder.1 */
    /* synthetic */ class C06391 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        static {
            $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = new int[Include.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[Include.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[Include.NON_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[Include.NON_NULL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[Include.ALWAYS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        this._outputProps = beanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.fasterxml.jackson.databind.ser.BeanPropertyWriter buildWriter(com.fasterxml.jackson.databind.SerializerProvider r13, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r14, com.fasterxml.jackson.databind.JavaType r15, com.fasterxml.jackson.databind.JsonSerializer<?> r16, com.fasterxml.jackson.databind.jsontype.TypeSerializer r17, com.fasterxml.jackson.databind.jsontype.TypeSerializer r18, com.fasterxml.jackson.databind.introspect.AnnotatedMember r19, boolean r20) {
        /*
        r12 = this;
        r0 = r19;
        r1 = r20;
        r2 = r12.findSerializationType(r0, r1, r15);
        if (r18 == 0) goto L_0x00e1;
    L_0x000a:
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        r2 = r15;
    L_0x000d:
        r3 = r2.getContentType();
        if (r3 != 0) goto L_0x0050;
    L_0x0013:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Problem trying to create BeanPropertyWriter for property '";
        r4 = r4.append(r5);
        r5 = r14.getName();
        r4 = r4.append(r5);
        r5 = "' (of type ";
        r4 = r4.append(r5);
        r5 = r12._beanDesc;
        r5 = r5.getType();
        r4 = r4.append(r5);
        r5 = "); serialization type ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " has no content";
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x0050:
        r0 = r18;
        r9 = r2.withContentTypeHandler(r0);
        r9.getContentType();
    L_0x0059:
        r11 = 0;
        r2 = 0;
        r3 = r12._annotationIntrospector;
        r4 = r12._outputProps;
        r0 = r19;
        r3 = r3.findSerializationInclusion(r0, r4);
        if (r3 == 0) goto L_0x0072;
    L_0x0067:
        r4 = com.fasterxml.jackson.databind.ser.PropertyBuilder.C06391.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;
        r3 = r3.ordinal();
        r3 = r4[r3];
        switch(r3) {
            case 1: goto L_0x00a8;
            case 2: goto L_0x00c7;
            case 3: goto L_0x00cc;
            case 4: goto L_0x00cd;
            default: goto L_0x0072;
        };
    L_0x0072:
        r10 = r2;
    L_0x0073:
        r2 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
        r3 = r12._beanDesc;
        r5 = r3.getClassAnnotations();
        r3 = r14;
        r4 = r19;
        r6 = r15;
        r7 = r16;
        r8 = r17;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11);
        r3 = r12._annotationIntrospector;
        r0 = r19;
        r3 = r3.findNullSerializer(r0);
        if (r3 == 0) goto L_0x0099;
    L_0x0090:
        r0 = r19;
        r3 = r13.serializerInstance(r0, r3);
        r2.assignNullSerializer(r3);
    L_0x0099:
        r3 = r12._annotationIntrospector;
        r0 = r19;
        r3 = r3.findUnwrappingNameTransformer(r0);
        if (r3 == 0) goto L_0x00a7;
    L_0x00a3:
        r2 = r2.unwrappingWriter(r3);
    L_0x00a7:
        return r2;
    L_0x00a8:
        r3 = r14.getName();
        r0 = r19;
        r11 = r12.getDefaultValue(r3, r0);
        if (r11 != 0) goto L_0x00b7;
    L_0x00b4:
        r2 = 1;
        r10 = r2;
        goto L_0x0073;
    L_0x00b7:
        r3 = r11.getClass();
        r3 = r3.isArray();
        if (r3 == 0) goto L_0x0072;
    L_0x00c1:
        r11 = com.fasterxml.jackson.databind.util.ArrayBuilders.getArrayComparator(r11);
        r10 = r2;
        goto L_0x0073;
    L_0x00c7:
        r2 = 1;
        r11 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY;
        r10 = r2;
        goto L_0x0073;
    L_0x00cc:
        r2 = 1;
    L_0x00cd:
        r3 = r15.isContainerType();
        if (r3 == 0) goto L_0x0072;
    L_0x00d3:
        r3 = r12._config;
        r4 = com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS;
        r3 = r3.isEnabled(r4);
        if (r3 != 0) goto L_0x0072;
    L_0x00dd:
        r11 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY;
        r10 = r2;
        goto L_0x0073;
    L_0x00e1:
        r9 = r2;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.introspect.AnnotatedMember, boolean):com.fasterxml.jackson.databind.ser.BeanPropertyWriter");
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType widenBy;
        boolean z2 = true;
        Class findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        if (findSerializationType != null) {
            Class rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                widenBy = javaType.widenBy(findSerializationType);
            } else if (rawClass.isAssignableFrom(findSerializationType)) {
                widenBy = this._config.constructSpecializedType(javaType, findSerializationType);
            } else {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + findSerializationType.getName() + " not a super-type of (declared) class " + rawClass.getName());
            }
            z = true;
        } else {
            widenBy = javaType;
        }
        JavaType modifySecondaryTypesByAnnotation = BasicSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, widenBy);
        if (modifySecondaryTypesByAnnotation != widenBy) {
            widenBy = modifySecondaryTypesByAnnotation;
            z = true;
        }
        Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
        if (!(findSerializationTyping == null || findSerializationTyping == Typing.DEFAULT_TYPING)) {
            if (findSerializationTyping != Typing.STATIC) {
                z2 = false;
            }
            z = z2;
        }
        if (z) {
            return widenBy;
        }
        return null;
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (this._defaultBean == null) {
                throw new IllegalArgumentException("Class " + this._beanDesc.getClassInfo().getAnnotated().getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, AnnotatedMember annotatedMember) {
        Object defaultBean = getDefaultBean();
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    protected Object _throwWrapped(Exception exception, String str, Object obj) {
        Throwable th = exception;
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else {
            throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
        }
    }
}
