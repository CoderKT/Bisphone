package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    private DataSetObservable f8a;

    public abstract boolean m57a(View view, Object obj);

    public abstract int getCount();

    public PagerAdapter() {
        this.f8a = new DataSetObservable();
    }

    public void m55a(ViewGroup viewGroup) {
        m53a((View) viewGroup);
    }

    public Object m50a(ViewGroup viewGroup, int i) {
        return m49a((View) viewGroup, i);
    }

    public void m56a(ViewGroup viewGroup, int i, Object obj) {
        m54a((View) viewGroup, i, obj);
    }

    public void m63b(ViewGroup viewGroup, int i, Object obj) {
        m61b((View) viewGroup, i, obj);
    }

    public void m62b(ViewGroup viewGroup) {
        m60b((View) viewGroup);
    }

    public void m53a(View view) {
    }

    public Object m49a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public void m54a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void m61b(View view, int i, Object obj) {
    }

    public void m60b(View view) {
    }

    public Parcelable m48a() {
        return null;
    }

    public void m52a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public int m47a(Object obj) {
        return -1;
    }

    public void m58b() {
        this.f8a.notifyChanged();
    }

    public void m51a(DataSetObserver dataSetObserver) {
        this.f8a.registerObserver(dataSetObserver);
    }

    public void m59b(DataSetObserver dataSetObserver) {
        this.f8a.unregisterObserver(dataSetObserver);
    }

    public float m64c(int i) {
        return 1.0f;
    }
}
