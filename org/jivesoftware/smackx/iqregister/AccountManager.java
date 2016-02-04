package org.jivesoftware.smackx.iqregister;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.jxmpp.util.XmppStringUtils;

public class AccountManager extends Manager {
    private static final Map<XMPPConnection, AccountManager> INSTANCES;
    private static final Logger LOGGER;
    private static boolean allowSensitiveOperationOverInsecureConnectionDefault;
    private boolean accountCreationSupported;
    private boolean allowSensitiveOperationOverInsecureConnection;
    private Registration info;

    static {
        LOGGER = Logger.getLogger(AccountManager.class.getName());
        INSTANCES = new WeakHashMap();
        allowSensitiveOperationOverInsecureConnectionDefault = false;
    }

    public static synchronized AccountManager getInstance(XMPPConnection xMPPConnection) {
        AccountManager accountManager;
        synchronized (AccountManager.class) {
            accountManager = (AccountManager) INSTANCES.get(xMPPConnection);
            if (accountManager == null) {
                accountManager = new AccountManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, accountManager);
            }
        }
        return accountManager;
    }

    public static void sensitiveOperationOverInsecureConnectionDefault(boolean z) {
        allowSensitiveOperationOverInsecureConnectionDefault = z;
    }

    public void sensitiveOperationOverInsecureConnection(boolean z) {
        this.allowSensitiveOperationOverInsecureConnection = z;
    }

    private AccountManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.allowSensitiveOperationOverInsecureConnection = allowSensitiveOperationOverInsecureConnectionDefault;
        this.info = null;
        this.accountCreationSupported = false;
    }

    void setSupportsAccountCreation(boolean z) {
        this.accountCreationSupported = z;
    }

    public boolean supportsAccountCreation() {
        boolean z = true;
        if (this.accountCreationSupported) {
            return true;
        }
        if (this.info == null) {
            getRegistrationInfo();
            if (this.info.getType() == Type.error) {
                z = false;
            }
            this.accountCreationSupported = z;
        }
        return this.accountCreationSupported;
    }

    public Set<String> getAccountAttributes() {
        if (this.info == null) {
            getRegistrationInfo();
        }
        Map attributes = this.info.getAttributes();
        if (attributes != null) {
            return Collections.unmodifiableSet(attributes.keySet());
        }
        return Collections.emptySet();
    }

    public String getAccountAttribute(String str) {
        if (this.info == null) {
            getRegistrationInfo();
        }
        return (String) this.info.getAttributes().get(str);
    }

    public String getAccountInstructions() {
        if (this.info == null) {
            getRegistrationInfo();
        }
        return this.info.getInstructions();
    }

    public void createAccount(String str, String str2) {
        Map hashMap = new HashMap();
        for (String put : getAccountAttributes()) {
            hashMap.put(put, "");
        }
        createAccount(str, str2, hashMap);
    }

    public void createAccount(String str, String str2, Map<String, String> map) {
        if (!(connection().isSecureConnection() || this.allowSensitiveOperationOverInsecureConnection)) {
            LOGGER.warning("Creating account over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        map.put("username", str);
        map.put("password", str2);
        IQ registration = new Registration(map);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public void changePassword(String str) {
        if (!(connection().isSecureConnection() || this.allowSensitiveOperationOverInsecureConnection)) {
            LOGGER.warning("Changing password over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        Map hashMap = new HashMap();
        hashMap.put("username", XmppStringUtils.m13441a(connection().getUser()));
        hashMap.put("password", str);
        IQ registration = new Registration(hashMap);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public void deleteAccount() {
        Map hashMap = new HashMap();
        hashMap.put(Item.REMOVE_ACTION, "");
        IQ registration = new Registration(hashMap);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    private synchronized void getRegistrationInfo() {
        IQ registration = new Registration();
        registration.setTo(connection().getServiceName());
        this.info = (Registration) createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    private PacketCollector createPacketCollectorAndSend(IQ iq) {
        return connection().createPacketCollectorAndSend(new StanzaIdFilter(iq.getStanzaId()), iq);
    }
}
