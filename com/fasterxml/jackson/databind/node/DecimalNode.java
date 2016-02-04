package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.math.BigDecimal;

public class DecimalNode extends NumericNode {
    private static final BigDecimal MAX_INTEGER;
    private static final BigDecimal MAX_LONG;
    private static final BigDecimal MIN_INTEGER;
    private static final BigDecimal MIN_LONG;
    public static final DecimalNode ZERO;
    protected final BigDecimal _value;

    static {
        ZERO = new DecimalNode(BigDecimal.ZERO);
        MIN_INTEGER = BigDecimal.valueOf(-2147483648L);
        MAX_INTEGER = BigDecimal.valueOf(2147483647L);
        MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
        MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
    }

    public DecimalNode(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigDecimal) {
        return new DecimalNode(bigDecimal);
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
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DecimalNode)) {
            return false;
        }
        if (((DecimalNode) obj)._value.compareTo(this._value) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Double.valueOf(doubleValue()).hashCode();
    }
}
