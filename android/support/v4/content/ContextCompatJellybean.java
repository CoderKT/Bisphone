package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class ContextCompatJellybean {
    public static void m609a(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
    }
}
