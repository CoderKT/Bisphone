package org.jxmpp.util.cache;

public interface Cache<K, V> {
    V get(Object obj);

    V put(K k, V v);
}
