package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.util.Args;
import java.util.Date;
import java.util.concurrent.locks.Condition;

@Deprecated
public class WaitingThread {
    private final Condition f7741a;
    private final RouteSpecificPool f7742b;
    private Thread f7743c;
    private boolean f7744d;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        Args.m12722a((Object) condition, "Condition");
        this.f7741a = condition;
        this.f7742b = routeSpecificPool;
    }

    public boolean m12383a(Date date) {
        if (this.f7743c != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.f7743c);
        } else if (this.f7744d) {
            throw new InterruptedException("Operation interrupted");
        } else {
            boolean awaitUntil;
            this.f7743c = Thread.currentThread();
            if (date != null) {
                try {
                    awaitUntil = this.f7741a.awaitUntil(date);
                } catch (Throwable th) {
                    this.f7743c = null;
                }
            } else {
                this.f7741a.await();
                awaitUntil = true;
            }
            if (this.f7744d) {
                throw new InterruptedException("Operation interrupted");
            }
            this.f7743c = null;
            return awaitUntil;
        }
    }

    public void m12382a() {
        if (this.f7743c == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.f7741a.signalAll();
    }

    public void m12384b() {
        this.f7744d = true;
        this.f7741a.signalAll();
    }
}
