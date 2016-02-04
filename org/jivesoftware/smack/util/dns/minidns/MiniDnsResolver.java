package org.jivesoftware.smack.util.dns.minidns;

import de.measite.minidns.Client;
import de.measite.minidns.DNSCache;
import de.measite.minidns.DNSMessage;
import de.measite.minidns.Question;
import de.measite.minidns.Record;
import de.measite.minidns.Record.CLASS;
import de.measite.minidns.Record.TYPE;
import de.measite.minidns.record.SRV;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.initializer.SmackInitializer;
import org.jivesoftware.smack.util.DNSUtil;
import org.jivesoftware.smack.util.dns.DNSResolver;
import org.jivesoftware.smack.util.dns.SRVRecord;
import org.jxmpp.util.cache.ExpirationCache;

public class MiniDnsResolver implements SmackInitializer, DNSResolver {
    private static final long ONE_DAY = 86400000;
    private static final ExpirationCache<Question, DNSMessage> cache;
    private static final MiniDnsResolver instance;
    private final Client client;

    /* renamed from: org.jivesoftware.smack.util.dns.minidns.MiniDnsResolver.1 */
    class C10311 implements DNSCache {
        C10311() {
        }

        public DNSMessage get(Question question) {
            return (DNSMessage) MiniDnsResolver.cache.get(question);
        }

        public void put(Question question, DNSMessage dNSMessage) {
            long j = MiniDnsResolver.ONE_DAY;
            for (Record record : dNSMessage.m12825e()) {
                if (record.m12838a(question)) {
                    j = record.m12839b();
                    break;
                }
            }
            MiniDnsResolver.cache.m13450a(question, dNSMessage, j);
        }
    }

    static {
        instance = new MiniDnsResolver();
        cache = new ExpirationCache(10, ONE_DAY);
    }

    public MiniDnsResolver() {
        this.client = new Client(new C10311());
    }

    public static DNSResolver getInstance() {
        return instance;
    }

    public List<SRVRecord> lookupSRVRecords(String str) {
        List<SRVRecord> linkedList = new LinkedList();
        DNSMessage a = this.client.m12809a(str, TYPE.SRV, CLASS.IN);
        if (a == null) {
            return linkedList;
        }
        for (Record a2 : a.m12825e()) {
            SRV srv = (SRV) a2.m12836a();
            linkedList.add(new SRVRecord(srv.m12849d(), srv.m12848c(), srv.m12845a(), srv.m12847b()));
        }
        return linkedList;
    }

    public static void setup() {
        DNSUtil.setDNSResolver(getInstance());
    }

    public List<Exception> initialize() {
        setup();
        return null;
    }
}
