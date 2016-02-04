package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.EncodingUtils;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.AuthMechanism;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.Response;
import org.jivesoftware.smack.util.StringUtils;

public class DigestScheme extends RFC2617Scheme {
    private static final char[] f7464a;
    private boolean f7465b;
    private String f7466c;
    private long f7467d;
    private String f7468e;
    private String f7469f;
    private String f7470g;

    static {
        f7464a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public DigestScheme(Charset charset) {
        super(charset);
        this.f7465b = false;
    }

    public DigestScheme() {
        this(Consts.f7198b);
    }

    public void m11908a(Header header) {
        super.m11884a(header);
        this.f7465b = true;
    }

    public boolean m11910d() {
        if ("true".equalsIgnoreCase(m11888a("stale"))) {
            return false;
        }
        return this.f7465b;
    }

    public String m11907a() {
        return "digest";
    }

    public boolean m11909c() {
        return false;
    }

    @Deprecated
    public Header m11905a(Credentials credentials, HttpRequest httpRequest) {
        return m11906a(credentials, httpRequest, new BasicHttpContext());
    }

    public Header m11906a(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Args.m12722a((Object) credentials, "Credentials");
        Args.m12722a((Object) httpRequest, "HTTP request");
        if (m11888a("realm") == null) {
            throw new AuthenticationException("missing realm in challenge");
        } else if (m11888a("nonce") == null) {
            throw new AuthenticationException("missing nonce in challenge");
        } else {
            m11892h().put("methodname", httpRequest.m10637h().m11406a());
            m11892h().put("uri", httpRequest.m10637h().m11408c());
            if (m11888a("charset") == null) {
                m11892h().put("charset", m11887a(httpRequest));
            }
            return m11902b(credentials, httpRequest);
        }
    }

    private static MessageDigest m11903b(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    private Header m11902b(Credentials credentials, HttpRequest httpRequest) {
        int i;
        String a = m11888a("uri");
        String a2 = m11888a("realm");
        String a3 = m11888a("nonce");
        String a4 = m11888a("opaque");
        String a5 = m11888a("methodname");
        String a6 = m11888a("algorithm");
        if (a6 == null) {
            a6 = StringUtils.MD5;
        }
        Set hashSet = new HashSet(8);
        int i2 = -1;
        String a7 = m11888a("qop");
        if (a7 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(a7, ",");
            while (stringTokenizer.hasMoreTokens()) {
                hashSet.add(stringTokenizer.nextToken().trim().toLowerCase(Locale.ENGLISH));
            }
            if ((httpRequest instanceof HttpEntityEnclosingRequest) && hashSet.contains("auth-int")) {
                i2 = 1;
            } else if (hashSet.contains(AuthMechanism.ELEMENT)) {
                i2 = 2;
            }
            i = i2;
        } else {
            i = 0;
        }
        if (i == -1) {
            throw new AuthenticationException("None of the qop methods is supported: " + a7);
        }
        String a8 = m11888a("charset");
        if (a8 == null) {
            a8 = "ISO-8859-1";
        }
        if (a6.equalsIgnoreCase("MD5-sess")) {
            a7 = StringUtils.MD5;
        } else {
            a7 = a6;
        }
        try {
            MessageDigest b = m11903b(a7);
            String name = credentials.m11441a().getName();
            a7 = credentials.m11442b();
            if (a3.equals(this.f7466c)) {
                this.f7467d++;
            } else {
                this.f7467d = 1;
                this.f7468e = null;
                this.f7466c = a3;
            }
            Appendable stringBuilder = new StringBuilder(256);
            Formatter formatter = new Formatter(stringBuilder, Locale.US);
            Object[] objArr = new Object[1];
            objArr[0] = Long.valueOf(this.f7467d);
            formatter.format("%08x", objArr);
            formatter.close();
            String stringBuilder2 = stringBuilder.toString();
            if (this.f7468e == null) {
                this.f7468e = m11904f();
            }
            this.f7469f = null;
            this.f7470g = null;
            if (a6.equalsIgnoreCase("MD5-sess")) {
                stringBuilder.setLength(0);
                stringBuilder.append(name).append(':').append(a2).append(':').append(a7);
                a7 = m11901a(b.digest(EncodingUtils.m12763a(stringBuilder.toString(), a8)));
                stringBuilder.setLength(0);
                stringBuilder.append(a7).append(':').append(a3).append(':').append(this.f7468e);
                this.f7469f = stringBuilder.toString();
            } else {
                stringBuilder.setLength(0);
                stringBuilder.append(name).append(':').append(a2).append(':').append(a7);
                this.f7469f = stringBuilder.toString();
            }
            String a9 = m11901a(b.digest(EncodingUtils.m12763a(this.f7469f, a8)));
            if (i == 2) {
                this.f7470g = a5 + ':' + a;
            } else if (i == 1) {
                HttpEntity httpEntity = null;
                if (httpRequest instanceof HttpEntityEnclosingRequest) {
                    httpEntity = ((HttpEntityEnclosingRequest) httpRequest).m10657c();
                }
                if (httpEntity == null || httpEntity.m10544d()) {
                    OutputStream httpEntityDigester = new HttpEntityDigester(b);
                    if (httpEntity != null) {
                        try {
                            httpEntity.m10541a(httpEntityDigester);
                        } catch (Throwable e) {
                            throw new AuthenticationException("I/O error reading entity content", e);
                        }
                    }
                    httpEntityDigester.close();
                    this.f7470g = a5 + ':' + a + ':' + m11901a(httpEntityDigester.m11915a());
                } else if (hashSet.contains(AuthMechanism.ELEMENT)) {
                    i = 2;
                    this.f7470g = a5 + ':' + a;
                } else {
                    throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
                }
            } else {
                this.f7470g = a5 + ':' + a;
            }
            a7 = m11901a(b.digest(EncodingUtils.m12763a(this.f7470g, a8)));
            if (i == 0) {
                stringBuilder.setLength(0);
                stringBuilder.append(a9).append(':').append(a3).append(':').append(a7);
                a8 = stringBuilder.toString();
            } else {
                stringBuilder.setLength(0);
                stringBuilder.append(a9).append(':').append(a3).append(':').append(stringBuilder2).append(':').append(this.f7468e).append(':').append(i == 1 ? "auth-int" : AuthMechanism.ELEMENT).append(':').append(a7);
                a8 = stringBuilder.toString();
            }
            a8 = m11901a(b.digest(EncodingUtils.m12762a(a8)));
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(128);
            if (m11886e()) {
                charArrayBuffer.m12751a("Proxy-Authorization");
            } else {
                charArrayBuffer.m12751a("Authorization");
            }
            charArrayBuffer.m12751a(": Digest ");
            List arrayList = new ArrayList(20);
            arrayList.add(new BasicNameValuePair("username", name));
            arrayList.add(new BasicNameValuePair("realm", a2));
            arrayList.add(new BasicNameValuePair("nonce", a3));
            arrayList.add(new BasicNameValuePair("uri", a));
            arrayList.add(new BasicNameValuePair(Response.ELEMENT, a8));
            if (i != 0) {
                arrayList.add(new BasicNameValuePair("qop", i == 1 ? "auth-int" : AuthMechanism.ELEMENT));
                arrayList.add(new BasicNameValuePair("nc", stringBuilder2));
                arrayList.add(new BasicNameValuePair("cnonce", this.f7468e));
            }
            arrayList.add(new BasicNameValuePair("algorithm", a6));
            if (a4 != null) {
                arrayList.add(new BasicNameValuePair("opaque", a4));
            }
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                boolean z;
                NameValuePair nameValuePair = (BasicNameValuePair) arrayList.get(i2);
                if (i2 > 0) {
                    charArrayBuffer.m12751a(", ");
                }
                String a10 = nameValuePair.m12627a();
                Object obj = ("nc".equals(a10) || "qop".equals(a10) || "algorithm".equals(a10)) ? 1 : null;
                BasicHeaderValueFormatter basicHeaderValueFormatter = BasicHeaderValueFormatter.f7863b;
                if (obj == null) {
                    z = true;
                } else {
                    z = false;
                }
                basicHeaderValueFormatter.m12580a(charArrayBuffer, nameValuePair, z);
            }
            return new BufferedHeader(charArrayBuffer);
        } catch (UnsupportedDigestAlgorithmException e2) {
            throw new AuthenticationException("Unsuppported digest algorithm: " + a7);
        }
    }

    static String m11901a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & 15;
            cArr[i * 2] = f7464a[(bArr[i] & 240) >> 4];
            cArr[(i * 2) + 1] = f7464a[i2];
        }
        return new String(cArr);
    }

    public static String m11904f() {
        byte[] bArr = new byte[8];
        new SecureRandom().nextBytes(bArr);
        return m11901a(bArr);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DIGEST [complete=").append(this.f7465b).append(", nonce=").append(this.f7466c).append(", nc=").append(this.f7467d).append("]");
        return stringBuilder.toString();
    }
}
