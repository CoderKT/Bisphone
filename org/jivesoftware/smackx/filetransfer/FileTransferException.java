package org.jivesoftware.smackx.filetransfer;

import org.jivesoftware.smack.SmackException;

public abstract class FileTransferException extends SmackException {
    private static final long serialVersionUID = 1;

    public class NoAcceptableTransferMechanisms extends FileTransferException {
        private static final long serialVersionUID = 1;
    }

    public class NoStreamMethodsOfferedException extends FileTransferException {
        private static final long serialVersionUID = 1;
    }
}
