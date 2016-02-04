package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.util.Objects;

public class IQTypeFilter extends FlexibleStanzaTypeFilter<IQ> {
    public static final StanzaFilter ERROR;
    public static final StanzaFilter GET;
    public static final StanzaFilter GET_OR_SET;
    public static final StanzaFilter RESULT;
    public static final StanzaFilter SET;
    private final Type type;

    static {
        GET = new IQTypeFilter(Type.get);
        SET = new IQTypeFilter(Type.set);
        RESULT = new IQTypeFilter(Type.result);
        ERROR = new IQTypeFilter(Type.error);
        GET_OR_SET = new OrFilter(GET, SET);
    }

    private IQTypeFilter(Type type) {
        super(IQ.class);
        this.type = (Type) Objects.requireNonNull(type, "Type must not be null");
    }

    protected boolean acceptSpecific(IQ iq) {
        return iq.getType() == this.type;
    }

    public String toString() {
        return getClass().getSimpleName() + ": type=" + this.type;
    }
}
