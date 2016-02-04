package android.support.v4.util;

public class LongSparseArray<E> implements Cloneable {
    private static final Object f386a;
    private boolean f387b;
    private long[] f388c;
    private Object[] f389d;
    private int f390e;

    public /* synthetic */ Object clone() {
        return m766a();
    }

    static {
        f386a = new Object();
    }

    public LongSparseArray() {
        this(10);
    }

    public LongSparseArray(int i) {
        this.f387b = false;
        if (i == 0) {
            this.f388c = ContainerHelpers.f382b;
            this.f389d = ContainerHelpers.f383c;
        } else {
            int b = ContainerHelpers.m761b(i);
            this.f388c = new long[b];
            this.f389d = new Object[b];
        }
        this.f390e = 0;
    }

    public LongSparseArray<E> m766a() {
        try {
            LongSparseArray<E> longSparseArray = (LongSparseArray) super.clone();
            try {
                longSparseArray.f388c = (long[]) this.f388c.clone();
                longSparseArray.f389d = (Object[]) this.f389d.clone();
                return longSparseArray;
            } catch (CloneNotSupportedException e) {
                return longSparseArray;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public E m767a(long j) {
        return m768a(j, null);
    }

    public E m768a(long j, E e) {
        int a = ContainerHelpers.m759a(this.f388c, this.f390e, j);
        return (a < 0 || this.f389d[a] == f386a) ? e : this.f389d[a];
    }

    public void m769a(int i) {
        if (this.f389d[i] != f386a) {
            this.f389d[i] = f386a;
            this.f387b = true;
        }
    }

    private void m765d() {
        int i = this.f390e;
        long[] jArr = this.f388c;
        Object[] objArr = this.f389d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f386a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f387b = false;
        this.f390e = i2;
    }

    public void m772b(long j, E e) {
        int a = ContainerHelpers.m759a(this.f388c, this.f390e, j);
        if (a >= 0) {
            this.f389d[a] = e;
            return;
        }
        a ^= -1;
        if (a >= this.f390e || this.f389d[a] != f386a) {
            if (this.f387b && this.f390e >= this.f388c.length) {
                m765d();
                a = ContainerHelpers.m759a(this.f388c, this.f390e, j) ^ -1;
            }
            if (this.f390e >= this.f388c.length) {
                int b = ContainerHelpers.m761b(this.f390e + 1);
                Object obj = new long[b];
                Object obj2 = new Object[b];
                System.arraycopy(this.f388c, 0, obj, 0, this.f388c.length);
                System.arraycopy(this.f389d, 0, obj2, 0, this.f389d.length);
                this.f388c = obj;
                this.f389d = obj2;
            }
            if (this.f390e - a != 0) {
                System.arraycopy(this.f388c, a, this.f388c, a + 1, this.f390e - a);
                System.arraycopy(this.f389d, a, this.f389d, a + 1, this.f390e - a);
            }
            this.f388c[a] = j;
            this.f389d[a] = e;
            this.f390e++;
            return;
        }
        this.f388c[a] = j;
        this.f389d[a] = e;
    }

    public int m770b() {
        if (this.f387b) {
            m765d();
        }
        return this.f390e;
    }

    public long m771b(int i) {
        if (this.f387b) {
            m765d();
        }
        return this.f388c[i];
    }

    public E m773c(int i) {
        if (this.f387b) {
            m765d();
        }
        return this.f389d[i];
    }

    public void m774c() {
        int i = this.f390e;
        Object[] objArr = this.f389d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f390e = 0;
        this.f387b = false;
    }

    public String toString() {
        if (m770b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f390e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f390e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(m771b(i));
            stringBuilder.append('=');
            LongSparseArray c = m773c(i);
            if (c != this) {
                stringBuilder.append(c);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
