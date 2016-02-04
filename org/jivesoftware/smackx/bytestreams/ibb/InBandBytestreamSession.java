package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import se.emilsjolander.stickylistheaders.C1128R;

public class InBandBytestreamSession implements BytestreamSession {
    private final Open byteStreamRequest;
    private boolean closeBothStreamsEnabled;
    private final XMPPConnection connection;
    private IBBInputStream inputStream;
    private boolean isClosed;
    private IBBOutputStream outputStream;
    private String remoteJID;

    /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.1 */
    /* synthetic */ class C10371 {
        static final /* synthetic */ int[] f8581xda8e09c8;

        static {
            f8581xda8e09c8 = new int[StanzaType.values().length];
            try {
                f8581xda8e09c8[StanzaType.IQ.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8581xda8e09c8[StanzaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class IBBDataPacketFilter implements StanzaFilter {
        private IBBDataPacketFilter() {
        }

        public boolean accept(Stanza stanza) {
            if (!stanza.getFrom().equalsIgnoreCase(InBandBytestreamSession.this.remoteJID)) {
                return false;
            }
            DataPacketExtension dataPacketExtension;
            if (stanza instanceof Data) {
                dataPacketExtension = ((Data) stanza).getDataPacketExtension();
            } else {
                dataPacketExtension = (DataPacketExtension) stanza.getExtension(DataPacketExtension.ELEMENT, Open.NAMESPACE);
                if (dataPacketExtension == null) {
                    return false;
                }
            }
            if (dataPacketExtension.getSessionID().equals(InBandBytestreamSession.this.byteStreamRequest.getSessionID())) {
                return true;
            }
            return false;
        }
    }

    abstract class IBBInputStream extends InputStream {
        private byte[] buffer;
        private int bufferPointer;
        private boolean closeInvoked;
        private final StanzaListener dataPacketListener;
        protected final BlockingQueue<DataPacketExtension> dataQueue;
        private boolean isClosed;
        private int readTimeout;
        private long seq;

        protected abstract StanzaFilter getDataPacketFilter();

        protected abstract StanzaListener getDataPacketListener();

        public IBBInputStream() {
            this.dataQueue = new LinkedBlockingQueue();
            this.bufferPointer = -1;
            this.seq = -1;
            this.isClosed = false;
            this.closeInvoked = false;
            this.readTimeout = 0;
            this.dataPacketListener = getDataPacketListener();
            InBandBytestreamSession.this.connection.addSyncStanzaListener(this.dataPacketListener, getDataPacketFilter());
        }

        public synchronized int read() {
            int i = -1;
            synchronized (this) {
                checkClosed();
                if ((this.bufferPointer != -1 && this.bufferPointer < this.buffer.length) || loadBuffer()) {
                    byte[] bArr = this.buffer;
                    int i2 = this.bufferPointer;
                    this.bufferPointer = i2 + 1;
                    i = bArr[i2] & 255;
                }
            }
            return i;
        }

        public synchronized int read(byte[] bArr, int i, int i2) {
            int i3 = -1;
            synchronized (this) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
                if (i >= 0) {
                    if (i <= bArr.length && i2 >= 0 && i + i2 <= bArr.length && i + i2 >= 0) {
                        if (i2 == 0) {
                            i3 = 0;
                        } else {
                            checkClosed();
                            if ((this.bufferPointer != -1 && this.bufferPointer < this.buffer.length) || loadBuffer()) {
                                i3 = this.buffer.length - this.bufferPointer;
                                if (i2 <= i3) {
                                    i3 = i2;
                                }
                                System.arraycopy(this.buffer, this.bufferPointer, bArr, i, i3);
                                this.bufferPointer += i3;
                            }
                        }
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            return i3;
        }

        public synchronized int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        private synchronized boolean loadBuffer() {
            boolean z;
            DataPacketExtension dataPacketExtension = null;
            try {
                if (this.readTimeout == 0) {
                    while (dataPacketExtension == null) {
                        if (this.isClosed && this.dataQueue.isEmpty()) {
                            z = false;
                            break;
                        }
                        dataPacketExtension = (DataPacketExtension) this.dataQueue.poll(1000, TimeUnit.MILLISECONDS);
                    }
                } else {
                    dataPacketExtension = (DataPacketExtension) this.dataQueue.poll((long) this.readTimeout, TimeUnit.MILLISECONDS);
                    if (dataPacketExtension == null) {
                        throw new SocketTimeoutException();
                    }
                }
                if (this.seq == 65535) {
                    this.seq = -1;
                }
                long seq = dataPacketExtension.getSeq();
                if (seq - 1 != this.seq) {
                    InBandBytestreamSession.this.close();
                    throw new IOException("Packets out of sequence");
                }
                this.seq = seq;
                this.buffer = dataPacketExtension.getDecodedData();
                this.bufferPointer = 0;
                z = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                z = false;
            }
            return z;
        }

        private void checkClosed() {
            if (this.closeInvoked) {
                this.dataQueue.clear();
                throw new IOException("Stream is closed");
            }
        }

        public boolean markSupported() {
            return false;
        }

        public void close() {
            if (!this.closeInvoked) {
                this.closeInvoked = true;
                InBandBytestreamSession.this.closeByLocal(true);
            }
        }

        private void closeInternal() {
            if (!this.isClosed) {
                this.isClosed = true;
            }
        }

        private void cleanup() {
            InBandBytestreamSession.this.connection.removeSyncStanzaListener(this.dataPacketListener);
        }
    }

    abstract class IBBOutputStream extends OutputStream {
        protected final byte[] buffer;
        protected int bufferPointer;
        protected boolean isClosed;
        protected long seq;

        protected abstract void writeToXML(DataPacketExtension dataPacketExtension);

        public IBBOutputStream() {
            this.bufferPointer = 0;
            this.seq = 0;
            this.isClosed = false;
            this.buffer = new byte[InBandBytestreamSession.this.byteStreamRequest.getBlockSize()];
        }

        public synchronized void write(int i) {
            if (this.isClosed) {
                throw new IOException("Stream is closed");
            }
            if (this.bufferPointer >= this.buffer.length) {
                flushBuffer();
            }
            byte[] bArr = this.buffer;
            int i2 = this.bufferPointer;
            this.bufferPointer = i2 + 1;
            bArr[i2] = (byte) i;
        }

        public synchronized void write(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            }
            if (i >= 0) {
                if (i <= bArr.length && i2 >= 0 && i + i2 <= bArr.length && i + i2 >= 0) {
                    if (i2 != 0) {
                        if (this.isClosed) {
                            throw new IOException("Stream is closed");
                        } else if (i2 >= this.buffer.length) {
                            writeOut(bArr, i, this.buffer.length);
                            write(bArr, this.buffer.length + i, i2 - this.buffer.length);
                        } else {
                            writeOut(bArr, i, i2);
                        }
                    }
                }
            }
            throw new IndexOutOfBoundsException();
        }

        public synchronized void write(byte[] bArr) {
            write(bArr, 0, bArr.length);
        }

        private synchronized void writeOut(byte[] bArr, int i, int i2) {
            if (this.isClosed) {
                throw new IOException("Stream is closed");
            }
            int i3 = 0;
            if (i2 > this.buffer.length - this.bufferPointer) {
                i3 = this.buffer.length - this.bufferPointer;
                System.arraycopy(bArr, i, this.buffer, this.bufferPointer, i3);
                this.bufferPointer += i3;
                flushBuffer();
            }
            System.arraycopy(bArr, i + i3, this.buffer, this.bufferPointer, i2 - i3);
            this.bufferPointer = (i2 - i3) + this.bufferPointer;
        }

        public synchronized void flush() {
            if (this.isClosed) {
                throw new IOException("Stream is closed");
            }
            flushBuffer();
        }

        private synchronized void flushBuffer() {
            if (this.bufferPointer != 0) {
                try {
                    long j;
                    writeToXML(new DataPacketExtension(InBandBytestreamSession.this.byteStreamRequest.getSessionID(), this.seq, Base64.encodeToString(this.buffer, 0, this.bufferPointer)));
                    this.bufferPointer = 0;
                    if (this.seq + 1 == 65535) {
                        j = 0;
                    } else {
                        j = this.seq + 1;
                    }
                    this.seq = j;
                } catch (Throwable e) {
                    IOException iOException = new IOException();
                    iOException.initCause(e);
                    throw iOException;
                }
            }
        }

        public void close() {
            if (!this.isClosed) {
                InBandBytestreamSession.this.closeByLocal(false);
            }
        }

        protected void closeInternal(boolean z) {
            if (!this.isClosed) {
                this.isClosed = true;
                if (z) {
                    try {
                        flushBuffer();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    class IQIBBInputStream extends IBBInputStream {

        /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.IQIBBInputStream.1 */
        class C10381 implements StanzaListener {
            private long lastSequence;

            C10381() {
                this.lastSequence = -1;
            }

            public void processPacket(Stanza stanza) {
                DataPacketExtension dataPacketExtension = ((Data) stanza).getDataPacketExtension();
                if (dataPacketExtension.getSeq() <= this.lastSequence) {
                    InBandBytestreamSession.this.connection.sendStanza(IQ.createErrorResponse((IQ) stanza, new XMPPError(Condition.unexpected_request)));
                } else if (dataPacketExtension.getDecodedData() == null) {
                    InBandBytestreamSession.this.connection.sendStanza(IQ.createErrorResponse((IQ) stanza, new XMPPError(Condition.bad_request)));
                } else {
                    IQIBBInputStream.this.dataQueue.offer(dataPacketExtension);
                    InBandBytestreamSession.this.connection.sendStanza(IQ.createResultIQ((IQ) stanza));
                    this.lastSequence = dataPacketExtension.getSeq();
                    if (this.lastSequence == 65535) {
                        this.lastSequence = -1;
                    }
                }
            }
        }

        private IQIBBInputStream() {
            super();
        }

        protected StanzaListener getDataPacketListener() {
            return new C10381();
        }

        protected StanzaFilter getDataPacketFilter() {
            return new AndFilter(new StanzaTypeFilter(Data.class), new IBBDataPacketFilter(null));
        }
    }

    class IQIBBOutputStream extends IBBOutputStream {
        private IQIBBOutputStream() {
            super();
        }

        protected synchronized void writeToXML(DataPacketExtension dataPacketExtension) {
            IQ data = new Data(dataPacketExtension);
            data.setTo(InBandBytestreamSession.this.remoteJID);
            try {
                InBandBytestreamSession.this.connection.createPacketCollectorAndSend(data).nextResultOrThrow();
            } catch (Throwable e) {
                if (!this.isClosed) {
                    InBandBytestreamSession.this.close();
                    IOException iOException = new IOException();
                    iOException.initCause(e);
                    throw iOException;
                }
            }
        }
    }

    class MessageIBBInputStream extends IBBInputStream {

        /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.MessageIBBInputStream.1 */
        class C10391 implements StanzaListener {
            C10391() {
            }

            public void processPacket(Stanza stanza) {
                DataPacketExtension dataPacketExtension = (DataPacketExtension) stanza.getExtension(DataPacketExtension.ELEMENT, Open.NAMESPACE);
                if (dataPacketExtension.getDecodedData() != null) {
                    MessageIBBInputStream.this.dataQueue.offer(dataPacketExtension);
                }
            }
        }

        private MessageIBBInputStream() {
            super();
        }

        protected StanzaListener getDataPacketListener() {
            return new C10391();
        }

        protected StanzaFilter getDataPacketFilter() {
            return new AndFilter(new StanzaTypeFilter(Message.class), new IBBDataPacketFilter(null));
        }
    }

    class MessageIBBOutputStream extends IBBOutputStream {
        private MessageIBBOutputStream() {
            super();
        }

        protected synchronized void writeToXML(DataPacketExtension dataPacketExtension) {
            Stanza message = new Message(InBandBytestreamSession.this.remoteJID);
            message.addExtension(dataPacketExtension);
            InBandBytestreamSession.this.connection.sendStanza(message);
        }
    }

    protected InBandBytestreamSession(XMPPConnection xMPPConnection, Open open, String str) {
        this.closeBothStreamsEnabled = false;
        this.isClosed = false;
        this.connection = xMPPConnection;
        this.byteStreamRequest = open;
        this.remoteJID = str;
        switch (C10371.f8581xda8e09c8[open.getStanza().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.inputStream = new IQIBBInputStream();
                this.outputStream = new IQIBBOutputStream();
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.inputStream = new MessageIBBInputStream();
                this.outputStream = new MessageIBBOutputStream();
            default:
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public int getReadTimeout() {
        return this.inputStream.readTimeout;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Timeout must be >= 0");
        }
        this.inputStream.readTimeout = i;
    }

    public boolean isCloseBothStreamsEnabled() {
        return this.closeBothStreamsEnabled;
    }

    public void setCloseBothStreamsEnabled(boolean z) {
        this.closeBothStreamsEnabled = z;
    }

    public void close() {
        closeByLocal(true);
        closeByLocal(false);
    }

    protected void closeByPeer(Close close) {
        this.inputStream.closeInternal();
        this.inputStream.cleanup();
        this.outputStream.closeInternal(false);
        this.connection.sendStanza(IQ.createResultIQ(close));
    }

    protected synchronized void closeByLocal(boolean z) {
        if (!this.isClosed) {
            if (this.closeBothStreamsEnabled) {
                this.inputStream.closeInternal();
                this.outputStream.closeInternal(true);
            } else if (z) {
                this.inputStream.closeInternal();
            } else {
                this.outputStream.closeInternal(true);
            }
            if (this.inputStream.isClosed && this.outputStream.isClosed) {
                this.isClosed = true;
                IQ close = new Close(this.byteStreamRequest.getSessionID());
                close.setTo(this.remoteJID);
                try {
                    this.connection.createPacketCollectorAndSend(close).nextResultOrThrow();
                    this.inputStream.cleanup();
                    InBandBytestreamManager.getByteStreamManager(this.connection).getSessions().remove(this);
                } catch (Throwable e) {
                    IOException iOException = new IOException();
                    iOException.initCause(e);
                    throw iOException;
                }
            }
        }
    }

    public void processIQPacket(Data data) {
        this.inputStream.dataPacketListener.processPacket(data);
    }
}
