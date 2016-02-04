package app.xmpp;

import java.util.Comparator;
import org.jivesoftware.smack.packet.Message;

public class GMRecord implements Comparable<GMRecord>, Comparator<GMRecord> {
    private long f4883a;
    private long f4884b;
    private Message f4885c;
    private int f4886d;

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m7313a((GMRecord) obj, (GMRecord) obj2);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m7312a((GMRecord) obj);
    }

    public GMRecord(long j, long j2, Message message, int i) {
        this.f4883a = j;
        this.f4885c = message;
        this.f4886d = i;
        this.f4884b = j2;
    }

    public long m7314a() {
        return this.f4884b;
    }

    public long m7317b() {
        return this.f4883a;
    }

    public Message m7319c() {
        return this.f4885c;
    }

    public int m7320d() {
        return this.f4886d;
    }

    public void m7315a(int i) {
        this.f4886d = i;
    }

    public void m7316a(long j) {
        this.f4883a = j;
    }

    public void m7318b(long j) {
        this.f4884b = j;
    }

    public int m7312a(GMRecord gMRecord) {
        if (gMRecord == null) {
            return 1;
        }
        if (this.f4883a < gMRecord.f4883a) {
            return -1;
        }
        if (this.f4883a == gMRecord.f4883a) {
            return 0;
        }
        return 1;
    }

    public int m7313a(GMRecord gMRecord, GMRecord gMRecord2) {
        if (gMRecord.f4883a < gMRecord2.f4883a) {
            return -1;
        }
        return gMRecord == gMRecord2 ? 0 : 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || this.f4885c == null) {
            return false;
        }
        GMRecord gMRecord = (GMRecord) obj;
        if (this.f4885c.getStanzaId() != null) {
            return this.f4885c.getStanzaId().equals(gMRecord.f4885c.getStanzaId());
        }
        if (gMRecord.f4885c.getStanzaId() != null) {
            return false;
        }
        return true;
    }

    public static GMRecord m7311a(Message message) {
        return new GMRecord(0, 0, message, 0);
    }
}
