package org.pjsip.pjsua2;

public class OnInstantMessageStatusParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnInstantMessageStatusParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnInstantMessageStatusParam onInstantMessageStatusParam) {
        return onInstantMessageStatusParam == null ? 0 : onInstantMessageStatusParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnInstantMessageStatusParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnInstantMessageStatusParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUserData() {
        long OnInstantMessageStatusParam_userData_get = pjsua2JNI.OnInstantMessageStatusParam_userData_get(this.swigCPtr, this);
        return OnInstantMessageStatusParam_userData_get == 0 ? null : new SWIGTYPE_p_void(OnInstantMessageStatusParam_userData_get, false);
    }

    public void setToUri(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_toUri_set(this.swigCPtr, this, str);
    }

    public String getToUri() {
        return pjsua2JNI.OnInstantMessageStatusParam_toUri_get(this.swigCPtr, this);
    }

    public void setMsgBody(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_msgBody_set(this.swigCPtr, this, str);
    }

    public String getMsgBody() {
        return pjsua2JNI.OnInstantMessageStatusParam_msgBody_get(this.swigCPtr, this);
    }

    public void setCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnInstantMessageStatusParam_code_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnInstantMessageStatusParam_code_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnInstantMessageStatusParam_reason_get(this.swigCPtr, this);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnInstantMessageStatusParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnInstantMessageStatusParam_rdata_get = pjsua2JNI.OnInstantMessageStatusParam_rdata_get(this.swigCPtr, this);
        return OnInstantMessageStatusParam_rdata_get == 0 ? null : new SipRxData(OnInstantMessageStatusParam_rdata_get, false);
    }

    public OnInstantMessageStatusParam() {
        this(pjsua2JNI.new_OnInstantMessageStatusParam(), true);
    }
}
