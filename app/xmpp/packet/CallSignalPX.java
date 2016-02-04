package app.xmpp.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

public class CallSignalPX implements ExtensionElement {
    private Type f5047a;
    private String f5048b;

    public enum Type {
        call,
        answer,
        busy,
        unavailable;

        public static Type m7524a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return unavailable;
            }
        }
    }

    public CallSignalPX(Type type, String str) {
        this.f5047a = type;
        this.f5048b = str;
    }

    public Type m7525a() {
        return this.f5047a;
    }

    public String m7526b() {
        return this.f5048b;
    }

    public String getElementName() {
        return "call";
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/media";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("call").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("'>").append("<").append("order").append(">").append(this.f5047a).append("</").append("order").append(">").append("<").append("callid").append(">").append(this.f5048b).append("</").append("callid").append(">").append("</").append("call").append(">");
        return stringBuilder;
    }
}
