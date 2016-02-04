package org.pjsip.pjsua2;

public class AccountNatConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountNatConfig(long j, boolean z) {
        super(pjsua2JNI.AccountNatConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountNatConfig accountNatConfig) {
        return accountNatConfig == null ? 0 : accountNatConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountNatConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setSipStunUse(pjsua_stun_use org_pjsip_pjsua2_pjsua_stun_use) {
        pjsua2JNI.AccountNatConfig_sipStunUse_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_stun_use.swigValue());
    }

    public pjsua_stun_use getSipStunUse() {
        return pjsua_stun_use.swigToEnum(pjsua2JNI.AccountNatConfig_sipStunUse_get(this.swigCPtr, this));
    }

    public void setMediaStunUse(pjsua_stun_use org_pjsip_pjsua2_pjsua_stun_use) {
        pjsua2JNI.AccountNatConfig_mediaStunUse_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_stun_use.swigValue());
    }

    public pjsua_stun_use getMediaStunUse() {
        return pjsua_stun_use.swigToEnum(pjsua2JNI.AccountNatConfig_mediaStunUse_get(this.swigCPtr, this));
    }

    public void setIceEnabled(boolean z) {
        pjsua2JNI.AccountNatConfig_iceEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getIceEnabled() {
        return pjsua2JNI.AccountNatConfig_iceEnabled_get(this.swigCPtr, this);
    }

    public void setIceMaxHostCands(int i) {
        pjsua2JNI.AccountNatConfig_iceMaxHostCands_set(this.swigCPtr, this, i);
    }

    public int getIceMaxHostCands() {
        return pjsua2JNI.AccountNatConfig_iceMaxHostCands_get(this.swigCPtr, this);
    }

    public void setIceAggressiveNomination(boolean z) {
        pjsua2JNI.AccountNatConfig_iceAggressiveNomination_set(this.swigCPtr, this, z);
    }

    public boolean getIceAggressiveNomination() {
        return pjsua2JNI.AccountNatConfig_iceAggressiveNomination_get(this.swigCPtr, this);
    }

    public void setIceNominatedCheckDelayMsec(long j) {
        pjsua2JNI.AccountNatConfig_iceNominatedCheckDelayMsec_set(this.swigCPtr, this, j);
    }

    public long getIceNominatedCheckDelayMsec() {
        return pjsua2JNI.AccountNatConfig_iceNominatedCheckDelayMsec_get(this.swigCPtr, this);
    }

    public void setIceWaitNominationTimeoutMsec(int i) {
        pjsua2JNI.AccountNatConfig_iceWaitNominationTimeoutMsec_set(this.swigCPtr, this, i);
    }

    public int getIceWaitNominationTimeoutMsec() {
        return pjsua2JNI.AccountNatConfig_iceWaitNominationTimeoutMsec_get(this.swigCPtr, this);
    }

    public void setIceNoRtcp(boolean z) {
        pjsua2JNI.AccountNatConfig_iceNoRtcp_set(this.swigCPtr, this, z);
    }

    public boolean getIceNoRtcp() {
        return pjsua2JNI.AccountNatConfig_iceNoRtcp_get(this.swigCPtr, this);
    }

    public void setIceAlwaysUpdate(boolean z) {
        pjsua2JNI.AccountNatConfig_iceAlwaysUpdate_set(this.swigCPtr, this, z);
    }

    public boolean getIceAlwaysUpdate() {
        return pjsua2JNI.AccountNatConfig_iceAlwaysUpdate_get(this.swigCPtr, this);
    }

    public void setTurnEnabled(boolean z) {
        pjsua2JNI.AccountNatConfig_turnEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getTurnEnabled() {
        return pjsua2JNI.AccountNatConfig_turnEnabled_get(this.swigCPtr, this);
    }

    public void setTurnServer(String str) {
        pjsua2JNI.AccountNatConfig_turnServer_set(this.swigCPtr, this, str);
    }

    public String getTurnServer() {
        return pjsua2JNI.AccountNatConfig_turnServer_get(this.swigCPtr, this);
    }

    public void setTurnConnType(pj_turn_tp_type org_pjsip_pjsua2_pj_turn_tp_type) {
        pjsua2JNI.AccountNatConfig_turnConnType_set(this.swigCPtr, this, org_pjsip_pjsua2_pj_turn_tp_type.swigValue());
    }

    public pj_turn_tp_type getTurnConnType() {
        return pj_turn_tp_type.swigToEnum(pjsua2JNI.AccountNatConfig_turnConnType_get(this.swigCPtr, this));
    }

    public void setTurnUserName(String str) {
        pjsua2JNI.AccountNatConfig_turnUserName_set(this.swigCPtr, this, str);
    }

    public String getTurnUserName() {
        return pjsua2JNI.AccountNatConfig_turnUserName_get(this.swigCPtr, this);
    }

    public void setTurnPasswordType(int i) {
        pjsua2JNI.AccountNatConfig_turnPasswordType_set(this.swigCPtr, this, i);
    }

    public int getTurnPasswordType() {
        return pjsua2JNI.AccountNatConfig_turnPasswordType_get(this.swigCPtr, this);
    }

    public void setTurnPassword(String str) {
        pjsua2JNI.AccountNatConfig_turnPassword_set(this.swigCPtr, this, str);
    }

    public String getTurnPassword() {
        return pjsua2JNI.AccountNatConfig_turnPassword_get(this.swigCPtr, this);
    }

    public void setContactRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_contactRewriteUse_set(this.swigCPtr, this, i);
    }

    public int getContactRewriteUse() {
        return pjsua2JNI.AccountNatConfig_contactRewriteUse_get(this.swigCPtr, this);
    }

    public void setContactRewriteMethod(int i) {
        pjsua2JNI.AccountNatConfig_contactRewriteMethod_set(this.swigCPtr, this, i);
    }

    public int getContactRewriteMethod() {
        return pjsua2JNI.AccountNatConfig_contactRewriteMethod_get(this.swigCPtr, this);
    }

    public void setContactUseSrcPort(int i) {
        pjsua2JNI.AccountNatConfig_contactUseSrcPort_set(this.swigCPtr, this, i);
    }

    public int getContactUseSrcPort() {
        return pjsua2JNI.AccountNatConfig_contactUseSrcPort_get(this.swigCPtr, this);
    }

    public void setViaRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_viaRewriteUse_set(this.swigCPtr, this, i);
    }

    public int getViaRewriteUse() {
        return pjsua2JNI.AccountNatConfig_viaRewriteUse_get(this.swigCPtr, this);
    }

    public void setSdpNatRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_sdpNatRewriteUse_set(this.swigCPtr, this, i);
    }

    public int getSdpNatRewriteUse() {
        return pjsua2JNI.AccountNatConfig_sdpNatRewriteUse_get(this.swigCPtr, this);
    }

    public void setSipOutboundUse(int i) {
        pjsua2JNI.AccountNatConfig_sipOutboundUse_set(this.swigCPtr, this, i);
    }

    public int getSipOutboundUse() {
        return pjsua2JNI.AccountNatConfig_sipOutboundUse_get(this.swigCPtr, this);
    }

    public void setSipOutboundInstanceId(String str) {
        pjsua2JNI.AccountNatConfig_sipOutboundInstanceId_set(this.swigCPtr, this, str);
    }

    public String getSipOutboundInstanceId() {
        return pjsua2JNI.AccountNatConfig_sipOutboundInstanceId_get(this.swigCPtr, this);
    }

    public void setSipOutboundRegId(String str) {
        pjsua2JNI.AccountNatConfig_sipOutboundRegId_set(this.swigCPtr, this, str);
    }

    public String getSipOutboundRegId() {
        return pjsua2JNI.AccountNatConfig_sipOutboundRegId_get(this.swigCPtr, this);
    }

    public void setUdpKaIntervalSec(long j) {
        pjsua2JNI.AccountNatConfig_udpKaIntervalSec_set(this.swigCPtr, this, j);
    }

    public long getUdpKaIntervalSec() {
        return pjsua2JNI.AccountNatConfig_udpKaIntervalSec_get(this.swigCPtr, this);
    }

    public void setUdpKaData(String str) {
        pjsua2JNI.AccountNatConfig_udpKaData_set(this.swigCPtr, this, str);
    }

    public String getUdpKaData() {
        return pjsua2JNI.AccountNatConfig_udpKaData_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountNatConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountNatConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountNatConfig() {
        this(pjsua2JNI.new_AccountNatConfig(), true);
    }
}
