package com.loopj.android.http;

import android.text.TextUtils;
import app.C0110R;
import com.loopj.android.http.RequestParams.FileWrapper;
import com.loopj.android.http.RequestParams.StreamWrapper;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import se.emilsjolander.stickylistheaders.C1128R;

public class JsonStreamerEntity implements HttpEntity {
    private static final UnsupportedOperationException f6584a;
    private static final byte[] f6585b;
    private static final byte[] f6586c;
    private static final byte[] f6587d;
    private static final byte[] f6588e;
    private static final byte[] f6589f;
    private static final byte[] f6590g;
    private static final Header f6591h;
    private static final Header f6592i;
    private final byte[] f6593j;
    private final Map<String, Object> f6594k;
    private final Header f6595l;
    private final byte[] f6596m;
    private final ResponseHandlerInterface f6597n;

    static {
        f6584a = new UnsupportedOperationException("Unsupported operation in this implementation.");
        f6585b = "true".getBytes();
        f6586c = "false".getBytes();
        f6587d = "null".getBytes();
        f6588e = m10666a("name");
        f6589f = m10666a("type");
        f6590g = m10666a("contents");
        f6591h = new BasicHeader("Content-Type", "application/json");
        f6592i = new BasicHeader("Content-Encoding", "gzip");
    }

    public JsonStreamerEntity(ResponseHandlerInterface responseHandlerInterface, boolean z, String str) {
        Header header;
        byte[] bArr = null;
        this.f6593j = new byte[4096];
        this.f6594k = new HashMap();
        this.f6597n = responseHandlerInterface;
        if (z) {
            header = f6592i;
        } else {
            header = null;
        }
        this.f6595l = header;
        if (!TextUtils.isEmpty(str)) {
            bArr = m10666a(str);
        }
        this.f6596m = bArr;
    }

