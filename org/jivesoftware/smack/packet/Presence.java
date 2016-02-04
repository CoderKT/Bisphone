package org.jivesoftware.smack.packet;

import java.util.Locale;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public final class Presence extends Stanza implements TypedCloneable<Presence> {
    public static final String ELEMENT = "presence";
    private Mode mode;
    private int priority;
    private String status;
    private Type type;

    public enum Mode {
        chat,
        available,
        away,
        xa,
        dnd;

        public static Mode fromString(String str) {
            return valueOf(str.toLowerCase(Locale.US));
        }
    }

    public enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe;

        public static Type fromString(String str) {
            return valueOf(str.toLowerCase(Locale.US));
        }
    }

    public Presence(Type type) {
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        setType(type);
    }

    public Presence(Type type, String str, int i, Mode mode) {
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        setType(type);
        setStatus(str);
        setPriority(i);
        setMode(mode);
    }

    public Presence(Presence presence) {
        super((Stanza) presence);
        this.type = Type.available;
        this.status = null;
        this.priority = Integer.MIN_VALUE;
        this.mode = null;
        this.type = presence.type;
        this.status = presence.status;
        this.priority = presence.priority;
        this.mode = presence.mode;
    }

    public boolean isAvailable() {
        return this.type == Type.available;
    }

    public boolean isAway() {
        return this.type == Type.available && (this.mode == Mode.away || this.mode == Mode.xa || this.mode == Mode.dnd);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = (Type) Objects.requireNonNull(type, "Type cannot be null");
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.priority = i;
    }

    public Mode getMode() {
        if (this.mode == null) {
            return Mode.available;
        }
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(ELEMENT);
        addCommonAttributes(xmlStringBuilder);
        if (this.type != Type.available) {
            xmlStringBuilder.attribute("type", this.type);
        }
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement(Status.ELEMENT, this.status);
        if (this.priority != Integer.MIN_VALUE) {
            xmlStringBuilder.element("priority", Integer.toString(this.priority));
        }
        if (!(this.mode == null || this.mode == Mode.available)) {
            xmlStringBuilder.element("show", this.mode);
        }
        xmlStringBuilder.append(getExtensionsXML());
        appendErrorIfExists(xmlStringBuilder);
        xmlStringBuilder.closeElement(ELEMENT);
        return xmlStringBuilder;
    }

    public Presence clone() {
        return new Presence(this);
    }

    public Presence cloneWithNewId() {
        Presence clone = clone();
        clone.setStanzaId(StanzaIdUtil.newStanzaId());
        return clone;
    }
}
