package de.measite.minidns;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache implements DNSCache {
    protected long f8045a;
    protected long f8046b;
    protected long f8047c;
    protected long f8048d;
    protected LinkedHashMap<Question, DNSMessage> f8049e;

    /* renamed from: de.measite.minidns.LRUCache.1 */
    class C09461 extends LinkedHashMap<Question, DNSMessage> {
        final /* synthetic */ int f8044a;

        protected boolean removeEldestEntry(Entry<Question, DNSMessage> entry) {
            return size() > this.f8044a;
        }
    }

    public synchronized void put(Question question, DNSMessage dNSMessage) {
        if (dNSMessage.m12822b() > 0) {
            this.f8049e.put(question, dNSMessage);
        }
    }

    public synchronized DNSMessage get(Question question) {
        DNSMessage dNSMessage;
        int i = 0;
        synchronized (this) {
            dNSMessage = (DNSMessage) this.f8049e.get(question);
            if (dNSMessage == null) {
                this.f8045a++;
                dNSMessage = null;
            } else {
                long j = this.f8048d;
                for (Record record : dNSMessage.m12825e()) {
                    j = Math.min(j, record.f8120d);
                }
                Record[] f = dNSMessage.m12826f();
                int length = f.length;
                while (i < length) {
                    j = Math.min(j, f[i].f8120d);
                    i++;
                }
                if (dNSMessage.m12822b() + j > System.currentTimeMillis()) {
                    this.f8045a++;
                    this.f8046b++;
                    this.f8049e.remove(question);
                    dNSMessage = null;
                } else {
                    this.f8047c++;
                }
            }
        }
        return dNSMessage;
    }
}
