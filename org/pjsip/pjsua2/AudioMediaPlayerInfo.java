package org.pjsip.pjsua2;

public class AudioMediaPlayerInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AudioMediaPlayerInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaPlayerInfo audioMediaPlayerInfo) {
        return audioMediaPlayerInfo == null ? 0 : audioMediaPlayerInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaPlayerInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setFormatId(pjmedia_format_id org_pjsip_pjsua2_pjmedia_format_id) {
        pjsua2JNI.AudioMediaPlayerInfo_formatId_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_format_id.swigValue());
    }

    public pjmedia_format_id getFormatId() {
        return pjmedia_format_id.swigToEnum(pjsua2JNI.AudioMediaPlayerInfo_formatId_get(this.swigCPtr, this));
    }

    public void setPayloadBitsPerSample(long j) {
        pjsua2JNI.AudioMediaPlayerInfo_payloadBitsPerSample_set(this.swigCPtr, this, j);
    }

    public long getPayloadBitsPerSample() {
        return pjsua2JNI.AudioMediaPlayerInfo_payloadBitsPerSample_get(this.swigCPtr, this);
    }

    public void setSizeBytes(long j) {
        pjsua2JNI.AudioMediaPlayerInfo_sizeBytes_set(this.swigCPtr, this, j);
    }

    public long getSizeBytes() {
        return pjsua2JNI.AudioMediaPlayerInfo_sizeBytes_get(this.swigCPtr, this);
    }

    public void setSizeSamples(long j) {
        pjsua2JNI.AudioMediaPlayerInfo_sizeSamples_set(this.swigCPtr, this, j);
    }

    public long getSizeSamples() {
        return pjsua2JNI.AudioMediaPlayerInfo_sizeSamples_get(this.swigCPtr, this);
    }

    public AudioMediaPlayerInfo() {
        this(pjsua2JNI.new_AudioMediaPlayerInfo(), true);
    }
}
