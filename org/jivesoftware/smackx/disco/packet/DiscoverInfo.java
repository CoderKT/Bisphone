package org.jivesoftware.smackx.disco.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppStringUtils;

public class DiscoverInfo extends IQ implements TypedCloneable<DiscoverInfo> {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/disco#info";
    private boolean containsDuplicateFeatures;
    private final List<Feature> features;
    private final Set<Feature> featuresSet;
    private final List<Identity> identities;
    private final Set<String> identitiesSet;
    private String node;

    public class Feature implements TypedCloneable<Feature> {
        private final String variable;

        public Feature(Feature feature) {
            this.variable = feature.variable;
        }

        public Feature(String str) {
            this.variable = (String) StringUtils.requireNotNullOrEmpty(str, "variable cannot be null");
        }

        public String getVar() {
            return this.variable;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement("feature");
            xmlStringBuilder.attribute("var", this.variable);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            return this.variable.equals(((Feature) obj).variable);
        }

        public int hashCode() {
            return this.variable.hashCode();
        }

        public Feature clone() {
            return new Feature(this);
        }
    }

    public class Identity implements Comparable<Identity>, TypedCloneable<Identity> {
        private final String category;
        private final String key;
        private final String lang;
        private final String name;
        private final String type;

        public Identity(Identity identity) {
            this.category = identity.category;
            this.type = identity.type;
            this.key = identity.type;
            this.name = identity.name;
            this.lang = identity.lang;
        }

        public Identity(String str, String str2) {
            this(str, str2, null, null);
        }

        public Identity(String str, String str2, String str3) {
            this(str, str3, str2, null);
        }

        public Identity(String str, String str2, String str3, String str4) {
            this.category = (String) StringUtils.requireNotNullOrEmpty(str, "category cannot be null");
            this.type = (String) StringUtils.requireNotNullOrEmpty(str2, "type cannot be null");
            this.key = XmppStringUtils.m13445b(str, str2);
            this.name = str3;
            this.lang = str4;
        }

        public String getCategory() {
            return this.category;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        public String getLanguage() {
            return this.lang;
        }

        private String getKey() {
            return this.key;
        }

        public boolean isOfCategoryAndType(String str, String str2) {
            return this.category.equals(str) && this.type.equals(str2);
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement("identity");
            xmlStringBuilder.xmllangAttribute(this.lang);
            xmlStringBuilder.attribute("category", this.category);
            xmlStringBuilder.optAttribute("name", this.name);
            xmlStringBuilder.optAttribute("type", this.type);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            Identity identity = (Identity) obj;
            if (!this.key.equals(identity.key)) {
                return false;
            }
            if (!(identity.lang == null ? "" : identity.lang).equals(this.lang == null ? "" : this.lang)) {
                return false;
            }
            if ((this.name == null ? "" : identity.name).equals(identity.name == null ? "" : identity.name)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.lang == null ? 0 : this.lang.hashCode()) + ((this.key.hashCode() + 37) * 37)) * 37;
            if (this.name != null) {
                i = this.name.hashCode();
            }
            return hashCode + i;
        }

        public int compareTo(Identity identity) {
            String str = identity.lang == null ? "" : identity.lang;
            String str2 = this.lang == null ? "" : this.lang;
            String str3 = identity.type == null ? "" : identity.type;
            String str4 = this.type == null ? "" : this.type;
            if (!this.category.equals(identity.category)) {
                return this.category.compareTo(identity.category);
            }
            if (!str4.equals(str3)) {
                return str4.compareTo(str3);
            }
            if (str2.equals(str)) {
                return 0;
            }
            return str2.compareTo(str);
        }

        public Identity clone() {
            return new Identity(this);
        }
    }

    public DiscoverInfo() {
        super(ELEMENT, NAMESPACE);
        this.features = new LinkedList();
        this.featuresSet = new HashSet();
        this.identities = new LinkedList();
        this.identitiesSet = new HashSet();
    }

    public DiscoverInfo(DiscoverInfo discoverInfo) {
        super((IQ) discoverInfo);
        this.features = new LinkedList();
        this.featuresSet = new HashSet();
        this.identities = new LinkedList();
        this.identitiesSet = new HashSet();
        setNode(discoverInfo.getNode());
        for (Feature clone : discoverInfo.features) {
            addFeature(clone.clone());
        }
        for (Identity clone2 : discoverInfo.identities) {
            addIdentity(clone2.clone());
        }
    }

    public boolean addFeature(String str) {
        return addFeature(new Feature(str));
    }

    public void addFeatures(Collection<String> collection) {
        if (collection != null) {
            for (String addFeature : collection) {
                addFeature(addFeature);
            }
        }
    }

    public boolean addFeature(Feature feature) {
        this.features.add(feature);
        boolean add = this.featuresSet.add(feature);
        if (!add) {
            this.containsDuplicateFeatures = true;
        }
        return add;
    }

    public List<Feature> getFeatures() {
        return Collections.unmodifiableList(this.features);
    }

    public void addIdentity(Identity identity) {
        this.identities.add(identity);
        this.identitiesSet.add(identity.getKey());
    }

    public void addIdentities(Collection<Identity> collection) {
        if (collection != null) {
            for (Identity addIdentity : collection) {
                addIdentity(addIdentity);
            }
        }
    }

    public List<Identity> getIdentities() {
        return Collections.unmodifiableList(this.identities);
    }

    public boolean hasIdentity(String str, String str2) {
        return this.identitiesSet.contains(XmppStringUtils.m13445b(str, str2));
    }

    public List<Identity> getIdentities(String str, String str2) {
        List<Identity> arrayList = new ArrayList(this.identities.size());
        for (Identity identity : this.identities) {
            if (identity.getCategory().equals(str) && identity.getType().equals(str2)) {
                arrayList.add(identity);
            }
        }
        return arrayList;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public boolean containsFeature(String str) {
        return this.features.contains(new Feature(str));
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.optAttribute("node", getNode());
        iQChildElementXmlStringBuilder.rightAngleBracket();
        for (Identity toXML : this.identities) {
            iQChildElementXmlStringBuilder.append(toXML.toXML());
        }
        for (Feature toXML2 : this.features) {
            iQChildElementXmlStringBuilder.append(toXML2.toXML());
        }
        return iQChildElementXmlStringBuilder;
    }

    public boolean containsDuplicateIdentities() {
        List<Identity> linkedList = new LinkedList();
        for (Identity identity : this.identities) {
            for (Identity equals : linkedList) {
                if (identity.equals(equals)) {
                    return true;
                }
            }
            linkedList.add(identity);
        }
        return false;
    }

    public boolean containsDuplicateFeatures() {
        return this.containsDuplicateFeatures;
    }

    public DiscoverInfo clone() {
        return new DiscoverInfo(this);
    }
}
