package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class TypeDeserializer {

    /* renamed from: com.fasterxml.jackson.databind.jsontype.TypeDeserializer.1 */
    /* synthetic */ class C06361 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract Class<?> getDefaultImpl();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract As getTypeInclusion();

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) {
        return deserializeIfNatural(jsonParser, deserializationContext, javaType.getRawClass());
    }

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            return null;
        }
        switch (C06361.$SwitchMap$com$fasterxml$jackson$core$JsonToken[currentToken.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (cls.isAssignableFrom(String.class)) {
                    return jsonParser.getText();
                }
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (cls.isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (cls.isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                if (cls.isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                if (cls.isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
}
