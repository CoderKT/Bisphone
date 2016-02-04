package org.pjsip.pjsua2;

public class OnNatDetectionCompleteParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnNatDetectionCompleteParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnNatDetectionCompleteParam onNatDetectionCompleteParam) {
        return onNatDetectionCompleteParam == null ? 0 : onNatDetectionCompleteParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnNatDetectionCompleteParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStatus(int i) {
        pjsua2JNI.OnNatDetectionCompleteParam_status_set(this.swigCPtr, this, i);
    }

    public int getStatus() {
        return pjsua2JNI.OnNatDetectionCompleteParam_status_get(this.swigCPtr, this);
    }

    public void setReason(String str) {
        pjsua2JNI.OnNatDetectionCompleteParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnNatDetectionCompleteParam_reason_get(this.swigCPtr, this);
    }

    public void setNatType(pj_stun_nat_type org_pjsip_pjsua2_pj_stun_nat_type) {
        pjsua2JNI.OnNatDetectionCompleteParam_natType_set(this.swigCPtr, this, org_pjsip_pjsua2_pj_stun_nat_type.swigValue());
    }

    public pj_stun_nat_type getNatType() {
        return pj_stun_nat_type.swigToEnum(pjsua2JNI.OnNatDetectionCompleteParam_natType_get(this.swigCPtr, this));
    }

    public void setNatTypeName(String str) {
        pjsua2JNI.OnNatDetectionCompleteParam_natTypeName_set(this.swigCPtr, this, str);
    }

    public String getNatTypeName() {
        return pjsua2JNI.OnNatDetectionCompleteParam_natTypeName_get(this.swigCPtr, this);
    }

    public OnNatDetectionCompleteParam() {
        this(pjsua2JNI.new_OnNatDetectionCompleteParam(), true);
    }
}
