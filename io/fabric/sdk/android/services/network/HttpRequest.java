package io.fabric.sdk.android.services.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.jivesoftware.smack.util.StringUtils;

public class HttpRequest {
    private static final String[] f8349b;
    private static ConnectionFactory f8350c;
    public final URL f8351a;
    private HttpURLConnection f8352d;
    private final String f8353e;
    private RequestOutputStream f8354f;
    private boolean f8355g;
    private boolean f8356h;
    private boolean f8357i;
    private int f8358j;
    private String f8359k;
    private int f8360l;

    public abstract class Operation<V> implements Callable<V> {
        protected abstract V m13170b();

        protected abstract void m13171c();

        protected Operation() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V call() {
            /*
            r3 = this;
            r1 = 1;
            r2 = 0;
            r0 = r3.m13170b();	 Catch:{ HttpRequestException -> 0x0011, IOException -> 0x0018, all -> 0x0028 }
            r3.m13171c();	 Catch:{ IOException -> 0x000a }
            return r0;
        L_0x000a:
            r0 = move-exception;
            r1 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException;
            r1.<init>(r0);
            throw r1;
        L_0x0011:
            r0 = move-exception;
            throw r0;	 Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception;
        L_0x0014:
            r3.m13171c();	 Catch:{ IOException -> 0x001f }
        L_0x0017:
            throw r0;
        L_0x0018:
            r0 = move-exception;
            r2 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException;	 Catch:{ all -> 0x0013 }
            r2.<init>(r0);	 Catch:{ all -> 0x0013 }
            throw r2;	 Catch:{ all -> 0x0013 }
        L_0x001f:
            r2 = move-exception;
            if (r1 != 0) goto L_0x0017;
        L_0x0022:
            r0 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException;
            r0.<init>(r2);
            throw r0;
        L_0x0028:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0014;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.HttpRequest.Operation.call():V");
        }
    }

    public abstract class CloseOperation<V> extends Operation<V> {
        private final Closeable f8342a;
        private final boolean f8343b;

        protected CloseOperation(Closeable closeable, boolean z) {
            this.f8342a = closeable;
            this.f8343b = z;
        }

        protected void m13172c() {
            if (this.f8342a instanceof Flushable) {
                ((Flushable) this.f8342a).flush();
            }
            if (this.f8343b) {
                try {
                    this.f8342a.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            this.f8342a.close();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.network.HttpRequest.8 */
    class C09738 extends CloseOperation<HttpRequest> {
        final /* synthetic */ InputStream f8344a;
        final /* synthetic */ OutputStream f8345b;
        final /* synthetic */ HttpRequest f8346c;

        C09738(HttpRequest httpRequest, Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
            this.f8346c = httpRequest;
            this.f8344a = inputStream;
            this.f8345b = outputStream;
            super(closeable, z);
        }

        public /* synthetic */ Object m13174b() {
            return m13173a();
        }

        public HttpRequest m13173a() {
            byte[] bArr = new byte[this.f8346c.f8358j];
            while (true) {
                int read = this.f8344a.read(bArr);
                if (read == -1) {
                    return this.f8346c;
                }
                this.f8345b.write(bArr, 0, read);
            }
        }
    }

    public interface ConnectionFactory {
        public static final ConnectionFactory f8347a;

        /* renamed from: io.fabric.sdk.android.services.network.HttpRequest.ConnectionFactory.1 */
        final class C09741 implements ConnectionFactory {
            C09741() {
            }

            public HttpURLConnection m13177a(URL url) {
                return (HttpURLConnection) url.openConnection();
            }

            public HttpURLConnection m13178a(URL url, Proxy proxy) {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        }

        HttpURLConnection m13175a(URL url);

        HttpURLConnection m13176a(URL url, Proxy proxy);

        static {
            f8347a = new C09741();
        }
    }

    public class HttpRequestException extends RuntimeException {
        public /* synthetic */ Throwable getCause() {
            return m13179a();
        }

        protected HttpRequestException(IOException iOException) {
            super(iOException);
        }

        public IOException m13179a() {
            return (IOException) super.getCause();
        }
    }

    public class RequestOutputStream extends BufferedOutputStream {
        private final CharsetEncoder f8348a;

        public RequestOutputStream(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.f8348a = Charset.forName(HttpRequest.m13193f(str)).newEncoder();
        }

        public RequestOutputStream m13180a(String str) {
            ByteBuffer encode = this.f8348a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    static {
        f8349b = new String[0];
        f8350c = ConnectionFactory.f8347a;
    }

    private static String m13193f(String str) {
        return (str == null || str.length() <= 0) ? StringUtils.UTF8 : str;
    }

    private static StringBuilder m13185a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    private static StringBuilder m13188b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    public static String m13183a(CharSequence charSequence) {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(63);
                if (indexOf > 0 && indexOf + 1 < toASCIIString.length()) {
                    toASCIIString = toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace("+", "%2B");
                }
                return toASCIIString;
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String m13184a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        m13185a(charSequence2, stringBuilder);
        m13188b(charSequence2, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static HttpRequest m13186b(CharSequence charSequence) {
        return new HttpRequest(charSequence, "GET");
    }

    public static HttpRequest m13182a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = m13184a(charSequence, (Map) map);
        if (z) {
            a = m13183a(a);
        }
        return m13186b(a);
    }

    public static HttpRequest m13189c(CharSequence charSequence) {
        return new HttpRequest(charSequence, "POST");
    }

    public static HttpRequest m13187b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = m13184a(charSequence, (Map) map);
        if (z) {
            a = m13183a(a);
        }
        return m13189c(a);
    }

    public static HttpRequest m13190d(CharSequence charSequence) {
        return new HttpRequest(charSequence, "PUT");
    }

    public static HttpRequest m13191e(CharSequence charSequence) {
        return new HttpRequest(charSequence, "DELETE");
    }

    public HttpRequest(CharSequence charSequence, String str) {
        this.f8352d = null;
        this.f8356h = true;
        this.f8357i = false;
        this.f8358j = 8192;
        try {
            this.f8351a = new URL(charSequence.toString());
            this.f8353e = str;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    private Proxy m13194q() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.f8359k, this.f8360l));
    }

    private HttpURLConnection m13195r() {
        try {
            HttpURLConnection a;
            if (this.f8359k != null) {
                a = f8350c.m13176a(this.f8351a, m13194q());
            } else {
                a = f8350c.m13175a(this.f8351a);
            }
            a.setRequestMethod(this.f8353e);
            return a;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        return m13234p() + ' ' + m13233o();
    }

    public HttpURLConnection m13209a() {
        if (this.f8352d == null) {
            this.f8352d = m13195r();
        }
        return this.f8352d;
    }

    public int m13210b() {
        try {
            m13229k();
            return m13209a().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public boolean m13216c() {
        return 200 == m13210b();
    }

    protected ByteArrayOutputStream m13219d() {
        int j = m13228j();
        if (j > 0) {
            return new ByteArrayOutputStream(j);
        }
        return new ByteArrayOutputStream();
    }

    public String m13208a(String str) {
        OutputStream d = m13219d();
        try {
            m13198a(m13224f(), d);
            return d.toString(m13193f(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String m13221e() {
        return m13208a(m13226h());
    }

    public BufferedInputStream m13224f() {
        return new BufferedInputStream(m13225g(), this.f8358j);
    }

    public InputStream m13225g() {
        if (m13210b() < 400) {
            try {
                InputStream inputStream = m13209a().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        }
        inputStream = m13209a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = m13209a().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        }
        if (!this.f8357i || !"gzip".equals(m13227i())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new HttpRequestException(e22);
        }
    }

    public HttpRequest m13197a(int i) {
        m13209a().setConnectTimeout(i);
        return this;
    }

    public HttpRequest m13200a(String str, String str2) {
        m13209a().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest m13206a(Entry<String, String> entry) {
        return m13200a((String) entry.getKey(), (String) entry.getValue());
    }

    public String m13212b(String str) {
        m13230l();
        return m13209a().getHeaderField(str);
    }

    public int m13214c(String str) {
        return m13196a(str, -1);
    }

    public int m13196a(String str, int i) {
        m13230l();
        return m13209a().getHeaderFieldInt(str, i);
    }

    public String m13213b(String str, String str2) {
        return m13215c(m13212b(str), str2);
    }

    protected String m13215c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(61, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    if (indexOf3 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(indexOf3 - 1)) {
                        return trim.substring(1, indexOf3 - 1);
                    }
                    return trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    public String m13226h() {
        return m13213b("Content-Type", "charset");
    }

    public HttpRequest m13207a(boolean z) {
        m13209a().setUseCaches(z);
        return this;
    }

    public String m13227i() {
        return m13212b("Content-Encoding");
    }

    public HttpRequest m13217d(String str) {
        return m13218d(str, null);
    }

    public HttpRequest m13218d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return m13200a("Content-Type", str);
        }
        String str3 = "; charset=";
        return m13200a("Content-Type", str + "; charset=" + str2);
    }

    public int m13228j() {
        return m13214c("Content-Length");
    }

    protected HttpRequest m13198a(InputStream inputStream, OutputStream outputStream) {
        return (HttpRequest) new C09738(this, inputStream, this.f8356h, inputStream, outputStream).call();
    }

    protected HttpRequest m13229k() {
        if (this.f8354f != null) {
            if (this.f8355g) {
                this.f8354f.m13180a("\r\n--00content0boundary00--\r\n");
            }
            if (this.f8356h) {
                try {
                    this.f8354f.close();
                } catch (IOException e) {
                }
            } else {
                this.f8354f.close();
            }
            this.f8354f = null;
        }
        return this;
    }

    protected HttpRequest m13230l() {
        try {
            return m13229k();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    protected HttpRequest m13231m() {
        if (this.f8354f == null) {
            m13209a().setDoOutput(true);
            this.f8354f = new RequestOutputStream(m13209a().getOutputStream(), m13215c(m13209a().getRequestProperty("Content-Type"), "charset"), this.f8358j);
        }
        return this;
    }

    protected HttpRequest m13232n() {
        if (this.f8355g) {
            this.f8354f.m13180a("\r\n--00content0boundary00\r\n");
        } else {
            this.f8355g = true;
            m13217d("multipart/form-data; boundary=00content0boundary00").m13231m();
            this.f8354f.m13180a("--00content0boundary00\r\n");
        }
        return this;
    }

    protected HttpRequest m13202a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append('\"');
        m13223f("Content-Disposition", stringBuilder.toString());
        if (str3 != null) {
            m13223f("Content-Type", str3);
        }
        return m13222f((CharSequence) "\r\n");
    }

    public HttpRequest m13220e(String str, String str2) {
        return m13211b(str, null, str2);
    }

    public HttpRequest m13211b(String str, String str2, String str3) {
        return m13205a(str, str2, null, str3);
    }

    public HttpRequest m13205a(String str, String str2, String str3, String str4) {
        try {
            m13232n();
            m13202a(str, str2, str3);
            this.f8354f.m13180a(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest m13199a(String str, Number number) {
        return m13201a(str, null, number);
    }

    public HttpRequest m13201a(String str, String str2, Number number) {
        return m13211b(str, str2, number != null ? number.toString() : null);
    }

    public HttpRequest m13203a(String str, String str2, String str3, File file) {
        IOException e;
        Throwable th;
        InputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                HttpRequest a = m13204a(str, str2, str3, bufferedInputStream);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return a;
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new HttpRequestException(e);
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
            throw new HttpRequestException(e);
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public HttpRequest m13204a(String str, String str2, String str3, InputStream inputStream) {
        try {
            m13232n();
            m13202a(str, str2, str3);
            m13198a(inputStream, this.f8354f);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest m13223f(String str, String str2) {
        return m13222f((CharSequence) str).m13222f((CharSequence) ": ").m13222f((CharSequence) str2).m13222f((CharSequence) "\r\n");
    }

    public HttpRequest m13222f(CharSequence charSequence) {
        try {
            m13231m();
            this.f8354f.m13180a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public URL m13233o() {
        return m13209a().getURL();
    }

    public String m13234p() {
        return m13209a().getRequestMethod();
    }
}
