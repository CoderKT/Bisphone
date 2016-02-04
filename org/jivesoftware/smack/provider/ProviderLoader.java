package org.jivesoftware.smack.provider;

import java.util.Collection;

public interface ProviderLoader {
    Collection<ExtensionProviderInfo> getExtensionProviderInfo();

    Collection<IQProviderInfo> getIQProviderInfo();

    Collection<StreamFeatureProviderInfo> getStreamFeatureProviderInfo();
}
