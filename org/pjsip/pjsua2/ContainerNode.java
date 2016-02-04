package org.pjsip.pjsua2;

public class ContainerNode {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ContainerNode(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ContainerNode containerNode) {
        return containerNode == null ? 0 : containerNode.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ContainerNode(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean hasUnread() {
        return pjsua2JNI.ContainerNode_hasUnread(this.swigCPtr, this);
    }

    public String unreadName() {
        return pjsua2JNI.ContainerNode_unreadName(this.swigCPtr, this);
    }

    public int readInt(String str) {
        return pjsua2JNI.ContainerNode_readInt__SWIG_0(this.swigCPtr, this, str);
    }

    public int readInt() {
        return pjsua2JNI.ContainerNode_readInt__SWIG_1(this.swigCPtr, this);
    }

    public float readNumber(String str) {
        return pjsua2JNI.ContainerNode_readNumber__SWIG_0(this.swigCPtr, this, str);
    }

    public float readNumber() {
        return pjsua2JNI.ContainerNode_readNumber__SWIG_1(this.swigCPtr, this);
    }

    public boolean readBool(String str) {
        return pjsua2JNI.ContainerNode_readBool__SWIG_0(this.swigCPtr, this, str);
    }

    public boolean readBool() {
        return pjsua2JNI.ContainerNode_readBool__SWIG_1(this.swigCPtr, this);
    }

    public String readString(String str) {
        return pjsua2JNI.ContainerNode_readString__SWIG_0(this.swigCPtr, this, str);
    }

    public String readString() {
        return pjsua2JNI.ContainerNode_readString__SWIG_1(this.swigCPtr, this);
    }

    public StringVector readStringVector(String str) {
        return new StringVector(pjsua2JNI.ContainerNode_readStringVector__SWIG_0(this.swigCPtr, this, str), true);
    }

    public StringVector readStringVector() {
        return new StringVector(pjsua2JNI.ContainerNode_readStringVector__SWIG_1(this.swigCPtr, this), true);
    }

    public void readObject(PersistentObject persistentObject) {
        pjsua2JNI.ContainerNode_readObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public ContainerNode readContainer(String str) {
        return new ContainerNode(pjsua2JNI.ContainerNode_readContainer__SWIG_0(this.swigCPtr, this, str), true);
    }

    public ContainerNode readContainer() {
        return new ContainerNode(pjsua2JNI.ContainerNode_readContainer__SWIG_1(this.swigCPtr, this), true);
    }

    public ContainerNode readArray(String str) {
        return new ContainerNode(pjsua2JNI.ContainerNode_readArray__SWIG_0(this.swigCPtr, this, str), true);
    }

    public ContainerNode readArray() {
        return new ContainerNode(pjsua2JNI.ContainerNode_readArray__SWIG_1(this.swigCPtr, this), true);
    }

    public void writeNumber(String str, float f) {
        pjsua2JNI.ContainerNode_writeNumber(this.swigCPtr, this, str, f);
    }

    public void writeInt(String str, int i) {
        pjsua2JNI.ContainerNode_writeInt(this.swigCPtr, this, str, i);
    }

    public void writeBool(String str, boolean z) {
        pjsua2JNI.ContainerNode_writeBool(this.swigCPtr, this, str, z);
    }

    public void writeString(String str, String str2) {
        pjsua2JNI.ContainerNode_writeString(this.swigCPtr, this, str, str2);
    }

    public void writeStringVector(String str, StringVector stringVector) {
        pjsua2JNI.ContainerNode_writeStringVector(this.swigCPtr, this, str, StringVector.getCPtr(stringVector), stringVector);
    }

    public void writeObject(PersistentObject persistentObject) {
        pjsua2JNI.ContainerNode_writeObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public ContainerNode writeNewContainer(String str) {
        return new ContainerNode(pjsua2JNI.ContainerNode_writeNewContainer(this.swigCPtr, this, str), true);
    }

    public ContainerNode writeNewArray(String str) {
        return new ContainerNode(pjsua2JNI.ContainerNode_writeNewArray(this.swigCPtr, this, str), true);
    }

    public ContainerNode() {
        this(pjsua2JNI.new_ContainerNode(), true);
    }
}
