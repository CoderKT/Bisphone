package org.jivesoftware.smackx.xdatavalidation.provider;

import java.util.logging.Logger;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.BasicValidateElement;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.ListRange;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.OpenValidateElement;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.RangeValidateElement;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.RegexValidateElement;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class DataValidationProvider {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(DataValidationProvider.class.getName());
    }

    public static ValidateElement parse(XmlPullParser xmlPullParser) {
        ListRange listRange = null;
        int depth = xmlPullParser.getDepth();
        String attributeValue = xmlPullParser.getAttributeValue("", "datatype");
        ValidateElement validateElement = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -725250226:
                            if (name.equals(ListRange.ELEMENT)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 3417674:
                            if (name.equals(OpenValidateElement.METHOD)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 93508654:
                            if (name.equals(BasicValidateElement.METHOD)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 108280125:
                            if (name.equals(RangeValidateElement.METHOD)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 108392519:
                            if (name.equals(RegexValidateElement.METHOD)) {
                                obj = 3;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            validateElement = new OpenValidateElement(attributeValue);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            validateElement = new BasicValidateElement(attributeValue);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            validateElement = new RangeValidateElement(attributeValue, xmlPullParser.getAttributeValue("", "min"), xmlPullParser.getAttributeValue("", "max"));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            validateElement = new RegexValidateElement(attributeValue, xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            Long longAttribute = ParserUtils.getLongAttribute(xmlPullParser, "min");
                            Long longAttribute2 = ParserUtils.getLongAttribute(xmlPullParser, "max");
                            if (longAttribute != null || longAttribute2 != null) {
                                listRange = new ListRange(longAttribute, longAttribute2);
                                break;
                            }
                            LOGGER.fine("Ignoring list-range element without min or max attribute");
                            break;
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    if (validateElement == null) {
                        validateElement = new BasicValidateElement(attributeValue);
                    }
                    validateElement.setListRange(listRange);
                    return validateElement;
                default:
                    break;
            }
        }
    }
}
