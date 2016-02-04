package org.pjsip.pjsua2;

public class OnCallTransferRequestParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallTransferRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallTransferRequestParam onCallTransferRequestParam) {
        return onCallTransferRequestParam == null ? 0 : onCallTransferRequestParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTransferRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDstUri(String str) {
        pjsua2JNI.OnCallTransferRequestParam_dstUri_set(this.swigCPtr, this, str);
    }

    public String getDstUri() {
        return pjsua2JNI.OnCallTransferRequestParam_dstUri_get(this.swigCPtr, this);
    }

    public void setStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnCallTransferRequestParam_statusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallTransferRequestParam_statusCode_get(this.swigCPtr, this));
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallTransferRequestParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public CallSetting getOpt() {
        long OnCallTransferRequestParam_opt_get = pjsua2JNI.OnCallTransferRequestParam_opt_get(this.swigCPtr, this);
        return OnCallTransferRequestParam_opt_get == 0 ? null : new CallSetting(OnCallTransferRequestParam_opt_get, false);
    }

    public OnCallTransferRequestParam() {
        this(pjsua2JNI.new_OnCallTransferRequestParam(), true);
    }
}
