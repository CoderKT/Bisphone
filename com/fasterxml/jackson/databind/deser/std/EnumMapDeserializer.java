package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements ContextualDeserializer {
    protected final Class<?> _enumClass;
    protected JsonDeserializer<Enum<?>> _keyDeserializer;
    protected final JavaType _mapType;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public EnumMapDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        super(EnumMap.class);
        this._mapType = javaType;
        this._enumClass = javaType.getKeyType().getRawClass();
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = typeDeserializer;
    }

    public EnumMapDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        return (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) ? this : new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, this._valueTypeDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JsonDeserializer jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(this._mapType.getKeyType(), beanProperty);
        }
        JsonDeserializer jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = deserializationContext.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty);
        } else {
            jsonDeserializer2 = deserializationContext.handleSecondaryContextualization(jsonDeserializer2, beanProperty);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(jsonDeserializer, jsonDeserializer2, typeDeserializer);
    }

    public boolean isCachable() {
        return true;
    }

    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(EnumMap.class);
        }
        EnumMap<?, ?> constructMap = constructMap();
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Enum enumR = (Enum) this._keyDeserializer.deserialize(jsonParser, deserializationContext);
            if (enumR != null) {
                Object nullValue;
                if (jsonParser.nextToken() == JsonToken.VALUE_NULL) {
                    nullValue = jsonDeserializer.getNullValue();
                } else if (typeDeserializer == null) {
                    nullValue = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    nullValue = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                constructMap.put(enumR, nullValue);
            } else if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                jsonParser.nextToken();
                jsonParser.skipChildren();
            } else {
                String str = null;
                try {
                    if (jsonParser.hasCurrentToken()) {
                        str = jsonParser.getText();
                    }
                } catch (Exception e) {
                }
                throw deserializationContext.weirdStringException(str, this._enumClass, "value not one of declared Enum instance names");
            }
        }
        return constructMap;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap(this._enumClass);
    }
}
