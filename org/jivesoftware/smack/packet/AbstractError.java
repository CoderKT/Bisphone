package org.jivesoftware.smack.packet;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.jivesoftware.smack.util.PacketUtil;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;

public class AbstractError {
    protected final Map<String, String> descriptiveTexts;
    private final List<ExtensionElement> extensions;
    private final String textNamespace;

    protected AbstractError(Map<String, String> map) {
        this(map, null);
    }

    protected AbstractError(Map<String, String> map, List<ExtensionElement> list) {
        this(map, null, list);
    }

    protected AbstractError(Map<String, String> map, String str, List<ExtensionElement> list) {
        if (map != null) {
            this.descriptiveTexts = map;
        } else {
            this.descriptiveTexts = Collections.emptyMap();
        }
        this.textNamespace = str;
        if (list != null) {
            this.extensions = list;
        } else {
            this.extensions = Collections.emptyList();
        }
    }

    public String getDescriptiveText() {
        String descriptiveText = getDescriptiveText(Locale.getDefault().getLanguage());
        if (descriptiveText == null) {
            return getDescriptiveText("");
        }
        return descriptiveText;
    }

    public String getDescriptiveText(String str) {
        return (String) this.descriptiveTexts.get(str);
    }

    public <PE extends ExtensionElement> PE getExtension(String str, String str2) {
        return PacketUtil.extensionElementFrom(this.extensions, str, str2);
    }

    protected void addDescriptiveTextsAndExtensions(XmlStringBuilder xmlStringBuilder) {
        for (Entry entry : this.descriptiveTexts.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            xmlStringBuilder.halfOpenElement(Text.ELEMENT).xmlnsAttribute(this.textNamespace).xmllangAttribute(str).rightAngleBracket();
            xmlStringBuilder.escape(str2);
            xmlStringBuilder.closeElement(Text.ELEMENT);
        }
        for (ExtensionElement toXML : this.extensions) {
            xmlStringBuilder.append(toXML.toXML());
        }
    }
}
