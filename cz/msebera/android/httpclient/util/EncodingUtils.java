package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.Consts;
import java.io.UnsupportedEncodingException;

public final class EncodingUtils {
    public static byte[] m12763a(String str, String str2) {
        Args.m12722a((Object) str, "Input");
        Args.m12721a((CharSequence) str2, "Charset");
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static byte[] m12762a(String str) {
        Args.m12722a((Object) str, "Input");
        try {
            return str.getBytes(Consts.f7198b.name());
        } catch (UnsupportedEncodingException e) {
            throw new Error("ASCII not supported");
        }
    }

    public static String m12761a(byte[] bArr, int i, int i2) {
        Args.m12722a((Object) bArr, "Input");
        try {
            return new String(bArr, i, i2, Consts.f7198b.name());
        } catch (UnsupportedEncodingException e) {
            throw new Error("ASCII not supported");
        }
    }

    public static String m12760a(byte[] bArr) {
        Args.m12722a((Object) bArr, "Input");
        return m12761a(bArr, 0, bArr.length);
    }
}
