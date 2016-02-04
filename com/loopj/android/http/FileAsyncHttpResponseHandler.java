package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import java.io.File;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    protected final File f3067d;
    protected final boolean f3068e;
    protected final boolean f3069f;
    protected File f3070g;

    public abstract void m5543a(int i, Header[] headerArr, File file);

    public abstract void m5544a(int i, Header[] headerArr, Throwable th, File file);

    public FileAsyncHttpResponseHandler(File file, boolean z, boolean z2, boolean z3) {
        super(z3);
        Utils.m10778a(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!(file.isDirectory() || file.getParentFile().isDirectory())) {
            Utils.m10778a(file.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file.isDirectory() && !file.mkdirs()) {
            AsyncHttpClient.f6518a.m10681b("FileAsyncHttpRH", "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.f3067d = file;
        this.f3068e = z;
        this.f3069f = z2;
    }

    protected File m5548l() {
        Utils.m10778a(this.f3067d != null, "Target file is null, fatal!");
        return this.f3067d;
    }

    public File m5549m() {
        if (this.f3070g == null) {
            this.f3070g = m5548l().isDirectory() ? m5550n() : m5548l();
        }
        return this.f3070g;
    }

    protected File m5550n() {
        Utils.m10778a(m5548l().isDirectory(), "Target file is not a directory, cannot proceed");
        Utils.m10778a(m5498e() != null, "RequestURI is null, cannot proceed");
        String uri = m5498e().toString();
        String substring = uri.substring(uri.lastIndexOf(47) + 1, uri.length());
        File file = new File(m5548l(), substring);
        if (!file.exists() || !this.f3069f) {
            return file;
        }
        if (substring.contains(".")) {
            uri = substring.substring(0, substring.lastIndexOf(46)) + " (%d)" + substring.substring(substring.lastIndexOf(46), substring.length());
        } else {
            uri = substring + " (%d)";
        }
        int i = 0;
        while (true) {
            File file2 = new File(m5548l(), String.format(uri, new Object[]{Integer.valueOf(i)}));
            if (!file2.exists()) {
                return file2;
            }
            i++;
        }
    }

    public final void m5546a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        m5544a(i, headerArr, th, m5549m());
    }

    public final void m5545a(int i, Header[] headerArr, byte[] bArr) {
        m5543a(i, headerArr, m5549m());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected byte[] m5547a(cz.msebera.android.httpclient.HttpEntity r9) {
        /*
        r8 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x004b;
    L_0x0003:
        r1 = r9.m10540a();
        r2 = r9.m10542b();
        r4 = new java.io.FileOutputStream;
        r5 = r8.m5549m();
        r6 = r8.f3068e;
        r4.<init>(r5, r6);
        if (r1 == 0) goto L_0x004b;
    L_0x0018:
        r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r5 = new byte[r5];	 Catch:{ all -> 0x0037 }
    L_0x001c:
        r6 = r1.read(r5);	 Catch:{ all -> 0x0037 }
        r7 = -1;
        if (r6 == r7) goto L_0x0042;
    L_0x0023:
        r7 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0037 }
        r7 = r7.isInterrupted();	 Catch:{ all -> 0x0037 }
        if (r7 != 0) goto L_0x0042;
    L_0x002d:
        r0 = r0 + r6;
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ all -> 0x0037 }
        r6 = (long) r0;	 Catch:{ all -> 0x0037 }
        r8.m5492b(r6, r2);	 Catch:{ all -> 0x0037 }
        goto L_0x001c;
    L_0x0037:
        r0 = move-exception;
        com.loopj.android.http.AsyncHttpClient.m10567a(r1);
        r4.flush();
        com.loopj.android.http.AsyncHttpClient.m10568a(r4);
        throw r0;
    L_0x0042:
        com.loopj.android.http.AsyncHttpClient.m10567a(r1);
        r4.flush();
        com.loopj.android.http.AsyncHttpClient.m10568a(r4);
    L_0x004b:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.FileAsyncHttpResponseHandler.a(cz.msebera.android.httpclient.HttpEntity):byte[]");
    }
}
