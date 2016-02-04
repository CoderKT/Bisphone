package org.pjsip.pjsua2;

public class OnMwiInfoParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnMwiInfoParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnMwiInfoParam onMwiInfoParam) {
        return onMwiInfoParam == null ? 0 : onMwiInfoParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnMwiInfoParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setState(pjsip_evsub_state org_pjsip_pjsua2_pjsip_evsub_state) {
        pjsua2JNI.OnMwiInfoParam_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_evsub_state.swigValue());
    }

    public pjsip_evsub_state getState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.OnMwiInfoParam_state_get(this.swigCPtr, this));
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnMwiInfoParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnMwiInfoParam_rdata_get = pjsua2JNI.OnMwiInfoParam_rdata_get(this.swigCPtr, this);
        return OnMwiInfoParam_rdata_get == 0 ? null : new SipRxData(OnMwiInfoParam_rdata_get, false);
    }

    public OnMwiInfoParam() {
        this(pjsua2JNI.new_OnMwiInfoParam(), true);
    }
}
