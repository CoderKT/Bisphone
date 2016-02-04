package app.xmpp.packet.reply;

public class ReplyStickerXE extends AbstractReplyXE {
    public ReplyStickerXE(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public String getElementName() {
        return "reply-sticker";
    }

    public CharSequence toXML() {
        return super.toXML();
    }
}
