package org.pjsip.pjsua2;

public class AccountSipConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountSipConfig(long j, boolean z) {
        super(pjsua2JNI.AccountSipConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountSipConfig accountSipConfig) {
        return accountSipConfig == null ? 0 : accountSipConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountSipConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setAuthCreds(AuthCredInfoVector authCredInfoVector) {
        pjsua2JNI.AccountSipConfig_authCreds_set(this.swigCPtr, this, AuthCredInfoVector.getCPtr(authCredInfoVector), authCredInfoVector);
    }

    public AuthCredInfoVector getAuthCreds() {
        long AccountSipConfig_authCreds_get = pjsua2JNI.AccountSipConfig_authCreds_get(this.swigCPtr, this);
        return AccountSipConfig_authCreds_get == 0 ? null : new AuthCredInfoVector(AccountSipConfig_authCreds_get, false);
    }

    public void setProxies(StringVector stringVector) {
        pjsua2JNI.AccountSipConfig_proxies_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public StringVector getProxies() {
        long AccountSipConfig_proxies_get = pjsua2JNI.AccountSipConfig_proxies_get(this.swigCPtr, this);
        return AccountSipConfig_proxies_get == 0 ? null : new StringVector(AccountSipConfig_proxies_get, false);
    }

    public void setContactForced(String str) {
        pjsua2JNI.AccountSipConfig_contactForced_set(this.swigCPtr, this, str);
    }

    public String getContactForced() {
        return pjsua2JNI.AccountSipConfig_contactForced_get(this.swigCPtr, this);
    }

    public void setContactParams(String str) {
        pjsua2JNI.AccountSipConfig_contactParams_set(this.swigCPtr, this, str);
    }

    public String getContactParams() {
        return pjsua2JNI.AccountSipConfig_contactParams_get(this.swigCPtr, this);
    }

    public void setContactUriParams(String str) {
        pjsua2JNI.AccountSipConfig_contactUriParams_set(this.swigCPtr, this, str);
    }

    public String getContactUriParams() {
        return pjsua2JNI.AccountSipConfig_contactUriParams_get(this.swigCPtr, this);
    }

    public void setAuthInitialEmpty(boolean z) {
        pjsua2JNI.AccountSipConfig_authInitialEmpty_set(this.swigCPtr, this, z);
    }

    public boolean getAuthInitialEmpty() {
        return pjsua2JNI.AccountSipConfig_authInitialEmpty_get(this.swigCPtr, this);
    }

    public void setAuthInitialAlgorithm(String str) {
        pjsua2JNI.AccountSipConfig_authInitialAlgorithm_set(this.swigCPtr, this, str);
    }

    public String getAuthInitialAlgorithm() {
        return pjsua2JNI.AccountSipConfig_authInitialAlgorithm_get(this.swigCPtr, this);
    }

    public void setTransportId(int i) {
        pjsua2JNI.AccountSipConfig_transportId_set(this.swigCPtr, this, i);
    }

    public int getTransportId() {
        return pjsua2JNI.AccountSipConfig_transportId_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountSipConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountSipConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountSipConfig() {
        this(pjsua2JNI.new_AccountSipConfig(), true);
    }
}
