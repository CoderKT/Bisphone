package org.pjsip.pjsua2;

public class AccountMediaConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountMediaConfig(long j, boolean z) {
        super(pjsua2JNI.AccountMediaConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountMediaConfig accountMediaConfig) {
        return accountMediaConfig == null ? 0 : accountMediaConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountMediaConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setTransportConfig(TransportConfig transportConfig) {
        pjsua2JNI.AccountMediaConfig_transportConfig_set(this.swigCPtr, this, TransportConfig.getCPtr(transportConfig), transportConfig);
    }

    public TransportConfig getTransportConfig() {
        long AccountMediaConfig_transportConfig_get = pjsua2JNI.AccountMediaConfig_transportConfig_get(this.swigCPtr, this);
        return AccountMediaConfig_transportConfig_get == 0 ? null : new TransportConfig(AccountMediaConfig_transportConfig_get, false);
    }

    public void setLockCodecEnabled(boolean z) {
        pjsua2JNI.AccountMediaConfig_lockCodecEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getLockCodecEnabled() {
        return pjsua2JNI.AccountMediaConfig_lockCodecEnabled_get(this.swigCPtr, this);
    }

    public void setStreamKaEnabled(boolean z) {
        pjsua2JNI.AccountMediaConfig_streamKaEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getStreamKaEnabled() {
        return pjsua2JNI.AccountMediaConfig_streamKaEnabled_get(this.swigCPtr, this);
    }

    public void setSrtpUse(pjmedia_srtp_use org_pjsip_pjsua2_pjmedia_srtp_use) {
        pjsua2JNI.AccountMediaConfig_srtpUse_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_srtp_use.swigValue());
    }

    public pjmedia_srtp_use getSrtpUse() {
        return pjmedia_srtp_use.swigToEnum(pjsua2JNI.AccountMediaConfig_srtpUse_get(this.swigCPtr, this));
    }

    public void setSrtpSecureSignaling(int i) {
        pjsua2JNI.AccountMediaConfig_srtpSecureSignaling_set(this.swigCPtr, this, i);
    }

    public int getSrtpSecureSignaling() {
        return pjsua2JNI.AccountMediaConfig_srtpSecureSignaling_get(this.swigCPtr, this);
    }

    public void setIpv6Use(pjsua_ipv6_use org_pjsip_pjsua2_pjsua_ipv6_use) {
        pjsua2JNI.AccountMediaConfig_ipv6Use_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_ipv6_use.swigValue());
    }

    public pjsua_ipv6_use getIpv6Use() {
        return pjsua_ipv6_use.swigToEnum(pjsua2JNI.AccountMediaConfig_ipv6Use_get(this.swigCPtr, this));
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountMediaConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountMediaConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountMediaConfig() {
        this(pjsua2JNI.new_AccountMediaConfig(), true);
    }
}
