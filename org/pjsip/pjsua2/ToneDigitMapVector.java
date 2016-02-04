package org.pjsip.pjsua2;

public class ToneDigitMapVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ToneDigitMapVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneDigitMapVector toneDigitMapVector) {
        return toneDigitMapVector == null ? 0 : toneDigitMapVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitMapVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ToneDigitMapVector() {
        this(pjsua2JNI.new_ToneDigitMapVector__SWIG_0(), true);
    }

    public ToneDigitMapVector(long j) {
        this(pjsua2JNI.new_ToneDigitMapVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.ToneDigitMapVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.ToneDigitMapVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.ToneDigitMapVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDigitMapVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDigitMapVector_clear(this.swigCPtr, this);
    }

    public void add(ToneDigitMapDigit toneDigitMapDigit) {
        pjsua2JNI.ToneDigitMapVector_add(this.swigCPtr, this, ToneDigitMapDigit.getCPtr(toneDigitMapDigit), toneDigitMapDigit);
    }

    public ToneDigitMapDigit get(int i) {
        return new ToneDigitMapDigit(pjsua2JNI.ToneDigitMapVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, ToneDigitMapDigit toneDigitMapDigit) {
        pjsua2JNI.ToneDigitMapVector_set(this.swigCPtr, this, i, ToneDigitMapDigit.getCPtr(toneDigitMapDigit), toneDigitMapDigit);
    }
}
