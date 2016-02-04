package app.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.util.ArrayList;

public class GroupTable {
    public static void m4816a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE group_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, group_jid TEXT NOT NULL, password TEXT, group_state TEXT NOT NULL, name TEXT NOT NULL, avatar_token TEXT, members TEXT NOT NULL, admins TEXT, is_mute INTEGER, time_stamp UNSIGNED BIG INT, invitees TEXT);");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS group_jid_index ON group_table (group_jid)");
    }

    public static void m4817a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 4 && i2 >= 4) {
            m4816a(sQLiteDatabase);
        }
        if (i < 10 && i2 >= 10) {
            m4821d(sQLiteDatabase);
        }
        if (i < 14 && i2 >= 14) {
            m4820c(sQLiteDatabase);
        }
        if (i < 16 && i2 >= 16) {
            m4818b(sQLiteDatabase);
        }
    }

    private static void m4818b(SQLiteDatabase sQLiteDatabase) {
        m4822e(sQLiteDatabase);
        try {
            sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS group_jid_index ON group_table (group_jid)");
        } catch (SQLException e) {
        }
    }

    private static void m4820c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE group_table ADD COLUMN is_mute INTEGER;");
        } catch (SQLiteException e) {
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_mute", Boolean.valueOf(false));
        sQLiteDatabase.update("group_table", contentValues, null, null);
    }

    private static void m4821d(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE group_table ADD COLUMN time_stamp UNSIGNED BIG INT");
        } catch (SQLiteException e) {
        }
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("group_table", new String[]{"group_jid"}, null, null, null, null, null);
        if (query != null && query.moveToFirst()) {
            ArrayList arrayList = new ArrayList();
            String str = "";
            do {
                str = str + "jabber_id=? ";
                arrayList.add(query.getString(query.getColumnIndex("group_jid")));
                if (!query.isLast()) {
                    str = str + " OR ";
                }
            } while (query.moveToNext());
            query.close();
            SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
            query = sQLiteDatabase3.query("message_table", new String[]{"timestamp", "jabber_id"}, str, (String[]) arrayList.toArray(new String[arrayList.size()]), "jabber_id", null, "timestamp");
            if (query != null && query.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time_stamp", query.getString(query.getColumnIndex("timestamp")));
                    String str2 = "group_table";
                    sQLiteDatabase.update(str2, contentValues, "group_jid=?", new String[]{query.getString(query.getColumnIndex("jabber_id"))});
                } while (query.moveToNext());
                query.close();
            }
        }
    }

    private static void m4822e(SQLiteDatabase sQLiteDatabase) {
        String str;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT t1._id FROM group_table AS t1 GROUP BY t1.group_jid HAVING COUNT (t1.group_jid) > 1", null);
        if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
            str = null;
        } else {
            str = "";
            do {
                str = str + "_id='" + rawQuery.getLong(rawQuery.getColumnIndex("_id")) + "'";
                if (!rawQuery.isLast()) {
                    str = str + " OR ";
                }
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        if (str != null) {
            sQLiteDatabase.delete("group_table", str, null);
        }
    }

    public static void m4819b(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS group_table");
        }
    }
}
