package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class MultiChannelIQProvider extends IQProvider<AbstractMultiChannelIQ> {

    /* renamed from: app.xmpp.packet.channel.MultiChannelIQProvider.1 */
    class C05781 extends AbstractMultiChannelIQ {
        final /* synthetic */ MultiChannelIQProvider f5084b;

        C05781(MultiChannelIQProvider multiChannelIQProvider, String str) {
            this.f5084b = multiChannelIQProvider;
            super(str);
        }

        protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
            iQChildElementXmlStringBuilder.rightAngleBracket();
            return iQChildElementXmlStringBuilder;
        }
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7566a(xmlPullParser, i);
    }

    public AbstractMultiChannelIQ m7566a(XmlPullParser xmlPullParser, int i) {
        Object obj = null;
        AbstractMultiChannelIQ c05781 = new C05781(this, "http://bisphone.com/protocol/channel");
        String namespace = xmlPullParser.getNamespace();
        Object obj2 = -1;
        switch (namespace.hashCode()) {
            case -2092182033:
                if (namespace.equals("http://bisphone.com/protocol/channel/common/discover#list")) {
                    obj2 = null;
                    break;
                }
                break;
        }
        switch (obj2) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                c05781 = new ChannelIQList();
                while (obj == null) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "channel".equals(xmlPullParser.getName())) {
                        c05781.m7538a(ChannelElement.m7543a(xmlPullParser));
                    } else if (next == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                        obj = 1;
                    }
                }
                break;
        }
        return c05781;
    }
}
