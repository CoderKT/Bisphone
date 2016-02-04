package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicListHeaderIterator implements HeaderIterator {
    protected final List<Header> f7882a;
    protected int f7883b;
    protected int f7884c;
    protected String f7885d;

    public BasicListHeaderIterator(List<Header> list, String str) {
        this.f7882a = (List) Args.m12722a((Object) list, "Header list");
        this.f7885d = str;
        this.f7883b = m12624a(-1);
        this.f7884c = -1;
    }

    protected int m12624a(int i) {
        if (i < -1) {
            return -1;
        }
        int size = this.f7882a.size() - 1;
        boolean z = false;
        int i2 = i;
        while (!z && i2 < size) {
            i = i2 + 1;
            z = m12626b(i);
            i2 = i;
        }
        if (!z) {
            i2 = -1;
        }
        return i2;
    }

    protected boolean m12626b(int i) {
        if (this.f7885d == null) {
            return true;
        }
        return this.f7885d.equalsIgnoreCase(((Header) this.f7882a.get(i)).m11361c());
    }

    public boolean hasNext() {
        return this.f7883b >= 0;
    }

    public Header m12625a() {
        int i = this.f7883b;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.f7884c = i;
        this.f7883b = m12624a(i);
        return (Header) this.f7882a.get(i);
    }

    public final Object next() {
        return m12625a();
    }

    public void remove() {
        Asserts.m12729a(this.f7884c >= 0, "No header to remove");
        this.f7882a.remove(this.f7884c);
        this.f7884c = -1;
        this.f7883b--;
    }
}
