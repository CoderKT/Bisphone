package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE;
    protected final String _value;

    static {
        EMPTY_STRING_NODE = new TextNode("");
    }

    public TextNode(String str) {
        this._value = str;
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(str);
    }

    public String asText() {
        return this._value;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(this._value);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof TextNode)) {
            return false;
        }
        return ((TextNode) obj)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        int length = this._value.length();
        StringBuilder stringBuilder = new StringBuilder((length >> 4) + (length + 2));
        appendQuoted(stringBuilder, this._value);
        return stringBuilder.toString();
    }

    protected static void appendQuoted(StringBuilder stringBuilder, String str) {
        stringBuilder.append('\"');
        CharTypes.appendQuoted(stringBuilder, str);
        stringBuilder.append('\"');
    }
}
