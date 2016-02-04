package org.jivesoftware.smackx.commands;

import org.jivesoftware.smackx.commands.packet.AdHocCommandData;

public abstract class LocalCommand extends AdHocCommand {
    private long creationDate;
    private int currenStage;
    private String ownerJID;
    private String sessionID;

    public abstract boolean hasPermission(String str);

    public abstract boolean isLastStage();

    public LocalCommand() {
        this.creationDate = System.currentTimeMillis();
        this.currenStage = -1;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
        getData().setSessionID(str);
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setOwnerJID(String str) {
        this.ownerJID = str;
    }

    public String getOwnerJID() {
        return this.ownerJID;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    public int getCurrentStage() {
        return this.currenStage;
    }

    void setData(AdHocCommandData adHocCommandData) {
        adHocCommandData.setSessionID(this.sessionID);
        super.setData(adHocCommandData);
    }

    void incrementStage() {
        this.currenStage++;
    }

    void decrementStage() {
        this.currenStage--;
    }
}
