package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.math.BigInteger;

public class BigIntegerNode extends NumericNode {
    private static final BigInteger MAX_INTEGER;
    private static final BigInteger MAX_LONG;
    private static final BigInteger MIN_INTEGER;
    private static final BigInteger MIN_LONG;
    protected final BigInteger _value;

    static {
        MIN_INTEGER = BigInteger.valueOf(-2147483648L);
        MAX_INTEGER = BigInteger.valueOf(2147483647L);
        MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    }

    public BigIntegerNode(BigInteger bigInteger) {
        this._value = bigInteger;
    }

    public static BigIntegerNode valueOf(BigInteger bigInteger) {
        return new BigIntegerNode(bigInteger);
    }

    public double doubleValue() {
        return this._value.doubleValue();
    }

    public String asText() {
        return this._value.toString();
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof BigIntegerNode)) {
            return false;
        }
        return ((BigIntegerNode) obj)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }
}
