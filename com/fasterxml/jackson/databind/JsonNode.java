package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.util.Iterator;

public abstract class JsonNode implements Iterable<JsonNode> {
    public abstract String asText();

    public abstract String toString();

    protected JsonNode() {
    }

    public int size() {
        return 0;
    }

    public double doubleValue() {
        return 0.0d;
    }

    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public Iterator<JsonNode> elements() {
        return EmptyIterator.instance();
    }
}
