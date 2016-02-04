package org.pjsip.pjsua2;

public class AccountVideoConfig extends PersistentObject {
    private long swigCPtr;

    protected AccountVideoConfig(long j, boolean z) {
        super(pjsua2JNI.AccountVideoConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AccountVideoConfig accountVideoConfig) {
        return accountVideoConfig == null ? 0 : accountVideoConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountVideoConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setAutoShowIncoming(boolean z) {
        pjsua2JNI.AccountVideoConfig_autoShowIncoming_set(this.swigCPtr, this, z);
    }

    public boolean getAutoShowIncoming() {
        return pjsua2JNI.AccountVideoConfig_autoShowIncoming_get(this.swigCPtr, this);
    }

    public void setAutoTransmitOutgoing(boolean z) {
        pjsua2JNI.AccountVideoConfig_autoTransmitOutgoing_set(this.swigCPtr, this, z);
    }

    public boolean getAutoTransmitOutgoing() {
        return pjsua2JNI.AccountVideoConfig_autoTransmitOutgoing_get(this.swigCPtr, this);
    }

    public void setWindowFlags(long j) {
        pjsua2JNI.AccountVideoConfig_windowFlags_set(this.swigCPtr, this, j);
    }

    public long getWindowFlags() {
        return pjsua2JNI.AccountVideoConfig_windowFlags_get(this.swigCPtr, this);
    }

    public void setDefaultCaptureDevice(int i) {
        pjsua2JNI.AccountVideoConfig_defaultCaptureDevice_set(this.swigCPtr, this, i);
    }

    public int getDefaultCaptureDevice() {
        return pjsua2JNI.AccountVideoConfig_defaultCaptureDevice_get(this.swigCPtr, this);
    }

    public void setDefaultRenderDevice(int i) {
        pjsua2JNI.AccountVideoConfig_defaultRenderDevice_set(this.swigCPtr, this, i);
    }

    public int getDefaultRenderDevice() {
        return pjsua2JNI.AccountVideoConfig_defaultRenderDevice_get(this.swigCPtr, this);
    }

    public void setRateControlMethod(pjmedia_vid_stream_rc_method org_pjsip_pjsua2_pjmedia_vid_stream_rc_method) {
        pjsua2JNI.AccountVideoConfig_rateControlMethod_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_vid_stream_rc_method.swigValue());
    }

    public pjmedia_vid_stream_rc_method getRateControlMethod() {
        return pjmedia_vid_stream_rc_method.swigToEnum(pjsua2JNI.AccountVideoConfig_rateControlMethod_get(this.swigCPtr, this));
    }

    public void setRateControlBandwidth(long j) {
        pjsua2JNI.AccountVideoConfig_rateControlBandwidth_set(this.swigCPtr, this, j);
    }

    public long getRateControlBandwidth() {
        return pjsua2JNI.AccountVideoConfig_rateControlBandwidth_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AccountVideoConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AccountVideoConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountVideoConfig() {
        this(pjsua2JNI.new_AccountVideoConfig(), true);
    }
}
