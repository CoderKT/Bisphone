package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HeaderElementIterator;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.NoSuchElementException;

public class BasicHeaderElementIterator implements HeaderElementIterator {
    private final HeaderIterator f7857a;
    private final HeaderValueParser f7858b;
    private HeaderElement f7859c;
    private CharArrayBuffer f7860d;
    private ParserCursor f7861e;

    public BasicHeaderElementIterator(HeaderIterator headerIterator, HeaderValueParser headerValueParser) {
        this.f7859c = null;
        this.f7860d = null;
        this.f7861e = null;
        this.f7857a = (HeaderIterator) Args.m12722a((Object) headerIterator, "Header iterator");
        this.f7858b = (HeaderValueParser) Args.m12722a((Object) headerValueParser, "Parser");
    }

    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, BasicHeaderValueParser.f7865b);
    }

    private void m12573b() {
        this.f7861e = null;
        this.f7860d = null;
        while (this.f7857a.hasNext()) {
            Header a = this.f7857a.m11373a();
            if (a instanceof FormattedHeader) {
                this.f7860d = ((FormattedHeader) a).m11364a();
                this.f7861e = new ParserCursor(0, this.f7860d.m12757c());
                this.f7861e.m12662a(((FormattedHeader) a).m11365b());
                return;
            }
            String d = a.m11362d();
            if (d != null) {
                this.f7860d = new CharArrayBuffer(d.length());
                this.f7860d.m12751a(d);
                this.f7861e = new ParserCursor(0, this.f7860d.m12757c());
                return;
            }
        }
    }

    private void m12574c() {
        HeaderElement b;
        loop0:
        while (true) {
            if (this.f7857a.hasNext() || this.f7861e != null) {
                if (this.f7861e == null || this.f7861e.m12664c()) {
                    m12573b();
                }
                if (this.f7861e != null) {
                    while (!this.f7861e.m12664c()) {
                        b = this.f7858b.m12586b(this.f7860d, this.f7861e);
                        if (b.m11368a().length() == 0) {
                            if (b.m11369b() != null) {
                                break loop0;
                            }
                        }
                        break loop0;
                    }
                    if (this.f7861e.m12664c()) {
                        this.f7861e = null;
                        this.f7860d = null;
                    }
                }
            } else {
                return;
            }
        }
        this.f7859c = b;
    }

    public boolean hasNext() {
        if (this.f7859c == null) {
            m12574c();
        }
        return this.f7859c != null;
    }

    public HeaderElement m12575a() {
        if (this.f7859c == null) {
            m12574c();
        }
        if (this.f7859c == null) {
            throw new NoSuchElementException("No more header elements available");
        }
        HeaderElement headerElement = this.f7859c;
        this.f7859c = null;
        return headerElement;
    }

    public final Object next() {
        return m12575a();
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
