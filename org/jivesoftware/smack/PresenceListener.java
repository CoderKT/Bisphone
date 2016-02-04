package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Presence;

public interface PresenceListener {
    void processPresence(Presence presence);
}
