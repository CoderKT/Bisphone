package org.pjsip.pjsua2;

public class IntVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected IntVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IntVector intVector) {
        return intVector == null ? 0 : intVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_IntVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public IntVector() {
        this(pjsua2JNI.new_IntVector__SWIG_0(), true);
    }

    public IntVector(long j) {
        this(pjsua2JNI.new_IntVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.IntVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.IntVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.IntVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.IntVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.IntVector_clear(this.swigCPtr, this);
    }

    public void add(int i) {
        pjsua2JNI.IntVector_add(this.swigCPtr, this, i);
    }

    public int get(int i) {
        return pjsua2JNI.IntVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, int i2) {
        pjsua2JNI.IntVector_set(this.swigCPtr, this, i, i2);
    }
}
