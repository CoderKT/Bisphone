package org.pjsip.pjsua2;

public class OnCallMediaEventParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallMediaEventParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallMediaEventParam onCallMediaEventParam) {
        return onCallMediaEventParam == null ? 0 : onCallMediaEventParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaEventParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMedIdx(long j) {
        pjsua2JNI.OnCallMediaEventParam_medIdx_set(this.swigCPtr, this, j);
    }

    public long getMedIdx() {
        return pjsua2JNI.OnCallMediaEventParam_medIdx_get(this.swigCPtr, this);
    }

    public void setEv(MediaEvent mediaEvent) {
        pjsua2JNI.OnCallMediaEventParam_ev_set(this.swigCPtr, this, MediaEvent.getCPtr(mediaEvent), mediaEvent);
    }

    public MediaEvent getEv() {
        long OnCallMediaEventParam_ev_get = pjsua2JNI.OnCallMediaEventParam_ev_get(this.swigCPtr, this);
        return OnCallMediaEventParam_ev_get == 0 ? null : new MediaEvent(OnCallMediaEventParam_ev_get, false);
    }

    public OnCallMediaEventParam() {
        this(pjsua2JNI.new_OnCallMediaEventParam(), true);
    }
}
