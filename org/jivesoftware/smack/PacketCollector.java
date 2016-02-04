package org.jivesoftware.smack;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class PacketCollector {
    private static final Logger LOGGER;
    private boolean cancelled;
    private final PacketCollector collectorToReset;
    private final XMPPConnection connection;
    private final StanzaFilter packetFilter;
    private final ArrayBlockingQueue<Stanza> resultQueue;
    private volatile long waitStart;

    public class Configuration {
        private PacketCollector collectorToReset;
        private StanzaFilter packetFilter;
        private int size;

        private Configuration() {
            this.size = SmackConfiguration.getPacketCollectorSize();
        }

        @Deprecated
        public Configuration setPacketFilter(StanzaFilter stanzaFilter) {
            return setStanzaFilter(stanzaFilter);
        }

        public Configuration setStanzaFilter(StanzaFilter stanzaFilter) {
            this.packetFilter = stanzaFilter;
            return this;
        }

        public Configuration setSize(int i) {
            this.size = i;
            return this;
        }

        public Configuration setCollectorToReset(PacketCollector packetCollector) {
            this.collectorToReset = packetCollector;
            return this;
        }
    }

    static {
        LOGGER = Logger.getLogger(PacketCollector.class.getName());
    }

    protected PacketCollector(XMPPConnection xMPPConnection, Configuration configuration) {
        this.cancelled = false;
        this.connection = xMPPConnection;
        this.packetFilter = configuration.packetFilter;
        this.resultQueue = new ArrayBlockingQueue(configuration.size);
        this.collectorToReset = configuration.collectorToReset;
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.connection.removePacketCollector(this);
        }
    }

    @Deprecated
    public StanzaFilter getPacketFilter() {
        return getStanzaFilter();
    }

    public StanzaFilter getStanzaFilter() {
        return this.packetFilter;
    }

    public <P extends Stanza> P pollResult() {
        return (Stanza) this.resultQueue.poll();
    }

    public <P extends Stanza> P pollResultOrThrow() {
        P pollResult = pollResult();
        if (pollResult != null) {
            XMPPErrorException.ifHasErrorThenThrow(pollResult);
        }
        return pollResult;
    }

    public <P extends Stanza> P nextResultBlockForever() {
        throwIfCancelled();
        P p = null;
        while (p == null) {
            try {
                p = (Stanza) this.resultQueue.take();
            } catch (Throwable e) {
                LOGGER.log(Level.FINE, "nextResultBlockForever was interrupted", e);
            }
        }
        return p;
    }

    public <P extends Stanza> P nextResult() {
        return nextResult(this.connection.getPacketReplyTimeout());
    }

    public <P extends Stanza> P nextResult(long j) {
        throwIfCancelled();
        this.waitStart = System.currentTimeMillis();
        long j2 = j;
        P p = null;
        while (true) {
            P p2;
            try {
                p2 = (Stanza) this.resultQueue.poll(j2, TimeUnit.MILLISECONDS);
            } catch (Throwable e) {
                LOGGER.log(Level.FINE, "nextResult was interrupted", e);
                p2 = p;
            }
            if (p2 != null) {
                return p2;
            }
            j2 = j - (System.currentTimeMillis() - this.waitStart);
            if (j2 <= 0) {
                return null;
            }
            p = p2;
        }
    }

    public <P extends Stanza> P nextResultOrThrow() {
        return nextResultOrThrow(this.connection.getPacketReplyTimeout());
    }

    public <P extends Stanza> P nextResultOrThrow(long j) {
        P nextResult = nextResult(j);
        cancel();
        if (nextResult == null) {
            throw NoResponseException.newWith(this.connection, this);
        }
        XMPPErrorException.ifHasErrorThenThrow(nextResult);
        return nextResult;
    }

    public int getCollectedCount() {
        return this.resultQueue.size();
    }

    protected void processPacket(Stanza stanza) {
        if (this.packetFilter == null || this.packetFilter.accept(stanza)) {
            while (!this.resultQueue.offer(stanza)) {
                this.resultQueue.poll();
            }
            if (this.collectorToReset != null) {
                this.collectorToReset.waitStart = System.currentTimeMillis();
            }
        }
    }

    private final void throwIfCancelled() {
        if (this.cancelled) {
            throw new IllegalStateException("Packet collector already cancelled");
        }
    }

    public static Configuration newConfiguration() {
        return new Configuration();
    }
}
