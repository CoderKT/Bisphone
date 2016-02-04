package com.fasterxml.jackson.databind.deser.std;

import app.C0110R;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    protected final Class<?> _valueClass;

    protected StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    protected StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }

    public Class<?> handledType() {
        return this._valueClass;
    }

    protected boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl((Object) jsonDeserializer);
    }

    protected boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl((Object) keyDeserializer);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        boolean z = true;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_NULL) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() != NumberType.INT) {
                return _parseBooleanFromNumber(jsonParser, deserializationContext);
            }
            if (jsonParser.getIntValue() == 0) {
                z = false;
            }
            return z;
        } else if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if ("true".equals(trim) || "True".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || "False".equals(trim) || trim.length() == 0 || _hasTextualNull(trim)) {
                return false;
            }
            throw deserializationContext.weirdStringException(trim, this._valueClass, "only \"true\" or \"false\" recognized");
        } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return _parseBooleanPrimitive;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'boolean' value but there was more than a single value in the array");
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() == NumberType.INT) {
                return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
            } else {
                return Boolean.valueOf(_parseBooleanFromNumber(jsonParser, deserializationContext));
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue();
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim) || "True".equals(trim)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(trim) || "False".equals(trim)) {
                    return Boolean.FALSE;
                }
                if (trim.length() == 0) {
                    return (Boolean) getEmptyValue();
                }
                if (_hasTextualNull(trim)) {
                    return (Boolean) getNullValue();
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "only \"true\" or \"false\" recognized");
            } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Boolean _parseBoolean = _parseBoolean(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseBoolean;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Boolean' value but there was more than a single value in the array");
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
    }

    protected final boolean _parseBooleanFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getNumberType() == NumberType.LONG) {
            return (jsonParser.getLongValue() == 0 ? Boolean.FALSE : Boolean.TRUE).booleanValue();
        }
        String text = jsonParser.getText();
        if ("0.0".equals(text) || "0".equals(text)) {
            return Boolean.FALSE.booleanValue();
        }
        return Boolean.TRUE.booleanValue();
    }

    protected Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Byte.valueOf(jsonParser.getByteValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (_hasTextualNull(trim)) {
                return (Byte) getNullValue();
            }
            try {
                if (trim.length() == 0) {
                    return (Byte) getEmptyValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt >= -128 && parseInt <= 255) {
                    return Byte.valueOf((byte) parseInt);
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "overflow, value can not be represented as 8-bit value");
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Byte value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Byte) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Byte _parseByte = _parseByte(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseByte;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) getEmptyValue();
                }
                if (_hasTextualNull(trim)) {
                    return (Short) getNullValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt >= -32768 && parseInt <= 32767) {
                    return Short.valueOf((short) parseInt);
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "overflow, value can not be represented as 16-bit value");
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Short value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Short) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Short _parseShort = _parseShort(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseShort;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Short' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive >= -32768 && _parseIntPrimitive <= 32767) {
            return (short) _parseIntPrimitive;
        }
        throw deserializationContext.weirdStringException(String.valueOf(_parseIntPrimitive), this._valueClass, "overflow, value can not be represented as 16-bit value");
    }

    protected final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (_hasTextualNull(trim)) {
                return 0;
            }
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    throw deserializationContext.weirdStringException(trim, this._valueClass, "Overflow: numeric value (" + trim + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
                } else if (length != 0) {
                    return NumberInput.parseInt(trim);
                } else {
                    return 0;
                }
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid int value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseIntPrimitive;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'int' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (_hasTextualNull(trim)) {
                    return (Integer) getNullValue();
                }
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    throw deserializationContext.weirdStringException(trim, this._valueClass, "Overflow: numeric value (" + trim + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
                } else if (length == 0) {
                    return (Integer) getEmptyValue();
                } else {
                    return Integer.valueOf(NumberInput.parseInt(trim));
                }
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Integer value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Integer) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Integer _parseInteger = _parseInteger(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseInteger;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Integer' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Long) getEmptyValue();
            }
            if (_hasTextualNull(trim)) {
                return (Long) getNullValue();
            }
            try {
                return Long.valueOf(NumberInput.parseLong(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Long) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Long _parseLong = _parseLong(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseLong;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Long' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0 || _hasTextualNull(trim)) {
                return 0;
            }
            try {
                return NumberInput.parseLong(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseLongPrimitive;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'long' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Float) getEmptyValue();
            }
            if (_hasTextualNull(trim)) {
                return (Float) getNullValue();
            }
            switch (trim.charAt(0)) {
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    if (_isNegInf(trim)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                    break;
                case C0110R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                    if (_isPosInf(trim)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                    break;
                case C0110R.styleable.Theme_panelBackground /*78*/:
                    if (_isNaN(trim)) {
                        return Float.valueOf(Float.NaN);
                    }
                    break;
            }
            try {
                return Float.valueOf(Float.parseFloat(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Float) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Float _parseFloat = _parseFloat(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseFloat;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0 || _hasTextualNull(trim)) {
                return 0.0f;
            }
            switch (trim.charAt(0)) {
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    if (_isNegInf(trim)) {
                        return Float.NEGATIVE_INFINITY;
                    }
                    break;
                case C0110R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                    if (_isPosInf(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                    break;
                case C0110R.styleable.Theme_panelBackground /*78*/:
                    if (_isNaN(trim)) {
                        return Float.NaN;
                    }
                    break;
            }
            try {
                return Float.parseFloat(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0f;
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseFloatPrimitive;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'float' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Double) getEmptyValue();
            }
            if (_hasTextualNull(trim)) {
                return (Double) getNullValue();
            }
            switch (trim.charAt(0)) {
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    if (_isNegInf(trim)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    break;
                case C0110R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                    if (_isPosInf(trim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                    break;
                case C0110R.styleable.Theme_panelBackground /*78*/:
                    if (_isNaN(trim)) {
                        return Double.valueOf(Double.NaN);
                    }
                    break;
            }
            try {
                return Double.valueOf(parseDouble(trim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Double) getNullValue();
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Double _parseDouble = _parseDouble(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseDouble;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Double' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0 || _hasTextualNull(trim)) {
                return 0.0d;
            }
            switch (trim.charAt(0)) {
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    if (_isNegInf(trim)) {
                        return Double.NEGATIVE_INFINITY;
                    }
                    break;
                case C0110R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                    if (_isPosInf(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                    break;
                case C0110R.styleable.Theme_panelBackground /*78*/:
                    if (_isNaN(trim)) {
                        return Double.NaN;
                    }
                    break;
            }
            try {
                return parseDouble(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0d;
        } else {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return _parseDoublePrimitive;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return new Date(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return (Date) getNullValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Date) getEmptyValue();
                }
                if (_hasTextualNull(trim)) {
                    return (Date) getNullValue();
                }
                return deserializationContext.parseDate(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(null, this._valueClass, "not a valid representation (error: " + e.getMessage() + ")");
            }
        } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return _parseDate;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.util.Date' value but there was more than a single value in the array");
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected static final double parseDouble(String str) {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    protected final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            return jsonParser.getText();
        }
        String _parseString;
        if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            _parseString = _parseString(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return _parseString;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'String' value but there was more than a single value in the array");
        }
        _parseString = jsonParser.getValueAsString();
        if (_parseString != null) {
            return _parseString;
        }
        throw deserializationContext.mappingException(String.class, jsonParser.getCurrentToken());
    }

    protected boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    protected final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    protected final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    protected JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || beanProperty == null) {
            return jsonDeserializer;
        }
        Object findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(beanProperty.getMember());
        if (findDeserializationContentConverter == null) {
            return jsonDeserializer;
        }
        Converter converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer);
    }

    protected void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        if (obj == null) {
            obj = handledType();
        }
        if (!deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            deserializationContext.reportUnknownProperty(obj, str, this);
            jsonParser.skipChildren();
        }
    }
}
