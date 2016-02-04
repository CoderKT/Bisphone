package org.jivesoftware.smackx.time.packet;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jxmpp.util.XmppDateTime;

public class Time extends IQ {
    public static final String ELEMENT = "time";
    private static final Logger LOGGER;
    public static final String NAMESPACE = "urn:xmpp:time";
    private String tzo;
    private String utc;

    static {
        LOGGER = Logger.getLogger(Time.class.getName());
    }

    public Time() {
        super(ELEMENT, NAMESPACE);
        setType(Type.get);
    }

    public Time(Calendar calendar) {
        super(ELEMENT, NAMESPACE);
        this.tzo = XmppDateTime.m13429a(calendar.getTimeZone());
        this.utc = XmppDateTime.m13428a(calendar.getTime());
    }

    public Date getTime() {
        Date date = null;
        if (this.utc != null) {
            try {
                date = XmppDateTime.m13435b(this.utc);
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Error getting local time", e);
            }
        }
        return date;
    }

    public void setTime(Date date) {
    }

    public String getUtc() {
        return this.utc;
    }

    public void setUtc(String str) {
        this.utc = str;
    }

    public String getTzo() {
        return this.tzo;
    }

    public void setTzo(String str) {
        this.tzo = str;
    }

    public static Time createResponse(IQ iq) {
        Time time = new Time(Calendar.getInstance());
        time.setType(Type.result);
        time.setTo(iq.getFrom());
        return time;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        if (this.utc != null) {
            iQChildElementXmlStringBuilder.rightAngleBracket();
            iQChildElementXmlStringBuilder.append((CharSequence) "<utc>").append(this.utc).append((CharSequence) "</utc>");
            iQChildElementXmlStringBuilder.append((CharSequence) "<tzo>").append(this.tzo).append((CharSequence) "</tzo>");
        } else {
            iQChildElementXmlStringBuilder.setEmptyElement();
        }
        return iQChildElementXmlStringBuilder;
    }
}
