package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.TokenIterator;
import cz.msebera.android.httpclient.util.Args;
import java.util.NoSuchElementException;

public class BasicTokenIterator implements TokenIterator {
    protected final HeaderIterator f7894a;
    protected String f7895b;
    protected String f7896c;
    protected int f7897d;

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.f7894a = (HeaderIterator) Args.m12722a((Object) headerIterator, "Header iterator");
        this.f7897d = m12635a(-1);
    }

    public boolean hasNext() {
        return this.f7896c != null;
    }

    public String m12636a() {
        if (this.f7896c == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        String str = this.f7896c;
        this.f7897d = m12635a(this.f7897d);
        return str;
    }

    public final Object next() {
        return m12636a();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    protected int m12635a(int i) {
        int c;
        if (i >= 0) {
            c = m12641c(i);
        } else if (!this.f7894a.hasNext()) {
            return -1;
        } else {
            this.f7895b = this.f7894a.m11373a().m11362d();
            c = 0;
        }
        int b = m12639b(c);
        if (b < 0) {
            this.f7896c = null;
            return -1;
        }
        c = m12643d(b);
        this.f7896c = m12637a(this.f7895b, b, c);
        return c;
    }

    protected String m12637a(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    protected int m12639b(int i) {
        int b = Args.m12726b(i, "Search position");
        Object obj = null;
        while (obj == null && this.f7895b != null) {
            int length = this.f7895b.length();
            Object obj2 = obj;
            int i2 = b;
            Object obj3 = obj2;
            while (obj3 == null && i2 < length) {
                char charAt = this.f7895b.charAt(i2);
                if (m12638a(charAt) || m12640b(charAt)) {
                    i2++;
                } else if (m12642c(this.f7895b.charAt(i2))) {
                    obj3 = 1;
                } else {
                    throw new ParseException("Invalid character before token (pos " + i2 + "): " + this.f7895b);
                }
            }
            if (obj3 == null) {
                if (this.f7894a.hasNext()) {
                    this.f7895b = this.f7894a.m11373a().m11362d();
                    i2 = 0;
                } else {
                    this.f7895b = null;
                }
            }
            obj2 = obj3;
            b = i2;
            obj = obj2;
        }
        return obj != null ? b : -1;
    }

    protected int m12641c(int i) {
        int b = Args.m12726b(i, "Search position");
        Object obj = null;
        int length = this.f7895b.length();
        while (obj == null && b < length) {
            char charAt = this.f7895b.charAt(b);
            if (m12638a(charAt)) {
                obj = 1;
            } else if (m12640b(charAt)) {
                b++;
            } else if (m12642c(charAt)) {
                throw new ParseException("Tokens without separator (pos " + b + "): " + this.f7895b);
            } else {
                throw new ParseException("Invalid character after token (pos " + b + "): " + this.f7895b);
            }
        }
        return b;
    }

    protected int m12643d(int i) {
        Args.m12726b(i, "Search position");
        int length = this.f7895b.length();
        int i2 = i + 1;
        while (i2 < length && m12642c(this.f7895b.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    protected boolean m12638a(char c) {
        return c == ',';
    }

    protected boolean m12640b(char c) {
        return c == '\t' || Character.isSpaceChar(c);
    }

    protected boolean m12642c(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        if (Character.isISOControl(c)) {
            return false;
        }
        if (m12644d(c)) {
            return false;
        }
        return true;
    }

    protected boolean m12644d(char c) {
        return " ,;=()<>@:\\\"/[]?{}\t".indexOf(c) >= 0;
    }
}
