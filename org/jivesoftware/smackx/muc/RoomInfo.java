package org.jivesoftware.smackx.muc;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

public class RoomInfo {
    private static final Logger LOGGER;
    private final List<String> contactJid;
    private final String description;
    private final Form form;
    private final String lang;
    private final String ldapgroup;
    private final URL logs;
    private final int maxhistoryfetch;
    private final boolean membersOnly;
    private final boolean moderated;
    private final String name;
    private final boolean nonanonymous;
    private final int occupantsCount;
    private final boolean passwordProtected;
    private final boolean persistent;
    private final String pubsub;
    private final String room;
    private final String subject;
    private final Boolean subjectmod;

    static {
        LOGGER = Logger.getLogger(RoomInfo.class.getName());
    }

    RoomInfo(DiscoverInfo discoverInfo) {
        String str;
        int i;
        List list;
        String str2;
        String str3;
        Boolean bool;
        URL url;
        int i2 = -1;
        String str4 = null;
        this.room = discoverInfo.getFrom();
        this.membersOnly = discoverInfo.containsFeature("muc_membersonly");
        this.moderated = discoverInfo.containsFeature("muc_moderated");
        this.nonanonymous = discoverInfo.containsFeature("muc_nonanonymous");
        this.passwordProtected = discoverInfo.containsFeature("muc_passwordprotected");
        this.persistent = discoverInfo.containsFeature("muc_persistent");
        List identities = discoverInfo.getIdentities();
        if (identities.isEmpty()) {
            LOGGER.warning("DiscoverInfo does not contain any Identity: " + discoverInfo.toXML());
            this.name = "";
        } else {
            this.name = ((Identity) identities.get(0)).getName();
        }
        String str5 = "";
        String str6 = "";
        this.form = Form.getFormFrom(discoverInfo);
        if (this.form != null) {
            FormField field = this.form.getField("muc#roominfo_description");
            if (field == null || field.getValues().isEmpty()) {
                str = str6;
            } else {
                str = (String) field.getValues().get(0);
            }
            FormField field2 = this.form.getField("muc#roominfo_subject");
            if (!(field2 == null || field2.getValues().isEmpty())) {
                str5 = (String) field2.getValues().get(0);
            }
            field2 = this.form.getField("muc#roominfo_occupants");
            if (field2 == null || field2.getValues().isEmpty()) {
                i = -1;
            } else {
                i = Integer.parseInt((String) field2.getValues().get(0));
            }
            field2 = this.form.getField("muc#maxhistoryfetch");
            if (!(field2 == null || field2.getValues().isEmpty())) {
                i2 = Integer.parseInt((String) field2.getValues().get(0));
            }
            field2 = this.form.getField("muc#roominfo_contactjid");
            if (field2 == null || field2.getValues().isEmpty()) {
                list = null;
            } else {
                list = field2.getValues();
            }
            field2 = this.form.getField("muc#roominfo_lang");
            if (field2 == null || field2.getValues().isEmpty()) {
                str2 = null;
            } else {
                str2 = (String) field2.getValues().get(0);
            }
            field2 = this.form.getField("muc#roominfo_ldapgroup");
            if (field2 == null || field2.getValues().isEmpty()) {
                str3 = null;
            } else {
                str3 = (String) field2.getValues().get(0);
            }
            field2 = this.form.getField("muc#roominfo_subjectmod");
            if (field2 == null || field2.getValues().isEmpty()) {
                bool = null;
            } else {
                bool = Boolean.valueOf((String) field2.getValues().get(0));
            }
            field2 = this.form.getField("muc#roominfo_logs");
            if (!(field2 == null || field2.getValues().isEmpty())) {
                try {
                    url = new URL((String) field2.getValues().get(0));
                } catch (Throwable e) {
                    LOGGER.log(Level.SEVERE, "Could not parse URL", e);
                }
                field2 = this.form.getField("muc#roominfo_pubsub");
                if (!(field2 == null || field2.getValues().isEmpty())) {
                    str4 = (String) field2.getValues().get(0);
                }
            }
            url = null;
            field2 = this.form.getField("muc#roominfo_pubsub");
            str4 = (String) field2.getValues().get(0);
        } else {
            url = null;
            bool = null;
            str3 = null;
            str2 = null;
            list = null;
            str = str6;
            i = -1;
        }
        this.description = str;
        this.subject = str5;
        this.occupantsCount = i;
        this.maxhistoryfetch = i2;
        this.contactJid = list;
        this.lang = str2;
        this.ldapgroup = str3;
        this.subjectmod = bool;
        this.logs = url;
        this.pubsub = str4;
    }

    public String getRoom() {
        return this.room;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getOccupantsCount() {
        return this.occupantsCount;
    }

    public boolean isMembersOnly() {
        return this.membersOnly;
    }

    public boolean isModerated() {
        return this.moderated;
    }

    public boolean isNonanonymous() {
        return this.nonanonymous;
    }

    public boolean isPasswordProtected() {
        return this.passwordProtected;
    }

    public boolean isPersistent() {
        return this.persistent;
    }

    public int getMaxHistoryFetch() {
        return this.maxhistoryfetch;
    }

    public List<String> getContactJids() {
        return this.contactJid;
    }

    public String getLang() {
        return this.lang;
    }

    public String getLdapGroup() {
        return this.ldapgroup;
    }

    public Boolean isSubjectModifiable() {
        return this.subjectmod;
    }

    public String getPubSub() {
        return this.pubsub;
    }

    public URL getLogsUrl() {
        return this.logs;
    }

    public Form getForm() {
        return this.form;
    }
}
