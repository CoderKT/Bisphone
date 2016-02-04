package app.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.MessageEntity;
import java.util.Calendar;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class MessageTable {
    public static void m4832a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE message_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, jabber_id TEXT NOT NULL, contact_username TEXT, text TEXT, type TEXT NOT NULL, timestamp TEXT NOT NULL, status TEXT NOT NULL, is_new_message BOOLEAN NOT NULL, am_i_sender BOOLEAN NOT NULL, thumbnail TEXT, message_type INTEGER, extra_data TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS index_messages_jid ON message_table(jabber_id)");
    }

    public static void m4833a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        m4839g(sQLiteDatabase, i, i2);
        m4838f(sQLiteDatabase, i, i2);
        m4837e(sQLiteDatabase, i, i2);
        m4836d(sQLiteDatabase, i, i2);
        m4835c(sQLiteDatabase, i, i2);
    }

    private static void m4835c(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS index_messages_text");
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("thumbnail", null);
            Calendar.getInstance().add(5, -14);
            String[] strArr = new String[]{r1.getTimeInMillis() + "", Type.PHOTO.toString()};
            sQLiteDatabase.update("message_table", contentValues, "timestamp< ? AND type=?", strArr);
            String str = "CASE type WHEN '" + Type.TEXT + "' THEN " + Type.TEXT.ordinal() + " " + "WHEN '" + Type.PHOTO + "' THEN " + Type.PHOTO.ordinal() + " " + "WHEN '" + Type.VIDEO + "' THEN " + Type.VIDEO.ordinal() + " " + "WHEN '" + Type.AUDIO + "' THEN " + Type.AUDIO.ordinal() + " " + "WHEN '" + Type.MAP + "' THEN " + Type.MAP.ordinal() + " " + "WHEN '" + Type.CALL + "' THEN " + Type.CALL.ordinal() + " " + "WHEN '" + Type.STICKER + "' THEN " + Type.STICKER.ordinal() + " " + "WHEN '" + Type.GROUP_STATUS + "' THEN " + Type.GROUP_STATUS.ordinal() + " ELSE " + Type.TEXT.ordinal() + " END, ";
            String str2 = "CASE status WHEN '" + DeliveryStatus.UPLOADING + "' THEN " + Type.TEXT.ordinal() + " " + "WHEN '" + DeliveryStatus.FAILED_TO_UPLOAD + "' THEN " + DeliveryStatus.FAILED_TO_UPLOAD.ordinal() + " " + "WHEN '" + DeliveryStatus.SENDING + "' THEN " + DeliveryStatus.SENDING.ordinal() + " " + "WHEN '" + DeliveryStatus.FAILED_TO_SEND + "' THEN " + DeliveryStatus.FAILED_TO_SEND.ordinal() + " " + "WHEN '" + DeliveryStatus.SENT + "' THEN " + DeliveryStatus.SENT.ordinal() + " " + "WHEN '" + DeliveryStatus.DELIVERED + "' THEN " + DeliveryStatus.DELIVERED.ordinal() + " " + "WHEN '" + DeliveryStatus.DRAFT + "' THEN " + DeliveryStatus.DRAFT.ordinal() + " " + "WHEN '" + DeliveryStatus.RECEIVED + "' THEN " + DeliveryStatus.RECEIVED.ordinal() + " ELSE " + DeliveryStatus.SENT.ordinal() + " END, ";
            String str3 = "message_id, contact_username, jabber_id, text, " + str + "timestamp" + ", " + str2 + "thumbnail" + ", " + "extra_data";
            str = "message_id, jabber_id, text, " + str + "timestamp" + ", " + str2 + "thumbnail" + ", " + "extra_data";
            str2 = "INSERT INTO normal_message_table (" + "message_id, contact_username, jabber_id, text, type, timestamp, status, thumbnail, extra_data" + ") " + "SELECT " + str3 + " " + "FROM " + "message_table" + " where " + "message_type" + "='" + 2 + "';";
            str = "INSERT INTO channel_message_table (" + "message_id, jabber_id, text, type, timestamp, status, thumbnail, extra_data" + ") " + "SELECT " + str + " " + "FROM " + "message_table" + " where " + "message_type" + "='" + 4 + "';";
            str3 = "INSERT INTO group_message_table (" + "message_id, contact_username, jabber_id, text, type, timestamp, status, thumbnail, extra_data" + ") " + "SELECT " + str3 + " " + "FROM " + "message_table" + " where " + "message_type" + "='" + 1 + "';";
            sQLiteDatabase.execSQL(str2);
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.execSQL(str3);
        }
    }

    private static void m4836d(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 16 && i2 >= 16) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE message_table ADD COLUMN message_type INTEGER;");
            } catch (SQLiteException e) {
            }
            sQLiteDatabase.execSQL("UPDATE message_table SET message_type = ( CASE WHEN jabber_id LIKE '%@group.bisphone.com%' THEN '1' WHEN jabber_id LIKE '%@channel.bisphone.com%' THEN '4' WHEN contact_username is null AND status != '" + MessageEntity.DeliveryStatus.DRAFT.toString() + "' THEN '" + 3 + "'" + " ELSE '" + 2 + "' END )");
        }
    }

    private static void m4837e(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 9 && i2 >= 9) {
            sQLiteDatabase.execSQL("ALTER TABLE message_table RENAME TO temp_table;");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS index_messages_text");
            m4832a(sQLiteDatabase);
            String str = "_id, message_id, contact_username, jabber_id, contact_username, text, type, timestamp, status, is_new_message, am_i_sender, thumbnail, extra_data";
            sQLiteDatabase.execSQL("INSERT OR REPLACE INTO message_table (" + str + ") " + "SELECT " + str + " FROM temp_table;");
            sQLiteDatabase.execSQL("UPDATE message_table SET status = 'FAILED_TO_SEND' WHERE status = 'UPLOADED';");
            sQLiteDatabase.execSQL("DROP TABLE temp_table;");
        }
    }

    private static void m4838f(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 4 && i2 >= 4) {
            sQLiteDatabase.execSQL("ALTER TABLE message_table RENAME TO temp_table_name;");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS index_messages_text");
            m4832a(sQLiteDatabase);
            sQLiteDatabase.execSQL("INSERT INTO message_table (" + "_id, message_id, jabber_id, text, type, timestamp, status, is_new_message, am_i_sender, thumbnail, extra_data" + ") " + "SELECT " + "_id, message_id, contact_username, text, type, timestamp, status, is_new_message, am_i_sender, thumbnail, extra_data" + " " + "FROM " + "temp_table_name" + ";");
            sQLiteDatabase.execSQL("UPDATE message_table SET contact_username = jabber_id, jabber_id = jabber_id || '@bisphone.com' WHERE jabber_id NOT LIKE 'b^_%' ESCAPE '^';");
            sQLiteDatabase.execSQL("DROP TABLE temp_table_name;");
        }
    }

    private static void m4839g(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2 && i2 >= 2) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Status.ELEMENT, MessageEntity.DeliveryStatus.RECEIVED.toString());
            sQLiteDatabase.update("message_table", contentValues, "status='DEFAULT' OR status='DOWNLOADED' OR status='FAILED_TO_DOWNLOAD'", null);
        }
    }

    public static void m4834b(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS message_table");
        }
    }
}
