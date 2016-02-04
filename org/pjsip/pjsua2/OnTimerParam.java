package org.pjsip.pjsua2;

public class OnTimerParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnTimerParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnTimerParam onTimerParam) {
        return onTimerParam == null ? 0 : onTimerParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTimerParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnTimerParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUserData() {
        long OnTimerParam_userData_get = pjsua2JNI.OnTimerParam_userData_get(this.swigCPtr, this);
        return OnTimerParam_userData_get == 0 ? null : new SWIGTYPE_p_void(OnTimerParam_userData_get, false);
    }

    public void setMsecDelay(long j) {
        pjsua2JNI.OnTimerParam_msecDelay_set(this.swigCPtr, this, j);
    }

    public long getMsecDelay() {
        return pjsua2JNI.OnTimerParam_msecDelay_get(this.swigCPtr, this);
    }

    public OnTimerParam() {
        this(pjsua2JNI.new_OnTimerParam(), true);
    }
}
