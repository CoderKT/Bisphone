package org.pjsip.pjsua2;

public class OnCreateMediaTransportParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCreateMediaTransportParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCreateMediaTransportParam onCreateMediaTransportParam) {
        return onCreateMediaTransportParam == null ? 0 : onCreateMediaTransportParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCreateMediaTransportParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMediaIdx(long j) {
        pjsua2JNI.OnCreateMediaTransportParam_mediaIdx_set(this.swigCPtr, this, j);
    }

    public long getMediaIdx() {
        return pjsua2JNI.OnCreateMediaTransportParam_mediaIdx_get(this.swigCPtr, this);
    }

    public void setMediaTp(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnCreateMediaTransportParam_mediaTp_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getMediaTp() {
        long OnCreateMediaTransportParam_mediaTp_get = pjsua2JNI.OnCreateMediaTransportParam_mediaTp_get(this.swigCPtr, this);
        return OnCreateMediaTransportParam_mediaTp_get == 0 ? null : new SWIGTYPE_p_void(OnCreateMediaTransportParam_mediaTp_get, false);
    }

    public void setFlags(long j) {
        pjsua2JNI.OnCreateMediaTransportParam_flags_set(this.swigCPtr, this, j);
    }

    public long getFlags() {
        return pjsua2JNI.OnCreateMediaTransportParam_flags_get(this.swigCPtr, this);
    }

    public OnCreateMediaTransportParam() {
        this(pjsua2JNI.new_OnCreateMediaTransportParam(), true);
    }
}
