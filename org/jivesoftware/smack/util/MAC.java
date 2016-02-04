package org.jivesoftware.smack.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MAC {
    public static final String HMACSHA1 = "HmacSHA1";
    private static Mac HMAC_SHA1;

    static {
        try {
            HMAC_SHA1 = Mac.getInstance(HMACSHA1);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static synchronized byte[] hmacsha1(SecretKeySpec secretKeySpec, byte[] bArr) {
        byte[] doFinal;
        synchronized (MAC.class) {
            HMAC_SHA1.init(secretKeySpec);
            doFinal = HMAC_SHA1.doFinal(bArr);
        }
        return doFinal;
    }

    public static byte[] hmacsha1(byte[] bArr, byte[] bArr2) {
        return hmacsha1(new SecretKeySpec(bArr, HMACSHA1), bArr2);
    }
}
