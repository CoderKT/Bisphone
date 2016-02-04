package org.jivesoftware.smack;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.proxy.ProxyInfo;

public abstract class ConnectionConfiguration {
    protected final boolean allowNullOrEmptyUsername;
    private final CallbackHandler callbackHandler;
    private final SSLContext customSSLContext;
    private final boolean debuggerEnabled;
    private final String[] enabledSSLCiphers;
    private final String[] enabledSSLProtocols;
    protected final String host;
    private final HostnameVerifier hostnameVerifier;
    private final String keystorePath;
    private final String keystoreType;
    private final boolean legacySessionDisabled;
    private final String password;
    private final String pkcs11Library;
    protected final int port;
    protected final ProxyInfo proxy;
    private final String resource;
    private final SecurityMode securityMode;
    private final boolean sendPresence;
    protected final String serviceName;
    private final SocketFactory socketFactory;
    private final CharSequence username;

    public abstract class Builder<B extends Builder<B, C>, C extends ConnectionConfiguration> {
        private boolean allowEmptyOrNullUsername;
        private CallbackHandler callbackHandler;
        private SSLContext customSSLContext;
        private boolean debuggerEnabled;
        private String[] enabledSSLCiphers;
        private String[] enabledSSLProtocols;
        private String host;
        private HostnameVerifier hostnameVerifier;
        private String keystorePath;
        private String keystoreType;
        private boolean legacySessionDisabled;
        private String password;
        private String pkcs11Library;
        private int port;
        private ProxyInfo proxy;
        private String resource;
        private SecurityMode securityMode;
        private boolean sendPresence;
        private String serviceName;
        private SocketFactory socketFactory;
        private CharSequence username;

        public abstract C build();

        protected abstract B getThis();

        protected Builder() {
            this.securityMode = SecurityMode.ifpossible;
            this.keystorePath = System.getProperty("javax.net.ssl.keyStore");
            this.keystoreType = "jks";
            this.pkcs11Library = "pkcs11.config";
            this.resource = "Smack";
            this.sendPresence = true;
            this.legacySessionDisabled = false;
            this.debuggerEnabled = SmackConfiguration.DEBUG;
            this.port = 5222;
            this.allowEmptyOrNullUsername = false;
        }

        public B setUsernameAndPassword(CharSequence charSequence, String str) {
            this.username = charSequence;
            this.password = str;
            return getThis();
        }

        public B setServiceName(String str) {
            this.serviceName = str;
            return getThis();
        }

        public B setResource(String str) {
            this.resource = str;
            return getThis();
        }

        public B setHost(String str) {
            this.host = str;
            return getThis();
        }

        public B setPort(int i) {
            this.port = i;
            return getThis();
        }

        public B setCallbackHandler(CallbackHandler callbackHandler) {
            this.callbackHandler = callbackHandler;
            return getThis();
        }

        public B setSecurityMode(SecurityMode securityMode) {
            this.securityMode = securityMode;
            return getThis();
        }

        public B setKeystorePath(String str) {
            this.keystorePath = str;
            return getThis();
        }

        public B setKeystoreType(String str) {
            this.keystoreType = str;
            return getThis();
        }

        public B setPKCS11Library(String str) {
            this.pkcs11Library = str;
            return getThis();
        }

        public B setCustomSSLContext(SSLContext sSLContext) {
            this.customSSLContext = sSLContext;
            return getThis();
        }

        public B setEnabledSSLProtocols(String[] strArr) {
            this.enabledSSLProtocols = strArr;
            return getThis();
        }

        public B setEnabledSSLCiphers(String[] strArr) {
            this.enabledSSLCiphers = strArr;
            return getThis();
        }

        public B setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return getThis();
        }

        @Deprecated
        public B setLegacySessionDisabled(boolean z) {
            this.legacySessionDisabled = z;
            return getThis();
        }

        public B setSendPresence(boolean z) {
            this.sendPresence = z;
            return getThis();
        }

        public B setDebuggerEnabled(boolean z) {
            this.debuggerEnabled = z;
            return getThis();
        }

        public B setSocketFactory(SocketFactory socketFactory) {
            this.socketFactory = socketFactory;
            return getThis();
        }

        public B setProxyInfo(ProxyInfo proxyInfo) {
            this.proxy = proxyInfo;
            return getThis();
        }

        public B allowEmptyOrNullUsernames() {
            this.allowEmptyOrNullUsername = true;
            return getThis();
        }
    }

    public enum SecurityMode {
        required,
        ifpossible,
        disabled
    }

    static {
        SmackConfiguration.getVersion();
    }

    protected ConnectionConfiguration(Builder<?, ?> builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.callbackHandler = builder.callbackHandler;
        this.resource = builder.resource;
        this.serviceName = builder.serviceName;
        if (this.serviceName == null) {
            throw new IllegalArgumentException("Must provide XMPP service name");
        }
        this.host = builder.host;
        this.port = builder.port;
        this.proxy = builder.proxy;
        if (this.proxy == null) {
            this.socketFactory = builder.socketFactory;
        } else if (builder.socketFactory != null) {
            throw new IllegalArgumentException("Can not use proxy together with custom socket factory");
        } else {
            this.socketFactory = this.proxy.getSocketFactory();
        }
        this.securityMode = builder.securityMode;
        this.keystoreType = builder.keystoreType;
        this.keystorePath = builder.keystorePath;
        this.pkcs11Library = builder.pkcs11Library;
        this.customSSLContext = builder.customSSLContext;
        this.enabledSSLProtocols = builder.enabledSSLProtocols;
        this.enabledSSLCiphers = builder.enabledSSLCiphers;
        this.hostnameVerifier = builder.hostnameVerifier;
        this.sendPresence = builder.sendPresence;
        this.legacySessionDisabled = builder.legacySessionDisabled;
        this.debuggerEnabled = builder.debuggerEnabled;
        this.allowNullOrEmptyUsername = builder.allowEmptyOrNullUsername;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public SecurityMode getSecurityMode() {
        return this.securityMode;
    }

    public String getKeystorePath() {
        return this.keystorePath;
    }

    public String getKeystoreType() {
        return this.keystoreType;
    }

    public String getPKCS11Library() {
        return this.pkcs11Library;
    }

    public SSLContext getCustomSSLContext() {
        return this.customSSLContext;
    }

    public String[] getEnabledSSLProtocols() {
        return this.enabledSSLProtocols;
    }

    public String[] getEnabledSSLCiphers() {
        return this.enabledSSLCiphers;
    }

    public HostnameVerifier getHostnameVerifier() {
        if (this.hostnameVerifier != null) {
            return this.hostnameVerifier;
        }
        return SmackConfiguration.getDefaultHostnameVerifier();
    }

    public boolean isDebuggerEnabled() {
        return this.debuggerEnabled;
    }

    @Deprecated
    public boolean isLegacySessionDisabled() {
        return this.legacySessionDisabled;
    }

    public CallbackHandler getCallbackHandler() {
        return this.callbackHandler;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public CharSequence getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getResource() {
        return this.resource;
    }

    public boolean isSendPresence() {
        return this.sendPresence;
    }

    public boolean isCompressionEnabled() {
        return false;
    }
}
