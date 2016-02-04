package org.pjsip.pjsua2;

public class CallMediaInfoVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallMediaInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallMediaInfoVector callMediaInfoVector) {
        return callMediaInfoVector == null ? 0 : callMediaInfoVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallMediaInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public CallMediaInfoVector() {
        this(pjsua2JNI.new_CallMediaInfoVector__SWIG_0(), true);
    }

    public CallMediaInfoVector(long j) {
        this(pjsua2JNI.new_CallMediaInfoVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.CallMediaInfoVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.CallMediaInfoVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.CallMediaInfoVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CallMediaInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CallMediaInfoVector_clear(this.swigCPtr, this);
    }

    public void add(CallMediaInfo callMediaInfo) {
        pjsua2JNI.CallMediaInfoVector_add(this.swigCPtr, this, CallMediaInfo.getCPtr(callMediaInfo), callMediaInfo);
    }

    public CallMediaInfo get(int i) {
        return new CallMediaInfo(pjsua2JNI.CallMediaInfoVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, CallMediaInfo callMediaInfo) {
        pjsua2JNI.CallMediaInfoVector_set(this.swigCPtr, this, i, CallMediaInfo.getCPtr(callMediaInfo), callMediaInfo);
    }
}
