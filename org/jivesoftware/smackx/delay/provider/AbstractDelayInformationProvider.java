package org.jivesoftware.smackx.delay.provider;

import java.util.Date;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AbstractDelayInformationProvider extends ExtensionElementProvider<DelayInformation> {
    protected abstract Date parseDate(String str);

    public final DelayInformation parse(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "stamp");
        String attributeValue2 = xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM);
        String str = null;
        if (xmlPullParser.isEmptyElementTag()) {
            xmlPullParser.next();
        } else {
            int next = xmlPullParser.next();
            switch (next) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    str = "";
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str = xmlPullParser.getText();
                    xmlPullParser.next();
                    break;
                default:
                    throw new IllegalStateException("Unexpected event: " + next);
            }
        }
        try {
            return new DelayInformation(parseDate(attributeValue), attributeValue2, str);
        } catch (Throwable e) {
            throw new SmackException(e);
        }
    }
}
