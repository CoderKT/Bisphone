package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;

public final class Base64Variant implements Serializable {
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    protected final transient int _maxLineLength;
    protected final String _name;
    protected final transient char _paddingChar;
    protected final transient boolean _usesPadding;

    public Base64Variant(String str, String str2, boolean z, char c, int i) {
        int i2 = 0;
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = i;
        int length = str2.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, this._base64ToAsciiC, 0);
        Arrays.fill(this._asciiToBase64, -1);
        while (i2 < length) {
            char c2 = this._base64ToAsciiC[i2];
            this._base64ToAsciiB[i2] = (byte) c2;
            this._asciiToBase64[c2] = i2;
            i2++;
        }
        if (z) {
            this._asciiToBase64[c] = -2;
        }
    }

    public Base64Variant(Base64Variant base64Variant, String str, int i) {
        this(base64Variant, str, base64Variant._usesPadding, base64Variant._paddingChar, i);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c, int i) {
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        Object obj = base64Variant._base64ToAsciiB;
        System.arraycopy(obj, 0, this._base64ToAsciiB, 0, obj.length);
        obj = base64Variant._base64ToAsciiC;
        System.arraycopy(obj, 0, this._base64ToAsciiC, 0, obj.length);
        obj = base64Variant._asciiToBase64;
        System.arraycopy(obj, 0, this._asciiToBase64, 0, obj.length);
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = i;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c) {
        return c == this._paddingChar;
    }

    public boolean usesPaddingChar(int i) {
        return i == this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public int decodeBase64Char(char c) {
        return c <= '\u007f' ? this._asciiToBase64[c] : -1;
    }

    public void encodeBase64Chunk(StringBuilder stringBuilder, int i) {
        stringBuilder.append(this._base64ToAsciiC[(i >> 18) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 12) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 6) & 63]);
        stringBuilder.append(this._base64ToAsciiC[i & 63]);
    }

    public void encodeBase64Partial(StringBuilder stringBuilder, int i, int i2) {
        stringBuilder.append(this._base64ToAsciiC[(i >> 18) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 12) & 63]);
        if (this._usesPadding) {
            stringBuilder.append(i2 == 2 ? this._base64ToAsciiC[(i >> 6) & 63] : this._paddingChar);
            stringBuilder.append(this._paddingChar);
        } else if (i2 == 2) {
            stringBuilder.append(this._base64ToAsciiC[(i >> 6) & 63]);
        }
    }

    public String encode(byte[] bArr, boolean z) {
        int i;
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder(((length >> 2) + length) + (length >> 3));
        if (z) {
            stringBuilder.append('\"');
        }
        int i2 = 0;
        int i3 = length - 3;
        int maxLineLength = getMaxLineLength() >> 2;
        while (i2 <= i3) {
            i = i2 + 1;
            int i4 = i + 1;
            i = i4 + 1;
            encodeBase64Chunk(stringBuilder, (((bArr[i2] << 8) | (bArr[i] & 255)) << 8) | (bArr[i4] & 255));
            i2 = maxLineLength - 1;
            if (i2 <= 0) {
                stringBuilder.append('\\');
                stringBuilder.append('n');
                i2 = getMaxLineLength() >> 2;
            }
            maxLineLength = i2;
            i2 = i;
        }
        i = length - i2;
        if (i > 0) {
            maxLineLength = i2 + 1;
            i2 = bArr[i2] << 16;
            if (i == 2) {
                length = maxLineLength + 1;
                i2 |= (bArr[maxLineLength] & 255) << 8;
            }
            encodeBase64Partial(stringBuilder, i2, i);
        }
        if (z) {
            stringBuilder.append('\"');
        }
        return stringBuilder.toString();
    }

    public byte[] decode(String str) {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public void decode(String str, ByteArrayBuilder byteArrayBuilder) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2;
            char charAt;
            while (true) {
                i2 = i + 1;
                charAt = str.charAt(i);
                if (i2 < length) {
                    if (charAt > ' ') {
                        break;
                    }
                    i = i2;
                } else {
                    return;
                }
            }
            int decodeBase64Char = decodeBase64Char(charAt);
            if (decodeBase64Char < 0) {
                _reportInvalidBase64(charAt, 0, null);
            }
            if (i2 >= length) {
                _reportBase64EOF();
            }
            i = i2 + 1;
            char charAt2 = str.charAt(i2);
            int decodeBase64Char2 = decodeBase64Char(charAt2);
            if (decodeBase64Char2 < 0) {
                _reportInvalidBase64(charAt2, 1, null);
            }
            i2 = (decodeBase64Char << 6) | decodeBase64Char2;
            if (i >= length) {
                if (usesPadding()) {
                    _reportBase64EOF();
                } else {
                    byteArrayBuilder.append(i2 >> 4);
                    return;
                }
            }
            decodeBase64Char = i + 1;
            charAt = str.charAt(i);
            decodeBase64Char2 = decodeBase64Char(charAt);
            char charAt3;
            if (decodeBase64Char2 < 0) {
                if (decodeBase64Char2 != -2) {
                    _reportInvalidBase64(charAt, 2, null);
                }
                if (decodeBase64Char >= length) {
                    _reportBase64EOF();
                }
                i = decodeBase64Char + 1;
                charAt3 = str.charAt(decodeBase64Char);
                if (!usesPaddingChar(charAt3)) {
                    _reportInvalidBase64(charAt3, 3, "expected padding character '" + getPaddingChar() + "'");
                }
                byteArrayBuilder.append(i2 >> 4);
            } else {
                i2 = (i2 << 6) | decodeBase64Char2;
                if (decodeBase64Char >= length) {
                    if (usesPadding()) {
                        _reportBase64EOF();
                    } else {
                        byteArrayBuilder.appendTwoBytes(i2 >> 2);
                        return;
                    }
                }
                i = decodeBase64Char + 1;
                charAt3 = str.charAt(decodeBase64Char);
                decodeBase64Char2 = decodeBase64Char(charAt3);
                if (decodeBase64Char2 < 0) {
                    if (decodeBase64Char2 != -2) {
                        _reportInvalidBase64(charAt3, 3, null);
                    }
                    byteArrayBuilder.appendTwoBytes(i2 >> 2);
                } else {
                    byteArrayBuilder.appendThreeBytes((i2 << 6) | decodeBase64Char2);
                }
            }
        }
    }

    public String toString() {
        return this._name;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    protected void _reportInvalidBase64(char c, int i, String str) {
        String str2;
        if (c <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (usesPaddingChar(c)) {
            str2 = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new IllegalArgumentException(str2);
    }

    protected void _reportBase64EOF() {
        throw new IllegalArgumentException("Unexpected end-of-String in base64 content");
    }
}
