package org.pjsip.pjsua2;

public class SipMultipartPart {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipMultipartPart(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipMultipartPart sipMultipartPart) {
        return sipMultipartPart == null ? 0 : sipMultipartPart.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMultipartPart(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.SipMultipartPart_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public SipHeaderVector getHeaders() {
        long SipMultipartPart_headers_get = pjsua2JNI.SipMultipartPart_headers_get(this.swigCPtr, this);
        return SipMultipartPart_headers_get == 0 ? null : new SipHeaderVector(SipMultipartPart_headers_get, false);
    }

    public void setContentType(SipMediaType sipMediaType) {
        pjsua2JNI.SipMultipartPart_contentType_set(this.swigCPtr, this, SipMediaType.getCPtr(sipMediaType), sipMediaType);
    }

    public SipMediaType getContentType() {
        long SipMultipartPart_contentType_get = pjsua2JNI.SipMultipartPart_contentType_get(this.swigCPtr, this);
        return SipMultipartPart_contentType_get == 0 ? null : new SipMediaType(SipMultipartPart_contentType_get, false);
    }

    public void setBody(String str) {
        pjsua2JNI.SipMultipartPart_body_set(this.swigCPtr, this, str);
    }

    public String getBody() {
        return pjsua2JNI.SipMultipartPart_body_get(this.swigCPtr, this);
    }

    public SipMultipartPart() {
        this(pjsua2JNI.new_SipMultipartPart(), true);
    }
}
