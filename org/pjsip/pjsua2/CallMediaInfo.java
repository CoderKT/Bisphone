package org.pjsip.pjsua2;

public class CallMediaInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallMediaInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallMediaInfo callMediaInfo) {
        return callMediaInfo == null ? 0 : callMediaInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallMediaInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setIndex(long j) {
        pjsua2JNI.CallMediaInfo_index_set(this.swigCPtr, this, j);
    }

    public long getIndex() {
        return pjsua2JNI.CallMediaInfo_index_get(this.swigCPtr, this);
    }

    public void setType(pjmedia_type org_pjsip_pjsua2_pjmedia_type) {
        pjsua2JNI.CallMediaInfo_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_type.swigValue());
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.CallMediaInfo_type_get(this.swigCPtr, this));
    }

    public void setDir(pjmedia_dir org_pjsip_pjsua2_pjmedia_dir) {
        pjsua2JNI.CallMediaInfo_dir_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_dir.swigValue());
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.CallMediaInfo_dir_get(this.swigCPtr, this));
    }

    public void setStatus(pjsua_call_media_status org_pjsip_pjsua2_pjsua_call_media_status) {
        pjsua2JNI.CallMediaInfo_status_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_call_media_status.swigValue());
    }

    public pjsua_call_media_status getStatus() {
        return pjsua_call_media_status.swigToEnum(pjsua2JNI.CallMediaInfo_status_get(this.swigCPtr, this));
    }

    public void setAudioConfSlot(int i) {
        pjsua2JNI.CallMediaInfo_audioConfSlot_set(this.swigCPtr, this, i);
    }

    public int getAudioConfSlot() {
        return pjsua2JNI.CallMediaInfo_audioConfSlot_get(this.swigCPtr, this);
    }

    public void setVideoIncomingWindowId(int i) {
        pjsua2JNI.CallMediaInfo_videoIncomingWindowId_set(this.swigCPtr, this, i);
    }

    public int getVideoIncomingWindowId() {
        return pjsua2JNI.CallMediaInfo_videoIncomingWindowId_get(this.swigCPtr, this);
    }

    public void setVideoCapDev(int i) {
        pjsua2JNI.CallMediaInfo_videoCapDev_set(this.swigCPtr, this, i);
    }

    public int getVideoCapDev() {
        return pjsua2JNI.CallMediaInfo_videoCapDev_get(this.swigCPtr, this);
    }

    public CallMediaInfo() {
        this(pjsua2JNI.new_CallMediaInfo(), true);
    }
}
