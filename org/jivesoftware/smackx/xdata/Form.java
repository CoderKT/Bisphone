package org.jivesoftware.smackx.xdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.xdata.FormField.Type;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import se.emilsjolander.stickylistheaders.C1128R;

public class Form {
    private DataForm dataForm;

    /* renamed from: org.jivesoftware.smackx.xdata.Form.1 */
    /* synthetic */ class C11181 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type;

        static {
            $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.text_multi.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.text_private.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.text_single.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.jid_single.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.hidden.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.jid_multi.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.list_multi.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.list_single.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static Form getFormFrom(Stanza stanza) {
        DataForm from = DataForm.from(stanza);
        if (from == null || from.getReportedData() != null) {
            return null;
        }
        return new Form(from);
    }

    public Form(DataForm dataForm) {
        this.dataForm = dataForm;
    }

    public Form(DataForm.Type type) {
        this.dataForm = new DataForm(type);
    }

    public void addField(FormField formField) {
        this.dataForm.addField(formField);
    }

    public void setAnswer(String str, String str2) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        switch (C11181.$SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[field.getType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                setAnswer(field, (Object) str2);
            default:
                throw new IllegalArgumentException("This field is not of type String.");
        }
    }

    public void setAnswer(String str, int i) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        validateThatFieldIsText(field);
        setAnswer(field, Integer.valueOf(i));
    }

    public void setAnswer(String str, long j) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        validateThatFieldIsText(field);
        setAnswer(field, Long.valueOf(j));
    }

    public void setAnswer(String str, float f) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        validateThatFieldIsText(field);
        setAnswer(field, Float.valueOf(f));
    }

    public void setAnswer(String str, double d) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        validateThatFieldIsText(field);
        setAnswer(field, Double.valueOf(d));
    }

    private static void validateThatFieldIsText(FormField formField) {
        switch (C11181.$SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[formField.getType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            default:
                throw new IllegalArgumentException("This field is not of type text (multi, private or single).");
        }
    }

    public void setAnswer(String str, boolean z) {
        FormField field = getField(str);
        if (field == null) {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else if (field.getType() != Type.bool) {
            throw new IllegalArgumentException("This field is not of type boolean.");
        } else {
            setAnswer(field, z ? "1" : "0");
        }
    }

    private void setAnswer(FormField formField, Object obj) {
        if (isSubmitType()) {
            formField.resetValues();
            formField.addValue(obj.toString());
            return;
        }
        throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
    }

    public void setAnswer(String str, List<String> list) {
        if (isSubmitType()) {
            FormField field = getField(str);
            if (field != null) {
                switch (C11181.$SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[field.getType().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                        field.resetValues();
                        field.addValues(list);
                        return;
                    default:
                        throw new IllegalArgumentException("This field only accept list of values.");
                }
            }
            throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
        }
        throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
    }

    public void setDefaultAnswer(String str) {
        if (isSubmitType()) {
            FormField field = getField(str);
            if (field != null) {
                field.resetValues();
                for (String addValue : field.getValues()) {
                    field.addValue(addValue);
                }
                return;
            }
            throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
        }
        throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
    }

    public List<FormField> getFields() {
        return this.dataForm.getFields();
    }

    public FormField getField(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Variable must not be null or blank.");
        }
        for (FormField formField : getFields()) {
            if (str.equals(formField.getVariable())) {
                return formField;
            }
        }
        return null;
    }

    public String getInstructions() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.dataForm.getInstructions().iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next());
            if (it.hasNext()) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String getTitle() {
        return this.dataForm.getTitle();
    }

    public DataForm.Type getType() {
        return this.dataForm.getType();
    }

    public void setInstructions(String str) {
        List arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        this.dataForm.setInstructions(arrayList);
    }

    public void setTitle(String str) {
        this.dataForm.setTitle(str);
    }

    public DataForm getDataFormToSend() {
        if (!isSubmitType()) {
            return this.dataForm;
        }
        DataForm dataForm = new DataForm(getType());
        for (FormField formField : getFields()) {
            if (!formField.getValues().isEmpty()) {
                dataForm.addField(formField);
            }
        }
        return dataForm;
    }

    private boolean isFormType() {
        return DataForm.Type.form == this.dataForm.getType();
    }

    private boolean isSubmitType() {
        return DataForm.Type.submit == this.dataForm.getType();
    }

    public Form createAnswerForm() {
        if (isFormType()) {
            Form form = new Form(DataForm.Type.submit);
            for (FormField formField : getFields()) {
                if (formField.getVariable() != null) {
                    FormField formField2 = new FormField(formField.getVariable());
                    formField2.setType(formField.getType());
                    form.addField(formField2);
                    if (formField.getType() == Type.hidden) {
                        List arrayList = new ArrayList();
                        for (String add : formField.getValues()) {
                            arrayList.add(add);
                        }
                        form.setAnswer(formField.getVariable(), arrayList);
                    }
                }
            }
            return form;
        }
        throw new IllegalStateException("Only forms of type \"form\" could be answered");
    }
}
