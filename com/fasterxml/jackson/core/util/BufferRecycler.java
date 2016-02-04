package com.fasterxml.jackson.core.util;

public class BufferRecycler {
    private static final int[] BYTE_BUFFER_LENGTHS;
    private static final int[] CHAR_BUFFER_LENGTHS;
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;

    static {
        BYTE_BUFFER_LENGTHS = new int[]{8000, 8000, 2000, 2000};
        CHAR_BUFFER_LENGTHS = new int[]{4000, 4000, 200, 200};
    }

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int i, int i2) {
        this._byteBuffers = new byte[i][];
        this._charBuffers = new char[i2][];
    }

    public final byte[] allocByteBuffer(int i) {
        return allocByteBuffer(i, 0);
    }

    public byte[] allocByteBuffer(int i, int i2) {
        int byteBufferLength = byteBufferLength(i);
        if (i2 < byteBufferLength) {
            i2 = byteBufferLength;
        }
        byte[] bArr = this._byteBuffers[i];
        if (bArr == null || bArr.length < i2) {
            return balloc(i2);
        }
        this._byteBuffers[i] = null;
        return bArr;
    }

    public final void releaseByteBuffer(int i, byte[] bArr) {
        this._byteBuffers[i] = bArr;
    }

    public final char[] allocCharBuffer(int i) {
        return allocCharBuffer(i, 0);
    }

    public char[] allocCharBuffer(int i, int i2) {
        int charBufferLength = charBufferLength(i);
        if (i2 < charBufferLength) {
            i2 = charBufferLength;
        }
        char[] cArr = this._charBuffers[i];
        if (cArr == null || cArr.length < i2) {
            return calloc(i2);
        }
        this._charBuffers[i] = null;
        return cArr;
    }

    public void releaseCharBuffer(int i, char[] cArr) {
        this._charBuffers[i] = cArr;
    }

    protected int byteBufferLength(int i) {
        return BYTE_BUFFER_LENGTHS[i];
    }

    protected int charBufferLength(int i) {
        return CHAR_BUFFER_LENGTHS[i];
    }

    protected byte[] balloc(int i) {
        return new byte[i];
    }

    protected char[] calloc(int i) {
        return new char[i];
    }
}
