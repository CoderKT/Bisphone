package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper extends ObjectCodec implements Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final BaseSettings DEFAULT_BASE;
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR;
    private static final JavaType JSON_NODE_TYPE;
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER;
    protected static final PrettyPrinter _defaultPrettyPrinter;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    static {
        JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
        DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
        DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
        STD_VISIBILITY_CHECKER = Std.defaultInstance();
        _defaultPrettyPrinter = new DefaultPrettyPrinter();
        DEFAULT_BASE = new BaseSettings(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), TimeZone.getTimeZone("GMT"), Base64Variants.getDefaultVariant());
    }

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory, DefaultSerializerProvider defaultSerializerProvider, DefaultDeserializationContext defaultDeserializationContext) {
        this._rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);
        if (jsonFactory == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jsonFactory;
            if (jsonFactory.getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        this._rootNames = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        Map hashMap = new HashMap();
        this._mixInAnnotations = hashMap;
        this._serializationConfig = new SerializationConfig(DEFAULT_BASE, this._subtypeResolver, hashMap);
        this._deserializationConfig = new DeserializationConfig(DEFAULT_BASE, this._subtypeResolver, hashMap);
        boolean requiresPropertyOrdering = this._jsonFactory.requiresPropertyOrdering();
        if ((this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY) ^ requiresPropertyOrdering) != 0) {
            configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, requiresPropertyOrdering);
        }
        if (defaultSerializerProvider == null) {
            defaultSerializerProvider = new Impl();
        }
        this._serializerProvider = defaultSerializerProvider;
        if (defaultDeserializationContext == null) {
            defaultDeserializationContext = new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
        }
        this._deserializationContext = defaultDeserializationContext;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public ObjectMapper configure(MapperFeature mapperFeature, boolean z) {
        SerializationConfig with;
        DeserializationConfig with2;
        if (z) {
            with = this._serializationConfig.with(mapperFeature);
        } else {
            with = this._serializationConfig.without(mapperFeature);
        }
        this._serializationConfig = with;
        if (z) {
            with2 = this._deserializationConfig.with(mapperFeature);
        } else {
            with2 = this._deserializationConfig.without(mapperFeature);
        }
        this._deserializationConfig = with2;
        return this;
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) {
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public <T> T readValue(String str, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createParser(str), this._typeFactory.constructType(cls));
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.createInstance(serializationConfig, this._serializerFactory);
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = (Closeable) obj;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable = null;
            try {
                closeable2.close();
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            closeable = closeable2;
            th = th4;
            if (closeable != null) {
                closeable.close();
            }
            throw th;
        }
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._deserializationContext.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) {
        try {
            Object nullValue;
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                nullValue = _findRootDeserializer(createDeserializationContext(jsonParser, getDeserializationConfig()), javaType).getNullValue();
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                nullValue = null;
            } else {
                DeserializationConfig deserializationConfig = getDeserializationConfig();
                DeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
                JsonDeserializer _findRootDeserializer = _findRootDeserializer(createDeserializationContext, javaType);
                if (deserializationConfig.useRootWrapping()) {
                    nullValue = _unwrapAndDeserialize(jsonParser, createDeserializationContext, deserializationConfig, javaType, _findRootDeserializer);
                } else {
                    nullValue = _findRootDeserializer.deserialize(jsonParser, createDeserializationContext);
                }
                createDeserializationContext.checkUnresolvedObjectId();
            }
            jsonParser.clearCurrentToken();
            return nullValue;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException e) {
            }
        }
    }

    protected JsonToken _initForReading(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            currentToken = jsonParser.nextToken();
            if (currentToken == null) {
                throw JsonMappingException.from(jsonParser, "No content to map due to end-of-input");
            }
        }
        return currentToken;
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        String rootName = deserializationConfig.getRootName();
        if (rootName == null) {
            rootName = this._rootNames.findRootName(javaType, (MapperConfig) deserializationConfig).getSimpleName();
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
        } else if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
        } else {
            String currentName = jsonParser.getCurrentName();
            if (rootName.equals(currentName)) {
                jsonParser.nextToken();
                Object deserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                    return deserialize;
                }
                throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + rootName + "'), but " + jsonParser.getCurrentToken());
            }
            throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + rootName + "') for type " + javaType);
        }
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) {
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findRootValueDeserializer(javaType);
            if (jsonDeserializer == null) {
                throw new JsonMappingException("Can not find a deserializer for type " + javaType);
            }
            this._rootDeserializers.put(javaType, jsonDeserializer);
        }
        return jsonDeserializer;
    }
}
