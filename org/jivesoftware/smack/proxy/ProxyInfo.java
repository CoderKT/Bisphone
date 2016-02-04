package org.jivesoftware.smack.proxy;

import javax.net.SocketFactory;

public class ProxyInfo {
    private String proxyAddress;
    private String proxyPassword;
    private int proxyPort;
    private ProxyType proxyType;
    private String proxyUsername;

    public enum ProxyType {
        NONE,
        HTTP,
        SOCKS4,
        SOCKS5
    }

    public ProxyInfo(ProxyType proxyType, String str, int i, String str2, String str3) {
        this.proxyType = proxyType;
        this.proxyAddress = str;
        this.proxyPort = i;
        this.proxyUsername = str2;
        this.proxyPassword = str3;
    }

    public static ProxyInfo forHttpProxy(String str, int i, String str2, String str3) {
        return new ProxyInfo(ProxyType.HTTP, str, i, str2, str3);
    }

    public static ProxyInfo forSocks4Proxy(String str, int i, String str2, String str3) {
        return new ProxyInfo(ProxyType.SOCKS4, str, i, str2, str3);
    }

    public static ProxyInfo forSocks5Proxy(String str, int i, String str2, String str3) {
        return new ProxyInfo(ProxyType.SOCKS5, str, i, str2, str3);
    }

    public static ProxyInfo forNoProxy() {
        return new ProxyInfo(ProxyType.NONE, null, 0, null, null);
    }

    public static ProxyInfo forDefaultProxy() {
        return new ProxyInfo(ProxyType.NONE, null, 0, null, null);
    }

    public ProxyType getProxyType() {
        return this.proxyType;
    }

    public String getProxyAddress() {
        return this.proxyAddress;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public String getProxyUsername() {
        return this.proxyUsername;
    }

    public String getProxyPassword() {
        return this.proxyPassword;
    }

    public SocketFactory getSocketFactory() {
        if (this.proxyType == ProxyType.NONE) {
            return new DirectSocketFactory();
        }
        if (this.proxyType == ProxyType.HTTP) {
            return new HTTPProxySocketFactory(this);
        }
        if (this.proxyType == ProxyType.SOCKS4) {
            return new Socks4ProxySocketFactory(this);
        }
        if (this.proxyType == ProxyType.SOCKS5) {
            return new Socks5ProxySocketFactory(this);
        }
        return null;
    }
}
