package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.sasl.SASLAnonymous;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.Success;

public class SASLAuthentication {
    private static final Set<String> BLACKLISTED_MECHANISMS;
    private static final Logger LOGGER;
    private static final List<SASLMechanism> REGISTERED_MECHANISMS;
    private boolean authenticationSuccessful;
    private final AbstractXMPPConnection connection;
    private SASLMechanism currentMechanism;
    private Exception saslException;

    static {
        LOGGER = Logger.getLogger(SASLAuthentication.class.getName());
        REGISTERED_MECHANISMS = new ArrayList();
        BLACKLISTED_MECHANISMS = new HashSet();
    }

    public static void registerSASLMechanism(SASLMechanism sASLMechanism) {
        synchronized (REGISTERED_MECHANISMS) {
            REGISTERED_MECHANISMS.add(sASLMechanism);
            Collections.sort(REGISTERED_MECHANISMS);
        }
    }

    public static Map<String, String> getRegisterdSASLMechanisms() {
        Map<String, String> hashMap = new HashMap();
        synchronized (REGISTERED_MECHANISMS) {
            for (SASLMechanism sASLMechanism : REGISTERED_MECHANISMS) {
                hashMap.put(sASLMechanism.getClass().getName(), sASLMechanism.getName());
            }
        }
        return hashMap;
    }

    public static boolean unregisterSASLMechanism(String str) {
        synchronized (REGISTERED_MECHANISMS) {
            Iterator it = REGISTERED_MECHANISMS.iterator();
            while (it.hasNext()) {
                if (((SASLMechanism) it.next()).getClass().getName().equals(str)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean blacklistSASLMechanism(String str) {
        boolean add;
        synchronized (BLACKLISTED_MECHANISMS) {
            add = BLACKLISTED_MECHANISMS.add(str);
        }
        return add;
    }

    public static boolean unBlacklistSASLMechanism(String str) {
        boolean remove;
        synchronized (BLACKLISTED_MECHANISMS) {
            remove = BLACKLISTED_MECHANISMS.remove(str);
        }
        return remove;
    }

    public static Set<String> getBlacklistedSASLMechanisms() {
        Set hashSet;
        synchronized (BLACKLISTED_MECHANISMS) {
            hashSet = new HashSet(BLACKLISTED_MECHANISMS);
        }
        return hashSet;
    }

    SASLAuthentication(AbstractXMPPConnection abstractXMPPConnection) {
        this.currentMechanism = null;
        this.connection = abstractXMPPConnection;
        init();
    }

    public boolean hasAnonymousAuthentication() {
        return serverMechanisms().contains(SASLAnonymous.NAME);
    }

    public boolean hasNonAnonymousAuthentication() {
        return (serverMechanisms().isEmpty() || (serverMechanisms().size() == 1 && hasAnonymousAuthentication())) ? false : true;
    }

    public void authenticate(String str, CallbackHandler callbackHandler) {
        SASLMechanism selectMechanism = selectMechanism();
        if (selectMechanism != null) {
            this.currentMechanism = selectMechanism;
            synchronized (this) {
                this.currentMechanism.authenticate(this.connection.getHost(), this.connection.getServiceName(), callbackHandler);
                try {
                    wait(this.connection.getPacketReplyTimeout());
                } catch (InterruptedException e) {
                }
            }
            maybeThrowException();
            if (!this.authenticationSuccessful) {
                throw NoResponseException.newWith(this.connection);
            }
            return;
        }
        throw new SmackException("SASL Authentication failed. No known authentication mechanisims.");
    }

    public void authenticate(String str, String str2, String str3) {
        SASLMechanism selectMechanism = selectMechanism();
        if (selectMechanism != null) {
            this.currentMechanism = selectMechanism;
            synchronized (this) {
                this.currentMechanism.authenticate(str, this.connection.getHost(), this.connection.getServiceName(), str2);
                try {
                    wait(this.connection.getPacketReplyTimeout());
                } catch (InterruptedException e) {
                }
            }
            maybeThrowException();
            if (!this.authenticationSuccessful) {
                throw NoResponseException.newWith(this.connection);
            }
            return;
        }
        throw new SmackException("SASL Authentication failed. No known authentication mechanisims.");
    }

    public void authenticateAnonymously() {
        this.currentMechanism = new SASLAnonymous().instanceForAuthentication(this.connection);
        synchronized (this) {
            this.currentMechanism.authenticate(null, null, null, "");
            try {
                wait(this.connection.getPacketReplyTimeout());
            } catch (InterruptedException e) {
            }
        }
        maybeThrowException();
        if (!this.authenticationSuccessful) {
            throw NoResponseException.newWith(this.connection);
        }
    }

    private void maybeThrowException() {
        if (this.saslException == null) {
            return;
        }
        if (this.saslException instanceof SmackException) {
            throw ((SmackException) this.saslException);
        } else if (this.saslException instanceof SASLErrorException) {
            throw ((SASLErrorException) this.saslException);
        } else {
            throw new IllegalStateException("Unexpected exception type", this.saslException);
        }
    }

    public void challengeReceived(String str) {
        challengeReceived(str, false);
    }

    public void challengeReceived(String str, boolean z) {
        try {
            this.currentMechanism.challengeReceived(str, z);
        } catch (Exception e) {
            authenticationFailed(e);
            throw e;
        }
    }

    public void authenticated(Success success) {
        if (success.getData() != null) {
            challengeReceived(success.getData(), true);
        }
        this.currentMechanism.checkIfSuccessfulOrThrow();
        this.authenticationSuccessful = true;
        synchronized (this) {
            notify();
        }
    }

    public void authenticationFailed(SASLFailure sASLFailure) {
        authenticationFailed(new SASLErrorException(this.currentMechanism.getName(), sASLFailure));
    }

    public void authenticationFailed(Exception exception) {
        this.saslException = exception;
        synchronized (this) {
            notify();
        }
    }

    public boolean authenticationSuccessful() {
        return this.authenticationSuccessful;
    }

    protected void init() {
        this.authenticationSuccessful = false;
        this.saslException = null;
    }

    private SASLMechanism selectMechanism() {
        for (SASLMechanism sASLMechanism : REGISTERED_MECHANISMS) {
            String name = sASLMechanism.getName();
            synchronized (BLACKLISTED_MECHANISMS) {
                if (BLACKLISTED_MECHANISMS.contains(name)) {
                } else {
                    if (serverMechanisms().contains(name)) {
                        return sASLMechanism.instanceForAuthentication(this.connection);
                    }
                }
            }
        }
        return null;
    }

    private List<String> serverMechanisms() {
        Mechanisms mechanisms = (Mechanisms) this.connection.getFeature(Mechanisms.ELEMENT, SaslStreamElements.NAMESPACE);
        if (mechanisms != null) {
            return mechanisms.getMechanisms();
        }
        LOGGER.warning("Server did not report any SASL mechanisms");
        return Collections.emptyList();
    }
}
