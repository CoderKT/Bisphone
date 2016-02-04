package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements ContextualDeserializer {
    protected final ArrayType _arrayType;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;
    protected final boolean _untyped;

    public ObjectArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(arrayType);
        this._arrayType = arrayType;
        this._elementClass = arrayType.getContentType().getRawClass();
        this._untyped = this._elementClass == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = typeDeserializer;
    }

    public ObjectArrayDeserializer withDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == this._elementDeserializer && typeDeserializer == this._elementTypeDeserializer) ? this : new ObjectArrayDeserializer(this._arrayType, jsonDeserializer, typeDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JsonDeserializer findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext, beanProperty, this._elementDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext.findContextualValueDeserializer(this._arrayType.getContentType(), beanProperty);
        } else {
            findConvertingContentDeserializer = deserializationContext.handleSecondaryContextualization(findConvertingContentDeserializer, beanProperty);
        }
        TypeDeserializer typeDeserializer = this._elementTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withDeserializer(typeDeserializer, findConvertingContentDeserializer);
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._elementDeserializer;
    }

    public Object[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext);
        }
        Object[] completeAndClearBuffer;
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        TypeDeserializer typeDeserializer = this._elementTypeDeserializer;
        int i = 0;
        while (true) {
            try {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    break;
                }
                Object nullValue;
                int i2;
                if (nextToken == JsonToken.VALUE_NULL) {
                    nullValue = this._elementDeserializer.getNullValue();
                } else if (typeDeserializer == null) {
                    nullValue = this._elementDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    nullValue = this._elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i2 = 0;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                resetAndStart[i2] = nullValue;
            } catch (Throwable e) {
                throw JsonMappingException.wrapWithPath(e, (Object) resetAndStart, i);
            }
        }
        if (this._untyped) {
            completeAndClearBuffer = leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i);
        } else {
            completeAndClearBuffer = leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i, this._elementClass);
        }
        deserializationContext.returnObjectBuffer(leaseObjectBuffer);
        return completeAndClearBuffer;
    }

    public Object[] deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return (Object[]) typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    protected Byte[] deserializeFromBase64(JsonParser jsonParser, DeserializationContext deserializationContext) {
        byte[] binaryValue = jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
        Byte[] bArr = new Byte[binaryValue.length];
        int length = binaryValue.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = Byte.valueOf(binaryValue[i]);
        }
        return bArr;
    }

    private final Object[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            Object nullValue;
            Object[] objArr;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                nullValue = this._elementDeserializer.getNullValue();
            } else if (this._elementTypeDeserializer == null) {
                nullValue = this._elementDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                nullValue = this._elementDeserializer.deserializeWithType(jsonParser, deserializationContext, this._elementTypeDeserializer);
            }
            if (this._untyped) {
                objArr = new Object[1];
            } else {
                objArr = (Object[]) Array.newInstance(this._elementClass, 1);
            }
            objArr[0] = nullValue;
            return objArr;
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && this._elementClass == Byte.class) {
            return deserializeFromBase64(jsonParser, deserializationContext);
        } else {
            throw deserializationContext.mappingException(this._arrayType.getRawClass());
        }
    }
}
