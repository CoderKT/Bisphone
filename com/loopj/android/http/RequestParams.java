package com.loopj.android.http;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.util.StringUtils;

public class RequestParams implements Serializable {
    protected final ConcurrentHashMap<String, String> f6625a;
    protected final ConcurrentHashMap<String, StreamWrapper> f6626b;
    protected final ConcurrentHashMap<String, FileWrapper> f6627c;
    protected final ConcurrentHashMap<String, List<FileWrapper>> f6628d;
    protected final ConcurrentHashMap<String, Object> f6629e;
    protected boolean f6630f;
    protected boolean f6631g;
    protected boolean f6632h;
    protected String f6633i;
    protected String f6634j;

    /* renamed from: com.loopj.android.http.RequestParams.1 */
    class C08961 extends HashMap<String, String> {
        final /* synthetic */ String f6616a;
        final /* synthetic */ String f6617b;

        C08961(String str, String str2) {
            this.f6616a = str;
            this.f6617b = str2;
            put(this.f6616a, this.f6617b);
        }
    }

    public class FileWrapper implements Serializable {
        public final File f6618a;
        public final String f6619b;
        public final String f6620c;

        public FileWrapper(File file, String str, String str2) {
            this.f6618a = file;
            this.f6619b = str;
            this.f6620c = str2;
        }
    }

    public class StreamWrapper {
        public final InputStream f6621a;
        public final String f6622b;
        public final String f6623c;
        public final boolean f6624d;

        public StreamWrapper(InputStream inputStream, String str, String str2, boolean z) {
            this.f6621a = inputStream;
            this.f6622b = str;
            this.f6623c = str2;
            this.f6624d = z;
        }

        static StreamWrapper m10734a(InputStream inputStream, String str, String str2, boolean z) {
            if (str2 == null) {
                str2 = "application/octet-stream";
            }
            return new StreamWrapper(inputStream, str, str2, z);
        }
    }

    public RequestParams() {
        this((Map) null);
    }

