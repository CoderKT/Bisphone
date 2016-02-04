package org.jivesoftware.smack.roster.rosterstore;

import java.io.File;
import java.io.FileFilter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemType;
import org.jivesoftware.smack.util.FileUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base32;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class DirectoryRosterStore implements RosterStore {
    private static final String ENTRY_PREFIX = "entry-";
    private static final Logger LOGGER;
    private static final String STORE_ID = "DEFAULT_ROSTER_STORE";
    private static final String VERSION_FILE_NAME = "__version__";
    private static final FileFilter rosterDirFilter;
    private final File fileDir;

    /* renamed from: org.jivesoftware.smack.roster.rosterstore.DirectoryRosterStore.1 */
    final class C10141 implements FileFilter {
        C10141() {
        }

        public boolean accept(File file) {
            return file.getName().startsWith(DirectoryRosterStore.ENTRY_PREFIX);
        }
    }

    static {
        LOGGER = Logger.getLogger(DirectoryRosterStore.class.getName());
        rosterDirFilter = new C10141();
    }

    private DirectoryRosterStore(File file) {
        this.fileDir = file;
    }

    public static DirectoryRosterStore init(File file) {
        DirectoryRosterStore directoryRosterStore = new DirectoryRosterStore(file);
        return directoryRosterStore.setRosterVersion("") ? directoryRosterStore : null;
    }

    public static DirectoryRosterStore open(File file) {
        DirectoryRosterStore directoryRosterStore = new DirectoryRosterStore(file);
        String readFile = FileUtils.readFile(directoryRosterStore.getVersionFile());
        return (readFile == null || !readFile.startsWith("DEFAULT_ROSTER_STORE\n")) ? null : directoryRosterStore;
    }

    private File getVersionFile() {
        return new File(this.fileDir, VERSION_FILE_NAME);
    }

    public List<Item> getEntries() {
        List<Item> arrayList = new ArrayList();
        for (File file : this.fileDir.listFiles(rosterDirFilter)) {
            Item readEntry = readEntry(file);
            if (readEntry == null) {
                log("Roster store file '" + file + "' is invalid.");
            } else {
                arrayList.add(readEntry);
            }
        }
        return arrayList;
    }

    public Item getEntry(String str) {
        return readEntry(getBareJidFile(str));
    }

    public String getRosterVersion() {
        String readFile = FileUtils.readFile(getVersionFile());
        if (readFile == null) {
            return null;
        }
        String[] split = readFile.split("\n", 2);
        if (split.length >= 2) {
            return split[1];
        }
        return null;
    }

    private boolean setRosterVersion(String str) {
        return FileUtils.writeFile(getVersionFile(), "DEFAULT_ROSTER_STORE\n" + str);
    }

    public boolean addEntry(Item item, String str) {
        return addEntryRaw(item) && setRosterVersion(str);
    }

    public boolean removeEntry(String str, String str2) {
        return getBareJidFile(str).delete() && setRosterVersion(str2);
    }

    public boolean resetEntries(Collection<Item> collection, String str) {
        for (File delete : this.fileDir.listFiles(rosterDirFilter)) {
            delete.delete();
        }
        for (Item addEntryRaw : collection) {
            if (!addEntryRaw(addEntryRaw)) {
                return false;
            }
        }
        return setRosterVersion(str);
    }

    private Item readEntry(File file) {
        String readFile = FileUtils.readFile(file);
        if (readFile == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(readFile));
            Object obj = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            while (obj == null) {
                int next = newPullParser.next();
                String name = newPullParser.getName();
                if (next == 2) {
                    if (name.equals(DataForm.Item.ELEMENT)) {
                        str = null;
                        str2 = null;
                        str3 = null;
                        str4 = null;
                    } else if (name.equals("user")) {
                        newPullParser.next();
                        str4 = newPullParser.getText();
                    } else if (name.equals("name")) {
                        newPullParser.next();
                        str3 = newPullParser.getText();
                    } else if (name.equals("type")) {
                        newPullParser.next();
                        str2 = newPullParser.getText();
                    } else if (name.equals(Status.ELEMENT)) {
                        newPullParser.next();
                        str = newPullParser.getText();
                    } else if (name.equals(Item.GROUP)) {
                        newPullParser.next();
                        newPullParser.next();
                        String text = newPullParser.getText();
                        if (text != null) {
                            arrayList.add(text);
                        } else {
                            log("Invalid group entry in store entry file " + file);
                        }
                    } else {
                        continue;
                    }
                } else if (next != 3) {
                    continue;
                } else if (name.equals(DataForm.Item.ELEMENT)) {
                    obj = 1;
                }
            }
            if (str4 == null) {
                return null;
            }
            Item item = new Item(str4, str3);
            for (String readFile2 : arrayList) {
                item.addGroupName(readFile2);
            }
            if (str2 != null) {
                try {
                    item.setItemType(ItemType.valueOf(str2));
                    if (str != null) {
                        ItemStatus fromString = ItemStatus.fromString(str);
                        if (fromString == null) {
                            log("Invalid status in store entry file " + file);
                            return null;
                        }
                        item.setItemStatus(fromString);
                    }
                } catch (IllegalArgumentException e) {
                    log("Invalid type in store entry file " + file);
                    return null;
                }
            }
            return item;
        } catch (Throwable e2) {
            LOGGER.log(Level.SEVERE, "readEntry()", e2);
            return null;
        } catch (Throwable e22) {
            log("Invalid group entry in store entry file " + file);
            LOGGER.log(Level.SEVERE, "readEntry()", e22);
            return null;
        }
    }

    private boolean addEntryRaw(Item item) {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.openElement(DataForm.Item.ELEMENT);
        xmlStringBuilder.element("user", item.getUser());
        xmlStringBuilder.optElement("name", item.getName());
        xmlStringBuilder.optElement("type", item.getItemType());
        xmlStringBuilder.optElement(Status.ELEMENT, item.getItemStatus());
        for (String str : item.getGroupNames()) {
            xmlStringBuilder.openElement(Item.GROUP);
            xmlStringBuilder.element("groupName", str);
            xmlStringBuilder.closeElement(Item.GROUP);
        }
        xmlStringBuilder.closeElement(DataForm.Item.ELEMENT);
        return FileUtils.writeFile(getBareJidFile(item.getUser()), xmlStringBuilder.toString());
    }

    private File getBareJidFile(String str) {
        return new File(this.fileDir, ENTRY_PREFIX + Base32.encode(str));
    }

    private void log(String str) {
        System.err.println(str);
    }
}
