package android.support.v4.util;

public class SparseArrayCompat<E> implements Cloneable {
    private static final Object f413a;
    private boolean f414b;
    private int[] f415c;
    private Object[] f416d;
    private int f417e;

    public /* synthetic */ Object clone() {
        return m791a();
    }

    static {
        f413a = new Object();
    }

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int i) {
        this.f414b = false;
        if (i == 0) {
            this.f415c = ContainerHelpers.f381a;
            this.f416d = ContainerHelpers.f383c;
        } else {
            int a = ContainerHelpers.m757a(i);
            this.f415c = new int[a];
            this.f416d = new Object[a];
        }
        this.f417e = 0;
    }

    public SparseArrayCompat<E> m791a() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            try {
                sparseArrayCompat.f415c = (int[]) this.f415c.clone();
                sparseArrayCompat.f416d = (Object[]) this.f416d.clone();
                return sparseArrayCompat;
            } catch (CloneNotSupportedException e) {
                return sparseArrayCompat;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    private void m789d() {
        int i = this.f417e;
        int[] iArr = this.f415c;
        Object[] objArr = this.f416d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f413a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f414b = false;
        this.f417e = i2;
    }

    public int m792b() {
        if (this.f414b) {
            m789d();
        }
        return this.f417e;
    }

    public int m790a(int i) {
        if (this.f414b) {
            m789d();
        }
        return this.f415c[i];
    }

    public E m793b(int i) {
        if (this.f414b) {
            m789d();
        }
        return this.f416d[i];
    }

    public void m794c() {
        int i = this.f417e;
        Object[] objArr = this.f416d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f417e = 0;
        this.f414b = false;
    }

    public String toString() {
        if (m792b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f417e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f417e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(m790a(i));
            stringBuilder.append('=');
            SparseArrayCompat b = m793b(i);
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
