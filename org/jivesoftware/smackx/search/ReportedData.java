package org.jivesoftware.smackx.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.FormField.Type;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;

public class ReportedData {
    private List<Column> columns;
    private List<Row> rows;
    private String title;

    public class Column {
        private final String label;
        private final Type type;
        private final String variable;

        public Column(String str, String str2, Type type) {
            this.label = str;
            this.variable = str2;
            this.type = type;
        }

        public String getLabel() {
            return this.label;
        }

        public Type getType() {
            return this.type;
        }

        public String getVariable() {
            return this.variable;
        }
    }

    public class Field {
        private List<String> values;
        private String variable;

        public Field(String str, List<String> list) {
            this.variable = str;
            this.values = list;
        }

        public String getVariable() {
            return this.variable;
        }

        public List<String> getValues() {
            return Collections.unmodifiableList(this.values);
        }
    }

    public class Row {
        private List<Field> fields;

        public Row(List<Field> list) {
            this.fields = new ArrayList();
            this.fields = list;
        }

        public List<String> getValues(String str) {
            for (Field field : getFields()) {
                if (str.equalsIgnoreCase(field.getVariable())) {
                    return field.getValues();
                }
            }
            return null;
        }

        private List<Field> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields));
        }
    }

    public static ReportedData getReportedDataFrom(Stanza stanza) {
        DataForm from = DataForm.from(stanza);
        if (from == null || from.getReportedData() == null) {
            return null;
        }
        return new ReportedData(from);
    }

    private ReportedData(DataForm dataForm) {
        this.columns = new ArrayList();
        this.rows = new ArrayList();
        this.title = "";
        for (FormField formField : dataForm.getReportedData().getFields()) {
            this.columns.add(new Column(formField.getLabel(), formField.getVariable(), formField.getType()));
        }
        for (Item item : dataForm.getItems()) {
            List arrayList = new ArrayList(this.columns.size());
            for (FormField formField2 : item.getFields()) {
                List arrayList2 = new ArrayList();
                for (String add : formField2.getValues()) {
                    arrayList2.add(add);
                }
                arrayList.add(new Field(formField2.getVariable(), arrayList2));
            }
            this.rows.add(new Row(arrayList));
        }
        this.title = dataForm.getTitle();
    }

    public ReportedData() {
        this.columns = new ArrayList();
        this.rows = new ArrayList();
        this.title = "";
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(new ArrayList(this.rows));
    }

    public List<Column> getColumns() {
        return Collections.unmodifiableList(new ArrayList(this.columns));
    }

    public String getTitle() {
        return this.title;
    }
}
