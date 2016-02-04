package app.storage;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import app.Main;
import java.io.File;
import java.io.IOException;
import se.emilsjolander.stickylistheaders.C1128R;

public class Storage {
    public static boolean m6936a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean m6937a(Context context) {
        boolean z = false;
        if (!m6936a() || !m6942b(context)) {
            return z;
        }
        try {
            return m6940a(new File(m6956j())) & (((((((m6940a(new File(m6941b())) & m6940a(new File(m6944c()))) & m6940a(new File(m6946d()))) & m6940a(new File(m6948e()))) & m6940a(new File(m6950f()))) & m6940a(new File(m6952g()))) & m6940a(new File(m6954h()))) & m6940a(new File(m6955i())));
        } catch (StorageException e) {
            return z;
        }
    }

    private static boolean m6940a(File file) {
        return file.exists() ? file.isDirectory() : file.mkdirs();
    }

    public static boolean m6939a(Context context, File file) {
        if (file.exists()) {
            return file.isDirectory();
        }
        return m6936a() && m6942b(context) && file.mkdirs();
    }

    public static boolean m6943b(Context context, File file) {
        boolean startsWith = file.getPath().startsWith(context.getFilesDir().getParent());
        try {
            if (file.exists()) {
                return file.isFile();
            }
            if (startsWith) {
                return file.createNewFile();
            }
            if (m6936a() && m6942b(context) && file.createNewFile()) {
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private static String m6958l() {
        File externalCacheDir = Main.f1927b.getExternalCacheDir();
        if (externalCacheDir != null) {
            return externalCacheDir.getAbsolutePath();
        }
        throw new StorageException("external storage is not currently mounted so it could not ensure the path exists");
    }

    private static String m6959m() {
        File externalFilesDir = Main.f1927b.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return externalFilesDir.getAbsolutePath();
        }
        throw new StorageException("external storage is not currently mounted so it could not ensure the path exists");
    }

    private static String m6960n() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.getAbsolutePath();
        }
        throw new StorageException("external storage is not currently mounted so it could not ensure the path exists");
    }

    public static String m6941b() {
        return m6958l() + File.separator + "Media/Images";
    }

    public static String m6944c() {
        return m6958l() + File.separator + "Media/Videos";
    }

    public static String m6946d() {
        return m6958l() + File.separator + "Media/Thumbnails";
    }

    public static String m6948e() {
        return m6958l() + File.separator + "Media/Audios";
    }

    public static String m6950f() {
        return m6958l() + File.separator + "Media/Uploads";
    }

    public static String m6952g() {
        return m6958l() + File.separator + "Media/.tmp";
    }

    public static String m6954h() {
        return m6958l() + File.separator + "Media/Backgrounds";
    }

    public static String m6955i() {
        return m6959m() + File.separator + "Stickers";
    }

    public static String m6956j() {
        return m6960n() + File.separator + "BisPhone";
    }

    public static boolean m6942b(Context context) {
        return m6938a(context, 20971520);
    }

    public static boolean m6938a(Context context, long j) {
        if (context == null) {
            return false;
        }
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir == null || externalFilesDir.getUsableSpace() <= 5242880 + j) {
            return false;
        }
        return true;
    }

    public static void m6945c(Context context) {
        String externalStorageState = Environment.getExternalStorageState();
        Object obj = -1;
        switch (externalStorageState.hashCode()) {
            case -903566235:
                if (externalStorageState.equals("shared")) {
                    obj = 1;
                    break;
                }
                break;
            case 1242932856:
                if (externalStorageState.equals("mounted")) {
                    obj = 2;
                    break;
                }
                break;
            case 1299749220:
                if (externalStorageState.equals("mounted_ro")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                m6947d(context);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m6949e(context);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (!m6942b(context)) {
                    m6953g(context);
                }
            default:
                m6951f(context);
        }
    }

    private static void m6947d(Context context) {
        m6935a(context, context.getResources().getString(2131296780));
    }

    private static void m6949e(Context context) {
        m6935a(context, context.getResources().getString(2131296781));
    }

    private static void m6951f(Context context) {
        m6935a(context, context.getResources().getString(2131296777));
    }

    private static void m6953g(Context context) {
        m6935a(context, context.getResources().getString(2131296776));
    }

    private static void m6935a(Context context, String str) {
        AlertDialog b = new Builder(context, 2131558538).m1974a(2131296331).m1982a(false).m1989c(2130837731).m1981a(Main.f1927b.getString(2131296624), null).m1986b((CharSequence) str).m1988b();
        b.setCanceledOnTouchOutside(false);
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                b.show();
            }
        } catch (Exception e) {
        }
    }

    public static void m6957k() {
        System.gc();
    }
}
