package com.fasterxml.jackson.databind.node;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory implements Serializable {
    private static final JsonNodeFactory decimalsAsIs;
    private static final JsonNodeFactory decimalsNormalized;
    public static final JsonNodeFactory instance;
    private final boolean _cfgBigDecimalExact;

    static {
        decimalsNormalized = new JsonNodeFactory(false);
        decimalsAsIs = new JsonNodeFactory(true);
        instance = decimalsNormalized;
    }

    public JsonNodeFactory(boolean z) {
        this._cfgBigDecimalExact = z;
    }

    protected JsonNodeFactory() {
        this(false);
    }

    public BooleanNode booleanNode(boolean z) {
        return z ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(int i) {
        return IntNode.valueOf(i);
    }

    public NumericNode numberNode(long j) {
        if (_inIntRange(j)) {
            return IntNode.valueOf((int) j);
        }
        return LongNode.valueOf(j);
    }

    public NumericNode numberNode(BigInteger bigInteger) {
        return BigIntegerNode.valueOf(bigInteger);
    }

    public NumericNode numberNode(double d) {
        return DoubleNode.valueOf(d);
    }

    public NumericNode numberNode(BigDecimal bigDecimal) {
        if (this._cfgBigDecimalExact) {
            return DecimalNode.valueOf(bigDecimal);
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) == 0 ? DecimalNode.ZERO : DecimalNode.valueOf(bigDecimal.stripTrailingZeros());
    }

    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }

    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    public ValueNode pojoNode(Object obj) {
        return new POJONode(obj);
    }

    protected boolean _inIntRange(long j) {
        return ((long) ((int) j)) == j;
    }
}
