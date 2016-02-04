package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ViewPropertyAnimatorCompat {
    static final ViewPropertyAnimatorCompatImpl f529a;
    private WeakReference<View> f530b;
    private Runnable f531c;
    private Runnable f532d;
    private int f533e;

    interface ViewPropertyAnimatorCompatImpl {
        long m1349a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view);

        void m1350a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);

        void m1351a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j);

        void m1352a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener);

        void m1353a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener);

        void m1354a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator);

        void m1355b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view);

        void m1356b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);

        void m1357b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j);

        void m1358c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view);

        void m1359c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);
    }

    class BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Runnable> f526a;

        class Starter implements Runnable {
            WeakReference<View> f523a;
            ViewPropertyAnimatorCompat f524b;
            final /* synthetic */ BaseViewPropertyAnimatorCompatImpl f525c;

            private Starter(BaseViewPropertyAnimatorCompatImpl baseViewPropertyAnimatorCompatImpl, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
                this.f525c = baseViewPropertyAnimatorCompatImpl;
                this.f523a = new WeakReference(view);
                this.f524b = viewPropertyAnimatorCompat;
            }

            public void run() {
                View view = (View) this.f523a.get();
                if (view != null) {
                    this.f525c.m1362d(this.f524b, view);
                }
            }
        }

        BaseViewPropertyAnimatorCompatImpl() {
            this.f526a = null;
        }

        public void m1366a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j) {
        }

        public void m1365a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            m1363e(viewPropertyAnimatorCompat, view);
        }

        public void m1371b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            m1363e(viewPropertyAnimatorCompat, view);
        }

        public void m1374c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            m1363e(viewPropertyAnimatorCompat, view);
        }

        public long m1364a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return 0;
        }

        public void m1369a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator) {
        }

        public void m1372b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j) {
        }

        public void m1370b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            m1363e(viewPropertyAnimatorCompat, view);
        }

        public void m1373c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            m1361a(view);
            m1362d(viewPropertyAnimatorCompat, view);
        }

        public void m1367a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, viewPropertyAnimatorListener);
        }

        public void m1368a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        }

        private void m1362d(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorListener viewPropertyAnimatorListener;
            Object tag = view.getTag(2113929216);
            if (tag instanceof ViewPropertyAnimatorListener) {
                viewPropertyAnimatorListener = (ViewPropertyAnimatorListener) tag;
            } else {
                viewPropertyAnimatorListener = null;
            }
            Runnable a = viewPropertyAnimatorCompat.f531c;
            Runnable b = viewPropertyAnimatorCompat.f532d;
            if (a != null) {
                a.run();
            }
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.m1375a(view);
                viewPropertyAnimatorListener.m1376b(view);
            }
            if (b != null) {
                b.run();
            }
            if (this.f526a != null) {
                this.f526a.remove(view);
            }
        }

        private void m1361a(View view) {
            if (this.f526a != null) {
                Runnable runnable = (Runnable) this.f526a.get(view);
                if (runnable != null) {
                    view.removeCallbacks(runnable);
                }
            }
        }

        private void m1363e(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            Runnable runnable;
            if (this.f526a != null) {
                runnable = (Runnable) this.f526a.get(view);
            } else {
                runnable = null;
            }
            if (runnable == null) {
                runnable = new Starter(viewPropertyAnimatorCompat, view, null);
                if (this.f526a == null) {
                    this.f526a = new WeakHashMap();
                }
                this.f526a.put(view, runnable);
            }
            view.removeCallbacks(runnable);
            view.post(runnable);
        }
    }

    class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Integer> f528b;

        class MyVpaListener implements ViewPropertyAnimatorListener {
            ViewPropertyAnimatorCompat f527a;

            MyVpaListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
                this.f527a = viewPropertyAnimatorCompat;
            }

            public void m1378a(View view) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener;
                if (this.f527a.f533e >= 0) {
                    ViewCompat.m1158a(view, 2, null);
                }
                if (this.f527a.f531c != null) {
                    this.f527a.f531c.run();
                }
                Object tag = view.getTag(2113929216);
                if (tag instanceof ViewPropertyAnimatorListener) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener) tag;
                } else {
                    viewPropertyAnimatorListener = null;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.m1375a(view);
                }
            }

            public void m1379b(View view) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener;
                if (this.f527a.f533e >= 0) {
                    ViewCompat.m1158a(view, this.f527a.f533e, null);
                    this.f527a.f533e = -1;
                }
                if (this.f527a.f532d != null) {
                    this.f527a.f532d.run();
                }
                Object tag = view.getTag(2113929216);
                if (tag instanceof ViewPropertyAnimatorListener) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener) tag;
                } else {
                    viewPropertyAnimatorListener = null;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.m1376b(view);
                }
            }

            public void m1380c(View view) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener;
                Object tag = view.getTag(2113929216);
                if (tag instanceof ViewPropertyAnimatorListener) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener) tag;
                } else {
                    viewPropertyAnimatorListener = null;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.m1377c(view);
                }
            }
        }

        ICSViewPropertyAnimatorCompatImpl() {
            this.f528b = null;
        }

        public void m1383a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j) {
            ViewPropertyAnimatorCompatICS.m1410a(view, j);
        }

        public void m1382a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            ViewPropertyAnimatorCompatICS.m1409a(view, f);
        }

        public void m1387b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            ViewPropertyAnimatorCompatICS.m1414b(view, f);
        }

        public void m1390c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f) {
            ViewPropertyAnimatorCompatICS.m1417c(view, f);
        }

        public long m1381a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            return ViewPropertyAnimatorCompatICS.m1408a(view);
        }

        public void m1385a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator) {
            ViewPropertyAnimatorCompatICS.m1412a(view, interpolator);
        }

        public void m1388b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j) {
            ViewPropertyAnimatorCompatICS.m1415b(view, j);
        }

        public void m1386b(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorCompatICS.m1413b(view);
        }

        public void m1389c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view) {
            ViewPropertyAnimatorCompatICS.m1416c(view);
        }

        public void m1384a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, viewPropertyAnimatorListener);
            ViewPropertyAnimatorCompatICS.m1411a(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }
    }

    class JBViewPropertyAnimatorCompatImpl extends ICSViewPropertyAnimatorCompatImpl {
        JBViewPropertyAnimatorCompatImpl() {
        }

        public void m1391a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            ViewPropertyAnimatorCompatJB.m1418a(view, viewPropertyAnimatorListener);
        }
    }

    class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl {
        JBMr2ViewPropertyAnimatorCompatImpl() {
        }
    }

    class KitKatViewPropertyAnimatorCompatImpl extends JBMr2ViewPropertyAnimatorCompatImpl {
        KitKatViewPropertyAnimatorCompatImpl() {
        }

        public void m1392a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
            ViewPropertyAnimatorCompatKK.m1419a(view, viewPropertyAnimatorUpdateListener);
        }
    }

    class LollipopViewPropertyAnimatorCompatImpl extends KitKatViewPropertyAnimatorCompatImpl {
        LollipopViewPropertyAnimatorCompatImpl() {
        }
    }

    ViewPropertyAnimatorCompat(View view) {
        this.f531c = null;
        this.f532d = null;
        this.f533e = -1;
        this.f530b = new WeakReference(view);
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f529a = new LollipopViewPropertyAnimatorCompatImpl();
        } else if (i >= 19) {
            f529a = new KitKatViewPropertyAnimatorCompatImpl();
        } else if (i >= 18) {
            f529a = new JBMr2ViewPropertyAnimatorCompatImpl();
        } else if (i >= 16) {
            f529a = new JBViewPropertyAnimatorCompatImpl();
        } else if (i >= 14) {
            f529a = new ICSViewPropertyAnimatorCompatImpl();
        } else {
            f529a = new BaseViewPropertyAnimatorCompatImpl();
        }
    }

    public ViewPropertyAnimatorCompat m1399a(long j) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1351a(this, view, j);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m1398a(float f) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1350a(this, view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m1403b(float f) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1356b(this, view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m1406c(float f) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1359c(this, view, f);
        }
        return this;
    }

    public long m1397a() {
        View view = (View) this.f530b.get();
        if (view != null) {
            return f529a.m1349a(this, view);
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat m1402a(Interpolator interpolator) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1354a(this, view, interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m1404b(long j) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1357b(this, view, j);
        }
        return this;
    }

    public void m1405b() {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1355b(this, view);
        }
    }

    public void m1407c() {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1358c(this, view);
        }
    }

    public ViewPropertyAnimatorCompat m1400a(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1352a(this, view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m1401a(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        View view = (View) this.f530b.get();
        if (view != null) {
            f529a.m1353a(this, view, viewPropertyAnimatorUpdateListener);
        }
        return this;
    }
}
