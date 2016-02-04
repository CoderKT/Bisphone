package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.dns.DNSResolver;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jivesoftware.smack.util.dns.SRVRecord;
import se.emilsjolander.stickylistheaders.C1128R;

public class DNSUtil {
    private static final Logger LOGGER;
    private static DNSResolver dnsResolver;
    private static StringTransformer idnaTransformer;

    /* renamed from: org.jivesoftware.smack.util.DNSUtil.1 */
    final class C10251 implements StringTransformer {
        C10251() {
        }

        public String transform(String str) {
            return str;
        }
    }

    /* renamed from: org.jivesoftware.smack.util.DNSUtil.2 */
    /* synthetic */ class C10262 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType;

        static {
            $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType = new int[DomainType.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[DomainType.Server.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[DomainType.Client.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    enum DomainType {
        Server,
        Client
    }

    static {
        LOGGER = Logger.getLogger(DNSUtil.class.getName());
        dnsResolver = null;
        idnaTransformer = new C10251();
    }

    public static void setDNSResolver(DNSResolver dNSResolver) {
        dnsResolver = dNSResolver;
    }

    public static DNSResolver getDNSResolver() {
        return dnsResolver;
    }

    public static void setIdnaTransformer(StringTransformer stringTransformer) {
        if (stringTransformer == null) {
            throw new NullPointerException();
        }
        idnaTransformer = stringTransformer;
    }

    public static List<HostAddress> resolveXMPPDomain(String str, List<HostAddress> list) {
        String transform = idnaTransformer.transform(str);
        if (dnsResolver != null) {
            return resolveDomain(transform, DomainType.Client, list);
        }
        LOGGER.warning("No DNS Resolver active in Smack, will be unable to perform DNS SRV lookups");
        List<HostAddress> arrayList = new ArrayList(1);
        arrayList.add(new HostAddress(transform, 5222));
        return arrayList;
    }

    public static List<HostAddress> resolveXMPPServerDomain(String str, List<HostAddress> list) {
        String transform = idnaTransformer.transform(str);
        if (dnsResolver != null) {
            return resolveDomain(transform, DomainType.Server, list);
        }
        LOGGER.warning("No DNS Resolver active in Smack, will be unable to perform DNS SRV lookups");
        List<HostAddress> arrayList = new ArrayList(1);
        arrayList.add(new HostAddress(transform, 5269));
        return arrayList;
    }

    private static List<HostAddress> resolveDomain(String str, DomainType domainType, List<HostAddress> list) {
        String str2;
        List<HostAddress> arrayList = new ArrayList();
        switch (C10262.$SwitchMap$org$jivesoftware$smack$util$DNSUtil$DomainType[domainType.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                str2 = "_xmpp-server._tcp." + str;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                str2 = "_xmpp-client._tcp." + str;
                break;
            default:
                throw new AssertionError();
        }
        try {
            List<SRVRecord> lookupSRVRecords = dnsResolver.lookupSRVRecords(str2);
            if (LOGGER.isLoggable(Level.FINE)) {
                String str3 = "Resolved SRV RR for " + str2 + ":";
                String str4 = str3;
                for (SRVRecord sRVRecord : lookupSRVRecords) {
                    str4 = str4 + " " + sRVRecord;
                }
                LOGGER.fine(str4);
            }
            arrayList.addAll(sortSRVRecords(lookupSRVRecords));
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, "Exception while resovling SRV records for " + str + ". Consider adding '_xmpp-(server|client)._tcp' DNS SRV Records", e);
            if (list != null) {
                HostAddress hostAddress = new HostAddress(str2);
                hostAddress.setException(e);
                list.add(hostAddress);
            }
        }
        arrayList.add(new HostAddress(str));
        return arrayList;
    }

    private static List<HostAddress> sortSRVRecords(List<SRVRecord> list) {
        if (list.size() == 1 && ((SRVRecord) list.get(0)).getFQDN().equals(".")) {
            return Collections.emptyList();
        }
        Collections.sort(list);
        SortedMap treeMap = new TreeMap();
        for (SRVRecord sRVRecord : list) {
            Integer valueOf = Integer.valueOf(sRVRecord.getPriority());
            List list2 = (List) treeMap.get(valueOf);
            if (list2 == null) {
                list2 = new LinkedList();
                treeMap.put(valueOf, list2);
            }
            list2.add(sRVRecord);
        }
        List<HostAddress> arrayList = new ArrayList(list.size());
        for (Integer num : treeMap.keySet()) {
            List<SRVRecord> list3 = (List) treeMap.get(num);
            while (true) {
                int size = list3.size();
                if (size > 0) {
                    int i;
                    int[] iArr = new int[list3.size()];
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 1;
                    for (SRVRecord weight : list3) {
                        if (weight.getWeight() > 0) {
                            i = 0;
                        } else {
                            i = i4;
                        }
                        i4 = i;
                    }
                    for (SRVRecord weight2 : list3) {
                        i2 += weight2.getWeight() + i4;
                        iArr[i3] = i2;
                        i3++;
                    }
                    if (i2 == 0) {
                        i = (int) (Math.random() * ((double) size));
                    } else {
                        i = bisect(iArr, Math.random() * ((double) i2));
                    }
                    arrayList.add((SRVRecord) list3.remove(i));
                }
            }
        }
        return arrayList;
    }

    private static int bisect(int[] iArr, double d) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length && d >= ((double) iArr[i])) {
            i2++;
            i++;
        }
        return i2;
    }
}
