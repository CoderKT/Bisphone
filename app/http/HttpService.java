package app.http;

import android.os.Looper;
import app.Main;
import app.http.listener.FileTaskListener;
import app.storage.Storage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;
import cz.msebera.android.httpclient.Header;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import se.emilsjolander.stickylistheaders.C1128R;

public class HttpService {
    private static AsyncHttpClient f3076a;
    private static volatile Queue<HttpTask> f3077b;
    private static volatile Map<String, HttpTask> f3078c;
    private static volatile Map<String, Object> f3079d;
    private static final Object f3080e;

    /* renamed from: app.http.HttpService.1 */
    final class C02361 extends FileAsyncHttpResponseHandler {
        float f3071a;
        final /* synthetic */ HttpTask f3072b;
        final /* synthetic */ FileTaskListener f3073c;

        C02361(File file, boolean z, boolean z2, boolean z3, HttpTask httpTask, FileTaskListener fileTaskListener) {
            this.f3072b = httpTask;
            this.f3073c = fileTaskListener;
            super(file, z, z2, z3);
            this.f3071a = 0.0f;
        }

        public void m5551a() {
            this.f3072b.m5582h().m3990f();
        }

        public void m5555a(long j, long j2) {
            float f = ((float) j) / ((float) j2);
            this.f3072b.m5574a(f);
            if (f > this.f3071a) {
                this.f3071a = (float) (((double) this.f3071a) + 0.04d);
                this.f3072b.m5582h().m3988a(j, j2);
            }
        }

        public void m5552a(int i) {
            this.f3072b.m5582h().m3985a(i);
        }

        public void m5554a(int i, Header[] headerArr, Throwable th, File file) {
            this.f3073c.m3996a(i, headerArr, file, th);
        }

        public void m5553a(int i, Header[] headerArr, File file) {
            this.f3073c.m3995a(i, headerArr, file);
        }

        public void m5556b() {
            HttpService.m5571c(this.f3072b);
            this.f3072b.m5582h().m3991g();
            HttpService.m5568b();
        }

        public void m5557c() {
            HttpService.m5571c(this.f3072b);
            this.f3072b.m5582h().m3992h();
            HttpService.m5568b();
        }
    }

    /* renamed from: app.http.HttpService.2 */
    final class C02372 extends AsyncHttpResponseHandler {
        final /* synthetic */ HttpTask f3074a;

        C02372(Looper looper, HttpTask httpTask) {
            this.f3074a = httpTask;
            super(looper);
        }

        public void m5558a() {
            this.f3074a.m5582h().m3990f();
        }

        public void m5562a(long j, long j2) {
            this.f3074a.m5574a(((float) j) / ((float) j2));
            this.f3074a.m5582h().m3988a(j, j2);
        }

        public void m5559a(int i) {
            this.f3074a.m5582h().m3985a(i);
        }

        public void m5560a(int i, Header[] headerArr, byte[] bArr) {
            this.f3074a.m5582h().m3986a(i, headerArr, bArr);
        }

        public void m5561a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            this.f3074a.m5582h().m3987a(i, headerArr, bArr, th);
        }

        public void m5563b() {
            HttpService.m5571c(this.f3074a);
            this.f3074a.m5582h().m3991g();
            HttpService.m5568b();
        }

        public void m5564c() {
            HttpService.m5571c(this.f3074a);
            this.f3074a.m5582h().m3992h();
            HttpService.m5568b();
        }
    }

    /* renamed from: app.http.HttpService.3 */
    /* synthetic */ class C02383 {
        static final /* synthetic */ int[] f3075a;

        static {
            f3075a = new int[RequestType.values().length];
            try {
                f3075a[RequestType.get.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3075a[RequestType.post.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3075a[RequestType.delete.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3075a[RequestType.head.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static {
        f3076a = new AsyncHttpClient();
        f3077b = new PriorityBlockingQueue(4);
        f3078c = new HashMap(4);
        f3079d = new HashMap(4);
        f3080e = new Object();
        f3076a.m10582a(new ThreadPoolExecutor(2, 2, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(20, true)));
        f3076a.m10580a(20000);
    }

    public static HttpTask m5565a(String str) {
        return (HttpTask) f3078c.get(str);
    }

    public static void m5567a(HttpTask httpTask) {
        if (f3077b.size() != 20) {
            f3077b.add(httpTask);
            f3078c.put(httpTask.m5573a(), httpTask);
            httpTask.m5582h().m3989e();
            if (f3077b.size() == 1) {
                synchronized (f3080e) {
                    if (f3077b.size() == 1) {
                        m5568b();
                    }
                }
            }
        }
    }

    public static void m5570b(String str) {
        HttpTask a = m5565a(str);
        if (a == null) {
            return;
        }
        if (f3079d.get(str) != null) {
            f3076a.m10581a(f3079d.get(str), true);
            return;
        }
        m5571c(a);
        a.m5582h().m3991g();
        m5568b();
    }

    private static void m5568b() {
        HttpTask httpTask = (HttpTask) f3077b.peek();
        if (httpTask != null) {
            ResponseHandlerInterface c02361;
            RequestHandle b;
            if ((httpTask.m5582h() instanceof FileTaskListener) && Storage.m6942b(Main.f1927b)) {
                FileTaskListener fileTaskListener = (FileTaskListener) httpTask.m5582h();
                c02361 = new C02361(fileTaskListener.m3993a(), fileTaskListener.m4000b(), fileTaskListener.m4001c(), fileTaskListener.m4002d(), httpTask, fileTaskListener);
            } else if (Storage.m6942b(Main.f1927b)) {
                c02361 = new C02372(Looper.getMainLooper(), httpTask);
            } else {
                Storage.m6945c(Main.f1927b);
                return;
            }
            switch (C02383.f3075a[httpTask.m5581g().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    b = f3076a.m10584b(httpTask.m5576b(), httpTask.m5577c(), httpTask.m5579e(), httpTask.m5578d(), c02361);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    b = f3076a.m10574a(httpTask.m5576b(), httpTask.m5577c(), httpTask.m5579e(), httpTask.m5578d(), httpTask.m5580f(), c02361);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    b = f3076a.m10587c(httpTask.m5576b(), httpTask.m5577c(), httpTask.m5579e(), httpTask.m5578d(), c02361);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    b = f3076a.m10573a(httpTask.m5576b(), httpTask.m5577c(), httpTask.m5579e(), httpTask.m5578d(), c02361);
                    break;
                default:
                    throw new IllegalStateException("Invalid Task Type");
            }
            b.m10728a(new Object());
            f3079d.put(httpTask.m5573a(), b.m10733d());
        }
    }

    private static void m5571c(HttpTask httpTask) {
        f3077b.remove(httpTask);
        f3078c.remove(httpTask.m5573a());
        f3079d.remove(httpTask.m5573a());
    }
}
