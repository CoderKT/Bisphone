package org.pjsip.pjsua2;

public class SipMediaType {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipMediaType(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipMediaType sipMediaType) {
        return sipMediaType == null ? 0 : sipMediaType.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMediaType(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(String str) {
        pjsua2JNI.SipMediaType_type_set(this.swigCPtr, this, str);
    }

    public String getType() {
        return pjsua2JNI.SipMediaType_type_get(this.swigCPtr, this);
    }

    public void setSubType(String str) {
        pjsua2JNI.SipMediaType_subType_set(this.swigCPtr, this, str);
    }

    public String getSubType() {
        return pjsua2JNI.SipMediaType_subType_get(this.swigCPtr, this);
    }

    public SipMediaType() {
        this(pjsua2JNI.new_SipMediaType(), true);
    }
}
