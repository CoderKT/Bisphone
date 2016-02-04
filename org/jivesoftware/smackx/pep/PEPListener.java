package org.jivesoftware.smackx.pep;

import org.jivesoftware.smackx.pep.packet.PEPEvent;

public interface PEPListener {
    void eventReceived(String str, PEPEvent pEPEvent);
}
