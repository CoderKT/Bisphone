package org.pjsip.pjsua2;

public class TlsConfig extends PersistentObject {
    private long swigCPtr;

    protected TlsConfig(long j, boolean z) {
        super(pjsua2JNI.TlsConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(TlsConfig tlsConfig) {
        return tlsConfig == null ? 0 : tlsConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TlsConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setCaListFile(String str) {
        pjsua2JNI.TlsConfig_CaListFile_set(this.swigCPtr, this, str);
    }

    public String getCaListFile() {
        return pjsua2JNI.TlsConfig_CaListFile_get(this.swigCPtr, this);
    }

    public void setCertFile(String str) {
        pjsua2JNI.TlsConfig_certFile_set(this.swigCPtr, this, str);
    }

    public String getCertFile() {
        return pjsua2JNI.TlsConfig_certFile_get(this.swigCPtr, this);
    }

    public void setPrivKeyFile(String str) {
        pjsua2JNI.TlsConfig_privKeyFile_set(this.swigCPtr, this, str);
    }

    public String getPrivKeyFile() {
        return pjsua2JNI.TlsConfig_privKeyFile_get(this.swigCPtr, this);
    }

    public void setPassword(String str) {
        pjsua2JNI.TlsConfig_password_set(this.swigCPtr, this, str);
    }

    public String getPassword() {
        return pjsua2JNI.TlsConfig_password_get(this.swigCPtr, this);
    }

    public void setMethod(pjsip_ssl_method org_pjsip_pjsua2_pjsip_ssl_method) {
        pjsua2JNI.TlsConfig_method_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_ssl_method.swigValue());
    }

    public pjsip_ssl_method getMethod() {
        return pjsip_ssl_method.swigToEnum(pjsua2JNI.TlsConfig_method_get(this.swigCPtr, this));
    }

    public void setCiphers(IntVector intVector) {
        pjsua2JNI.TlsConfig_ciphers_set(this.swigCPtr, this, IntVector.getCPtr(intVector), intVector);
    }

    public IntVector getCiphers() {
        long TlsConfig_ciphers_get = pjsua2JNI.TlsConfig_ciphers_get(this.swigCPtr, this);
        return TlsConfig_ciphers_get == 0 ? null : new IntVector(TlsConfig_ciphers_get, false);
    }

    public void setVerifyServer(boolean z) {
        pjsua2JNI.TlsConfig_verifyServer_set(this.swigCPtr, this, z);
    }

    public boolean getVerifyServer() {
        return pjsua2JNI.TlsConfig_verifyServer_get(this.swigCPtr, this);
    }

    public void setVerifyClient(boolean z) {
        pjsua2JNI.TlsConfig_verifyClient_set(this.swigCPtr, this, z);
    }

    public boolean getVerifyClient() {
        return pjsua2JNI.TlsConfig_verifyClient_get(this.swigCPtr, this);
    }

    public void setRequireClientCert(boolean z) {
        pjsua2JNI.TlsConfig_requireClientCert_set(this.swigCPtr, this, z);
    }

    public boolean getRequireClientCert() {
        return pjsua2JNI.TlsConfig_requireClientCert_get(this.swigCPtr, this);
    }

    public void setMsecTimeout(long j) {
        pjsua2JNI.TlsConfig_msecTimeout_set(this.swigCPtr, this, j);
    }

    public long getMsecTimeout() {
        return pjsua2JNI.TlsConfig_msecTimeout_get(this.swigCPtr, this);
    }

    public void setQosType(pj_qos_type org_pjsip_pjsua2_pj_qos_type) {
        pjsua2JNI.TlsConfig_qosType_set(this.swigCPtr, this, org_pjsip_pjsua2_pj_qos_type.swigValue());
    }

    public pj_qos_type getQosType() {
        return pj_qos_type.swigToEnum(pjsua2JNI.TlsConfig_qosType_get(this.swigCPtr, this));
    }

    public void setQosParams(pj_qos_params org_pjsip_pjsua2_pj_qos_params) {
        pjsua2JNI.TlsConfig_qosParams_set(this.swigCPtr, this, pj_qos_params.getCPtr(org_pjsip_pjsua2_pj_qos_params), org_pjsip_pjsua2_pj_qos_params);
    }

    public pj_qos_params getQosParams() {
        long TlsConfig_qosParams_get = pjsua2JNI.TlsConfig_qosParams_get(this.swigCPtr, this);
        return TlsConfig_qosParams_get == 0 ? null : new pj_qos_params(TlsConfig_qosParams_get, false);
    }

    public void setQosIgnoreError(boolean z) {
        pjsua2JNI.TlsConfig_qosIgnoreError_set(this.swigCPtr, this, z);
    }

    public boolean getQosIgnoreError() {
        return pjsua2JNI.TlsConfig_qosIgnoreError_get(this.swigCPtr, this);
    }

    public TlsConfig() {
        this(pjsua2JNI.new_TlsConfig(), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.TlsConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.TlsConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
