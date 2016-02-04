package com.google.android.gms.internal;

import java.util.Map;

public class zzmi<K, V> {
    static Object[] f6123b;
    static int f6124c;
    static Object[] f6125d;
    static int f6126e;
    int[] f6127f;
    Object[] f6128g;
    int f6129h;

    public zzmi() {
        this.f6127f = zzmf.f6131a;
        this.f6128g = zzmf.f6133c;
        this.f6129h = 0;
    }

    private static void m9281a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (zzme.class) {
                if (f6126e < 10) {
                    objArr[0] = f6125d;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f6125d = objArr;
                    f6126e++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (zzme.class) {
                if (f6124c < 10) {
                    objArr[0] = f6123b;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f6123b = objArr;
                    f6124c++;
                }
            }
        }
    }

    private void m9282e(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (zzme.class) {
                if (f6125d != null) {
                    objArr = f6125d;
                    this.f6128g = objArr;
                    f6125d = (Object[]) objArr[0];
                    this.f6127f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f6126e--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (zzme.class) {
                if (f6123b != null) {
                    objArr = f6123b;
                    this.f6128g = objArr;
                    f6123b = (Object[]) objArr[0];
                    this.f6127f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f6124c--;
                    return;
                }
            }
        }
        this.f6127f = new int[i];
        this.f6128g = new Object[(i << 1)];
    }

    int m9283a() {
        int i = this.f6129h;
        if (i == 0) {
            return -1;
        }
        int a = zzmf.m9292a(this.f6127f, i, 0);
        if (a < 0 || this.f6128g[a << 1] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f6127f[i2] == 0) {
            if (this.f6128g[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        a--;
        while (a >= 0 && this.f6127f[a] == 0) {
            if (this.f6128g[a << 1] == null) {
                return a;
            }
            a--;
        }
        return i2 ^ -1;
    }

    int m9284a(Object obj) {
        int i = 1;
        int i2 = this.f6129h * 2;
        Object[] objArr = this.f6128g;
        if (obj == null) {
            while (i < i2) {
                if (objArr[i] == null) {
                    return i >> 1;
                }
                i += 2;
            }
        } else {
            while (i < i2) {
                if (obj.equals(objArr[i])) {
                    return i >> 1;
                }
                i += 2;
            }
        }
        return -1;
    }

    int m9285a(Object obj, int i) {
        int i2 = this.f6129h;
        if (i2 == 0) {
            return -1;
        }
        int a = zzmf.m9292a(this.f6127f, i2, i);
        if (a < 0 || obj.equals(this.f6128g[a << 1])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f6127f[i3] == i) {
            if (obj.equals(this.f6128g[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        a--;
        while (a >= 0 && this.f6127f[a] == i) {
            if (obj.equals(this.f6128g[a << 1])) {
                return a;
            }
            a--;
        }
        return i3 ^ -1;
    }

    public V m9286a(int i, V v) {
        int i2 = (i << 1) + 1;
        V v2 = this.f6128g[i2];
        this.f6128g[i2] = v;
        return v2;
    }

    public void m9287a(int i) {
        if (this.f6127f.length < i) {
            Object obj = this.f6127f;
            Object obj2 = this.f6128g;
            m9282e(i);
            if (this.f6129h > 0) {
                System.arraycopy(obj, 0, this.f6127f, 0, this.f6129h);
                System.arraycopy(obj2, 0, this.f6128g, 0, this.f6129h << 1);
            }
            m9281a(obj, obj2, this.f6129h);
        }
    }

    public K m9288b(int i) {
        return this.f6128g[i << 1];
    }

    public V m9289c(int i) {
        return this.f6128g[(i << 1) + 1];
    }

    public void clear() {
        if (this.f6129h != 0) {
            m9281a(this.f6127f, this.f6128g, this.f6129h);
            this.f6127f = zzmf.f6131a;
            this.f6128g = zzmf.f6133c;
            this.f6129h = 0;
        }
    }

    public boolean containsKey(Object obj) {
        return obj == null ? m9283a() >= 0 : m9285a(obj, obj.hashCode()) >= 0;
    }

    public boolean containsValue(Object obj) {
        return m9284a(obj) >= 0;
    }

    public V m9290d(int i) {
        int i2 = 8;
        V v = this.f6128g[(i << 1) + 1];
        if (this.f6129h <= 1) {
            m9281a(this.f6127f, this.f6128g, this.f6129h);
            this.f6127f = zzmf.f6131a;
            this.f6128g = zzmf.f6133c;
            this.f6129h = 0;
        } else if (this.f6127f.length <= 8 || this.f6129h >= this.f6127f.length / 3) {
            this.f6129h--;
            if (i < this.f6129h) {
                System.arraycopy(this.f6127f, i + 1, this.f6127f, i, this.f6129h - i);
                System.arraycopy(this.f6128g, (i + 1) << 1, this.f6128g, i << 1, (this.f6129h - i) << 1);
            }
            this.f6128g[this.f6129h << 1] = null;
            this.f6128g[(this.f6129h << 1) + 1] = null;
        } else {
            if (this.f6129h > 8) {
                i2 = this.f6129h + (this.f6129h >> 1);
            }
            Object obj = this.f6127f;
            Object obj2 = this.f6128g;
            m9282e(i2);
            this.f6129h--;
            if (i > 0) {
                System.arraycopy(obj, 0, this.f6127f, 0, i);
                System.arraycopy(obj2, 0, this.f6128g, 0, i << 1);
            }
            if (i < this.f6129h) {
                System.arraycopy(obj, i + 1, this.f6127f, i, this.f6129h - i);
                System.arraycopy(obj2, (i + 1) << 1, this.f6128g, i << 1, (this.f6129h - i) << 1);
            }
        }
        return v;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        int i = 0;
        while (i < this.f6129h) {
            try {
                Object b = m9288b(i);
                Object c = m9289c(i);
                Object obj2 = map.get(b);
                if (c == null) {
                    if (obj2 != null || !map.containsKey(b)) {
                        return false;
                    }
                } else if (!c.equals(obj2)) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public V get(Object obj) {
        int a = obj == null ? m9283a() : m9285a(obj, obj.hashCode());
        return a >= 0 ? this.f6128g[(a << 1) + 1] : null;
    }

    public int hashCode() {
        int[] iArr = this.f6127f;
        Object[] objArr = this.f6128g;
        int i = this.f6129h;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public boolean isEmpty() {
        return this.f6129h <= 0;
    }

    public V put(K k, V v) {
        int a;
        int i;
        int i2 = 8;
        if (k == null) {
            a = m9283a();
            i = 0;
        } else {
            i = k.hashCode();
            a = m9285a((Object) k, i);
        }
        if (a >= 0) {
            int i3 = (a << 1) + 1;
            V v2 = this.f6128g[i3];
            this.f6128g[i3] = v;
            return v2;
        }
        a ^= -1;
        if (this.f6129h >= this.f6127f.length) {
            if (this.f6129h >= 8) {
                i2 = this.f6129h + (this.f6129h >> 1);
            } else if (this.f6129h < 4) {
                i2 = 4;
            }
            Object obj = this.f6127f;
            Object obj2 = this.f6128g;
            m9282e(i2);
            if (this.f6127f.length > 0) {
                System.arraycopy(obj, 0, this.f6127f, 0, obj.length);
                System.arraycopy(obj2, 0, this.f6128g, 0, obj2.length);
            }
            m9281a(obj, obj2, this.f6129h);
        }
        if (a < this.f6129h) {
            System.arraycopy(this.f6127f, a, this.f6127f, a + 1, this.f6129h - a);
            System.arraycopy(this.f6128g, a << 1, this.f6128g, (a + 1) << 1, (this.f6129h - a) << 1);
        }
        this.f6127f[a] = i;
        this.f6128g[a << 1] = k;
        this.f6128g[(a << 1) + 1] = v;
        this.f6129h++;
        return null;
    }

    public V remove(Object obj) {
        int a = obj == null ? m9283a() : m9285a(obj, obj.hashCode());
        return a >= 0 ? m9290d(a) : null;
    }

    public int size() {
        return this.f6129h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f6129h * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f6129h; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            zzmi b = m9288b(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            b = m9289c(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
