package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final DateFormat _customFormat;
    protected final Boolean _useTimestamp;

    protected abstract long _timestamp(T t);

    public abstract DateTimeSerializerBase<T> withFormat(Boolean bool, DateFormat dateFormat);

    protected DateTimeSerializerBase(Class<T> cls, Boolean bool, DateFormat dateFormat) {
        super(cls);
        this._useTimestamp = bool;
        this._customFormat = dateFormat;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        if (beanProperty == null) {
            return this;
        }
        Value findFormat = serializerProvider.getAnnotationIntrospector().findFormat(beanProperty.getMember());
        if (findFormat == null) {
            return this;
        }
        if (findFormat.getShape().isNumeric()) {
            return withFormat(Boolean.TRUE, null);
        }
        Boolean bool = findFormat.getShape() == Shape.STRING ? Boolean.FALSE : null;
        TimeZone timeZone = findFormat.getTimeZone();
        if (findFormat.hasPattern()) {
            TimeZone timeZone2;
            DateFormat simpleDateFormat = new SimpleDateFormat(findFormat.getPattern(), findFormat.hasLocale() ? findFormat.getLocale() : serializerProvider.getLocale());
            if (timeZone == null) {
                timeZone2 = serializerProvider.getTimeZone();
            } else {
                timeZone2 = timeZone;
            }
            simpleDateFormat.setTimeZone(timeZone2);
            return withFormat(bool, simpleDateFormat);
        } else if (timeZone == null) {
            return this;
        } else {
            DateFormat dateFormat = serializerProvider.getConfig().getDateFormat();
            if (dateFormat.getClass() == StdDateFormat.class) {
                dateFormat = StdDateFormat.getISO8601Format(timeZone, findFormat.hasLocale() ? findFormat.getLocale() : serializerProvider.getLocale());
            } else {
                dateFormat = (DateFormat) dateFormat.clone();
                dateFormat.setTimeZone(timeZone);
            }
            return withFormat(bool, dateFormat);
        }
    }

    public boolean isEmpty(T t) {
        return t == null || _timestamp(t) == 0;
    }

    protected boolean _asTimestamp(SerializerProvider serializerProvider) {
        if (this._useTimestamp != null) {
            return this._useTimestamp.booleanValue();
        }
        if (this._customFormat != null) {
            return false;
        }
        if (serializerProvider != null) {
            return serializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        throw new IllegalArgumentException("Null 'provider' passed for " + handledType().getName());
    }
}
