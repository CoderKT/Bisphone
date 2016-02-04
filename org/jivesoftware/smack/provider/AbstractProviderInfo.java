package org.jivesoftware.smack.provider;

abstract class AbstractProviderInfo {
    private String element;
    private String ns;
    private Object provider;

    AbstractProviderInfo(String str, String str2, Object obj) {
        this.element = str;
        this.ns = str2;
        this.provider = obj;
    }

    public String getElementName() {
        return this.element;
    }

    public String getNamespace() {
        return this.ns;
    }

    Object getProvider() {
        return this.provider;
    }
}
