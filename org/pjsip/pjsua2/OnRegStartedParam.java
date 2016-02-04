package org.pjsip.pjsua2;

public class OnRegStartedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnRegStartedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnRegStartedParam onRegStartedParam) {
        return onRegStartedParam == null ? 0 : onRegStartedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnRegStartedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRenew(boolean z) {
        pjsua2JNI.OnRegStartedParam_renew_set(this.swigCPtr, this, z);
    }

    public boolean getRenew() {
        return pjsua2JNI.OnRegStartedParam_renew_get(this.swigCPtr, this);
    }

    public OnRegStartedParam() {
        this(pjsua2JNI.new_OnRegStartedParam(), true);
    }
}
