package de.measite.minidns;

import de.measite.minidns.DNSMessage.RESPONSE_CODE;
import de.measite.minidns.Record.CLASS;
import de.measite.minidns.Record.TYPE;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger f8002e;
    protected Random f8003a;
    protected int f8004b;
    protected int f8005c;
    protected DNSCache f8006d;

    /* renamed from: de.measite.minidns.Client.1 */
    class C09451 implements DNSCache {
        final /* synthetic */ Map f8001a;

        public void put(Question question, DNSMessage dNSMessage) {
            this.f8001a.put(question, dNSMessage);
        }

        public DNSMessage get(Question question) {
            return (DNSMessage) this.f8001a.get(question);
        }
    }

    static {
        f8002e = Logger.getLogger(Client.class.getName());
    }

    public Client(DNSCache dNSCache) {
        this.f8004b = 1500;
        this.f8005c = 5000;
        try {
            this.f8003a = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            this.f8003a = new SecureRandom();
        }
        this.f8006d = dNSCache;
    }

    public Client() {
        this((DNSCache) null);
    }

    public DNSMessage m12809a(String str, TYPE type, CLASS classR) {
        return m12806a(new Question(str, type, classR));
    }

    public DNSMessage m12807a(Question question, String str) {
        return m12808a(question, str, 53);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public de.measite.minidns.DNSMessage m12808a(de.measite.minidns.Question r10, java.lang.String r11, int r12) {
        /*
        r9 = this;
        r4 = 1;
        r2 = 0;
        r1 = 0;
        r0 = r9.f8006d;
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r1;
    L_0x0008:
        if (r0 == 0) goto L_0x0012;
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = r9.f8006d;
        r0 = r0.get(r10);
        goto L_0x0008;
    L_0x0012:
        r3 = new de.measite.minidns.DNSMessage;
        r3.<init>();
        r0 = new de.measite.minidns.Question[r4];
        r0[r2] = r10;
        r3.m12821a(r0);
        r3.m12820a(r4);
        r0 = r9.f8003a;
        r0 = r0.nextInt();
        r3.m12819a(r0);
        r0 = r3.m12823c();
        r4 = new java.net.DatagramSocket;
        r4.<init>();
        r5 = new java.net.DatagramPacket;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r6 = r0.length;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r7 = java.net.InetAddress.getByName(r11);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r5.<init>(r0, r6, r7, r12);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r0 = r9.f8005c;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r4.setSoTimeout(r0);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r4.send(r5);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r0 = new java.net.DatagramPacket;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r5 = r9.f8004b;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r5 = new byte[r5];	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r6 = r9.f8004b;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r0.<init>(r5, r6);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r4.receive(r0);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r0 = r0.getData();	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r0 = de.measite.minidns.DNSMessage.m12817a(r0);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r5 = r0.m12818a();	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r3 = r3.m12818a();	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        if (r5 == r3) goto L_0x0077;
    L_0x0065:
        if (r4 == 0) goto L_0x006c;
    L_0x0067:
        if (r1 == 0) goto L_0x0073;
    L_0x0069:
        r4.close();	 Catch:{ Throwable -> 0x006e }
    L_0x006c:
        r0 = r1;
        goto L_0x000a;
    L_0x006e:
        r0 = move-exception;
        r1.addSuppressed(r0);
        goto L_0x006c;
    L_0x0073:
        r4.close();
        goto L_0x006c;
    L_0x0077:
        r3 = r0.m12825e();	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r5 = r3.length;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
    L_0x007c:
        if (r2 >= r5) goto L_0x008f;
    L_0x007e:
        r6 = r3[r2];	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r6 = r6.m12838a(r10);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        if (r6 == 0) goto L_0x009e;
    L_0x0086:
        r2 = r9.f8006d;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        if (r2 == 0) goto L_0x008f;
    L_0x008a:
        r2 = r9.f8006d;	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
        r2.put(r10, r0);	 Catch:{ Throwable -> 0x00a6, all -> 0x00bd }
    L_0x008f:
        if (r4 == 0) goto L_0x000a;
    L_0x0091:
        if (r1 == 0) goto L_0x00a1;
    L_0x0093:
        r4.close();	 Catch:{ Throwable -> 0x0098 }
        goto L_0x000a;
    L_0x0098:
        r2 = move-exception;
        r1.addSuppressed(r2);
        goto L_0x000a;
    L_0x009e:
        r2 = r2 + 1;
        goto L_0x007c;
    L_0x00a1:
        r4.close();
        goto L_0x000a;
    L_0x00a6:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x00a8 }
    L_0x00a8:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x00ac:
        if (r4 == 0) goto L_0x00b3;
    L_0x00ae:
        if (r1 == 0) goto L_0x00b9;
    L_0x00b0:
        r4.close();	 Catch:{ Throwable -> 0x00b4 }
    L_0x00b3:
        throw r0;
    L_0x00b4:
        r2 = move-exception;
        r1.addSuppressed(r2);
        goto L_0x00b3;
    L_0x00b9:
        r4.close();
        goto L_0x00b3;
    L_0x00bd:
        r0 = move-exception;
        goto L_0x00ac;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.measite.minidns.Client.a(de.measite.minidns.Question, java.lang.String, int):de.measite.minidns.DNSMessage");
    }

    public DNSMessage m12806a(Question question) {
        DNSMessage dNSMessage = this.f8006d == null ? null : this.f8006d.get(question);
        if (dNSMessage != null) {
            return dNSMessage;
        }
        for (String a : m12810a()) {
            try {
                dNSMessage = m12807a(question, a);
                if (dNSMessage != null && dNSMessage.m12824d() == RESPONSE_CODE.NO_ERROR) {
                    for (Record a2 : dNSMessage.m12825e()) {
                        if (a2.m12838a(question)) {
                            return dNSMessage;
                        }
                    }
                    continue;
                }
            } catch (Throwable e) {
                f8002e.log(Level.FINE, "IOException in query", e);
            }
        }
        return null;
    }

    public String[] m12810a() {
        String[] c = m12812c();
        if (c != null) {
            f8002e.fine("Got DNS servers via reflection: " + Arrays.toString(c));
            return c;
        }
        c = m12811b();
        if (c != null) {
            f8002e.fine("Got DNS servers via exec: " + Arrays.toString(c));
            return c;
        }
        f8002e.fine("No DNS found? Using fallback [8.8.8.8, [2001:4860:4860::8888]]");
        return new String[]{"8.8.8.8", "[2001:4860:4860::8888]"};
    }

    protected String[] m12811b() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            HashSet hashSet = new HashSet(6);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    readLine = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(readLine);
                        if (byName != null) {
                            readLine = byName.getHostAddress();
                            if (!(readLine == null || readLine.length() == 0)) {
                                hashSet.add(readLine);
                            }
                        }
                    }
                }
            }
            if (hashSet.size() > 0) {
                return (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        } catch (Throwable e) {
            f8002e.log(Level.WARNING, "Exception in findDNSByExec", e);
        }
        return null;
    }

    protected String[] m12812c() {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            ArrayList arrayList = new ArrayList(5);
            int length = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"}.length;
            for (int i = 0; i < length; i++) {
                String str = (String) method.invoke(null, new Object[]{r5[i]});
                if (!(str == null || str.length() == 0 || arrayList.contains(str))) {
                    InetAddress byName = InetAddress.getByName(str);
                    if (byName != null) {
                        str = byName.getHostAddress();
                        if (!(str == null || str.length() == 0 || arrayList.contains(str))) {
                            arrayList.add(str);
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
        } catch (Throwable e) {
            f8002e.log(Level.WARNING, "Exception in findDNSByReflection", e);
        }
        return null;
    }
}
