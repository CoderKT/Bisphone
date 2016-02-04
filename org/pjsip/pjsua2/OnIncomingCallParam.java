package org.pjsip.pjsua2;

public class OnIncomingCallParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnIncomingCallParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnIncomingCallParam onIncomingCallParam) {
        return onIncomingCallParam == null ? 0 : onIncomingCallParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIncomingCallParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setCallId(int i) {
        pjsua2JNI.OnIncomingCallParam_callId_set(this.swigCPtr, this, i);
    }

    public int getCallId() {
        return pjsua2JNI.OnIncomingCallParam_callId_get(this.swigCPtr, this);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnIncomingCallParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnIncomingCallParam_rdata_get = pjsua2JNI.OnIncomingCallParam_rdata_get(this.swigCPtr, this);
        return OnIncomingCallParam_rdata_get == 0 ? null : new SipRxData(OnIncomingCallParam_rdata_get, false);
    }

    public OnIncomingCallParam() {
        this(pjsua2JNI.new_OnIncomingCallParam(), true);
    }
}
