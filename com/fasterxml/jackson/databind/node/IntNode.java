package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IntNode extends NumericNode {
    private static final IntNode[] CANONICALS;
    protected final int _value;

    static {
        CANONICALS = new IntNode[12];
        for (int i = 0; i < 12; i++) {
            CANONICALS[i] = new IntNode(i - 1);
        }
    }

    public IntNode(int i) {
        this._value = i;
    }

    public static IntNode valueOf(int i) {
        if (i > 10 || i < -1) {
            return new IntNode(i);
        }
        return CANONICALS[i + 1];
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
        if (!(obj instanceof IntNode)) {
            return false;
        }
        if (((IntNode) obj)._value != this._value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this._value;
    }
}
