package org.pjsip.pjsua2;

public class OnCallRedirectedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallRedirectedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallRedirectedParam onCallRedirectedParam) {
        return onCallRedirectedParam == null ? 0 : onCallRedirectedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallRedirectedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTargetUri(String str) {
        pjsua2JNI.OnCallRedirectedParam_targetUri_set(this.swigCPtr, this, str);
    }

    public String getTargetUri() {
        return pjsua2JNI.OnCallRedirectedParam_targetUri_get(this.swigCPtr, this);
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallRedirectedParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public SipEvent getE() {
        long OnCallRedirectedParam_e_get = pjsua2JNI.OnCallRedirectedParam_e_get(this.swigCPtr, this);
        return OnCallRedirectedParam_e_get == 0 ? null : new SipEvent(OnCallRedirectedParam_e_get, false);
    }

    public OnCallRedirectedParam() {
        this(pjsua2JNI.new_OnCallRedirectedParam(), true);
    }
}
