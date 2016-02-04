package org.pjsip.pjsua2;

public class StringVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected StringVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(StringVector stringVector) {
        return stringVector == null ? 0 : stringVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StringVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public StringVector() {
        this(pjsua2JNI.new_StringVector__SWIG_0(), true);
    }

    public StringVector(long j) {
        this(pjsua2JNI.new_StringVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.StringVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.StringVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.StringVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.StringVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.StringVector_clear(this.swigCPtr, this);
    }

    public void add(String str) {
        pjsua2JNI.StringVector_add(this.swigCPtr, this, str);
    }

    public String get(int i) {
        return pjsua2JNI.StringVector_get(this.swigCPtr, this, i);
    }

    public void set(int i, String str) {
        pjsua2JNI.StringVector_set(this.swigCPtr, this, i, str);
    }
}
