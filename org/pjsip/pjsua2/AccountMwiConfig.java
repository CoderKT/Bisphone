package org.pjsip.pjsua2;

public class AccountMwiConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountMwiConfig(long j, boolean z) {
        super(pjsua2JNI.AccountMwiConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountMwiConfig accountMwiConfig) {
        return accountMwiConfig == null ? 0 : accountMwiConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountMwiConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setEnabled(boolean z) {
        pjsua2JNI.AccountMwiConfig_enabled_set(this.swigCPtr, this, z);
    }

    public boolean getEnabled() {
        return pjsua2JNI.AccountMwiConfig_enabled_get(this.swigCPtr, this);
    }

    public void setExpirationSec(long j) {
        pjsua2JNI.AccountMwiConfig_expirationSec_set(this.swigCPtr, this, j);
    }

    public long getExpirationSec() {
        return pjsua2JNI.AccountMwiConfig_expirationSec_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountMwiConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountMwiConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountMwiConfig() {
        this(pjsua2JNI.new_AccountMwiConfig(), true);
    }
}
