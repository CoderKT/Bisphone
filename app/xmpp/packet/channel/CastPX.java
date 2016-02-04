package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.ExtensionElement;

public class CastPX implements ExtensionElement {
    private Type f5065a;

    public enum Type {
        unicast,
        broadcast;

        public static Type m7539a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return broadcast;
            }
        }
    }

    public CastPX(Type type) {
        this.f5065a = type;
    }

    public Type m7540a() {
        return this.f5065a;
    }

    public String getElementName() {
        return "cast";
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/cast";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("cast").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/cast").append("' ").append("type").append("='").append(this.f5065a).append("'/>");
        return stringBuilder;
    }
}
