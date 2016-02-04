package org.pjsip.pjsua2;

public class OnTransportStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnTransportStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnTransportStateParam onTransportStateParam) {
        return onTransportStateParam == null ? 0 : onTransportStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTransportStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setHnd(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnTransportStateParam_hnd_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getHnd() {
        long OnTransportStateParam_hnd_get = pjsua2JNI.OnTransportStateParam_hnd_get(this.swigCPtr, this);
        return OnTransportStateParam_hnd_get == 0 ? null : new SWIGTYPE_p_void(OnTransportStateParam_hnd_get, false);
    }

    public void setState(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state) {
        pjsua2JNI.OnTransportStateParam_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_transport_state.swigValue());
    }

    public pjsip_transport_state getState() {
        return pjsip_transport_state.swigToEnum(pjsua2JNI.OnTransportStateParam_state_get(this.swigCPtr, this));
    }

    public void setLastError(int i) {
        pjsua2JNI.OnTransportStateParam_lastError_set(this.swigCPtr, this, i);
    }

    public int getLastError() {
        return pjsua2JNI.OnTransportStateParam_lastError_get(this.swigCPtr, this);
    }

    public OnTransportStateParam() {
        this(pjsua2JNI.new_OnTransportStateParam(), true);
    }
}
