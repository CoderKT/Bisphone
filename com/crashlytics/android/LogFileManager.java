package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.QueueFile;
import io.fabric.sdk.android.services.common.QueueFile.ElementReader;
import java.io.File;
import java.io.InputStream;

class LogFileManager {
    private final Context f5404a;
    private final File f5405b;
    private QueueFile f5406c;

    /* renamed from: com.crashlytics.android.LogFileManager.1 */
    class C06111 implements ElementReader {
        final /* synthetic */ byte[] f5401a;
        final /* synthetic */ int[] f5402b;
        final /* synthetic */ LogFileManager f5403c;

        C06111(LogFileManager logFileManager, byte[] bArr, int[] iArr) {
            this.f5403c = logFileManager;
            this.f5401a = bArr;
            this.f5402b = iArr;
        }

        public void m8009a(InputStream inputStream, int i) {
            try {
                inputStream.read(this.f5401a, this.f5402b[0], i);
                int[] iArr = this.f5402b;
                iArr[0] = iArr[0] + i;
            } finally {
                inputStream.close();
            }
        }
    }

    public LogFileManager(Context context, File file) {
        this(context, file, null);
    }

    LogFileManager(Context context, File file, QueueFile queueFile) {
        this.f5404a = context;
        this.f5405b = file;
        this.f5406c = queueFile;
    }

    ByteString m8010a() {
        if (this.f5406c == null) {
            return null;
        }
        int[] iArr = new int[]{0};
        byte[] bArr = new byte[this.f5406c.m13098a()];
        try {
            this.f5406c.m13099a(new C06111(this, bArr, iArr));
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "A problem occurred while reading the Crashlytics log file.", e);
        }
        return ByteString.m7768a(bArr, 0, iArr[0]);
    }

    void m8011b() {
        CommonUtils.m13016a(this.f5406c, "There was a problem closing the Crashlytics log file.");
        this.f5406c = null;
    }
}
