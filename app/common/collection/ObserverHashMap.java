package app.common.collection;

import android.os.Handler;
import android.os.Looper;
import app.Main;
import app.common.collection.ObservableCollectionItem.ObjectObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

public class ObserverHashMap<K, V> extends HashMap<K, V> {
    private List<HashMapListener> f2031a;
    private boolean f2032b;
    private boolean f2033c;
    private Handler f2034d;

    /* renamed from: app.common.collection.ObserverHashMap.1 */
    class C01231 implements ObjectObserver {
        final /* synthetic */ ObserverHashMap f2024a;

        C01231(ObserverHashMap observerHashMap) {
            this.f2024a = observerHashMap;
        }

        public void m4081a() {
            this.f2024a.m4087a(ObserverMethod.ON_ENTRY_CHANGED);
        }
    }

    /* renamed from: app.common.collection.ObserverHashMap.3 */
    /* synthetic */ class C01243 {
        static final /* synthetic */ int[] f2025a;

        static {
            f2025a = new int[ObserverMethod.values().length];
            try {
                f2025a[ObserverMethod.ON_ENTRY_ADD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2025a[ObserverMethod.ON_ENTRY_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2025a[ObserverMethod.ON_ENTRY_REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2025a[ObserverMethod.ON_MAP_CLEARED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public interface HashMapListener {
        void m4082a();

        void m4083b();

        void m4084c();

        void m4085d();
    }

    public enum ObserverMethod {
        ON_ENTRY_ADD,
        ON_ENTRY_REMOVED,
        ON_MAP_CLEARED,
        ON_ENTRY_CHANGED
    }

    public ObserverHashMap() {
        this.f2032b = true;
        this.f2033c = false;
        m4086a();
    }

    private void m4086a() {
        this.f2031a = new ArrayList();
        if (this.f2034d == null) {
            this.f2034d = new Handler(Looper.getMainLooper());
        }
    }

    public V put(K k, V v) {
        if (v == null) {
            throw new RuntimeException("opps");
        }
        m4089a((Object) v);
        V put = super.put(k, v);
        m4087a(ObserverMethod.ON_ENTRY_ADD);
        return put;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        super.putAll(map);
        m4087a(ObserverMethod.ON_ENTRY_ADD);
    }

    public V remove(Object obj) {
        V remove = super.remove(obj);
        m4087a(ObserverMethod.ON_ENTRY_REMOVED);
        return remove;
    }

    public void clear() {
        super.clear();
        m4087a(ObserverMethod.ON_MAP_CLEARED);
    }

    private void m4089a(V v) {
        if (v instanceof ObservableCollectionItem) {
            ((ObservableCollectionItem) v).m4057a(new C01231(this));
        } else {
            Main.f1926a.m5677a("item class must extended from 'ObservableCollectionItem' class");
        }
    }

    private void m4087a(ObserverMethod observerMethod) {
        if (!this.f2031a.isEmpty() && this.f2032b && !this.f2033c) {
            for (HashMapListener hashMapListener : this.f2031a) {
                switch (C01243.f2025a[observerMethod.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        hashMapListener.m4082a();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        hashMapListener.m4085d();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        hashMapListener.m4083b();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        hashMapListener.m4084c();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void m4091a(boolean z) {
        this.f2032b = z;
    }

    public void m4090a(HashMapListener hashMapListener) {
        if (this.f2031a == null) {
            this.f2031a = new ArrayList();
        }
        this.f2031a.add(hashMapListener);
    }
}
