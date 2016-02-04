package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.XMPPError;

public abstract class XMPPException extends Exception {
    private static final long serialVersionUID = 6881651633890968625L;

    public class StreamErrorException extends XMPPException {
        private static final long serialVersionUID = 3400556867134848886L;
        private final StreamError streamError;

        public StreamErrorException(StreamError streamError) {
            super(streamError.getCondition().toString() + " You can read more about the meaning of this stream error at http://xmpp.org/rfcs/rfc6120.html#streams-error-conditions\n" + streamError.toString());
            this.streamError = streamError;
        }

        public StreamError getStreamError() {
            return this.streamError;
        }
    }

    public class XMPPErrorException extends XMPPException {
        private static final long serialVersionUID = 212790389529249604L;
        private final XMPPError error;

        public XMPPErrorException(XMPPError xMPPError) {
            this.error = xMPPError;
        }

        public XMPPErrorException(String str, XMPPError xMPPError, Throwable th) {
            super(str, th);
            this.error = xMPPError;
        }

        public XMPPErrorException(String str, XMPPError xMPPError) {
            super(str);
            this.error = xMPPError;
        }

        public XMPPError getXMPPError() {
            return this.error;
        }

        public String getMessage() {
            String message = super.getMessage();
            return message != null ? message : this.error.toString();
        }

        public static void ifHasErrorThenThrow(Stanza stanza) {
            XMPPError error = stanza.getError();
            if (error != null) {
                throw new XMPPErrorException(error);
            }
        }
    }

    protected XMPPException() {
    }

    protected XMPPException(String str) {
        super(str);
    }

    protected XMPPException(String str, Throwable th) {
        super(str, th);
    }
}
