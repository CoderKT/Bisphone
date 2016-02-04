package org.pjsip.pjsua2;

public class MediaTransportInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MediaTransportInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaTransportInfo mediaTransportInfo) {
        return mediaTransportInfo == null ? 0 : mediaTransportInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaTransportInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSrcRtpName(String str) {
        pjsua2JNI.MediaTransportInfo_srcRtpName_set(this.swigCPtr, this, str);
    }

    public String getSrcRtpName() {
        return pjsua2JNI.MediaTransportInfo_srcRtpName_get(this.swigCPtr, this);
    }

    public void setSrcRtcpName(String str) {
        pjsua2JNI.MediaTransportInfo_srcRtcpName_set(this.swigCPtr, this, str);
    }

    public String getSrcRtcpName() {
        return pjsua2JNI.MediaTransportInfo_srcRtcpName_get(this.swigCPtr, this);
    }

    public MediaTransportInfo() {
        this(pjsua2JNI.new_MediaTransportInfo(), true);
    }
}
