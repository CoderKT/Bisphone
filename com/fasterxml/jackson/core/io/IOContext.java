package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class IOContext {
    protected byte[] _base64Buffer;
    protected final BufferRecycler _bufferRecycler;
    protected char[] _concatCBuffer;
    protected final boolean _managedResource;
    protected char[] _nameCopyBuffer;
    protected byte[] _readIOBuffer;
    protected final Object _sourceRef;
    protected char[] _tokenCBuffer;
    protected byte[] _writeEncodingBuffer;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this._readIOBuffer = null;
        this._writeEncodingBuffer = null;
        this._base64Buffer = null;
        this._tokenCBuffer = null;
        this._concatCBuffer = null;
        this._nameCopyBuffer = null;
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = obj;
        this._managedResource = z;
    }

    public Object getSourceReference() {
        return this._sourceRef;
    }

    public boolean isResourceManaged() {
        return this._managedResource;
    }

    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }

    public byte[] allocBase64Buffer() {
        _verifyAlloc(this._base64Buffer);
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(3);
        this._base64Buffer = allocByteBuffer;
        return allocByteBuffer;
    }

    public char[] allocTokenBuffer() {
        _verifyAlloc(this._tokenCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(0);
        this._tokenCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocTokenBuffer(int i) {
        _verifyAlloc(this._tokenCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(0, i);
        this._tokenCBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocNameCopyBuffer(int i) {
        _verifyAlloc(this._nameCopyBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(3, i);
        this._nameCopyBuffer = allocCharBuffer;
        return allocCharBuffer;
    }

    public void releaseBase64Buffer(byte[] bArr) {
        if (bArr != null) {
            _verifyRelease(bArr, this._base64Buffer);
            this._base64Buffer = null;
            this._bufferRecycler.releaseByteBuffer(3, bArr);
        }
    }

    public void releaseTokenBuffer(char[] cArr) {
        if (cArr != null) {
            _verifyRelease(cArr, this._tokenCBuffer);
            this._tokenCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(0, cArr);
        }
    }

    public void releaseNameCopyBuffer(char[] cArr) {
        if (cArr != null) {
            _verifyRelease(cArr, this._nameCopyBuffer);
            this._nameCopyBuffer = null;
            this._bufferRecycler.releaseCharBuffer(3, cArr);
        }
    }

    protected final void _verifyAlloc(Object obj) {
        if (obj != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }

    protected final void _verifyRelease(byte[] bArr, byte[] bArr2) {
        if (bArr != bArr2 && bArr.length <= bArr2.length) {
            throw wrongBuf();
        }
    }

    protected final void _verifyRelease(char[] cArr, char[] cArr2) {
        if (cArr != cArr2 && cArr.length <= cArr2.length) {
            throw wrongBuf();
        }
    }

    private IllegalArgumentException wrongBuf() {
        return new IllegalArgumentException("Trying to release buffer not owned by the context");
    }
}
