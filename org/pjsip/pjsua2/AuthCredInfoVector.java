package org.pjsip.pjsua2;

public class AuthCredInfoVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AuthCredInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AuthCredInfoVector authCredInfoVector) {
        return authCredInfoVector == null ? 0 : authCredInfoVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public AuthCredInfoVector() {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_0(), true);
    }

    public AuthCredInfoVector(long j) {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.AuthCredInfoVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.AuthCredInfoVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AuthCredInfoVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AuthCredInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AuthCredInfoVector_clear(this.swigCPtr, this);
    }

    public void add(AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_add(this.swigCPtr, this, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }

    public AuthCredInfo get(int i) {
        return new AuthCredInfo(pjsua2JNI.AuthCredInfoVector_get(this.swigCPtr, this, i), false);
    }

    public void set(int i, AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_set(this.swigCPtr, this, i, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }
}
