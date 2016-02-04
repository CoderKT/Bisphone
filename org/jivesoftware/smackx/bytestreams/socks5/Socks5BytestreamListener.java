package org.jivesoftware.smackx.bytestreams.socks5;

import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;

public abstract class Socks5BytestreamListener implements BytestreamListener {
    public abstract void incomingBytestreamRequest(Socks5BytestreamRequest socks5BytestreamRequest);

    public void incomingBytestreamRequest(BytestreamRequest bytestreamRequest) {
        incomingBytestreamRequest((Socks5BytestreamRequest) bytestreamRequest);
    }
}
