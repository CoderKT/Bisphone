package org.pjsip.pjsua2;

public class AccountRegConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountRegConfig(long j, boolean z) {
        super(pjsua2JNI.AccountRegConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountRegConfig accountRegConfig) {
        return accountRegConfig == null ? 0 : accountRegConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountRegConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setRegistrarUri(String str) {
        pjsua2JNI.AccountRegConfig_registrarUri_set(this.swigCPtr, this, str);
    }

    public String getRegistrarUri() {
        return pjsua2JNI.AccountRegConfig_registrarUri_get(this.swigCPtr, this);
    }

    public void setRegisterOnAdd(boolean z) {
        pjsua2JNI.AccountRegConfig_registerOnAdd_set(this.swigCPtr, this, z);
    }

    public boolean getRegisterOnAdd() {
        return pjsua2JNI.AccountRegConfig_registerOnAdd_get(this.swigCPtr, this);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountRegConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public SipHeaderVector getHeaders() {
        long AccountRegConfig_headers_get = pjsua2JNI.AccountRegConfig_headers_get(this.swigCPtr, this);
        return AccountRegConfig_headers_get == 0 ? null : new SipHeaderVector(AccountRegConfig_headers_get, false);
    }

    public void setTimeoutSec(long j) {
        pjsua2JNI.AccountRegConfig_timeoutSec_set(this.swigCPtr, this, j);
    }

    public long getTimeoutSec() {
        return pjsua2JNI.AccountRegConfig_timeoutSec_get(this.swigCPtr, this);
    }

    public void setRetryIntervalSec(long j) {
        pjsua2JNI.AccountRegConfig_retryIntervalSec_set(this.swigCPtr, this, j);
    }

    public long getRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_retryIntervalSec_get(this.swigCPtr, this);
    }

    public void setFirstRetryIntervalSec(long j) {
        pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_set(this.swigCPtr, this, j);
    }

    public long getFirstRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_get(this.swigCPtr, this);
    }

    public void setDelayBeforeRefreshSec(long j) {
        pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_set(this.swigCPtr, this, j);
    }

    public long getDelayBeforeRefreshSec() {
        return pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_get(this.swigCPtr, this);
    }

    public void setDropCallsOnFail(boolean z) {
        pjsua2JNI.AccountRegConfig_dropCallsOnFail_set(this.swigCPtr, this, z);
    }

    public boolean getDropCallsOnFail() {
        return pjsua2JNI.AccountRegConfig_dropCallsOnFail_get(this.swigCPtr, this);
    }

    public void setUnregWaitSec(long j) {
        pjsua2JNI.AccountRegConfig_unregWaitSec_set(this.swigCPtr, this, j);
    }

    public long getUnregWaitSec() {
        return pjsua2JNI.AccountRegConfig_unregWaitSec_get(this.swigCPtr, this);
    }

    public void setProxyUse(long j) {
        pjsua2JNI.AccountRegConfig_proxyUse_set(this.swigCPtr, this, j);
    }

    public long getProxyUse() {
        return pjsua2JNI.AccountRegConfig_proxyUse_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountRegConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountRegConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountRegConfig() {
        this(pjsua2JNI.new_AccountRegConfig(), true);
    }
}
