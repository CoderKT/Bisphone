package org.pjsip.pjsua2;

public class OnCallReplaceRequestParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallReplaceRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallReplaceRequestParam onCallReplaceRequestParam) {
        return onCallReplaceRequestParam == null ? 0 : onCallReplaceRequestParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallReplaceRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnCallReplaceRequestParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnCallReplaceRequestParam_rdata_get = pjsua2JNI.OnCallReplaceRequestParam_rdata_get(this.swigCPtr, this);
        return OnCallReplaceRequestParam_rdata_get == 0 ? null : new SipRxData(OnCallReplaceRequestParam_rdata_get, false);
    }

    public void setStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnCallReplaceRequestParam_statusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallReplaceRequestParam_statusCode_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnCallReplaceRequestParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnCallReplaceRequestParam_reason_get(this.swigCPtr, this);
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallReplaceRequestParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public CallSetting getOpt() {
        long OnCallReplaceRequestParam_opt_get = pjsua2JNI.OnCallReplaceRequestParam_opt_get(this.swigCPtr, this);
        return OnCallReplaceRequestParam_opt_get == 0 ? null : new CallSetting(OnCallReplaceRequestParam_opt_get, false);
    }

    public OnCallReplaceRequestParam() {
        this(pjsua2JNI.new_OnCallReplaceRequestParam(), true);
    }
}
