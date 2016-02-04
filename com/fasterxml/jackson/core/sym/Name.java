package com.fasterxml.jackson.core.sym;

public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public String toString() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }
}
