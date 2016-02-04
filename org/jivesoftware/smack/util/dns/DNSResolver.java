package org.jivesoftware.smack.util.dns;

import java.util.List;

public interface DNSResolver {
    List<SRVRecord> lookupSRVRecords(String str);
}
