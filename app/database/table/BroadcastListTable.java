package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class BroadcastListTable {
    public static void m4778a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS broadcast_list_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, broadcast_id TEXT NOT NULL, name TEXT NOT NULL, avatar_token TEXT, message_body TEXT, message_type INTEGER NOT NULL, message_time_stamp TEXT NOT NULL, users_hash TEXT NOT NULL);");
    }

    public static void m4779a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 21 && i2 >= 21) {
            m4778a(sQLiteDatabase);
        }
    }
}
