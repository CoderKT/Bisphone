package org.pjsip.pjsua2;

public class OnIncomingSubscribeParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnIncomingSubscribeParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnIncomingSubscribeParam onIncomingSubscribeParam) {
        return onIncomingSubscribeParam == null ? 0 : onIncomingSubscribeParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIncomingSubscribeParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSrvPres(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnIncomingSubscribeParam_srvPres_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getSrvPres() {
        long OnIncomingSubscribeParam_srvPres_get = pjsua2JNI.OnIncomingSubscribeParam_srvPres_get(this.swigCPtr, this);
        return OnIncomingSubscribeParam_srvPres_get == 0 ? null : new SWIGTYPE_p_void(OnIncomingSubscribeParam_srvPres_get, false);
    }

    public void setFromUri(String str) {
        pjsua2JNI.OnIncomingSubscribeParam_fromUri_set(this.swigCPtr, this, str);
    }

    public String getFromUri() {
        return pjsua2JNI.OnIncomingSubscribeParam_fromUri_get(this.swigCPtr, this);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnIncomingSubscribeParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnIncomingSubscribeParam_rdata_get = pjsua2JNI.OnIncomingSubscribeParam_rdata_get(this.swigCPtr, this);
        return OnIncomingSubscribeParam_rdata_get == 0 ? null : new SipRxData(OnIncomingSubscribeParam_rdata_get, false);
    }

    public void setCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnIncomingSubscribeParam_code_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnIncomingSubscribeParam_code_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnIncomingSubscribeParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnIncomingSubscribeParam_reason_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.OnIncomingSubscribeParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long OnIncomingSubscribeParam_txOption_get = pjsua2JNI.OnIncomingSubscribeParam_txOption_get(this.swigCPtr, this);
        return OnIncomingSubscribeParam_txOption_get == 0 ? null : new SipTxOption(OnIncomingSubscribeParam_txOption_get, false);
    }

    public OnIncomingSubscribeParam() {
        this(pjsua2JNI.new_OnIncomingSubscribeParam(), true);
    }
}
