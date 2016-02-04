package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class SimpleType extends TypeBase {
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    protected SimpleType(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = javaTypeArr;
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        return new SimpleType(cls, null, null, null, null, false);
    }

    protected JavaType _narrow(Class<?> cls) {
        return new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    public JavaType widenContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    public SimpleType withTypeHandler(Object obj) {
        return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
    }

    public JavaType withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    public SimpleType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
    }

    public SimpleType withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    protected String buildCanonicalName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this._class.getName());
        if (this._typeParameters != null && this._typeParameters.length > 0) {
            stringBuilder.append('<');
            Object obj = 1;
            for (JavaType javaType : this._typeParameters) {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(',');
                }
                stringBuilder.append(javaType.toCanonical());
            }
            stringBuilder.append('>');
        }
        return stringBuilder.toString();
    }

    public boolean isContainerType() {
        return false;
    }

    public int containedTypeCount() {
        return this._typeParameters == null ? 0 : this._typeParameters.length;
    }

    public JavaType containedType(int i) {
        if (i < 0 || this._typeParameters == null || i >= this._typeParameters.length) {
            return null;
        }
        return this._typeParameters[i];
    }

    public String containedTypeName(int i) {
        if (i < 0 || this._typeNames == null || i >= this._typeNames.length) {
            return null;
        }
        return this._typeNames[i];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(40);
        stringBuilder.append("[simple type, class ").append(buildCanonicalName()).append(']');
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != this._class) {
            return false;
        }
        JavaType[] javaTypeArr = this._typeParameters;
        JavaType[] javaTypeArr2 = simpleType._typeParameters;
        if (javaTypeArr == null) {
            if (javaTypeArr2 == null || javaTypeArr2.length == 0) {
                return true;
            }
            return false;
        } else if (javaTypeArr2 == null || javaTypeArr.length != javaTypeArr2.length) {
            return false;
        } else {
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
