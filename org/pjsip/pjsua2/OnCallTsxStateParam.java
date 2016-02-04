package org.pjsip.pjsua2;

public class OnCallTsxStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallTsxStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallTsxStateParam onCallTsxStateParam) {
        return onCallTsxStateParam == null ? 0 : onCallTsxStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTsxStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallTsxStateParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public SipEvent getE() {
        long OnCallTsxStateParam_e_get = pjsua2JNI.OnCallTsxStateParam_e_get(this.swigCPtr, this);
        return OnCallTsxStateParam_e_get == 0 ? null : new SipEvent(OnCallTsxStateParam_e_get, false);
    }

    public OnCallTsxStateParam() {
        this(pjsua2JNI.new_OnCallTsxStateParam(), true);
    }
}
