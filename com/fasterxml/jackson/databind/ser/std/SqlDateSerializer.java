package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.sql.Date;
import java.text.DateFormat;

@JacksonStdImpl
public class SqlDateSerializer extends DateTimeSerializerBase<Date> {
    public SqlDateSerializer() {
        this(Boolean.FALSE);
    }

    protected SqlDateSerializer(Boolean bool) {
        super(Date.class, bool, null);
    }

    public SqlDateSerializer withFormat(Boolean bool, DateFormat dateFormat) {
        return new SqlDateSerializer(bool);
    }

    protected long _timestamp(Date date) {
        return date == null ? 0 : date.getTime();
    }

    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._useTimestamp.booleanValue()) {
            jsonGenerator.writeNumber(_timestamp(date));
        } else {
            jsonGenerator.writeString(date.toString());
        }
    }
}
