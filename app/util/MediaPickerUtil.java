package app.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.database.datasource.ContactDataSource;
import app.galley.SelectResourceFromSDCardActivity;
import app.galley.external.SystemCropper;
import app.location.MapActivity;
import app.storage.Storage;
import app.storage.StorageException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class MediaPickerUtil {
    public static File m7032a(Activity activity, int i) {
        File file = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        try {
            file = FileUtil.m7016a("camera_image", null, Storage.m6941b());
            intent.putExtra("output", Uri.fromFile(file));
            try {
                activity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                Main.m3905a(activity.getString(2131296810));
                e.printStackTrace();
            }
        } catch (StorageException e2) {
            Main.f1926a.m5679b("Can't create file to take picture!");
            Main.m3905a(activity.getString(2131296838));
            return file;
        } catch (IOException e3) {
            Main.f1926a.m5679b("Can't create file to take picture!");
            Main.m3905a(activity.getString(2131296838));
            return file;
        }
        return file;
    }

    public static void m7035b(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, MapActivity.class), i);
    }

    public static File m7033a(Activity activity, Uri uri, int i) {
        File file = null;
        try {
            file = FileUtil.m7016a("camera_temp", ".jpg", Storage.m6952g());
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            if (activity.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
                intent = new Intent(activity, SelectResourceFromSDCardActivity.class);
                intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_CROP");
                intent.putExtra(DataPacketExtension.ELEMENT, uri.getPath());
                intent.putExtra("output", Uri.fromFile(file).toString());
                activity.startActivityForResult(intent, i);
            } else {
                SystemCropper.m5114a(activity, uri.getPath(), file, i);
            }
        } catch (StorageException e) {
            Throwable e2 = e;
            Main.f1926a.m5682c(e2);
            return file;
        } catch (IOException e3) {
            e2 = e3;
            Main.f1926a.m5682c(e2);
            return file;
        }
        return file;
    }

    public static void m7034a(String str, ImageView imageView) {
        Throwable e;
        try {
            Bitmap a = BitmapUtil.m6971a(str, 1080);
            if (a == null) {
                Main.m3905a(Main.f1927b.getString(2131296837));
                return;
            }
            if (str.startsWith(Storage.m6952g())) {
                new File(str).delete();
            }
            if (Storage.m6939a(Main.f1927b, new File(Storage.m6952g()))) {
                File file = new File(Storage.m6952g() + File.separator + StringUtil.m7063a(4));
                OutputStream fileOutputStream = new FileOutputStream(file, false);
                BitmapUtil.m6975a(a, fileOutputStream);
                a.recycle();
                fileOutputStream.close();
                String b = FileUtil.m7024b(file);
                File file2 = new File(Storage.m6941b() + File.separator + b);
                if (file2.exists()) {
                    file.delete();
                } else {
                    file.renameTo(file2);
                }
                if (ContactDataSource.m4553a().m4580f()) {
                    ContactEntity g = ContactDataSource.m4553a().m4581g();
                    g.m4205j(b);
                    ContactDataSource.m4553a().m4566a(g);
                } else {
                    SharedPreferencesUtil.m7055a(Main.f1927b.getString(2131296919), b);
                }
                CustomImageLoader.m4009a().m4020a(imageView, b, null, 2130837596);
                return;
            }
            a.recycle();
        } catch (StorageException e2) {
            e = e2;
            Main.f1926a.m5678a(e);
        } catch (IOException e3) {
            e = e3;
            Main.f1926a.m5678a(e);
        }
    }
}
