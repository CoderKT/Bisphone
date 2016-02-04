package org.pjsip.pjsua2;

public class JsonDocument extends PersistentDocument {
    private long swigCPtr;

    protected JsonDocument(long j, boolean z) {
        super(pjsua2JNI.JsonDocument_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(JsonDocument jsonDocument) {
        return jsonDocument == null ? 0 : jsonDocument.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_JsonDocument(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public JsonDocument() {
        this(pjsua2JNI.new_JsonDocument(), true);
    }

    public void loadFile(String str) {
        pjsua2JNI.JsonDocument_loadFile(this.swigCPtr, this, str);
    }

    public void loadString(String str) {
        pjsua2JNI.JsonDocument_loadString(this.swigCPtr, this, str);
    }

    public void saveFile(String str) {
        pjsua2JNI.JsonDocument_saveFile(this.swigCPtr, this, str);
    }

    public String saveString() {
        return pjsua2JNI.JsonDocument_saveString(this.swigCPtr, this);
    }

    public ContainerNode getRootContainer() {
        return new ContainerNode(pjsua2JNI.JsonDocument_getRootContainer(this.swigCPtr, this), false);
    }
}
