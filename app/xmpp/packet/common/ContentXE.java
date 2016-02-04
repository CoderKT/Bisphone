package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.ExtensionElement;
import se.emilsjolander.stickylistheaders.C1128R;

public class ContentXE implements ExtensionElement {
    private Type f5097a;

    /* renamed from: app.xmpp.packet.common.ContentXE.1 */
    /* synthetic */ class C05801 {
        static final /* synthetic */ int[] f5091a;

        static {
            f5091a = new int[Type.values().length];
            try {
                f5091a[Type.command.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5091a[Type.result.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5091a[Type.conversation.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5091a[Type.event.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum Type {
        command,
        result,
        conversation,
        event;

        public String toString() {
            switch (C05801.f5091a[ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return "cmd";
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return "rsl";
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return "cnv";
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    return "evt";
                default:
                    return null;
            }
        }

        public static Type m7572a(String str) {
            String toLowerCase = str.toLowerCase();
            Object obj = -1;
            switch (toLowerCase.hashCode()) {
                case 98618:
                    if (toLowerCase.equals("cmd")) {
                        obj = null;
                        break;
                    }
                    break;
                case 98667:
                    if (toLowerCase.equals("cnv")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 100835:
                    if (toLowerCase.equals("evt")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 113227:
                    if (toLowerCase.equals("rsl")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    return command;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return result;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return conversation;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return event;
                default:
                    throw new EnumConstantNotPresentException(Type.class, str);
            }
        }
    }

    public ContentXE(Type type) {
        this.f5097a = type;
    }

    public Type m7573a() {
        return this.f5097a;
    }

    public String getNamespace() {
        return "bpn:common:v1";
    }

    public String getElementName() {
        return "content";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("content").append(" ").append("xmlns").append("='").append("bpn:common:v1").append("' ").append("t").append("='").append(this.f5097a).append("'/>");
        return stringBuilder;
    }
}
