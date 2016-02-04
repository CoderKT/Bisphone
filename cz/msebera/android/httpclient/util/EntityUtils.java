package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.HttpEntity;
import java.io.InputStream;

public final class EntityUtils {
    public static void m12764a(HttpEntity httpEntity) {
        if (httpEntity != null && httpEntity.m10546f()) {
            InputStream a = httpEntity.m10540a();
            if (a != null) {
                a.close();
            }
        }
    }

    public static byte[] m12765b(HttpEntity httpEntity) {
        int i = 4096;
        boolean z = false;
        Args.m12722a((Object) httpEntity, "Entity");
        InputStream a = httpEntity.m10540a();
        if (a == null) {
            return null;
        }
        try {
            if (httpEntity.m10542b() <= 2147483647L) {
                z = true;
            }
            Args.m12724a(z, "HTTP entity too large to be buffered in memory");
            int b = (int) httpEntity.m10542b();
            if (b >= 0) {
                i = b;
            }
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = a.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.m12734a(bArr, 0, read);
            }
            bArr = byteArrayBuffer.m12737b();
            return bArr;
        } finally {
            a.close();
        }
    }
}
