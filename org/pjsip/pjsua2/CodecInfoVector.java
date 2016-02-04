package org.pjsip.pjsua2;

public class CodecInfoVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CodecInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CodecInfoVector codecInfoVector) {
        return codecInfoVector == null ? 0 : codecInfoVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public CodecInfoVector() {
        this(pjsua2JNI.new_CodecInfoVector__SWIG_0(), true);
    }

    public CodecInfoVector(long j) {
        this(pjsua2JNI.new_CodecInfoVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.CodecInfoVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.CodecInfoVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.CodecInfoVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CodecInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CodecInfoVector_clear(this.swigCPtr, this);
    }

    public void add(CodecInfo codecInfo) {
        pjsua2JNI.CodecInfoVector_add(this.swigCPtr, this, CodecInfo.getCPtr(codecInfo), codecInfo);
    }

    public CodecInfo get(int i) {
        long CodecInfoVector_get = pjsua2JNI.CodecInfoVector_get(this.swigCPtr, this, i);
        return CodecInfoVector_get == 0 ? null : new CodecInfo(CodecInfoVector_get, false);
    }

    public void set(int i, CodecInfo codecInfo) {
        pjsua2JNI.CodecInfoVector_set(this.swigCPtr, this, i, CodecInfo.getCPtr(codecInfo), codecInfo);
    }
}
