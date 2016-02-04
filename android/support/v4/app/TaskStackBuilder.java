package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskStackBuilder implements Iterable<Intent> {
    private static final TaskStackBuilderImpl f339a;
    private final ArrayList<Intent> f340b;
    private final Context f341c;

    interface TaskStackBuilderImpl {
    }

    class TaskStackBuilderImplBase implements TaskStackBuilderImpl {
        TaskStackBuilderImplBase() {
        }
    }

    class TaskStackBuilderImplHoneycomb implements TaskStackBuilderImpl {
        TaskStackBuilderImplHoneycomb() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f339a = new TaskStackBuilderImplHoneycomb();
        } else {
            f339a = new TaskStackBuilderImplBase();
        }
    }

    private TaskStackBuilder(Context context) {
        this.f340b = new ArrayList();
        this.f341c = context;
    }

    public static TaskStackBuilder m603a(Context context) {
        return new TaskStackBuilder(context);
    }

    public TaskStackBuilder m604a(Intent intent) {
        this.f340b.add(intent);
        return this;
    }

    public Iterator<Intent> iterator() {
        return this.f340b.iterator();
    }

    public void m605a() {
        m606a(null);
    }

    public void m606a(Bundle bundle) {
        if (this.f340b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.f340b.toArray(new Intent[this.f340b.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (!ContextCompat.m99a(this.f341c, intentArr, bundle)) {
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            intent.addFlags(268435456);
            this.f341c.startActivity(intent);
        }
    }
}
