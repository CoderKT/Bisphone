package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends ContainerNode<ArrayNode> {
    private final List<JsonNode> _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new ArrayList();
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        for (JsonNode jsonNode : this._children) {
            ((BaseJsonNode) jsonNode).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(this, jsonGenerator);
        for (JsonNode jsonNode : this._children) {
            ((BaseJsonNode) jsonNode).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(this, jsonGenerator);
    }

    public ArrayNode add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ArrayNode)) {
            return false;
        }
        return this._children.equals(((ArrayNode) obj)._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder((size() << 4) + 16);
        stringBuilder.append('[');
        int size = this._children.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(((JsonNode) this._children.get(i)).toString());
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    protected ArrayNode _add(JsonNode jsonNode) {
        this._children.add(jsonNode);
        return this;
    }
}
