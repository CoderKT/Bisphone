package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private final FragmentManager f221a;
    private FragmentTransaction f222b;
    private ArrayList<SavedState> f223c;
    private ArrayList<Fragment> f224d;
    private Fragment f225e;

    public abstract Fragment m449a(int i);

    public FragmentStatePagerAdapter(FragmentManager fragmentManager) {
        this.f222b = null;
        this.f223c = new ArrayList();
        this.f224d = new ArrayList();
        this.f225e = null;
        this.f221a = fragmentManager;
    }

    public void m452a(ViewGroup viewGroup) {
    }

    public Object m450a(ViewGroup viewGroup, int i) {
        if (this.f224d.size() > i) {
            Fragment fragment = (Fragment) this.f224d.get(i);
            if (fragment != null) {
                return fragment;
            }
        }
        if (this.f222b == null) {
            this.f222b = this.f221a.m353a();
        }
        Fragment a = m449a(i);
        if (this.f223c.size() > i) {
            SavedState savedState = (SavedState) this.f223c.get(i);
            if (savedState != null) {
                a.m196a(savedState);
            }
        }
        while (this.f224d.size() <= i) {
            this.f224d.add(null);
        }
        a.m219e(false);
        a.m221f(false);
        this.f224d.set(i, a);
        this.f222b.m110a(viewGroup.getId(), a);
        return a;
    }

    public void m453a(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f222b == null) {
            this.f222b = this.f221a.m353a();
        }
        while (this.f223c.size() <= i) {
            this.f223c.add(null);
        }
        this.f223c.set(i, this.f221a.m350a(fragment));
        this.f224d.set(i, null);
        this.f222b.m112a(fragment);
    }

    public void m456b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f225e) {
            if (this.f225e != null) {
                this.f225e.m219e(false);
                this.f225e.m221f(false);
            }
            if (fragment != null) {
                fragment.m219e(true);
                fragment.m221f(true);
            }
            this.f225e = fragment;
        }
    }

    public void m455b(ViewGroup viewGroup) {
        if (this.f222b != null) {
            this.f222b.m114b();
            this.f222b = null;
            this.f221a.m357b();
        }
    }

    public boolean m454a(View view, Object obj) {
        return ((Fragment) obj).m237p() == view;
    }

    public Parcelable m448a() {
        Bundle bundle = null;
        if (this.f223c.size() > 0) {
            bundle = new Bundle();
            Parcelable[] parcelableArr = new SavedState[this.f223c.size()];
            this.f223c.toArray(parcelableArr);
            bundle.putParcelableArray("states", parcelableArr);
        }
        Parcelable parcelable = bundle;
        for (int i = 0; i < this.f224d.size(); i++) {
            Fragment fragment = (Fragment) this.f224d.get(i);
            if (fragment != null && fragment.m234m()) {
                if (parcelable == null) {
                    parcelable = new Bundle();
                }
                this.f221a.m355a(parcelable, "f" + i, fragment);
            }
        }
        return parcelable;
    }

    public void m451a(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.f223c.clear();
            this.f224d.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.f223c.add((SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment a = this.f221a.m351a(bundle, str);
                    if (a != null) {
                        while (this.f224d.size() <= parseInt) {
                            this.f224d.add(null);
                        }
                        a.m219e(false);
                        this.f224d.set(parseInt, a);
                    } else {
                        Log.w("FragmentStatePagerAdapter", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }
}
