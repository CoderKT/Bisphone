package org.pjsip.pjsua2;

public class UaConfig extends PersistentObject {
    private long swigCPtr;

    protected UaConfig(long j, boolean z) {
        super(pjsua2JNI.UaConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(UaConfig uaConfig) {
        return uaConfig == null ? 0 : uaConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_UaConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setMaxCalls(long j) {
        pjsua2JNI.UaConfig_maxCalls_set(this.swigCPtr, this, j);
    }

    public long getMaxCalls() {
        return pjsua2JNI.UaConfig_maxCalls_get(this.swigCPtr, this);
    }

    public void setThreadCnt(long j) {
        pjsua2JNI.UaConfig_threadCnt_set(this.swigCPtr, this, j);
    }

    public long getThreadCnt() {
        return pjsua2JNI.UaConfig_threadCnt_get(this.swigCPtr, this);
    }

    public void setMainThreadOnly(boolean z) {
        pjsua2JNI.UaConfig_mainThreadOnly_set(this.swigCPtr, this, z);
    }

    public boolean getMainThreadOnly() {
        return pjsua2JNI.UaConfig_mainThreadOnly_get(this.swigCPtr, this);
    }

    public void setNameserver(StringVector stringVector) {
        pjsua2JNI.UaConfig_nameserver_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public StringVector getNameserver() {
        long UaConfig_nameserver_get = pjsua2JNI.UaConfig_nameserver_get(this.swigCPtr, this);
        return UaConfig_nameserver_get == 0 ? null : new StringVector(UaConfig_nameserver_get, false);
    }

    public void setUserAgent(String str) {
        pjsua2JNI.UaConfig_userAgent_set(this.swigCPtr, this, str);
    }

    public String getUserAgent() {
        return pjsua2JNI.UaConfig_userAgent_get(this.swigCPtr, this);
    }

    public void setStunServer(StringVector stringVector) {
        pjsua2JNI.UaConfig_stunServer_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public StringVector getStunServer() {
        long UaConfig_stunServer_get = pjsua2JNI.UaConfig_stunServer_get(this.swigCPtr, this);
        return UaConfig_stunServer_get == 0 ? null : new StringVector(UaConfig_stunServer_get, false);
    }

    public void setStunIgnoreFailure(boolean z) {
        pjsua2JNI.UaConfig_stunIgnoreFailure_set(this.swigCPtr, this, z);
    }

    public boolean getStunIgnoreFailure() {
        return pjsua2JNI.UaConfig_stunIgnoreFailure_get(this.swigCPtr, this);
    }

    public void setNatTypeInSdp(int i) {
        pjsua2JNI.UaConfig_natTypeInSdp_set(this.swigCPtr, this, i);
    }

    public int getNatTypeInSdp() {
        return pjsua2JNI.UaConfig_natTypeInSdp_get(this.swigCPtr, this);
    }

    public void setMwiUnsolicitedEnabled(boolean z) {
        pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getMwiUnsolicitedEnabled() {
        return pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_get(this.swigCPtr, this);
    }

    public UaConfig() {
        this(pjsua2JNI.new_UaConfig(), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.UaConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.UaConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
