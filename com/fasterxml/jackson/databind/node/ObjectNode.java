package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectNode extends ContainerNode<ObjectNode> {
    protected final Map<String, JsonNode> _children;

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new LinkedHashMap();
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        for (Entry entry : this._children.entrySet()) {
            jsonGenerator.writeFieldName((String) entry.getKey());
            ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(this, jsonGenerator);
        for (Entry entry : this._children.entrySet()) {
            jsonGenerator.writeFieldName((String) entry.getKey());
            ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(this, jsonGenerator);
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return (JsonNode) this._children.put(str, jsonNode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ObjectNode)) {
            return false;
        }
        return _childrenEqual((ObjectNode) obj);
    }

    protected boolean _childrenEqual(ObjectNode objectNode) {
        return this._children.equals(objectNode._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder((size() << 4) + 32);
        stringBuilder.append("{");
        int i = 0;
        for (Entry entry : this._children.entrySet()) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            int i2 = i + 1;
            TextNode.appendQuoted(stringBuilder, (String) entry.getKey());
            stringBuilder.append(':');
            stringBuilder.append(((JsonNode) entry.getValue()).toString());
            i = i2;
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
