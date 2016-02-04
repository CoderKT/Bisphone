package org.pjsip.pjsua2;

public class OnStreamCreatedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnStreamCreatedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnStreamCreatedParam onStreamCreatedParam) {
        return onStreamCreatedParam == null ? 0 : onStreamCreatedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnStreamCreatedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStream(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamCreatedParam_stream_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getStream() {
        long OnStreamCreatedParam_stream_get = pjsua2JNI.OnStreamCreatedParam_stream_get(this.swigCPtr, this);
        return OnStreamCreatedParam_stream_get == 0 ? null : new SWIGTYPE_p_void(OnStreamCreatedParam_stream_get, false);
    }

    public void setStreamIdx(long j) {
        pjsua2JNI.OnStreamCreatedParam_streamIdx_set(this.swigCPtr, this, j);
    }

    public long getStreamIdx() {
        return pjsua2JNI.OnStreamCreatedParam_streamIdx_get(this.swigCPtr, this);
    }

    public void setPPort(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamCreatedParam_pPort_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPPort() {
        long OnStreamCreatedParam_pPort_get = pjsua2JNI.OnStreamCreatedParam_pPort_get(this.swigCPtr, this);
        return OnStreamCreatedParam_pPort_get == 0 ? null : new SWIGTYPE_p_void(OnStreamCreatedParam_pPort_get, false);
    }

    public OnStreamCreatedParam() {
        this(pjsua2JNI.new_OnStreamCreatedParam(), true);
    }
}
