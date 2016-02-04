package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    protected final EnumResolver<?> _resolver;

    public class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.getAnnotated();
            this._inputType = cls2;
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            String text;
            if (this._inputType == null) {
                text = jsonParser.getText();
            } else if (this._inputType == Integer.class) {
                text = Integer.valueOf(jsonParser.getValueAsInt());
            } else if (this._inputType == Long.class) {
                text = Long.valueOf(jsonParser.getValueAsLong());
            } else {
                throw deserializationContext.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, new Object[]{text});
            } catch (Throwable th) {
                Throwable th2 = ClassUtil.getRootCause(th2);
                if (th2 instanceof IOException) {
                    throw ((IOException) th2);
                }
                throw deserializationContext.instantiationException(this._enumClass, th2);
            }
        }
    }

    public EnumDeserializer(EnumResolver<?> enumResolver) {
        super(Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class rawParameterType = annotatedMethod.getRawParameterType(0);
        if (rawParameterType == String.class) {
            rawParameterType = null;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            rawParameterType = Integer.class;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            rawParameterType = Long.class;
        } else {
            throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String or int/Integer/long/Long");
        }
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod, rawParameterType);
    }

    public boolean isCachable() {
        return true;
    }

    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        Enum<?> findEnum;
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            String text = jsonParser.getText();
            findEnum = this._resolver.findEnum(text);
            if (findEnum == null) {
                return _deserializeAltString(jsonParser, deserializationContext, text);
            }
            return findEnum;
        } else if (currentToken != JsonToken.VALUE_NUMBER_INT) {
            return _deserializeOther(jsonParser, deserializationContext);
        } else {
            if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            }
            int intValue = jsonParser.getIntValue();
            findEnum = this._resolver.getEnum(intValue);
            if (findEnum != null || deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return findEnum;
            }
            throw deserializationContext.weirdNumberException(Integer.valueOf(intValue), this._resolver.getEnumClass(), "index value outside legal index range [0.." + this._resolver.lastValidIndex() + "]");
        }
    }

    private final Enum<?> _deserializeAltString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) {
        String trim = str.trim();
        if (trim.length() != 0) {
            char charAt = trim.charAt(0);
            if (charAt >= '0' && charAt <= '9') {
                try {
                    Enum<?> enumR = this._resolver.getEnum(Integer.parseInt(trim));
                    if (enumR != null) {
                        return enumR;
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
        }
        throw deserializationContext.weirdStringException(trim, this._resolver.getEnumClass(), "value not one of declared Enum instance names: " + this._resolver.getEnums());
    }

    private final Enum<?> _deserializeOther(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            Enum<?> deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return deserialize;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._resolver.getEnumClass().getName() + "' value but there was more than a single value in the array");
        }
        throw deserializationContext.mappingException(this._resolver.getEnumClass());
    }
}
