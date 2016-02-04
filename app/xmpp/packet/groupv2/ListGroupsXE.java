package app.xmpp.packet.groupv2;

import se.emilsjolander.stickylistheaders.C1128R;

public class ListGroupsXE extends AbstractXESimple {
    GetGroupType f5169a;

    public enum GetGroupType {
        invited,
        joined;

        public static GetGroupType m7655a(String str) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -1154529463:
                    if (str.equals("joined")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1960030843:
                    if (str.equals("invited")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    return invited;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return joined;
                default:
                    throw new EnumConstantNotPresentException(GetGroupType.class, str);
            }
        }
    }

    public ListGroupsXE(GetGroupType getGroupType) {
        this.f5169a = getGroupType;
    }

    public String getElementName() {
        return "get-groups";
    }

    public GetGroupType m7656a() {
        return this.f5169a;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("get-groups").append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' >").append("<query").append(" ").append("s").append("='").append(this.f5169a).append("'/>");
        stringBuilder.append("</").append("get-groups").append(">");
        return stringBuilder;
    }
}
