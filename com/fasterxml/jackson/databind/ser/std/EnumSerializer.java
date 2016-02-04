package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    public EnumSerializer(EnumValues enumValues, Boolean bool) {
        super(Enum.class, false);
        this._values = enumValues;
        this._serializeAsIndex = bool;
    }

    public static EnumSerializer construct(Class<Enum<?>> cls, SerializationConfig serializationConfig, BeanDescription beanDescription, Value value) {
        return new EnumSerializer(EnumValues.construct(serializationConfig, cls), _isShapeWrittenUsingIndex(cls, value, true));
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        if (beanProperty == null) {
            return this;
        }
        Value findFormat = serializerProvider.getAnnotationIntrospector().findFormat(beanProperty.getMember());
        if (findFormat == null) {
            return this;
        }
        Boolean _isShapeWrittenUsingIndex = _isShapeWrittenUsingIndex(beanProperty.getType().getRawClass(), findFormat, false);
        if (_isShapeWrittenUsingIndex != this._serializeAsIndex) {
            return new EnumSerializer(this._values, _isShapeWrittenUsingIndex);
        }
        return this;
    }

    public final void serialize(Enum<?> enumR, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (_serializeAsIndex(serializerProvider)) {
            jsonGenerator.writeNumber(enumR.ordinal());
        } else {
            jsonGenerator.writeString(this._values.serializedValueFor(enumR));
        }
    }

    protected final boolean _serializeAsIndex(SerializerProvider serializerProvider) {
        if (this._serializeAsIndex != null) {
            return this._serializeAsIndex.booleanValue();
        }
        return serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> cls, Value value, boolean z) {
        Shape shape = value == null ? null : value.getShape();
        if (shape == null || shape == Shape.ANY || shape == Shape.SCALAR) {
            return null;
        }
        if (shape == Shape.STRING) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric()) {
            return Boolean.TRUE;
        }
        throw new IllegalArgumentException("Unsupported serialization shape (" + shape + ") for Enum " + cls.getName() + ", not supported as " + (z ? "class" : "property") + " annotation");
    }
}
