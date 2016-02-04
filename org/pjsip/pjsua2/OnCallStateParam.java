package org.pjsip.pjsua2;

public class OnCallStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallStateParam onCallStateParam) {
        return onCallStateParam == null ? 0 : onCallStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallStateParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public SipEvent getE() {
        long OnCallStateParam_e_get = pjsua2JNI.OnCallStateParam_e_get(this.swigCPtr, this);
        return OnCallStateParam_e_get == 0 ? null : new SipEvent(OnCallStateParam_e_get, false);
    }

    public OnCallStateParam() {
        this(pjsua2JNI.new_OnCallStateParam(), true);
    }
}
