package org.jivesoftware.smackx.commands.provider;

import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import org.jivesoftware.smackx.commands.AdHocCommandNote.Type;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.xmlpull.v1.XmlPullParser;

public class AdHocCommandDataProvider extends IQProvider<AdHocCommandData> {

    public class BadActionError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badAction);
        }
    }

    public class BadLocaleError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badLocale);
        }
    }

    public class BadPayloadError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badPayload);
        }
    }

    public class BadSessionIDError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badSessionid);
        }
    }

    public class MalformedActionError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.malformedAction);
        }
    }

    public class SessionExpiredError extends ExtensionElementProvider<SpecificError> {
        public SpecificError parse(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.sessionExpired);
        }
    }

    public AdHocCommandData parse(XmlPullParser xmlPullParser, int i) {
        Object obj;
        int next;
        String name;
        String attributeValue;
        AdHocCommandData adHocCommandData = new AdHocCommandData();
        DataFormProvider dataFormProvider = new DataFormProvider();
        adHocCommandData.setSessionID(xmlPullParser.getAttributeValue("", "sessionid"));
        adHocCommandData.setNode(xmlPullParser.getAttributeValue("", "node"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", Status.ELEMENT);
        if (AdHocCommand.Status.executing.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.executing);
        } else if (AdHocCommand.Status.completed.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.completed);
        } else if (AdHocCommand.Status.canceled.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.canceled);
        }
        attributeValue2 = xmlPullParser.getAttributeValue("", Action.ATTRIBUTE_NAME);
        if (attributeValue2 != null) {
            AdHocCommand.Action valueOf = AdHocCommand.Action.valueOf(attributeValue2);
            if (valueOf == null || valueOf.equals(AdHocCommand.Action.unknown)) {
                adHocCommandData.setAction(AdHocCommand.Action.unknown);
                obj = null;
                while (obj == null) {
                    next = xmlPullParser.next();
                    name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if (next != 2) {
                        if (xmlPullParser.getName().equals("actions")) {
                            attributeValue = xmlPullParser.getAttributeValue("", "execute");
                            if (attributeValue != null) {
                                adHocCommandData.setExecuteAction(AdHocCommand.Action.valueOf(attributeValue));
                            }
                        } else if (xmlPullParser.getName().equals("next")) {
                            adHocCommandData.addAction(AdHocCommand.Action.next);
                        } else if (xmlPullParser.getName().equals("complete")) {
                            adHocCommandData.addAction(AdHocCommand.Action.complete);
                        } else if (xmlPullParser.getName().equals("prev")) {
                            adHocCommandData.addAction(AdHocCommand.Action.prev);
                        } else if (!name.equals(DataForm.ELEMENT) && namespace.equals(DataForm.NAMESPACE)) {
                            adHocCommandData.setForm((DataForm) dataFormProvider.parse(xmlPullParser));
                        } else if (xmlPullParser.getName().equals("note")) {
                            adHocCommandData.addNote(new AdHocCommandNote(Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.nextText()));
                        } else if (xmlPullParser.getName().equals(XMPPError.ERROR)) {
                            adHocCommandData.setError(PacketParserUtils.parseError(xmlPullParser));
                        }
                    } else if (next == 3 && xmlPullParser.getName().equals(AdHocCommandData.ELEMENT)) {
                        obj = 1;
                    }
                }
                return adHocCommandData;
            }
            adHocCommandData.setAction(valueOf);
        }
        obj = null;
        while (obj == null) {
            next = xmlPullParser.next();
            name = xmlPullParser.getName();
            String namespace2 = xmlPullParser.getNamespace();
            if (next != 2) {
                obj = 1;
            } else if (xmlPullParser.getName().equals("actions")) {
                attributeValue = xmlPullParser.getAttributeValue("", "execute");
                if (attributeValue != null) {
                    adHocCommandData.setExecuteAction(AdHocCommand.Action.valueOf(attributeValue));
                }
            } else if (xmlPullParser.getName().equals("next")) {
                adHocCommandData.addAction(AdHocCommand.Action.next);
            } else if (xmlPullParser.getName().equals("complete")) {
                adHocCommandData.addAction(AdHocCommand.Action.complete);
            } else if (xmlPullParser.getName().equals("prev")) {
                adHocCommandData.addAction(AdHocCommand.Action.prev);
            } else {
                if (!name.equals(DataForm.ELEMENT)) {
                }
                if (xmlPullParser.getName().equals("note")) {
                    adHocCommandData.addNote(new AdHocCommandNote(Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(XMPPError.ERROR)) {
                    adHocCommandData.setError(PacketParserUtils.parseError(xmlPullParser));
                }
            }
        }
        return adHocCommandData;
    }
}
