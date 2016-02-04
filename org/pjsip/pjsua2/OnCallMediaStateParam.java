package org.pjsip.pjsua2;

public class OnCallMediaStateParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnCallMediaStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnCallMediaStateParam onCallMediaStateParam) {
        return onCallMediaStateParam == null ? 0 : onCallMediaStateParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public OnCallMediaStateParam() {
        this(pjsua2JNI.new_OnCallMediaStateParam(), true);
    }
}
