package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class HistoryOneToOneMessageTable {
    public static void m4830a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS normal_message_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, contact_username TEXT, jabber_id TEXT, text TEXT, type INTEGER NOT NULL, timestamp UNSIGNED BIG INT NOT NULL, status INTEGER NOT NULL, thumbnail TEXT, extra_data TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS index_normal_messages_jid ON normal_message_table(contact_username)");
        sQLiteDatabase.delete("normal_message_table", null, null);
    }

    public static void m4831a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4830a(sQLiteDatabase);
        }
    }
}
