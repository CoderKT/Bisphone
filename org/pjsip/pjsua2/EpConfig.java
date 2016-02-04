package org.pjsip.pjsua2;

public class EpConfig extends PersistentObject {
    private long swigCPtr;

    protected EpConfig(long j, boolean z) {
        super(pjsua2JNI.EpConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(EpConfig epConfig) {
        return epConfig == null ? 0 : epConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_EpConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setUaConfig(UaConfig uaConfig) {
        pjsua2JNI.EpConfig_uaConfig_set(this.swigCPtr, this, UaConfig.getCPtr(uaConfig), uaConfig);
    }

    public UaConfig getUaConfig() {
        long EpConfig_uaConfig_get = pjsua2JNI.EpConfig_uaConfig_get(this.swigCPtr, this);
        return EpConfig_uaConfig_get == 0 ? null : new UaConfig(EpConfig_uaConfig_get, false);
    }

    public void setLogConfig(LogConfig logConfig) {
        pjsua2JNI.EpConfig_logConfig_set(this.swigCPtr, this, LogConfig.getCPtr(logConfig), logConfig);
    }

    public LogConfig getLogConfig() {
        long EpConfig_logConfig_get = pjsua2JNI.EpConfig_logConfig_get(this.swigCPtr, this);
        return EpConfig_logConfig_get == 0 ? null : new LogConfig(EpConfig_logConfig_get, false);
    }

    public void setMedConfig(MediaConfig mediaConfig) {
        pjsua2JNI.EpConfig_medConfig_set(this.swigCPtr, this, MediaConfig.getCPtr(mediaConfig), mediaConfig);
    }

    public MediaConfig getMedConfig() {
        long EpConfig_medConfig_get = pjsua2JNI.EpConfig_medConfig_get(this.swigCPtr, this);
        return EpConfig_medConfig_get == 0 ? null : new MediaConfig(EpConfig_medConfig_get, false);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.EpConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.EpConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public EpConfig() {
        this(pjsua2JNI.new_EpConfig(), true);
    }
}
