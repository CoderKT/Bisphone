package app.xmpp.packet.groupv2;

import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;

public class InviteXE extends AbstractXEWithMemberList {
    public String getElementName() {
        return Invite.ELEMENT;
    }
}