    public RequestParams(Map<String, String> map) {
        this.f6625a = new ConcurrentHashMap();
        this.f6626b = new ConcurrentHashMap();
        this.f6627c = new ConcurrentHashMap();
        this.f6628d = new ConcurrentHashMap();
        this.f6629e = new ConcurrentHashMap();
        this.f6631g = false;
        this.f6633i = "_elapsed";
        this.f6634j = StringUtils.UTF8;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                m10743a((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public RequestParams(String str, String str2) {
        this(new C08961(str, str2));
    }

    public void m10743a(String str, String str2) {
        if (str != null && str2 != null) {
            this.f6625a.put(str, str2);
        }
    }

    public void m10741a(String str, File file) {
        m10742a(str, file, null, null);
    }

    public void m10742a(String str, File file, String str2, String str3) {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        } else if (str != null) {
            this.f6627c.put(str, new FileWrapper(file, str2, str3));
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.f6625a.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.f6626b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("STREAM");
        }
        for (Entry entry22 : this.f6627c.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry22.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry222 : this.f6628d.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry222.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILES(SIZE=").append(((List) entry222.getValue()).size()).append(")");
        }
        for (BasicNameValuePair basicNameValuePair : m10735a(null, this.f6629e)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(basicNameValuePair.m12627a());
            stringBuilder.append("=");
            stringBuilder.append(basicNameValuePair.m12628b());
        }
        return stringBuilder.toString();
    }

    public HttpEntity m10739a(ResponseHandlerInterface responseHandlerInterface) {
        if (this.f6632h) {
            return m10736b(responseHandlerInterface);
        }
        if (!this.f6631g && this.f6626b.isEmpty() && this.f6627c.isEmpty() && this.f6628d.isEmpty()) {
            return m10737c();
        }
        return m10738c(responseHandlerInterface);
    }

    private HttpEntity m10736b(ResponseHandlerInterface responseHandlerInterface) {
        boolean z = (this.f6627c.isEmpty() && this.f6626b.isEmpty()) ? false : true;
        HttpEntity jsonStreamerEntity = new JsonStreamerEntity(responseHandlerInterface, z, this.f6633i);
        for (Entry entry : this.f6625a.entrySet()) {
            jsonStreamerEntity.m10670a((String) entry.getKey(), entry.getValue());
        }
        for (Entry entry2 : this.f6629e.entrySet()) {
            jsonStreamerEntity.m10670a((String) entry2.getKey(), entry2.getValue());
        }
        for (Entry entry22 : this.f6627c.entrySet()) {
            jsonStreamerEntity.m10670a((String) entry22.getKey(), entry22.getValue());
        }
        for (Entry entry222 : this.f6626b.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry222.getValue();
            if (streamWrapper.f6621a != null) {
                jsonStreamerEntity.m10670a((String) entry222.getKey(), StreamWrapper.m10734a(streamWrapper.f6621a, streamWrapper.f6622b, streamWrapper.f6623c, streamWrapper.f6624d));
            }
        }
        return jsonStreamerEntity;
    }

    private HttpEntity m10737c() {
        try {
            return new UrlEncodedFormEntity(m10740a(), this.f6634j);
        } catch (Throwable e) {
            AsyncHttpClient.f6518a.m10682b("RequestParams", "createFormEntity failed", e);
            return null;
        }
    }

    private HttpEntity m10738c(ResponseHandlerInterface responseHandlerInterface) {
        HttpEntity simpleMultipartEntity = new SimpleMultipartEntity(responseHandlerInterface);
        simpleMultipartEntity.m10767a(this.f6630f);
        for (Entry entry : this.f6625a.entrySet()) {
            simpleMultipartEntity.m10769b((String) entry.getKey(), (String) entry.getValue(), this.f6634j);
        }
        for (BasicNameValuePair basicNameValuePair : m10735a(null, this.f6629e)) {
            simpleMultipartEntity.m10769b(basicNameValuePair.m12627a(), basicNameValuePair.m12628b(), this.f6634j);
        }
        for (Entry entry2 : this.f6626b.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry2.getValue();
            if (streamWrapper.f6621a != null) {
                simpleMultipartEntity.m10765a((String) entry2.getKey(), streamWrapper.f6622b, streamWrapper.f6621a, streamWrapper.f6623c);
            }
        }
        for (Entry entry22 : this.f6627c.entrySet()) {
            FileWrapper fileWrapper = (FileWrapper) entry22.getValue();
            simpleMultipartEntity.m10764a((String) entry22.getKey(), fileWrapper.f6618a, fileWrapper.f6619b, fileWrapper.f6620c);
        }
        for (Entry entry222 : this.f6628d.entrySet()) {
            for (FileWrapper fileWrapper2 : (List) entry222.getValue()) {
                simpleMultipartEntity.m10764a((String) entry222.getKey(), fileWrapper2.f6618a, fileWrapper2.f6619b, fileWrapper2.f6620c);
            }
        }
        return simpleMultipartEntity;
    }

    protected List<BasicNameValuePair> m10740a() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.f6625a.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        linkedList.addAll(m10735a(null, this.f6629e));
        return linkedList;
    }

    private List<BasicNameValuePair> m10735a(String str, Object obj) {
        List<BasicNameValuePair> linkedList = new LinkedList();
        if (obj instanceof Map) {
            Map map = (Map) obj;
            List arrayList = new ArrayList(map.keySet());
            if (arrayList.size() > 0 && (arrayList.get(0) instanceof Comparable)) {
                Collections.sort(arrayList);
            }
            for (Object next : arrayList) {
                if (next instanceof String) {
                    Object obj2 = map.get(next);
                    if (obj2 != null) {
                        String str2;
                        if (str == null) {
                            str2 = (String) next;
                        } else {
                            str2 = String.format(Locale.US, "%s[%s]", new Object[]{str, next});
                        }
                        linkedList.addAll(m10735a(str2, obj2));
                    }
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            r3 = list.size();
            for (r0 = 0; r0 < r3; r0++) {
                linkedList.addAll(m10735a(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), list.get(r0)));
            }
        } else if (obj instanceof Object[]) {
            for (Object a : (Object[]) obj) {
                linkedList.addAll(m10735a(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), a));
            }
        } else if (obj instanceof Set) {
            for (Object a2 : (Set) obj) {
                linkedList.addAll(m10735a(str, a2));
            }
        } else {
            linkedList.add(new BasicNameValuePair(str, obj.toString()));
        }
        return linkedList;
    }

    protected String m10744b() {
        return URLEncodedUtils.m11601a(m10740a(), this.f6634j);
    }
}
