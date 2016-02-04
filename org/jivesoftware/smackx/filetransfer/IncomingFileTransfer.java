package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Error;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Status;

public class IncomingFileTransfer extends FileTransfer {
    private static final Logger LOGGER;
    private InputStream inputStream;
    private FileTransferRequest recieveRequest;

    /* renamed from: org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.1 */
    class C10661 implements Runnable {
        final /* synthetic */ File val$file;

        C10661(File file) {
            this.val$file = file;
        }

        public void run() {
            OutputStream fileOutputStream;
            Exception e;
            Exception exception;
            try {
                IncomingFileTransfer.this.inputStream = IncomingFileTransfer.this.negotiateStream();
                try {
                    fileOutputStream = new FileOutputStream(this.val$file);
                    try {
                        IncomingFileTransfer.this.setStatus(Status.in_progress);
                        IncomingFileTransfer.this.writeToStream(IncomingFileTransfer.this.inputStream, fileOutputStream);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        IncomingFileTransfer.this.setStatus(Status.error);
                        IncomingFileTransfer.this.setError(Error.bad_file);
                        IncomingFileTransfer.this.setException(e);
                        if (IncomingFileTransfer.this.getStatus().equals(Status.in_progress)) {
                            IncomingFileTransfer.this.setStatus(Status.complete);
                        }
                        if (IncomingFileTransfer.this.inputStream != null) {
                            try {
                                IncomingFileTransfer.this.inputStream.close();
                            } catch (Throwable e3) {
                                IncomingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", e3);
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                IncomingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", e4);
                                return;
                            }
                        }
                    } catch (IOException e5) {
                        e = e5;
                        IncomingFileTransfer.this.setStatus(Status.error);
                        IncomingFileTransfer.this.setError(Error.stream);
                        IncomingFileTransfer.this.setException(e);
                        if (IncomingFileTransfer.this.getStatus().equals(Status.in_progress)) {
                            IncomingFileTransfer.this.setStatus(Status.complete);
                        }
                        if (IncomingFileTransfer.this.inputStream != null) {
                            IncomingFileTransfer.this.inputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                } catch (Exception e6) {
                    exception = e6;
                    fileOutputStream = null;
                    e = exception;
                    IncomingFileTransfer.this.setStatus(Status.error);
                    IncomingFileTransfer.this.setError(Error.bad_file);
                    IncomingFileTransfer.this.setException(e);
                    if (IncomingFileTransfer.this.getStatus().equals(Status.in_progress)) {
                        IncomingFileTransfer.this.setStatus(Status.complete);
                    }
                    if (IncomingFileTransfer.this.inputStream != null) {
                        IncomingFileTransfer.this.inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Exception e62) {
                    exception = e62;
                    fileOutputStream = null;
                    e = exception;
                    IncomingFileTransfer.this.setStatus(Status.error);
                    IncomingFileTransfer.this.setError(Error.stream);
                    IncomingFileTransfer.this.setException(e);
                    if (IncomingFileTransfer.this.getStatus().equals(Status.in_progress)) {
                        IncomingFileTransfer.this.setStatus(Status.complete);
                    }
                    if (IncomingFileTransfer.this.inputStream != null) {
                        IncomingFileTransfer.this.inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
                if (IncomingFileTransfer.this.getStatus().equals(Status.in_progress)) {
                    IncomingFileTransfer.this.setStatus(Status.complete);
                }
                if (IncomingFileTransfer.this.inputStream != null) {
                    IncomingFileTransfer.this.inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e622) {
                IncomingFileTransfer.this.setStatus(Status.error);
                IncomingFileTransfer.this.setException(e622);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.2 */
    class C10672 implements Callable<InputStream> {
        final /* synthetic */ StreamNegotiator val$streamNegotiator;

        C10672(StreamNegotiator streamNegotiator) {
            this.val$streamNegotiator = streamNegotiator;
        }

        public InputStream call() {
            return this.val$streamNegotiator.createIncomingStream(IncomingFileTransfer.this.recieveRequest.getStreamInitiation());
        }
    }

    static {
        LOGGER = Logger.getLogger(IncomingFileTransfer.class.getName());
    }

    protected IncomingFileTransfer(FileTransferRequest fileTransferRequest, FileTransferNegotiator fileTransferNegotiator) {
        super(fileTransferRequest.getRequestor(), fileTransferRequest.getStreamID(), fileTransferNegotiator);
        this.recieveRequest = fileTransferRequest;
    }

    public InputStream recieveFile() {
        if (this.inputStream != null) {
            throw new IllegalStateException("Transfer already negotiated!");
        }
        try {
            this.inputStream = negotiateStream();
            return this.inputStream;
        } catch (Exception e) {
            setException(e);
            throw e;
        }
    }

    public void recieveFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.canWrite()) {
            new Thread(new C10661(file), "File Transfer " + this.streamID).start();
            return;
        }
        throw new IllegalArgumentException("Cannot write to provided file");
    }

    private InputStream negotiateStream() {
        setStatus(Status.negotiating_transfer);
        StreamNegotiator selectStreamNegotiator = this.negotiator.selectStreamNegotiator(this.recieveRequest);
        setStatus(Status.negotiating_stream);
        FutureTask futureTask = new FutureTask(new C10672(selectStreamNegotiator));
        futureTask.run();
        try {
            InputStream inputStream = (InputStream) futureTask.get(15, TimeUnit.SECONDS);
            futureTask.cancel(true);
            setStatus(Status.negotiated);
            return inputStream;
        } catch (Throwable e) {
            throw new SmackException("Interruption while executing", e);
        } catch (Throwable e2) {
            throw new SmackException("Error in execution", e2);
        } catch (Throwable e22) {
            throw new SmackException("Request timed out", e22);
        } catch (Throwable th) {
            futureTask.cancel(true);
        }
    }

    public void cancel() {
        setStatus(Status.cancelled);
    }
}
