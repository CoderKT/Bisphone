package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.TimeZone;

public class TimeZoneSerializer extends StdScalarSerializer<TimeZone> {
    public TimeZoneSerializer() {
        super(TimeZone.class);
    }

    public void serialize(TimeZone timeZone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(timeZone.getID());
    }

    public void serializeWithType(TimeZone timeZone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(timeZone, jsonGenerator, TimeZone.class);
        serialize(timeZone, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(timeZone, jsonGenerator);
    }
}
