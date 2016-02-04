package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class LRUMap<K, V> implements Serializable {
    protected final transient ConcurrentHashMap<K, V> _map;
    protected final transient int _maxEntries;

    public LRUMap(int i, int i2) {
        this._map = new ConcurrentHashMap(i, 0.8f, 4);
        this._maxEntries = i2;
    }

    public V put(K k, V v) {
        if (this._map.size() >= this._maxEntries) {
            synchronized (this) {
                if (this._map.size() >= this._maxEntries) {
                    clear();
                }
            }
        }
        return this._map.put(k, v);
    }

    public V get(Object obj) {
        return this._map.get(obj);
    }

    public void clear() {
        this._map.clear();
    }
}
