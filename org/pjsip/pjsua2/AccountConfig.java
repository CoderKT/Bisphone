package org.pjsip.pjsua2;

public class AccountConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountConfig(long j, boolean z) {
        super(pjsua2JNI.AccountConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountConfig accountConfig) {
        return accountConfig == null ? 0 : accountConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setPriority(int i) {
        pjsua2JNI.AccountConfig_priority_set(this.swigCPtr, this, i);
    }

    public int getPriority() {
        return pjsua2JNI.AccountConfig_priority_get(this.swigCPtr, this);
    }

    public void setIdUri(String str) {
        pjsua2JNI.AccountConfig_idUri_set(this.swigCPtr, this, str);
    }

    public String getIdUri() {
        return pjsua2JNI.AccountConfig_idUri_get(this.swigCPtr, this);
    }

    public void setRegConfig(AccountRegConfig accountRegConfig) {
        pjsua2JNI.AccountConfig_regConfig_set(this.swigCPtr, this, AccountRegConfig.getCPtr(accountRegConfig), accountRegConfig);
    }

    public AccountRegConfig getRegConfig() {
        long AccountConfig_regConfig_get = pjsua2JNI.AccountConfig_regConfig_get(this.swigCPtr, this);
        return AccountConfig_regConfig_get == 0 ? null : new AccountRegConfig(AccountConfig_regConfig_get, false);
    }

    public void setSipConfig(AccountSipConfig accountSipConfig) {
        pjsua2JNI.AccountConfig_sipConfig_set(this.swigCPtr, this, AccountSipConfig.getCPtr(accountSipConfig), accountSipConfig);
    }

    public AccountSipConfig getSipConfig() {
        long AccountConfig_sipConfig_get = pjsua2JNI.AccountConfig_sipConfig_get(this.swigCPtr, this);
        return AccountConfig_sipConfig_get == 0 ? null : new AccountSipConfig(AccountConfig_sipConfig_get, false);
    }

    public void setCallConfig(AccountCallConfig accountCallConfig) {
        pjsua2JNI.AccountConfig_callConfig_set(this.swigCPtr, this, AccountCallConfig.getCPtr(accountCallConfig), accountCallConfig);
    }

    public AccountCallConfig getCallConfig() {
        long AccountConfig_callConfig_get = pjsua2JNI.AccountConfig_callConfig_get(this.swigCPtr, this);
        return AccountConfig_callConfig_get == 0 ? null : new AccountCallConfig(AccountConfig_callConfig_get, false);
    }

    public void setPresConfig(AccountPresConfig accountPresConfig) {
        pjsua2JNI.AccountConfig_presConfig_set(this.swigCPtr, this, AccountPresConfig.getCPtr(accountPresConfig), accountPresConfig);
    }

    public AccountPresConfig getPresConfig() {
        long AccountConfig_presConfig_get = pjsua2JNI.AccountConfig_presConfig_get(this.swigCPtr, this);
        return AccountConfig_presConfig_get == 0 ? null : new AccountPresConfig(AccountConfig_presConfig_get, false);
    }

    public void setMwiConfig(AccountMwiConfig accountMwiConfig) {
        pjsua2JNI.AccountConfig_mwiConfig_set(this.swigCPtr, this, AccountMwiConfig.getCPtr(accountMwiConfig), accountMwiConfig);
    }

    public AccountMwiConfig getMwiConfig() {
        long AccountConfig_mwiConfig_get = pjsua2JNI.AccountConfig_mwiConfig_get(this.swigCPtr, this);
        return AccountConfig_mwiConfig_get == 0 ? null : new AccountMwiConfig(AccountConfig_mwiConfig_get, false);
    }

    public void setNatConfig(AccountNatConfig accountNatConfig) {
        pjsua2JNI.AccountConfig_natConfig_set(this.swigCPtr, this, AccountNatConfig.getCPtr(accountNatConfig), accountNatConfig);
    }

    public AccountNatConfig getNatConfig() {
        long AccountConfig_natConfig_get = pjsua2JNI.AccountConfig_natConfig_get(this.swigCPtr, this);
        return AccountConfig_natConfig_get == 0 ? null : new AccountNatConfig(AccountConfig_natConfig_get, false);
    }

    public void setMediaConfig(AccountMediaConfig accountMediaConfig) {
        pjsua2JNI.AccountConfig_mediaConfig_set(this.swigCPtr, this, AccountMediaConfig.getCPtr(accountMediaConfig), accountMediaConfig);
    }

    public AccountMediaConfig getMediaConfig() {
        long AccountConfig_mediaConfig_get = pjsua2JNI.AccountConfig_mediaConfig_get(this.swigCPtr, this);
        return AccountConfig_mediaConfig_get == 0 ? null : new AccountMediaConfig(AccountConfig_mediaConfig_get, false);
    }

    public void setVideoConfig(AccountVideoConfig accountVideoConfig) {
        pjsua2JNI.AccountConfig_videoConfig_set(this.swigCPtr, this, AccountVideoConfig.getCPtr(accountVideoConfig), accountVideoConfig);
    }

    public AccountVideoConfig getVideoConfig() {
        long AccountConfig_videoConfig_get = pjsua2JNI.AccountConfig_videoConfig_get(this.swigCPtr, this);
        return AccountConfig_videoConfig_get == 0 ? null : new AccountVideoConfig(AccountConfig_videoConfig_get, false);
    }

    public AccountConfig() {
        this(pjsua2JNI.new_AccountConfig(), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
