package app.http;

import android.content.Context;
import app.http.listener.ITaskListener;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

public class HttpTask implements Comparable<HttpTask> {
    private String f3081a;
    private Context f3082b;
    private String f3083c;
    private RequestParams f3084d;
    private Header[] f3085e;
    private String f3086f;
    private RequestType f3087g;
    private TaskPriority f3088h;
    private ITaskListener f3089i;
    private float f3090j;

    public /* synthetic */ int compareTo(Object obj) {
        return m5572a((HttpTask) obj);
    }

    HttpTask(TaskBuilder taskBuilder) {
        this.f3090j = 0.0f;
        this.f3081a = taskBuilder.f3096a;
        this.f3082b = taskBuilder.f3097b;
        this.f3083c = taskBuilder.f3098c;
        this.f3084d = taskBuilder.f3099d;
        this.f3085e = taskBuilder.f3100e;
        this.f3086f = taskBuilder.f3101f;
        this.f3087g = taskBuilder.f3102g;
        this.f3088h = taskBuilder.f3103h;
        this.f3089i = taskBuilder.f3104i;
    }

    public String m5573a() {
        return this.f3081a;
    }

    public Context m5576b() {
        return this.f3082b;
    }

    public String m5577c() {
        return this.f3083c;
    }

    public RequestParams m5578d() {
        return this.f3084d;
    }

    public Header[] m5579e() {
        return this.f3085e;
    }

    public String m5580f() {
        return this.f3086f;
    }

    public RequestType m5581g() {
        return this.f3087g;
    }

    public ITaskListener m5582h() {
        return this.f3089i;
    }

    public float m5583i() {
        return this.f3090j;
    }

    public void m5575a(ITaskListener iTaskListener) {
        this.f3089i = iTaskListener;
    }

    void m5574a(float f) {
        this.f3090j = f;
    }

    public int m5572a(HttpTask httpTask) {
        return this.f3088h.ordinal() - httpTask.f3088h.ordinal();
    }
}
