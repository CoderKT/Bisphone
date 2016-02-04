package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.sql.Time;

@JacksonStdImpl
public class SqlTimeSerializer extends StdScalarSerializer<Time> {
    public SqlTimeSerializer() {
        super(Time.class);
    }

    public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(time.toString());
    }
}
