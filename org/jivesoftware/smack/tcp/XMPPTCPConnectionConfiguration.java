package org.jivesoftware.smack.tcp;

import org.jivesoftware.smack.ConnectionConfiguration;

public class XMPPTCPConnectionConfiguration extends ConnectionConfiguration {
    public static int DEFAULT_CONNECT_TIMEOUT;
    private final boolean compressionEnabled;
    private final int connectTimeout;

    public class Builder extends org.jivesoftware.smack.ConnectionConfiguration.Builder<Builder, XMPPTCPConnectionConfiguration> {
        private boolean compressionEnabled;
        private int connectTimeout;

        private Builder() {
            this.compressionEnabled = false;
            this.connectTimeout = XMPPTCPConnectionConfiguration.DEFAULT_CONNECT_TIMEOUT;
        }

        public Builder setCompressionEnabled(boolean z) {
            this.compressionEnabled = z;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        protected Builder getThis() {
            return this;
        }

        public XMPPTCPConnectionConfiguration build() {
            return new XMPPTCPConnectionConfiguration();
        }
    }

    static {
        DEFAULT_CONNECT_TIMEOUT = 30000;
    }

    private XMPPTCPConnectionConfiguration(Builder builder) {
        super(builder);
        this.compressionEnabled = builder.compressionEnabled;
        this.connectTimeout = builder.connectTimeout;
    }

    public boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public static Builder builder() {
        return new Builder();
    }
}
