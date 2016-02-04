package org.jivesoftware.smackx.vcardtemp.packet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.vcardtemp.VCardManager;

public class VCard extends IQ {
    private static final String DEFAULT_MIME_TYPE = "image/jpeg";
    public static final String ELEMENT = "vCard";
    private static final Logger LOGGER;
    public static final String NAMESPACE = "vcard-temp";
    private String emailHome;
    private String emailWork;
    private String firstName;
    private Map<String, String> homeAddr;
    private Map<String, String> homePhones;
    private String lastName;
    private String middleName;
    private String organization;
    private String organizationUnit;
    private Map<String, String> otherSimpleFields;
    private Map<String, String> otherUnescapableFields;
    private String photoBinval;
    private String photoMimeType;
    private String prefix;
    private String suffix;
    private Map<String, String> workAddr;
    private Map<String, String> workPhones;

    static {
        LOGGER = Logger.getLogger(VCard.class.getName());
    }

    public VCard() {
        super(ELEMENT, NAMESPACE);
        this.homePhones = new HashMap();
        this.workPhones = new HashMap();
        this.homeAddr = new HashMap();
        this.workAddr = new HashMap();
        this.otherSimpleFields = new HashMap();
        this.otherUnescapableFields = new HashMap();
    }

    public String getField(String str) {
        return (String) this.otherSimpleFields.get(str);
    }

    public void setField(String str, String str2) {
        setField(str, str2, false);
    }

    public void setField(String str, String str2, boolean z) {
        if (z) {
            this.otherUnescapableFields.put(str, str2);
        } else {
            this.otherSimpleFields.put(str, str2);
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
        updateFN();
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
        updateFN();
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String str) {
        this.middleName = str;
        updateFN();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String str) {
        this.prefix = str;
        updateFN();
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String str) {
        this.suffix = str;
        updateFN();
    }

    public String getNickName() {
        return (String) this.otherSimpleFields.get("NICKNAME");
    }

    public void setNickName(String str) {
        this.otherSimpleFields.put("NICKNAME", str);
    }

    public String getEmailHome() {
        return this.emailHome;
    }

    public void setEmailHome(String str) {
        this.emailHome = str;
    }

    public String getEmailWork() {
        return this.emailWork;
    }

    public void setEmailWork(String str) {
        this.emailWork = str;
    }

    public String getJabberId() {
        return (String) this.otherSimpleFields.get("JABBERID");
    }

    public void setJabberId(String str) {
        this.otherSimpleFields.put("JABBERID", str);
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String str) {
        this.organization = str;
    }

    public String getOrganizationUnit() {
        return this.organizationUnit;
    }

    public void setOrganizationUnit(String str) {
        this.organizationUnit = str;
    }

    public String getAddressFieldHome(String str) {
        return (String) this.homeAddr.get(str);
    }

    public void setAddressFieldHome(String str, String str2) {
        this.homeAddr.put(str, str2);
    }

    public String getAddressFieldWork(String str) {
        return (String) this.workAddr.get(str);
    }

    public void setAddressFieldWork(String str, String str2) {
        this.workAddr.put(str, str2);
    }

    public void setPhoneHome(String str, String str2) {
        this.homePhones.put(str, str2);
    }

    public String getPhoneHome(String str) {
        return (String) this.homePhones.get(str);
    }

    public void setPhoneWork(String str, String str2) {
        this.workPhones.put(str, str2);
    }

    public String getPhoneWork(String str) {
        return (String) this.workPhones.get(str);
    }

    public void setAvatar(URL url) {
        byte[] bArr = new byte[0];
        try {
            bArr = getBytes(url);
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Error getting bytes from URL: " + url, e);
        }
        setAvatar(bArr);
    }

    public void removeAvatar() {
        this.photoBinval = null;
        this.photoMimeType = null;
    }

    public void setAvatar(byte[] bArr) {
        setAvatar(bArr, DEFAULT_MIME_TYPE);
    }

    public void setAvatar(byte[] bArr, String str) {
        if (bArr == null) {
            removeAvatar();
        } else {
            setAvatar(Base64.encodeToString(bArr), str);
        }
    }

    public void setAvatar(String str, String str2) {
        this.photoBinval = str;
        this.photoMimeType = str2;
    }

    @Deprecated
    public void setEncodedImage(String str) {
        setAvatar(str, DEFAULT_MIME_TYPE);
    }

    public byte[] getAvatar() {
        if (this.photoBinval == null) {
            return null;
        }
        return Base64.decode(this.photoBinval);
    }

    public String getAvatarMimeType() {
        return this.photoMimeType;
    }

    public static byte[] getBytes(URL url) {
        File file = new File(url.getPath());
        if (file.exists()) {
            return getFileBytes(file);
        }
        return null;
    }

    private static byte[] getFileBytes(File file) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[((int) file.length())];
                if (bufferedInputStream.read(bArr) != bArr.length) {
                    throw new IOException("Entire file not read");
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public String getAvatarHash() {
        String str = null;
        byte[] avatar = getAvatar();
        if (avatar == null) {
            return str;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(StringUtils.SHA1);
            instance.update(avatar);
            return StringUtils.encodeHex(instance.digest());
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Failed to get message digest", e);
            return str;
        }
    }

    private void updateFN() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.firstName != null) {
            stringBuilder.append(StringUtils.escapeForXML(this.firstName)).append(' ');
        }
        if (this.middleName != null) {
            stringBuilder.append(StringUtils.escapeForXML(this.middleName)).append(' ');
        }
        if (this.lastName != null) {
            stringBuilder.append(StringUtils.escapeForXML(this.lastName));
        }
        setField("FN", stringBuilder.toString());
    }

