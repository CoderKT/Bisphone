package app.common.collection;

import android.os.Handler;
import android.os.Looper;
import app.Main;
import app.common.collection.ObservableCollectionItem.ObjectObserver;
import java.util.ArrayList;
import java.util.Collection;

public class ObserverArrayList<E> extends ArrayList<E> {
    private ArrayListListener f2020a;
    private boolean f2021b;
    private boolean f2022c;
    private Handler f2023d;

    /* renamed from: app.common.collection.ObserverArrayList.1 */
    class C01211 implements ObjectObserver {
        final /* synthetic */ ObserverArrayList f2018a;

        C01211(ObserverArrayList observerArrayList) {
            this.f2018a = observerArrayList;
        }

        public void m4058a() {
            if (this.f2018a.f2020a != null && this.f2018a.f2021b && !this.f2018a.f2022c) {
                this.f2018a.m4075d();
                this.f2018a.f2020a.m4066d();
            }
        }
    }

    /* renamed from: app.common.collection.ObserverArrayList.2 */
    class C01222 implements Runnable {
        final /* synthetic */ ObserverArrayList f2019a;

        C01222(ObserverArrayList observerArrayList) {
            this.f2019a = observerArrayList;
        }

        public void run() {
            this.f2019a.f2022c = false;
            Main.f1926a.m5683d("observer unlocked");
        }
    }

    public interface ArrayListListener {
        void m4059a();

        void m4060a(int i);

        void m4061a(int i, int i2);

        void m4062b();

        void m4063b(int i);

        void m4064c();

        void m4065c(int i);

        void m4066d();

        void m4067d(int i);

        void m4068e();
    }

    public ObserverArrayList() {
        this.f2021b = true;
        this.f2022c = false;
        m4073c();
    }

    private void m4073c() {
        if (this.f2023d == null) {
            this.f2023d = new Handler(Looper.getMainLooper());
        }
    }

    public boolean add(E e) {
        if (e == null) {
            throw new RuntimeException("opps");
        }
        m4070a((Object) e);
        boolean add = super.add(e);
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4059a();
        }
        return add;
    }

    public void add(int i, E e) {
        super.add(i, e);
        if (e == null) {
            throw new RuntimeException("opps");
        }
        m4070a((Object) e);
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4060a(i);
        }
        super.add(e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean addAll = super.addAll(collection);
        for (Object next : collection) {
            if (next != null) {
                m4070a(next);
            }
        }
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4062b();
        }
        return addAll;
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        boolean addAll = super.addAll(i, collection);
        for (Object next : collection) {
            if (next != null) {
                m4070a(next);
            }
        }
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4063b(i);
        }
        return addAll;
    }

    public E remove(int i) {
        E remove = super.remove(i);
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4065c(i);
        }
        return remove;
    }

    public boolean remove(Object obj) {
        boolean remove = super.remove(obj);
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4064c();
        }
        return remove;
    }

    protected void removeRange(int i, int i2) {
        super.removeRange(i, i2);
        if (this.f2020a != null && this.f2021b && !this.f2022c) {
            m4075d();
            this.f2020a.m4061a(i, i2);
        }
    }

    public E set(int i, E e) {
        E e2 = super.set(i, e);
        if (!(this.f2020a == null || !this.f2021b || this.f2022c)) {
            m4075d();
            this.f2020a.m4067d(i);
        }
        return e2;
    }

    public void clear() {
        super.clear();
        if (this.f2020a != null && this.f2021b && !this.f2022c) {
            m4075d();
            this.f2020a.m4068e();
        }
    }

    private void m4070a(E e) {
        if (e instanceof ObservableCollectionItem) {
            ((ObservableCollectionItem) e).m4057a(new C01211(this));
        } else {
            Main.f1926a.m5677a("item class must extended from 'ObservableCollectionItem' class");
        }
    }

    private void m4075d() {
        if (!this.f2022c) {
            this.f2022c = true;
            Main.f1926a.m5683d("observer locked");
            this.f2023d.postDelayed(new C01222(this), 100);
        }
    }

    public E m4077a() {
        return get(size() - 1);
    }

    public void m4080b() {
        if (this.f2020a != null && this.f2021b && !this.f2022c) {
            this.f2020a.m4064c();
        }
    }

    public void m4079a(boolean z) {
        this.f2021b = z;
    }

    public void m4078a(ArrayListListener arrayListListener) {
        this.f2020a = arrayListListener;
    }
}
