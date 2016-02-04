package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public String asText() {
        return this._value == null ? "null" : this._value.toString();
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else {
            jsonGenerator.writeObject(this._value);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof POJONode)) {
            return false;
        }
        return _pojoEquals((POJONode) obj);
    }

    protected boolean _pojoEquals(POJONode pOJONode) {
        if (this._value == null) {
            return pOJONode._value == null;
        } else {
            return this._value.equals(pOJONode._value);
        }
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        return String.valueOf(this._value);
    }
}
