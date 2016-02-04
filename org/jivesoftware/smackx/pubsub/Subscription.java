package org.jivesoftware.smackx.pubsub;

public class Subscription extends NodeExtension {
    protected boolean configRequired;
    protected String id;
    protected String jid;
    protected State state;

    public enum State {
        subscribed,
        unconfigured,
        pending,
        none
    }

    public Subscription(String str) {
        this(str, null, null, null);
    }

    public Subscription(String str, String str2) {
        this(str, str2, null, null);
    }

    public Subscription(String str, String str2, String str3, State state) {
        super(PubSubElementType.SUBSCRIPTION, str2);
        this.configRequired = false;
        this.jid = str;
        this.id = str3;
        this.state = state;
    }

    public Subscription(String str, String str2, String str3, State state, boolean z) {
        super(PubSubElementType.SUBSCRIPTION, str2);
        this.configRequired = false;
        this.jid = str;
        this.id = str3;
        this.state = state;
        this.configRequired = z;
    }

    public String getJid() {
        return this.jid;
    }

    public String getId() {
        return this.id;
    }

    public State getState() {
        return this.state;
    }

    public boolean isConfigRequired() {
        return this.configRequired;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<subscription");
        appendAttribute(stringBuilder, "jid", this.jid);
        if (getNode() != null) {
            appendAttribute(stringBuilder, "node", getNode());
        }
        if (this.id != null) {
            appendAttribute(stringBuilder, "subid", this.id);
        }
        if (this.state != null) {
            appendAttribute(stringBuilder, "subscription", this.state.toString());
        }
        stringBuilder.append("/>");
        return stringBuilder.toString();
    }

    private void appendAttribute(StringBuilder stringBuilder, String str, String str2) {
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append("='");
        stringBuilder.append(str2);
        stringBuilder.append("'");
    }
}
