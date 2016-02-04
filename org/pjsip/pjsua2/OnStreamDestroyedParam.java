package org.pjsip.pjsua2;

public class OnStreamDestroyedParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnStreamDestroyedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnStreamDestroyedParam onStreamDestroyedParam) {
        return onStreamDestroyedParam == null ? 0 : onStreamDestroyedParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnStreamDestroyedParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStream(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamDestroyedParam_stream_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getStream() {
        long OnStreamDestroyedParam_stream_get = pjsua2JNI.OnStreamDestroyedParam_stream_get(this.swigCPtr, this);
        return OnStreamDestroyedParam_stream_get == 0 ? null : new SWIGTYPE_p_void(OnStreamDestroyedParam_stream_get, false);
    }

    public void setStreamIdx(long j) {
        pjsua2JNI.OnStreamDestroyedParam_streamIdx_set(this.swigCPtr, this, j);
    }

    public long getStreamIdx() {
        return pjsua2JNI.OnStreamDestroyedParam_streamIdx_get(this.swigCPtr, this);
    }

    public OnStreamDestroyedParam() {
        this(pjsua2JNI.new_OnStreamDestroyedParam(), true);
    }
}
