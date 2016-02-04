package app.xmpp.packet.common;

import app.C0110R;
import org.jivesoftware.smack.packet.ExtensionElement;
import se.emilsjolander.stickylistheaders.C1128R;

public class CastXE implements ExtensionElement {
    private Type f5090a;

    /* renamed from: app.xmpp.packet.common.CastXE.1 */
    /* synthetic */ class C05791 {
        static final /* synthetic */ int[] f5086a;

        static {
            f5086a = new int[Type.values().length];
            try {
                f5086a[Type.broadcast.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5086a[Type.unicast.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum Type {
        broadcast,
        unicast;

        public String toString() {
            switch (C05791.f5086a[ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return "b";
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return "u";
                default:
                    return null;
            }
        }

        public static Type m7570a(String str) {
            String toLowerCase = str.toLowerCase();
            Object obj = -1;
            switch (toLowerCase.hashCode()) {
                case -1618876223:
                    if (toLowerCase.equals("broadcast")) {
                        obj = 1;
                        break;
                    }
                    break;
                case -287029201:
                    if (toLowerCase.equals("unicast")) {
                        obj = 3;
                        break;
                    }
                    break;
                case C0110R.styleable.Theme_buttonBarNeutralButtonStyle /*98*/:
                    if (toLowerCase.equals("b")) {
                        obj = null;
                        break;
                    }
                    break;
                case 117:
                    if (toLowerCase.equals("u")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return broadcast;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return unicast;
                default:
                    throw new EnumConstantNotPresentException(Type.class, str);
            }
        }
    }

    public CastXE(Type type) {
        this.f5090a = type;
    }

    public String getNamespace() {
        return "bpn:common:v1";
    }

    public String getElementName() {
        return "cast";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("cast").append(" ").append("xmlns").append("='").append("bpn:common:v1").append("' ").append("v").append("='").append(this.f5090a).append("'/>");
        return stringBuilder;
    }
}
