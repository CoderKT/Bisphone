package org.jivesoftware.smack.proxy;

import java.io.IOException;
import org.jivesoftware.smack.proxy.ProxyInfo.ProxyType;

public class ProxyException extends IOException {
    private static final long serialVersionUID = 1;

    public ProxyException(ProxyType proxyType, String str, Throwable th) {
        super("Proxy Exception " + proxyType.toString() + " : " + str + ", " + th);
    }

    public ProxyException(ProxyType proxyType, String str) {
        super("Proxy Exception " + proxyType.toString() + " : " + str);
    }

    public ProxyException(ProxyType proxyType) {
        super("Proxy Exception " + proxyType.toString() + " : " + "Unknown Error");
    }
}
