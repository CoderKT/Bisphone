package org.jivesoftware.smack.util.dns;

import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class SRVRecord extends HostAddress implements Comparable<SRVRecord> {
    private int priority;
    private int weight;

    public SRVRecord(String str, int i, int i2, int i3) {
        super(str, i);
        if (i3 < 0 || i3 > InBandBytestreamManager.MAXIMUM_BLOCK_SIZE) {
            throw new IllegalArgumentException("DNS SRV records weight must be a 16-bit unsiged integer (i.e. between 0-65535. Weight was: " + i3);
        } else if (i2 < 0 || i2 > InBandBytestreamManager.MAXIMUM_BLOCK_SIZE) {
            throw new IllegalArgumentException("DNS SRV records priority must be a 16-bit unsiged integer (i.e. between 0-65535. Priority was: " + i2);
        } else {
            this.priority = i2;
            this.weight = i3;
        }
    }

    public int getPriority() {
        return this.priority;
    }

    public int getWeight() {
        return this.weight;
    }

    public int compareTo(SRVRecord sRVRecord) {
        int i = sRVRecord.priority - this.priority;
        if (i == 0) {
            return this.weight - sRVRecord.weight;
        }
        return i;
    }

    public String toString() {
        return super.toString() + " prio:" + this.priority + ":w:" + this.weight;
    }
}
