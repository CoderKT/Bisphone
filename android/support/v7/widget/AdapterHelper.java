package android.support.v7.widget;

import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

class AdapterHelper implements Callback {
    final ArrayList<UpdateOp> f1317a;
    final ArrayList<UpdateOp> f1318b;
    final Callback f1319c;
    Runnable f1320d;
    final boolean f1321e;
    final OpReorderer f1322f;
    private Pool<UpdateOp> f1323g;
    private int f1324h;

    interface Callback {
        ViewHolder m2734a(int i);

        void m2735a(int i, int i2);

        void m2736a(int i, int i2, Object obj);

        void m2737a(UpdateOp updateOp);

        void m2738b(int i, int i2);

        void m2739b(UpdateOp updateOp);

        void m2740c(int i, int i2);

        void m2741d(int i, int i2);
    }

    class UpdateOp {
        int f1313a;
        int f1314b;
        Object f1315c;
        int f1316d;

        UpdateOp(int i, int i2, int i3, Object obj) {
            this.f1313a = i;
            this.f1314b = i2;
            this.f1316d = i3;
            this.f1315c = obj;
        }

        String m2742a() {
            switch (this.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return "add";
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return "rm";
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    return "up";
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    return "mv";
                default:
                    return "??";
            }
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + m2742a() + ",s:" + this.f1314b + "c:" + this.f1316d + ",p:" + this.f1315c + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            if (this.f1313a != updateOp.f1313a) {
                return false;
            }
            if (this.f1313a == 8 && Math.abs(this.f1316d - this.f1314b) == 1 && this.f1316d == updateOp.f1314b && this.f1314b == updateOp.f1316d) {
                return true;
            }
            if (this.f1316d != updateOp.f1316d) {
                return false;
            }
            if (this.f1314b != updateOp.f1314b) {
                return false;
            }
            if (this.f1315c != null) {
                if (this.f1315c.equals(updateOp.f1315c)) {
                    return true;
                }
                return false;
            } else if (updateOp.f1315c != null) {
                return false;
            } else {
                return true;
            }
        }

        public int hashCode() {
            return (((this.f1313a * 31) + this.f1314b) * 31) + this.f1316d;
        }
    }

    AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean z) {
        this.f1323g = new SimplePool(30);
        this.f1317a = new ArrayList();
        this.f1318b = new ArrayList();
        this.f1324h = 0;
        this.f1319c = callback;
        this.f1321e = z;
        this.f1322f = new OpReorderer(this);
    }

    void m2755a() {
        m2758a(this.f1317a);
        m2758a(this.f1318b);
        this.f1324h = 0;
    }

    void m2762b() {
        this.f1322f.m3262a(this.f1317a);
        int size = this.f1317a.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = (UpdateOp) this.f1317a.get(i);
            switch (updateOp.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m2751f(updateOp);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m2746c(updateOp);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    m2748d(updateOp);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    m2745b(updateOp);
                    break;
            }
            if (this.f1320d != null) {
                this.f1320d.run();
            }
        }
        this.f1317a.clear();
    }

    void m2765c() {
        int size = this.f1318b.size();
        for (int i = 0; i < size; i++) {
            this.f1319c.m2739b((UpdateOp) this.f1318b.get(i));
        }
        m2758a(this.f1318b);
        this.f1324h = 0;
    }

    private void m2745b(UpdateOp updateOp) {
        m2752g(updateOp);
    }

    private void m2746c(UpdateOp updateOp) {
        int i = updateOp.f1314b;
        int i2 = updateOp.f1314b + updateOp.f1316d;
        Object obj = -1;
        int i3 = updateOp.f1314b;
        int i4 = 0;
        while (i3 < i2) {
            Object obj2;
            int i5;
            if (this.f1319c.m2734a(i3) != null || m2749d(i3)) {
                if (obj == null) {
                    m2750e(m2754a(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = 1;
            } else {
                if (obj == 1) {
                    m2752g(m2754a(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = null;
            }
            if (obj2 != null) {
                i5 = i3 - i4;
                i3 = i2 - i4;
                i2 = 1;
            } else {
                int i6 = i3;
                i3 = i2;
                i2 = i4 + 1;
                i5 = i6;
            }
            i4 = i2;
            i2 = i3;
            i3 = i5 + 1;
        }
        if (i4 != updateOp.f1316d) {
            m2756a(updateOp);
            updateOp = m2754a(2, i, i4, null);
        }
        if (obj == null) {
            m2750e(updateOp);
        } else {
            m2752g(updateOp);
        }
    }

    private void m2748d(UpdateOp updateOp) {
        int i = updateOp.f1314b;
        int i2 = updateOp.f1314b + updateOp.f1316d;
        int i3 = updateOp.f1314b;
        Object obj = -1;
        int i4 = 0;
        while (i3 < i2) {
            int i5;
            Object obj2;
            if (this.f1319c.m2734a(i3) != null || m2749d(i3)) {
                if (obj == null) {
                    m2750e(m2754a(4, i, i4, updateOp.f1315c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = 1;
            } else {
                if (obj == 1) {
                    m2752g(m2754a(4, i, i4, updateOp.f1315c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = null;
            }
            i3++;
            Object obj3 = obj2;
            i4 = i + 1;
            i = i5;
            obj = obj3;
        }
        if (i4 != updateOp.f1316d) {
            Object obj4 = updateOp.f1315c;
            m2756a(updateOp);
            updateOp = m2754a(4, i, i4, obj4);
        }
        if (obj == null) {
            m2750e(updateOp);
        } else {
            m2752g(updateOp);
        }
    }

    private void m2750e(UpdateOp updateOp) {
        if (updateOp.f1313a == 1 || updateOp.f1313a == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int i;
        int d = m2747d(updateOp.f1314b, updateOp.f1313a);
        int i2 = updateOp.f1314b;
        switch (updateOp.f1313a) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                i = 0;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                i = 1;
                break;
            default:
                throw new IllegalArgumentException("op should be remove or update." + updateOp);
        }
        int i3 = 1;
        int i4 = d;
        d = i2;
        for (i2 = 1; i2 < updateOp.f1316d; i2++) {
            Object obj;
            int d2 = m2747d(updateOp.f1314b + (i * i2), updateOp.f1313a);
            int i5;
            switch (updateOp.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (d2 != i4) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    if (d2 != i4 + 1) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                default:
                    obj = null;
                    break;
            }
            if (obj != null) {
                i3++;
            } else {
                UpdateOp a = m2754a(updateOp.f1313a, i4, i3, updateOp.f1315c);
                m2757a(a, d);
                m2756a(a);
                if (updateOp.f1313a == 4) {
                    d += i3;
                }
                i3 = 1;
                i4 = d2;
            }
        }
        Object obj2 = updateOp.f1315c;
        m2756a(updateOp);
        if (i3 > 0) {
            UpdateOp a2 = m2754a(updateOp.f1313a, i4, i3, obj2);
            m2757a(a2, d);
            m2756a(a2);
        }
    }

    void m2757a(UpdateOp updateOp, int i) {
        this.f1319c.m2737a(updateOp);
        switch (updateOp.f1313a) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f1319c.m2735a(i, updateOp.f1316d);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f1319c.m2736a(i, updateOp.f1316d, updateOp.f1315c);
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int m2747d(int i, int i2) {
        int i3;
        int i4 = i;
        for (int size = this.f1318b.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = (UpdateOp) this.f1318b.get(size);
            if (updateOp.f1313a == 8) {
                int i5;
                int i6;
                if (updateOp.f1314b < updateOp.f1316d) {
                    i5 = updateOp.f1314b;
                    i3 = updateOp.f1316d;
                } else {
                    i5 = updateOp.f1316d;
                    i3 = updateOp.f1314b;
                }
                if (i4 < i5 || i4 > r2) {
                    if (i4 < updateOp.f1314b) {
                        if (i2 == 1) {
                            updateOp.f1314b++;
                            updateOp.f1316d++;
                            i6 = i4;
                        } else if (i2 == 2) {
                            updateOp.f1314b--;
                            updateOp.f1316d--;
                        }
                    }
                    i6 = i4;
                } else if (i5 == updateOp.f1314b) {
                    if (i2 == 1) {
                        updateOp.f1316d++;
                    } else if (i2 == 2) {
                        updateOp.f1316d--;
                    }
                    i6 = i4 + 1;
                } else {
                    if (i2 == 1) {
                        updateOp.f1314b++;
                    } else if (i2 == 2) {
                        updateOp.f1314b--;
                    }
                    i6 = i4 - 1;
                }
                i4 = i6;
            } else if (updateOp.f1314b <= i4) {
                if (updateOp.f1313a == 1) {
                    i4 -= updateOp.f1316d;
                } else if (updateOp.f1313a == 2) {
                    i4 += updateOp.f1316d;
                }
            } else if (i2 == 1) {
                updateOp.f1314b++;
            } else if (i2 == 2) {
                updateOp.f1314b--;
            }
        }
        for (i3 = this.f1318b.size() - 1; i3 >= 0; i3--) {
            updateOp = (UpdateOp) this.f1318b.get(i3);
            if (updateOp.f1313a == 8) {
                if (updateOp.f1316d == updateOp.f1314b || updateOp.f1316d < 0) {
                    this.f1318b.remove(i3);
                    m2756a(updateOp);
                }
            } else if (updateOp.f1316d <= 0) {
                this.f1318b.remove(i3);
                m2756a(updateOp);
            }
        }
        return i4;
    }

    private boolean m2749d(int i) {
        int size = this.f1318b.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) this.f1318b.get(i2);
            if (updateOp.f1313a == 8) {
                if (m2753a(updateOp.f1316d, i2 + 1) == i) {
                    return true;
                }
            } else if (updateOp.f1313a == 1) {
                int i3 = updateOp.f1314b + updateOp.f1316d;
                for (int i4 = updateOp.f1314b; i4 < i3; i4++) {
                    if (m2753a(i4, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void m2751f(UpdateOp updateOp) {
        m2752g(updateOp);
    }

    private void m2752g(UpdateOp updateOp) {
        this.f1318b.add(updateOp);
        switch (updateOp.f1313a) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f1319c.m2740c(updateOp.f1314b, updateOp.f1316d);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f1319c.m2738b(updateOp.f1314b, updateOp.f1316d);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f1319c.m2736a(updateOp.f1314b, updateOp.f1316d, updateOp.f1315c);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                this.f1319c.m2741d(updateOp.f1314b, updateOp.f1316d);
            default:
                throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    boolean m2767d() {
        return this.f1317a.size() > 0;
    }

    boolean m2759a(int i) {
        return (this.f1324h & i) != 0;
    }

    int m2761b(int i) {
        return m2753a(i, 0);
    }

    int m2753a(int i, int i2) {
        int size = this.f1318b.size();
        int i3 = i;
        while (i2 < size) {
            UpdateOp updateOp = (UpdateOp) this.f1318b.get(i2);
            if (updateOp.f1313a == 8) {
                if (updateOp.f1314b == i3) {
                    i3 = updateOp.f1316d;
                } else {
                    if (updateOp.f1314b < i3) {
                        i3--;
                    }
                    if (updateOp.f1316d <= i3) {
                        i3++;
                    }
                }
            } else if (updateOp.f1314b > i3) {
                continue;
            } else if (updateOp.f1313a == 2) {
                if (i3 < updateOp.f1314b + updateOp.f1316d) {
                    return -1;
                }
                i3 -= updateOp.f1316d;
            } else if (updateOp.f1313a == 1) {
                i3 += updateOp.f1316d;
            }
            i2++;
        }
        return i3;
    }

    boolean m2760a(int i, int i2, Object obj) {
        this.f1317a.add(m2754a(4, i, i2, obj));
        this.f1324h |= 4;
        if (this.f1317a.size() == 1) {
            return true;
        }
        return false;
    }

    boolean m2763b(int i, int i2) {
        this.f1317a.add(m2754a(1, i, i2, null));
        this.f1324h |= 1;
        if (this.f1317a.size() == 1) {
            return true;
        }
        return false;
    }

    boolean m2766c(int i, int i2) {
        this.f1317a.add(m2754a(2, i, i2, null));
        this.f1324h |= 2;
        if (this.f1317a.size() == 1) {
            return true;
        }
        return false;
    }

    void m2768e() {
        m2765c();
        int size = this.f1317a.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = (UpdateOp) this.f1317a.get(i);
            switch (updateOp.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f1319c.m2739b(updateOp);
                    this.f1319c.m2740c(updateOp.f1314b, updateOp.f1316d);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f1319c.m2739b(updateOp);
                    this.f1319c.m2735a(updateOp.f1314b, updateOp.f1316d);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f1319c.m2739b(updateOp);
                    this.f1319c.m2736a(updateOp.f1314b, updateOp.f1316d, updateOp.f1315c);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    this.f1319c.m2739b(updateOp);
                    this.f1319c.m2741d(updateOp.f1314b, updateOp.f1316d);
                    break;
            }
            if (this.f1320d != null) {
                this.f1320d.run();
            }
        }
        m2758a(this.f1317a);
        this.f1324h = 0;
    }

    public int m2764c(int i) {
        int size = this.f1317a.size();
        int i2 = i;
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = (UpdateOp) this.f1317a.get(i3);
            switch (updateOp.f1313a) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (updateOp.f1314b > i2) {
                        break;
                    }
                    i2 += updateOp.f1316d;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (updateOp.f1314b <= i2) {
                        if (updateOp.f1314b + updateOp.f1316d <= i2) {
                            i2 -= updateOp.f1316d;
                            break;
                        }
                        return -1;
                    }
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    if (updateOp.f1314b != i2) {
                        if (updateOp.f1314b < i2) {
                            i2--;
                        }
                        if (updateOp.f1316d > i2) {
                            break;
                        }
                        i2++;
                        break;
                    }
                    i2 = updateOp.f1316d;
                    break;
                default:
                    break;
            }
        }
        return i2;
    }

    public UpdateOp m2754a(int i, int i2, int i3, Object obj) {
        UpdateOp updateOp = (UpdateOp) this.f1323g.m784a();
        if (updateOp == null) {
            return new UpdateOp(i, i2, i3, obj);
        }
        updateOp.f1313a = i;
        updateOp.f1314b = i2;
        updateOp.f1316d = i3;
        updateOp.f1315c = obj;
        return updateOp;
    }

    public void m2756a(UpdateOp updateOp) {
        if (!this.f1321e) {
            updateOp.f1315c = null;
            this.f1323g.m785a(updateOp);
        }
    }

    void m2758a(List<UpdateOp> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            m2756a((UpdateOp) list.get(i));
        }
        list.clear();
    }
}
