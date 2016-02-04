package org.pjsip.pjsua2;

public class AccountCallConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountCallConfig(long j, boolean z) {
        super(pjsua2JNI.AccountCallConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountCallConfig accountCallConfig) {
        return accountCallConfig == null ? 0 : accountCallConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountCallConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setHoldType(pjsua_call_hold_type org_pjsip_pjsua2_pjsua_call_hold_type) {
        pjsua2JNI.AccountCallConfig_holdType_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_call_hold_type.swigValue());
    }

    public pjsua_call_hold_type getHoldType() {
        return pjsua_call_hold_type.swigToEnum(pjsua2JNI.AccountCallConfig_holdType_get(this.swigCPtr, this));
    }

    public void setPrackUse(pjsua_100rel_use org_pjsip_pjsua2_pjsua_100rel_use) {
        pjsua2JNI.AccountCallConfig_prackUse_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_100rel_use.swigValue());
    }

    public pjsua_100rel_use getPrackUse() {
        return pjsua_100rel_use.swigToEnum(pjsua2JNI.AccountCallConfig_prackUse_get(this.swigCPtr, this));
    }

    public void setTimerUse(pjsua_sip_timer_use org_pjsip_pjsua2_pjsua_sip_timer_use) {
        pjsua2JNI.AccountCallConfig_timerUse_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_sip_timer_use.swigValue());
    }

    public pjsua_sip_timer_use getTimerUse() {
        return pjsua_sip_timer_use.swigToEnum(pjsua2JNI.AccountCallConfig_timerUse_get(this.swigCPtr, this));
    }

    public void setTimerMinSESec(long j) {
        pjsua2JNI.AccountCallConfig_timerMinSESec_set(this.swigCPtr, this, j);
    }

    public long getTimerMinSESec() {
        return pjsua2JNI.AccountCallConfig_timerMinSESec_get(this.swigCPtr, this);
    }

    public void setTimerSessExpiresSec(long j) {
        pjsua2JNI.AccountCallConfig_timerSessExpiresSec_set(this.swigCPtr, this, j);
    }

    public long getTimerSessExpiresSec() {
        return pjsua2JNI.AccountCallConfig_timerSessExpiresSec_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountCallConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountCallConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountCallConfig() {
        this(pjsua2JNI.new_AccountCallConfig(), true);
    }
}