    @Deprecated
    public void save(XMPPConnection xMPPConnection) {
        VCardManager.getInstanceFor(xMPPConnection).saveVCard(this);
    }

    @Deprecated
    public void load(XMPPConnection xMPPConnection) {
        load(xMPPConnection, null);
    }

    @Deprecated
    public void load(XMPPConnection xMPPConnection, String str) {
        copyFieldsFrom(VCardManager.getInstanceFor(xMPPConnection).loadVCard(str));
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        if (hasContent()) {
            String str;
            iQChildElementXmlStringBuilder.rightAngleBracket();
            if (hasNameField()) {
                iQChildElementXmlStringBuilder.openElement("N");
                iQChildElementXmlStringBuilder.optElement("FAMILY", this.lastName);
                iQChildElementXmlStringBuilder.optElement("GIVEN", this.firstName);
                iQChildElementXmlStringBuilder.optElement("MIDDLE", this.middleName);
                iQChildElementXmlStringBuilder.optElement("PREFIX", this.prefix);
                iQChildElementXmlStringBuilder.optElement("SUFFIX", this.suffix);
                iQChildElementXmlStringBuilder.closeElement("N");
            }
            if (hasOrganizationFields()) {
                iQChildElementXmlStringBuilder.openElement("ORG");
                iQChildElementXmlStringBuilder.optElement("ORGNAME", this.organization);
                iQChildElementXmlStringBuilder.optElement("ORGUNIT", this.organizationUnit);
                iQChildElementXmlStringBuilder.closeElement("ORG");
            }
            for (Entry entry : this.otherSimpleFields.entrySet()) {
                iQChildElementXmlStringBuilder.optElement((String) entry.getKey(), (String) entry.getValue());
            }
            for (Entry entry2 : this.otherUnescapableFields.entrySet()) {
                str = (String) entry2.getValue();
                if (str != null) {
                    iQChildElementXmlStringBuilder.openElement((String) entry2.getKey());
                    iQChildElementXmlStringBuilder.append((CharSequence) str);
                    iQChildElementXmlStringBuilder.closeElement((String) entry2.getKey());
                }
            }
            if (this.photoBinval != null) {
                iQChildElementXmlStringBuilder.openElement("PHOTO");
                iQChildElementXmlStringBuilder.escapedElement("BINVAL", this.photoBinval);
                iQChildElementXmlStringBuilder.element("TYPE", this.photoMimeType);
                iQChildElementXmlStringBuilder.closeElement("PHOTO");
            }
            if (this.emailWork != null) {
                iQChildElementXmlStringBuilder.openElement("EMAIL");
                iQChildElementXmlStringBuilder.emptyElement("WORK");
                iQChildElementXmlStringBuilder.emptyElement("INTERNET");
                iQChildElementXmlStringBuilder.emptyElement("PREF");
                iQChildElementXmlStringBuilder.element("USERID", this.emailWork);
                iQChildElementXmlStringBuilder.closeElement("EMAIL");
            }
            if (this.emailHome != null) {
                iQChildElementXmlStringBuilder.openElement("EMAIL");
                iQChildElementXmlStringBuilder.emptyElement("HOME");
                iQChildElementXmlStringBuilder.emptyElement("INTERNET");
                iQChildElementXmlStringBuilder.emptyElement("PREF");
                iQChildElementXmlStringBuilder.element("USERID", this.emailHome);
                iQChildElementXmlStringBuilder.closeElement("EMAIL");
            }
            for (Entry entry22 : this.workPhones.entrySet()) {
                str = (String) entry22.getValue();
                if (str != null) {
                    iQChildElementXmlStringBuilder.openElement("TEL");
                    iQChildElementXmlStringBuilder.emptyElement("WORK");
                    iQChildElementXmlStringBuilder.emptyElement((String) entry22.getKey());
                    iQChildElementXmlStringBuilder.element("NUMBER", str);
                    iQChildElementXmlStringBuilder.closeElement("TEL");
                }
            }
            for (Entry entry222 : this.homePhones.entrySet()) {
                str = (String) entry222.getValue();
                if (str != null) {
                    iQChildElementXmlStringBuilder.openElement("TEL");
                    iQChildElementXmlStringBuilder.emptyElement("HOME");
                    iQChildElementXmlStringBuilder.emptyElement((String) entry222.getKey());
                    iQChildElementXmlStringBuilder.element("NUMBER", str);
                    iQChildElementXmlStringBuilder.closeElement("TEL");
                }
            }
            if (!this.workAddr.isEmpty()) {
                iQChildElementXmlStringBuilder.openElement("ADR");
                iQChildElementXmlStringBuilder.emptyElement("WORK");
                for (Entry entry2222 : this.workAddr.entrySet()) {
                    str = (String) entry2222.getValue();
                    if (str != null) {
                        iQChildElementXmlStringBuilder.element((String) entry2222.getKey(), str);
                    }
                }
                iQChildElementXmlStringBuilder.closeElement("ADR");
            }
            if (!this.homeAddr.isEmpty()) {
                iQChildElementXmlStringBuilder.openElement("ADR");
                iQChildElementXmlStringBuilder.emptyElement("HOME");
                for (Entry entry22222 : this.homeAddr.entrySet()) {
                    str = (String) entry22222.getValue();
                    if (str != null) {
                        iQChildElementXmlStringBuilder.element((String) entry22222.getKey(), str);
                    }
                }
                iQChildElementXmlStringBuilder.closeElement("ADR");
            }
        } else {
            iQChildElementXmlStringBuilder.setEmptyElement();
        }
        return iQChildElementXmlStringBuilder;
    }

