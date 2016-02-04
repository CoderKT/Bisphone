package app.xmpp.packet.common;

import app.xmpp.packet.groupv2.AbstractXE;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import se.emilsjolander.stickylistheaders.C1128R;

public class ErrorXE extends AbstractXE {
    private ErrorType f5117a;

    /* renamed from: app.xmpp.packet.common.ErrorXE.1 */
    /* synthetic */ class C05811 {
        static final /* synthetic */ int[] f5098a;

        static {
            f5098a = new int[ErrorType.values().length];
            try {
                f5098a[ErrorType.Internal_Server_Error.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5098a[ErrorType.Bad_Request.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5098a[ErrorType.User_Not_Authorized.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5098a[ErrorType.User_Not_Exist.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f5098a[ErrorType.Reach_Max_Group.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f5098a[ErrorType.Group_Already_Exist.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f5098a[ErrorType.Group_Not_Exist.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f5098a[ErrorType.Message_Not_Exist.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f5098a[ErrorType.User_Already_Joined.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f5098a[ErrorType.Reach_Max_Member.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f5098a[ErrorType.User_Not_Join.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f5098a[ErrorType.User_Not_Allowed.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f5098a[ErrorType.Invitation_Expire.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f5098a[ErrorType.User_Was_Kicked.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f5098a[ErrorType.Group_Was_Destroyed.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f5098a[ErrorType.Malformed_Request.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f5098a[ErrorType.Time_out.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public enum ErrorType {
        Internal_Server_Error,
        Bad_Request,
        User_Not_Authorized,
        User_Not_Exist,
        Reach_Max_Group,
        Group_Already_Exist,
        Group_Not_Exist,
        Message_Not_Exist,
        User_Already_Joined,
        Reach_Max_Member,
        User_Not_Join,
        User_Not_Allowed,
        Invitation_Expire,
        User_Was_Kicked,
        Group_Was_Destroyed,
        Malformed_Request,
        Time_out;

        public String toString() {
            switch (C05811.f5098a[ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return "Internal_Server_Error";
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return "Bad_Request";
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return "User_Not_Authorized";
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    return "User_Not_Exist";
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    return "Reach_Max_Group";
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return "Group_Already_Exist";
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    return "Group_Not_Exist";
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    return "Message_Not_Exist";
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    return "User_Already_Joined";
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    return "Reach_Max_Member";
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    return "User_Not_Join";
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    return "User_Not_Allowed";
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    return "Invitation_Expire";
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    return "User_Was_Kicked";
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    return "Group_Was_Destroyed";
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    return "Malformed_Request";
                case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                    return "Time_out";
                default:
                    return null;
            }
        }

        public static ErrorType m7575a(String str) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -2012387588:
                    if (str.equals("Time_out")) {
                        obj = 16;
                        break;
                    }
                    break;
                case -1805510358:
                    if (str.equals("User_Not_Join")) {
                        obj = 10;
                        break;
                    }
                    break;
                case -1564195416:
                    if (str.equals("User_Not_Allowed")) {
                        obj = 11;
                        break;
                    }
                    break;
                case -1414274512:
                    if (str.equals("Group_Already_Exist")) {
                        obj = 5;
                        break;
                    }
                    break;
                case -1374510971:
                    if (str.equals("Invitation_Expire")) {
                        obj = 12;
                        break;
                    }
                    break;
                case -916968520:
                    if (str.equals("Reach_Max_Group")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -916382733:
                    if (str.equals("Message_Not_Exist")) {
                        obj = 7;
                        break;
                    }
                    break;
                case -140595465:
                    if (str.equals("User_Not_Exist")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 245137429:
                    if (str.equals("Bad_Request")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 282973291:
                    if (str.equals("Group_Not_Exist")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 326079524:
                    if (str.equals("User_Already_Joined")) {
                        obj = 8;
                        break;
                    }
                    break;
                case 412337295:
                    if (str.equals("User_Was_Kicked")) {
                        obj = 13;
                        break;
                    }
                    break;
                case 471767086:
                    if (str.equals("Internal_Server_Error")) {
                        obj = null;
                        break;
                    }
                    break;
                case 914858531:
                    if (str.equals("Group_Was_Destroyed")) {
                        obj = 14;
                        break;
                    }
                    break;
                case 1285566875:
                    if (str.equals("User_Not_Authorized")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1550041547:
                    if (str.equals("Malformed_Request")) {
                        obj = 15;
                        break;
                    }
                    break;
                case 1798438017:
                    if (str.equals("Reach_Max_Member")) {
                        obj = 9;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    return Internal_Server_Error;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return Bad_Request;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return User_Not_Authorized;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return User_Not_Exist;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    return Reach_Max_Group;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    return Group_Already_Exist;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return Group_Not_Exist;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    return Message_Not_Exist;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    return User_Already_Joined;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    return Reach_Max_Member;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    return User_Not_Join;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    return User_Not_Allowed;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    return Invitation_Expire;
                case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                    return User_Was_Kicked;
                case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                    return Group_Was_Destroyed;
                case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                    return Malformed_Request;
                case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                    return Time_out;
                default:
                    throw new EnumConstantNotPresentException(ErrorType.class, str);
            }
        }
    }

    public ErrorXE(ErrorType errorType) {
        this.f5117a = errorType;
    }

    public ErrorType m7576a() {
        return this.f5117a;
    }

    public String getNamespace() {
        return "bpn:fault:v2";
    }

    public String getElementName() {
        return "err";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("err").append(" ").append("xmlns").append("='").append("bpn:fault:v2").append("' ").append(AckRequest.ELEMENT).append("='").append(this.f5117a).append("'/>");
        return stringBuilder;
    }
}
