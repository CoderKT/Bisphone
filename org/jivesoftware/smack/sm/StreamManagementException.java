package org.jivesoftware.smack.sm;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Stanza;

public abstract class StreamManagementException extends SmackException {
    private static final long serialVersionUID = 3767590115788821101L;

    public class StreamIdDoesNotMatchException extends StreamManagementException {
        private static final long serialVersionUID = 1191073341336559621L;

        public StreamIdDoesNotMatchException(String str, String str2) {
            super("Stream IDs do not match. Expected '" + str + "', but got '" + str2 + "'");
        }
    }

    public class StreamManagementCounterError extends StreamManagementException {
        private static final long serialVersionUID = 1;
        private final long ackedStanzaCount;
        private final List<Stanza> ackedStanzas;
        private final long handledCount;
        private final int outstandingStanzasCount;
        private final long previousServerHandledCount;

        public StreamManagementCounterError(long j, long j2, long j3, List<Stanza> list) {
            super("There was an error regarding the Stream Mangement counters. Server reported " + j + " handled stanzas, which means that the " + j3 + " recently send stanzas by client are now acked by the server. But Smack had only " + list.size() + " to acknowledge. The stanza id of the last acked outstanding stanza is " + (list.isEmpty() ? "<no acked stanzas>" : ((Stanza) list.get(list.size() - 1)).getStanzaId()));
            this.handledCount = j;
            this.previousServerHandledCount = j2;
            this.ackedStanzaCount = j3;
            this.outstandingStanzasCount = list.size();
            this.ackedStanzas = Collections.unmodifiableList(list);
        }

        public long getHandledCount() {
            return this.handledCount;
        }

        public long getPreviousServerHandledCount() {
            return this.previousServerHandledCount;
        }

        public long getAckedStanzaCount() {
            return this.ackedStanzaCount;
        }

        public int getOutstandingStanzasCount() {
            return this.outstandingStanzasCount;
        }

        public List<Stanza> getAckedStanzas() {
            return this.ackedStanzas;
        }
    }

    public class StreamManagementNotEnabledException extends StreamManagementException {
        private static final long serialVersionUID = 2624821584352571307L;
    }

    public StreamManagementException(String str) {
        super(str);
    }
}
