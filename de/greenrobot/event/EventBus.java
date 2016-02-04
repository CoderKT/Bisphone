package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import se.emilsjolander.stickylistheaders.C1128R;

public class EventBus {
    public static String f7942a;
    static volatile EventBus f7943b;
    private static final EventBusBuilder f7944c;
    private static final Map<Class<?>, List<Class<?>>> f7945d;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> f7946e;
    private final Map<Object, List<Class<?>>> f7947f;
    private final Map<Class<?>, Object> f7948g;
    private final ThreadLocal<PostingThreadState> f7949h;
    private final HandlerPoster f7950i;
    private final BackgroundPoster f7951j;
    private final AsyncPoster f7952k;
    private final SubscriberMethodFinder f7953l;
    private final ExecutorService f7954m;
    private final boolean f7955n;
    private final boolean f7956o;
    private final boolean f7957p;
    private final boolean f7958q;
    private final boolean f7959r;
    private final boolean f7960s;

    /* renamed from: de.greenrobot.event.EventBus.1 */
    class C09431 extends ThreadLocal<PostingThreadState> {
        final /* synthetic */ EventBus f7934a;

        C09431(EventBus eventBus) {
            this.f7934a = eventBus;
        }

        protected /* synthetic */ Object initialValue() {
            return m12778a();
        }

        protected PostingThreadState m12778a() {
            return new PostingThreadState();
        }
    }

