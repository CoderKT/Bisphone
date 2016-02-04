package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

public class CreatorCollector {
    protected final BeanDescription _beanDesc;
    protected AnnotatedWithParams _booleanCreator;
    protected final boolean _canFixAccess;
    protected AnnotatedWithParams _defaultConstructor;
    protected CreatorProperty[] _delegateArgs;
    protected AnnotatedWithParams _delegateCreator;
    protected AnnotatedWithParams _doubleCreator;
    protected AnnotatedParameter _incompleteParameter;
    protected AnnotatedWithParams _intCreator;
    protected AnnotatedWithParams _longCreator;
    protected CreatorProperty[] _propertyBasedArgs;
    protected AnnotatedWithParams _propertyBasedCreator;
    protected AnnotatedWithParams _stringCreator;

    public final class Vanilla extends ValueInstantiator implements Serializable {
        private final int _type;

        public Vanilla(int i) {
            this._type = i;
        }

        public String getValueTypeDesc() {
            switch (this._type) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return ArrayList.class.getName();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return LinkedHashMap.class.getName();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return HashMap.class.getName();
                default:
                    return Object.class.getName();
            }
        }

        public boolean canInstantiate() {
            return true;
        }

        public boolean canCreateUsingDefault() {
            return true;
        }

        public Object createUsingDefault(DeserializationContext deserializationContext) {
            switch (this._type) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return new ArrayList();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return new LinkedHashMap();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return new HashMap();
                default:
                    throw new IllegalStateException("Unknown type " + this._type);
            }
        }
    }

    public CreatorCollector(BeanDescription beanDescription, boolean z) {
        this._propertyBasedArgs = null;
        this._beanDesc = beanDescription;
        this._canFixAccess = z;
    }

    public ValueInstantiator constructValueInstantiator(DeserializationConfig deserializationConfig) {
        JavaType javaType;
        int i = 0;
        int i2 = this._delegateCreator == null ? 1 : 0;
        if (i2 != 0) {
            javaType = null;
        } else {
            int i3;
            if (this._delegateArgs != null) {
                int length = this._delegateArgs.length;
                i3 = 0;
                while (i3 < length) {
                    if (this._delegateArgs[i3] == null) {
                        break;
                    }
                    i3++;
                }
            }
            i3 = 0;
            javaType = this._beanDesc.bindingsForBeanType().resolveType(this._delegateCreator.getGenericParameterType(i3));
        }
        JavaType type = this._beanDesc.getType();
        if (this._propertyBasedCreator == null && this._delegateCreator == null && this._stringCreator == null && this._longCreator == null && this._doubleCreator == null && this._booleanCreator == null) {
            i = 1;
        }
        if ((i2 & i) != 0) {
            Class rawClass = type.getRawClass();
            if (rawClass == Collection.class || rawClass == List.class || rawClass == ArrayList.class) {
                return new Vanilla(1);
            }
            if (rawClass == Map.class || rawClass == LinkedHashMap.class) {
                return new Vanilla(2);
            }
            if (rawClass == HashMap.class) {
                return new Vanilla(3);
            }
        }
        ValueInstantiator stdValueInstantiator = new StdValueInstantiator(deserializationConfig, type);
        stdValueInstantiator.configureFromObjectSettings(this._defaultConstructor, this._delegateCreator, javaType, this._delegateArgs, this._propertyBasedCreator, this._propertyBasedArgs);
        stdValueInstantiator.configureFromStringCreator(this._stringCreator);
        stdValueInstantiator.configureFromIntCreator(this._intCreator);
        stdValueInstantiator.configureFromLongCreator(this._longCreator);
        stdValueInstantiator.configureFromDoubleCreator(this._doubleCreator);
        stdValueInstantiator.configureFromBooleanCreator(this._booleanCreator);
        stdValueInstantiator.configureIncompleteParameter(this._incompleteParameter);
        return stdValueInstantiator;
    }

    public void setDefaultCreator(AnnotatedWithParams annotatedWithParams) {
        this._defaultConstructor = (AnnotatedWithParams) _fixAccess(annotatedWithParams);
    }

    public void addStringCreator(AnnotatedWithParams annotatedWithParams) {
        this._stringCreator = verifyNonDup(annotatedWithParams, this._stringCreator, "String");
    }

    public void addIntCreator(AnnotatedWithParams annotatedWithParams) {
        this._intCreator = verifyNonDup(annotatedWithParams, this._intCreator, "int");
    }

    public void addLongCreator(AnnotatedWithParams annotatedWithParams) {
        this._longCreator = verifyNonDup(annotatedWithParams, this._longCreator, "long");
    }

    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        this._doubleCreator = verifyNonDup(annotatedWithParams, this._doubleCreator, "double");
    }

    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        this._booleanCreator = verifyNonDup(annotatedWithParams, this._booleanCreator, "boolean");
    }

    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        this._delegateCreator = verifyNonDup(annotatedWithParams, this._delegateCreator, "delegate");
        this._delegateArgs = creatorPropertyArr;
    }

    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        this._propertyBasedCreator = verifyNonDup(annotatedWithParams, this._propertyBasedCreator, "property-based");
        if (creatorPropertyArr.length > 1) {
            HashMap hashMap = new HashMap();
            int length = creatorPropertyArr.length;
            int i = 0;
            while (i < length) {
                String name = creatorPropertyArr[i].getName();
                if (name.length() != 0 || creatorPropertyArr[i].getInjectableValueId() == null) {
                    Integer num = (Integer) hashMap.put(name, Integer.valueOf(i));
                    if (num != null) {
                        throw new IllegalArgumentException("Duplicate creator property \"" + name + "\" (index " + num + " vs " + i + ")");
                    }
                }
                i++;
            }
        }
        this._propertyBasedArgs = creatorPropertyArr;
    }

    public void addIncompeteParameter(AnnotatedParameter annotatedParameter) {
        if (this._incompleteParameter == null) {
            this._incompleteParameter = annotatedParameter;
        }
    }

    public boolean hasDefaultCreator() {
        return this._defaultConstructor != null;
    }

    private <T extends AnnotatedMember> T _fixAccess(T t) {
        if (t != null && this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member) t.getAnnotated());
        }
        return t;
    }

    protected AnnotatedWithParams verifyNonDup(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, String str) {
        if (annotatedWithParams2 == null || annotatedWithParams2.getClass() != annotatedWithParams.getClass()) {
            return (AnnotatedWithParams) _fixAccess(annotatedWithParams);
        }
        throw new IllegalArgumentException("Conflicting " + str + " creators: already had " + annotatedWithParams2 + ", encountered " + annotatedWithParams);
    }
}
