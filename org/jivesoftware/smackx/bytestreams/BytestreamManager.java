package org.jivesoftware.smackx.bytestreams;

public interface BytestreamManager {
    void addIncomingBytestreamListener(BytestreamListener bytestreamListener);

    void addIncomingBytestreamListener(BytestreamListener bytestreamListener, String str);

    BytestreamSession establishSession(String str);

    BytestreamSession establishSession(String str, String str2);

    void removeIncomingBytestreamListener(String str);

    void removeIncomingBytestreamListener(BytestreamListener bytestreamListener);
}
