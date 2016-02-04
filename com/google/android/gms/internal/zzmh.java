package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class zzmh<K, V> {
    zzb f6119b;
    zzc f6120c;
    zze f6121d;

    final class zza<T> implements Iterator<T> {
        final int f6134a;
        int f6135b;
        int f6136c;
        boolean f6137d;
        final /* synthetic */ zzmh f6138e;

        zza(zzmh com_google_android_gms_internal_zzmh, int i) {
            this.f6138e = com_google_android_gms_internal_zzmh;
            this.f6137d = false;
            this.f6134a = i;
            this.f6135b = com_google_android_gms_internal_zzmh.m9258a();
        }

        public boolean hasNext() {
            return this.f6136c < this.f6135b;
        }

        public T next() {
            T a = this.f6138e.m9260a(this.f6136c, this.f6134a);
            this.f6136c++;
            this.f6137d = true;
            return a;
        }

        public void remove() {
            if (this.f6137d) {
                this.f6136c--;
                this.f6135b--;
                this.f6137d = false;
                this.f6138e.m9262a(this.f6136c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class zzb implements Set<Entry<K, V>> {
        final /* synthetic */ zzmh f6139a;

        zzb(zzmh com_google_android_gms_internal_zzmh) {
            this.f6139a = com_google_android_gms_internal_zzmh;
        }

        public boolean m9294a(Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ boolean add(Object obj) {
            return m9294a((Entry) obj);
        }

        public boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int a = this.f6139a.m9258a();
            for (Entry entry : collection) {
                this.f6139a.m9263a(entry.getKey(), entry.getValue());
            }
            return a != this.f6139a.m9258a();
        }

        public void clear() {
            this.f6139a.m9268c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            int a = this.f6139a.m9259a(entry.getKey());
            return a >= 0 ? zzmf.m9293a(this.f6139a.m9260a(a, 1), entry.getValue()) : false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return zzmh.m9255a((Set) this, obj);
        }

        public int hashCode() {
            int a = this.f6139a.m9258a() - 1;
            int i = 0;
            while (a >= 0) {
                Object a2 = this.f6139a.m9260a(a, 0);
                Object a3 = this.f6139a.m9260a(a, 1);
                a--;
                i += (a3 == null ? 0 : a3.hashCode()) ^ (a2 == null ? 0 : a2.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f6139a.m9258a() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new zzd(this.f6139a);
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.f6139a.m9258a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    final class zzc implements Set<K> {
        final /* synthetic */ zzmh f6140a;

        zzc(zzmh com_google_android_gms_internal_zzmh) {
            this.f6140a = com_google_android_gms_internal_zzmh;
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f6140a.m9268c();
        }

        public boolean contains(Object obj) {
            return this.f6140a.m9259a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return zzmh.m9254a(this.f6140a.m9266b(), (Collection) collection);
        }

        public boolean equals(Object obj) {
            return zzmh.m9255a((Set) this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a = this.f6140a.m9258a() - 1; a >= 0; a--) {
                Object a2 = this.f6140a.m9260a(a, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f6140a.m9258a() == 0;
        }

        public Iterator<K> iterator() {
            return new zza(this.f6140a, 0);
        }

        public boolean remove(Object obj) {
            int a = this.f6140a.m9259a(obj);
            if (a < 0) {
                return false;
            }
            this.f6140a.m9262a(a);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return zzmh.m9256b(this.f6140a.m9266b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return zzmh.m9257c(this.f6140a.m9266b(), collection);
        }

        public int size() {
            return this.f6140a.m9258a();
        }

        public Object[] toArray() {
            return this.f6140a.m9267b(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f6140a.m9264a((Object[]) tArr, 0);
        }
    }

    final class zzd implements Iterator<Entry<K, V>>, Entry<K, V> {
        int f6141a;
        int f6142b;
        boolean f6143c;
        final /* synthetic */ zzmh f6144d;

        zzd(zzmh com_google_android_gms_internal_zzmh) {
            this.f6144d = com_google_android_gms_internal_zzmh;
            this.f6143c = false;
            this.f6141a = com_google_android_gms_internal_zzmh.m9258a() - 1;
            this.f6142b = -1;
        }

        public Entry<K, V> m9295a() {
            this.f6142b++;
            this.f6143c = true;
            return this;
        }

        public final boolean equals(Object obj) {
            boolean z = true;
            if (!this.f6143c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Entry)) {
                return false;
            } else {
                Entry entry = (Entry) obj;
                if (!(zzmf.m9293a(entry.getKey(), this.f6144d.m9260a(this.f6142b, 0)) && zzmf.m9293a(entry.getValue(), this.f6144d.m9260a(this.f6142b, 1)))) {
                    z = false;
                }
                return z;
            }
        }

        public K getKey() {
            if (this.f6143c) {
                return this.f6144d.m9260a(this.f6142b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.f6143c) {
                return this.f6144d.m9260a(this.f6142b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.f6142b < this.f6141a;
        }

        public final int hashCode() {
            int i = 0;
            if (this.f6143c) {
                Object a = this.f6144d.m9260a(this.f6142b, 0);
                Object a2 = this.f6144d.m9260a(this.f6142b, 1);
                int hashCode = a == null ? 0 : a.hashCode();
                if (a2 != null) {
                    i = a2.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public /* synthetic */ Object next() {
            return m9295a();
        }

        public void remove() {
            if (this.f6143c) {
                this.f6144d.m9262a(this.f6142b);
                this.f6142b--;
                this.f6141a--;
                this.f6143c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.f6143c) {
                return this.f6144d.m9261a(this.f6142b, (Object) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class zze implements Collection<V> {
        final /* synthetic */ zzmh f6145a;

        zze(zzmh com_google_android_gms_internal_zzmh) {
            this.f6145a = com_google_android_gms_internal_zzmh;
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f6145a.m9268c();
        }

        public boolean contains(Object obj) {
            return this.f6145a.m9265b(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.f6145a.m9258a() == 0;
        }

        public Iterator<V> iterator() {
            return new zza(this.f6145a, 1);
        }

        public boolean remove(Object obj) {
            int b = this.f6145a.m9265b(obj);
            if (b < 0) {
                return false;
            }
            this.f6145a.m9262a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int i = 0;
            int a = this.f6145a.m9258a();
            boolean z = false;
            while (i < a) {
                if (collection.contains(this.f6145a.m9260a(i, 1))) {
                    this.f6145a.m9262a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int i = 0;
            int a = this.f6145a.m9258a();
            boolean z = false;
            while (i < a) {
                if (!collection.contains(this.f6145a.m9260a(i, 1))) {
                    this.f6145a.m9262a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return this.f6145a.m9258a();
        }

        public Object[] toArray() {
            return this.f6145a.m9267b(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f6145a.m9264a((Object[]) tArr, 1);
        }
    }

    zzmh() {
    }

    public static <K, V> boolean m9254a(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean m9255a(Set<T> set, Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public static <K, V> boolean m9256b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean m9257c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract int m9258a();

    protected abstract int m9259a(Object obj);

    protected abstract Object m9260a(int i, int i2);

    protected abstract V m9261a(int i, V v);

    protected abstract void m9262a(int i);

    protected abstract void m9263a(K k, V v);

    public <T> T[] m9264a(T[] tArr, int i) {
        int a = m9258a();
        T[] tArr2 = tArr.length < a ? (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a) : tArr;
        for (int i2 = 0; i2 < a; i2++) {
            tArr2[i2] = m9260a(i2, i);
        }
        if (tArr2.length > a) {
            tArr2[a] = null;
        }
        return tArr2;
    }

    protected abstract int m9265b(Object obj);

    protected abstract Map<K, V> m9266b();

    public Object[] m9267b(int i) {
        int a = m9258a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = m9260a(i2, i);
        }
        return objArr;
    }

    protected abstract void m9268c();

    public Set<Entry<K, V>> m9269d() {
        if (this.f6119b == null) {
            this.f6119b = new zzb(this);
        }
        return this.f6119b;
    }

    public Set<K> m9270e() {
        if (this.f6120c == null) {
            this.f6120c = new zzc(this);
        }
        return this.f6120c;
    }

    public Collection<V> m9271f() {
        if (this.f6121d == null) {
            this.f6121d = new zze(this);
        }
        return this.f6121d;
    }
}
