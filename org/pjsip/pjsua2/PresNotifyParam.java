package org.pjsip.pjsua2;

public class PresNotifyParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected PresNotifyParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PresNotifyParam presNotifyParam) {
        return presNotifyParam == null ? 0 : presNotifyParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PresNotifyParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSrvPres(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.PresNotifyParam_srvPres_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getSrvPres() {
        long PresNotifyParam_srvPres_get = pjsua2JNI.PresNotifyParam_srvPres_get(this.swigCPtr, this);
        return PresNotifyParam_srvPres_get == 0 ? null : new SWIGTYPE_p_void(PresNotifyParam_srvPres_get, false);
    }

    public void setState(pjsip_evsub_state org_pjsip_pjsua2_pjsip_evsub_state) {
        pjsua2JNI.PresNotifyParam_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_evsub_state.swigValue());
    }

    public pjsip_evsub_state getState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.PresNotifyParam_state_get(this.swigCPtr, this));
    }

    public void setStateStr(String str) {
        pjsua2JNI.PresNotifyParam_stateStr_set(this.swigCPtr, this, str);
    }

    public String getStateStr() {
        return pjsua2JNI.PresNotifyParam_stateStr_get(this.swigCPtr, this);
    }

    public void setReason(String str) {
        pjsua2JNI.PresNotifyParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.PresNotifyParam_reason_get(this.swigCPtr, this);
    }

    public void setWithBody(boolean z) {
        pjsua2JNI.PresNotifyParam_withBody_set(this.swigCPtr, this, z);
    }

    public boolean getWithBody() {
        return pjsua2JNI.PresNotifyParam_withBody_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.PresNotifyParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long PresNotifyParam_txOption_get = pjsua2JNI.PresNotifyParam_txOption_get(this.swigCPtr, this);
        return PresNotifyParam_txOption_get == 0 ? null : new SipTxOption(PresNotifyParam_txOption_get, false);
    }

    public PresNotifyParam() {
        this(pjsua2JNI.new_PresNotifyParam(), true);
    }
}
