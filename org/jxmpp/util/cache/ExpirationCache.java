package org.jxmpp.util.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ExpirationCache<K, V> implements Map<K, V>, Cache<K, V> {
    private final LruCache<K, ExpireElement<V>> f8631a;
    private long f8632b;

    class EntryImpl<K, V> implements Entry<K, V> {
        private final K f8627a;
        private V f8628b;

        public EntryImpl(K k, V v) {
            this.f8627a = k;
            this.f8628b = v;
        }

        public K getKey() {
            return this.f8627a;
        }

        public V getValue() {
            return this.f8628b;
        }

        public V setValue(V v) {
            V v2 = this.f8628b;
            this.f8628b = v;
            return v2;
        }
    }

    class ExpireElement<V> {
        public final V f8629a;
        public final long f8630b;

        public ExpireElement(V v, long j) {
            this.f8629a = v;
            this.f8630b = System.currentTimeMillis() + j;
        }

        public boolean m13449a() {
            return System.currentTimeMillis() > this.f8630b;
        }

        public int hashCode() {
            return this.f8629a.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ExpireElement)) {
                return false;
            }
            return this.f8629a.equals(((ExpireElement) obj).f8629a);
        }
    }

    public ExpirationCache(int i, long j) {
        this.f8631a = new LruCache(i);
        m13451a(j);
    }

    public void m13451a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException();
        }
        this.f8632b = j;
    }

    public V put(K k, V v) {
        return m13450a(k, v, this.f8632b);
    }

    public V m13450a(K k, V v, long j) {
        ExpireElement expireElement = (ExpireElement) this.f8631a.put(k, new ExpireElement(v, j));
        if (expireElement == null) {
            return null;
        }
        return expireElement.f8629a;
    }

    public V get(Object obj) {
        ExpireElement expireElement = (ExpireElement) this.f8631a.get(obj);
        if (expireElement == null) {
            return null;
        }
        if (!expireElement.m13449a()) {
            return expireElement.f8629a;
        }
        remove(obj);
        return null;
    }

    public V remove(Object obj) {
        ExpireElement expireElement = (ExpireElement) this.f8631a.remove(obj);
        if (expireElement == null) {
            return null;
        }
        return expireElement.f8629a;
    }

    public int size() {
        return this.f8631a.size();
    }

    public boolean isEmpty() {
        return this.f8631a.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.f8631a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.f8631a.containsValue(obj);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        this.f8631a.clear();
    }

    public Set<K> keySet() {
        return this.f8631a.keySet();
    }

    public Collection<V> values() {
        Collection hashSet = new HashSet();
        for (ExpireElement expireElement : this.f8631a.values()) {
            hashSet.add(expireElement.f8629a);
        }
        return hashSet;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> hashSet = new HashSet();
        for (Entry entry : this.f8631a.entrySet()) {
            hashSet.add(new EntryImpl(entry.getKey(), ((ExpireElement) entry.getValue()).f8629a));
        }
        return hashSet;
    }
}
