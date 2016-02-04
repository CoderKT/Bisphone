package org.jivesoftware.smack.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileUtils {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(FileUtils.class.getName());
    }

    public static InputStream getStreamForUrl(String str, ClassLoader classLoader) {
        URI create = URI.create(str);
        if (create.getScheme() == null) {
            throw new MalformedURLException("No protocol found in file URL: " + str);
        } else if (!create.getScheme().equals("classpath")) {
            return create.toURL().openStream();
        } else {
            List<ClassLoader> classLoaders = getClassLoaders();
            if (classLoader != null) {
                classLoaders.add(0, classLoader);
            }
            for (ClassLoader resourceAsStream : classLoaders) {
                InputStream resourceAsStream2 = resourceAsStream.getResourceAsStream(create.getSchemeSpecificPart());
                if (resourceAsStream2 != null) {
                    return resourceAsStream2;
                }
            }
            return null;
        }
    }

    public static List<ClassLoader> getClassLoaders() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{FileUtils.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List<ClassLoader> arrayList = new ArrayList(classLoaderArr.length);
        int length = classLoaderArr.length;
        while (i < length) {
            Object obj = classLoaderArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
            i++;
        }
        return arrayList;
    }

    public static boolean addLines(String str, Set<String> set) {
        InputStream streamForUrl = getStreamForUrl(str, null);
        if (streamForUrl == null) {
            return false;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamForUrl));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return true;
            }
            set.add(readLine);
        }
    }

    public static String readFileOrThrow(File file) {
        Throwable th;
        Reader fileReader;
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[8192];
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = fileReader.read(cArr);
                    if (read < 0) {
                        break;
                    }
                    stringBuilder.append(cArr, 0, read);
                }
                String stringBuilder2 = stringBuilder.toString();
                if (fileReader != null) {
                    fileReader.close();
                }
                return stringBuilder2;
            } catch (Throwable th2) {
                th = th2;
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static String readFile(File file) {
        try {
            return readFileOrThrow(file);
        } catch (Throwable e) {
            LOGGER.log(Level.FINE, "readFile", e);
            return null;
        } catch (Throwable e2) {
            LOGGER.log(Level.WARNING, "readFile", e2);
            return null;
        }
    }

    public static void writeFileOrThrow(File file, String str) {
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(str);
        fileWriter.close();
    }

    public static boolean writeFile(File file, String str) {
        try {
            writeFileOrThrow(file, str);
            return true;
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, "writeFile", e);
            return false;
        }
    }
}
