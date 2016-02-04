package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;

public class JulDebugger extends AbstractDebugger {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(JulDebugger.class.getName());
    }

    public JulDebugger(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        super(xMPPConnection, writer, reader);
    }

    protected void log(String str) {
        LOGGER.fine(str);
    }
}
