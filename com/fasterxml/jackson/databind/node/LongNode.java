package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LongNode extends NumericNode {
    protected final long _value;

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public String asText() {
        return NumberOutput.toString(this._value);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LongNode)) {
            return false;
        }
        if (((LongNode) obj)._value != this._value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((int) this._value) ^ ((int) (this._value >> 32));
    }
}
