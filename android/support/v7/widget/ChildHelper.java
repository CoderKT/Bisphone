package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ChildHelper {
    final Callback f1448a;
    final Bucket f1449b;
    final List<View> f1450c;

    class Bucket {
        long f1446a;
        Bucket f1447b;

        Bucket() {
            this.f1446a = 0;
        }

        void m2858a(int i) {
            if (i >= 64) {
                m2856b();
                this.f1447b.m2858a(i - 64);
                return;
            }
            this.f1446a |= 1 << i;
        }

        private void m2856b() {
            if (this.f1447b == null) {
                this.f1447b = new Bucket();
            }
        }

        void m2860b(int i) {
            if (i < 64) {
                this.f1446a &= (1 << i) ^ -1;
            } else if (this.f1447b != null) {
                this.f1447b.m2860b(i - 64);
            }
        }

        boolean m2861c(int i) {
            if (i < 64) {
                return (this.f1446a & (1 << i)) != 0;
            } else {
                m2856b();
                return this.f1447b.m2861c(i - 64);
            }
        }

        void m2857a() {
            this.f1446a = 0;
            if (this.f1447b != null) {
                this.f1447b.m2857a();
            }
        }

        void m2859a(int i, boolean z) {
            if (i >= 64) {
                m2856b();
                this.f1447b.m2859a(i - 64, z);
                return;
            }
            boolean z2 = (this.f1446a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            this.f1446a = (((j ^ -1) & this.f1446a) << 1) | (this.f1446a & j);
            if (z) {
                m2858a(i);
            } else {
                m2860b(i);
            }
            if (z2 || this.f1447b != null) {
                m2856b();
                this.f1447b.m2859a(0, z2);
            }
        }

        boolean m2862d(int i) {
            if (i >= 64) {
                m2856b();
                return this.f1447b.m2862d(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.f1446a & j) != 0;
            this.f1446a &= j ^ -1;
            j--;
            this.f1446a = Long.rotateRight((j ^ -1) & this.f1446a, 1) | (this.f1446a & j);
            if (this.f1447b == null) {
                return z;
            }
            if (this.f1447b.m2861c(0)) {
                m2858a(63);
            }
            this.f1447b.m2862d(0);
            return z;
        }

        int m2863e(int i) {
            if (this.f1447b == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f1446a);
                }
                return Long.bitCount(this.f1446a & ((1 << i) - 1));
            } else if (i < 64) {
                return Long.bitCount(this.f1446a & ((1 << i) - 1));
            } else {
                return this.f1447b.m2863e(i - 64) + Long.bitCount(this.f1446a);
            }
        }

        public String toString() {
            return this.f1447b == null ? Long.toBinaryString(this.f1446a) : this.f1447b.toString() + "xx" + Long.toBinaryString(this.f1446a);
        }
    }

    interface Callback {
        int m2864a();

        int m2865a(View view);

        void m2866a(int i);

        void m2867a(View view, int i);

        void m2868a(View view, int i, LayoutParams layoutParams);

        ViewHolder m2869b(View view);

        View m2870b(int i);

        void m2871b();

        void m2872c(int i);

        void m2873c(View view);

        void m2874d(View view);
    }

    ChildHelper(Callback callback) {
        this.f1448a = callback;
        this.f1449b = new Bucket();
        this.f1450c = new ArrayList();
    }

    private void m2876g(View view) {
        this.f1450c.add(view);
        this.f1448a.m2873c(view);
    }

    private boolean m2877h(View view) {
        if (!this.f1450c.remove(view)) {
            return false;
        }
        this.f1448a.m2874d(view);
        return true;
    }

    void m2884a(View view, boolean z) {
        m2883a(view, -1, z);
    }

    void m2883a(View view, int i, boolean z) {
        int a;
        if (i < 0) {
            a = this.f1448a.m2864a();
        } else {
            a = m2875e(i);
        }
        this.f1449b.m2859a(a, z);
        if (z) {
            m2876g(view);
        }
        this.f1448a.m2867a(view, a);
    }

    private int m2875e(int i) {
        if (i < 0) {
            return -1;
        }
        int a = this.f1448a.m2864a();
        int i2 = i;
        while (i2 < a) {
            int e = i - (i2 - this.f1449b.m2863e(i2));
            if (e == 0) {
                while (this.f1449b.m2861c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e;
        }
        return -1;
    }

    void m2881a(View view) {
        int a = this.f1448a.m2865a(view);
        if (a >= 0) {
            if (this.f1449b.m2862d(a)) {
                m2877h(view);
            }
            this.f1448a.m2866a(a);
        }
    }

    void m2880a(int i) {
        int e = m2875e(i);
        View b = this.f1448a.m2870b(e);
        if (b != null) {
            if (this.f1449b.m2862d(e)) {
                m2877h(b);
            }
            this.f1448a.m2866a(e);
        }
    }

    View m2887b(int i) {
        return this.f1448a.m2870b(m2875e(i));
    }

    void m2879a() {
        this.f1449b.m2857a();
        for (int size = this.f1450c.size() - 1; size >= 0; size--) {
            this.f1448a.m2874d((View) this.f1450c.get(size));
            this.f1450c.remove(size);
        }
        this.f1448a.m2871b();
    }

    View m2878a(int i, int i2) {
        int size = this.f1450c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) this.f1450c.get(i3);
            ViewHolder b = this.f1448a.m2869b(view);
            if (b.m3497d() == i && !b.m3507n() && !b.m3510q() && (i2 == -1 || b.m3501h() == i2)) {
                return view;
            }
        }
        return null;
    }

    void m2882a(View view, int i, LayoutParams layoutParams, boolean z) {
        int a;
        if (i < 0) {
            a = this.f1448a.m2864a();
        } else {
            a = m2875e(i);
        }
        this.f1449b.m2859a(a, z);
        if (z) {
            m2876g(view);
        }
        this.f1448a.m2868a(view, a, layoutParams);
    }

    int m2885b() {
        return this.f1448a.m2864a() - this.f1450c.size();
    }

    int m2888c() {
        return this.f1448a.m2864a();
    }

    View m2889c(int i) {
        return this.f1448a.m2870b(i);
    }

    void m2891d(int i) {
        int e = m2875e(i);
        this.f1449b.m2862d(e);
        this.f1448a.m2872c(e);
    }

    int m2886b(View view) {
        int a = this.f1448a.m2865a(view);
        if (a == -1 || this.f1449b.m2861c(a)) {
            return -1;
        }
        return a - this.f1449b.m2863e(a);
    }

    boolean m2890c(View view) {
        return this.f1450c.contains(view);
    }

    void m2892d(View view) {
        int a = this.f1448a.m2865a(view);
        if (a < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.f1449b.m2858a(a);
        m2876g(view);
    }

    void m2893e(View view) {
        int a = this.f1448a.m2865a(view);
        if (a < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.f1449b.m2861c(a)) {
            this.f1449b.m2860b(a);
            m2877h(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f1449b.toString() + ", hidden list:" + this.f1450c.size();
    }

    boolean m2894f(View view) {
        int a = this.f1448a.m2865a(view);
        if (a == -1) {
            return m2877h(view) ? true : true;
        } else {
            if (!this.f1449b.m2861c(a)) {
                return false;
            }
            this.f1449b.m2862d(a);
            if (m2877h(view)) {
                this.f1448a.m2866a(a);
            } else {
                this.f1448a.m2866a(a);
            }
            return true;
        }
    }
}
