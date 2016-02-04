package org.pjsip.pjsua2;

public final class pjsip_status_code {
    public static final pjsip_status_code PJSIP_AC_AMBIGUOUS;
    public static final pjsip_status_code PJSIP_SC_ACCEPTED;
    public static final pjsip_status_code PJSIP_SC_ADDRESS_INCOMPLETE;
    public static final pjsip_status_code PJSIP_SC_ALTERNATIVE_SERVICE;
    public static final pjsip_status_code PJSIP_SC_BAD_EVENT;
    public static final pjsip_status_code PJSIP_SC_BAD_EXTENSION;
    public static final pjsip_status_code PJSIP_SC_BAD_GATEWAY;
    public static final pjsip_status_code PJSIP_SC_BAD_REQUEST;
    public static final pjsip_status_code PJSIP_SC_BUSY_EVERYWHERE;
    public static final pjsip_status_code PJSIP_SC_BUSY_HERE;
    public static final pjsip_status_code PJSIP_SC_CALL_BEING_FORWARDED;
    public static final pjsip_status_code PJSIP_SC_CALL_TSX_DOES_NOT_EXIST;
    public static final pjsip_status_code PJSIP_SC_DECLINE;
    public static final pjsip_status_code PJSIP_SC_DOES_NOT_EXIST_ANYWHERE;
    public static final pjsip_status_code PJSIP_SC_EXTENSION_REQUIRED;
    public static final pjsip_status_code PJSIP_SC_FORBIDDEN;
    public static final pjsip_status_code PJSIP_SC_GONE;
    public static final pjsip_status_code PJSIP_SC_INTERNAL_SERVER_ERROR;
    public static final pjsip_status_code PJSIP_SC_INTERVAL_TOO_BRIEF;
    public static final pjsip_status_code PJSIP_SC_LOOP_DETECTED;
    public static final pjsip_status_code PJSIP_SC_MESSAGE_TOO_LARGE;
    public static final pjsip_status_code PJSIP_SC_METHOD_NOT_ALLOWED;
    public static final pjsip_status_code PJSIP_SC_MOVED_PERMANENTLY;
    public static final pjsip_status_code PJSIP_SC_MOVED_TEMPORARILY;
    public static final pjsip_status_code PJSIP_SC_MULTIPLE_CHOICES;
    public static final pjsip_status_code PJSIP_SC_NOT_ACCEPTABLE;
    public static final pjsip_status_code PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE;
    public static final pjsip_status_code PJSIP_SC_NOT_ACCEPTABLE_HERE;
    public static final pjsip_status_code PJSIP_SC_NOT_FOUND;
    public static final pjsip_status_code PJSIP_SC_NOT_IMPLEMENTED;
    public static final pjsip_status_code PJSIP_SC_OK;
    public static final pjsip_status_code PJSIP_SC_PAYMENT_REQUIRED;
    public static final pjsip_status_code PJSIP_SC_PRECONDITION_FAILURE;
    public static final pjsip_status_code PJSIP_SC_PROGRESS;
    public static final pjsip_status_code PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED;
    public static final pjsip_status_code PJSIP_SC_QUEUED;
    public static final pjsip_status_code PJSIP_SC_REQUEST_ENTITY_TOO_LARGE;
    public static final pjsip_status_code PJSIP_SC_REQUEST_PENDING;
    public static final pjsip_status_code PJSIP_SC_REQUEST_TERMINATED;
    public static final pjsip_status_code PJSIP_SC_REQUEST_TIMEOUT;
    public static final pjsip_status_code PJSIP_SC_REQUEST_UPDATED;
    public static final pjsip_status_code PJSIP_SC_REQUEST_URI_TOO_LONG;
    public static final pjsip_status_code PJSIP_SC_RINGING;
    public static final pjsip_status_code PJSIP_SC_SERVER_TIMEOUT;
    public static final pjsip_status_code PJSIP_SC_SERVICE_UNAVAILABLE;
    public static final pjsip_status_code PJSIP_SC_SESSION_TIMER_TOO_SMALL;
    public static final pjsip_status_code PJSIP_SC_TEMPORARILY_UNAVAILABLE;
    public static final pjsip_status_code PJSIP_SC_TOO_MANY_HOPS;
    public static final pjsip_status_code PJSIP_SC_TRYING;
    public static final pjsip_status_code PJSIP_SC_TSX_TIMEOUT;
    public static final pjsip_status_code PJSIP_SC_TSX_TRANSPORT_ERROR;
    public static final pjsip_status_code PJSIP_SC_UNAUTHORIZED;
    public static final pjsip_status_code PJSIP_SC_UNDECIPHERABLE;
    public static final pjsip_status_code PJSIP_SC_UNSUPPORTED_MEDIA_TYPE;
    public static final pjsip_status_code PJSIP_SC_UNSUPPORTED_URI_SCHEME;
    public static final pjsip_status_code PJSIP_SC_USE_PROXY;
    public static final pjsip_status_code PJSIP_SC_VERSION_NOT_SUPPORTED;
    public static final pjsip_status_code PJSIP_SC__force_32bit;
    private static int swigNext;
    private static pjsip_status_code[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_SC_TRYING = new pjsip_status_code("PJSIP_SC_TRYING", pjsua2JNI.PJSIP_SC_TRYING_get());
        PJSIP_SC_RINGING = new pjsip_status_code("PJSIP_SC_RINGING", pjsua2JNI.PJSIP_SC_RINGING_get());
        PJSIP_SC_CALL_BEING_FORWARDED = new pjsip_status_code("PJSIP_SC_CALL_BEING_FORWARDED", pjsua2JNI.PJSIP_SC_CALL_BEING_FORWARDED_get());
        PJSIP_SC_QUEUED = new pjsip_status_code("PJSIP_SC_QUEUED", pjsua2JNI.PJSIP_SC_QUEUED_get());
        PJSIP_SC_PROGRESS = new pjsip_status_code("PJSIP_SC_PROGRESS", pjsua2JNI.PJSIP_SC_PROGRESS_get());
        PJSIP_SC_OK = new pjsip_status_code("PJSIP_SC_OK", pjsua2JNI.PJSIP_SC_OK_get());
        PJSIP_SC_ACCEPTED = new pjsip_status_code("PJSIP_SC_ACCEPTED", pjsua2JNI.PJSIP_SC_ACCEPTED_get());
        PJSIP_SC_MULTIPLE_CHOICES = new pjsip_status_code("PJSIP_SC_MULTIPLE_CHOICES", pjsua2JNI.PJSIP_SC_MULTIPLE_CHOICES_get());
        PJSIP_SC_MOVED_PERMANENTLY = new pjsip_status_code("PJSIP_SC_MOVED_PERMANENTLY", pjsua2JNI.PJSIP_SC_MOVED_PERMANENTLY_get());
        PJSIP_SC_MOVED_TEMPORARILY = new pjsip_status_code("PJSIP_SC_MOVED_TEMPORARILY", pjsua2JNI.PJSIP_SC_MOVED_TEMPORARILY_get());
        PJSIP_SC_USE_PROXY = new pjsip_status_code("PJSIP_SC_USE_PROXY", pjsua2JNI.PJSIP_SC_USE_PROXY_get());
        PJSIP_SC_ALTERNATIVE_SERVICE = new pjsip_status_code("PJSIP_SC_ALTERNATIVE_SERVICE", pjsua2JNI.PJSIP_SC_ALTERNATIVE_SERVICE_get());
        PJSIP_SC_BAD_REQUEST = new pjsip_status_code("PJSIP_SC_BAD_REQUEST", pjsua2JNI.PJSIP_SC_BAD_REQUEST_get());
        PJSIP_SC_UNAUTHORIZED = new pjsip_status_code("PJSIP_SC_UNAUTHORIZED", pjsua2JNI.PJSIP_SC_UNAUTHORIZED_get());
        PJSIP_SC_PAYMENT_REQUIRED = new pjsip_status_code("PJSIP_SC_PAYMENT_REQUIRED", pjsua2JNI.PJSIP_SC_PAYMENT_REQUIRED_get());
        PJSIP_SC_FORBIDDEN = new pjsip_status_code("PJSIP_SC_FORBIDDEN", pjsua2JNI.PJSIP_SC_FORBIDDEN_get());
        PJSIP_SC_NOT_FOUND = new pjsip_status_code("PJSIP_SC_NOT_FOUND", pjsua2JNI.PJSIP_SC_NOT_FOUND_get());
        PJSIP_SC_METHOD_NOT_ALLOWED = new pjsip_status_code("PJSIP_SC_METHOD_NOT_ALLOWED", pjsua2JNI.PJSIP_SC_METHOD_NOT_ALLOWED_get());
        PJSIP_SC_NOT_ACCEPTABLE = new pjsip_status_code("PJSIP_SC_NOT_ACCEPTABLE", pjsua2JNI.PJSIP_SC_NOT_ACCEPTABLE_get());
        PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED = new pjsip_status_code("PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED", pjsua2JNI.PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED_get());
        PJSIP_SC_REQUEST_TIMEOUT = new pjsip_status_code("PJSIP_SC_REQUEST_TIMEOUT", pjsua2JNI.PJSIP_SC_REQUEST_TIMEOUT_get());
        PJSIP_SC_GONE = new pjsip_status_code("PJSIP_SC_GONE", pjsua2JNI.PJSIP_SC_GONE_get());
        PJSIP_SC_REQUEST_ENTITY_TOO_LARGE = new pjsip_status_code("PJSIP_SC_REQUEST_ENTITY_TOO_LARGE", pjsua2JNI.PJSIP_SC_REQUEST_ENTITY_TOO_LARGE_get());
        PJSIP_SC_REQUEST_URI_TOO_LONG = new pjsip_status_code("PJSIP_SC_REQUEST_URI_TOO_LONG", pjsua2JNI.PJSIP_SC_REQUEST_URI_TOO_LONG_get());
        PJSIP_SC_UNSUPPORTED_MEDIA_TYPE = new pjsip_status_code("PJSIP_SC_UNSUPPORTED_MEDIA_TYPE", pjsua2JNI.PJSIP_SC_UNSUPPORTED_MEDIA_TYPE_get());
        PJSIP_SC_UNSUPPORTED_URI_SCHEME = new pjsip_status_code("PJSIP_SC_UNSUPPORTED_URI_SCHEME", pjsua2JNI.PJSIP_SC_UNSUPPORTED_URI_SCHEME_get());
        PJSIP_SC_BAD_EXTENSION = new pjsip_status_code("PJSIP_SC_BAD_EXTENSION", pjsua2JNI.PJSIP_SC_BAD_EXTENSION_get());
        PJSIP_SC_EXTENSION_REQUIRED = new pjsip_status_code("PJSIP_SC_EXTENSION_REQUIRED", pjsua2JNI.PJSIP_SC_EXTENSION_REQUIRED_get());
        PJSIP_SC_SESSION_TIMER_TOO_SMALL = new pjsip_status_code("PJSIP_SC_SESSION_TIMER_TOO_SMALL", pjsua2JNI.PJSIP_SC_SESSION_TIMER_TOO_SMALL_get());
        PJSIP_SC_INTERVAL_TOO_BRIEF = new pjsip_status_code("PJSIP_SC_INTERVAL_TOO_BRIEF", pjsua2JNI.PJSIP_SC_INTERVAL_TOO_BRIEF_get());
        PJSIP_SC_TEMPORARILY_UNAVAILABLE = new pjsip_status_code("PJSIP_SC_TEMPORARILY_UNAVAILABLE", pjsua2JNI.PJSIP_SC_TEMPORARILY_UNAVAILABLE_get());
        PJSIP_SC_CALL_TSX_DOES_NOT_EXIST = new pjsip_status_code("PJSIP_SC_CALL_TSX_DOES_NOT_EXIST", pjsua2JNI.PJSIP_SC_CALL_TSX_DOES_NOT_EXIST_get());
        PJSIP_SC_LOOP_DETECTED = new pjsip_status_code("PJSIP_SC_LOOP_DETECTED", pjsua2JNI.PJSIP_SC_LOOP_DETECTED_get());
        PJSIP_SC_TOO_MANY_HOPS = new pjsip_status_code("PJSIP_SC_TOO_MANY_HOPS", pjsua2JNI.PJSIP_SC_TOO_MANY_HOPS_get());
        PJSIP_SC_ADDRESS_INCOMPLETE = new pjsip_status_code("PJSIP_SC_ADDRESS_INCOMPLETE", pjsua2JNI.PJSIP_SC_ADDRESS_INCOMPLETE_get());
        PJSIP_AC_AMBIGUOUS = new pjsip_status_code("PJSIP_AC_AMBIGUOUS", pjsua2JNI.PJSIP_AC_AMBIGUOUS_get());
        PJSIP_SC_BUSY_HERE = new pjsip_status_code("PJSIP_SC_BUSY_HERE", pjsua2JNI.PJSIP_SC_BUSY_HERE_get());
        PJSIP_SC_REQUEST_TERMINATED = new pjsip_status_code("PJSIP_SC_REQUEST_TERMINATED", pjsua2JNI.PJSIP_SC_REQUEST_TERMINATED_get());
        PJSIP_SC_NOT_ACCEPTABLE_HERE = new pjsip_status_code("PJSIP_SC_NOT_ACCEPTABLE_HERE", pjsua2JNI.PJSIP_SC_NOT_ACCEPTABLE_HERE_get());
        PJSIP_SC_BAD_EVENT = new pjsip_status_code("PJSIP_SC_BAD_EVENT", pjsua2JNI.PJSIP_SC_BAD_EVENT_get());
        PJSIP_SC_REQUEST_UPDATED = new pjsip_status_code("PJSIP_SC_REQUEST_UPDATED", pjsua2JNI.PJSIP_SC_REQUEST_UPDATED_get());
        PJSIP_SC_REQUEST_PENDING = new pjsip_status_code("PJSIP_SC_REQUEST_PENDING", pjsua2JNI.PJSIP_SC_REQUEST_PENDING_get());
        PJSIP_SC_UNDECIPHERABLE = new pjsip_status_code("PJSIP_SC_UNDECIPHERABLE", pjsua2JNI.PJSIP_SC_UNDECIPHERABLE_get());
        PJSIP_SC_INTERNAL_SERVER_ERROR = new pjsip_status_code("PJSIP_SC_INTERNAL_SERVER_ERROR", pjsua2JNI.PJSIP_SC_INTERNAL_SERVER_ERROR_get());
        PJSIP_SC_NOT_IMPLEMENTED = new pjsip_status_code("PJSIP_SC_NOT_IMPLEMENTED", pjsua2JNI.PJSIP_SC_NOT_IMPLEMENTED_get());
        PJSIP_SC_BAD_GATEWAY = new pjsip_status_code("PJSIP_SC_BAD_GATEWAY", pjsua2JNI.PJSIP_SC_BAD_GATEWAY_get());
        PJSIP_SC_SERVICE_UNAVAILABLE = new pjsip_status_code("PJSIP_SC_SERVICE_UNAVAILABLE", pjsua2JNI.PJSIP_SC_SERVICE_UNAVAILABLE_get());
        PJSIP_SC_SERVER_TIMEOUT = new pjsip_status_code("PJSIP_SC_SERVER_TIMEOUT", pjsua2JNI.PJSIP_SC_SERVER_TIMEOUT_get());
        PJSIP_SC_VERSION_NOT_SUPPORTED = new pjsip_status_code("PJSIP_SC_VERSION_NOT_SUPPORTED", pjsua2JNI.PJSIP_SC_VERSION_NOT_SUPPORTED_get());
        PJSIP_SC_MESSAGE_TOO_LARGE = new pjsip_status_code("PJSIP_SC_MESSAGE_TOO_LARGE", pjsua2JNI.PJSIP_SC_MESSAGE_TOO_LARGE_get());
        PJSIP_SC_PRECONDITION_FAILURE = new pjsip_status_code("PJSIP_SC_PRECONDITION_FAILURE", pjsua2JNI.PJSIP_SC_PRECONDITION_FAILURE_get());
        PJSIP_SC_BUSY_EVERYWHERE = new pjsip_status_code("PJSIP_SC_BUSY_EVERYWHERE", pjsua2JNI.PJSIP_SC_BUSY_EVERYWHERE_get());
        PJSIP_SC_DECLINE = new pjsip_status_code("PJSIP_SC_DECLINE", pjsua2JNI.PJSIP_SC_DECLINE_get());
        PJSIP_SC_DOES_NOT_EXIST_ANYWHERE = new pjsip_status_code("PJSIP_SC_DOES_NOT_EXIST_ANYWHERE", pjsua2JNI.PJSIP_SC_DOES_NOT_EXIST_ANYWHERE_get());
        PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE = new pjsip_status_code("PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE", pjsua2JNI.PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE_get());
        PJSIP_SC_TSX_TIMEOUT = new pjsip_status_code("PJSIP_SC_TSX_TIMEOUT", pjsua2JNI.PJSIP_SC_TSX_TIMEOUT_get());
        PJSIP_SC_TSX_TRANSPORT_ERROR = new pjsip_status_code("PJSIP_SC_TSX_TRANSPORT_ERROR", pjsua2JNI.PJSIP_SC_TSX_TRANSPORT_ERROR_get());
        PJSIP_SC__force_32bit = new pjsip_status_code("PJSIP_SC__force_32bit", pjsua2JNI.PJSIP_SC__force_32bit_get());
        swigValues = new pjsip_status_code[]{PJSIP_SC_TRYING, PJSIP_SC_RINGING, PJSIP_SC_CALL_BEING_FORWARDED, PJSIP_SC_QUEUED, PJSIP_SC_PROGRESS, PJSIP_SC_OK, PJSIP_SC_ACCEPTED, PJSIP_SC_MULTIPLE_CHOICES, PJSIP_SC_MOVED_PERMANENTLY, PJSIP_SC_MOVED_TEMPORARILY, PJSIP_SC_USE_PROXY, PJSIP_SC_ALTERNATIVE_SERVICE, PJSIP_SC_BAD_REQUEST, PJSIP_SC_UNAUTHORIZED, PJSIP_SC_PAYMENT_REQUIRED, PJSIP_SC_FORBIDDEN, PJSIP_SC_NOT_FOUND, PJSIP_SC_METHOD_NOT_ALLOWED, PJSIP_SC_NOT_ACCEPTABLE, PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED, PJSIP_SC_REQUEST_TIMEOUT, PJSIP_SC_GONE, PJSIP_SC_REQUEST_ENTITY_TOO_LARGE, PJSIP_SC_REQUEST_URI_TOO_LONG, PJSIP_SC_UNSUPPORTED_MEDIA_TYPE, PJSIP_SC_UNSUPPORTED_URI_SCHEME, PJSIP_SC_BAD_EXTENSION, PJSIP_SC_EXTENSION_REQUIRED, PJSIP_SC_SESSION_TIMER_TOO_SMALL, PJSIP_SC_INTERVAL_TOO_BRIEF, PJSIP_SC_TEMPORARILY_UNAVAILABLE, PJSIP_SC_CALL_TSX_DOES_NOT_EXIST, PJSIP_SC_LOOP_DETECTED, PJSIP_SC_TOO_MANY_HOPS, PJSIP_SC_ADDRESS_INCOMPLETE, PJSIP_AC_AMBIGUOUS, PJSIP_SC_BUSY_HERE, PJSIP_SC_REQUEST_TERMINATED, PJSIP_SC_NOT_ACCEPTABLE_HERE, PJSIP_SC_BAD_EVENT, PJSIP_SC_REQUEST_UPDATED, PJSIP_SC_REQUEST_PENDING, PJSIP_SC_UNDECIPHERABLE, PJSIP_SC_INTERNAL_SERVER_ERROR, PJSIP_SC_NOT_IMPLEMENTED, PJSIP_SC_BAD_GATEWAY, PJSIP_SC_SERVICE_UNAVAILABLE, PJSIP_SC_SERVER_TIMEOUT, PJSIP_SC_VERSION_NOT_SUPPORTED, PJSIP_SC_MESSAGE_TOO_LARGE, PJSIP_SC_PRECONDITION_FAILURE, PJSIP_SC_BUSY_EVERYWHERE, PJSIP_SC_DECLINE, PJSIP_SC_DOES_NOT_EXIST_ANYWHERE, PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE, PJSIP_SC_TSX_TIMEOUT, PJSIP_SC_TSX_TRANSPORT_ERROR, PJSIP_SC__force_32bit};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_status_code swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_status_code.class + " with value " + i);
    }

    private pjsip_status_code(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_status_code(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_status_code(String str, pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_status_code.swigValue;
        swigNext = this.swigValue + 1;
    }
}
