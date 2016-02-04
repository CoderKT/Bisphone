package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

public interface InvitationListener {
    void invitationReceived(XMPPConnection xMPPConnection, MultiUserChat multiUserChat, String str, String str2, String str3, Message message);
}
