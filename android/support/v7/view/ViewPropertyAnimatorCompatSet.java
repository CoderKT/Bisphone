package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet {
    private final ArrayList<ViewPropertyAnimatorCompat> f955a;
    private long f956b;
    private Interpolator f957c;
    private ViewPropertyAnimatorListener f958d;
    private boolean f959e;
    private final ViewPropertyAnimatorListenerAdapter f960f;

    /* renamed from: android.support.v7.view.ViewPropertyAnimatorCompatSet.1 */
    class C00591 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimatorCompatSet f952a;
        private boolean f953b;
        private int f954c;

        C00591(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
            this.f952a = viewPropertyAnimatorCompatSet;
            this.f953b = false;
            this.f954c = 0;
        }

        public void m2302a(View view) {
            if (!this.f953b) {
                this.f953b = true;
                if (this.f952a.f958d != null) {
                    this.f952a.f958d.m1375a(null);
                }
            }
        }

        void m2301a() {
            this.f954c = 0;
            this.f953b = false;
            this.f952a.m2307c();
        }

        public void m2303b(View view) {
            int i = this.f954c + 1;
            this.f954c = i;
            if (i == this.f952a.f955a.size()) {
                if (this.f952a.f958d != null) {
                    this.f952a.f958d.m1376b(null);
                }
                m2301a();
            }
        }
    }

    public ViewPropertyAnimatorCompatSet() {
        this.f956b = -1;
        this.f960f = new C00591(this);
        this.f955a = new ArrayList();
    }

    public ViewPropertyAnimatorCompatSet m2309a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.f959e) {
            this.f955a.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet m2310a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.f955a.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.m1404b(viewPropertyAnimatorCompat.m1397a());
        this.f955a.add(viewPropertyAnimatorCompat2);
        return this;
    }

    public void m2313a() {
        if (!this.f959e) {
            Iterator it = this.f955a.iterator();
            while (it.hasNext()) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) it.next();
                if (this.f956b >= 0) {
                    viewPropertyAnimatorCompat.m1399a(this.f956b);
                }
                if (this.f957c != null) {
                    viewPropertyAnimatorCompat.m1402a(this.f957c);
                }
                if (this.f958d != null) {
                    viewPropertyAnimatorCompat.m1400a(this.f960f);
                }
                viewPropertyAnimatorCompat.m1407c();
            }
            this.f959e = true;
        }
    }

    private void m2307c() {
        this.f959e = false;
    }

    public void m2314b() {
        if (this.f959e) {
            Iterator it = this.f955a.iterator();
            while (it.hasNext()) {
                ((ViewPropertyAnimatorCompat) it.next()).m1405b();
            }
            this.f959e = false;
        }
    }

    public ViewPropertyAnimatorCompatSet m2308a(long j) {
        if (!this.f959e) {
            this.f956b = j;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet m2312a(Interpolator interpolator) {
        if (!this.f959e) {
            this.f957c = interpolator;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet m2311a(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.f959e) {
            this.f958d = viewPropertyAnimatorListener;
        }
        return this;
    }
}
