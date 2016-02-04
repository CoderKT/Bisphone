package app.xmpp.packet.reply;

import app.xmpp.packet.common.CastXE.Type;
import app.xmpp.packet.groupv2.AbstractXE;
import org.jivesoftware.smackx.caps.packet.CapsExtension;

public abstract class AbstractReplyXE extends AbstractXE {
    protected String f5223a;
    protected Type f5224b;
    protected String f5225c;
    protected String f5226d;

    public AbstractReplyXE(String str, Type type, String str2, String str3) {
        this.f5224b = null;
        this.f5225c = null;
        this.f5226d = null;
        this.f5223a = str;
        this.f5224b = type;
        this.f5225c = str2;
        this.f5226d = str3;
    }

    public AbstractReplyXE(String str, String str2, String str3) {
        this.f5224b = null;
        this.f5225c = null;
        this.f5226d = null;
        this.f5223a = str;
        this.f5225c = str2;
        this.f5226d = str3;
    }

    public String m7707a() {
        return this.f5223a;
    }

    public String m7708b() {
        return this.f5225c;
    }

    public String getNamespace() {
        return "bpn:common:v1:reply";
    }

    public String m7709c() {
        return this.f5226d;
    }

    public CharSequence toXML() {
        Object stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:common:v1:reply").append("' ").append("id").append("='").append(this.f5223a).append("' ");
        if (this.f5224b != null) {
            stringBuilder.append(CapsExtension.ELEMENT).append("='").append(this.f5224b).append("' ");
        }
        if (this.f5226d != null) {
            stringBuilder.append("u").append("='").append(this.f5226d).append("' ");
        }
        if (this.f5225c == null) {
            stringBuilder.append("/>");
        } else {
            stringBuilder.append(">").append(this.f5225c).append("<").append("/").append(getElementName()).append(">");
        }
        return stringBuilder;
    }
}