    /* renamed from: de.greenrobot.event.EventBus.2 */
    /* synthetic */ class C09442 {
        static final /* synthetic */ int[] f7935a;

        static {
            f7935a = new int[ThreadMode.values().length];
            try {
                f7935a[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7935a[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7935a[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7935a[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    final class PostingThreadState {
        final List<Object> f7936a;
        boolean f7937b;
        boolean f7938c;
        Subscription f7939d;
        Object f7940e;
        boolean f7941f;

        PostingThreadState() {
            this.f7936a = new ArrayList();
        }
    }

    static {
        f7942a = "Event";
        f7944c = new EventBusBuilder();
        f7945d = new HashMap();
    }

    public static EventBus m12779a() {
        if (f7943b == null) {
            synchronized (EventBus.class) {
                if (f7943b == null) {
                    f7943b = new EventBus();
                }
            }
        }
        return f7943b;
    }

    public EventBus() {
        this(f7944c);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.f7949h = new C09431(this);
        this.f7946e = new HashMap();
        this.f7947f = new HashMap();
        this.f7948g = new ConcurrentHashMap();
        this.f7950i = new HandlerPoster(this, Looper.getMainLooper(), 10);
        this.f7951j = new BackgroundPoster(this);
        this.f7952k = new AsyncPoster(this);
        this.f7953l = new SubscriberMethodFinder(eventBusBuilder.f7969h);
        this.f7956o = eventBusBuilder.f7962a;
        this.f7957p = eventBusBuilder.f7963b;
        this.f7958q = eventBusBuilder.f7964c;
        this.f7959r = eventBusBuilder.f7965d;
        this.f7955n = eventBusBuilder.f7966e;
        this.f7960s = eventBusBuilder.f7967f;
        this.f7954m = eventBusBuilder.f7968g;
    }

    public void m12791a(Object obj) {
        m12786a(obj, false, 0);
    }

    private synchronized void m12786a(Object obj, boolean z, int i) {
        for (SubscriberMethod a : this.f7953l.m12805a(obj.getClass())) {
            m12784a(obj, a, z, i);
        }
    }

    private void m12784a(Object obj, SubscriberMethod subscriberMethod, boolean z, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class cls = subscriberMethod.f7988c;
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.f7946e.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod, i);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.f7946e.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else if (copyOnWriteArrayList2.contains(subscription)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (i2 <= size) {
            if (i2 == size || subscription.f7994c > ((Subscription) copyOnWriteArrayList.get(i2)).f7994c) {
                copyOnWriteArrayList.add(i2, subscription);
                break;
            }
            i2++;
        }
        List list = (List) this.f7947f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f7947f.put(obj, list);
        }
        list.add(cls);
        if (z) {
            Object obj2;
            synchronized (this.f7948g) {
                obj2 = this.f7948g.get(cls);
            }
            if (obj2 != null) {
                boolean z2;
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                m12782a(subscription, obj2, z2);
            }
        }
    }

    public synchronized boolean m12793b(Object obj) {
        return this.f7947f.containsKey(obj);
    }

    private void m12785a(Object obj, Class<?> cls) {
        List list = (List) this.f7946e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                Subscription subscription = (Subscription) list.get(i);
                if (subscription.f7992a == obj) {
                    subscription.f7995d = false;
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void m12794c(Object obj) {
        List<Class> list = (List) this.f7947f.get(obj);
        if (list != null) {
            for (Class a : list) {
                m12785a(obj, a);
            }
            this.f7947f.remove(obj);
        } else {
            Log.w(f7942a, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void m12795d(Object obj) {
        PostingThreadState postingThreadState = (PostingThreadState) this.f7949h.get();
        List list = postingThreadState.f7936a;
        list.add(obj);
        if (!postingThreadState.f7937b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            postingThreadState.f7938c = z;
            postingThreadState.f7937b = true;
            if (postingThreadState.f7941f) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    m12783a(list.remove(0), postingThreadState);
                } finally {
                    postingThreadState.f7937b = false;
                    postingThreadState.f7938c = false;
                }
            }
        }
    }

    public void m12796e(Object obj) {
        synchronized (this.f7948g) {
            this.f7948g.put(obj.getClass(), obj);
        }
        m12795d(obj);
    }

    public boolean m12797f(Object obj) {
        boolean z;
        synchronized (this.f7948g) {
            Class cls = obj.getClass();
            if (obj.equals(this.f7948g.get(cls))) {
                this.f7948g.remove(cls);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    private void m12783a(Object obj, PostingThreadState postingThreadState) {
        boolean z;
        Class cls = obj.getClass();
        if (this.f7960s) {
            List a = m12780a(cls);
            boolean z2 = false;
            for (int i = 0; i < a.size(); i++) {
                z2 |= m12788a(obj, postingThreadState, (Class) a.get(i));
            }
            z = z2;
        } else {
            z = m12788a(obj, postingThreadState, cls);
        }
        if (!z) {
            if (this.f7957p) {
                Log.d(f7942a, "No subscribers registered for event " + cls);
            }
            if (this.f7959r && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                m12795d(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean m12788a(Object obj, PostingThreadState postingThreadState, Class<?> cls) {
        synchronized (this) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.f7946e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        loop0:
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            postingThreadState.f7940e = obj;
            postingThreadState.f7939d = subscription;
            try {
                m12782a(subscription, obj, postingThreadState.f7938c);
                Object obj2 = postingThreadState.f7941f;
                continue;
            } finally {
                postingThreadState.f7940e = null;
                postingThreadState.f7939d = null;
                postingThreadState.f7941f = false;
            }
            if (obj2 != null) {
                break loop0;
            }
        }
        return true;
    }

    private void m12782a(Subscription subscription, Object obj, boolean z) {
        switch (C09442.f7935a[subscription.f7993b.f7987b.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m12790a(subscription, obj);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (z) {
                    m12790a(subscription, obj);
                } else {
                    this.f7950i.m12798a(subscription, obj);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (z) {
                    this.f7951j.m12777a(subscription, obj);
                } else {
                    m12790a(subscription, obj);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f7952k.m12776a(subscription, obj);
            default:
                throw new IllegalStateException("Unknown thread mode: " + subscription.f7993b.f7987b);
        }
    }

    private List<Class<?>> m12780a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (f7945d) {
            list = (List) f7945d.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    m12787a((List) list, cls2.getInterfaces());
                }
                f7945d.put(cls, list);
            }
        }
        return list;
    }

    static void m12787a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                m12787a((List) list, cls.getInterfaces());
            }
        }
    }

    void m12789a(PendingPost pendingPost) {
        Object obj = pendingPost.f7977a;
        Subscription subscription = pendingPost.f7978b;
        PendingPost.m12800a(pendingPost);
        if (subscription.f7995d) {
            m12790a(subscription, obj);
        }
    }

    void m12790a(Subscription subscription, Object obj) {
        try {
            subscription.f7993b.f7986a.invoke(subscription.f7992a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            m12781a(subscription, obj, e.getCause());
        } catch (Throwable e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void m12781a(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.f7956o) {
                Log.e(f7942a, "SubscriberExceptionEvent subscriber " + subscription.f7992a.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Log.e(f7942a, "Initial event " + subscriberExceptionEvent.f7984c + " caused exception in " + subscriberExceptionEvent.f7985d, subscriberExceptionEvent.f7983b);
            }
        } else if (this.f7955n) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.f7956o) {
                Log.e(f7942a, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.f7992a.getClass(), th);
            }
            if (this.f7958q) {
                m12795d(new SubscriberExceptionEvent(this, th, obj, subscription.f7992a));
            }
        }
    }

    ExecutorService m12792b() {
        return this.f7954m;
    }
}
