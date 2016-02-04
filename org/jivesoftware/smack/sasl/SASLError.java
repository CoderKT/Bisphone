package org.jivesoftware.smack.sasl;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum SASLError {
    aborted,
    account_disabled,
    credentials_expired,
    encryption_required,
    incorrect_encoding,
    invalid_authzid,
    invalid_mechanism,
    malformed_request,
    mechanism_too_weak,
    not_authorized,
    temporary_auth_failure;
    
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(SASLError.class.getName());
    }

    public String toString() {
        return name().replace('_', '-');
    }

    public static SASLError fromString(String str) {
        String replace = str.replace('-', '_');
        SASLError sASLError = null;
        try {
            sASLError = valueOf(replace);
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, "Could not transform string '" + replace + "' to SASLError", e);
        }
        return sASLError;
    }
}
