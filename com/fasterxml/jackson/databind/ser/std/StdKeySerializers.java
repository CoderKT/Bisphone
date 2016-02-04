package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Calendar;
import java.util.Date;

public class StdKeySerializers {
    protected static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER;
    protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER;

    public class CalendarKeySerializer extends StdSerializer<Calendar> {
        protected static final JsonSerializer<?> instance;

        static {
            instance = new CalendarKeySerializer();
        }

        public CalendarKeySerializer() {
            super(Calendar.class);
        }

        public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateKey(calendar.getTimeInMillis(), jsonGenerator);
        }
    }

    public class DateKeySerializer extends StdSerializer<Date> {
        protected static final JsonSerializer<?> instance;

        static {
            instance = new DateKeySerializer();
        }

        public DateKeySerializer() {
            super(Date.class);
        }

        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeDateKey(date, jsonGenerator);
        }
    }

    public class StringKeySerializer extends StdSerializer<String> {
        public StringKeySerializer() {
            super(String.class);
        }

        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeFieldName(str);
        }
    }

    static {
        DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
        DEFAULT_STRING_SERIALIZER = new StringKeySerializer();
    }

    public static JsonSerializer<Object> getStdKeySerializer(JavaType javaType) {
        if (javaType == null) {
            return DEFAULT_KEY_SERIALIZER;
        }
        Class rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return DEFAULT_STRING_SERIALIZER;
        }
        if (rawClass == Object.class || rawClass.isPrimitive() || Number.class.isAssignableFrom(rawClass)) {
            return DEFAULT_KEY_SERIALIZER;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateKeySerializer.instance;
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarKeySerializer.instance;
        }
        return DEFAULT_KEY_SERIALIZER;
    }
}
