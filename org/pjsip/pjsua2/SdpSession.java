package org.pjsip.pjsua2;

public class SdpSession {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SdpSession(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SdpSession sdpSession) {
        return sdpSession == null ? 0 : sdpSession.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SdpSession(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setWholeSdp(String str) {
        pjsua2JNI.SdpSession_wholeSdp_set(this.swigCPtr, this, str);
    }

    public String getWholeSdp() {
        return pjsua2JNI.SdpSession_wholeSdp_get(this.swigCPtr, this);
    }

    public void setPjSdpSession(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SdpSession_pjSdpSession_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPjSdpSession() {
        long SdpSession_pjSdpSession_get = pjsua2JNI.SdpSession_pjSdpSession_get(this.swigCPtr, this);
        return SdpSession_pjSdpSession_get == 0 ? null : new SWIGTYPE_p_void(SdpSession_pjSdpSession_get, false);
    }

    public SdpSession() {
        this(pjsua2JNI.new_SdpSession(), true);
    }
}
