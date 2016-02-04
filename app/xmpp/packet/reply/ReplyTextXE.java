package app.xmpp.packet.reply;

import app.xmpp.packet.common.CastXE.Type;

public class ReplyTextXE extends AbstractReplyXE {
    public ReplyTextXE(String str, String str2, Type type, String str3) {
        super(str, type, str2, str3);
    }

    public String getElementName() {
        return "reply-text";
    }

    public CharSequence toXML() {
        return super.toXML();
    }
}
