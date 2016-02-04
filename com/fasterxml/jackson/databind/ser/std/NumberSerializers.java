package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

public class NumberSerializers {

    /* renamed from: com.fasterxml.jackson.databind.ser.std.NumberSerializers.1 */
    /* synthetic */ class C06411 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape;

        static {
            $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = new int[Shape.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[Shape.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public abstract class Base<T> extends StdScalarSerializer<T> implements ContextualSerializer {
        protected final boolean _isInt;
        protected final NumberType _numberType;
        protected final String _schemaType;

        protected Base(Class<T> cls, NumberType numberType, String str) {
            super(cls);
            this._numberType = numberType;
            this._schemaType = str;
            boolean z = numberType == NumberType.INT || numberType == NumberType.LONG || numberType == NumberType.BIG_INTEGER;
            this._isInt = z;
        }

        public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            if (beanProperty == null) {
                return this;
            }
            Value findFormat = serializerProvider.getAnnotationIntrospector().findFormat(beanProperty.getMember());
            if (findFormat == null) {
                return this;
            }
            switch (C06411.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[findFormat.getShape().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return ToStringSerializer.instance;
                default:
                    return this;
            }
        }
    }

    @JacksonStdImpl
    public final class DoubleSerializer extends Base<Double> {
        static final DoubleSerializer instance;

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        static {
            instance = new DoubleSerializer();
        }

        public DoubleSerializer() {
            super(Double.class, NumberType.DOUBLE, "number");
        }

        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(d.doubleValue());
        }

        public void serializeWithType(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            serialize(d, jsonGenerator, serializerProvider);
        }
    }

    @JacksonStdImpl
    public final class FloatSerializer extends Base<Float> {
        static final FloatSerializer instance;

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        static {
            instance = new FloatSerializer();
        }

        public FloatSerializer() {
            super(Float.class, NumberType.FLOAT, "number");
        }

        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    @JacksonStdImpl
    public final class IntLikeSerializer extends Base<Number> {
        static final IntLikeSerializer instance;

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        static {
            instance = new IntLikeSerializer();
        }

        public IntLikeSerializer() {
            super(Number.class, NumberType.INT, "integer");
        }

        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(number.intValue());
        }
    }

    @JacksonStdImpl
    public final class IntegerSerializer extends Base<Integer> {
        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        public IntegerSerializer() {
            super(Integer.class, NumberType.INT, "integer");
        }

        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(num.intValue());
        }

        public void serializeWithType(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            serialize(num, jsonGenerator, serializerProvider);
        }
    }

    @JacksonStdImpl
    public final class LongSerializer extends Base<Long> {
        static final LongSerializer instance;

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        static {
            instance = new LongSerializer();
        }

        public LongSerializer() {
            super(Long.class, NumberType.LONG, "number");
        }

        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    @JacksonStdImpl
    public final class ShortSerializer extends Base<Short> {
        static final ShortSerializer instance;

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
            return super.createContextual(serializerProvider, beanProperty);
        }

        static {
            instance = new ShortSerializer();
        }

        public ShortSerializer() {
            super(Short.class, NumberType.INT, "number");
        }

        public void serialize(Short sh, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(sh.shortValue());
        }
    }

    public static void addAll(Map<String, JsonSerializer<?>> map) {
        IntegerSerializer integerSerializer = new IntegerSerializer();
        map.put(Integer.class.getName(), integerSerializer);
        map.put(Integer.TYPE.getName(), integerSerializer);
        map.put(Long.class.getName(), LongSerializer.instance);
        map.put(Long.TYPE.getName(), LongSerializer.instance);
        map.put(Byte.class.getName(), IntLikeSerializer.instance);
        map.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
        map.put(Short.class.getName(), ShortSerializer.instance);
        map.put(Short.TYPE.getName(), ShortSerializer.instance);
        map.put(Float.class.getName(), FloatSerializer.instance);
        map.put(Float.TYPE.getName(), FloatSerializer.instance);
        map.put(Double.class.getName(), DoubleSerializer.instance);
        map.put(Double.TYPE.getName(), DoubleSerializer.instance);
    }
}
