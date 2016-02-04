package android.support.v4.os;

import android.os.AsyncTask;
import android.os.Build.VERSION;

public class AsyncTaskCompat {
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> m691a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        if (VERSION.SDK_INT >= 11) {
            AsyncTaskCompatHoneycomb.m692a(asyncTask, paramsArr);
        } else {
            asyncTask.execute(paramsArr);
        }
        return asyncTask;
    }
}
