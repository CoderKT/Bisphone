package org.pjsip.pjsua2;

public class PersistentDocument {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected PersistentDocument(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PersistentDocument persistentDocument) {
        return persistentDocument == null ? 0 : persistentDocument.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PersistentDocument(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void loadFile(String str) {
        pjsua2JNI.PersistentDocument_loadFile(this.swigCPtr, this, str);
    }

    public void loadString(String str) {
        pjsua2JNI.PersistentDocument_loadString(this.swigCPtr, this, str);
    }

    public void saveFile(String str) {
        pjsua2JNI.PersistentDocument_saveFile(this.swigCPtr, this, str);
    }

    public String saveString() {
        return pjsua2JNI.PersistentDocument_saveString(this.swigCPtr, this);
    }

    public ContainerNode getRootContainer() {
        return new ContainerNode(pjsua2JNI.PersistentDocument_getRootContainer(this.swigCPtr, this), false);
    }

    public boolean hasUnread() {
        return pjsua2JNI.PersistentDocument_hasUnread(this.swigCPtr, this);
    }

    public String unreadName() {
        return pjsua2JNI.PersistentDocument_unreadName(this.swigCPtr, this);
    }

    public int readInt(String str) {
        return pjsua2JNI.PersistentDocument_readInt__SWIG_0(this.swigCPtr, this, str);
    }

    public int readInt() {
        return pjsua2JNI.PersistentDocument_readInt__SWIG_1(this.swigCPtr, this);
    }

    public float readNumber(String str) {
        return pjsua2JNI.PersistentDocument_readNumber__SWIG_0(this.swigCPtr, this, str);
    }

    public float readNumber() {
        return pjsua2JNI.PersistentDocument_readNumber__SWIG_1(this.swigCPtr, this);
    }

    public boolean readBool(String str) {
        return pjsua2JNI.PersistentDocument_readBool__SWIG_0(this.swigCPtr, this, str);
    }

    public boolean readBool() {
        return pjsua2JNI.PersistentDocument_readBool__SWIG_1(this.swigCPtr, this);
    }

    public String readString(String str) {
        return pjsua2JNI.PersistentDocument_readString__SWIG_0(this.swigCPtr, this, str);
    }

    public String readString() {
        return pjsua2JNI.PersistentDocument_readString__SWIG_1(this.swigCPtr, this);
    }

    public StringVector readStringVector(String str) {
        return new StringVector(pjsua2JNI.PersistentDocument_readStringVector__SWIG_0(this.swigCPtr, this, str), true);
    }

    public StringVector readStringVector() {
        return new StringVector(pjsua2JNI.PersistentDocument_readStringVector__SWIG_1(this.swigCPtr, this), true);
    }

    public void readObject(PersistentObject persistentObject) {
        pjsua2JNI.PersistentDocument_readObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public ContainerNode readContainer(String str) {
        return new ContainerNode(pjsua2JNI.PersistentDocument_readContainer__SWIG_0(this.swigCPtr, this, str), true);
    }

    public ContainerNode readContainer() {
        return new ContainerNode(pjsua2JNI.PersistentDocument_readContainer__SWIG_1(this.swigCPtr, this), true);
    }

    public ContainerNode readArray(String str) {
        return new ContainerNode(pjsua2JNI.PersistentDocument_readArray__SWIG_0(this.swigCPtr, this, str), true);
    }

    public ContainerNode readArray() {
        return new ContainerNode(pjsua2JNI.PersistentDocument_readArray__SWIG_1(this.swigCPtr, this), true);
    }

    public void writeNumber(String str, float f) {
        pjsua2JNI.PersistentDocument_writeNumber(this.swigCPtr, this, str, f);
    }

    public void writeInt(String str, int i) {
        pjsua2JNI.PersistentDocument_writeInt(this.swigCPtr, this, str, i);
    }

    public void writeBool(String str, boolean z) {
        pjsua2JNI.PersistentDocument_writeBool(this.swigCPtr, this, str, z);
    }

    public void writeString(String str, String str2) {
        pjsua2JNI.PersistentDocument_writeString(this.swigCPtr, this, str, str2);
    }

    public void writeStringVector(String str, StringVector stringVector) {
        pjsua2JNI.PersistentDocument_writeStringVector(this.swigCPtr, this, str, StringVector.getCPtr(stringVector), stringVector);
    }

    public void writeObject(PersistentObject persistentObject) {
        pjsua2JNI.PersistentDocument_writeObject(this.swigCPtr, this, PersistentObject.getCPtr(persistentObject), persistentObject);
    }

    public ContainerNode writeNewContainer(String str) {
        return new ContainerNode(pjsua2JNI.PersistentDocument_writeNewContainer(this.swigCPtr, this, str), true);
    }

    public ContainerNode writeNewArray(String str) {
        return new ContainerNode(pjsua2JNI.PersistentDocument_writeNewArray(this.swigCPtr, this, str), true);
    }
}
