package org.jivesoftware.smackx.privacy.packet;

import org.jivesoftware.smack.util.NumberUtil;

public class PrivacyItem {
    public static final String SUBSCRIPTION_BOTH = "both";
    public static final String SUBSCRIPTION_FROM = "from";
    public static final String SUBSCRIPTION_NONE = "none";
    public static final String SUBSCRIPTION_TO = "to";
    private final boolean allow;
    private boolean filterIQ;
    private boolean filterMessage;
    private boolean filterPresenceIn;
    private boolean filterPresenceOut;
    private final long order;
    private final Type type;
    private final String value;

    public enum Type {
        group,
        jid,
        subscription
    }

    public PrivacyItem(boolean z, long j) {
        this(null, null, z, j);
    }

    public PrivacyItem(Type type, String str, boolean z, long j) {
        this.filterIQ = false;
        this.filterMessage = false;
        this.filterPresenceIn = false;
        this.filterPresenceOut = false;
        NumberUtil.checkIfInUInt32Range(j);
        this.type = type;
        this.value = str;
        this.allow = z;
        this.order = j;
    }

    public boolean isAllow() {
        return this.allow;
    }

    public boolean isFilterIQ() {
        return this.filterIQ;
    }

    public void setFilterIQ(boolean z) {
        this.filterIQ = z;
    }

    public boolean isFilterMessage() {
        return this.filterMessage;
    }

    public void setFilterMessage(boolean z) {
        this.filterMessage = z;
    }

    public boolean isFilterPresenceIn() {
        return this.filterPresenceIn;
    }

    public void setFilterPresenceIn(boolean z) {
        this.filterPresenceIn = z;
    }

    public boolean isFilterPresenceOut() {
        return this.filterPresenceOut;
    }

    public void setFilterPresenceOut(boolean z) {
        this.filterPresenceOut = z;
    }

    public long getOrder() {
        return this.order;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isFilterEverything() {
        return (isFilterIQ() || isFilterMessage() || isFilterPresenceIn() || isFilterPresenceOut()) ? false : true;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<item");
        if (isAllow()) {
            stringBuilder.append(" action=\"allow\"");
        } else {
            stringBuilder.append(" action=\"deny\"");
        }
        stringBuilder.append(" order=\"").append(getOrder()).append("\"");
        if (getType() != null) {
            stringBuilder.append(" type=\"").append(getType()).append("\"");
        }
        if (getValue() != null) {
            stringBuilder.append(" value=\"").append(getValue()).append("\"");
        }
        if (isFilterEverything()) {
            stringBuilder.append("/>");
        } else {
            stringBuilder.append(">");
            if (isFilterIQ()) {
                stringBuilder.append("<iq/>");
            }
            if (isFilterMessage()) {
                stringBuilder.append("<message/>");
            }
            if (isFilterPresenceIn()) {
                stringBuilder.append("<presence-in/>");
            }
            if (isFilterPresenceOut()) {
                stringBuilder.append("<presence-out/>");
            }
            stringBuilder.append("</item>");
        }
        return stringBuilder.toString();
    }
}
