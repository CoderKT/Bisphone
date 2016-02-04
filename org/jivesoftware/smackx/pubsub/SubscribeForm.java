package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UnknownFormatConversionException;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.packet.DataForm.Type;
import org.jxmpp.util.XmppDateTime;

public class SubscribeForm extends Form {
    public SubscribeForm(DataForm dataForm) {
        super(dataForm);
    }

    public SubscribeForm(Form form) {
        super(form.getDataFormToSend());
    }

    public SubscribeForm(Type type) {
        super(type);
    }

    public boolean isDeliverOn() {
        return parseBoolean(getFieldValue(SubscribeOptionFields.deliver));
    }

    public void setDeliverOn(boolean z) {
        addField(SubscribeOptionFields.deliver, FormField.Type.bool);
        setAnswer(SubscribeOptionFields.deliver.getFieldName(), z);
    }

    public boolean isDigestOn() {
        return parseBoolean(getFieldValue(SubscribeOptionFields.digest));
    }

    public void setDigestOn(boolean z) {
        addField(SubscribeOptionFields.deliver, FormField.Type.bool);
        setAnswer(SubscribeOptionFields.deliver.getFieldName(), z);
    }

    public int getDigestFrequency() {
        return Integer.parseInt(getFieldValue(SubscribeOptionFields.digest_frequency));
    }

    public void setDigestFrequency(int i) {
        addField(SubscribeOptionFields.digest_frequency, FormField.Type.text_single);
        setAnswer(SubscribeOptionFields.digest_frequency.getFieldName(), i);
    }

    public Date getExpiry() {
        String fieldValue = getFieldValue(SubscribeOptionFields.expire);
        try {
            return XmppDateTime.m13435b(fieldValue);
        } catch (Throwable e) {
            UnknownFormatConversionException unknownFormatConversionException = new UnknownFormatConversionException(fieldValue);
            unknownFormatConversionException.initCause(e);
            throw unknownFormatConversionException;
        }
    }

    public void setExpiry(Date date) {
        addField(SubscribeOptionFields.expire, FormField.Type.text_single);
        setAnswer(SubscribeOptionFields.expire.getFieldName(), XmppDateTime.m13428a(date));
    }

    public boolean isIncludeBody() {
        return parseBoolean(getFieldValue(SubscribeOptionFields.include_body));
    }

    public void setIncludeBody(boolean z) {
        addField(SubscribeOptionFields.include_body, FormField.Type.bool);
        setAnswer(SubscribeOptionFields.include_body.getFieldName(), z);
    }

    public List<PresenceState> getShowValues() {
        List arrayList = new ArrayList(5);
        for (String valueOf : getFieldValues(SubscribeOptionFields.show_values)) {
            arrayList.add(PresenceState.valueOf(valueOf));
        }
        return arrayList;
    }

    public void setShowValues(Collection<PresenceState> collection) {
        List arrayList = new ArrayList(collection.size());
        for (PresenceState presenceState : collection) {
            arrayList.add(presenceState.toString());
        }
        addField(SubscribeOptionFields.show_values, FormField.Type.list_multi);
        setAnswer(SubscribeOptionFields.show_values.getFieldName(), arrayList);
    }

    private static boolean parseBoolean(String str) {
        return "1".equals(str) || "true".equals(str);
    }

    private String getFieldValue(SubscribeOptionFields subscribeOptionFields) {
        return (String) getField(subscribeOptionFields.getFieldName()).getValues().get(0);
    }

    private List<String> getFieldValues(SubscribeOptionFields subscribeOptionFields) {
        return getField(subscribeOptionFields.getFieldName()).getValues();
    }

    private void addField(SubscribeOptionFields subscribeOptionFields, FormField.Type type) {
        String fieldName = subscribeOptionFields.getFieldName();
        if (getField(fieldName) == null) {
            FormField formField = new FormField(fieldName);
            formField.setType(type);
            addField(formField);
        }
    }
}
