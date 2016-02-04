package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public class MapProperty extends PropertyWriter {
    protected Object _key;
    protected JsonSerializer<Object> _keySerializer;
    protected TypeSerializer _typeSerializer;
    protected Object _value;
    protected JsonSerializer<Object> _valueSerializer;

    public MapProperty(TypeSerializer typeSerializer) {
        this._typeSerializer = typeSerializer;
    }

    public void reset(Object obj, Object obj2, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        this._key = obj;
        this._value = obj2;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
    }

    public String getName() {
        if (this._key instanceof String) {
            return (String) this._key;
        }
        return String.valueOf(this._key);
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        this._keySerializer.serialize(this._key, jsonGenerator, serializerProvider);
        if (this._typeSerializer == null) {
            this._valueSerializer.serialize(this._value, jsonGenerator, serializerProvider);
        } else {
            this._valueSerializer.serializeWithType(this._value, jsonGenerator, serializerProvider, this._typeSerializer);
        }
    }

    public void serializeAsOmittedField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (!jsonGenerator.canOmitFields()) {
            jsonGenerator.writeOmittedField(getName());
        }
    }
}
