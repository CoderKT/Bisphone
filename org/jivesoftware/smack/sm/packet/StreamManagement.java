package org.jivesoftware.smack.sm.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class StreamManagement {
    public static final String NAMESPACE = "urn:xmpp:sm:3";

    abstract class AbstractEnable extends FullStreamElement {
        protected int max;
        protected boolean resume;

        private AbstractEnable() {
            this.max = -1;
            this.resume = false;
        }

        protected void maybeAddResumeAttributeTo(XmlStringBuilder xmlStringBuilder) {
            if (this.resume) {
                xmlStringBuilder.attribute(Resume.ELEMENT, "true");
            }
        }

        protected void maybeAddMaxAttributeTo(XmlStringBuilder xmlStringBuilder) {
            if (this.max > 0) {
                xmlStringBuilder.attribute("max", Integer.toString(this.max));
            }
        }

        public boolean isResumeSet() {
            return this.resume;
        }

        public int getMaxResumptionTime() {
            return this.max;
        }

        public final String getNamespace() {
            return StreamManagement.NAMESPACE;
        }
    }

    abstract class AbstractResume extends FullStreamElement {
        private final long handledCount;
        private final String previd;

        public AbstractResume(long j, String str) {
            this.handledCount = j;
            this.previd = str;
        }

        public long getHandledCount() {
            return this.handledCount;
        }

        public String getPrevId() {
            return this.previd;
        }

        public final String getNamespace() {
            return StreamManagement.NAMESPACE;
        }

        public final XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            xmlStringBuilder.attribute(XHTMLText.f8585H, Long.toString(this.handledCount));
            xmlStringBuilder.attribute("previd", this.previd);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }

    public class AckAnswer extends FullStreamElement {
        public static final String ELEMENT = "a";
        private final long handledCount;

        public AckAnswer(long j) {
            this.handledCount = j;
        }

        public long getHandledCount() {
            return this.handledCount;
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            xmlStringBuilder.attribute(XHTMLText.f8585H, Long.toString(this.handledCount));
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getNamespace() {
            return StreamManagement.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class AckRequest extends FullStreamElement {
        public static final String ELEMENT = "r";
        public static final AckRequest INSTANCE;

        static {
            INSTANCE = new AckRequest();
        }

        private AckRequest() {
        }

        public CharSequence toXML() {
            return "<r xmlns='urn:xmpp:sm:3'/>";
        }

        public String getNamespace() {
            return StreamManagement.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Enable extends AbstractEnable {
        public static final String ELEMENT = "enable";
        public static final Enable INSTANCE;

        public /* bridge */ /* synthetic */ int getMaxResumptionTime() {
            return super.getMaxResumptionTime();
        }

        public /* bridge */ /* synthetic */ boolean isResumeSet() {
            return super.isResumeSet();
        }

        static {
            INSTANCE = new Enable();
        }

        private Enable() {
            super();
        }

        public Enable(boolean z) {
            super();
            this.resume = z;
        }

        public Enable(boolean z, int i) {
            this(z);
            this.max = i;
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            maybeAddResumeAttributeTo(xmlStringBuilder);
            maybeAddMaxAttributeTo(xmlStringBuilder);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Enabled extends AbstractEnable {
        public static final String ELEMENT = "enabled";
        private final String id;
        private final String location;

        public /* bridge */ /* synthetic */ int getMaxResumptionTime() {
            return super.getMaxResumptionTime();
        }

        public /* bridge */ /* synthetic */ boolean isResumeSet() {
            return super.isResumeSet();
        }

        public Enabled(String str, boolean z) {
            this(str, z, null, -1);
        }

        public Enabled(String str, boolean z, String str2, int i) {
            super();
            this.id = str;
            this.resume = z;
            this.location = str2;
            this.max = i;
        }

        public String getId() {
            return this.id;
        }

        public String getLocation() {
            return this.location;
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            xmlStringBuilder.optAttribute("id", this.id);
            maybeAddResumeAttributeTo(xmlStringBuilder);
            xmlStringBuilder.optAttribute("location", this.location);
            maybeAddMaxAttributeTo(xmlStringBuilder);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Failed extends FullStreamElement {
        public static final String ELEMENT = "failed";
        private Condition condition;

        public Failed(Condition condition) {
            this.condition = condition;
        }

        public Condition getXMPPErrorCondition() {
            return this.condition;
        }

        public CharSequence toXML() {
            Object xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            if (this.condition != null) {
                xmlStringBuilder.rightAngleBracket();
                xmlStringBuilder.append(this.condition.toString());
                xmlStringBuilder.xmlnsAttribute(XMPPError.NAMESPACE);
                xmlStringBuilder.closeElement(ELEMENT);
            } else {
                xmlStringBuilder.closeEmptyElement();
            }
            return xmlStringBuilder;
        }

        public String getNamespace() {
            return StreamManagement.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Resume extends AbstractResume {
        public static final String ELEMENT = "resume";

        public /* bridge */ /* synthetic */ long getHandledCount() {
            return super.getHandledCount();
        }

        public /* bridge */ /* synthetic */ String getPrevId() {
            return super.getPrevId();
        }

        public Resume(long j, String str) {
            super(j, str);
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Resumed extends AbstractResume {
        public static final String ELEMENT = "resumed";

        public /* bridge */ /* synthetic */ long getHandledCount() {
            return super.getHandledCount();
        }

        public /* bridge */ /* synthetic */ String getPrevId() {
            return super.getPrevId();
        }

        public Resumed(long j, String str) {
            super(j, str);
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class StreamManagementFeature implements ExtensionElement {
        public static final String ELEMENT = "sm";
        public static final StreamManagementFeature INSTANCE;

        static {
            INSTANCE = new StreamManagementFeature();
        }

        private StreamManagementFeature() {
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getNamespace() {
            return StreamManagement.NAMESPACE;
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }
}
