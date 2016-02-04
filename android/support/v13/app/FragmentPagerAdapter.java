package android.support.v13.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private final FragmentManager f9a;
    private FragmentTransaction f10b;
    private Fragment f11c;

    public abstract Fragment m66a(int i);

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.f10b = null;
        this.f11c = null;
        this.f9a = fragmentManager;
    }

    public void m70a(ViewGroup viewGroup) {
    }

    public Object m68a(ViewGroup viewGroup, int i) {
        if (this.f10b == null) {
            this.f10b = this.f9a.beginTransaction();
        }
        long b = m73b(i);
        Fragment findFragmentByTag = this.f9a.findFragmentByTag(m65a(viewGroup.getId(), b));
        if (findFragmentByTag != null) {
            this.f10b.attach(findFragmentByTag);
        } else {
            findFragmentByTag = m66a(i);
            this.f10b.add(viewGroup.getId(), findFragmentByTag, m65a(viewGroup.getId(), b));
        }
        if (findFragmentByTag != this.f11c) {
            FragmentCompat.m43a(findFragmentByTag, false);
            FragmentCompat.m44b(findFragmentByTag, false);
        }
        return findFragmentByTag;
    }

    public void m71a(ViewGroup viewGroup, int i, Object obj) {
        if (this.f10b == null) {
            this.f10b = this.f9a.beginTransaction();
        }
        this.f10b.detach((Fragment) obj);
    }

    public void m75b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f11c) {
            if (this.f11c != null) {
                FragmentCompat.m43a(this.f11c, false);
                FragmentCompat.m44b(this.f11c, false);
            }
            if (fragment != null) {
                FragmentCompat.m43a(fragment, true);
                FragmentCompat.m44b(fragment, true);
            }
            this.f11c = fragment;
        }
    }

    public void m74b(ViewGroup viewGroup) {
        if (this.f10b != null) {
            this.f10b.commitAllowingStateLoss();
            this.f10b = null;
            this.f9a.executePendingTransactions();
        }
    }

    public boolean m72a(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Parcelable m67a() {
        return null;
    }

    public void m69a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public long m73b(int i) {
        return (long) i;
    }

    private static String m65a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }
}
