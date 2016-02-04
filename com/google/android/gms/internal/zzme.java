package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzme<K, V> extends zzmi<K, V> implements Map<K, V> {
    zzmh<K, V> f6130a;

    /* renamed from: com.google.android.gms.internal.zzme.1 */
    class C08681 extends zzmh<K, V> {
        final /* synthetic */ zzme f6122a;

        C08681(zzme com_google_android_gms_internal_zzme) {
            this.f6122a = com_google_android_gms_internal_zzme;
        }

        protected int m9272a() {
            return this.f6122a.h;
        }

        protected int m9273a(Object obj) {
            return obj == null ? this.f6122a.m9283a() : this.f6122a.m9285a(obj, obj.hashCode());
        }

        protected Object m9274a(int i, int i2) {
            return this.f6122a.g[(i << 1) + i2];
        }

        protected V m9275a(int i, V v) {
            return this.f6122a.m9286a(i, (Object) v);
        }

        protected void m9276a(int i) {
            this.f6122a.m9290d(i);
        }

        protected void m9277a(K k, V v) {
            this.f6122a.put(k, v);
        }

        protected int m9278b(Object obj) {
            return this.f6122a.m9284a(obj);
        }

        protected Map<K, V> m9279b() {
            return this.f6122a;
        }

        protected void m9280c() {
            this.f6122a.clear();
        }
    }

    private zzmh<K, V> m9291b() {
        if (this.f6130a == null) {
            this.f6130a = new C08681(this);
        }
        return this.f6130a;
    }

    public Set<Entry<K, V>> entrySet() {
        return m9291b().m9269d();
    }

    public Set<K> keySet() {
        return m9291b().m9270e();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        m9287a(this.h + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        return m9291b().m9271f();
    }
}
