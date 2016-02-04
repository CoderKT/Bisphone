package org.jivesoftware.smackx.shim.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;

public class HeadersProvider extends EmbeddedExtensionProvider<HeadersExtension> {
    public static final HeadersProvider INSTANCE;

    static {
        INSTANCE = new HeadersProvider();
    }

    protected HeadersExtension createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new HeadersExtension(list);
    }
}
