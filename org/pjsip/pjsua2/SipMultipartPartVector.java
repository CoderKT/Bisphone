package org.pjsip.pjsua2;

public class SipMultipartPartVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipMultipartPartVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipMultipartPartVector sipMultipartPartVector) {
        return sipMultipartPartVector == null ? 0 : sipMultipartPartVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMultipartPartVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public SipMultipartPartVector() {
        this(pjsua2JNI.new_SipMultipartPartVector__SWIG_0(), true);
    }

    public SipMultipartPartVector(long j) {
        this(pjsua2JNI.new_SipMultipartPartVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.SipMultipartPartVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.SipMultipartPartVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.SipMultipartPartVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.SipMultipartPartVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.SipMultipartPartVector_clear(this.swigCPtr, this);
    }

    public void add(SipMultipartPart sipMultipartPart) {
        pjsua2JNI.SipMultipartPartVector_add(this.swigCPtr, this, SipMultipartPart.getCPtr(sipMultipartPart), sipMultipartPart);
    }

    public SipMultipartPart get(int i) {
        return new SipMultipartPart(pjsua2JNI.SipMultipartPartVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, SipMultipartPart sipMultipartPart) {
        pjsua2JNI.SipMultipartPartVector_set(this.swigCPtr, this, i, SipMultipartPart.getCPtr(sipMultipartPart), sipMultipartPart);
    }
}
