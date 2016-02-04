package org.jivesoftware.smackx.amp.provider;

import java.util.logging.Logger;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.amp.AMPDeliverCondition;
import org.jivesoftware.smackx.amp.AMPDeliverCondition.Value;
import org.jivesoftware.smackx.amp.AMPExpireAtCondition;
import org.jivesoftware.smackx.amp.AMPMatchResourceCondition;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Condition;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Rule;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Status;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.xmlpull.v1.XmlPullParser;

public class AMPExtensionProvider extends ExtensionElementProvider<AMPExtension> {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(AMPExtensionProvider.class.getName());
    }

    public AMPExtension parse(XmlPullParser xmlPullParser, int i) {
        Status valueOf;
        AMPExtension aMPExtension;
        Object obj;
        int next;
        Action valueOf2;
        String attributeValue = xmlPullParser.getAttributeValue(null, PrivacyItem.SUBSCRIPTION_FROM);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, PrivacyItem.SUBSCRIPTION_TO);
        String attributeValue3 = xmlPullParser.getAttributeValue(null, MUCUser.Status.ELEMENT);
        if (attributeValue3 != null) {
            try {
                valueOf = Status.valueOf(attributeValue3);
            } catch (IllegalArgumentException e) {
                LOGGER.severe("Found invalid amp status " + attributeValue3);
            }
            aMPExtension = new AMPExtension(attributeValue, attributeValue2, valueOf);
            attributeValue3 = xmlPullParser.getAttributeValue(null, "per-hop");
            if (attributeValue3 != null) {
                aMPExtension.setPerHop(Boolean.parseBoolean(attributeValue3));
            }
            obj = null;
            while (obj == null) {
                next = xmlPullParser.next();
                if (next != 2) {
                    if (xmlPullParser.getName().equals(Rule.ELEMENT)) {
                        attributeValue = xmlPullParser.getAttributeValue(null, Action.ATTRIBUTE_NAME);
                        Condition createCondition = createCondition(xmlPullParser.getAttributeValue(null, Condition.ATTRIBUTE_NAME), xmlPullParser.getAttributeValue(null, "value"));
                        if (attributeValue != null) {
                            try {
                                valueOf2 = Action.valueOf(attributeValue);
                            } catch (IllegalArgumentException e2) {
                                LOGGER.severe("Found invalid rule action value " + attributeValue);
                            }
                            if (valueOf2 != null || createCondition == null) {
                                LOGGER.severe("Rule is skipped because either it's action or it's condition is invalid");
                            } else {
                                aMPExtension.addRule(new Rule(valueOf2, createCondition));
                            }
                        }
                        valueOf2 = null;
                        if (valueOf2 != null) {
                        }
                        LOGGER.severe("Rule is skipped because either it's action or it's condition is invalid");
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(AMPExtension.ELEMENT)) {
                    obj = 1;
                }
            }
            return aMPExtension;
        }
        valueOf = null;
        aMPExtension = new AMPExtension(attributeValue, attributeValue2, valueOf);
        attributeValue3 = xmlPullParser.getAttributeValue(null, "per-hop");
        if (attributeValue3 != null) {
            aMPExtension.setPerHop(Boolean.parseBoolean(attributeValue3));
        }
        obj = null;
        while (obj == null) {
            next = xmlPullParser.next();
            if (next != 2) {
                obj = 1;
            } else if (xmlPullParser.getName().equals(Rule.ELEMENT)) {
                attributeValue = xmlPullParser.getAttributeValue(null, Action.ATTRIBUTE_NAME);
                Condition createCondition2 = createCondition(xmlPullParser.getAttributeValue(null, Condition.ATTRIBUTE_NAME), xmlPullParser.getAttributeValue(null, "value"));
                if (attributeValue != null) {
                    valueOf2 = Action.valueOf(attributeValue);
                    if (valueOf2 != null) {
                    }
                    LOGGER.severe("Rule is skipped because either it's action or it's condition is invalid");
                }
                valueOf2 = null;
                if (valueOf2 != null) {
                }
                LOGGER.severe("Rule is skipped because either it's action or it's condition is invalid");
            }
        }
        return aMPExtension;
    }

    private Condition createCondition(String str, String str2) {
        if (str == null || str2 == null) {
            LOGGER.severe("Can't create rule condition from null name and/or value");
            return null;
        } else if (AMPDeliverCondition.NAME.equals(str)) {
            try {
                return new AMPDeliverCondition(Value.valueOf(str2));
            } catch (IllegalArgumentException e) {
                LOGGER.severe("Found invalid rule delivery condition value " + str2);
                return null;
            }
        } else if (AMPExpireAtCondition.NAME.equals(str)) {
            return new AMPExpireAtCondition(str2);
        } else {
            if (AMPMatchResourceCondition.NAME.equals(str)) {
                try {
                    return new AMPMatchResourceCondition(AMPMatchResourceCondition.Value.valueOf(str2));
                } catch (IllegalArgumentException e2) {
                    LOGGER.severe("Found invalid rule match-resource condition value " + str2);
                    return null;
                }
            }
            LOGGER.severe("Found unknown rule condition name " + str);
            return null;
        }
    }
}
