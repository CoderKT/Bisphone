package app.database.table;

import android.database.sqlite.SQLiteDatabase;
import app.common.entity.HistoryEntity.Type;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class HistoryChannelMessageTable {
    public static void m4826a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS channel_message_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, jabber_id TEXT, text TEXT, type INTEGER NOT NULL DEFAULT " + Type.TEXT + ", " + "timestamp" + " UNSIGNED BIG INT NOT NULL, " + Status.ELEMENT + " INTEGER NOT NULL, " + "thumbnail" + " TEXT, " + "extra_data" + " TEXT);");
        sQLiteDatabase.delete("channel_message_table", null, null);
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS index_channel_messages_jid ON channel_message_table(jabber_id)");
    }

    public static void m4827a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4826a(sQLiteDatabase);
        }
    }
}
