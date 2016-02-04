package app.xmpp.packet.reply;

public class ReplyMapXE extends AbstractReplyXE {
    public ReplyMapXE(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public String getElementName() {
        return "reply-map";
    }

    public CharSequence toXML() {
        return super.toXML();
    }
}
