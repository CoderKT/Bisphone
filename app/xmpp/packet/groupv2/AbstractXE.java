package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.packet.ExtensionElement;

public abstract class AbstractXE implements ExtensionElement {
    public String getNamespace() {
        return "bpn:group:v2";
    }
}
