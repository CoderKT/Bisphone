package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import se.emilsjolander.stickylistheaders.C1128R;

/* compiled from: JsonNodeDeserializer */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    public BaseNodeDeserializer(Class<T> cls) {
        super((Class) cls);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected void _reportProblem(JsonParser jsonParser, String str) {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    @Deprecated
    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) {
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            _reportProblem(jsonParser, "Duplicate field '" + str + "' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled");
        }
        _handleDuplicateField(str, objectNode, jsonNode, jsonNode2);
    }

    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            JsonNode deserializeObject;
            String currentName = jsonParser.getCurrentName();
            switch (jsonParser.nextToken().id()) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    deserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    deserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    deserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    deserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    deserializeObject = jsonNodeFactory.booleanNode(true);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    deserializeObject = jsonNodeFactory.booleanNode(false);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    deserializeObject = jsonNodeFactory.nullNode();
                    break;
                default:
                    deserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                    break;
            }
            JsonNode replace = objectNode.replace(currentName, deserializeObject);
            if (replace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, currentName, objectNode, replace, deserializeObject);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != null) {
                switch (nextToken.id()) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        return arrayNode;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        arrayNode.add(_fromInt(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                        arrayNode.add(jsonNodeFactory.booleanNode(true));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                        arrayNode.add(jsonNodeFactory.booleanNode(false));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                        arrayNode.add(jsonNodeFactory.nullNode());
                        break;
                    default:
                        arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                        break;
                }
            }
            throw deserializationContext.mappingException("Unexpected end-of-input when binding data into ArrayNode");
        }
    }

    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        switch (jsonParser.getCurrentTokenId()) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return _fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return jsonNodeFactory.booleanNode(true);
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return jsonNodeFactory.booleanNode(false);
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return jsonNodeFactory.nullNode();
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                return _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
            default:
                throw deserializationContext.mappingException(handledType());
        }
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        NumberType numberType = jsonParser.getNumberType();
        if (numberType == NumberType.BIG_INTEGER || deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
            return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
        }
        if (numberType == NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getLongValue());
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        if (jsonParser.getNumberType() == NumberType.BIG_DECIMAL || deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) {
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return jsonNodeFactory.nullNode();
        }
        Class cls = embeddedObject.getClass();
        if (cls == byte[].class) {
            return jsonNodeFactory.binaryNode((byte[]) embeddedObject);
        }
        if (JsonNode.class.isAssignableFrom(cls)) {
            return (JsonNode) embeddedObject;
        }
        return jsonNodeFactory.pojoNode(embeddedObject);
    }
}
