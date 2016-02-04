package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider extends DatabindContext {
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER;
    protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER;
    @Deprecated
    protected static final JavaType TYPE_OBJECT;
    protected transient ContextAttributes _attributes;
    protected final SerializationConfig _config;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final Class<?> _serializationView;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected final boolean _stdNullValueSerializer;
    protected JsonSerializer<Object> _unknownTypeSerializer;

    public abstract WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj);

    static {
        TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
        DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
        DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
    }

    public SerializerProvider() {
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._config = null;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
        this._serializationView = null;
        this._attributes = null;
        this._stdNullValueSerializer = true;
    }

    protected SerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._config = serializationConfig;
        this._serializerCache = serializerProvider._serializerCache;
        this._unknownTypeSerializer = serializerProvider._unknownTypeSerializer;
        this._keySerializer = serializerProvider._keySerializer;
        this._nullValueSerializer = serializerProvider._nullValueSerializer;
        this._nullKeySerializer = serializerProvider._nullKeySerializer;
        this._stdNullValueSerializer = this._nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER;
        this._rootNames = serializerProvider._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
        this._serializationView = serializationConfig.getActiveView();
        this._attributes = serializationConfig.getAttributes();
    }

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public final Class<?> getActiveView() {
        return this._serializationView;
    }

    public final boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls);
                    }
                }
            }
        }
        return handleSecondaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType.getRawClass());
                }
            }
        }
        return handleSecondaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findPrimaryPropertySerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType.getRawClass());
                }
            }
        }
        return handlePrimaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findPrimaryPropertySerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls);
                    }
                }
            }
        }
        return handlePrimaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(cls);
        if (typedValueSerializer == null) {
            typedValueSerializer = this._serializerCache.typedValueSerializer(cls);
            if (typedValueSerializer == null) {
                JsonSerializer<Object> findValueSerializer = findValueSerializer((Class) cls, beanProperty);
                TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls));
                if (createTypeSerializer != null) {
                    typedValueSerializer = new TypeWrappedSerializer(createTypeSerializer.forProperty(beanProperty), findValueSerializer);
                } else {
                    typedValueSerializer = findValueSerializer;
                }
                if (z) {
                    this._serializerCache.addTypedSerializer(cls, typedValueSerializer);
                }
            }
        }
        return typedValueSerializer;
    }

    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        return _handleContextualResolvable(this._serializerFactory.createKeySerializer(this._config, javaType, this._keySerializer), beanProperty);
    }

    public JsonSerializer<Object> getDefaultNullValueSerializer() {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> findNullKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        return this._nullKeySerializer;
    }

    public JsonSerializer<Object> findNullValueSerializer(BeanProperty beanProperty) {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        return this._unknownTypeSerializer;
    }

    public JsonSerializer<?> handlePrimaryContextualization(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) {
        if (jsonSerializer == null || !(jsonSerializer instanceof ContextualSerializer)) {
            return jsonSerializer;
        }
        return ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }

    public JsonSerializer<?> handleSecondaryContextualization(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) {
        if (jsonSerializer == null || !(jsonSerializer instanceof ContextualSerializer)) {
            return jsonSerializer;
        }
        return ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) {
        if (obj != null) {
            findTypedValueSerializer(obj.getClass(), true, null).serialize(obj, jsonGenerator, this);
        } else if (this._stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            this._nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
        } else {
            jsonGenerator.writeString(_dateFormat().format(date));
        }
    }

    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j));
        } else {
            jsonGenerator.writeFieldName(_dateFormat().format(new Date(j)));
        }
    }

    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
        } else {
            jsonGenerator.writeFieldName(_dateFormat().format(date));
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) {
        if (this._stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            this._nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls));
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer((Class) cls, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(javaType);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType) {
        return this._serializerFactory.createSerializer(this, javaType);
    }

    protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return handleSecondaryContextualization(jsonSerializer, beanProperty);
    }

    protected JsonSerializer<Object> _handleResolvable(JsonSerializer<?> jsonSerializer) {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return jsonSerializer;
    }

    protected final DateFormat _dateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }
}
