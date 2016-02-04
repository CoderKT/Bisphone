package org.pjsip.pjsua2;

public class OnCallReplacedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallReplacedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallReplacedParam onCallReplacedParam) {
        return onCallReplacedParam == null ? 0 : onCallReplacedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallReplacedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNewCallId(int i) {
        pjsua2JNI.OnCallReplacedParam_newCallId_set(this.swigCPtr, this, i);
    }

    public int getNewCallId() {
        return pjsua2JNI.OnCallReplacedParam_newCallId_get(this.swigCPtr, this);
    }

    public OnCallReplacedParam() {
        this(pjsua2JNI.new_OnCallReplacedParam(), true);
    }
}
