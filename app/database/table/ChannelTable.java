package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class ChannelTable {
    public static void m4789a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS channel_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, channel_jid TEXT UNIQUE, name TEXT NOT NULL, description TEXT, state TEXT NOT NULL, avatar_token TEXT, cover_token TEXT, followers_count INTEGER, following BOOLEAN NOT NULL, readonly BOOLEAN NOT NULL, muted BOOLEAN NOT NULL, unread_count INTEGER, timestamp UNSIGNED BIG INT NOT NULL,lastseen_timestamp UNSIGNED BIG INT);");
        sQLiteDatabase.execSQL("CREATE INDEX index_jid ON channel_table(channel_jid)");
        sQLiteDatabase.execSQL("CREATE INDEX index_timestamp ON channel_table(timestamp)");
    }

    public static void m4790a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 8 && i2 >= 8) {
            m4789a(sQLiteDatabase);
        }
        if (i < 21 && i2 >= 21) {
            m4791b(sQLiteDatabase);
        }
    }

    private static void m4791b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE channel_table ADD COLUMN lastseen_timestamp UNSIGNED BIG INT;");
    }
}
