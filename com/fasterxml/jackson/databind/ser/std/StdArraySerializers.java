package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.HashMap;

public class StdArraySerializers {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;

    @JacksonStdImpl
    public final class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);
        }

        public BooleanArraySerializer() {
            super(boolean[].class, null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public boolean isEmpty(boolean[] zArr) {
            return zArr == null || zArr.length == 0;
        }

        public boolean hasSingleElement(boolean[] zArr) {
            return zArr.length == 1;
        }

        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (boolean writeBoolean : zArr) {
                jsonGenerator.writeBoolean(writeBoolean);
            }
        }
    }

    @JacksonStdImpl
    public final class ByteArraySerializer extends StdSerializer<byte[]> {
        public ByteArraySerializer() {
            super(byte[].class);
        }

        public boolean isEmpty(byte[] bArr) {
            return bArr == null || bArr.length == 0;
        }

        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), bArr, 0, bArr.length);
        }

        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForScalar(bArr, jsonGenerator);
            jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), bArr, 0, bArr.length);
            typeSerializer.writeTypeSuffixForScalar(bArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public final class CharArraySerializer extends StdSerializer<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        public boolean isEmpty(char[] cArr) {
            return cArr == null || cArr.length == 0;
        }

        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray();
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }
    }

    @JacksonStdImpl
    public final class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);
        }

        public DoubleArraySerializer() {
            super(double[].class, null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public boolean isEmpty(double[] dArr) {
            return dArr == null || dArr.length == 0;
        }

        public boolean hasSingleElement(double[] dArr) {
            return dArr.length == 1;
        }

        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (double writeNumber : dArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    public abstract class TypedPrimitiveArraySerializer<T> extends ArraySerializerBase<T> {
        protected final TypeSerializer _valueTypeSerializer;

        protected TypedPrimitiveArraySerializer(Class<T> cls) {
            super(cls);
            this._valueTypeSerializer = null;
        }

        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer<T> typedPrimitiveArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super((ArraySerializerBase) typedPrimitiveArraySerializer, beanProperty);
            this._valueTypeSerializer = typeSerializer;
        }
    }

    @JacksonStdImpl
    public final class FloatArraySerializer extends TypedPrimitiveArraySerializer<float[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);
        }

        public FloatArraySerializer() {
            super(float[].class);
        }

        public FloatArraySerializer(FloatArraySerializer floatArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(floatArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(this, this._property, typeSerializer);
        }

        public boolean isEmpty(float[] fArr) {
            return fArr == null || fArr.length == 0;
        }

        public boolean hasSingleElement(float[] fArr) {
            return fArr.length == 1;
        }

        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int i = 0;
            int length;
            if (this._valueTypeSerializer != null) {
                length = fArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar(null, jsonGenerator, Float.TYPE);
                    jsonGenerator.writeNumber(fArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar(null, jsonGenerator);
                    i++;
                }
                return;
            }
            length = fArr.length;
            while (i < length) {
                jsonGenerator.writeNumber(fArr[i]);
                i++;
            }
        }
    }

    @JacksonStdImpl
    public final class IntArraySerializer extends ArraySerializerBase<int[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);
        }

        public IntArraySerializer() {
            super(int[].class, null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public boolean isEmpty(int[] iArr) {
            return iArr == null || iArr.length == 0;
        }

        public boolean hasSingleElement(int[] iArr) {
            return iArr.length == 1;
        }

        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (int writeNumber : iArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }
    }

    @JacksonStdImpl
    public final class LongArraySerializer extends TypedPrimitiveArraySerializer<long[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);
        }

        public LongArraySerializer() {
            super(long[].class);
        }

        public LongArraySerializer(LongArraySerializer longArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(longArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(this, this._property, typeSerializer);
        }

        public boolean isEmpty(long[] jArr) {
            return jArr == null || jArr.length == 0;
        }

        public boolean hasSingleElement(long[] jArr) {
            return jArr.length == 1;
        }

        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int i = 0;
            int length;
            if (this._valueTypeSerializer != null) {
                length = jArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar(null, jsonGenerator, Long.TYPE);
                    jsonGenerator.writeNumber(jArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar(null, jsonGenerator);
                    i++;
                }
                return;
            }
            length = jArr.length;
            while (i < length) {
                jsonGenerator.writeNumber(jArr[i]);
                i++;
            }
        }
    }

    @JacksonStdImpl
    public final class ShortArraySerializer extends TypedPrimitiveArraySerializer<short[]> {
        private static final JavaType VALUE_TYPE;

        static {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
        }

        public ShortArraySerializer() {
            super(short[].class);
        }

        public ShortArraySerializer(ShortArraySerializer shortArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(shortArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(this, this._property, typeSerializer);
        }

        public boolean isEmpty(short[] sArr) {
            return sArr == null || sArr.length == 0;
        }

        public boolean hasSingleElement(short[] sArr) {
            return sArr.length == 1;
        }

        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int i = 0;
            int length;
            if (this._valueTypeSerializer != null) {
                length = sArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar(null, jsonGenerator, Short.TYPE);
                    jsonGenerator.writeNumber(sArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar(null, jsonGenerator);
                    i++;
                }
                return;
            }
            length = sArr.length;
            while (i < length) {
                jsonGenerator.writeNumber(sArr[i]);
                i++;
            }
        }
    }

    static {
        _arraySerializers = new HashMap();
        _arraySerializers.put(boolean[].class.getName(), new BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
    }

    public static JsonSerializer<?> findStandardImpl(Class<?> cls) {
        return (JsonSerializer) _arraySerializers.get(cls.getName());
    }
}
