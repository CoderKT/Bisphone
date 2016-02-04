package org.pjsip.pjsua2;

public class OnRegStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnRegStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnRegStateParam onRegStateParam) {
        return onRegStateParam == null ? 0 : onRegStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnRegStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStatus(int i) {
        pjsua2JNI.OnRegStateParam_status_set(this.swigCPtr, this, i);
    }

    public int getStatus() {
        return pjsua2JNI.OnRegStateParam_status_get(this.swigCPtr, this);
    }

    public void setCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnRegStateParam_code_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnRegStateParam_code_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnRegStateParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnRegStateParam_reason_get(this.swigCPtr, this);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnRegStateParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnRegStateParam_rdata_get = pjsua2JNI.OnRegStateParam_rdata_get(this.swigCPtr, this);
        return OnRegStateParam_rdata_get == 0 ? null : new SipRxData(OnRegStateParam_rdata_get, false);
    }

    public void setExpiration(int i) {
        pjsua2JNI.OnRegStateParam_expiration_set(this.swigCPtr, this, i);
    }

    public int getExpiration() {
        return pjsua2JNI.OnRegStateParam_expiration_get(this.swigCPtr, this);
    }

    public OnRegStateParam() {
        this(pjsua2JNI.new_OnRegStateParam(), true);
    }
}
