package org.jivesoftware.smack.packet;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import se.emilsjolander.stickylistheaders.C1128R;

public class StreamError extends AbstractError implements PlainStreamElement {
    public static final String ELEMENT = "stream:error";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-streams";
    private final Condition condition;
    private final String conditionText;

    /* renamed from: org.jivesoftware.smack.packet.StreamError.1 */
    /* synthetic */ class C10071 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition = new int[Condition.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition[Condition.see_other_host.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public enum Condition {
        bad_format,
        bad_namespace_prefix,
        conflict,
        connection_timeout,
        host_gone,
        host_unknown,
        improper_addressing,
        internal_server_error,
        invalid_from,
        invalid_namespace,
        invalid_xml,
        not_authorized,
        not_well_formed,
        policy_violation,
        remote_connection_failed,
        reset,
        resource_constraint,
        restricted_xml,
        see_other_host,
        system_shutdown,
        undeficed_condition,
        unsupported_encoding,
        unsupported_feature,
        unsupported_stanza_type,
        unsupported_version;

        public String toString() {
            return name().replace('_', '-');
        }

        public static Condition fromString(String str) {
            String replace = str.replace('-', '_');
            try {
                return valueOf(replace);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not transform string '" + replace + "' to XMPPErrorCondition", e);
            }
        }
    }

    public StreamError(Condition condition, String str, Map<String, String> map, List<ExtensionElement> list) {
        super(map, list);
        if (StringUtils.isNullOrEmpty(str)) {
            str = null;
        }
        if (str != null) {
            switch (C10071.$SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition[condition.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                default:
                    throw new IllegalArgumentException("The given condition '" + condition + "' can not contain a conditionText");
            }
        }
        this.condition = condition;
        this.conditionText = str;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public String getConditionText() {
        return this.conditionText;
    }

    public String toString() {
        return toXML().toString();
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.openElement(ELEMENT);
        xmlStringBuilder.halfOpenElement(this.condition.toString()).xmlnsAttribute(NAMESPACE).closeEmptyElement();
        addDescriptiveTextsAndExtensions(xmlStringBuilder);
        xmlStringBuilder.closeElement(ELEMENT);
        return xmlStringBuilder;
    }
}
