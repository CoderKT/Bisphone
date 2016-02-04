package org.pjsip.pjsua2;

public class SipHeader {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipHeader(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipHeader sipHeader) {
        return sipHeader == null ? 0 : sipHeader.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipHeader(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setHName(String str) {
        pjsua2JNI.SipHeader_hName_set(this.swigCPtr, this, str);
    }

    public String getHName() {
        return pjsua2JNI.SipHeader_hName_get(this.swigCPtr, this);
    }

    public void setHValue(String str) {
        pjsua2JNI.SipHeader_hValue_set(this.swigCPtr, this, str);
    }

    public String getHValue() {
        return pjsua2JNI.SipHeader_hValue_get(this.swigCPtr, this);
    }

    public SipHeader() {
        this(pjsua2JNI.new_SipHeader(), true);
    }
}
