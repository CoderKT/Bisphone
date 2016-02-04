package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BooleanNode extends ValueNode {
    public static final BooleanNode FALSE;
    public static final BooleanNode TRUE;
    private final boolean _value;

    static {
        TRUE = new BooleanNode(true);
        FALSE = new BooleanNode(false);
    }

    private BooleanNode(boolean z) {
        this._value = z;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public String asText() {
        return this._value ? "true" : "false";
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBoolean(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BooleanNode)) {
            return false;
        }
        if (this._value != ((BooleanNode) obj)._value) {
            return false;
        }
        return true;
    }
}
