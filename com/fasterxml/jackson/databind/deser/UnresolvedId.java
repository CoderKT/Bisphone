package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;

public class UnresolvedId {
    private final Object _id;
    private final JsonLocation _location;
    private final Class<?> _type;

    public UnresolvedId(Object obj, Class<?> cls, JsonLocation jsonLocation) {
        this._id = obj;
        this._type = cls;
        this._location = jsonLocation;
    }

    public String toString() {
        return String.format("Object id [%s] (for %s) at %s", new Object[]{this._id, this._type, this._location});
    }
}
