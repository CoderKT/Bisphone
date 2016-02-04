package org.jivesoftware.smack.util;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ObservableWriter extends Writer {
    List<WriterListener> listeners;
    Writer wrappedWriter;

    public ObservableWriter(Writer writer) {
        this.wrappedWriter = null;
        this.listeners = new ArrayList();
        this.wrappedWriter = writer;
    }

    public void write(char[] cArr, int i, int i2) {
        this.wrappedWriter.write(cArr, i, i2);
        notifyListeners(new String(cArr, i, i2));
    }

    public void flush() {
        this.wrappedWriter.flush();
    }

    public void close() {
        this.wrappedWriter.close();
    }

    public void write(int i) {
        this.wrappedWriter.write(i);
    }

    public void write(char[] cArr) {
        this.wrappedWriter.write(cArr);
        notifyListeners(new String(cArr));
    }

    public void write(String str) {
        this.wrappedWriter.write(str);
        notifyListeners(str);
    }

    public void write(String str, int i, int i2) {
        this.wrappedWriter.write(str, i, i2);
        notifyListeners(str.substring(i, i + i2));
    }

    private void notifyListeners(String str) {
        synchronized (this.listeners) {
            WriterListener[] writerListenerArr = new WriterListener[this.listeners.size()];
            this.listeners.toArray(writerListenerArr);
        }
        for (WriterListener write : writerListenerArr) {
            write.write(str);
        }
    }

    public void addWriterListener(WriterListener writerListener) {
        if (writerListener != null) {
            synchronized (this.listeners) {
                if (!this.listeners.contains(writerListener)) {
                    this.listeners.add(writerListener);
                }
            }
        }
    }

    public void removeWriterListener(WriterListener writerListener) {
        synchronized (this.listeners) {
            this.listeners.remove(writerListener);
        }
    }
}
