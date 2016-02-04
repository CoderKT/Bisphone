package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ContextualDeserializer {
    protected final JavaType _collectionType;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, valueInstantiator, null, jsonDeserializer);
    }

    protected StringCollectionDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        super(javaType);
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = jsonDeserializer;
    }

    protected StringCollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        return (this._valueDeserializer == jsonDeserializer2 && this._delegateDeserializer == jsonDeserializer) ? this : new StringCollectionDeserializer(this._collectionType, this._valueInstantiator, jsonDeserializer, jsonDeserializer2);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JsonDeserializer jsonDeserializer;
        JsonDeserializer jsonDeserializer2 = null;
        if (this._valueInstantiator == null || this._valueInstantiator.getDelegateCreator() == null) {
            jsonDeserializer = null;
        } else {
            jsonDeserializer = findDeserializer(deserializationContext, this._valueInstantiator.getDelegateType(deserializationContext.getConfig()), beanProperty);
        }
        JsonDeserializer jsonDeserializer3 = this._valueDeserializer;
        if (jsonDeserializer3 == null) {
            jsonDeserializer3 = findConvertingContentDeserializer(deserializationContext, beanProperty, jsonDeserializer3);
            if (jsonDeserializer3 == null) {
                jsonDeserializer3 = deserializationContext.findContextualValueDeserializer(this._collectionType.getContentType(), beanProperty);
            }
        } else {
            jsonDeserializer3 = deserializationContext.handleSecondaryContextualization(jsonDeserializer3, beanProperty);
        }
        if (!isDefaultDeserializer(jsonDeserializer3)) {
            jsonDeserializer2 = jsonDeserializer3;
        }
        return withResolved(jsonDeserializer, jsonDeserializer2);
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault(deserializationContext));
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        if (this._valueDeserializer != null) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection, this._valueDeserializer);
        }
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            Object text;
            if (nextToken == JsonToken.VALUE_STRING) {
                text = jsonParser.getText();
            } else {
                try {
                    if (nextToken == JsonToken.VALUE_NULL) {
                        text = null;
                    } else {
                        text = _parseString(jsonParser, deserializationContext);
                    }
                } catch (Throwable e) {
                    throw JsonMappingException.wrapWithPath(e, (Object) collection, collection.size());
                }
            }
            collection.add(text);
        }
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection, JsonDeserializer<String> jsonDeserializer) {
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            Object obj;
            if (nextToken == JsonToken.VALUE_NULL) {
                obj = (String) jsonDeserializer.getNullValue();
            } else {
                String str = (String) jsonDeserializer.deserialize(jsonParser, deserializationContext);
            }
            collection.add(obj);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) {
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            Object obj;
            JsonDeserializer jsonDeserializer = this._valueDeserializer;
            String str;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                if (jsonDeserializer == null) {
                    obj = null;
                } else {
                    str = (String) jsonDeserializer.getNullValue();
                }
            } else if (jsonDeserializer == null) {
                obj = _parseString(jsonParser, deserializationContext);
            } else {
                str = (String) jsonDeserializer.deserialize(jsonParser, deserializationContext);
            }
            collection.add(obj);
            return collection;
        }
        throw deserializationContext.mappingException(this._collectionType.getRawClass());
    }
}
