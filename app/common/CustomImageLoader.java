package app.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.widget.ImageView;
import app.Main;
import app.common.entity.HistoryEntity.Type;
import app.http.HttpService;
import app.http.HttpTask;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.http.listener.ITaskListener;
import app.logger.HandledException;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.Utils;
import com.crashlytics.android.Crashlytics;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import cz.msebera.android.httpclient.Header;
import java.io.File;

public class CustomImageLoader {
    private static CustomImageLoader f2000b;
    private DisplayImageOptions f2001a;

    /* renamed from: app.common.CustomImageLoader.1 */
    class C01181 extends FileTaskListener {
        final /* synthetic */ File f1997a;
        final /* synthetic */ ImageView f1998b;
        final /* synthetic */ CustomImageLoader f1999c;

        C01181(CustomImageLoader customImageLoader, File file, File file2, ImageView imageView) {
            this.f1999c = customImageLoader;
            this.f1997a = file2;
            this.f1998b = imageView;
            super(file);
        }

        public void m4007a(int i, Header[] headerArr, File file) {
            file.renameTo(this.f1997a);
            this.f1999c.m4016a(this.f1998b, this.f1997a);
        }

        public void m4008a(int i, Header[] headerArr, File file, Throwable th) {
        }
    }

    static {
        f2000b = null;
    }

    private CustomImageLoader() {
        this.f2001a = new Builder().m11021a(ImageScaleType.IN_SAMPLE_INT).m11019a(Config.ARGB_8888).m11025c(true).m11022a(true).m11024b(false).m11023a();
    }

    public static synchronized CustomImageLoader m4009a() {
        CustomImageLoader customImageLoader;
        synchronized (CustomImageLoader.class) {
            if (f2000b == null) {
                f2000b = new CustomImageLoader();
            }
            customImageLoader = f2000b;
        }
        return customImageLoader;
    }

    public void m4015a(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
        builder.m11117a(new CustomImageDecoder(false));
        ImageLoader.m11076a().m11082a(builder.m11116a(QueueProcessingType.FIFO).m11115a(this.f2001a).m11119b(10).m11112a(3).m11114a(new LruMemoryCache((int) (context.getResources().getDisplayMetrics().density * 1.048576E7f))).m11113a(null).m11118a());
    }

    public void m4021b() {
        ImageLoader.m11076a().m11089b();
    }

    public void m4016a(ImageView imageView, File file) {
        m4019a(imageView, m4011b(file), null);
    }

    public void m4017a(ImageView imageView, File file, ImageLoadingListener imageLoadingListener) {
        m4019a(imageView, m4011b(file), imageLoadingListener);
    }

    public void m4018a(ImageView imageView, String str) {
        m4019a(imageView, str, null);
    }

    public void m4019a(ImageView imageView, String str, ImageLoadingListener imageLoadingListener) {
        ImageLoader.m11076a().m11084a(str, imageView, imageLoadingListener);
    }

    public void m4020a(ImageView imageView, String str, String str2, int i) {
        ImageLoader.m11076a().m11081a(imageView);
        m4010a(imageView, i);
        if (str == null || str.length() == 0) {
            m4012b(imageView, str2);
            return;
        }
        try {
            File file = new File(Utils.m7079a(str, Type.PHOTO));
            File file2 = new File(Storage.m6952g() + File.separator + str);
            if (file.exists()) {
                m4016a(imageView, file);
                return;
            }
            HttpTask a = HttpService.m5565a(str);
            ITaskListener c01181 = new C01181(this, file2, file, imageView);
            if (a != null) {
                a.m5575a(c01181);
                return;
            }
            HttpService.m5567a(new TaskBuilder().m5590a(str).m5585a(Main.f1927b).m5592b(Utils.m7082b(str)).m5589a(new RequestParams("token", str)).m5586a(RequestType.post).m5587a(TaskPriority.medium).m5588a(c01181).m5584a());
        } catch (StorageException e) {
            m4012b(imageView, str2);
        }
    }

    public void m4022b(ImageView imageView, File file) {
        ImageLoader.m11076a().m11083a(m4011b(file), imageView);
    }

    public Bitmap m4013a(ImageSize imageSize, String str) {
        return ImageLoader.m11076a().m11079a(str, imageSize);
    }

    public Bitmap m4014a(File file) {
        return ImageLoader.m11076a().m11078a(m4011b(file));
    }

    private void m4010a(ImageView imageView, int i) {
        imageView.setImageDrawable(Main.f1927b.getResources().getDrawable(i));
    }

    private void m4012b(ImageView imageView, String str) {
        if (str != null) {
            if (str.equals("null")) {
                Crashlytics.m7882a(new HandledException("Contact local avatar uri is \"null\""));
            } else {
                ImageLoader.m11076a().m11083a(str, imageView);
            }
        }
    }

    private String m4011b(File file) {
        return Uri.fromFile(file).toString();
    }
}
