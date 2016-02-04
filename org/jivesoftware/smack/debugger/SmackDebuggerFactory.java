package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.XMPPConnection;

public interface SmackDebuggerFactory {
    SmackDebugger create(XMPPConnection xMPPConnection, Writer writer, Reader reader);
}
