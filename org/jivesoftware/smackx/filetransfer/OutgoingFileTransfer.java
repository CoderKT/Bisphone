package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException.IllegalStateChangeException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Error;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Status;
import se.emilsjolander.stickylistheaders.C1128R;

public class OutgoingFileTransfer extends FileTransfer {
    private static final Logger LOGGER;
    private static int RESPONSE_TIMEOUT;
    private NegotiationProgress callback;
    private String initiator;
    private OutputStream outputStream;
    private Thread transferThread;

    /* renamed from: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.1 */
    class C10681 implements Runnable {
        final /* synthetic */ String val$description;
        final /* synthetic */ String val$fileName;
        final /* synthetic */ long val$fileSize;
        final /* synthetic */ NegotiationProgress val$progress;

        C10681(String str, long j, String str2, NegotiationProgress negotiationProgress) {
            this.val$fileName = str;
            this.val$fileSize = j;
            this.val$description = str2;
            this.val$progress = negotiationProgress;
        }

        public void run() {
            try {
                OutgoingFileTransfer.this.outputStream = OutgoingFileTransfer.this.negotiateStream(this.val$fileName, this.val$fileSize, this.val$description);
                this.val$progress.outputStreamEstablished(OutgoingFileTransfer.this.outputStream);
            } catch (XMPPErrorException e) {
                OutgoingFileTransfer.this.handleXMPPException(e);
            } catch (Exception e2) {
                OutgoingFileTransfer.this.setException(e2);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.2 */
    class C10692 implements Runnable {
        final /* synthetic */ String val$description;
        final /* synthetic */ File val$file;

        C10692(File file, String str) {
            this.val$file = file;
            this.val$description = str;
        }

        public void run() {
            Exception e;
            InputStream fileInputStream;
            Throwable e2;
            try {
                OutgoingFileTransfer.this.outputStream = OutgoingFileTransfer.this.negotiateStream(this.val$file.getName(), this.val$file.length(), this.val$description);
            } catch (XMPPErrorException e3) {
                OutgoingFileTransfer.this.handleXMPPException(e3);
                return;
            } catch (Exception e4) {
                OutgoingFileTransfer.this.setException(e4);
            }
            if (OutgoingFileTransfer.this.outputStream != null && OutgoingFileTransfer.this.updateStatus(Status.negotiated, Status.in_progress)) {
                try {
                    fileInputStream = new FileInputStream(this.val$file);
                    try {
                        OutgoingFileTransfer.this.writeToStream(fileInputStream, OutgoingFileTransfer.this.outputStream);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e22) {
                                OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", e22);
                            }
                        }
                        try {
                            OutgoingFileTransfer.this.outputStream.close();
                        } catch (Throwable e222) {
                            OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", e222);
                        }
                    } catch (FileNotFoundException e5) {
                        e4 = e5;
                        try {
                            OutgoingFileTransfer.this.setStatus(Status.error);
                            OutgoingFileTransfer.this.setError(Error.bad_file);
                            OutgoingFileTransfer.this.setException(e4);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e2222) {
                                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", e2222);
                                }
                            }
                            try {
                                OutgoingFileTransfer.this.outputStream.close();
                            } catch (Throwable e22222) {
                                OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", e22222);
                            }
                            OutgoingFileTransfer.this.updateStatus(Status.in_progress, Status.complete);
                        } catch (Throwable th) {
                            e22222 = th;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e6) {
                                    OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", e6);
                                }
                            }
                            try {
                                OutgoingFileTransfer.this.outputStream.close();
                            } catch (Throwable e62) {
                                OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", e62);
                            }
                            throw e22222;
                        }
                    } catch (IOException e7) {
                        e4 = e7;
                        OutgoingFileTransfer.this.setStatus(Status.error);
                        OutgoingFileTransfer.this.setException(e4);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e222222) {
                                OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing input stream", e222222);
                            }
                        }
                        try {
                            OutgoingFileTransfer.this.outputStream.close();
                        } catch (Throwable e2222222) {
                            OutgoingFileTransfer.LOGGER.log(Level.WARNING, "Closing output stream", e2222222);
                        }
                        OutgoingFileTransfer.this.updateStatus(Status.in_progress, Status.complete);
                    }
                } catch (FileNotFoundException e8) {
                    e4 = e8;
                    fileInputStream = null;
                    OutgoingFileTransfer.this.setStatus(Status.error);
                    OutgoingFileTransfer.this.setError(Error.bad_file);
                    OutgoingFileTransfer.this.setException(e4);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    OutgoingFileTransfer.this.outputStream.close();
                    OutgoingFileTransfer.this.updateStatus(Status.in_progress, Status.complete);
                } catch (IOException e9) {
                    e4 = e9;
                    fileInputStream = null;
                    OutgoingFileTransfer.this.setStatus(Status.error);
                    OutgoingFileTransfer.this.setException(e4);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    OutgoingFileTransfer.this.outputStream.close();
                    OutgoingFileTransfer.this.updateStatus(Status.in_progress, Status.complete);
                } catch (Throwable th2) {
                    e2222222 = th2;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    OutgoingFileTransfer.this.outputStream.close();
                    throw e2222222;
                }
                OutgoingFileTransfer.this.updateStatus(Status.in_progress, Status.complete);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.3 */
    class C10703 implements Runnable {
        final /* synthetic */ String val$description;
        final /* synthetic */ String val$fileName;
        final /* synthetic */ long val$fileSize;
        final /* synthetic */ InputStream val$in;

        public void run() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x007c in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:58)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r6 = this;
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r2 = r6.val$fileName;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r4 = r6.val$fileSize;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r3 = r6.val$description;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r1 = r1.negotiateStream(r2, r4, r3);	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
            r0.outputStream = r1;	 Catch:{ XMPPErrorException -> 0x001a, Exception -> 0x0021 }
        L_0x0011:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r0 = r0.outputStream;
            if (r0 != 0) goto L_0x0028;
        L_0x0019:
            return;
        L_0x001a:
            r0 = move-exception;
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r1.handleXMPPException(r0);
            goto L_0x0019;
        L_0x0021:
            r0 = move-exception;
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r1.setException(r0);
            goto L_0x0011;
        L_0x0028:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.negotiated;
            r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress;
            r0 = r0.updateStatus(r1, r2);
            if (r0 == 0) goto L_0x0019;
        L_0x0034:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r1 = r6.val$in;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r2 = r2.outputStream;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r0.writeToStream(r1, r2);	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r0 = r6.val$in;	 Catch:{ IOException -> 0x00b0 }
            if (r0 == 0) goto L_0x004a;	 Catch:{ IOException -> 0x00b0 }
        L_0x0045:
            r0 = r6.val$in;	 Catch:{ IOException -> 0x00b0 }
            r0.close();	 Catch:{ IOException -> 0x00b0 }
        L_0x004a:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x00b0 }
            r0 = r0.outputStream;	 Catch:{ IOException -> 0x00b0 }
            r0.flush();	 Catch:{ IOException -> 0x00b0 }
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x00b0 }
            r0 = r0.outputStream;	 Catch:{ IOException -> 0x00b0 }
            r0.close();	 Catch:{ IOException -> 0x00b0 }
        L_0x005c:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress;
            r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.complete;
            r0.updateStatus(r1, r2);
            goto L_0x0019;
        L_0x0066:
            r0 = move-exception;
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r1.setStatus(r2);	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r1.setException(r0);	 Catch:{ IOException -> 0x0066, all -> 0x0091 }
            r0 = r6.val$in;
            if (r0 == 0) goto L_0x007c;
        L_0x0077:
            r0 = r6.val$in;
            r0.close();
        L_0x007c:
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r0 = r0.outputStream;
            r0.flush();
            r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;
            r0 = r0.outputStream;
            r0.close();
            goto L_0x005c;
        L_0x008f:
            r0 = move-exception;
            goto L_0x005c;
        L_0x0091:
            r0 = move-exception;
            r1 = r6.val$in;	 Catch:{ IOException -> 0x00ae }
            if (r1 == 0) goto L_0x009b;	 Catch:{ IOException -> 0x00ae }
        L_0x0096:
            r1 = r6.val$in;	 Catch:{ IOException -> 0x00ae }
            r1.close();	 Catch:{ IOException -> 0x00ae }
        L_0x009b:
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x00ae }
            r1 = r1.outputStream;	 Catch:{ IOException -> 0x00ae }
            r1.flush();	 Catch:{ IOException -> 0x00ae }
            r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this;	 Catch:{ IOException -> 0x00ae }
            r1 = r1.outputStream;	 Catch:{ IOException -> 0x00ae }
            r1.close();	 Catch:{ IOException -> 0x00ae }
        L_0x00ad:
            throw r0;
        L_0x00ae:
            r1 = move-exception;
            goto L_0x00ad;
        L_0x00b0:
            r0 = move-exception;
            goto L_0x005c;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.3.run():void");
        }

        C10703(String str, long j, String str2, InputStream inputStream) {
            this.val$fileName = str;
            this.val$fileSize = j;
            this.val$description = str2;
            this.val$in = inputStream;
        }
    }

    /* renamed from: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.4 */
    /* synthetic */ class C10714 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition = new int[Condition.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.forbidden.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.bad_request.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public interface NegotiationProgress {
        void errorEstablishingStream(Exception exception);

        void outputStreamEstablished(OutputStream outputStream);

        void statusUpdated(Status status, Status status2);
    }

    static {
        LOGGER = Logger.getLogger(OutgoingFileTransfer.class.getName());
        RESPONSE_TIMEOUT = 60000;
    }

    public static int getResponseTimeout() {
        return RESPONSE_TIMEOUT;
    }

    public static void setResponseTimeout(int i) {
        RESPONSE_TIMEOUT = i;
    }

    protected OutgoingFileTransfer(String str, String str2, String str3, FileTransferNegotiator fileTransferNegotiator) {
        super(str2, str3, fileTransferNegotiator);
        this.initiator = str;
    }

    protected void setOutputStream(OutputStream outputStream) {
        if (this.outputStream == null) {
            this.outputStream = outputStream;
        }
    }

    protected OutputStream getOutputStream() {
        if (getStatus().equals(Status.negotiated)) {
            return this.outputStream;
        }
        return null;
    }

    public synchronized OutputStream sendFile(String str, long j, String str2) {
        if (isDone() || this.outputStream != null) {
            throw new IllegalStateException("The negotation process has already been attempted on this file transfer");
        }
        try {
            setFileInfo(str, j);
            this.outputStream = negotiateStream(str, j, str2);
        } catch (XMPPErrorException e) {
            handleXMPPException(e);
            throw e;
        }
        return this.outputStream;
    }

    public synchronized void sendFile(String str, long j, String str2, NegotiationProgress negotiationProgress) {
        if (negotiationProgress == null) {
            throw new IllegalArgumentException("Callback progress cannot be null.");
        }
        checkTransferThread();
        if (isDone() || this.outputStream != null) {
            throw new IllegalStateException("The negotation process has already been attempted for this file transfer");
        }
        setFileInfo(str, j);
        this.callback = negotiationProgress;
        this.transferThread = new Thread(new C10681(str, j, str2, negotiationProgress), "File Transfer Negotiation " + this.streamID);
        this.transferThread.start();
    }

    private void checkTransferThread() {
        if ((this.transferThread != null && this.transferThread.isAlive()) || isDone()) {
            throw new IllegalStateException("File transfer in progress or has already completed.");
        }
    }

    public synchronized void sendFile(File file, String str) {
        checkTransferThread();
        if (file != null && file.exists() && file.canRead()) {
            setFileInfo(file.getAbsolutePath(), file.getName(), file.length());
            this.transferThread = new Thread(new C10692(file, str), "File Transfer " + this.streamID);
            this.transferThread.start();
        } else {
            throw new IllegalArgumentException("Could not read file");
        }
    }

    public synchronized void sendStream(InputStream inputStream, String str, long j, String str2) {
        checkTransferThread();
        setFileInfo(str, j);
        this.transferThread = new Thread(new C10703(str, j, str2, inputStream), "File Transfer " + this.streamID);
        this.transferThread.start();
    }

    private void handleXMPPException(XMPPErrorException xMPPErrorException) {
        XMPPError xMPPError = xMPPErrorException.getXMPPError();
        if (xMPPError != null) {
            switch (C10714.$SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[xMPPError.getCondition().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    setStatus(Status.refused);
                    return;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    setStatus(Status.error);
                    setError(Error.not_acceptable);
                    break;
                default:
                    setStatus(Status.error);
                    break;
            }
        }
        setException(xMPPErrorException);
    }

    public long getBytesSent() {
        return this.amountWritten;
    }

    private OutputStream negotiateStream(String str, long j, String str2) {
        if (updateStatus(Status.initial, Status.negotiating_transfer)) {
            StreamNegotiator negotiateOutgoingTransfer = this.negotiator.negotiateOutgoingTransfer(getPeer(), this.streamID, str, j, str2, RESPONSE_TIMEOUT);
            if (updateStatus(Status.negotiating_transfer, Status.negotiating_stream)) {
                this.outputStream = negotiateOutgoingTransfer.createOutgoingStream(this.streamID, this.initiator, getPeer());
                if (updateStatus(Status.negotiating_stream, Status.negotiated)) {
                    return this.outputStream;
                }
                throw new IllegalStateChangeException();
            }
            throw new IllegalStateChangeException();
        }
        throw new IllegalStateChangeException();
    }

    public void cancel() {
        setStatus(Status.cancelled);
    }

    protected boolean updateStatus(Status status, Status status2) {
        boolean updateStatus = super.updateStatus(status, status2);
        if (this.callback != null && updateStatus) {
            this.callback.statusUpdated(status, status2);
        }
        return updateStatus;
    }

    protected void setStatus(Status status) {
        Status status2 = getStatus();
        super.setStatus(status);
        if (this.callback != null) {
            this.callback.statusUpdated(status2, status);
        }
    }

    protected void setException(Exception exception) {
        super.setException(exception);
        if (this.callback != null) {
            this.callback.errorEstablishingStream(exception);
        }
    }
}
