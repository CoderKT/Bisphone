package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.util.Objects;

public class PresenceTypeFilter extends FlexibleStanzaTypeFilter<Presence> {
    public static final PresenceTypeFilter AVAILABLE;
    public static final PresenceTypeFilter ERROR;
    public static final PresenceTypeFilter PROBE;
    public static final PresenceTypeFilter SUBSCRIBE;
    public static final PresenceTypeFilter SUBSCRIBED;
    public static final PresenceTypeFilter UNAVAILABLE;
    public static final PresenceTypeFilter UNSUBSCRIBE;
    public static final PresenceTypeFilter UNSUBSCRIBED;
    private final Type type;

    static {
        AVAILABLE = new PresenceTypeFilter(Type.available);
        UNAVAILABLE = new PresenceTypeFilter(Type.unavailable);
        SUBSCRIBE = new PresenceTypeFilter(Type.subscribe);
        SUBSCRIBED = new PresenceTypeFilter(Type.subscribed);
        UNSUBSCRIBE = new PresenceTypeFilter(Type.unsubscribe);
        UNSUBSCRIBED = new PresenceTypeFilter(Type.unsubscribed);
        ERROR = new PresenceTypeFilter(Type.error);
        PROBE = new PresenceTypeFilter(Type.probe);
    }

    private PresenceTypeFilter(Type type) {
        super(Presence.class);
        this.type = (Type) Objects.requireNonNull(type, "type must not be null");
    }

    protected boolean acceptSpecific(Presence presence) {
        return presence.getType() == this.type;
    }

    public String toString() {
        return getClass().getSimpleName() + ": type=" + this.type;
    }
}
