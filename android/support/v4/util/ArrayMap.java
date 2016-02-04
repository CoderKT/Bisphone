package android.support.v4.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    MapCollections<K, V> f380a;

    /* renamed from: android.support.v4.util.ArrayMap.1 */
    class C00151 extends MapCollections<K, V> {
        final /* synthetic */ ArrayMap f372a;

        C00151(ArrayMap arrayMap) {
            this.f372a = arrayMap;
        }

        protected int m735a() {
            return this.f372a.h;
        }

        protected Object m737a(int i, int i2) {
            return this.f372a.g[(i << 1) + i2];
        }

        protected int m736a(Object obj) {
            return this.f372a.m747a(obj);
        }

        protected int m741b(Object obj) {
            return this.f372a.m751b(obj);
        }

        protected Map<K, V> m742b() {
            return this.f372a;
        }

        protected void m740a(K k, V v) {
            this.f372a.put(k, v);
        }

        protected V m738a(int i, V v) {
            return this.f372a.m749a(i, (Object) v);
        }

        protected void m739a(int i) {
            this.f372a.m754d(i);
        }

        protected void m743c() {
            this.f372a.clear();
        }
    }

    private MapCollections<K, V> m755b() {
        if (this.f380a == null) {
            this.f380a = new C00151(this);
        }
        return this.f380a;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        m750a(this.h + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean m756a(Collection<?> collection) {
        return MapCollections.m720c(this, collection);
    }

    public Set<Entry<K, V>> entrySet() {
        return m755b().m732d();
    }

    public Set<K> keySet() {
        return m755b().m733e();
    }

    public Collection<V> values() {
        return m755b().m734f();
    }
}
