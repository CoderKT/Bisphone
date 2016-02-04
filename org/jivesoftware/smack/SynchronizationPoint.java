package org.jivesoftware.smack;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.TopLevelStreamElement;
import se.emilsjolander.stickylistheaders.C1128R;

public class SynchronizationPoint<E extends Exception> {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final Logger LOGGER;
    private final Condition condition;
    private final AbstractXMPPConnection connection;
    private final Lock connectionLock;
    private E failureException;
    private State state;

    /* renamed from: org.jivesoftware.smack.SynchronizationPoint.1 */
    /* synthetic */ class C09951 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State;

        static {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State = new int[State.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Failure.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Initial.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.NoResponse.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.RequestSent.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    enum State {
        Initial,
        RequestSent,
        NoResponse,
        Success,
        Failure
    }

    static {
        $assertionsDisabled = !SynchronizationPoint.class.desiredAssertionStatus();
        LOGGER = Logger.getLogger(SynchronizationPoint.class.getName());
    }

    public SynchronizationPoint(AbstractXMPPConnection abstractXMPPConnection) {
        this.connection = abstractXMPPConnection;
        this.connectionLock = abstractXMPPConnection.getConnectionLock();
        this.condition = abstractXMPPConnection.getConnectionLock().newCondition();
        init();
    }

    public void init() {
        this.connectionLock.lock();
        this.state = State.Initial;
        this.failureException = null;
        this.connectionLock.unlock();
    }

    public void sendAndWaitForResponse(TopLevelStreamElement topLevelStreamElement) {
        if ($assertionsDisabled || this.state == State.Initial) {
            this.connectionLock.lock();
            if (topLevelStreamElement != null) {
                try {
                    if (topLevelStreamElement instanceof Stanza) {
                        this.connection.sendStanza((Stanza) topLevelStreamElement);
                    } else if (topLevelStreamElement instanceof PlainStreamElement) {
                        this.connection.send((PlainStreamElement) topLevelStreamElement);
                    } else {
                        throw new IllegalStateException("Unsupported element type");
                    }
                    this.state = State.RequestSent;
                } catch (Throwable th) {
                    this.connectionLock.unlock();
                }
            }
            waitForConditionOrTimeout();
            this.connectionLock.unlock();
            checkForResponse();
            return;
        }
        throw new AssertionError();
    }

    public void sendAndWaitForResponseOrThrow(PlainStreamElement plainStreamElement) {
        sendAndWaitForResponse(plainStreamElement);
        switch (C09951.$SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[this.state.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.failureException != null) {
                    throw this.failureException;
                }
            default:
        }
    }

    public void checkIfSuccessOrWaitOrThrow() {
        checkIfSuccessOrWait();
        if (this.state == State.Failure) {
            throw this.failureException;
        }
    }

    public void checkIfSuccessOrWait() {
        this.connectionLock.lock();
        try {
            if (this.state != State.Success) {
                waitForConditionOrTimeout();
                this.connectionLock.unlock();
                checkForResponse();
            }
        } finally {
            this.connectionLock.unlock();
        }
    }

    public void reportSuccess() {
        this.connectionLock.lock();
        try {
            this.state = State.Success;
            this.condition.signalAll();
        } finally {
            this.connectionLock.unlock();
        }
    }

    @Deprecated
    public void reportFailure() {
        reportFailure(null);
    }

    public void reportFailure(E e) {
        if ($assertionsDisabled || e != null) {
            this.connectionLock.lock();
            try {
                this.state = State.Failure;
                this.failureException = e;
                this.condition.signalAll();
            } finally {
                this.connectionLock.unlock();
            }
        } else {
            throw new AssertionError();
        }
    }

    public boolean wasSuccessful() {
        this.connectionLock.lock();
        try {
            boolean z = this.state == State.Success;
            this.connectionLock.unlock();
            return z;
        } catch (Throwable th) {
            this.connectionLock.unlock();
        }
    }

    public boolean requestSent() {
        this.connectionLock.lock();
        try {
            boolean z = this.state == State.RequestSent;
            this.connectionLock.unlock();
            return z;
        } catch (Throwable th) {
            this.connectionLock.unlock();
        }
    }

    private void waitForConditionOrTimeout() {
        long toNanos = TimeUnit.MILLISECONDS.toNanos(this.connection.getPacketReplyTimeout());
        while (true) {
            if (this.state != State.RequestSent && this.state != State.Initial) {
                return;
            }
            if (toNanos <= 0) {
                try {
                    this.state = State.NoResponse;
                    return;
                } catch (Throwable e) {
                    LOGGER.log(Level.WARNING, "Thread interrupt while waiting for condition or timeout ignored", e);
                }
            } else {
                toNanos = this.condition.awaitNanos(toNanos);
            }
        }
    }

    private void checkForResponse() {
        switch (C09951.$SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[this.state.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                throw NoResponseException.newWith(this.connection);
            default:
        }
    }
}
