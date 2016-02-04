package io.fabric.sdk.android.services.events;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPQueueFileEventStorage extends QueueFileEventStorage {
    public GZIPQueueFileEventStorage(Context context, File file, String str, String str2) {
        super(context, file, str, str2);
    }

    public OutputStream m13159a(File file) {
        return new GZIPOutputStream(new FileOutputStream(file));
    }
}
