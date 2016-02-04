package org.jivesoftware.smackx.caps.cache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.stringencoder.Base32;
import org.jivesoftware.smack.util.stringencoder.StringEncoder;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;

public class SimpleDirectoryPersistentCache implements EntityCapsPersistentCache {
    private static final Logger LOGGER;
    private File cacheDir;
    private StringEncoder filenameEncoder;

    static {
        LOGGER = Logger.getLogger(SimpleDirectoryPersistentCache.class.getName());
    }

    public SimpleDirectoryPersistentCache(File file) {
        this(file, Base32.getStringEncoder());
    }

    public SimpleDirectoryPersistentCache(File file, StringEncoder stringEncoder) {
        if (!file.exists()) {
            throw new IllegalStateException("Cache directory \"" + file + "\" does not exist");
        } else if (file.isDirectory()) {
            this.cacheDir = file;
            this.filenameEncoder = stringEncoder;
        } else {
            throw new IllegalStateException("Cache directory \"" + file + "\" is not a directory");
        }
    }

    public void addDiscoverInfoByNodePersistent(String str, DiscoverInfo discoverInfo) {
        File fileFor = getFileFor(str);
        try {
            if (fileFor.createNewFile()) {
                writeInfoToFile(fileFor, discoverInfo);
            }
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Failed to write disco info to file", e);
        }
    }

    public DiscoverInfo lookup(String str) {
        DiscoverInfo discoverInfo = null;
        File fileFor = getFileFor(str);
        if (fileFor.isFile()) {
            try {
                discoverInfo = restoreInfoFromFile(fileFor);
            } catch (Throwable e) {
                LOGGER.log(Level.WARNING, "Coud not restore info from file", e);
            }
        }
        return discoverInfo;
    }

    private File getFileFor(String str) {
        return new File(this.cacheDir, this.filenameEncoder.encode(str));
    }

    public void emptyCache() {
        for (File delete : this.cacheDir.listFiles()) {
            delete.delete();
        }
    }

    private static void writeInfoToFile(File file, DiscoverInfo discoverInfo) {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeUTF(discoverInfo.toXML().toString());
        } finally {
            dataOutputStream.close();
        }
    }

    private static DiscoverInfo restoreInfoFromFile(File file) {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            String readUTF = dataInputStream.readUTF();
            if (readUTF == null) {
                return null;
            }
            return (DiscoverInfo) PacketParserUtils.parseStanza(readUTF);
        } finally {
            dataInputStream.close();
        }
    }
}
