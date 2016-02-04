package org.jivesoftware.smackx.filetransfer;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.filetransfer.FileTransferException.NoAcceptableTransferMechanisms;
import org.jivesoftware.smackx.filetransfer.FileTransferException.NoStreamMethodsOfferedException;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jivesoftware.smackx.si.packet.StreamInitiation.File;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.FormField.Option;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class FileTransferNegotiator extends Manager {
    public static boolean IBB_ONLY = false;
    private static final Map<XMPPConnection, FileTransferNegotiator> INSTANCES;
    private static final String[] NAMESPACE;
    public static final String SI_NAMESPACE = "http://jabber.org/protocol/si";
    public static final String SI_PROFILE_FILE_TRANSFER_NAMESPACE = "http://jabber.org/protocol/si/profile/file-transfer";
    protected static final String STREAM_DATA_FIELD_NAME = "stream-method";
    private static final String STREAM_INIT_PREFIX = "jsi_";
    private static final Random randomGenerator;
    private final StreamNegotiator byteStreamTransferManager;
    private final StreamNegotiator inbandTransferManager;

    static {
        boolean z = true;
        NAMESPACE = new String[]{SI_NAMESPACE, SI_PROFILE_FILE_TRANSFER_NAMESPACE};
        INSTANCES = new WeakHashMap();
        randomGenerator = new Random();
        if (System.getProperty("ibb") == null) {
            z = false;
        }
        IBB_ONLY = z;
    }

    public static synchronized FileTransferNegotiator getInstanceFor(XMPPConnection xMPPConnection) {
        FileTransferNegotiator fileTransferNegotiator;
        synchronized (FileTransferNegotiator.class) {
            fileTransferNegotiator = (FileTransferNegotiator) INSTANCES.get(xMPPConnection);
            if (fileTransferNegotiator == null) {
                fileTransferNegotiator = new FileTransferNegotiator(xMPPConnection);
                INSTANCES.put(xMPPConnection, fileTransferNegotiator);
            }
        }
        return fileTransferNegotiator;
    }

    private static void setServiceEnabled(XMPPConnection xMPPConnection, boolean z) {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(xMPPConnection);
        List<String> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(NAMESPACE));
        arrayList.add(Open.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Bytestream.NAMESPACE);
        }
        for (String str : arrayList) {
            if (z) {
                instanceFor.addFeature(str);
            } else {
                instanceFor.removeFeature(str);
            }
        }
    }

    public static boolean isServiceEnabled(XMPPConnection xMPPConnection) {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(xMPPConnection);
        List<String> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(NAMESPACE));
        arrayList.add(Open.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Bytestream.NAMESPACE);
        }
        for (String includesFeature : arrayList) {
            if (!instanceFor.includesFeature(includesFeature)) {
                return false;
            }
        }
        return true;
    }

    public static Collection<String> getSupportedProtocols() {
        List arrayList = new ArrayList();
        arrayList.add(Open.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Bytestream.NAMESPACE);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private FileTransferNegotiator(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.byteStreamTransferManager = new Socks5TransferNegotiator(xMPPConnection);
        this.inbandTransferManager = new IBBTransferNegotiator(xMPPConnection);
        setServiceEnabled(xMPPConnection, true);
    }

    public StreamNegotiator selectStreamNegotiator(FileTransferRequest fileTransferRequest) {
        IQ streamInitiation = fileTransferRequest.getStreamInitiation();
        FormField streamMethodField = getStreamMethodField(streamInitiation.getFeatureNegotiationForm());
        if (streamMethodField == null) {
            connection().sendStanza(IQ.createErrorResponse(streamInitiation, XMPPError.from(Condition.bad_request, "No stream methods contained in stanza.")));
            throw new NoStreamMethodsOfferedException();
        }
        try {
            return getNegotiator(streamMethodField);
        } catch (NoAcceptableTransferMechanisms e) {
            connection().sendStanza(IQ.createErrorResponse(streamInitiation, XMPPError.from(Condition.bad_request, "No acceptable transfer mechanism")));
            throw e;
        }
    }

    private FormField getStreamMethodField(DataForm dataForm) {
        for (FormField formField : dataForm.getFields()) {
            if (formField.getVariable().equals(STREAM_DATA_FIELD_NAME)) {
                return formField;
            }
        }
        return null;
    }

    private StreamNegotiator getNegotiator(FormField formField) {
        Object obj = null;
        Object obj2 = null;
        for (Option value : formField.getOptions()) {
            Object obj3;
            String value2 = value.getValue();
            if (value2.equals(Bytestream.NAMESPACE) && !IBB_ONLY) {
                obj3 = obj;
                obj = 1;
            } else if (value2.equals(Open.NAMESPACE)) {
                int i = 1;
                obj = obj2;
            } else {
                obj3 = obj;
                obj = obj2;
            }
            obj2 = obj;
            obj = obj3;
        }
        if (obj2 == null && obj == null) {
            throw new NoAcceptableTransferMechanisms();
        } else if (obj2 != null && obj != null) {
            return new FaultTolerantNegotiator(connection(), this.byteStreamTransferManager, this.inbandTransferManager);
        } else {
            if (obj2 != null) {
                return this.byteStreamTransferManager;
            }
            return this.inbandTransferManager;
        }
    }

    public String getNextStreamID() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STREAM_INIT_PREFIX);
        stringBuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringBuilder.toString();
    }

    public StreamNegotiator negotiateOutgoingTransfer(String str, String str2, String str3, long j, String str4, int i) {
        IQ streamInitiation = new StreamInitiation();
        streamInitiation.setSessionID(str2);
        streamInitiation.setMimeType(URLConnection.guessContentTypeFromName(str3));
        File file = new File(str3, j);
        file.setDesc(str4);
        streamInitiation.setFile(file);
        streamInitiation.setFeatureNegotiationForm(createDefaultInitiationForm());
        streamInitiation.setFrom(connection().getUser());
        streamInitiation.setTo(str);
        streamInitiation.setType(Type.set);
        Stanza nextResultOrThrow = connection().createPacketCollectorAndSend(streamInitiation).nextResultOrThrow((long) i);
        if (!(nextResultOrThrow instanceof IQ)) {
            return null;
        }
        streamInitiation = (IQ) nextResultOrThrow;
        if (streamInitiation.getType().equals(Type.result)) {
            return getOutgoingNegotiator(getStreamMethodField(((StreamInitiation) nextResultOrThrow).getFeatureNegotiationForm()));
        }
        throw new XMPPErrorException(streamInitiation.getError());
    }

    private StreamNegotiator getOutgoingNegotiator(FormField formField) {
        Object obj = null;
        Object obj2 = null;
        for (String str : formField.getValues()) {
            Object obj3;
            if (str.equals(Bytestream.NAMESPACE) && !IBB_ONLY) {
                obj3 = obj;
                obj = 1;
            } else if (str.equals(Open.NAMESPACE)) {
                int i = 1;
                obj = obj2;
            } else {
                obj3 = obj;
                obj = obj2;
            }
            obj2 = obj;
            obj = obj3;
        }
        if (obj2 == null && obj == null) {
            throw new NoAcceptableTransferMechanisms();
        } else if (obj2 != null && obj != null) {
            return new FaultTolerantNegotiator(connection(), this.byteStreamTransferManager, this.inbandTransferManager);
        } else {
            if (obj2 != null) {
                return this.byteStreamTransferManager;
            }
            return this.inbandTransferManager;
        }
    }

    private DataForm createDefaultInitiationForm() {
        DataForm dataForm = new DataForm(DataForm.Type.form);
        FormField formField = new FormField(STREAM_DATA_FIELD_NAME);
        formField.setType(FormField.Type.list_single);
        if (!IBB_ONLY) {
            formField.addOption(new Option(Bytestream.NAMESPACE));
        }
        formField.addOption(new Option(Open.NAMESPACE));
        dataForm.addField(formField);
        return dataForm;
    }
}
