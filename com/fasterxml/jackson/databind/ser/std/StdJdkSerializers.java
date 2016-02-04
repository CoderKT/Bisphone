package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class StdJdkSerializers {

    public final class AtomicBooleanSerializer extends StdScalarSerializer<AtomicBoolean> {
        public AtomicBooleanSerializer() {
            super(AtomicBoolean.class, false);
        }

        public void serialize(AtomicBoolean atomicBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBoolean(atomicBoolean.get());
        }
    }

    public final class AtomicIntegerSerializer extends StdScalarSerializer<AtomicInteger> {
        public AtomicIntegerSerializer() {
            super(AtomicInteger.class, false);
        }

        public void serialize(AtomicInteger atomicInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(atomicInteger.get());
        }
    }

    public final class AtomicLongSerializer extends StdScalarSerializer<AtomicLong> {
        public AtomicLongSerializer() {
            super(AtomicLong.class, false);
        }

        public void serialize(AtomicLong atomicLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(atomicLong.get());
        }
    }

    public final class AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> {
        public AtomicReferenceSerializer() {
            super(AtomicReference.class, false);
        }

        public void serialize(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeValue(atomicReference.get(), jsonGenerator);
        }
    }

    public static Collection<Entry<Class<?>, Object>> all() {
        HashMap hashMap = new HashMap();
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        hashMap.put(URL.class, toStringSerializer);
        hashMap.put(URI.class, toStringSerializer);
        hashMap.put(Currency.class, toStringSerializer);
        hashMap.put(UUID.class, new UUIDSerializer());
        hashMap.put(Pattern.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(AtomicReference.class, AtomicReferenceSerializer.class);
        hashMap.put(AtomicBoolean.class, AtomicBooleanSerializer.class);
        hashMap.put(AtomicInteger.class, AtomicIntegerSerializer.class);
        hashMap.put(AtomicLong.class, AtomicLongSerializer.class);
        hashMap.put(File.class, FileSerializer.class);
        hashMap.put(Class.class, ClassSerializer.class);
        hashMap.put(Void.class, NullSerializer.instance);
        hashMap.put(Void.TYPE, NullSerializer.instance);
        return hashMap.entrySet();
    }
}
