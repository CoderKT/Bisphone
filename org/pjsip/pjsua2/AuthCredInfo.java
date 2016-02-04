package org.pjsip.pjsua2;

public class AuthCredInfo extends PersistentObject {
    private long swigCPtr;

    protected AuthCredInfo(long j, boolean z) {
        super(pjsua2JNI.AuthCredInfo_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AuthCredInfo authCredInfo) {
        return authCredInfo == null ? 0 : authCredInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setScheme(String str) {
        pjsua2JNI.AuthCredInfo_scheme_set(this.swigCPtr, this, str);
    }

    public String getScheme() {
        return pjsua2JNI.AuthCredInfo_scheme_get(this.swigCPtr, this);
    }

    public void setRealm(String str) {
        pjsua2JNI.AuthCredInfo_realm_set(this.swigCPtr, this, str);
    }

    public String getRealm() {
        return pjsua2JNI.AuthCredInfo_realm_get(this.swigCPtr, this);
    }

    public void setUsername(String str) {
        pjsua2JNI.AuthCredInfo_username_set(this.swigCPtr, this, str);
    }

    public String getUsername() {
        return pjsua2JNI.AuthCredInfo_username_get(this.swigCPtr, this);
    }

    public void setDataType(int i) {
        pjsua2JNI.AuthCredInfo_dataType_set(this.swigCPtr, this, i);
    }

    public int getDataType() {
        return pjsua2JNI.AuthCredInfo_dataType_get(this.swigCPtr, this);
    }

    public void setData(String str) {
        pjsua2JNI.AuthCredInfo_data_set(this.swigCPtr, this, str);
    }

    public String getData() {
        return pjsua2JNI.AuthCredInfo_data_get(this.swigCPtr, this);
    }

    public void setAkaK(String str) {
        pjsua2JNI.AuthCredInfo_akaK_set(this.swigCPtr, this, str);
    }

    public String getAkaK() {
        return pjsua2JNI.AuthCredInfo_akaK_get(this.swigCPtr, this);
    }

    public void setAkaOp(String str) {
        pjsua2JNI.AuthCredInfo_akaOp_set(this.swigCPtr, this, str);
    }

    public String getAkaOp() {
        return pjsua2JNI.AuthCredInfo_akaOp_get(this.swigCPtr, this);
    }

    public void setAkaAmf(String str) {
        pjsua2JNI.AuthCredInfo_akaAmf_set(this.swigCPtr, this, str);
    }

    public String getAkaAmf() {
        return pjsua2JNI.AuthCredInfo_akaAmf_get(this.swigCPtr, this);
    }

    public AuthCredInfo() {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_0(), true);
    }

    public AuthCredInfo(String str, String str2, String str3, int i, String str4) {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_1(str, str2, str3, i, str4), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.AuthCredInfo_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.AuthCredInfo_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
