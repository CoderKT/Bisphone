package app.xmpp.packet.groupv2;

import org.jivesoftware.smackx.muc.packet.Destroy;

public class DestroyGroupsXE extends AbstractXESimple {
    public String getElementName() {
        return Destroy.ELEMENT;
    }
}
