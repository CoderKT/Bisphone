package org.jivesoftware.smackx.delay.provider;

import java.util.Date;
import org.jxmpp.util.XmppDateTime;

public class LegacyDelayInformationProvider extends AbstractDelayInformationProvider {
    protected Date parseDate(String str) {
        return XmppDateTime.m13435b(str);
    }
}
