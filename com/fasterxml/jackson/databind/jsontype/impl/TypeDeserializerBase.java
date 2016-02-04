package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.HashMap;

public abstract class TypeDeserializerBase extends TypeDeserializer implements Serializable {
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer<Object> _defaultImplDeserializer;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;
    protected final boolean _typeIdVisible;
    protected final String _typePropertyName;

    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            this._defaultImpl = javaType.forcedNarrowBy(cls);
        }
        this._property = null;
    }

    protected TypeDeserializerBase(TypeDeserializerBase typeDeserializerBase, BeanProperty beanProperty) {
        this._baseType = typeDeserializerBase._baseType;
        this._idResolver = typeDeserializerBase._idResolver;
        this._typePropertyName = typeDeserializerBase._typePropertyName;
        this._typeIdVisible = typeDeserializerBase._typeIdVisible;
        this._deserializers = typeDeserializerBase._deserializers;
        this._defaultImpl = typeDeserializerBase._defaultImpl;
        this._defaultImplDeserializer = typeDeserializerBase._defaultImplDeserializer;
        this._property = beanProperty;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    public final String getPropertyName() {
        return this._typePropertyName;
    }

    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    public Class<?> getDefaultImpl() {
        return this._defaultImpl == null ? null : this._defaultImpl.getRawClass();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(getClass().getName());
        stringBuilder.append("; base-type:").append(this._baseType);
        stringBuilder.append("; id-resolver: ").append(this._idResolver);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = (JsonDeserializer) this._deserializers.get(str);
            if (jsonDeserializer == null) {
                JavaType typeFromId;
                if (this._idResolver instanceof TypeIdResolverBase) {
                    typeFromId = ((TypeIdResolverBase) this._idResolver).typeFromId(deserializationContext, str);
                } else {
                    typeFromId = this._idResolver.typeFromId(str);
                }
                if (typeFromId == null) {
                    jsonDeserializer = _findDefaultImplDeserializer(deserializationContext);
                    if (jsonDeserializer == null) {
                        throw deserializationContext.unknownTypeException(this._baseType, str);
                    }
                }
                if (this._baseType != null && this._baseType.getClass() == typeFromId.getClass()) {
                    typeFromId = this._baseType.narrowBy(typeFromId.getRawClass());
                }
                jsonDeserializer = deserializationContext.findContextualValueDeserializer(typeFromId, this._property);
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    protected final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext deserializationContext) {
        if (this._defaultImpl == null) {
            if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
            return NullifyingDeserializer.instance;
        } else if (ClassUtil.isBogusClass(this._defaultImpl.getRawClass())) {
            return NullifyingDeserializer.instance;
        } else {
            JsonDeserializer<Object> jsonDeserializer;
            synchronized (this._defaultImpl) {
                if (this._defaultImplDeserializer == null) {
                    this._defaultImplDeserializer = deserializationContext.findContextualValueDeserializer(this._defaultImpl, this._property);
                }
                jsonDeserializer = this._defaultImplDeserializer;
            }
            return jsonDeserializer;
        }
    }

    protected Object _deserializeWithNativeTypeId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        JsonDeserializer _findDefaultImplDeserializer;
        if (obj == null) {
            _findDefaultImplDeserializer = _findDefaultImplDeserializer(deserializationContext);
            if (_findDefaultImplDeserializer == null) {
                throw deserializationContext.mappingException("No (native) type id found when one was expected for polymorphic type handling");
            }
        }
        _findDefaultImplDeserializer = _findDeserializer(deserializationContext, obj instanceof String ? (String) obj : String.valueOf(obj));
        return _findDefaultImplDeserializer.deserialize(jsonParser, deserializationContext);
    }
}
