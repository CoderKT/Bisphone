package org.jivesoftware.smack.util.stringencoder.android;

import android.util.Base64;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.StringEncoder;

public class AndroidBase64UrlSafeEncoder implements StringEncoder {
    private static final int BASE64_ENCODER_FLAGS = 10;
    private static AndroidBase64UrlSafeEncoder instance;

    static {
        instance = new AndroidBase64UrlSafeEncoder();
    }

    private AndroidBase64UrlSafeEncoder() {
    }

    public static AndroidBase64UrlSafeEncoder getInstance() {
        return instance;
    }

    public String encode(String str) {
        try {
            return Base64.encodeToString(str.getBytes(StringUtils.UTF8), BASE64_ENCODER_FLAGS);
        } catch (Throwable e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }

    public String decode(String str) {
        try {
            return new String(Base64.decode(str, BASE64_ENCODER_FLAGS), StringUtils.UTF8);
        } catch (Throwable e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }
}
