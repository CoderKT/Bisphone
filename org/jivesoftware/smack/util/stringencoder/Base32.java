package org.jivesoftware.smack.util.stringencoder;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import se.emilsjolander.stickylistheaders.C1128R;

public class Base32 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ2345678";
    private static final StringEncoder base32Stringencoder;

    /* renamed from: org.jivesoftware.smack.util.stringencoder.Base32.1 */
    final class C10321 implements StringEncoder {
        C10321() {
        }

        public String encode(String str) {
            return Base32.encode(str);
        }

        public String decode(String str) {
            return Base32.decode(str);
        }
    }

    static {
        base32Stringencoder = new C10321();
    }

    public static StringEncoder getStringEncoder() {
        return base32Stringencoder;
    }

    public static String decode(String str) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            char c = (char) b;
            if (!Character.isWhitespace(c)) {
                byteArrayOutputStream.write((byte) Character.toUpperCase(c));
            }
        }
        while (byteArrayOutputStream.size() % 8 != 0) {
            byteArrayOutputStream.write(56);
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        int i = 0;
        while (i < toByteArray.length / 8) {
            short[] sArr = new short[8];
            int[] iArr = new int[5];
            int i2 = 0;
            int i3 = 8;
            while (i2 < 8 && ((char) toByteArray[(i * 8) + i2]) != '8') {
                sArr[i2] = (short) ALPHABET.indexOf(toByteArray[(i * 8) + i2]);
                if (sArr[i2] < (short) 0) {
                    return null;
                }
                i3--;
                i2++;
            }
            i3 = paddingToLen(i3);
            if (i3 < 0) {
                return null;
            }
            iArr[0] = (sArr[0] << 3) | (sArr[1] >> 2);
            iArr[1] = (((sArr[1] & 3) << 6) | (sArr[2] << 1)) | (sArr[3] >> 4);
            iArr[2] = ((sArr[3] & 15) << 4) | ((sArr[4] >> 1) & 15);
            iArr[3] = ((sArr[4] << 7) | (sArr[5] << 2)) | (sArr[6] >> 3);
            iArr[4] = ((sArr[6] & 7) << 5) | sArr[7];
            i2 = 0;
            while (i2 < i3) {
                try {
                    dataOutputStream.writeByte((byte) (iArr[i2] & 255));
                    i2++;
                } catch (IOException e) {
                }
            }
            i++;
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    public static String encode(String str) {
        byte[] bytes = str.getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < (bytes.length + 4) / 5; i++) {
            int i2;
            short[] sArr = new short[5];
            int[] iArr = new int[8];
            int i3 = 5;
            for (i2 = 0; i2 < 5; i2++) {
                if ((i * 5) + i2 < bytes.length) {
                    sArr[i2] = (short) (bytes[(i * 5) + i2] & 255);
                } else {
                    sArr[i2] = (short) 0;
                    i3--;
                }
            }
            i2 = lenToPadding(i3);
            iArr[0] = (byte) ((sArr[0] >> 3) & 31);
            iArr[1] = (byte) (((sArr[0] & 7) << 2) | ((sArr[1] >> 6) & 3));
            iArr[2] = (byte) ((sArr[1] >> 1) & 31);
            iArr[3] = (byte) (((sArr[1] & 1) << 4) | ((sArr[2] >> 4) & 15));
            iArr[4] = (byte) (((sArr[2] & 15) << 1) | ((sArr[3] >> 7) & 1));
            iArr[5] = (byte) ((sArr[3] >> 2) & 31);
            iArr[6] = (byte) (((sArr[3] & 3) << 3) | ((sArr[4] >> 5) & 7));
            iArr[7] = (byte) (sArr[4] & 31);
            for (i3 = 0; i3 < iArr.length - i2; i3++) {
                byteArrayOutputStream.write(ALPHABET.charAt(iArr[i3]));
            }
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    private static int lenToPadding(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return 6;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return 4;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return 3;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return 1;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return 0;
            default:
                return -1;
        }
    }

    private static int paddingToLen(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return 5;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return 4;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return 3;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return 2;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return 1;
            default:
                return -1;
        }
    }
}
