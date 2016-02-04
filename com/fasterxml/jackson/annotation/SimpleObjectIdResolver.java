package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import java.util.HashMap;
import java.util.Map;

public class SimpleObjectIdResolver implements ObjectIdResolver {
    private Map<IdKey, Object> _items;

    public SimpleObjectIdResolver() {
        this._items = new HashMap();
    }

    public void bindItem(IdKey idKey, Object obj) {
        if (this._items.containsKey(idKey)) {
            throw new IllegalStateException("Already had POJO for id (" + idKey.key.getClass().getName() + ") [" + idKey + "]");
        }
        this._items.put(idKey, obj);
    }

    public Object resolveId(IdKey idKey) {
        return this._items.get(idKey);
    }

    public boolean canUseFor(ObjectIdResolver objectIdResolver) {
        return objectIdResolver.getClass() == getClass();
    }

    public ObjectIdResolver newForDeserialization(Object obj) {
        return this;
    }
}
