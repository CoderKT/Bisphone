package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class BroadcastUsersTable {
    public static void m4782a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS broadcast_users_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, broadcast_id TEXT NOT NULL, recipients TEXT NOT NULL);");
    }

    public static void m4783a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 21 && i2 >= 21) {
            m4782a(sQLiteDatabase);
        }
    }
}
