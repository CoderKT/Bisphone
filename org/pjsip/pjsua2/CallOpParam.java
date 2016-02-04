package org.pjsip.pjsua2;

public class CallOpParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallOpParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallOpParam callOpParam) {
        return callOpParam == null ? 0 : callOpParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallOpParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.CallOpParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public CallSetting getOpt() {
        long CallOpParam_opt_get = pjsua2JNI.CallOpParam_opt_get(this.swigCPtr, this);
        return CallOpParam_opt_get == 0 ? null : new CallSetting(CallOpParam_opt_get, false);
    }

    public void setStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.CallOpParam_statusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.CallOpParam_statusCode_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.CallOpParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.CallOpParam_reason_get(this.swigCPtr, this);
    }

    public void setOptions(long j) {
        pjsua2JNI.CallOpParam_options_set(this.swigCPtr, this, j);
    }

    public long getOptions() {
        return pjsua2JNI.CallOpParam_options_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.CallOpParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long CallOpParam_txOption_get = pjsua2JNI.CallOpParam_txOption_get(this.swigCPtr, this);
        return CallOpParam_txOption_get == 0 ? null : new SipTxOption(CallOpParam_txOption_get, false);
    }

    public CallOpParam(boolean z) {
        this(pjsua2JNI.new_CallOpParam__SWIG_0(z), true);
    }

    public CallOpParam() {
        this(pjsua2JNI.new_CallOpParam__SWIG_1(), true);
    }
}
