package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class MapCollections<K, V> {
    EntrySet f369b;
    KeySet f370c;
    ValuesCollection f371d;

    final class ArrayIterator<T> implements Iterator<T> {
        final int f399a;
        int f400b;
        int f401c;
        boolean f402d;
        final /* synthetic */ MapCollections f403e;

        ArrayIterator(MapCollections mapCollections, int i) {
            this.f403e = mapCollections;
            this.f402d = false;
            this.f399a = i;
            this.f400b = mapCollections.m721a();
        }

        public boolean hasNext() {
            return this.f401c < this.f400b;
        }

        public T next() {
            T a = this.f403e.m723a(this.f401c, this.f399a);
            this.f401c++;
            this.f402d = true;
            return a;
        }

        public void remove() {
            if (this.f402d) {
                this.f401c--;
                this.f400b--;
                this.f402d = false;
                this.f403e.m725a(this.f401c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class EntrySet implements Set<Entry<K, V>> {
        final /* synthetic */ MapCollections f404a;

        EntrySet(MapCollections mapCollections) {
            this.f404a = mapCollections;
        }

        public /* synthetic */ boolean add(Object obj) {
            return m782a((Entry) obj);
        }

        public boolean m782a(Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int a = this.f404a.m721a();
            for (Entry entry : collection) {
                this.f404a.m726a(entry.getKey(), entry.getValue());
            }
            return a != this.f404a.m721a();
        }

        public void clear() {
            this.f404a.m731c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            int a = this.f404a.m722a(entry.getKey());
            if (a >= 0) {
                return ContainerHelpers.m760a(this.f404a.m723a(a, 1), entry.getValue());
            }
            return false;
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
            return this.f404a.m721a() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new MapIterator(this.f404a);
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
            return this.f404a.m721a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            return MapCollections.m718a((Set) this, obj);
        }

        public int hashCode() {
            int a = this.f404a.m721a() - 1;
            int i = 0;
            while (a >= 0) {
                Object a2 = this.f404a.m723a(a, 0);
                Object a3 = this.f404a.m723a(a, 1);
                a--;
                i += (a3 == null ? 0 : a3.hashCode()) ^ (a2 == null ? 0 : a2.hashCode());
            }
            return i;
        }
    }

    final class KeySet implements Set<K> {
        final /* synthetic */ MapCollections f405a;

        KeySet(MapCollections mapCollections) {
            this.f405a = mapCollections;
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f405a.m731c();
        }

        public boolean contains(Object obj) {
            return this.f405a.m722a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return MapCollections.m717a(this.f405a.m729b(), (Collection) collection);
        }

        public boolean isEmpty() {
            return this.f405a.m721a() == 0;
        }

        public Iterator<K> iterator() {
            return new ArrayIterator(this.f405a, 0);
        }

        public boolean remove(Object obj) {
            int a = this.f405a.m722a(obj);
            if (a < 0) {
                return false;
            }
            this.f405a.m725a(a);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return MapCollections.m719b(this.f405a.m729b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return MapCollections.m720c(this.f405a.m729b(), collection);
        }

        public int size() {
            return this.f405a.m721a();
        }

        public Object[] toArray() {
            return this.f405a.m730b(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f405a.m727a((Object[]) tArr, 0);
        }

        public boolean equals(Object obj) {
            return MapCollections.m718a((Set) this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a = this.f405a.m721a() - 1; a >= 0; a--) {
                Object a2 = this.f405a.m723a(a, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }
    }

    final class MapIterator implements Iterator<Entry<K, V>>, Entry<K, V> {
        int f406a;
        int f407b;
        boolean f408c;
        final /* synthetic */ MapCollections f409d;

        public /* synthetic */ Object next() {
            return m783a();
        }

        MapIterator(MapCollections mapCollections) {
            this.f409d = mapCollections;
            this.f408c = false;
            this.f406a = mapCollections.m721a() - 1;
            this.f407b = -1;
        }

        public boolean hasNext() {
            return this.f407b < this.f406a;
        }

        public Entry<K, V> m783a() {
            this.f407b++;
            this.f408c = true;
            return this;
        }

        public void remove() {
            if (this.f408c) {
                this.f409d.m725a(this.f407b);
                this.f407b--;
                this.f406a--;
                this.f408c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public K getKey() {
            if (this.f408c) {
                return this.f409d.m723a(this.f407b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.f408c) {
                return this.f409d.m723a(this.f407b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V setValue(V v) {
            if (this.f408c) {
                return this.f409d.m724a(this.f407b, (Object) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final boolean equals(Object obj) {
            boolean z = true;
            if (!this.f408c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Entry)) {
                return false;
            } else {
                Entry entry = (Entry) obj;
                if (!(ContainerHelpers.m760a(entry.getKey(), this.f409d.m723a(this.f407b, 0)) && ContainerHelpers.m760a(entry.getValue(), this.f409d.m723a(this.f407b, 1)))) {
                    z = false;
                }
                return z;
            }
        }

        public final int hashCode() {
            int i = 0;
            if (this.f408c) {
                Object a = this.f409d.m723a(this.f407b, 0);
                Object a2 = this.f409d.m723a(this.f407b, 1);
                int hashCode = a == null ? 0 : a.hashCode();
                if (a2 != null) {
                    i = a2.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class ValuesCollection implements Collection<V> {
        final /* synthetic */ MapCollections f410a;

        ValuesCollection(MapCollections mapCollections) {
            this.f410a = mapCollections;
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f410a.m731c();
        }

        public boolean contains(Object obj) {
            return this.f410a.m728b(obj) >= 0;
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
            return this.f410a.m721a() == 0;
        }

        public Iterator<V> iterator() {
            return new ArrayIterator(this.f410a, 1);
        }

        public boolean remove(Object obj) {
            int b = this.f410a.m728b(obj);
            if (b < 0) {
                return false;
            }
            this.f410a.m725a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int i = 0;
            int a = this.f410a.m721a();
            boolean z = false;
            while (i < a) {
                if (collection.contains(this.f410a.m723a(i, 1))) {
                    this.f410a.m725a(i);
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
            int a = this.f410a.m721a();
            boolean z = false;
            while (i < a) {
                if (!collection.contains(this.f410a.m723a(i, 1))) {
                    this.f410a.m725a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return this.f410a.m721a();
        }

        public Object[] toArray() {
            return this.f410a.m730b(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f410a.m727a((Object[]) tArr, 1);
        }
    }

    protected abstract int m721a();

    protected abstract int m722a(Object obj);

    protected abstract Object m723a(int i, int i2);

    protected abstract V m724a(int i, V v);

    protected abstract void m725a(int i);

    protected abstract void m726a(K k, V v);

    protected abstract int m728b(Object obj);

    protected abstract Map<K, V> m729b();

    protected abstract void m731c();

    MapCollections() {
    }

    public static <K, V> boolean m717a(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> boolean m719b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean m720c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public Object[] m730b(int i) {
        int a = m721a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = m723a(i2, i);
        }
        return objArr;
    }

    public <T> T[] m727a(T[] tArr, int i) {
        T[] tArr2;
        int a = m721a();
        if (tArr.length < a) {
            tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a);
        } else {
            tArr2 = tArr;
        }
        for (int i2 = 0; i2 < a; i2++) {
            tArr2[i2] = m723a(i2, i);
        }
        if (tArr2.length > a) {
            tArr2[a] = null;
        }
        return tArr2;
    }

    public static <T> boolean m718a(Set<T> set, Object obj) {
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

    public Set<Entry<K, V>> m732d() {
        if (this.f369b == null) {
            this.f369b = new EntrySet(this);
        }
        return this.f369b;
    }

    public Set<K> m733e() {
        if (this.f370c == null) {
            this.f370c = new KeySet(this);
        }
        return this.f370c;
    }

    public Collection<V> m734f() {
        if (this.f371d == null) {
            this.f371d = new ValuesCollection(this);
        }
        return this.f371d;
    }
}
