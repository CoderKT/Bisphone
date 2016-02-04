package org.jivesoftware.smack.filter;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.XmppStringUtils;

public class IQReplyFilter implements StanzaFilter {
    private static final Logger LOGGER;
    private final OrFilter fromFilter;
    private final StanzaFilter iqAndIdFilter;
    private final String local;
    private final String packetId;
    private final String server;
    private final String to;

    static {
        LOGGER = Logger.getLogger(IQReplyFilter.class.getName());
    }

    public IQReplyFilter(IQ iq, XMPPConnection xMPPConnection) {
        if (iq.isRequestIQ()) {
            if (iq.getTo() != null) {
                this.to = iq.getTo().toLowerCase(Locale.US);
            } else {
                this.to = null;
            }
            String user = xMPPConnection.getUser();
            if (user == null) {
                throw new IllegalArgumentException("Must have a local (user) JID set. Either you didn't configure one or you where not connected at least once");
            }
            this.local = user.toLowerCase(Locale.US);
            this.server = xMPPConnection.getServiceName().toLowerCase(Locale.US);
            this.packetId = iq.getStanzaId();
            OrFilter orFilter = new OrFilter(IQTypeFilter.ERROR, IQTypeFilter.RESULT);
            StanzaIdFilter stanzaIdFilter = new StanzaIdFilter((Stanza) iq);
            this.iqAndIdFilter = new AndFilter(orFilter, stanzaIdFilter);
            this.fromFilter = new OrFilter();
            this.fromFilter.addFilter(FromMatchesFilter.createFull(this.to));
            if (this.to == null) {
                this.fromFilter.addFilter(FromMatchesFilter.createBare(this.local));
                this.fromFilter.addFilter(FromMatchesFilter.createFull(this.server));
                return;
            } else if (this.to.equals(XmppStringUtils.m13447d(this.local))) {
                this.fromFilter.addFilter(FromMatchesFilter.createFull(null));
                return;
            } else {
                return;
            }
        }
        throw new IllegalArgumentException("IQ must be a request IQ, i.e. of type 'get' or 'set'.");
    }

    public boolean accept(Stanza stanza) {
        if (!this.iqAndIdFilter.accept(stanza)) {
            return false;
        }
        if (this.fromFilter.accept(stanza)) {
            return true;
        }
        LOGGER.log(Level.WARNING, String.format("Rejected potentially spoofed reply to IQ-packet. Filter settings: packetId=%s, to=%s, local=%s, server=%s. Received packet with from=%s", new Object[]{this.packetId, this.to, this.local, this.server, stanza.getFrom()}), stanza);
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(": iqAndIdFilter (").append(this.iqAndIdFilter.toString()).append("), ");
        stringBuilder.append(": fromFilter (").append(this.fromFilter.toString()).append(')');
        return stringBuilder.toString();
    }
}
