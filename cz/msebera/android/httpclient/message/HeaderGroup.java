package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeaderGroup implements Serializable, Cloneable {
    private final List<Header> f7901a;

    public HeaderGroup() {
        this.f7901a = new ArrayList(16);
    }

    public void m12650a() {
        this.f7901a.clear();
    }

    public void m12651a(Header header) {
        if (header != null) {
            this.f7901a.add(header);
        }
    }

    public void m12655b(Header header) {
        if (header != null) {
            this.f7901a.remove(header);
        }
    }

    public void m12658c(Header header) {
        if (header != null) {
            for (int i = 0; i < this.f7901a.size(); i++) {
                if (((Header) this.f7901a.get(i)).m11361c().equalsIgnoreCase(header.m11361c())) {
                    this.f7901a.set(i, header);
                    return;
                }
            }
            this.f7901a.add(header);
        }
    }

    public void m12652a(Header[] headerArr) {
        m12650a();
        if (headerArr != null) {
            Collections.addAll(this.f7901a, headerArr);
        }
    }

    public Header[] m12653a(String str) {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.f7901a.size(); i++) {
            Header header = (Header) this.f7901a.get(i);
            if (header.m11361c().equalsIgnoreCase(str)) {
                arrayList.add(header);
            }
        }
        return (Header[]) arrayList.toArray(new Header[arrayList.size()]);
    }

    public Header m12654b(String str) {
        for (int i = 0; i < this.f7901a.size(); i++) {
            Header header = (Header) this.f7901a.get(i);
            if (header.m11361c().equalsIgnoreCase(str)) {
                return header;
            }
        }
        return null;
    }

    public Header[] m12656b() {
        return (Header[]) this.f7901a.toArray(new Header[this.f7901a.size()]);
    }

    public boolean m12659c(String str) {
        for (int i = 0; i < this.f7901a.size(); i++) {
            if (((Header) this.f7901a.get(i)).m11361c().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public HeaderIterator m12657c() {
        return new BasicListHeaderIterator(this.f7901a, null);
    }

    public HeaderIterator m12660d(String str) {
        return new BasicListHeaderIterator(this.f7901a, str);
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        return this.f7901a.toString();
    }
}
