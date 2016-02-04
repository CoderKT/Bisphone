package cz.msebera.android.httpclient.impl.conn.tsccm;

@Deprecated
public class WaitingThreadAborter {
    private WaitingThread f7745a;
    private boolean f7746b;

    public void m12385a() {
        this.f7746b = true;
        if (this.f7745a != null) {
            this.f7745a.m12384b();
        }
    }

    public void m12386a(WaitingThread waitingThread) {
        this.f7745a = waitingThread;
        if (this.f7746b) {
            waitingThread.m12384b();
        }
    }
}
