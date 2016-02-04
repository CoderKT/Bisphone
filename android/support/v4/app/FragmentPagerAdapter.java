package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private final FragmentManager f207a;
    private FragmentTransaction f208b;
    private Fragment f209c;

    public abstract Fragment m436a(int i);

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.f208b = null;
        this.f209c = null;
        this.f207a = fragmentManager;
    }

    public void m439a(ViewGroup viewGroup) {
    }

    public Object m437a(ViewGroup viewGroup, int i) {
        if (this.f208b == null) {
            this.f208b = this.f207a.m353a();
        }
        long b = m442b(i);
        Fragment a = this.f207a.m352a(m434a(viewGroup.getId(), b));
        if (a != null) {
            this.f208b.m118c(a);
        } else {
            a = m436a(i);
            this.f208b.m111a(viewGroup.getId(), a, m434a(viewGroup.getId(), b));
        }
        if (a != this.f209c) {
            a.m219e(false);
            a.m221f(false);
        }
        return a;
    }

    public void m440a(ViewGroup viewGroup, int i, Object obj) {
        if (this.f208b == null) {
            this.f208b = this.f207a.m353a();
        }
        this.f208b.m117b((Fragment) obj);
    }

    public void m444b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f209c) {
            if (this.f209c != null) {
                this.f209c.m219e(false);
                this.f209c.m221f(false);
            }
            if (fragment != null) {
                fragment.m219e(true);
                fragment.m221f(true);
            }
            this.f209c = fragment;
        }
    }

    public void m443b(ViewGroup viewGroup) {
        if (this.f208b != null) {
            this.f208b.m114b();
            this.f208b = null;
            this.f207a.m357b();
        }
    }

    public boolean m441a(View view, Object obj) {
        return ((Fragment) obj).m237p() == view;
    }

    public Parcelable m435a() {
        return null;
    }

    public void m438a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public long m442b(int i) {
        return (long) i;
    }

    private static String m434a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }
}
