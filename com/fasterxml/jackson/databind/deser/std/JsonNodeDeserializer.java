package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.emilsjolander.stickylistheaders.C1128R;

public class JsonNodeDeserializer extends BaseNodeDeserializer<JsonNode> {
    private static final JsonNodeDeserializer instance;

    final class ArrayDeserializer extends BaseNodeDeserializer<ArrayNode> {
        protected static final ArrayDeserializer _instance;

        static {
            _instance = new ArrayDeserializer();
        }

        protected ArrayDeserializer() {
            super(ArrayNode.class);
        }

        public static ArrayDeserializer getInstance() {
            return _instance;
        }

        public ArrayNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.isExpectedStartArrayToken()) {
                return deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            }
            throw deserializationContext.mappingException(ArrayNode.class);
        }
    }

    final class ObjectDeserializer extends BaseNodeDeserializer<ObjectNode> {
        protected static final ObjectDeserializer _instance;

        static {
            _instance = new ObjectDeserializer();
        }

        protected ObjectDeserializer() {
            super(ObjectNode.class);
        }

        public static ObjectDeserializer getInstance() {
            return _instance;
        }

        public ObjectNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                jsonParser.nextToken();
                return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            } else if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            } else {
                throw deserializationContext.mappingException(ObjectNode.class);
            }
        }
    }

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    static {
        instance = new JsonNodeDeserializer();
    }

    protected JsonNodeDeserializer() {
        super(JsonNode.class);
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> cls) {
        if (cls == ObjectNode.class) {
            return ObjectDeserializer.getInstance();
        }
        if (cls == ArrayNode.class) {
            return ArrayDeserializer.getInstance();
        }
        return instance;
    }

    public JsonNode getNullValue() {
        return NullNode.getInstance();
    }

    public JsonNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        switch (jsonParser.getCurrentTokenId()) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            default:
                return deserializeAny(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
        }
    }
}
