package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public ArrayBlockingQueueDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator, JsonDeserializer<Object> jsonDeserializer2) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, jsonDeserializer2);
    }

    protected ArrayBlockingQueueDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer2, typeDeserializer, this._valueInstantiator, jsonDeserializer);
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            if (text.length() == 0) {
                return (Collection) this._valueInstantiator.createFromString(deserializationContext, text);
            }
        }
        return deserialize(jsonParser, deserializationContext, null);
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, new ArrayBlockingQueue(1));
        }
        Object arrayList = new ArrayList();
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            try {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    break;
                }
                Object nullValue;
                if (nextToken == JsonToken.VALUE_NULL) {
                    nullValue = jsonDeserializer.getNullValue();
                } else if (typeDeserializer == null) {
                    nullValue = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    nullValue = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                arrayList.add(nullValue);
            } catch (Throwable e) {
                throw JsonMappingException.wrapWithPath(e, arrayList, arrayList.size());
            }
        }
        if (collection == null) {
            return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
        }
        collection.addAll(arrayList);
        return collection;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
