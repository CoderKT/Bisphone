package org.jivesoftware.smackx.pubsub;

public class SubscribeExtension extends NodeExtension {
    protected String jid;

    public SubscribeExtension(String str) {
        super(PubSubElementType.SUBSCRIBE);
        this.jid = str;
    }

    public SubscribeExtension(String str, String str2) {
        super(PubSubElementType.SUBSCRIBE, str2);
        this.jid = str;
    }

    public String getJid() {
        return this.jid;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        if (getNode() != null) {
            stringBuilder.append(" node='");
            stringBuilder.append(getNode());
            stringBuilder.append("'");
        }
        stringBuilder.append(" jid='");
        stringBuilder.append(getJid());
        stringBuilder.append("'/>");
        return stringBuilder.toString();
    }
}
