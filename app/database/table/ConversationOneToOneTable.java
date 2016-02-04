package app.database.table;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class ConversationOneToOneTable {
    public static void m4809a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS normal_conversation_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, username TEXT NOT NULL UNIQUE, unread_count INTEGER, message_body TEXT, message_type TEXT, time_stamp UNSIGNED BIG INT, last_seen_timestamp UNSIGNED BIG INT, unread_position INTEGER);");
        sQLiteDatabase.delete("normal_conversation_table", null, null);
    }

    public static void m4810a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4809a(sQLiteDatabase);
        }
        if (i < 21 && i2 >= 21) {
            m4811b(sQLiteDatabase);
        }
    }

    private static void m4811b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE normal_conversation_table ADD COLUMN last_seen_timestamp UNSIGNED BIG INT;");
        } catch (SQLiteException e) {
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE normal_conversation_table ADD COLUMN unread_position UNSIGNED BIG INT;");
        } catch (SQLiteException e2) {
        }
        sQLiteDatabase.execSQL("UPDATE normal_conversation_table SET unread_position=unread_count;");
    }
}
