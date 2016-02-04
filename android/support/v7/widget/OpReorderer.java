package android.support.v7.widget;

import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

class OpReorderer {
    final Callback f1585a;

    interface Callback {
        UpdateOp m2743a(int i, int i2, int i3, Object obj);

        void m2744a(UpdateOp updateOp);
    }

    public OpReorderer(Callback callback) {
        this.f1585a = callback;
    }

    void m3262a(List<UpdateOp> list) {
        while (true) {
            int b = m3260b(list);
            if (b != -1) {
                m3259a(list, b, b + 1);
            } else {
                return;
            }
        }
    }

    private void m3259a(List<UpdateOp> list, int i, int i2) {
        UpdateOp updateOp = (UpdateOp) list.get(i);
        UpdateOp updateOp2 = (UpdateOp) list.get(i2);
        switch (updateOp2.f1313a) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m3261c(list, i, updateOp, i2, updateOp2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m3263a(list, i, updateOp, i2, updateOp2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                m3264b(list, i, updateOp, i2, updateOp2);
            default:
        }
    }

    void m3263a(List<UpdateOp> list, int i, UpdateOp updateOp, int i2, UpdateOp updateOp2) {
        int i3;
        UpdateOp updateOp3;
        int i4 = 0;
        if (updateOp.f1314b < updateOp.f1316d) {
            i3 = (updateOp2.f1314b == updateOp.f1314b && updateOp2.f1316d == updateOp.f1316d - updateOp.f1314b) ? 1 : 0;
        } else if (updateOp2.f1314b == updateOp.f1316d + 1 && updateOp2.f1316d == updateOp.f1314b - updateOp.f1316d) {
            i4 = 1;
            i3 = 1;
        } else {
            i3 = 0;
            i4 = 1;
        }
        if (updateOp.f1316d < updateOp2.f1314b) {
            updateOp2.f1314b--;
        } else if (updateOp.f1316d < updateOp2.f1314b + updateOp2.f1316d) {
            updateOp2.f1316d--;
            updateOp.f1313a = 2;
            updateOp.f1316d = 1;
            if (updateOp2.f1316d == 0) {
                list.remove(i2);
                this.f1585a.m2744a(updateOp2);
                return;
            }
            return;
        }
        if (updateOp.f1314b <= updateOp2.f1314b) {
            updateOp2.f1314b++;
            updateOp3 = null;
        } else if (updateOp.f1314b < updateOp2.f1314b + updateOp2.f1316d) {
            updateOp3 = this.f1585a.m2743a(2, updateOp.f1314b + 1, (updateOp2.f1314b + updateOp2.f1316d) - updateOp.f1314b, null);
            updateOp2.f1316d = updateOp.f1314b - updateOp2.f1314b;
        } else {
            updateOp3 = null;
        }
        if (i3 != 0) {
            list.set(i, updateOp2);
            list.remove(i2);
            this.f1585a.m2744a(updateOp);
            return;
        }
        if (i4 != 0) {
            if (updateOp3 != null) {
                if (updateOp.f1314b > updateOp3.f1314b) {
                    updateOp.f1314b -= updateOp3.f1316d;
                }
                if (updateOp.f1316d > updateOp3.f1314b) {
                    updateOp.f1316d -= updateOp3.f1316d;
                }
            }
            if (updateOp.f1314b > updateOp2.f1314b) {
                updateOp.f1314b -= updateOp2.f1316d;
            }
            if (updateOp.f1316d > updateOp2.f1314b) {
                updateOp.f1316d -= updateOp2.f1316d;
            }
        } else {
            if (updateOp3 != null) {
                if (updateOp.f1314b >= updateOp3.f1314b) {
                    updateOp.f1314b -= updateOp3.f1316d;
                }
                if (updateOp.f1316d >= updateOp3.f1314b) {
                    updateOp.f1316d -= updateOp3.f1316d;
                }
            }
            if (updateOp.f1314b >= updateOp2.f1314b) {
                updateOp.f1314b -= updateOp2.f1316d;
            }
            if (updateOp.f1316d >= updateOp2.f1314b) {
                updateOp.f1316d -= updateOp2.f1316d;
            }
        }
        list.set(i, updateOp2);
        if (updateOp.f1314b != updateOp.f1316d) {
            list.set(i2, updateOp);
        } else {
            list.remove(i2);
        }
        if (updateOp3 != null) {
            list.add(i, updateOp3);
        }
    }

    private void m3261c(List<UpdateOp> list, int i, UpdateOp updateOp, int i2, UpdateOp updateOp2) {
        int i3 = 0;
        if (updateOp.f1316d < updateOp2.f1314b) {
            i3 = -1;
        }
        if (updateOp.f1314b < updateOp2.f1314b) {
            i3++;
        }
        if (updateOp2.f1314b <= updateOp.f1314b) {
            updateOp.f1314b += updateOp2.f1316d;
        }
        if (updateOp2.f1314b <= updateOp.f1316d) {
            updateOp.f1316d += updateOp2.f1316d;
        }
        updateOp2.f1314b = i3 + updateOp2.f1314b;
        list.set(i, updateOp2);
        list.set(i2, updateOp);
    }

    void m3264b(List<UpdateOp> list, int i, UpdateOp updateOp, int i2, UpdateOp updateOp2) {
        Object obj;
        Object obj2 = null;
        if (updateOp.f1316d < updateOp2.f1314b) {
            updateOp2.f1314b--;
            obj = null;
        } else if (updateOp.f1316d < updateOp2.f1314b + updateOp2.f1316d) {
            updateOp2.f1316d--;
            obj = this.f1585a.m2743a(4, updateOp.f1314b, 1, updateOp2.f1315c);
        } else {
            obj = null;
        }
        if (updateOp.f1314b <= updateOp2.f1314b) {
            updateOp2.f1314b++;
        } else if (updateOp.f1314b < updateOp2.f1314b + updateOp2.f1316d) {
            int i3 = (updateOp2.f1314b + updateOp2.f1316d) - updateOp.f1314b;
            obj2 = this.f1585a.m2743a(4, updateOp.f1314b + 1, i3, updateOp2.f1315c);
            updateOp2.f1316d -= i3;
        }
        list.set(i2, updateOp);
        if (updateOp2.f1316d > 0) {
            list.set(i, updateOp2);
        } else {
            list.remove(i);
            this.f1585a.m2744a(updateOp2);
        }
        if (obj != null) {
            list.add(i, obj);
        }
        if (obj2 != null) {
            list.add(i, obj2);
        }
    }

    private int m3260b(List<UpdateOp> list) {
        Object obj = null;
        int size = list.size() - 1;
        while (size >= 0) {
            Object obj2;
            if (((UpdateOp) list.get(size)).f1313a != 8) {
                obj2 = 1;
            } else if (obj != null) {
                return size;
            } else {
                obj2 = obj;
            }
            size--;
            obj = obj2;
        }
        return -1;
    }
}
