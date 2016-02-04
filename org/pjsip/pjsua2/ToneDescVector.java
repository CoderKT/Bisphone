package org.pjsip.pjsua2;

public class ToneDescVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ToneDescVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneDescVector toneDescVector) {
        return toneDescVector == null ? 0 : toneDescVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDescVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ToneDescVector() {
        this(pjsua2JNI.new_ToneDescVector__SWIG_0(), true);
    }

    public ToneDescVector(long j) {
        this(pjsua2JNI.new_ToneDescVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.ToneDescVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.ToneDescVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.ToneDescVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDescVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDescVector_clear(this.swigCPtr, this);
    }

    public void add(ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_add(this.swigCPtr, this, ToneDesc.getCPtr(toneDesc), toneDesc);
    }

    public ToneDesc get(int i) {
        return new ToneDesc(pjsua2JNI.ToneDescVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_set(this.swigCPtr, this, i, ToneDesc.getCPtr(toneDesc), toneDesc);
    }
}
