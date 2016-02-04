package org.jivesoftware.smackx.filetransfer;

public abstract class FileTransfer {
    private static final int BUFFER_SIZE = 8192;
    protected long amountWritten;
    private Error error;
    private Exception exception;
    private String fileName;
    private String filePath;
    private long fileSize;
    protected FileTransferNegotiator negotiator;
    private String peer;
    private Status status;
    private final Object statusMonitor;
    protected String streamID;

    public enum Error {
        none("No error"),
        not_acceptable("The peer did not find any of the provided stream mechanisms acceptable."),
        bad_file("The provided file to transfer does not exist or could not be read."),
        no_response("The remote user did not respond or the connection timed out."),
        connection("An error occured over the socket connected to send the file."),
        stream("An error occured while sending or recieving the file.");
        
        private final String msg;

        private Error(String str) {
            this.msg = str;
        }

        public String getMessage() {
            return this.msg;
        }

        public String toString() {
            return this.msg;
        }
    }

    public enum Status {
        error("Error"),
        initial("Initial"),
        negotiating_transfer("Negotiating Transfer"),
        refused("Refused"),
        negotiating_stream("Negotiating Stream"),
        negotiated("Negotiated"),
        in_progress("In Progress"),
        complete("Complete"),
        cancelled("Cancelled");
        
        private String status;

        private Status(String str) {
            this.status = str;
        }

        public String toString() {
            return this.status;
        }
    }

    public abstract void cancel();

    protected FileTransfer(String str, String str2, FileTransferNegotiator fileTransferNegotiator) {
        this.status = Status.initial;
        this.statusMonitor = new Object();
        this.amountWritten = -1;
        this.peer = str;
        this.streamID = str2;
        this.negotiator = fileTransferNegotiator;
    }

    protected void setFileInfo(String str, long j) {
        this.fileName = str;
        this.fileSize = j;
    }

    protected void setFileInfo(String str, String str2, long j) {
        this.filePath = str;
        this.fileName = str2;
        this.fileSize = j;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getPeer() {
        return this.peer;
    }

    public double getProgress() {
        if (this.amountWritten <= 0 || this.fileSize <= 0) {
            return 0.0d;
        }
        return ((double) this.amountWritten) / ((double) this.fileSize);
    }

    public boolean isDone() {
        return this.status == Status.cancelled || this.status == Status.error || this.status == Status.complete || this.status == Status.refused;
    }

    public Status getStatus() {
        return this.status;
    }

    protected void setError(Error error) {
        this.error = error;
    }

    public Error getError() {
        return this.error;
    }

    public Exception getException() {
        return this.exception;
    }

    public String getStreamID() {
        return this.streamID;
    }

    protected void setException(Exception exception) {
        this.exception = exception;
    }

    protected void setStatus(Status status) {
        synchronized (this.statusMonitor) {
            this.status = status;
        }
    }

    protected boolean updateStatus(Status status, Status status2) {
        boolean z;
        synchronized (this.statusMonitor) {
            if (status != this.status) {
                z = false;
            } else {
                this.status = status2;
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void writeToStream(java.io.InputStream r7, java.io.OutputStream r8) {
        /*
        r6 = this;
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r0];
        r2 = 0;
        r6.amountWritten = r2;
    L_0x0008:
        r1 = r7.read(r0);
        if (r1 <= 0) goto L_0x0025;
    L_0x000e:
        r2 = r6.getStatus();
        r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.cancelled;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0025;
    L_0x001a:
        r2 = 0;
        r8.write(r0, r2, r1);
        r2 = r6.amountWritten;
        r4 = (long) r1;
        r2 = r2 + r4;
        r6.amountWritten = r2;
        goto L_0x0008;
    L_0x0025:
        r0 = r6.getStatus();
        r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.cancelled;
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x004a;
    L_0x0031:
        r0 = r6.getError();
        r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Error.none;
        if (r0 != r1) goto L_0x004a;
    L_0x0039:
        r0 = r6.amountWritten;
        r2 = r6.fileSize;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x004a;
    L_0x0041:
        r0 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error;
        r6.setStatus(r0);
        r0 = org.jivesoftware.smackx.filetransfer.FileTransfer.Error.connection;
        r6.error = r0;
    L_0x004a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.filetransfer.FileTransfer.writeToStream(java.io.InputStream, java.io.OutputStream):void");
    }

    public long getAmountWritten() {
        return this.amountWritten;
    }
}
