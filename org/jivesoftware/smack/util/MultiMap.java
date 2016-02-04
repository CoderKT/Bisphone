package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MultiMap<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int DEFAULT_MAP_SIZE = 6;
    private static final int ENTRY_SET_SIZE = 3;
    private final Map<K, Set<V>> map;

    class SimpleMapEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        private SimpleMapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    static {
        $assertionsDisabled = !MultiMap.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public MultiMap() {
        this(DEFAULT_MAP_SIZE);
    }

    public MultiMap(int i) {
        this.map = new LinkedHashMap(i);
    }

    public int size() {
        int i = 0;
        for (Set size : this.map.values()) {
            i = size.size() + i;
        }
        return i;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        for (Set contains : this.map.values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return $assertionsDisabled;
    }

    public V getFirst(Object obj) {
        Set all = getAll(obj);
        if (all.isEmpty()) {
            return null;
        }
        return all.iterator().next();
    }

    public Set<V> getAll(Object obj) {
        Set<V> set = (Set) this.map.get(obj);
        if (set == null) {
            return Collections.emptySet();
        }
        return set;
    }

    public boolean put(K k, V v) {
        Set set = (Set) this.map.get(k);
        if (set == null) {
            set = new LinkedHashSet(ENTRY_SET_SIZE);
            set.add(v);
            this.map.put(k, set);
            return $assertionsDisabled;
        }
        set.add(v);
        return true;
    }

    public V remove(Object obj) {
        Set set = (Set) this.map.remove(obj);
        if (set == null) {
            return null;
        }
        if ($assertionsDisabled || !set.isEmpty()) {
            return set.iterator().next();
        }
        throw new AssertionError();
    }

    public boolean removeOne(Object obj, V v) {
        Set set = (Set) this.map.get(obj);
        if (set == null) {
            return $assertionsDisabled;
        }
        boolean remove = set.remove(v);
        if (set.isEmpty()) {
            this.map.remove(obj);
        }
        return remove;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        this.map.clear();
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public List<V> values() {
        List<V> arrayList = new ArrayList(size());
        for (Set addAll : this.map.values()) {
            arrayList.addAll(addAll);
        }
        return arrayList;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> linkedHashSet = new LinkedHashSet(size());
        for (Entry entry : this.map.entrySet()) {
            Object key = entry.getKey();
            for (Object simpleMapEntry : (Set) entry.getValue()) {
                linkedHashSet.add(new SimpleMapEntry(simpleMapEntry, null));
            }
        }
        return linkedHashSet;
    }
}
