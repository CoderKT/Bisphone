package android.support.v4.util;

import java.util.Map;

public class SimpleArrayMap<K, V> {
    static Object[] f373b;
    static int f374c;
    static Object[] f375d;
    static int f376e;
    int[] f377f;
    Object[] f378g;
    int f379h;

    int m748a(Object obj, int i) {
        int i2 = this.f379h;
        if (i2 == 0) {
            return -1;
        }
        int a = ContainerHelpers.m758a(this.f377f, i2, i);
        if (a < 0 || obj.equals(this.f378g[a << 1])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f377f[i3] == i) {
            if (obj.equals(this.f378g[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        a--;
        while (a >= 0 && this.f377f[a] == i) {
            if (obj.equals(this.f378g[a << 1])) {
                return a;
            }
            a--;
        }
        return i3 ^ -1;
    }

    int m746a() {
        int i = this.f379h;
        if (i == 0) {
            return -1;
        }
        int a = ContainerHelpers.m758a(this.f377f, i, 0);
        if (a < 0 || this.f378g[a << 1] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f377f[i2] == 0) {
            if (this.f378g[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        a--;
        while (a >= 0 && this.f377f[a] == 0) {
            if (this.f378g[a << 1] == null) {
                return a;
            }
            a--;
        }
        return i2 ^ -1;
    }

    private void m745e(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (ArrayMap.class) {
                if (f375d != null) {
                    objArr = f375d;
                    this.f378g = objArr;
                    f375d = (Object[]) objArr[0];
                    this.f377f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f376e--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArrayMap.class) {
                if (f373b != null) {
                    objArr = f373b;
                    this.f378g = objArr;
                    f373b = (Object[]) objArr[0];
                    this.f377f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f374c--;
                    return;
                }
            }
        }
        this.f377f = new int[i];
        this.f378g = new Object[(i << 1)];
    }

    private static void m744a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (ArrayMap.class) {
                if (f376e < 10) {
                    objArr[0] = f375d;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f375d = objArr;
                    f376e++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArrayMap.class) {
                if (f374c < 10) {
                    objArr[0] = f373b;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f373b = objArr;
                    f374c++;
                }
            }
        }
    }

    public SimpleArrayMap() {
        this.f377f = ContainerHelpers.f381a;
        this.f378g = ContainerHelpers.f383c;
        this.f379h = 0;
    }

    public SimpleArrayMap(int i) {
        if (i == 0) {
            this.f377f = ContainerHelpers.f381a;
            this.f378g = ContainerHelpers.f383c;
        } else {
            m745e(i);
        }
        this.f379h = 0;
    }

    public void clear() {
        if (this.f379h != 0) {
            m744a(this.f377f, this.f378g, this.f379h);
            this.f377f = ContainerHelpers.f381a;
            this.f378g = ContainerHelpers.f383c;
            this.f379h = 0;
        }
    }

    public void m750a(int i) {
        if (this.f377f.length < i) {
            Object obj = this.f377f;
            Object obj2 = this.f378g;
            m745e(i);
            if (this.f379h > 0) {
                System.arraycopy(obj, 0, this.f377f, 0, this.f379h);
                System.arraycopy(obj2, 0, this.f378g, 0, this.f379h << 1);
            }
            m744a(obj, obj2, this.f379h);
        }
    }

    public boolean containsKey(Object obj) {
        return m747a(obj) >= 0;
    }

    public int m747a(Object obj) {
        return obj == null ? m746a() : m748a(obj, obj.hashCode());
    }

    int m751b(Object obj) {
        int i = 1;
        int i2 = this.f379h * 2;
        Object[] objArr = this.f378g;
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

    public boolean containsValue(Object obj) {
        return m751b(obj) >= 0;
    }

    public V get(Object obj) {
        int a = m747a(obj);
        return a >= 0 ? this.f378g[(a << 1) + 1] : null;
    }

    public K m752b(int i) {
        return this.f378g[i << 1];
    }

    public V m753c(int i) {
        return this.f378g[(i << 1) + 1];
    }

    public V m749a(int i, V v) {
        int i2 = (i << 1) + 1;
        V v2 = this.f378g[i2];
        this.f378g[i2] = v;
        return v2;
    }

    public boolean isEmpty() {
        return this.f379h <= 0;
    }

    public V put(K k, V v) {
        int a;
        int i;
        int i2 = 8;
        if (k == null) {
            a = m746a();
            i = 0;
        } else {
            i = k.hashCode();
            a = m748a((Object) k, i);
        }
        if (a >= 0) {
            int i3 = (a << 1) + 1;
            V v2 = this.f378g[i3];
            this.f378g[i3] = v;
            return v2;
        }
        a ^= -1;
        if (this.f379h >= this.f377f.length) {
            if (this.f379h >= 8) {
                i2 = this.f379h + (this.f379h >> 1);
            } else if (this.f379h < 4) {
                i2 = 4;
            }
            Object obj = this.f377f;
            Object obj2 = this.f378g;
            m745e(i2);
            if (this.f377f.length > 0) {
                System.arraycopy(obj, 0, this.f377f, 0, obj.length);
                System.arraycopy(obj2, 0, this.f378g, 0, obj2.length);
            }
            m744a(obj, obj2, this.f379h);
        }
        if (a < this.f379h) {
            System.arraycopy(this.f377f, a, this.f377f, a + 1, this.f379h - a);
            System.arraycopy(this.f378g, a << 1, this.f378g, (a + 1) << 1, (this.f379h - a) << 1);
        }
        this.f377f[a] = i;
        this.f378g[a << 1] = k;
        this.f378g[(a << 1) + 1] = v;
        this.f379h++;
        return null;
    }

    public V remove(Object obj) {
        int a = m747a(obj);
        if (a >= 0) {
            return m754d(a);
        }
        return null;
    }

    public V m754d(int i) {
        int i2 = 8;
        V v = this.f378g[(i << 1) + 1];
        if (this.f379h <= 1) {
            m744a(this.f377f, this.f378g, this.f379h);
            this.f377f = ContainerHelpers.f381a;
            this.f378g = ContainerHelpers.f383c;
            this.f379h = 0;
        } else if (this.f377f.length <= 8 || this.f379h >= this.f377f.length / 3) {
            this.f379h--;
            if (i < this.f379h) {
                System.arraycopy(this.f377f, i + 1, this.f377f, i, this.f379h - i);
                System.arraycopy(this.f378g, (i + 1) << 1, this.f378g, i << 1, (this.f379h - i) << 1);
            }
            this.f378g[this.f379h << 1] = null;
            this.f378g[(this.f379h << 1) + 1] = null;
        } else {
            if (this.f379h > 8) {
                i2 = this.f379h + (this.f379h >> 1);
            }
            Object obj = this.f377f;
            Object obj2 = this.f378g;
            m745e(i2);
            this.f379h--;
            if (i > 0) {
                System.arraycopy(obj, 0, this.f377f, 0, i);
                System.arraycopy(obj2, 0, this.f378g, 0, i << 1);
            }
            if (i < this.f379h) {
                System.arraycopy(obj, i + 1, this.f377f, i, this.f379h - i);
                System.arraycopy(obj2, (i + 1) << 1, this.f378g, i << 1, (this.f379h - i) << 1);
            }
        }
        return v;
    }

    public int size() {
        return this.f379h;
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
        while (i < this.f379h) {
            try {
                Object b = m752b(i);
                Object c = m753c(i);
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

    public int hashCode() {
        int[] iArr = this.f377f;
        Object[] objArr = this.f378g;
        int i = this.f379h;
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

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f379h * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f379h; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            SimpleArrayMap b = m752b(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            b = m753c(i);
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
