package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import se.emilsjolander.stickylistheaders.C1128R;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer instance;

    /* renamed from: com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer.1 */
    /* synthetic */ class C06241 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        instance = new NullifyingDeserializer();
    }

    public NullifyingDeserializer() {
        super(Object.class);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        jsonParser.skipChildren();
        return null;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        switch (C06241.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
            default:
                return null;
        }
    }
}
