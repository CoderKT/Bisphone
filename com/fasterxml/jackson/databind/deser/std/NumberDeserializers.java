package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import se.emilsjolander.stickylistheaders.C1128R;

public class NumberDeserializers {
    private static final HashSet<String> _classNames;

    /* renamed from: com.fasterxml.jackson.databind.deser.std.NumberDeserializers.1 */
    /* synthetic */ class C06251 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[NumberType.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @JacksonStdImpl
    public class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance;

        static {
            instance = new BigDecimalDeserializer();
        }

        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                try {
                    return new BigDecimal(trim);
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid representation");
                }
            } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                BigDecimal deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigDecimal' value but there was more than a single value in the array");
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
    }

    @JacksonStdImpl
    public class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance;

        static {
            instance = new BigIntegerDeserializer();
        }

        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (C06251.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        return BigInteger.valueOf(jsonParser.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            } else {
                if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    jsonParser.nextToken();
                    BigInteger deserialize = deserialize(jsonParser, deserializationContext);
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        return deserialize;
                    }
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigInteger' value but there was more than a single value in the array");
                } else if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext.mappingException(this._valueClass, currentToken);
                }
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid representation");
            }
        }
    }

    public abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        protected final T _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super(cls);
            this._nullValue = t;
        }

        public final T getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    public final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        private static final BooleanDeserializer primitiveInstance;
        private static final BooleanDeserializer wrapperInstance;

        static {
            primitiveInstance = new BooleanDeserializer(Boolean.class, Boolean.FALSE);
            wrapperInstance = new BooleanDeserializer(Boolean.TYPE, null);
        }

        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        private static final ByteDeserializer primitiveInstance;
        private static final ByteDeserializer wrapperInstance;

        static {
            primitiveInstance = new ByteDeserializer(Byte.TYPE, Byte.valueOf((byte) 0));
            wrapperInstance = new ByteDeserializer(Byte.class, null);
        }

        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        private static final CharacterDeserializer primitiveInstance;
        private static final CharacterDeserializer wrapperInstance;

        static {
            primitiveInstance = new CharacterDeserializer(Character.class, Character.valueOf('\u0000'));
            wrapperInstance = new CharacterDeserializer(Character.TYPE, null);
        }

        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= InBandBytestreamManager.MAXIMUM_BLOCK_SIZE) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
                if (text.length() == 0) {
                    return (Character) getEmptyValue();
                }
            } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Character deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @JacksonStdImpl
    public class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        private static final DoubleDeserializer primitiveInstance;
        private static final DoubleDeserializer wrapperInstance;

        static {
            primitiveInstance = new DoubleDeserializer(Double.class, Double.valueOf(0.0d));
            wrapperInstance = new DoubleDeserializer(Double.TYPE, null);
        }

        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseDouble(jsonParser, deserializationContext);
        }

        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        private static final FloatDeserializer primitiveInstance;
        private static final FloatDeserializer wrapperInstance;

        static {
            primitiveInstance = new FloatDeserializer(Float.class, Float.valueOf(0.0f));
            wrapperInstance = new FloatDeserializer(Float.TYPE, null);
        }

        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        private static final IntegerDeserializer primitiveInstance;
        private static final IntegerDeserializer wrapperInstance;

        static {
            primitiveInstance = new IntegerDeserializer(Integer.class, Integer.valueOf(0));
            wrapperInstance = new IntegerDeserializer(Integer.TYPE, null);
        }

        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseInteger(jsonParser, deserializationContext);
        }

        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        private static final LongDeserializer primitiveInstance;
        private static final LongDeserializer wrapperInstance;

        static {
            primitiveInstance = new LongDeserializer(Long.class, Long.valueOf(0));
            wrapperInstance = new LongDeserializer(Long.TYPE, null);
        }

        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public class NumberDeserializer extends StdScalarDeserializer<Number> {
        public static final NumberDeserializer instance;

        static {
            instance = new NumberDeserializer();
        }

        public NumberDeserializer() {
            super(Number.class);
        }

        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Number) getEmptyValue();
                }
                if (_hasTextualNull(trim)) {
                    return (Number) getNullValue();
                }
                if (_isPosInf(trim)) {
                    return Double.valueOf(Double.POSITIVE_INFINITY);
                }
                if (_isNegInf(trim)) {
                    return Double.valueOf(Double.NEGATIVE_INFINITY);
                }
                if (_isNaN(trim)) {
                    return Double.valueOf(Double.NaN);
                }
                try {
                    if (trim.indexOf(46) >= 0) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return new BigDecimal(trim);
                        }
                        return new Double(trim);
                    } else if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        return new BigInteger(trim);
                    } else {
                        long parseLong = Long.parseLong(trim);
                        if (parseLong > 2147483647L || parseLong < -2147483648L) {
                            return Long.valueOf(parseLong);
                        }
                        return Integer.valueOf((int) parseLong);
                    }
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid number");
                }
            } else if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Number deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            switch (C06251.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return deserialize(jsonParser, deserializationContext);
                default:
                    return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
        }
    }

    @JacksonStdImpl
    public class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        private static final ShortDeserializer primitiveInstance;
        private static final ShortDeserializer wrapperInstance;

        static {
            primitiveInstance = new ShortDeserializer(Short.class, Short.valueOf((short) 0));
            wrapperInstance = new ShortDeserializer(Short.TYPE, null);
        }

        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    static {
        int i = 0;
        _classNames = new HashSet();
        Class[] clsArr = new Class[]{Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        int length = clsArr.length;
        while (i < length) {
            _classNames.add(clsArr[i].getName());
            i++;
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
        } else if (!_classNames.contains(str)) {
            return null;
        } else {
            if (cls == Integer.class) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (cls == Boolean.class) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (cls == Long.class) {
                return LongDeserializer.wrapperInstance;
            }
            if (cls == Double.class) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (cls == Character.class) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (cls == Byte.class) {
                return ByteDeserializer.wrapperInstance;
            }
            if (cls == Short.class) {
                return ShortDeserializer.wrapperInstance;
            }
            if (cls == Float.class) {
                return FloatDeserializer.wrapperInstance;
            }
            if (cls == Number.class) {
                return NumberDeserializer.instance;
            }
            if (cls == BigDecimal.class) {
                return BigDecimalDeserializer.instance;
            }
            if (cls == BigInteger.class) {
                return BigIntegerDeserializer.instance;
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + cls.getName());
    }
}
