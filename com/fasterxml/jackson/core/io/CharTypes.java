package com.fasterxml.jackson.core.io;

import app.C0110R;
import java.util.Arrays;

public final class CharTypes {
    private static final byte[] HB;
    private static final char[] HC;
    static final int[] sHexValues;
    static final int[] sInputCodes;
    static final int[] sInputCodesComment;
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUTF8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sInputCodesWS;
    static final int[] sOutputEscapes128;

    static {
        int i;
        HC = "0123456789ABCDEF".toCharArray();
        int length = HC.length;
        HB = new byte[length];
        for (i = 0; i < length; i++) {
            HB[i] = (byte) HC[i];
        }
        int[] iArr = new int[256];
        for (i = 0; i < 32; i++) {
            iArr[i] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        Object obj = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, obj, 0, obj.length);
        for (length = 128; length < 256; length++) {
            i = (length & 224) == 192 ? 2 : (length & 240) == 224 ? 3 : (length & 248) == 240 ? 4 : -1;
            obj[length] = i;
        }
        sInputCodesUTF8 = obj;
        iArr = new int[256];
        Arrays.fill(iArr, -1);
        for (i = 33; i < 256; i++) {
            if (Character.isJavaIdentifierPart((char) i)) {
                iArr[i] = 0;
            }
        }
        iArr[64] = 0;
        iArr[35] = 0;
        iArr[42] = 0;
        iArr[45] = 0;
        iArr[43] = 0;
        sInputCodesJsNames = iArr;
        Object obj2 = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, obj2, 0, obj2.length);
        Arrays.fill(obj2, 128, 128, 0);
        sInputCodesUtf8JsNames = obj2;
        obj2 = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, obj2, 128, 128);
        Arrays.fill(obj2, 0, 32, -1);
        obj2[9] = null;
        obj2[10] = 10;
        obj2[13] = 13;
        obj2[42] = 42;
        sInputCodesComment = obj2;
        obj2 = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, obj2, 128, 128);
        Arrays.fill(obj2, 0, 32, -1);
        obj2[32] = 1;
        obj2[9] = 1;
        obj2[10] = 10;
        obj2[13] = 13;
        obj2[47] = 47;
        obj2[35] = 35;
        sInputCodesWS = obj2;
        iArr = new int[128];
        for (i = 0; i < 32; i++) {
            iArr[i] = -1;
        }
        iArr[34] = 34;
        iArr[92] = 92;
        iArr[8] = 98;
        iArr[9] = 116;
        iArr[12] = C0110R.styleable.Theme_checkboxStyle;
        iArr[10] = 110;
        iArr[13] = 114;
        sOutputEscapes128 = iArr;
        sHexValues = new int[128];
        Arrays.fill(sHexValues, -1);
        for (i = 0; i < 10; i++) {
            sHexValues[i + 48] = i;
        }
        for (i = 0; i < 6; i++) {
            sHexValues[i + 97] = i + 10;
            sHexValues[i + 65] = i + 10;
        }
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int charToHex(int i) {
        return i > 127 ? -1 : sHexValues[i];
    }

    public static void appendQuoted(StringBuilder stringBuilder, String str) {
        int[] iArr = sOutputEscapes128;
        char length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            if (charAt >= length || iArr[charAt] == 0) {
                stringBuilder.append(charAt);
            } else {
                stringBuilder.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    stringBuilder.append('u');
                    stringBuilder.append('0');
                    stringBuilder.append('0');
                    stringBuilder.append(HC[charAt >> 4]);
                    stringBuilder.append(HC[charAt & 15]);
                } else {
                    stringBuilder.append((char) i2);
                }
            }
        }
    }
}
