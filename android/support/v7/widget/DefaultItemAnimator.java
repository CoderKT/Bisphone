package android.support.v7.widget;

import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultItemAnimator extends SimpleItemAnimator {
    private ArrayList<ViewHolder> f1493b;
    private ArrayList<ViewHolder> f1494c;
    private ArrayList<MoveInfo> f1495d;
    private ArrayList<ChangeInfo> f1496e;
    private ArrayList<ArrayList<ViewHolder>> f1497f;
    private ArrayList<ArrayList<MoveInfo>> f1498g;
    private ArrayList<ArrayList<ChangeInfo>> f1499h;
    private ArrayList<ViewHolder> f1500i;
    private ArrayList<ViewHolder> f1501j;
    private ArrayList<ViewHolder> f1502k;
    private ArrayList<ViewHolder> f1503l;

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.1 */
    class C00741 implements Runnable {
        final /* synthetic */ ArrayList f1451a;
        final /* synthetic */ DefaultItemAnimator f1452b;

        C00741(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
            this.f1452b = defaultItemAnimator;
            this.f1451a = arrayList;
        }

        public void run() {
            Iterator it = this.f1451a.iterator();
            while (it.hasNext()) {
                MoveInfo moveInfo = (MoveInfo) it.next();
                this.f1452b.m2994b(moveInfo.f1481a, moveInfo.f1482b, moveInfo.f1483c, moveInfo.f1484d, moveInfo.f1485e);
            }
            this.f1451a.clear();
            this.f1452b.f1498g.remove(this.f1451a);
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.2 */
    class C00752 implements Runnable {
        final /* synthetic */ ArrayList f1453a;
        final /* synthetic */ DefaultItemAnimator f1454b;

        C00752(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
            this.f1454b = defaultItemAnimator;
            this.f1453a = arrayList;
        }

        public void run() {
            Iterator it = this.f1453a.iterator();
            while (it.hasNext()) {
                this.f1454b.m2986a((ChangeInfo) it.next());
            }
            this.f1453a.clear();
            this.f1454b.f1499h.remove(this.f1453a);
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.3 */
    class C00763 implements Runnable {
        final /* synthetic */ ArrayList f1455a;
        final /* synthetic */ DefaultItemAnimator f1456b;

        C00763(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
            this.f1456b = defaultItemAnimator;
            this.f1455a = arrayList;
        }

        public void run() {
            Iterator it = this.f1455a.iterator();
            while (it.hasNext()) {
                this.f1456b.m3003u((ViewHolder) it.next());
            }
            this.f1455a.clear();
            this.f1456b.f1497f.remove(this.f1455a);
        }
    }

    class VpaListenerAdapter implements ViewPropertyAnimatorListener {
        private VpaListenerAdapter() {
        }

        public void m2924a(View view) {
        }

        public void m2925b(View view) {
        }

        public void m2926c(View view) {
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.4 */
    class C00774 extends VpaListenerAdapter {
        final /* synthetic */ ViewHolder f1457a;
        final /* synthetic */ ViewPropertyAnimatorCompat f1458b;
        final /* synthetic */ DefaultItemAnimator f1459c;

        C00774(DefaultItemAnimator defaultItemAnimator, ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f1459c = defaultItemAnimator;
            this.f1457a = viewHolder;
            this.f1458b = viewPropertyAnimatorCompat;
            super();
        }

        public void m2927a(View view) {
            this.f1459c.m2976k(this.f1457a);
        }

        public void m2928b(View view) {
            this.f1458b.m1400a(null);
            ViewCompat.m1171c(view, 1.0f);
            this.f1459c.m2973h(this.f1457a);
            this.f1459c.f1502k.remove(this.f1457a);
            this.f1459c.m3001j();
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.5 */
    class C00785 extends VpaListenerAdapter {
        final /* synthetic */ ViewHolder f1460a;
        final /* synthetic */ ViewPropertyAnimatorCompat f1461b;
        final /* synthetic */ DefaultItemAnimator f1462c;

        C00785(DefaultItemAnimator defaultItemAnimator, ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f1462c = defaultItemAnimator;
            this.f1460a = viewHolder;
            this.f1461b = viewPropertyAnimatorCompat;
            super();
        }

        public void m2929a(View view) {
            this.f1462c.m2978m(this.f1460a);
        }

        public void m2931c(View view) {
            ViewCompat.m1171c(view, 1.0f);
        }

        public void m2930b(View view) {
            this.f1461b.m1400a(null);
            this.f1462c.m2975j(this.f1460a);
            this.f1462c.f1500i.remove(this.f1460a);
            this.f1462c.m3001j();
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.6 */
    class C00796 extends VpaListenerAdapter {
        final /* synthetic */ ViewHolder f1463a;
        final /* synthetic */ int f1464b;
        final /* synthetic */ int f1465c;
        final /* synthetic */ ViewPropertyAnimatorCompat f1466d;
        final /* synthetic */ DefaultItemAnimator f1467e;

        C00796(DefaultItemAnimator defaultItemAnimator, ViewHolder viewHolder, int i, int i2, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f1467e = defaultItemAnimator;
            this.f1463a = viewHolder;
            this.f1464b = i;
            this.f1465c = i2;
            this.f1466d = viewPropertyAnimatorCompat;
            super();
        }

        public void m2932a(View view) {
            this.f1467e.m2977l(this.f1463a);
        }

        public void m2934c(View view) {
            if (this.f1464b != 0) {
                ViewCompat.m1156a(view, 0.0f);
            }
            if (this.f1465c != 0) {
                ViewCompat.m1167b(view, 0.0f);
            }
        }

        public void m2933b(View view) {
            this.f1466d.m1400a(null);
            this.f1467e.m2974i(this.f1463a);
            this.f1467e.f1501j.remove(this.f1463a);
            this.f1467e.m3001j();
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.7 */
    class C00807 extends VpaListenerAdapter {
        final /* synthetic */ ChangeInfo f1468a;
        final /* synthetic */ ViewPropertyAnimatorCompat f1469b;
        final /* synthetic */ DefaultItemAnimator f1470c;

        C00807(DefaultItemAnimator defaultItemAnimator, ChangeInfo changeInfo, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f1470c = defaultItemAnimator;
            this.f1468a = changeInfo;
            this.f1469b = viewPropertyAnimatorCompat;
            super();
        }

        public void m2935a(View view) {
            this.f1470c.m2966b(this.f1468a.f1475a, true);
        }

        public void m2936b(View view) {
            this.f1469b.m1400a(null);
            ViewCompat.m1171c(view, 1.0f);
            ViewCompat.m1156a(view, 0.0f);
            ViewCompat.m1167b(view, 0.0f);
            this.f1470c.m2960a(this.f1468a.f1475a, true);
            this.f1470c.f1503l.remove(this.f1468a.f1475a);
            this.f1470c.m3001j();
        }
    }

    /* renamed from: android.support.v7.widget.DefaultItemAnimator.8 */
    class C00818 extends VpaListenerAdapter {
        final /* synthetic */ ChangeInfo f1471a;
        final /* synthetic */ ViewPropertyAnimatorCompat f1472b;
        final /* synthetic */ View f1473c;
        final /* synthetic */ DefaultItemAnimator f1474d;

        C00818(DefaultItemAnimator defaultItemAnimator, ChangeInfo changeInfo, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            this.f1474d = defaultItemAnimator;
            this.f1471a = changeInfo;
            this.f1472b = viewPropertyAnimatorCompat;
            this.f1473c = view;
            super();
        }

        public void m2937a(View view) {
            this.f1474d.m2966b(this.f1471a.f1476b, false);
        }

        public void m2938b(View view) {
            this.f1472b.m1400a(null);
            ViewCompat.m1171c(this.f1473c, 1.0f);
            ViewCompat.m1156a(this.f1473c, 0.0f);
            ViewCompat.m1167b(this.f1473c, 0.0f);
            this.f1474d.m2960a(this.f1471a.f1476b, false);
            this.f1474d.f1503l.remove(this.f1471a.f1476b);
            this.f1474d.m3001j();
        }
    }

    class ChangeInfo {
        public ViewHolder f1475a;
        public ViewHolder f1476b;
        public int f1477c;
        public int f1478d;
        public int f1479e;
        public int f1480f;

        private ChangeInfo(ViewHolder viewHolder, ViewHolder viewHolder2) {
            this.f1475a = viewHolder;
            this.f1476b = viewHolder2;
        }

        private ChangeInfo(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this(viewHolder, viewHolder2);
            this.f1477c = i;
            this.f1478d = i2;
            this.f1479e = i3;
            this.f1480f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1475a + ", newHolder=" + this.f1476b + ", fromX=" + this.f1477c + ", fromY=" + this.f1478d + ", toX=" + this.f1479e + ", toY=" + this.f1480f + '}';
        }
    }

    class MoveInfo {
        public ViewHolder f1481a;
        public int f1482b;
        public int f1483c;
        public int f1484d;
        public int f1485e;

        private MoveInfo(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.f1481a = viewHolder;
            this.f1482b = i;
            this.f1483c = i2;
            this.f1484d = i3;
            this.f1485e = i4;
        }
    }

    public DefaultItemAnimator() {
        this.f1493b = new ArrayList();
        this.f1494c = new ArrayList();
        this.f1495d = new ArrayList();
        this.f1496e = new ArrayList();
        this.f1497f = new ArrayList();
        this.f1498g = new ArrayList();
        this.f1499h = new ArrayList();
        this.f1500i = new ArrayList();
        this.f1501j = new ArrayList();
        this.f1502k = new ArrayList();
        this.f1503l = new ArrayList();
    }

    public void m3005a() {
        int i;
        int i2;
        int i3;
        int i4 = !this.f1493b.isEmpty() ? 1 : 0;
        if (this.f1495d.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.f1496e.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (this.f1494c.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (i4 != 0 || i != 0 || i3 != 0 || i2 != 0) {
            ArrayList arrayList;
            Runnable c00741;
            Iterator it = this.f1493b.iterator();
            while (it.hasNext()) {
                m3002t((ViewHolder) it.next());
            }
            this.f1493b.clear();
            if (i != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.f1495d);
                this.f1498g.add(arrayList);
                this.f1495d.clear();
                c00741 = new C00741(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.m1164a(((MoveInfo) arrayList.get(0)).f1481a.f1642a, c00741, m2954f());
                } else {
                    c00741.run();
                }
            }
            if (i2 != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.f1496e);
                this.f1499h.add(arrayList);
                this.f1496e.clear();
                c00741 = new C00752(this, arrayList);
                if (i4 != 0) {
                    ViewCompat.m1164a(((ChangeInfo) arrayList.get(0)).f1475a.f1642a, c00741, m2954f());
                } else {
                    c00741.run();
                }
            }
            if (i3 != 0) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.f1494c);
                this.f1497f.add(arrayList2);
                this.f1494c.clear();
                Runnable c00763 = new C00763(this, arrayList2);
                if (i4 == 0 && i == 0 && i2 == 0) {
                    c00763.run();
                    return;
                }
                long d;
                long g;
                long f = i4 != 0 ? m2954f() : 0;
                if (i != 0) {
                    d = m2951d();
                } else {
                    d = 0;
                }
                if (i2 != 0) {
                    g = m2956g();
                } else {
                    g = 0;
                }
                ViewCompat.m1164a(((ViewHolder) arrayList2.get(0)).f1642a, c00763, f + Math.max(d, g));
            }
        }
    }

    public boolean m3007a(ViewHolder viewHolder) {
        m3004v(viewHolder);
        this.f1493b.add(viewHolder);
        return true;
    }

    private void m3002t(ViewHolder viewHolder) {
        ViewPropertyAnimatorCompat n = ViewCompat.m1185n(viewHolder.f1642a);
        this.f1502k.add(viewHolder);
        n.m1399a(m2954f()).m1398a(0.0f).m1400a(new C00774(this, viewHolder, n)).m1407c();
    }

    public boolean m3011b(ViewHolder viewHolder) {
        m3004v(viewHolder);
        ViewCompat.m1171c(viewHolder.f1642a, 0.0f);
        this.f1494c.add(viewHolder);
        return true;
    }

    private void m3003u(ViewHolder viewHolder) {
        ViewPropertyAnimatorCompat n = ViewCompat.m1185n(viewHolder.f1642a);
        this.f1500i.add(viewHolder);
        n.m1398a(1.0f).m1399a(m2952e()).m1400a(new C00785(this, viewHolder, n)).m1407c();
    }

    public boolean m3008a(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.f1642a;
        int j = (int) (((float) i) + ViewCompat.m1181j(viewHolder.f1642a));
        int k = (int) (((float) i2) + ViewCompat.m1182k(viewHolder.f1642a));
        m3004v(viewHolder);
        int i5 = i3 - j;
        int i6 = i4 - k;
        if (i5 == 0 && i6 == 0) {
            m2974i(viewHolder);
            return false;
        }
        if (i5 != 0) {
            ViewCompat.m1156a(view, (float) (-i5));
        }
        if (i6 != 0) {
            ViewCompat.m1167b(view, (float) (-i6));
        }
        this.f1495d.add(new MoveInfo(j, k, i3, i4, null));
        return true;
    }

    private void m2994b(ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.f1642a;
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (i5 != 0) {
            ViewCompat.m1185n(view).m1403b(0.0f);
        }
        if (i6 != 0) {
            ViewCompat.m1185n(view).m1406c(0.0f);
        }
        ViewPropertyAnimatorCompat n = ViewCompat.m1185n(view);
        this.f1501j.add(viewHolder);
        n.m1399a(m2951d()).m1400a(new C00796(this, viewHolder, i5, i6, n)).m1407c();
    }

    public boolean m3009a(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return m3008a(viewHolder, i, i2, i3, i4);
        }
        float j = ViewCompat.m1181j(viewHolder.f1642a);
        float k = ViewCompat.m1182k(viewHolder.f1642a);
        float f = ViewCompat.m1177f(viewHolder.f1642a);
        m3004v(viewHolder);
        int i5 = (int) (((float) (i3 - i)) - j);
        int i6 = (int) (((float) (i4 - i2)) - k);
        ViewCompat.m1156a(viewHolder.f1642a, j);
        ViewCompat.m1167b(viewHolder.f1642a, k);
        ViewCompat.m1171c(viewHolder.f1642a, f);
        if (viewHolder2 != null) {
            m3004v(viewHolder2);
            ViewCompat.m1156a(viewHolder2.f1642a, (float) (-i5));
            ViewCompat.m1167b(viewHolder2.f1642a, (float) (-i6));
            ViewCompat.m1171c(viewHolder2.f1642a, 0.0f);
        }
        this.f1496e.add(new ChangeInfo(viewHolder2, i, i2, i3, i4, null));
        return true;
    }

    private void m2986a(ChangeInfo changeInfo) {
        View view = null;
        ViewHolder viewHolder = changeInfo.f1475a;
        View view2 = viewHolder == null ? null : viewHolder.f1642a;
        ViewHolder viewHolder2 = changeInfo.f1476b;
        if (viewHolder2 != null) {
            view = viewHolder2.f1642a;
        }
        if (view2 != null) {
            ViewPropertyAnimatorCompat a = ViewCompat.m1185n(view2).m1399a(m2956g());
            this.f1503l.add(changeInfo.f1475a);
            a.m1403b((float) (changeInfo.f1479e - changeInfo.f1477c));
            a.m1406c((float) (changeInfo.f1480f - changeInfo.f1478d));
            a.m1398a(0.0f).m1400a(new C00807(this, changeInfo, a)).m1407c();
        }
        if (view != null) {
            a = ViewCompat.m1185n(view);
            this.f1503l.add(changeInfo.f1476b);
            a.m1403b(0.0f).m1406c(0.0f).m1399a(m2956g()).m1398a(1.0f).m1400a(new C00818(this, changeInfo, a, view)).m1407c();
        }
    }

    private void m2990a(List<ChangeInfo> list, ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (m2991a(changeInfo, viewHolder) && changeInfo.f1475a == null && changeInfo.f1476b == null) {
                list.remove(changeInfo);
            }
        }
    }

    private void m2993b(ChangeInfo changeInfo) {
        if (changeInfo.f1475a != null) {
            m2991a(changeInfo, changeInfo.f1475a);
        }
        if (changeInfo.f1476b != null) {
            m2991a(changeInfo, changeInfo.f1476b);
        }
    }

    private boolean m2991a(ChangeInfo changeInfo, ViewHolder viewHolder) {
        boolean z = false;
        if (changeInfo.f1476b == viewHolder) {
            changeInfo.f1476b = null;
        } else if (changeInfo.f1475a != viewHolder) {
            return false;
        } else {
            changeInfo.f1475a = null;
            z = true;
        }
        ViewCompat.m1171c(viewHolder.f1642a, 1.0f);
        ViewCompat.m1156a(viewHolder.f1642a, 0.0f);
        ViewCompat.m1167b(viewHolder.f1642a, 0.0f);
        m2960a(viewHolder, z);
        return true;
    }

    public void m3013c(ViewHolder viewHolder) {
        int size;
        View view = viewHolder.f1642a;
        ViewCompat.m1185n(view).m1405b();
        for (size = this.f1495d.size() - 1; size >= 0; size--) {
            if (((MoveInfo) this.f1495d.get(size)).f1481a == viewHolder) {
                ViewCompat.m1167b(view, 0.0f);
                ViewCompat.m1156a(view, 0.0f);
                m2974i(viewHolder);
                this.f1495d.remove(size);
            }
        }
        m2990a(this.f1496e, viewHolder);
        if (this.f1493b.remove(viewHolder)) {
            ViewCompat.m1171c(view, 1.0f);
            m2973h(viewHolder);
        }
        if (this.f1494c.remove(viewHolder)) {
            ViewCompat.m1171c(view, 1.0f);
            m2975j(viewHolder);
        }
        for (size = this.f1499h.size() - 1; size >= 0; size--) {
            List list = (ArrayList) this.f1499h.get(size);
            m2990a(list, viewHolder);
            if (list.isEmpty()) {
                this.f1499h.remove(size);
            }
        }
        for (int size2 = this.f1498g.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.f1498g.get(size2);
            int size3 = arrayList.size() - 1;
            while (size3 >= 0) {
                if (((MoveInfo) arrayList.get(size3)).f1481a == viewHolder) {
                    ViewCompat.m1167b(view, 0.0f);
                    ViewCompat.m1156a(view, 0.0f);
                    m2974i(viewHolder);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.f1498g.remove(size2);
                    }
                } else {
                    size3--;
                }
            }
        }
        for (size = this.f1497f.size() - 1; size >= 0; size--) {
            arrayList = (ArrayList) this.f1497f.get(size);
            if (arrayList.remove(viewHolder)) {
                ViewCompat.m1171c(view, 1.0f);
                m2975j(viewHolder);
                if (arrayList.isEmpty()) {
                    this.f1497f.remove(size);
                }
            }
        }
        if (this.f1502k.remove(viewHolder)) {
        }
        if (this.f1500i.remove(viewHolder)) {
        }
        if (this.f1503l.remove(viewHolder)) {
        }
        if (this.f1501j.remove(viewHolder)) {
            m3001j();
        } else {
            m3001j();
        }
    }

    private void m3004v(ViewHolder viewHolder) {
        AnimatorCompatHelper.m76a(viewHolder.f1642a);
        m3013c(viewHolder);
    }

    public boolean m3010b() {
        return (this.f1494c.isEmpty() && this.f1496e.isEmpty() && this.f1495d.isEmpty() && this.f1493b.isEmpty() && this.f1501j.isEmpty() && this.f1502k.isEmpty() && this.f1500i.isEmpty() && this.f1503l.isEmpty() && this.f1498g.isEmpty() && this.f1497f.isEmpty() && this.f1499h.isEmpty()) ? false : true;
    }

    private void m3001j() {
        if (!m3010b()) {
            m2958h();
        }
    }

    public void m3012c() {
        int size;
        for (size = this.f1495d.size() - 1; size >= 0; size--) {
            MoveInfo moveInfo = (MoveInfo) this.f1495d.get(size);
            View view = moveInfo.f1481a.f1642a;
            ViewCompat.m1167b(view, 0.0f);
            ViewCompat.m1156a(view, 0.0f);
            m2974i(moveInfo.f1481a);
            this.f1495d.remove(size);
        }
        for (size = this.f1493b.size() - 1; size >= 0; size--) {
            m2973h((ViewHolder) this.f1493b.get(size));
            this.f1493b.remove(size);
        }
        for (size = this.f1494c.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.f1494c.get(size);
            ViewCompat.m1171c(viewHolder.f1642a, 1.0f);
            m2975j(viewHolder);
            this.f1494c.remove(size);
        }
        for (size = this.f1496e.size() - 1; size >= 0; size--) {
            m2993b((ChangeInfo) this.f1496e.get(size));
        }
        this.f1496e.clear();
        if (m3010b()) {
            int size2;
            ArrayList arrayList;
            int size3;
            for (size2 = this.f1498g.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.f1498g.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size3);
                    View view2 = moveInfo2.f1481a.f1642a;
                    ViewCompat.m1167b(view2, 0.0f);
                    ViewCompat.m1156a(view2, 0.0f);
                    m2974i(moveInfo2.f1481a);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.f1498g.remove(arrayList);
                    }
                }
            }
            for (size2 = this.f1497f.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.f1497f.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    ViewHolder viewHolder2 = (ViewHolder) arrayList.get(size3);
                    ViewCompat.m1171c(viewHolder2.f1642a, 1.0f);
                    m2975j(viewHolder2);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.f1497f.remove(arrayList);
                    }
                }
            }
            for (size2 = this.f1499h.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.f1499h.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    m2993b((ChangeInfo) arrayList.get(size3));
                    if (arrayList.isEmpty()) {
                        this.f1499h.remove(arrayList);
                    }
                }
            }
            m3006a(this.f1502k);
            m3006a(this.f1501j);
            m3006a(this.f1500i);
            m3006a(this.f1503l);
            m2958h();
        }
    }

    void m3006a(List<ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.m1185n(((ViewHolder) list.get(size)).f1642a).m1405b();
        }
    }
}
