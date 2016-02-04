package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry;
import cz.msebera.android.httpclient.impl.conn.AbstractPooledConnAdapter;

@Deprecated
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager threadSafeClientConnManager, AbstractPoolEntry abstractPoolEntry) {
        super(threadSafeClientConnManager, abstractPoolEntry);
        m12186k();
    }

    protected ClientConnectionManager m12337p() {
        return super.m12191p();
    }

    protected AbstractPoolEntry m12338s() {
        return super.m12210s();
    }

    protected void m12336n() {
        super.m12209n();
    }
}
