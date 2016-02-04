package org.pjsip.pjsua2;

public class OnCallMediaTransportStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallMediaTransportStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallMediaTransportStateParam onCallMediaTransportStateParam) {
        return onCallMediaTransportStateParam == null ? 0 : onCallMediaTransportStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaTransportStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMedIdx(long j) {
        pjsua2JNI.OnCallMediaTransportStateParam_medIdx_set(this.swigCPtr, this, j);
    }

    public long getMedIdx() {
        return pjsua2JNI.OnCallMediaTransportStateParam_medIdx_get(this.swigCPtr, this);
    }

    public void setState(pjsua_med_tp_st org_pjsip_pjsua2_pjsua_med_tp_st) {
        pjsua2JNI.OnCallMediaTransportStateParam_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_med_tp_st.swigValue());
    }

    public pjsua_med_tp_st getState() {
        return pjsua_med_tp_st.swigToEnum(pjsua2JNI.OnCallMediaTransportStateParam_state_get(this.swigCPtr, this));
    }

    public void setStatus(int i) {
        pjsua2JNI.OnCallMediaTransportStateParam_status_set(this.swigCPtr, this, i);
    }

    public int getStatus() {
        return pjsua2JNI.OnCallMediaTransportStateParam_status_get(this.swigCPtr, this);
    }

    public void setSipErrorCode(int i) {
        pjsua2JNI.OnCallMediaTransportStateParam_sipErrorCode_set(this.swigCPtr, this, i);
    }

    public int getSipErrorCode() {
        return pjsua2JNI.OnCallMediaTransportStateParam_sipErrorCode_get(this.swigCPtr, this);
    }

    public OnCallMediaTransportStateParam() {
        this(pjsua2JNI.new_OnCallMediaTransportStateParam(), true);
    }
}
