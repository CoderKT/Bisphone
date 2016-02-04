package app.xmpp.packet.reply;

import app.xmpp.packet.common.CastXE.Type;

public class ReplyAudioXE extends AbstractReplyXE {
    public ReplyAudioXE(String str, Type type, String str2) {
        super(str, type, null, str2);
    }

    public String getElementName() {
        return "reply-audio";
    }

    public CharSequence toXML() {
        return super.toXML();
    }
}
