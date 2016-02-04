package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class EventsFilesManager<T> {
    protected final Context f5475a;
    protected final EventTransform<T> f5476b;
    protected final CurrentTimeProvider f5477c;
    protected final EventsStorage f5478d;
    protected volatile long f5479e;
    protected final List<EventsStorageListener> f5480f;
    private final int f5481g;

    /* renamed from: io.fabric.sdk.android.services.events.EventsFilesManager.1 */
    class C09671 implements Comparator<FileWithTimestamp> {
        final /* synthetic */ EventsFilesManager f8314a;

        C09671(EventsFilesManager eventsFilesManager) {
            this.f8314a = eventsFilesManager;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m13137a((FileWithTimestamp) obj, (FileWithTimestamp) obj2);
        }

        public int m13137a(FileWithTimestamp fileWithTimestamp, FileWithTimestamp fileWithTimestamp2) {
            return (int) (fileWithTimestamp.f8316b - fileWithTimestamp2.f8316b);
        }
    }

    class FileWithTimestamp {
        final File f8315a;
        final long f8316b;

        public FileWithTimestamp(File file, long j) {
            this.f8315a = file;
            this.f8316b = j;
        }
    }

    protected abstract String m8160a();

    public EventsFilesManager(Context context, EventTransform<T> eventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage, int i) {
        this.f5480f = new CopyOnWriteArrayList();
        this.f5475a = context.getApplicationContext();
        this.f5476b = eventTransform;
        this.f5478d = eventsStorage;
        this.f5477c = currentTimeProvider;
        this.f5479e = this.f5477c.m13041a();
        this.f5481g = i;
    }

    public void m8162a(T t) {
        byte[] a = this.f5476b.m8180a(t);
        m8157a(a.length);
        this.f5478d.m13142a(a);
    }

    public void m8161a(EventsStorageListener eventsStorageListener) {
        if (eventsStorageListener != null) {
            this.f5480f.add(eventsStorageListener);
        }
    }

    public boolean m8166d() {
        boolean z = true;
        String str = null;
        if (this.f5478d.m13144b()) {
            z = false;
        } else {
            str = m8160a();
            this.f5478d.m13140a(str);
            CommonUtils.m13013a(this.f5475a, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{str}));
            this.f5479e = this.f5477c.m13041a();
        }
        m8158b(str);
        return z;
    }

    private void m8157a(int i) {
        if (!this.f5478d.m13143a(i, m8165c())) {
            CommonUtils.m13013a(this.f5475a, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.f5478d.m13138a()), Integer.valueOf(i), Integer.valueOf(m8165c())}));
            m8166d();
        }
    }

    protected int m8164b() {
        return this.f5481g;
    }

    protected int m8165c() {
        return 8000;
    }

    private void m8158b(String str) {
        for (EventsStorageListener a : this.f5480f) {
            try {
                a.m8184a(str);
            } catch (Throwable e) {
                CommonUtils.m13015a(this.f5475a, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public List<File> m8167e() {
        return this.f5478d.m13139a(1);
    }

    public void m8163a(List<File> list) {
        this.f5478d.m13141a((List) list);
    }

    public void m8168f() {
        this.f5478d.m13141a(this.f5478d.m13145c());
        this.f5478d.m13146d();
    }

    public void m8169g() {
        List<File> c = this.f5478d.m13145c();
        int b = m8164b();
        if (c.size() > b) {
            int size = c.size() - b;
            CommonUtils.m13014a(this.f5475a, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(b), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new C09671(this));
            for (File file : c) {
                treeSet.add(new FileWithTimestamp(file, m8159a(file.getName())));
            }
            List arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((FileWithTimestamp) it.next()).f8315a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.f5478d.m13141a(arrayList);
        }
    }

    public long m8159a(String str) {
        long j = 0;
        String[] split = str.split("_");
        if (split.length == 3) {
            try {
                j = Long.valueOf(split[2]).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
