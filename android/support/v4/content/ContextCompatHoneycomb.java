package android.support.v4.content;

import android.content.Context;
import android.content.Intent;

class ContextCompatHoneycomb {
    static void m608a(Context context, Intent[] intentArr) {
        context.startActivities(intentArr);
    }
}
