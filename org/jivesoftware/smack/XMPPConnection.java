package org.jivesoftware.smack;

import org.jivesoftware.smack.PacketCollector.Configuration;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;

public interface XMPPConnection {

    public enum FromMode {
        UNCHANGED,
        OMITTED,
        USER
    }

    void addAsyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addConnectionListener(ConnectionListener connectionListener);

    void addOneTimeSyncCallback(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addPacketInterceptor(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    @Deprecated
    void addPacketListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addPacketSendingListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addSyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    PacketCollector createPacketCollector(Configuration configuration);

    PacketCollector createPacketCollector(StanzaFilter stanzaFilter);

    PacketCollector createPacketCollectorAndSend(StanzaFilter stanzaFilter, Stanza stanza);

    PacketCollector createPacketCollectorAndSend(IQ iq);

    int getConnectionCounter();

    <F extends ExtensionElement> F getFeature(String str, String str2);

    FromMode getFromMode();

    String getHost();

    long getLastStanzaReceived();

    long getPacketReplyTimeout();

    int getPort();

    String getServiceName();

    String getStreamId();

    String getUser();

    boolean hasFeature(String str, String str2);

    boolean isAnonymous();

    boolean isAuthenticated();

    boolean isConnected();

    boolean isSecureConnection();

    boolean isUsingCompression();

    IQRequestHandler registerIQRequestHandler(IQRequestHandler iQRequestHandler);

    boolean removeAsyncStanzaListener(StanzaListener stanzaListener);

    void removeConnectionListener(ConnectionListener connectionListener);

    void removePacketCollector(PacketCollector packetCollector);

    void removePacketInterceptor(StanzaListener stanzaListener);

    @Deprecated
    boolean removePacketListener(StanzaListener stanzaListener);

    void removePacketSendingListener(StanzaListener stanzaListener);

    boolean removeSyncStanzaListener(StanzaListener stanzaListener);

    void send(PlainStreamElement plainStreamElement);

    void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener);

    void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener, ExceptionCallback exceptionCallback);

    void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener, ExceptionCallback exceptionCallback, long j);

    @Deprecated
    void sendPacket(Stanza stanza);

    void sendStanza(Stanza stanza);

    void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener);

    void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener, ExceptionCallback exceptionCallback);

    void sendStanzaWithResponseCallback(Stanza stanza, StanzaFilter stanzaFilter, StanzaListener stanzaListener, ExceptionCallback exceptionCallback, long j);

    void setFromMode(FromMode fromMode);

    void setPacketReplyTimeout(long j);

    IQRequestHandler unregisterIQRequestHandler(String str, String str2, Type type);

    IQRequestHandler unregisterIQRequestHandler(IQRequestHandler iQRequestHandler);
}
