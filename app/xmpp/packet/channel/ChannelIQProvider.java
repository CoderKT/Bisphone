package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class ChannelIQProvider extends IQProvider<AbstractChannelIQ> {

    /* renamed from: app.xmpp.packet.channel.ChannelIQProvider.1 */
    class C05771 extends AbstractChannelIQ {
        final /* synthetic */ ChannelIQProvider f5078d;

        C05771(ChannelIQProvider channelIQProvider, String str) {
            this.f5078d = channelIQProvider;
            super(str);
        }

        protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
            iQChildElementXmlStringBuilder.rightAngleBracket();
            return iQChildElementXmlStringBuilder;
        }
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7562a(xmlPullParser, i);
    }

    public AbstractChannelIQ m7562a(XmlPullParser xmlPullParser, int i) {
        int i2;
        Object obj = null;
        AbstractChannelIQ c05771 = new C05771(this, "http://bisphone.com/protocol/channel");
        String namespace = xmlPullParser.getNamespace();
        Object obj2 = -1;
        switch (namespace.hashCode()) {
            case -2092267009:
                if (namespace.equals("http://bisphone.com/protocol/channel/common/discover#info")) {
                    obj2 = 4;
                    break;
                }
                break;
            case -1400747404:
                if (namespace.equals("http://bisphone.com/protocol/channel/common/action#enter")) {
                    i2 = 2;
                    break;
                }
                break;
            case -1394568621:
                if (namespace.equals("http://bisphone.com/protocol/channel/common/action#leave")) {
                    i2 = 3;
                    break;
                }
                break;
            case 324451232:
                if (namespace.equals("http://bisphone.com/protocol/channel/consumer/action#follow")) {
                    obj2 = null;
                    break;
                }
                break;
            case 1875735481:
                if (namespace.equals("http://bisphone.com/protocol/channel/consumer/action#unfollow")) {
                    i2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                c05771 = new ChannelIQFollow();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                c05771 = new ChannelIQUnfollow();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                c05771 = new ChannelIQEnter();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                c05771 = new ChannelIQLeave();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                c05771 = new ChannelIQInfo();
                break;
            default:
                return c05771;
        }
        while (obj == null) {
            i2 = xmlPullParser.next();
            if (i2 == 2 && "channel".equals(xmlPullParser.getName())) {
                c05771.m7534a(ChannelElement.m7543a(xmlPullParser));
            } else if (i2 == 2 && Time.ELEMENT.equals(xmlPullParser.getName())) {
                c05771.m7536a(TimeElement.m7567a(xmlPullParser));
            } else if (i2 == 2 && History.ELEMENT.equals(xmlPullParser.getName())) {
                c05771.m7535a(HistoryElement.m7564a(xmlPullParser));
            } else if (i2 == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return c05771;
    }
}
