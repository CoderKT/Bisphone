package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class UnresolvedForwardReference extends JsonMappingException {
    private ReadableObjectId _roid;
    private List<UnresolvedId> _unresolvedIds;

    public UnresolvedForwardReference(String str, JsonLocation jsonLocation, ReadableObjectId readableObjectId) {
        super(str, jsonLocation);
        this._roid = readableObjectId;
    }

    public UnresolvedForwardReference(String str) {
        super(str);
        this._unresolvedIds = new ArrayList();
    }

    public ReadableObjectId getRoid() {
        return this._roid;
    }

    public Object getUnresolvedId() {
        return this._roid.getKey().key;
    }

    public void addUnresolvedId(Object obj, Class<?> cls, JsonLocation jsonLocation) {
        this._unresolvedIds.add(new UnresolvedId(obj, cls, jsonLocation));
    }

    public String getMessage() {
        String message = super.getMessage();
        if (this._unresolvedIds == null) {
            return message;
        }
        StringBuilder stringBuilder = new StringBuilder(message);
        Iterator it = this._unresolvedIds.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((UnresolvedId) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append('.');
        return stringBuilder.toString();
    }
}
