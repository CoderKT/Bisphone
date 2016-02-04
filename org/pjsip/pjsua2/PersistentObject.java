package org.pjsip.pjsua2;

public class PersistentObject {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected PersistentObject(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PersistentObject persistentObject) {
        return persistentObject == null ? 0 : persistentObject.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PersistentObject(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.PersistentObject_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.PersistentObject_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
