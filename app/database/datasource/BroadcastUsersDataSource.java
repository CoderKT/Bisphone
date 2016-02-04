package app.database.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class BroadcastUsersDataSource {
    private static BroadcastUsersDataSource f2307c;
    private DatabaseHelper f2308a;
    private SQLiteDatabase f2309b;

    public static BroadcastUsersDataSource m4519a() {
        if (f2307c == null) {
            synchronized (BroadcastUsersDataSource.class) {
                if (f2307c == null) {
                    f2307c = new BroadcastUsersDataSource();
                }
            }
        }
        return f2307c;
    }

    private BroadcastUsersDataSource() {
        this.f2308a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2309b = this.f2308a.getWritableDatabase();
    }

    public ContentValues m4520a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("broadcast_id", str);
        contentValues.put("recipients", str2);
        return contentValues;
    }

    public void m4524b() {
        this.f2309b.delete("broadcast_users_table", null, null);
    }

    public ArrayList<String> m4521a(String str) {
        ArrayList<String> arrayList = new ArrayList();
        String[] strArr = new String[]{str};
        String[] strArr2 = new String[]{"recipients"};
        Cursor query = this.f2309b.query("broadcast_users_table", strArr2, "broadcast_id=?", strArr, null, null, null);
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(query.getString(0));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void m4522a(String str, List<String> list) {
        if (list != null && !list.isEmpty()) {
            try {
                this.f2309b.beginTransaction();
                for (String a : list) {
                    this.f2309b.insert("broadcast_users_table", null, m4520a(str, a));
                }
                this.f2309b.setTransactionSuccessful();
            } finally {
                this.f2309b.endTransaction();
            }
        }
    }

    public void m4523a(List<String> list) {
        try {
            this.f2309b.beginTransaction();
            for (String b : list) {
                m4525b(b);
            }
            this.f2309b.setTransactionSuccessful();
        } finally {
            this.f2309b.endTransaction();
        }
    }

    public void m4525b(String str) {
        String[] strArr = new String[]{str};
        this.f2309b.delete("broadcast_users_table", "broadcast_id=?", strArr);
    }

    public void m4526b(String str, List<String> list) {
        m4525b(str);
        m4522a(str, (List) list);
    }
}
