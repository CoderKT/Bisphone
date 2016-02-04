package app.http;

import android.content.Context;
import app.http.listener.ITaskListener;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

public class TaskBuilder {
    String f3096a;
    Context f3097b;
    String f3098c;
    RequestParams f3099d;
    Header[] f3100e;
    String f3101f;
    RequestType f3102g;
    TaskPriority f3103h;
    ITaskListener f3104i;

    public TaskBuilder() {
        this.f3096a = null;
        this.f3097b = null;
        this.f3098c = null;
        this.f3099d = null;
        this.f3100e = null;
        this.f3101f = null;
        this.f3102g = null;
        this.f3103h = TaskPriority.medium;
        this.f3104i = null;
    }

    public TaskBuilder m5590a(String str) {
        this.f3096a = str;
        return this;
    }

    public TaskBuilder m5585a(Context context) {
        this.f3097b = context;
        return this;
    }

    public TaskBuilder m5592b(String str) {
        this.f3098c = str;
        return this;
    }

    public TaskBuilder m5589a(RequestParams requestParams) {
        this.f3099d = requestParams;
        return this;
    }

    public TaskBuilder m5591a(Header[] headerArr) {
        this.f3100e = headerArr;
        return this;
    }

    public TaskBuilder m5586a(RequestType requestType) {
        this.f3102g = requestType;
        return this;
    }

    public TaskBuilder m5587a(TaskPriority taskPriority) {
        this.f3103h = taskPriority;
        return this;
    }

    public TaskBuilder m5588a(ITaskListener iTaskListener) {
        this.f3104i = iTaskListener;
        return this;
    }

    public HttpTask m5584a() {
        if (this.f3102g != null) {
            return new HttpTask(this);
        }
        throw new IllegalArgumentException("Invalid RequestType!");
    }
}
