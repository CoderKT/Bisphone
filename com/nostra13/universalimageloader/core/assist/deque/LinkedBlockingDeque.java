package com.nostra13.universalimageloader.core.assist.deque;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements BlockingDeque<E>, Serializable {
    transient Node<E> f7038a;
    transient Node<E> f7039b;
    final ReentrantLock f7040c;
    private transient int f7041d;
    private final int f7042e;
    private final Condition f7043f;
    private final Condition f7044g;

    abstract class AbstractItr implements Iterator<E> {
        Node<E> f7045a;
        E f7046b;
        final /* synthetic */ LinkedBlockingDeque f7047c;
        private Node<E> f7048d;

        abstract Node<E> m11190a();

        abstract Node<E> m11191a(Node<E> node);

        AbstractItr(LinkedBlockingDeque linkedBlockingDeque) {
            this.f7047c = linkedBlockingDeque;
            ReentrantLock reentrantLock = linkedBlockingDeque.f7040c;
            reentrantLock.lock();
            try {
                this.f7045a = m11190a();
                this.f7046b = this.f7045a == null ? null : this.f7045a.f7050a;
            } finally {
                reentrantLock.unlock();
            }
        }

        private Node<E> m11189b(Node<E> node) {
            while (true) {
                Node<E> a = m11191a(node);
                if (a == null) {
                    return null;
                }
                if (a.f7050a != null) {
                    return a;
                }
                if (a == node) {
                    return m11190a();
                }
                node = a;
            }
        }

        void m11192b() {
            ReentrantLock reentrantLock = this.f7047c.f7040c;
            reentrantLock.lock();
            try {
                this.f7045a = m11189b(this.f7045a);
                this.f7046b = this.f7045a == null ? null : this.f7045a.f7050a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.f7045a != null;
        }

        public E next() {
            if (this.f7045a == null) {
                throw new NoSuchElementException();
            }
            this.f7048d = this.f7045a;
            E e = this.f7046b;
            m11192b();
            return e;
        }

        public void remove() {
            Node node = this.f7048d;
            if (node == null) {
                throw new IllegalStateException();
            }
            this.f7048d = null;
            ReentrantLock reentrantLock = this.f7047c.f7040c;
            reentrantLock.lock();
            try {
                if (node.f7050a != null) {
                    this.f7047c.m11178a(node);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    class Itr extends AbstractItr {
        final /* synthetic */ LinkedBlockingDeque f7049d;

        private Itr(LinkedBlockingDeque linkedBlockingDeque) {
            this.f7049d = linkedBlockingDeque;
            super(linkedBlockingDeque);
        }

        Node<E> m11193a() {
            return this.f7049d.f7038a;
        }

        Node<E> m11194a(Node<E> node) {
            return node.f7052c;
        }
    }

    final class Node<E> {
        E f7050a;
        Node<E> f7051b;
        Node<E> f7052c;

        Node(E e) {
            this.f7050a = e;
        }
    }

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int i) {
        this.f7040c = new ReentrantLock();
        this.f7043f = this.f7040c.newCondition();
        this.f7044g = this.f7040c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.f7042e = i;
    }

    private boolean m11172b(Node<E> node) {
        if (this.f7041d >= this.f7042e) {
            return false;
        }
        Node node2 = this.f7038a;
        node.f7052c = node2;
        this.f7038a = node;
        if (this.f7039b == null) {
            this.f7039b = node;
        } else {
            node2.f7051b = node;
        }
        this.f7041d++;
        this.f7043f.signal();
        return true;
    }

    private boolean m11173c(Node<E> node) {
        if (this.f7041d >= this.f7042e) {
            return false;
        }
        Node node2 = this.f7039b;
        node.f7051b = node2;
        this.f7039b = node;
        if (this.f7038a == null) {
            this.f7038a = node;
        } else {
            node2.f7052c = node;
        }
        this.f7041d++;
        this.f7043f.signal();
        return true;
    }

    private E m11174f() {
        Node node = this.f7038a;
        if (node == null) {
            return null;
        }
        Node node2 = node.f7052c;
        E e = node.f7050a;
        node.f7050a = null;
        node.f7052c = node;
        this.f7038a = node2;
        if (node2 == null) {
            this.f7039b = null;
        } else {
            node2.f7051b = null;
        }
        this.f7041d--;
        this.f7044g.signal();
        return e;
    }

    private E m11175g() {
        Node node = this.f7039b;
        if (node == null) {
            return null;
        }
        Node node2 = node.f7051b;
        E e = node.f7050a;
        node.f7050a = null;
        node.f7051b = node;
        this.f7039b = node2;
        if (node2 == null) {
            this.f7038a = null;
        } else {
            node2.f7052c = null;
        }
        this.f7041d--;
        this.f7044g.signal();
        return e;
    }

    void m11178a(Node<E> node) {
        Node node2 = node.f7051b;
        Node node3 = node.f7052c;
        if (node2 == null) {
            m11174f();
        } else if (node3 == null) {
            m11175g();
        } else {
            node2.f7052c = node3;
            node3.f7051b = node2;
            node.f7050a = null;
            this.f7041d--;
            this.f7044g.signal();
        }
    }

    public void m11179a(E e) {
        if (!m11184c((Object) e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean m11182b(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            boolean b = m11172b(node);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean m11184c(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            boolean c = m11173c(node);
            return c;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void m11186d(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        while (!m11173c(node)) {
            try {
                this.f7044g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean m11180a(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node node = new Node(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lockInterruptibly();
        while (!m11173c(node)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.f7044g.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E m11176a() {
        E b = m11181b();
        if (b != null) {
            return b;
        }
        throw new NoSuchElementException();
    }

    public E m11181b() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            E f = m11174f();
            return f;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E m11183c() {
        E f;
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        while (true) {
            try {
                f = m11174f();
                if (f != null) {
                    break;
                }
                this.f7043f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return f;
    }

    public E m11177a(long j, TimeUnit timeUnit) {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E f = m11174f();
                if (f != null) {
                    reentrantLock.unlock();
                    return f;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.f7043f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E m11185d() {
        E e = m11187e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public E m11187e() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            E e = this.f7038a == null ? null : this.f7038a.f7050a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean m11188e(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            for (Node node = this.f7038a; node != null; node = node.f7052c) {
                if (obj.equals(node.f7050a)) {
                    m11178a(node);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        m11179a((Object) e);
        return true;
    }

    public boolean offer(E e) {
        return m11184c((Object) e);
    }

    public void put(E e) {
        m11186d(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return m11180a(e, j, timeUnit);
    }

    public E remove() {
        return m11176a();
    }

    public E poll() {
        return m11181b();
    }

    public E take() {
        return m11183c();
    }

    public E poll(long j, TimeUnit timeUnit) {
        return m11177a(j, timeUnit);
    }

    public E element() {
        return m11185d();
    }

    public E peek() {
        return m11187e();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            int i = this.f7042e - this.f7041d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.f7040c;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.f7041d);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.f7038a.f7050a);
                    m11174f();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean remove(Object obj) {
        return m11188e(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            int i = this.f7041d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            for (Node node = this.f7038a; node != null; node = node.f7052c) {
                if (obj.equals(node.f7050a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.f7041d];
            int i = 0;
            Node node = this.f7038a;
            while (node != null) {
                int i2 = i + 1;
                objArr[i] = node.f7050a;
                node = node.f7052c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.f7041d) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f7041d);
            }
            int i = 0;
            Node node = this.f7038a;
            while (node != null) {
                int i2 = i + 1;
                tArr[i] = node.f7050a;
                node = node.f7052c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            String str;
            Node node = this.f7038a;
            if (node == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                Node node2 = node;
                while (true) {
                    Object obj = node2.f7050a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    node = node2.f7052c;
                    if (node == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    node2 = node;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.f7040c;
        reentrantLock.lock();
        try {
            Node node = this.f7038a;
            while (node != null) {
                node.f7050a = null;
                Node node2 = node.f7052c;
                node.f7051b = null;
                node.f7052c = null;
                node = node2;
            }
            this.f7039b = null;
            this.f7038a = null;
            this.f7041d = 0;
            this.f7044g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new Itr();
    }
}
