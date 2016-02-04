package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.util.dns.HostAddress;

public class SmackException extends Exception {
    private static final long serialVersionUID = 1844674365368214457L;

    public class AlreadyConnectedException extends SmackException {
        private static final long serialVersionUID = 5011416918049135231L;

        public AlreadyConnectedException() {
            super("Client is already connected");
        }
    }

    public class AlreadyLoggedInException extends SmackException {
        private static final long serialVersionUID = 5011416918049935231L;

        public AlreadyLoggedInException() {
            super("Client is already logged in");
        }
    }

    public class ConnectionException extends SmackException {
        private static final long serialVersionUID = 1686944201672697996L;
        private final List<HostAddress> failedAddresses;

        public ConnectionException(Throwable th) {
            super(th);
            this.failedAddresses = new ArrayList(0);
        }

        private ConnectionException(String str, List<HostAddress> list) {
            super(str);
            this.failedAddresses = list;
        }

        public static ConnectionException from(List<HostAddress> list) {
            String str = ", ";
            StringBuilder stringBuilder = new StringBuilder("The following addresses failed: ");
            for (HostAddress errorMessage : list) {
                stringBuilder.append(errorMessage.getErrorMessage());
                stringBuilder.append(", ");
            }
            stringBuilder.setLength(stringBuilder.length() - ", ".length());
            return new ConnectionException(stringBuilder.toString(), list);
        }

        public List<HostAddress> getFailedAddresses() {
            return this.failedAddresses;
        }
    }

    public class FeatureNotSupportedException extends SmackException {
        private static final long serialVersionUID = 4713404802621452016L;
        private final String feature;
        private final String jid;

        public FeatureNotSupportedException(String str) {
            this(str, null);
        }

        public FeatureNotSupportedException(String str, String str2) {
            super(str + " not supported" + (str2 == null ? "" : " by '" + str2 + "'"));
            this.jid = str2;
            this.feature = str;
        }

        public String getFeature() {
            return this.feature;
        }

        public String getJid() {
            return this.jid;
        }
    }

    public class IllegalStateChangeException extends SmackException {
        private static final long serialVersionUID = -1766023961577168927L;
    }

    public class NoResponseException extends SmackException {
        private static final long serialVersionUID = -6523363748984543636L;
        private final StanzaFilter filter;

        private NoResponseException(String str, StanzaFilter stanzaFilter) {
            super(str);
            this.filter = stanzaFilter;
        }

        public StanzaFilter getFilter() {
            return this.filter;
        }

        public static NoResponseException newWith(XMPPConnection xMPPConnection) {
            return newWith(xMPPConnection, (StanzaFilter) null);
        }

        public static NoResponseException newWith(XMPPConnection xMPPConnection, PacketCollector packetCollector) {
            return newWith(xMPPConnection, packetCollector.getStanzaFilter());
        }

        public static NoResponseException newWith(XMPPConnection xMPPConnection, StanzaFilter stanzaFilter) {
            long packetReplyTimeout = xMPPConnection.getPacketReplyTimeout();
            StringBuilder stringBuilder = new StringBuilder(256);
            stringBuilder.append("No response received within reply timeout. Timeout was " + packetReplyTimeout + "ms (~" + (packetReplyTimeout / 1000) + "s). Used filter: ");
            if (stanzaFilter != null) {
                stringBuilder.append(stanzaFilter.toString());
            } else {
                stringBuilder.append("No filter used or filter was 'null'");
            }
            stringBuilder.append('.');
            return new NoResponseException(stringBuilder.toString(), stanzaFilter);
        }
    }

    public class NotConnectedException extends SmackException {
        private static final long serialVersionUID = 9197980400776001173L;

        public NotConnectedException() {
            this(null);
        }

        public NotConnectedException(String str) {
            super("Client is not, or no longer, connected." + (str != null ? ' ' + str : ""));
        }
    }

    public class NotLoggedInException extends SmackException {
        private static final long serialVersionUID = 3216216839100019278L;

        public NotLoggedInException() {
            super("Client is not logged in");
        }
    }

    public class ResourceBindingNotOfferedException extends SmackException {
        private static final long serialVersionUID = 2346934138253437571L;

        public ResourceBindingNotOfferedException() {
            super("Resource binding was not offered by server");
        }
    }

    public class SecurityNotPossibleException extends SmackException {
        private static final long serialVersionUID = -6836090872690331336L;

        public SecurityNotPossibleException(String str) {
            super(str);
        }
    }

    public abstract class SecurityRequiredException extends SmackException {
        private static final long serialVersionUID = 384291845029773545L;

        public SecurityRequiredException(String str) {
            super(str);
        }
    }

    public class SecurityRequiredByClientException extends SecurityRequiredException {
        private static final long serialVersionUID = 2395325821201543159L;

        public SecurityRequiredByClientException() {
            super("SSL/TLS required by client but not supported by server");
        }
    }

    public class SecurityRequiredByServerException extends SecurityRequiredException {
        private static final long serialVersionUID = 8268148813117631819L;

        public SecurityRequiredByServerException() {
            super("SSL/TLS required by server but disabled in client");
        }
    }

    public SmackException(Throwable th) {
        super(th);
    }

    public SmackException(String str) {
        super(str);
    }

    public SmackException(String str, Throwable th) {
        super(str, th);
    }

    protected SmackException() {
    }
}