    private void copyFieldsFrom(VCard vCard) {
        for (Field field : VCard.class.getDeclaredFields()) {
            if (field.getDeclaringClass() == VCard.class && !Modifier.isFinal(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    field.set(this, field.get(vCard));
                } catch (Throwable e) {
                    throw new RuntimeException("This cannot happen:" + field, e);
                }
            }
        }
    }

    private boolean hasContent() {
        return hasNameField() || hasOrganizationFields() || this.emailHome != null || this.emailWork != null || this.otherSimpleFields.size() > 0 || this.otherUnescapableFields.size() > 0 || this.homeAddr.size() > 0 || this.homePhones.size() > 0 || this.workAddr.size() > 0 || this.workPhones.size() > 0 || this.photoBinval != null;
    }

    private boolean hasNameField() {
        return (this.firstName == null && this.lastName == null && this.middleName == null && this.prefix == null && this.suffix == null) ? false : true;
    }

    private boolean hasOrganizationFields() {
        return (this.organization == null && this.organizationUnit == null) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCard vCard = (VCard) obj;
        if (this.emailHome != null) {
            if (!this.emailHome.equals(vCard.emailHome)) {
                return false;
            }
        } else if (vCard.emailHome != null) {
            return false;
        }
        if (this.emailWork != null) {
            if (!this.emailWork.equals(vCard.emailWork)) {
                return false;
            }
        } else if (vCard.emailWork != null) {
            return false;
        }
        if (this.firstName != null) {
            if (!this.firstName.equals(vCard.firstName)) {
                return false;
            }
        } else if (vCard.firstName != null) {
            return false;
        }
        if (!this.homeAddr.equals(vCard.homeAddr) || !this.homePhones.equals(vCard.homePhones)) {
            return false;
        }
        if (this.lastName != null) {
            if (!this.lastName.equals(vCard.lastName)) {
                return false;
            }
        } else if (vCard.lastName != null) {
            return false;
        }
        if (this.middleName != null) {
            if (!this.middleName.equals(vCard.middleName)) {
                return false;
            }
        } else if (vCard.middleName != null) {
            return false;
        }
        if (this.organization != null) {
            if (!this.organization.equals(vCard.organization)) {
                return false;
            }
        } else if (vCard.organization != null) {
            return false;
        }
        if (this.organizationUnit != null) {
            if (!this.organizationUnit.equals(vCard.organizationUnit)) {
                return false;
            }
        } else if (vCard.organizationUnit != null) {
            return false;
        }
        if (!this.otherSimpleFields.equals(vCard.otherSimpleFields) || !this.workAddr.equals(vCard.workAddr)) {
            return false;
        }
        if (this.photoBinval != null) {
            if (!this.photoBinval.equals(vCard.photoBinval)) {
                return false;
            }
        } else if (vCard.photoBinval != null) {
            return false;
        }
        return this.workPhones.equals(vCard.workPhones);
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((this.firstName != null ? this.firstName.hashCode() : 0) + (((((((this.homePhones.hashCode() * 29) + this.workPhones.hashCode()) * 29) + this.homeAddr.hashCode()) * 29) + this.workAddr.hashCode()) * 29)) * 29;
        if (this.lastName != null) {
            hashCode = this.lastName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 29;
        if (this.middleName != null) {
            hashCode = this.middleName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 29;
        if (this.emailHome != null) {
            hashCode = this.emailHome.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 29;
        if (this.emailWork != null) {
            hashCode = this.emailWork.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 29;
        if (this.organization != null) {
            hashCode = this.organization.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 29;
        if (this.organizationUnit != null) {
            hashCode = this.organizationUnit.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + hashCode2) * 29) + this.otherSimpleFields.hashCode()) * 29;
        if (this.photoBinval != null) {
            i = this.photoBinval.hashCode();
        }
        return hashCode + i;
    }
}
