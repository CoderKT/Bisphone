package com.fasterxml.jackson.databind.node;

public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode {
    protected final JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    public String asText() {
        return "";
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }
}
