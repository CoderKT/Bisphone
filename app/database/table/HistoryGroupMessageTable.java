package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class HistoryGroupMessageTable {
    public static void m4828a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS group_message_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, contact_username TEXT, jabber_id TEXT, text TEXT, type INTEGER NOT NULL, timestamp UNSIGNED BIG INT NOT NULL, status INTEGER NOT NULL, thumbnail TEXT, extra_data TEXT);");
        sQLiteDatabase.delete("group_message_table", null, null);
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS index_group_messages_jid ON group_message_table(jabber_id)");
    }

    public static void m4829a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4828a(sQLiteDatabase);
        }
    }
}
