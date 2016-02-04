package org.pjsip.pjsua2;

public class OnCallTransferStatusParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallTransferStatusParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallTransferStatusParam onCallTransferStatusParam) {
        return onCallTransferStatusParam == null ? 0 : onCallTransferStatusParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTransferStatusParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.OnCallTransferStatusParam_statusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallTransferStatusParam_statusCode_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnCallTransferStatusParam_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.OnCallTransferStatusParam_reason_get(this.swigCPtr, this);
    }

    public void setFinalNotify(boolean z) {
        pjsua2JNI.OnCallTransferStatusParam_finalNotify_set(this.swigCPtr, this, z);
    }

    public boolean getFinalNotify() {
        return pjsua2JNI.OnCallTransferStatusParam_finalNotify_get(this.swigCPtr, this);
    }

    public void setCont(boolean z) {
        pjsua2JNI.OnCallTransferStatusParam_cont_set(this.swigCPtr, this, z);
    }

    public boolean getCont() {
        return pjsua2JNI.OnCallTransferStatusParam_cont_get(this.swigCPtr, this);
    }

    public OnCallTransferStatusParam() {
        this(pjsua2JNI.new_OnCallTransferStatusParam(), true);
    }
}
