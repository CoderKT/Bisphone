package org.jivesoftware.smack.roster.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterVer;
import org.xmlpull.v1.XmlPullParser;

public class RosterVerStreamFeatureProvider extends ExtensionElementProvider<RosterVer> {
    public RosterVer parse(XmlPullParser xmlPullParser, int i) {
        return RosterVer.INSTANCE;
    }
}
