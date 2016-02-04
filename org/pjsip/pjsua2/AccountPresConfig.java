package org.pjsip.pjsua2;

public class AccountPresConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountPresConfig(long j, boolean z) {
        super(pjsua2JNI.AccountPresConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountPresConfig accountPresConfig) {
        return accountPresConfig == null ? 0 : accountPresConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountPresConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountPresConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public SipHeaderVector getHeaders() {
        long AccountPresConfig_headers_get = pjsua2JNI.AccountPresConfig_headers_get(this.swigCPtr, this);
        return AccountPresConfig_headers_get == 0 ? null : new SipHeaderVector(AccountPresConfig_headers_get, false);
    }

    public void setPublishEnabled(boolean z) {
        pjsua2JNI.AccountPresConfig_publishEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getPublishEnabled() {
        return pjsua2JNI.AccountPresConfig_publishEnabled_get(this.swigCPtr, this);
    }

    public void setPublishQueue(boolean z) {
        pjsua2JNI.AccountPresConfig_publishQueue_set(this.swigCPtr, this, z);
    }

    public boolean getPublishQueue() {
        return pjsua2JNI.AccountPresConfig_publishQueue_get(this.swigCPtr, this);
    }

    public void setPublishShutdownWaitMsec(long j) {
        pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_set(this.swigCPtr, this, j);
    }

    public long getPublishShutdownWaitMsec() {
        return pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_get(this.swigCPtr, this);
    }

    public void setPidfTupleId(String str) {
        pjsua2JNI.AccountPresConfig_pidfTupleId_set(this.swigCPtr, this, str);
    }

    public String getPidfTupleId() {
        return pjsua2JNI.AccountPresConfig_pidfTupleId_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountPresConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountPresConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountPresConfig() {
        this(pjsua2JNI.new_AccountPresConfig(), true);
    }
}
