package org.jivesoftware.smack.packet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import se.emilsjolander.stickylistheaders.C1128R;

public class XMPPError extends AbstractError {
    private static final Map<Condition, Type> CONDITION_TO_TYPE;
    public static final String ERROR = "error";
    private static final Logger LOGGER;
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-stanzas";
    private final Condition condition;
    private final String conditionText;
    private final String errorGenerator;
    private final Type type;

    /* renamed from: org.jivesoftware.smack.packet.XMPPError.1 */
    /* synthetic */ class C10091 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition = new int[Condition.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.gone.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.redirect.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum Condition {
        bad_request,
        conflict,
        feature_not_implemented,
        forbidden,
        gone,
        internal_server_error,
        item_not_found,
        jid_malformed,
        not_acceptable,
        not_allowed,
        not_authorized,
        policy_violation,
        recipient_unavailable,
        redirect,
        registration_required,
        remote_server_not_found,
        remote_server_timeout,
        resource_constraint,
        service_unavailable,
        subscription_required,
        undefined_condition,
        unexpected_request;

        public String toString() {
            return name().replace('_', '-');
        }

        public static Condition fromString(String str) {
            if ("xml-not-well-formed".equals(str)) {
                str = "not-well-formed";
            }
            String replace = str.replace('-', '_');
            try {
                return valueOf(replace);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not transform string '" + replace + "' to XMPPErrorCondition", e);
            }
        }
    }

    public enum Type {
        WAIT,
        CANCEL,
        MODIFY,
        AUTH,
        CONTINUE;

        public String toString() {
            return name().toLowerCase();
        }

        public static Type fromString(String str) {
            return valueOf(str.toUpperCase());
        }
    }

    static {
        LOGGER = Logger.getLogger(XMPPError.class.getName());
        CONDITION_TO_TYPE = new HashMap();
        CONDITION_TO_TYPE.put(Condition.bad_request, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.conflict, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.feature_not_implemented, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.forbidden, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.gone, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.internal_server_error, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.item_not_found, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.jid_malformed, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.not_acceptable, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.not_allowed, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.not_authorized, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.policy_violation, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.recipient_unavailable, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.redirect, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.registration_required, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.remote_server_not_found, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.remote_server_timeout, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.resource_constraint, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.service_unavailable, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.subscription_required, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.unexpected_request, Type.MODIFY);
    }

    public XMPPError(Condition condition) {
        this(condition, null, null, null, null, null);
    }

    public XMPPError(Condition condition, ExtensionElement extensionElement) {
        this(condition, null, null, null, null, Arrays.asList(new ExtensionElement[]{extensionElement}));
    }

    public XMPPError(Condition condition, String str, String str2, Type type, Map<String, String> map, List<ExtensionElement> list) {
        super(map, NAMESPACE, list);
        this.condition = condition;
        if (StringUtils.isNullOrEmpty(str)) {
            str = null;
        }
        if (str != null) {
            switch (C10091.$SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[condition.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    break;
                default:
                    throw new IllegalArgumentException("Condition text can only be set with condtion types 'gone' and 'redirect', not " + condition);
            }
        }
        this.conditionText = str;
        this.errorGenerator = str2;
        if (type == null) {
            Type type2 = (Type) CONDITION_TO_TYPE.get(condition);
            if (type2 == null) {
                LOGGER.warning("Could not determine type for condition: " + condition);
                type2 = Type.CANCEL;
            }
            this.type = type2;
            return;
        }
        this.type = type;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public Type getType() {
        return this.type;
    }

    public String getErrorGenerator() {
        return this.errorGenerator;
    }

    public String getConditionText() {
        return this.conditionText;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XMPPError: ");
        stringBuilder.append(this.condition.toString()).append(" - ").append(this.type.toString());
        if (this.errorGenerator != null) {
            stringBuilder.append(". Generated by ").append(this.errorGenerator);
        }
        return stringBuilder.toString();
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(ERROR);
        xmlStringBuilder.attribute("type", this.type.toString());
        xmlStringBuilder.optAttribute("by", this.errorGenerator);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.halfOpenElement(this.condition.toString());
        xmlStringBuilder.xmlnsAttribute(NAMESPACE);
        if (this.conditionText != null) {
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.escape(this.conditionText);
            xmlStringBuilder.closeElement(this.condition.toString());
        } else {
            xmlStringBuilder.closeEmptyElement();
        }
        addDescriptiveTextsAndExtensions(xmlStringBuilder);
        xmlStringBuilder.closeElement(ERROR);
        return xmlStringBuilder;
    }

    public static XMPPError from(Condition condition, String str) {
        Map hashMap = new HashMap();
        hashMap.put("en", str);
        return new XMPPError(condition, null, null, null, hashMap, null);
    }
}
