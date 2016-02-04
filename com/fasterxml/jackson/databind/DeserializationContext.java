package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract void checkUnresolvedObjectId();

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj);

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj);

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        if (deserializerCache == null) {
            deserializerCache = new DeserializerCache();
        }
        this._cache = deserializerCache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
        this._attributes = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        this._attributes = deserializationConfig.getAttributes();
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._featureFlags & deserializationFeature.getMask()) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        if (this._injectableValues != null) {
            return this._injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
        }
        throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer != null) {
            return handleSecondaryContextualization(findValueDeserializer, beanProperty);
        }
        return findValueDeserializer;
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) {
        JsonDeserializer findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer == null) {
            return null;
        }
        JsonDeserializer<Object> handleSecondaryContextualization = handleSecondaryContextualization(findValueDeserializer, null);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return findTypeDeserializer != null ? new TypeWrappedDeserializer(findTypeDeserializer.forProperty(null), handleSecondaryContextualization) : handleSecondaryContextualization;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) {
        KeyDeserializer findKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        if (findKeyDeserializer instanceof ContextualKeyDeserializer) {
            return ((ContextualKeyDeserializer) findKeyDeserializer).createContextual(this, beanProperty);
        }
        return findKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) {
        return ClassUtil.findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty) {
        if (jsonDeserializer == null || !(jsonDeserializer instanceof ContextualDeserializer)) {
            return jsonDeserializer;
        }
        return ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
    }

    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty) {
        if (jsonDeserializer == null || !(jsonDeserializer instanceof ContextualDeserializer)) {
            return jsonDeserializer;
        }
        return ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
    }

    public Date parseDate(String str) {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse Date value '" + str + "': " + e.getMessage());
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        instance.setTime(date);
        return instance;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) {
        LinkedNode problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            for (LinkedNode linkedNode = problemHandlers; linkedNode != null; linkedNode = linkedNode.next()) {
                if (((DeserializationProblemHandler) linkedNode.value()).handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) {
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }

    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        return JsonMappingException.from(this._parser, "Can not deserialize instance of " + _calcName(cls) + " out of " + jsonToken + " token");
    }

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + ", problem: " + th.getMessage(), th);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + ", problem: " + str);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        return InvalidFormatException.from(this._parser, "Can not construct instance of " + cls.getName() + " from String value '" + _valueDesc() + "': " + str2, str, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        return InvalidFormatException.from(this._parser, "Can not construct instance of " + cls.getName() + " from number value (" + _valueDesc() + "): " + str, null, cls);
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        return InvalidFormatException.from(this._parser, "Can not construct Map key of type " + cls.getName() + " from String \"" + _desc(str) + "\": " + str2, str, cls);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        String str2 = "Unexpected token (" + jsonParser.getCurrentToken() + "), expected " + jsonToken;
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return JsonMappingException.from(jsonParser, str2);
    }

    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        return JsonMappingException.from(this._parser, "Could not resolve type id '" + str + "' into a subtype of " + javaType);
    }

    protected DateFormat getDateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }

    protected String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType()) + "[]";
        }
        return cls.getName();
    }

    protected String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception e) {
            return "[N/A]";
        }
    }

    protected String _desc(String str) {
        if (str.length() > PacketWriter.QUEUE_SIZE) {
            return str.substring(0, PacketWriter.QUEUE_SIZE) + "]...[" + str.substring(str.length() - 500);
        }
        return str;
    }
}
