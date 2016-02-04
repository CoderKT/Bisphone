package org.jivesoftware.smackx.caps;

public class CapsVersionAndHash {
    public final String hash;
    public final String version;

    public CapsVersionAndHash(String str, String str2) {
        this.version = str;
        this.hash = str2;
    }
}
