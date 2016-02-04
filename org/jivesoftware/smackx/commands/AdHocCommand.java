package org.jivesoftware.smackx.commands;

import java.util.List;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.Form;

public abstract class AdHocCommand {
    private AdHocCommandData data;

    public enum Action {
        execute,
        cancel,
        prev,
        next,
        complete,
        unknown
    }

    public enum SpecificErrorCondition {
        badAction("bad-action"),
        malformedAction("malformed-action"),
        badLocale("bad-locale"),
        badPayload("bad-payload"),
        badSessionid("bad-sessionid"),
        sessionExpired("session-expired");
        
        private String value;

        private SpecificErrorCondition(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Status {
        executing,
        completed,
        canceled
    }

    public abstract void cancel();

    public abstract void complete(Form form);

    public abstract void execute();

    public abstract String getOwnerJID();

    public abstract void next(Form form);

    public abstract void prev();

    public AdHocCommand() {
        this.data = new AdHocCommandData();
    }

    public static SpecificErrorCondition getSpecificErrorCondition(XMPPError xMPPError) {
        for (SpecificErrorCondition specificErrorCondition : SpecificErrorCondition.values()) {
            if (xMPPError.getExtension(specificErrorCondition.toString(), AdHocCommandData.NAMESPACE) != null) {
                return specificErrorCondition;
            }
        }
        return null;
    }

    public void setName(String str) {
        this.data.setName(str);
    }

    public String getName() {
        return this.data.getName();
    }

    public void setNode(String str) {
        this.data.setNode(str);
    }

    public String getNode() {
        return this.data.getNode();
    }

    public List<AdHocCommandNote> getNotes() {
        return this.data.getNotes();
    }

    protected void addNote(AdHocCommandNote adHocCommandNote) {
        this.data.addNote(adHocCommandNote);
    }

    public String getRaw() {
        return this.data.getChildElementXML().toString();
    }

    public Form getForm() {
        if (this.data.getForm() == null) {
            return null;
        }
        return new Form(this.data.getForm());
    }

    protected void setForm(Form form) {
        this.data.setForm(form.getDataFormToSend());
    }

    protected List<Action> getActions() {
        return this.data.getActions();
    }

    protected void addActionAvailable(Action action) {
        this.data.addAction(action);
    }

    protected Action getExecuteAction() {
        return this.data.getExecuteAction();
    }

    protected void setExecuteAction(Action action) {
        this.data.setExecuteAction(action);
    }

    public Status getStatus() {
        return this.data.getStatus();
    }

    void setData(AdHocCommandData adHocCommandData) {
        this.data = adHocCommandData;
    }

    AdHocCommandData getData() {
        return this.data;
    }

    protected boolean isValidAction(Action action) {
        return getActions().contains(action) || Action.cancel.equals(action);
    }
}
