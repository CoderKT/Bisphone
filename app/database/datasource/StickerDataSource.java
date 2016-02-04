package app.database.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseArray;
import app.Main;
import app.common.entity.StickerEntity;
import app.common.entity.StickerPackEntity;
import app.database.provider.StickerPackProvider;
import app.database.provider.StickerProvider;
import java.util.List;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class StickerDataSource {
    public static SparseArray<StickerEntity> m4753a() {
        Cursor query = Main.f1927b.getContentResolver().query(StickerProvider.f2394a, null, null, null, "_id DESC");
        if (query == null) {
            return new SparseArray(0);
        }
        SparseArray<StickerEntity> sparseArray = new SparseArray(query.getCount());
        if (query.moveToFirst()) {
            do {
                sparseArray.append(query.getInt(query.getColumnIndexOrThrow("_id")), m4754a(query));
            } while (query.moveToNext());
        }
        query.close();
        return sparseArray;
    }

    public static SparseArray<StickerPackEntity> m4759b() {
        Cursor query = Main.f1927b.getContentResolver().query(StickerPackProvider.f2391a, null, "downloaded=1", null, "_id DESC");
        if (query == null) {
            return new SparseArray(0);
        }
        SparseArray<StickerPackEntity> sparseArray = new SparseArray(query.getCount());
        if (query.moveToFirst()) {
            do {
                sparseArray.append(query.getInt(query.getColumnIndexOrThrow("_id")), m4760b(query));
            } while (query.moveToNext());
        }
        query.close();
        return sparseArray;
    }

    public static int m4750a(int i) {
        String[] strArr = new String[]{i + ""};
        ContentValues contentValues = new ContentValues();
        contentValues.put("downloaded", Boolean.valueOf(false));
        return Main.f1927b.getContentResolver().update(StickerPackProvider.f2391a, contentValues, "_id =?", strArr);
    }

    public static SparseArray<StickerPackEntity> m4762c() {
        Cursor query = Main.f1927b.getContentResolver().query(StickerPackProvider.f2391a, null, null, null, "_id DESC");
        if (query == null) {
            return new SparseArray(0);
        }
        SparseArray<StickerPackEntity> sparseArray = new SparseArray(query.getCount());
        if (query.moveToFirst()) {
            do {
                sparseArray.append(query.getInt(query.getColumnIndexOrThrow("_id")), m4760b(query));
            } while (query.moveToNext());
        }
        query.close();
        return sparseArray;
    }

    public static Uri m4751a(StickerEntity stickerEntity) {
        return Main.f1927b.getContentResolver().insert(StickerProvider.f2394a, m4757b(stickerEntity));
    }

    public static Uri m4752a(StickerPackEntity stickerPackEntity) {
        Main.f1926a.m5683d("*************** added sticker pack id is " + stickerPackEntity.m4480a());
        if (m4761c(stickerPackEntity) == 0) {
            return Main.f1927b.getContentResolver().insert(StickerPackProvider.f2391a, m4758b(stickerPackEntity));
        }
        Main.f1926a.m5679b("**************** insert fail ***************");
        return null;
    }

    private static int m4761c(StickerPackEntity stickerPackEntity) {
        return Main.f1927b.getContentResolver().update(StickerPackProvider.f2391a, m4758b(stickerPackEntity), "_id=" + stickerPackEntity.m4480a(), null);
    }

    public static int m4756b(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(0));
        return Main.f1927b.getContentResolver().update(StickerPackProvider.f2391a, contentValues, "_id=" + i + " AND " + Status.ELEMENT + "=1", null);
    }

    public static boolean m4763c(int i) {
        Cursor query = Main.f1927b.getContentResolver().query(StickerPackProvider.f2391a, null, "_id=? AND downloaded=?", new String[]{i + "", "1"}, null);
        boolean moveToFirst = query.moveToFirst();
        query.close();
        return moveToFirst;
    }

    public static ContentValues m4757b(StickerEntity stickerEntity) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("_id", Integer.valueOf(stickerEntity.m4473a()));
        contentValues.put("pack_id", Integer.valueOf(stickerEntity.m4475b()));
        return contentValues;
    }

    public static ContentValues m4758b(StickerPackEntity stickerPackEntity) {
        ContentValues contentValues = new ContentValues(7);
        contentValues.put("_id", Integer.valueOf(stickerPackEntity.m4480a()));
        contentValues.put("name", stickerPackEntity.m4484b());
        contentValues.put("layout", stickerPackEntity.m4488c());
        contentValues.put(Status.ELEMENT, Boolean.valueOf(stickerPackEntity.m4490d()));
        contentValues.put("visible", Boolean.valueOf(stickerPackEntity.m4491e()));
        contentValues.put("position", Integer.valueOf(stickerPackEntity.m4492f()));
        contentValues.put("downloaded", Boolean.valueOf(stickerPackEntity.m4493g()));
        return contentValues;
    }

    private static StickerEntity m4754a(Cursor cursor) {
        return new StickerEntity(cursor.getInt(cursor.getColumnIndexOrThrow("_id")), cursor.getInt(cursor.getColumnIndexOrThrow("pack_id")));
    }

    private static StickerPackEntity m4760b(Cursor cursor) {
        boolean z = true;
        int i = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("layout"));
        boolean z2 = cursor.getInt(cursor.getColumnIndexOrThrow(Status.ELEMENT)) != 0;
        boolean z3 = cursor.getInt(cursor.getColumnIndexOrThrow("visible")) != 0;
        int i2 = cursor.getInt(cursor.getColumnIndexOrThrow("position"));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("downloaded")) == 0) {
            z = false;
        }
        return new StickerPackEntity(i, string, string2, z2, z3, i2, z);
    }

    public static void m4755a(List<Integer> list) {
        String str = "downloaded=0 AND ";
        for (int i = 0; i < list.size(); i++) {
            str = str + "_id!=" + list.get(i);
            if (i < list.size() - 1) {
                str = str + " AND ";
            }
        }
        Main.f1927b.getContentResolver().delete(StickerPackProvider.f2391a, str, null);
    }

    public static int m4764d() {
        Cursor query = Main.f1927b.getContentResolver().query(StickerPackProvider.f2391a, null, "status=1", null, null);
        if (query == null) {
            return 0;
        }
        int count = query.getCount();
        query.close();
        return count;
    }

    public static void m4765e() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(0));
        Main.f1927b.getContentResolver().update(StickerPackProvider.f2391a, contentValues, null, null);
    }
}