    static byte[] m10666a(String str) {
        if (str == null) {
            return f6587d;
        }
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append('\"');
        int length = str.length();
        int i = -1;
        while (true) {
            int i2 = i + 1;
            if (i2 < length) {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                        stringBuilder.append("\\b");
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                        stringBuilder.append("\\t");
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                        stringBuilder.append("\\n");
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                        stringBuilder.append("\\f");
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                        stringBuilder.append("\\r");
                        break;
                    case C0110R.styleable.Theme_actionModePasteDrawable /*34*/:
                        stringBuilder.append("\\\"");
                        break;
                    case C0110R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                        stringBuilder.append("\\\\");
                        break;
                    default:
                        if (charAt > '\u001f' && ((charAt < '\u007f' || charAt > '\u009f') && (charAt < '\u2000' || charAt > '\u20ff'))) {
                            stringBuilder.append(charAt);
                            break;
                        }
                        String toHexString = Integer.toHexString(charAt);
                        stringBuilder.append("\\u");
                        int length2 = 4 - toHexString.length();
                        for (i = 0; i < length2; i++) {
                            stringBuilder.append('0');
                        }
                        stringBuilder.append(toHexString.toUpperCase(Locale.US));
                        break;
                        break;
                }
                i = i2;
            } else {
                stringBuilder.append('\"');
                return stringBuilder.toString().getBytes();
            }
        }
    }

    public void m10670a(String str, Object obj) {
        this.f6594k.put(str, obj);
    }

    public boolean m10673d() {
        return false;
    }

    public boolean m10674e() {
        return false;
    }

    public boolean m10675f() {
        return false;
    }

    public long m10671b() {
        return -1;
    }

    public Header m10676g() {
        return this.f6595l;
    }

    public Header m10677h() {
        return f6591h;
    }

    public void m10672c() {
    }

    public InputStream m10668a() {
        throw f6584a;
    }

    public void m10669a(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalStateException("Output stream cannot be null.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f6595l != null) {
            outputStream = new GZIPOutputStream(outputStream, 4096);
        }
        outputStream.write(123);
        Set<String> keySet = this.f6594k.keySet();
        int size = keySet.size();
        if (size > 0) {
            int i = 0;
            for (String str : keySet) {
                int i2 = i + 1;
                Object obj = this.f6594k.get(str);
                outputStream.write(m10666a(str));
                outputStream.write(58);
                if (obj == null) {
                    outputStream.write(f6587d);
                } else {
                    boolean z = obj instanceof FileWrapper;
                    if (z || (obj instanceof StreamWrapper)) {
                        outputStream.write(123);
                        if (z) {
                            m10663a(outputStream, (FileWrapper) obj);
                        } else {
                            try {
                                m10664a(outputStream, (StreamWrapper) obj);
                            } catch (Throwable th) {
                                if (this.f6596m != null || i2 < size) {
                                    outputStream.write(44);
                                }
                            }
                        }
                        outputStream.write(125);
                    } else if (obj instanceof JsonValueInterface) {
                        outputStream.write(((JsonValueInterface) obj).m10678a());
                    } else if (obj instanceof JSONObject) {
                        outputStream.write(obj.toString().getBytes());
                    } else if (obj instanceof JSONArray) {
                        outputStream.write(obj.toString().getBytes());
                    } else if (obj instanceof Boolean) {
                        outputStream.write(((Boolean) obj).booleanValue() ? f6585b : f6586c);
                    } else if (obj instanceof Long) {
                        outputStream.write((((Number) obj).longValue() + "").getBytes());
                    } else if (obj instanceof Double) {
                        outputStream.write((((Number) obj).doubleValue() + "").getBytes());
                    } else if (obj instanceof Float) {
                        outputStream.write((((Number) obj).floatValue() + "").getBytes());
                    } else if (obj instanceof Integer) {
                        outputStream.write((((Number) obj).intValue() + "").getBytes());
                    } else {
                        outputStream.write(m10666a(obj.toString()));
                    }
                }
                if (this.f6596m != null || i2 < size) {
                    outputStream.write(44);
                }
                i = i2;
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.f6596m != null) {
                outputStream.write(this.f6596m);
                outputStream.write(58);
                outputStream.write((currentTimeMillis2 + "").getBytes());
            }
            AsyncHttpClient.f6518a.m10683c("JsonStreamerEntity", "Uploaded JSON in " + Math.floor((double) (currentTimeMillis2 / 1000)) + " seconds");
        }
        outputStream.write(125);
        outputStream.flush();
        AsyncHttpClient.m10568a(outputStream);
    }

    private void m10664a(OutputStream outputStream, StreamWrapper streamWrapper) {
        m10665a(outputStream, streamWrapper.f6622b, streamWrapper.f6623c);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        while (true) {
            int read = streamWrapper.f6621a.read(this.f6593j);
            if (read == -1) {
                break;
            }
            base64OutputStream.write(this.f6593j, 0, read);
        }
        AsyncHttpClient.m10568a(base64OutputStream);
        m10667b(outputStream);
        if (streamWrapper.f6624d) {
            AsyncHttpClient.m10567a(streamWrapper.f6621a);
        }
    }

    private void m10663a(OutputStream outputStream, FileWrapper fileWrapper) {
        m10665a(outputStream, fileWrapper.f6618a.getName(), fileWrapper.f6619b);
        long j = 0;
        long length = fileWrapper.f6618a.length();
        InputStream fileInputStream = new FileInputStream(fileWrapper.f6618a);
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, 18);
        while (true) {
            int read = fileInputStream.read(this.f6593j);
            if (read != -1) {
                base64OutputStream.write(this.f6593j, 0, read);
                j += (long) read;
                this.f6597n.m5463b(j, length);
            } else {
                AsyncHttpClient.m10568a(base64OutputStream);
                m10667b(outputStream);
                AsyncHttpClient.m10567a(fileInputStream);
                return;
            }
        }
    }

    private void m10665a(OutputStream outputStream, String str, String str2) {
        outputStream.write(f6588e);
        outputStream.write(58);
        outputStream.write(m10666a(str));
        outputStream.write(44);
        outputStream.write(f6589f);
        outputStream.write(58);
        outputStream.write(m10666a(str2));
        outputStream.write(44);
        outputStream.write(f6590g);
        outputStream.write(58);
        outputStream.write(34);
    }

    private void m10667b(OutputStream outputStream) {
        outputStream.write(34);
    }
}
