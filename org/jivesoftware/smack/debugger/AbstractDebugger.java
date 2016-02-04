package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.ObservableReader;
import org.jivesoftware.smack.util.ObservableWriter;
import org.jivesoftware.smack.util.ReaderListener;
import org.jivesoftware.smack.util.WriterListener;
import org.jxmpp.util.XmppStringUtils;

public abstract class AbstractDebugger implements SmackDebugger {
    public static boolean printInterpreted;
    private final ConnectionListener connListener;
    private final XMPPConnection connection;
    private final StanzaListener listener;
    private ObservableReader reader;
    private final ReaderListener readerListener;
    private ObservableWriter writer;
    private final WriterListener writerListener;

    /* renamed from: org.jivesoftware.smack.debugger.AbstractDebugger.1 */
    class C10001 implements ReaderListener {
        final /* synthetic */ XMPPConnection val$connection;

        C10001(XMPPConnection xMPPConnection) {
            this.val$connection = xMPPConnection;
        }

        public void read(String str) {
            AbstractDebugger.this.log("RECV (" + this.val$connection.getConnectionCounter() + "): " + str);
        }
    }

    /* renamed from: org.jivesoftware.smack.debugger.AbstractDebugger.2 */
    class C10012 implements WriterListener {
        final /* synthetic */ XMPPConnection val$connection;

        C10012(XMPPConnection xMPPConnection) {
            this.val$connection = xMPPConnection;
        }

        public void write(String str) {
            AbstractDebugger.this.log("SENT (" + this.val$connection.getConnectionCounter() + "): " + str);
        }
    }

    /* renamed from: org.jivesoftware.smack.debugger.AbstractDebugger.3 */
    class C10023 implements StanzaListener {
        final /* synthetic */ XMPPConnection val$connection;

        C10023(XMPPConnection xMPPConnection) {
            this.val$connection = xMPPConnection;
        }

        public void processPacket(Stanza stanza) {
            if (AbstractDebugger.printInterpreted) {
                AbstractDebugger.this.log("RCV PKT (" + this.val$connection.getConnectionCounter() + "): " + stanza.toXML());
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.debugger.AbstractDebugger.4 */
    class C10034 implements ConnectionListener {
        final /* synthetic */ XMPPConnection val$connection;

        C10034(XMPPConnection xMPPConnection) {
            this.val$connection = xMPPConnection;
        }

        public void connected(XMPPConnection xMPPConnection) {
            AbstractDebugger.this.log("XMPPConnection connected (" + xMPPConnection.getConnectionCounter() + ")");
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            String str = "XMPPConnection authenticated (" + xMPPConnection.getConnectionCounter() + ")";
            if (z) {
                str = str + " and resumed";
            }
            AbstractDebugger.this.log(str);
        }

        public void connectionClosed() {
            AbstractDebugger.this.log("XMPPConnection closed (" + this.val$connection.getConnectionCounter() + ")");
        }

        public void connectionClosedOnError(Exception exception) {
            AbstractDebugger.this.log("XMPPConnection closed due to an exception (" + this.val$connection.getConnectionCounter() + ")");
            exception.printStackTrace();
        }

        public void reconnectionFailed(Exception exception) {
            AbstractDebugger.this.log("Reconnection failed due to an exception (" + this.val$connection.getConnectionCounter() + ")");
            exception.printStackTrace();
        }

        public void reconnectionSuccessful() {
            AbstractDebugger.this.log("XMPPConnection reconnected (" + this.val$connection.getConnectionCounter() + ")");
        }

        public void reconnectingIn(int i) {
            AbstractDebugger.this.log("XMPPConnection (" + this.val$connection.getConnectionCounter() + ") will reconnect in " + i);
        }
    }

    protected abstract void log(String str);

    static {
        printInterpreted = false;
    }

    public AbstractDebugger(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        this.connection = xMPPConnection;
        this.reader = new ObservableReader(reader);
        this.readerListener = new C10001(xMPPConnection);
        this.reader.addReaderListener(this.readerListener);
        this.writer = new ObservableWriter(writer);
        this.writerListener = new C10012(xMPPConnection);
        this.writer.addWriterListener(this.writerListener);
        this.listener = new C10023(xMPPConnection);
        this.connListener = new C10034(xMPPConnection);
    }

    public Reader newConnectionReader(Reader reader) {
        this.reader.removeReaderListener(this.readerListener);
        ObservableReader observableReader = new ObservableReader(reader);
        observableReader.addReaderListener(this.readerListener);
        this.reader = observableReader;
        return this.reader;
    }

    public Writer newConnectionWriter(Writer writer) {
        this.writer.removeWriterListener(this.writerListener);
        ObservableWriter observableWriter = new ObservableWriter(writer);
        observableWriter.addWriterListener(this.writerListener);
        this.writer = observableWriter;
        return this.writer;
    }

    public void userHasLogged(String str) {
        String a = XmppStringUtils.m13441a(str);
        boolean equals = "".equals(a);
        StringBuilder append = new StringBuilder().append("User logged (").append(this.connection.getConnectionCounter()).append("): ");
        if (equals) {
            a = "";
        }
        log(append.append(a).append("@").append(this.connection.getServiceName()).append(":").append(this.connection.getPort()).toString() + "/" + XmppStringUtils.m13446c(str));
        this.connection.addConnectionListener(this.connListener);
    }

    public Reader getReader() {
        return this.reader;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public StanzaListener getReaderListener() {
        return this.listener;
    }

    public StanzaListener getWriterListener() {
        return null;
    }
}
