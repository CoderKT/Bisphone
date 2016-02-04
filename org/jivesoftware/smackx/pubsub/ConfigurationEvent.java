package org.jivesoftware.smackx.pubsub;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;

public class ConfigurationEvent extends NodeExtension implements EmbeddedPacketExtension {
    private ConfigureForm form;

    public ConfigurationEvent(String str) {
        super(PubSubElementType.CONFIGURATION, str);
    }

    public ConfigurationEvent(String str, ConfigureForm configureForm) {
        super(PubSubElementType.CONFIGURATION, str);
        this.form = configureForm;
    }

    public ConfigureForm getConfiguration() {
        return this.form;
    }

    public List<ExtensionElement> getExtensions() {
        if (getConfiguration() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(new ExtensionElement[]{getConfiguration().getDataFormToSend()});
    }
}
