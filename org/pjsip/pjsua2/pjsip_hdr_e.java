package org.pjsip.pjsua2;

public final class pjsip_hdr_e {
    public static final pjsip_hdr_e PJSIP_H_ACCEPT;
    public static final pjsip_hdr_e PJSIP_H_ACCEPT_ENCODING_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_ACCEPT_LANGUAGE_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_ALERT_INFO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_ALLOW;
    public static final pjsip_hdr_e PJSIP_H_AUTHENTICATION_INFO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_AUTHORIZATION;
    public static final pjsip_hdr_e PJSIP_H_CALL_ID;
    public static final pjsip_hdr_e PJSIP_H_CALL_INFO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_CONTACT;
    public static final pjsip_hdr_e PJSIP_H_CONTENT_DISPOSITION_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_CONTENT_ENCODING_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_CONTENT_LANGUAGE_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_CONTENT_LENGTH;
    public static final pjsip_hdr_e PJSIP_H_CONTENT_TYPE;
    public static final pjsip_hdr_e PJSIP_H_CSEQ;
    public static final pjsip_hdr_e PJSIP_H_DATE_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_ERROR_INFO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_EXPIRES;
    public static final pjsip_hdr_e PJSIP_H_FROM;
    public static final pjsip_hdr_e PJSIP_H_IN_REPLY_TO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_MAX_FORWARDS;
    public static final pjsip_hdr_e PJSIP_H_MIME_VERSION_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_MIN_EXPIRES;
    public static final pjsip_hdr_e PJSIP_H_ORGANIZATION_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_OTHER;
    public static final pjsip_hdr_e PJSIP_H_PRIORITY_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_PROXY_AUTHENTICATE;
    public static final pjsip_hdr_e PJSIP_H_PROXY_AUTHORIZATION;
    public static final pjsip_hdr_e PJSIP_H_PROXY_REQUIRE_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_RECORD_ROUTE;
    public static final pjsip_hdr_e PJSIP_H_REPLY_TO_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_REQUIRE;
    public static final pjsip_hdr_e PJSIP_H_RETRY_AFTER;
    public static final pjsip_hdr_e PJSIP_H_ROUTE;
    public static final pjsip_hdr_e PJSIP_H_SERVER_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_SUBJECT_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_SUPPORTED;
    public static final pjsip_hdr_e PJSIP_H_TIMESTAMP_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_TO;
    public static final pjsip_hdr_e PJSIP_H_UNSUPPORTED;
    public static final pjsip_hdr_e PJSIP_H_USER_AGENT_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_VIA;
    public static final pjsip_hdr_e PJSIP_H_WARNING_UNIMP;
    public static final pjsip_hdr_e PJSIP_H_WWW_AUTHENTICATE;
    private static int swigNext;
    private static pjsip_hdr_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_H_ACCEPT = new pjsip_hdr_e("PJSIP_H_ACCEPT");
        PJSIP_H_ACCEPT_ENCODING_UNIMP = new pjsip_hdr_e("PJSIP_H_ACCEPT_ENCODING_UNIMP");
        PJSIP_H_ACCEPT_LANGUAGE_UNIMP = new pjsip_hdr_e("PJSIP_H_ACCEPT_LANGUAGE_UNIMP");
        PJSIP_H_ALERT_INFO_UNIMP = new pjsip_hdr_e("PJSIP_H_ALERT_INFO_UNIMP");
        PJSIP_H_ALLOW = new pjsip_hdr_e("PJSIP_H_ALLOW");
        PJSIP_H_AUTHENTICATION_INFO_UNIMP = new pjsip_hdr_e("PJSIP_H_AUTHENTICATION_INFO_UNIMP");
        PJSIP_H_AUTHORIZATION = new pjsip_hdr_e("PJSIP_H_AUTHORIZATION");
        PJSIP_H_CALL_ID = new pjsip_hdr_e("PJSIP_H_CALL_ID");
        PJSIP_H_CALL_INFO_UNIMP = new pjsip_hdr_e("PJSIP_H_CALL_INFO_UNIMP");
        PJSIP_H_CONTACT = new pjsip_hdr_e("PJSIP_H_CONTACT");
        PJSIP_H_CONTENT_DISPOSITION_UNIMP = new pjsip_hdr_e("PJSIP_H_CONTENT_DISPOSITION_UNIMP");
        PJSIP_H_CONTENT_ENCODING_UNIMP = new pjsip_hdr_e("PJSIP_H_CONTENT_ENCODING_UNIMP");
        PJSIP_H_CONTENT_LANGUAGE_UNIMP = new pjsip_hdr_e("PJSIP_H_CONTENT_LANGUAGE_UNIMP");
        PJSIP_H_CONTENT_LENGTH = new pjsip_hdr_e("PJSIP_H_CONTENT_LENGTH");
        PJSIP_H_CONTENT_TYPE = new pjsip_hdr_e("PJSIP_H_CONTENT_TYPE");
        PJSIP_H_CSEQ = new pjsip_hdr_e("PJSIP_H_CSEQ");
        PJSIP_H_DATE_UNIMP = new pjsip_hdr_e("PJSIP_H_DATE_UNIMP");
        PJSIP_H_ERROR_INFO_UNIMP = new pjsip_hdr_e("PJSIP_H_ERROR_INFO_UNIMP");
        PJSIP_H_EXPIRES = new pjsip_hdr_e("PJSIP_H_EXPIRES");
        PJSIP_H_FROM = new pjsip_hdr_e("PJSIP_H_FROM");
        PJSIP_H_IN_REPLY_TO_UNIMP = new pjsip_hdr_e("PJSIP_H_IN_REPLY_TO_UNIMP");
        PJSIP_H_MAX_FORWARDS = new pjsip_hdr_e("PJSIP_H_MAX_FORWARDS");
        PJSIP_H_MIME_VERSION_UNIMP = new pjsip_hdr_e("PJSIP_H_MIME_VERSION_UNIMP");
        PJSIP_H_MIN_EXPIRES = new pjsip_hdr_e("PJSIP_H_MIN_EXPIRES");
        PJSIP_H_ORGANIZATION_UNIMP = new pjsip_hdr_e("PJSIP_H_ORGANIZATION_UNIMP");
        PJSIP_H_PRIORITY_UNIMP = new pjsip_hdr_e("PJSIP_H_PRIORITY_UNIMP");
        PJSIP_H_PROXY_AUTHENTICATE = new pjsip_hdr_e("PJSIP_H_PROXY_AUTHENTICATE");
        PJSIP_H_PROXY_AUTHORIZATION = new pjsip_hdr_e("PJSIP_H_PROXY_AUTHORIZATION");
        PJSIP_H_PROXY_REQUIRE_UNIMP = new pjsip_hdr_e("PJSIP_H_PROXY_REQUIRE_UNIMP");
        PJSIP_H_RECORD_ROUTE = new pjsip_hdr_e("PJSIP_H_RECORD_ROUTE");
        PJSIP_H_REPLY_TO_UNIMP = new pjsip_hdr_e("PJSIP_H_REPLY_TO_UNIMP");
        PJSIP_H_REQUIRE = new pjsip_hdr_e("PJSIP_H_REQUIRE");
        PJSIP_H_RETRY_AFTER = new pjsip_hdr_e("PJSIP_H_RETRY_AFTER");
        PJSIP_H_ROUTE = new pjsip_hdr_e("PJSIP_H_ROUTE");
        PJSIP_H_SERVER_UNIMP = new pjsip_hdr_e("PJSIP_H_SERVER_UNIMP");
        PJSIP_H_SUBJECT_UNIMP = new pjsip_hdr_e("PJSIP_H_SUBJECT_UNIMP");
        PJSIP_H_SUPPORTED = new pjsip_hdr_e("PJSIP_H_SUPPORTED");
        PJSIP_H_TIMESTAMP_UNIMP = new pjsip_hdr_e("PJSIP_H_TIMESTAMP_UNIMP");
        PJSIP_H_TO = new pjsip_hdr_e("PJSIP_H_TO");
        PJSIP_H_UNSUPPORTED = new pjsip_hdr_e("PJSIP_H_UNSUPPORTED");
        PJSIP_H_USER_AGENT_UNIMP = new pjsip_hdr_e("PJSIP_H_USER_AGENT_UNIMP");
        PJSIP_H_VIA = new pjsip_hdr_e("PJSIP_H_VIA");
        PJSIP_H_WARNING_UNIMP = new pjsip_hdr_e("PJSIP_H_WARNING_UNIMP");
        PJSIP_H_WWW_AUTHENTICATE = new pjsip_hdr_e("PJSIP_H_WWW_AUTHENTICATE");
        PJSIP_H_OTHER = new pjsip_hdr_e("PJSIP_H_OTHER");
        swigValues = new pjsip_hdr_e[]{PJSIP_H_ACCEPT, PJSIP_H_ACCEPT_ENCODING_UNIMP, PJSIP_H_ACCEPT_LANGUAGE_UNIMP, PJSIP_H_ALERT_INFO_UNIMP, PJSIP_H_ALLOW, PJSIP_H_AUTHENTICATION_INFO_UNIMP, PJSIP_H_AUTHORIZATION, PJSIP_H_CALL_ID, PJSIP_H_CALL_INFO_UNIMP, PJSIP_H_CONTACT, PJSIP_H_CONTENT_DISPOSITION_UNIMP, PJSIP_H_CONTENT_ENCODING_UNIMP, PJSIP_H_CONTENT_LANGUAGE_UNIMP, PJSIP_H_CONTENT_LENGTH, PJSIP_H_CONTENT_TYPE, PJSIP_H_CSEQ, PJSIP_H_DATE_UNIMP, PJSIP_H_ERROR_INFO_UNIMP, PJSIP_H_EXPIRES, PJSIP_H_FROM, PJSIP_H_IN_REPLY_TO_UNIMP, PJSIP_H_MAX_FORWARDS, PJSIP_H_MIME_VERSION_UNIMP, PJSIP_H_MIN_EXPIRES, PJSIP_H_ORGANIZATION_UNIMP, PJSIP_H_PRIORITY_UNIMP, PJSIP_H_PROXY_AUTHENTICATE, PJSIP_H_PROXY_AUTHORIZATION, PJSIP_H_PROXY_REQUIRE_UNIMP, PJSIP_H_RECORD_ROUTE, PJSIP_H_REPLY_TO_UNIMP, PJSIP_H_REQUIRE, PJSIP_H_RETRY_AFTER, PJSIP_H_ROUTE, PJSIP_H_SERVER_UNIMP, PJSIP_H_SUBJECT_UNIMP, PJSIP_H_SUPPORTED, PJSIP_H_TIMESTAMP_UNIMP, PJSIP_H_TO, PJSIP_H_UNSUPPORTED, PJSIP_H_USER_AGENT_UNIMP, PJSIP_H_VIA, PJSIP_H_WARNING_UNIMP, PJSIP_H_WWW_AUTHENTICATE, PJSIP_H_OTHER};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_hdr_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_hdr_e.class + " with value " + i);
    }

    private pjsip_hdr_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_hdr_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_hdr_e(String str, pjsip_hdr_e org_pjsip_pjsua2_pjsip_hdr_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_hdr_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
