package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.MalformedChunkCodingException;
import cz.msebera.android.httpclient.TruncatedChunkException;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import se.emilsjolander.stickylistheaders.C1128R;

public class ChunkedInputStream extends InputStream {
    private final SessionInputBuffer f7822a;
    private final CharArrayBuffer f7823b;
    private int f7824c;
    private int f7825d;
    private int f7826e;
    private boolean f7827f;
    private boolean f7828g;
    private Header[] f7829h;

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        this.f7827f = false;
        this.f7828g = false;
        this.f7829h = new Header[0];
        this.f7822a = (SessionInputBuffer) Args.m12722a((Object) sessionInputBuffer, "Session input buffer");
        this.f7826e = 0;
        this.f7823b = new CharArrayBuffer(16);
        this.f7824c = 1;
    }

    public int available() {
        if (this.f7822a instanceof BufferInfo) {
            return Math.min(((BufferInfo) this.f7822a).m12521e(), this.f7825d - this.f7826e);
        }
        return 0;
    }

    public int read() {
        if (this.f7828g) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f7827f) {
            return -1;
        } else {
            if (this.f7824c != 2) {
                m12549a();
                if (this.f7827f) {
                    return -1;
                }
            }
            int a = this.f7822a.m12265a();
            if (a != -1) {
                this.f7826e++;
                if (this.f7826e >= this.f7825d) {
                    this.f7824c = 3;
                }
            }
            return a;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f7828g) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f7827f) {
            return -1;
        } else {
            if (this.f7824c != 2) {
                m12549a();
                if (this.f7827f) {
                    return -1;
                }
            }
            int a = this.f7822a.m12267a(bArr, i, Math.min(i2, this.f7825d - this.f7826e));
            if (a != -1) {
                this.f7826e += a;
                if (this.f7826e >= this.f7825d) {
                    this.f7824c = 3;
                }
                return a;
            }
            this.f7827f = true;
            throw new TruncatedChunkException("Truncated chunk ( expected size: " + this.f7825d + "; actual size: " + this.f7826e + ")");
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    private void m12549a() {
        this.f7825d = m12550b();
        if (this.f7825d < 0) {
            throw new MalformedChunkCodingException("Negative chunk size");
        }
        this.f7824c = 2;
        this.f7826e = 0;
        if (this.f7825d == 0) {
            this.f7827f = true;
            m12551c();
        }
    }

    private int m12550b() {
        int i = 0;
        switch (this.f7824c) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f7823b.m12747a();
                if (this.f7822a.m12266a(this.f7823b) != -1) {
                    if (this.f7823b.m12759d()) {
                        this.f7824c = 1;
                    } else {
                        throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                    }
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f7823b.m12747a();
                if (this.f7822a.m12266a(this.f7823b) != -1) {
                    i = this.f7823b.m12758c(59);
                    if (i < 0) {
                        i = this.f7823b.m12757c();
                    }
                    try {
                        i = Integer.parseInt(this.f7823b.m12754b(0, i), 16);
                        break;
                    } catch (NumberFormatException e) {
                        throw new MalformedChunkCodingException("Bad chunk header");
                    }
                }
                break;
            default:
                throw new IllegalStateException("Inconsistent codec state");
        }
        return i;
    }

    private void m12551c() {
        try {
            this.f7829h = AbstractMessageParser.m12242a(this.f7822a, -1, -1, null);
        } catch (Throwable e) {
            IOException malformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + e.getMessage());
            malformedChunkCodingException.initCause(e);
            throw malformedChunkCodingException;
        }
    }

    public void close() {
        if (!this.f7828g) {
            try {
                if (!this.f7827f) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
                this.f7827f = true;
                this.f7828g = true;
            } catch (Throwable th) {
                this.f7827f = true;
                this.f7828g = true;
            }
        }
    }
}
