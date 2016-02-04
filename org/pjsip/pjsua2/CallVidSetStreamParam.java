package org.pjsip.pjsua2;

public class CallVidSetStreamParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallVidSetStreamParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallVidSetStreamParam callVidSetStreamParam) {
        return callVidSetStreamParam == null ? 0 : callVidSetStreamParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallVidSetStreamParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMedIdx(int i) {
        pjsua2JNI.CallVidSetStreamParam_medIdx_set(this.swigCPtr, this, i);
    }

    public int getMedIdx() {
        return pjsua2JNI.CallVidSetStreamParam_medIdx_get(this.swigCPtr, this);
    }

    public void setDir(pjmedia_dir org_pjsip_pjsua2_pjmedia_dir) {
        pjsua2JNI.CallVidSetStreamParam_dir_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_dir.swigValue());
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.CallVidSetStreamParam_dir_get(this.swigCPtr, this));
    }

    public void setCapDev(int i) {
        pjsua2JNI.CallVidSetStreamParam_capDev_set(this.swigCPtr, this, i);
    }

    public int getCapDev() {
        return pjsua2JNI.CallVidSetStreamParam_capDev_get(this.swigCPtr, this);
    }

    public CallVidSetStreamParam() {
        this(pjsua2JNI.new_CallVidSetStreamParam(), true);
    }
}
