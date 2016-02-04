package org.jivesoftware.smackx.delay.provider;

import java.util.Date;
import org.jxmpp.util.XmppDateTime;

public class DelayInformationProvider extends AbstractDelayInformationProvider {
    public static final DelayInformationProvider INSTANCE;

    static {
        INSTANCE = new DelayInformationProvider();
    }

    protected Date parseDate(String str) {
        return XmppDateTime.m13432a(str);
    }
}
