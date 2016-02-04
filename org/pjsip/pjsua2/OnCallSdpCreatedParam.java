package org.pjsip.pjsua2;

public class OnCallSdpCreatedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallSdpCreatedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallSdpCreatedParam onCallSdpCreatedParam) {
        return onCallSdpCreatedParam == null ? 0 : onCallSdpCreatedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallSdpCreatedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSdp(SdpSession sdpSession) {
        pjsua2JNI.OnCallSdpCreatedParam_sdp_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public SdpSession getSdp() {
        long OnCallSdpCreatedParam_sdp_get = pjsua2JNI.OnCallSdpCreatedParam_sdp_get(this.swigCPtr, this);
        return OnCallSdpCreatedParam_sdp_get == 0 ? null : new SdpSession(OnCallSdpCreatedParam_sdp_get, false);
    }

    public void setRemSdp(SdpSession sdpSession) {
        pjsua2JNI.OnCallSdpCreatedParam_remSdp_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public SdpSession getRemSdp() {
        long OnCallSdpCreatedParam_remSdp_get = pjsua2JNI.OnCallSdpCreatedParam_remSdp_get(this.swigCPtr, this);
        return OnCallSdpCreatedParam_remSdp_get == 0 ? null : new SdpSession(OnCallSdpCreatedParam_remSdp_get, false);
    }

    public OnCallSdpCreatedParam() {
        this(pjsua2JNI.new_OnCallSdpCreatedParam(), true);
    }
}
