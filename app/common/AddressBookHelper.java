package app.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import app.Main;
import app.common.entity.ContactEntity;
import app.util.PhoneNumberUtil;
import app.util.SdkVersionUtil;
import app.util.SharedPreferencesUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddressBookHelper {
    static final Uri f1962a;
    static final Uri f1963b;
    static final String f1964c;
    static final String f1965d;
    static final String[] f1966e;
    static final String[] f1967f;
    public static String[] f1968g;
    private static AddressBookHelper f1969h;

    static {
        f1962a = Contacts.CONTENT_URI;
        f1963b = Contacts.CONTENT_FILTER_URI;
        f1964c = (SdkVersionUtil.m7049a() ? "display_name" : "display_name") + "<>''" + " AND " + "in_visible_group" + "=1";
        f1965d = SdkVersionUtil.m7049a() ? "sort_key" : "display_name";
        f1966e = new String[]{"_id", "lookup", m3942a(), m3945b(), f1965d};
        f1967f = new String[]{"_id", "contact_id", "display_name", "account_type"};
        f1968g = new String[]{"lookup", "_id", m3945b(), m3942a()};
        f1969h = null;
    }

    private static String m3942a() {
        return SdkVersionUtil.m7049a() ? "display_name" : "display_name";
    }

    private static String m3945b() {
        return SdkVersionUtil.m7049a() ? "photo_thumb_uri" : "_id";
    }

    public static List<ContactEntity> m3943a(Context context) {
        List<ContactEntity> arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"_id", "contact_id", "raw_contact_id", "display_name", "data1", "version"}, null, null, "data1 COLLATE NOCASE DESC");
        if (query == null) {
            Main.f1926a.m5677a("DO NOT IGNORE ME!!!");
            Main.f1926a.m5677a("getContactsHavingPhoneNumber resultCursor is null!");
            return null;
        }
        Main.f1926a.m5681c("Reading Contacts, query result count = " + query.getCount());
        if (query.moveToFirst()) {
            String a = SharedPreferencesUtil.m7051a(2131296925, context);
            do {
                ContactEntity contactEntity = new ContactEntity();
                contactEntity.m4190c(query.getString(query.getColumnIndexOrThrow("contact_id"))).m4180a(query.getLong(query.getColumnIndexOrThrow("_id"))).m4194d(query.getString(query.getColumnIndexOrThrow("display_name"))).m4195e(query.getString(query.getColumnIndexOrThrow("data1"))).m4186b(query.getLong(query.getColumnIndexOrThrow("version")));
                String c = PhoneNumberUtil.m7047c(a, contactEntity.m4198f());
                if (c != null) {
                    contactEntity.m4197f(c);
                    arrayList.add(contactEntity);
                }
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public static long m3944b(Context context) {
        Cursor query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"version"}, null, null, "display_name COLLATE NOCASE ASC");
        long j = 0;
        if (query == null) {
            Main.f1926a.m5677a("DO NOT IGNORE ME!!!");
            Main.f1926a.m5677a("getContactsVersionSum resultCursor is null!");
        } else {
            if (query.moveToFirst()) {
                do {
                    j += query.getLong(0);
                } while (query.moveToNext());
            }
            query.close();
        }
        return j;
    }

    public static Bitmap m3940a(ContentResolver contentResolver, Uri uri, boolean z) {
        Bitmap bitmap = null;
        try {
            InputStream openContactPhotoInputStream = Contacts.openContactPhotoInputStream(contentResolver, uri, z);
            if (openContactPhotoInputStream != null) {
                bitmap = BitmapFactory.decodeStream(openContactPhotoInputStream);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

    public static Uri m3941a(long j) {
        try {
            Cursor query = Main.f1927b.getContentResolver().query(Data.CONTENT_URI, null, "contact_id=" + j + " AND " + "mimetype" + "='" + "vnd.android.cursor.item/photo" + "'", null, null);
            if (query == null) {
                return null;
            }
            if (query.moveToFirst()) {
                query.close();
                return Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(j));
            }
            query.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
