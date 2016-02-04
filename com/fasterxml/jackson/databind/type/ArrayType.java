package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;

public final class ArrayType extends TypeBase {
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    private ArrayType(JavaType javaType, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), javaType.hashCode(), obj2, obj3, z);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0), null, null, false);
    }

    public ArrayType withTypeHandler(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    public ArrayType withContentTypeHandler(Object obj) {
        return obj == this._componentType.getTypeHandler() ? this : new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ArrayType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new ArrayType(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }

    public ArrayType withContentValueHandler(Object obj) {
        return obj == this._componentType.getValueHandler() ? this : new ArrayType(this._componentType.withValueHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    protected String buildCanonicalName() {
        return this._class.getName();
    }

    protected JavaType _narrow(Class<?> cls) {
        if (cls.isArray()) {
            return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()), this._valueHandler, this._typeHandler);
        }
        throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + cls.getName());
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public boolean isArrayType() {
        return true;
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isConcrete() {
        return true;
    }

    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    public boolean isContainerType() {
        return true;
    }

    public JavaType getContentType() {
        return this._componentType;
    }

    public int containedTypeCount() {
        return 1;
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._componentType : null;
    }

    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }
}
