package org.jivesoftware.smackx.search;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smackx.search.ReportedData.Column;
import org.jivesoftware.smackx.search.ReportedData.Field;
import org.jivesoftware.smackx.search.ReportedData.Row;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.FormField.Type;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;

class SimpleUserSearch extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:search";
    private ReportedData data;
    private Form form;

    public SimpleUserSearch() {
        super(ELEMENT, NAMESPACE);
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public ReportedData getReportedData() {
        return this.data;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        iQChildElementXmlStringBuilder.append(getItemsToSearch());
        return iQChildElementXmlStringBuilder;
    }

    private String getItemsToSearch() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.form == null) {
            this.form = Form.getFormFrom(this);
        }
        if (this.form == null) {
            return "";
        }
        for (FormField formField : this.form.getFields()) {
            String variable = formField.getVariable();
            String singleValue = getSingleValue(formField);
            if (singleValue.trim().length() > 0) {
                stringBuilder.append("<").append(variable).append(">").append(singleValue).append("</").append(variable).append(">");
            }
        }
        return stringBuilder.toString();
    }

    private static String getSingleValue(FormField formField) {
        List values = formField.getValues();
        if (values.isEmpty()) {
            return "";
        }
        return (String) values.get(0);
    }

    protected void parseItems(XmlPullParser xmlPullParser) {
        ReportedData reportedData = new ReportedData();
        reportedData.addColumn(new Column("JID", "jid", Type.text_single));
        List arrayList = new ArrayList();
        Object obj = null;
        while (obj == null) {
            ArrayList arrayList2;
            Object obj2;
            if (xmlPullParser.getAttributeCount() > 0) {
                String attributeValue = xmlPullParser.getAttributeValue("", "jid");
                List arrayList3 = new ArrayList();
                arrayList3.add(attributeValue);
                arrayList.add(new Field("jid", arrayList3));
            }
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals(Item.ELEMENT)) {
                arrayList2 = new ArrayList();
                obj2 = obj;
            } else if (next == 3 && xmlPullParser.getName().equals(Item.ELEMENT)) {
                reportedData.addRow(new Row(arrayList));
                r0 = arrayList;
                obj2 = obj;
            } else if (next == 2) {
                Object obj3;
                String name = xmlPullParser.getName();
                attributeValue = xmlPullParser.nextText();
                List arrayList4 = new ArrayList();
                arrayList4.add(attributeValue);
                arrayList.add(new Field(name, arrayList4));
                for (Column variable : reportedData.getColumns()) {
                    if (variable.getVariable().equals(name)) {
                        obj3 = 1;
                        break;
                    }
                }
                obj3 = null;
                if (obj3 == null) {
                    reportedData.addColumn(new Column(name, name, Type.text_single));
                }
                r0 = arrayList;
                obj2 = obj;
            } else if (next == 3 && xmlPullParser.getName().equals(ELEMENT)) {
                r0 = arrayList;
                int i = 1;
            } else {
                r0 = arrayList;
                obj2 = obj;
            }
            obj = obj2;
            obj2 = arrayList2;
        }
        this.data = reportedData;
    }
}
