package android.support.v7.widget;

class RtlSpacingHelper {
    private int f1711a;
    private int f1712b;
    private int f1713c;
    private int f1714d;
    private int f1715e;
    private int f1716f;
    private boolean f1717g;
    private boolean f1718h;

    RtlSpacingHelper() {
        this.f1711a = 0;
        this.f1712b = 0;
        this.f1713c = Integer.MIN_VALUE;
        this.f1714d = Integer.MIN_VALUE;
        this.f1715e = 0;
        this.f1716f = 0;
        this.f1717g = false;
        this.f1718h = false;
    }

    public int m3648a() {
        return this.f1711a;
    }

    public int m3651b() {
        return this.f1712b;
    }

    public int m3653c() {
        return this.f1717g ? this.f1712b : this.f1711a;
    }

    public int m3654d() {
        return this.f1717g ? this.f1711a : this.f1712b;
    }

    public void m3649a(int i, int i2) {
        this.f1713c = i;
        this.f1714d = i2;
        this.f1718h = true;
        if (this.f1717g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f1711a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f1712b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f1711a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1712b = i2;
        }
    }

    public void m3652b(int i, int i2) {
        this.f1718h = false;
        if (i != Integer.MIN_VALUE) {
            this.f1715e = i;
            this.f1711a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1716f = i2;
            this.f1712b = i2;
        }
    }

    public void m3650a(boolean z) {
        if (z != this.f1717g) {
            this.f1717g = z;
            if (!this.f1718h) {
                this.f1711a = this.f1715e;
                this.f1712b = this.f1716f;
            } else if (z) {
                this.f1711a = this.f1714d != Integer.MIN_VALUE ? this.f1714d : this.f1715e;
                this.f1712b = this.f1713c != Integer.MIN_VALUE ? this.f1713c : this.f1716f;
            } else {
                this.f1711a = this.f1713c != Integer.MIN_VALUE ? this.f1713c : this.f1715e;
                this.f1712b = this.f1714d != Integer.MIN_VALUE ? this.f1714d : this.f1716f;
            }
        }
    }
}
