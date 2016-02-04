package org.jivesoftware.smack.parsing;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionLoggingCallback extends ParsingExceptionCallback {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ExceptionLoggingCallback.class.getName());
    }

    public void handleUnparsablePacket(UnparsablePacket unparsablePacket) {
        LOGGER.log(Level.SEVERE, "Smack message parsing exception: ", unparsablePacket.getParsingException());
        LOGGER.severe("Unparsed content: " + unparsablePacket.getContent());
    }
}
