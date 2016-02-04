package org.pjsip.pjsua2;

public class OnCallRxOfferParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallRxOfferParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallRxOfferParam onCallRxOfferParam) {
        return onCallRxOfferParam == null ? 0 : onCallRxOfferParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallRxOfferParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setOffer(SdpSession sdpSession) {
        pjsua2JNI.OnCallRxOfferParam_offer_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public SdpSession getOffer() {
        long OnCallRxOfferParam_offer_get = pjsua2JNI.OnCallRxOfferParam_offer_get(this.swigCPtr, this);
        return OnCallRxOfferParam_offer_get == 0 ? null : new SdpSession(OnCallRxOfferParam_offer_get, false);
    }

    public void setStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnCallRxOfferParam_statusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallRxOfferParam_statusCode_get(this.swigCPtr, this));
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallRxOfferParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public CallSetting getOpt() {
        long OnCallRxOfferParam_opt_get = pjsua2JNI.OnCallRxOfferParam_opt_get(this.swigCPtr, this);
        return OnCallRxOfferParam_opt_get == 0 ? null : new CallSetting(OnCallRxOfferParam_opt_get, false);
    }

    public OnCallRxOfferParam() {
        this(pjsua2JNI.new_OnCallRxOfferParam(), true);
    }
}
