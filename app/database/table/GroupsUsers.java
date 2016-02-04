package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class GroupsUsers {
    public static void m4823a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS groups_users_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, group_jid TEXT NOT NULL, user_status INTEGER NOT NULL, contact_username TEXT NOT NULL);");
        sQLiteDatabase.delete("groups_users_table", null, null);
    }

    public static void m4824a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4823a(sQLiteDatabase);
        }
        if (i < 20 && i2 >= 20) {
            m4825b(sQLiteDatabase);
        }
    }

    private static void m4825b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("groups_users_table", null, null);
    }
}
