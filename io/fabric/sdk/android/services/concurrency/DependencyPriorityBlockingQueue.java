package io.fabric.sdk.android.services.concurrency;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import se.emilsjolander.stickylistheaders.C1128R;

public class DependencyPriorityBlockingQueue<E extends Dependency & Task & PriorityProvider> extends PriorityBlockingQueue<E> {
    final Queue<E> f8299a;
    private final ReentrantLock f8300b;

    public /* synthetic */ Object peek() {
        return m13116b();
    }

    public /* synthetic */ Object poll() {
        return m13118c();
    }

    public /* synthetic */ Object poll(long j, TimeUnit timeUnit) {
        return m13112a(j, timeUnit);
    }

    public /* synthetic */ Object take() {
        return m13110a();
    }

    public DependencyPriorityBlockingQueue() {
        this.f8299a = new LinkedList();
        this.f8300b = new ReentrantLock();
    }

    public E m13110a() {
        return m13117b(0, null, null);
    }

    public E m13116b() {
        E e = null;
        try {
            e = m13117b(1, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public E m13112a(long j, TimeUnit timeUnit) {
        return m13117b(3, Long.valueOf(j), timeUnit);
    }

    public E m13118c() {
        E e = null;
        try {
            e = m13117b(2, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public int size() {
        try {
            this.f8300b.lock();
            int size = this.f8299a.size() + super.size();
            return size;
        } finally {
            this.f8300b.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        try {
            this.f8300b.lock();
            T[] a = m13115a(super.toArray(tArr), this.f8299a.toArray(tArr));
            return a;
        } finally {
            this.f8300b.unlock();
        }
    }

    public Object[] toArray() {
        try {
            this.f8300b.lock();
            Object[] a = m13115a(super.toArray(), this.f8299a.toArray());
            return a;
        } finally {
            this.f8300b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        try {
            this.f8300b.lock();
            int drainTo = super.drainTo(collection) + this.f8299a.size();
            while (!this.f8299a.isEmpty()) {
                collection.add(this.f8299a.poll());
            }
            return drainTo;
        } finally {
            this.f8300b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection, int i) {
        try {
            this.f8300b.lock();
            int drainTo = super.drainTo(collection, i);
            while (!this.f8299a.isEmpty() && drainTo <= i) {
                collection.add(this.f8299a.poll());
                drainTo++;
            }
            this.f8300b.unlock();
            return drainTo;
        } catch (Throwable th) {
            this.f8300b.unlock();
        }
    }

    public boolean contains(Object obj) {
        try {
            this.f8300b.lock();
            boolean z = super.contains(obj) || this.f8299a.contains(obj);
            this.f8300b.unlock();
            return z;
        } catch (Throwable th) {
            this.f8300b.unlock();
        }
    }

    public void clear() {
        try {
            this.f8300b.lock();
            this.f8299a.clear();
            super.clear();
        } finally {
            this.f8300b.unlock();
        }
    }

    public boolean remove(Object obj) {
        try {
            this.f8300b.lock();
            boolean z = super.remove(obj) || this.f8299a.remove(obj);
            this.f8300b.unlock();
            return z;
        } catch (Throwable th) {
            this.f8300b.unlock();
        }
    }

    public boolean removeAll(Collection<?> collection) {
        try {
            this.f8300b.lock();
            boolean removeAll = super.removeAll(collection) | this.f8299a.removeAll(collection);
            return removeAll;
        } finally {
            this.f8300b.unlock();
        }
    }

    E m13111a(int i, Long l, TimeUnit timeUnit) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return (Dependency) super.take();
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return (Dependency) super.peek();
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return (Dependency) super.poll();
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return (Dependency) super.poll(l.longValue(), timeUnit);
            default:
                return null;
        }
    }

    boolean m13113a(int i, E e) {
        try {
            this.f8300b.lock();
            if (i == 1) {
                super.remove(e);
            }
            boolean offer = this.f8299a.offer(e);
            return offer;
        } finally {
            this.f8300b.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    E m13117b(int r3, java.lang.Long r4, java.util.concurrent.TimeUnit r5) {
        /*
        r2 = this;
    L_0x0000:
        r0 = r2.m13111a(r3, r4, r5);
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r1 = r2.m13114a(r0);
        if (r1 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r2.m13113a(r3, r0);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.concurrency.DependencyPriorityBlockingQueue.b(int, java.lang.Long, java.util.concurrent.TimeUnit):E");
    }

    boolean m13114a(E e) {
        return e.m7831d();
    }

    public void m13119d() {
        try {
            this.f8300b.lock();
            Iterator it = this.f8299a.iterator();
            while (it.hasNext()) {
                Dependency dependency = (Dependency) it.next();
                if (m13114a(dependency)) {
                    super.offer(dependency);
                    it.remove();
                }
            }
        } finally {
            this.f8300b.unlock();
        }
    }

    <T> T[] m13115a(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + length2);
        System.arraycopy(tArr, 0, objArr, 0, length);
        System.arraycopy(tArr2, 0, objArr, length, length2);
        return objArr;
    }
}
