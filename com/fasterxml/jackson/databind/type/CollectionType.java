package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class CollectionType extends CollectionLikeType {
    private CollectionType(Class<?> cls, JavaType javaType, Object obj, Object obj2, boolean z) {
        super(cls, javaType, obj, obj2, z);
    }

    protected JavaType _narrow(Class<?> cls) {
        return new CollectionType(cls, this._elementType, null, null, this._asStatic);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public static CollectionType construct(Class<?> cls, JavaType javaType) {
        return new CollectionType(cls, javaType, null, null, false);
    }

    public CollectionType withTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    public CollectionType withContentTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public CollectionType withValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    public CollectionType withContentValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public String toString() {
        return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }
}
