package org.jivesoftware.smack.iqrequest;

import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AbstractIqRequestHandler implements IQRequestHandler {
    private final String element;
    private final Mode mode;
    private final String namespace;
    private final Type type;

    /* renamed from: org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler.1 */
    /* synthetic */ class C10041 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$IQ$Type;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.set.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.get.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public abstract IQ handleIQRequest(IQ iq);

    protected AbstractIqRequestHandler(String str, String str2, Type type, Mode mode) {
        switch (C10041.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.element = str;
                this.namespace = str2;
                this.type = type;
                this.mode = mode;
            default:
                throw new IllegalArgumentException("Only get and set IQ type allowed");
        }
    }

    public Mode getMode() {
        return this.mode;
    }

    public Type getType() {
        return this.type;
    }

    public String getElement() {
        return this.element;
    }

    public String getNamespace() {
        return this.namespace;
    }
}
