package org.pjsip.pjsua2;

public class BuddyConfig extends PersistentObject {
    private long swigCPtr;

    protected BuddyConfig(long j, boolean z) {
        super(pjsua2JNI.BuddyConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(BuddyConfig buddyConfig) {
        return buddyConfig == null ? 0 : buddyConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setUri(String str) {
        pjsua2JNI.BuddyConfig_uri_set(this.swigCPtr, this, str);
    }

    public String getUri() {
        return pjsua2JNI.BuddyConfig_uri_get(this.swigCPtr, this);
    }

    public void setSubscribe(boolean z) {
        pjsua2JNI.BuddyConfig_subscribe_set(this.swigCPtr, this, z);
    }

    public boolean getSubscribe() {
        return pjsua2JNI.BuddyConfig_subscribe_get(this.swigCPtr, this);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.BuddyConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.BuddyConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public BuddyConfig() {
        this(pjsua2JNI.new_BuddyConfig(), true);
    }
}
