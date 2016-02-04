package app.database.table;

import android.database.sqlite.SQLiteDatabase;
import app.logger.HandledException;
import com.crashlytics.android.Crashlytics;

public class ChatHistoryDatabaseView {
    public static void m4793a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(m4795b());
    }

    public static void m4796b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(m4792a());
    }

    public static void m4794a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4797c(sQLiteDatabase);
            m4796b(sQLiteDatabase);
            sQLiteDatabase.execSQL("DELETE FROM message_table WHERE contact_username IS NULL AND message_type='2'");
            try {
                sQLiteDatabase.execSQL("INSERT INTO normal_conversation_table (" + "message_id, username, unread_count, message_body, message_type, time_stamp" + ") " + "SELECT " + "message_id, username, badge, message_text, message_type, message_timestamp" + " " + "FROM " + "chats_history_view" + " where " + "message_message_type" + "='" + 2 + "' GROUP BY " + "username" + ";");
                m4797c(sQLiteDatabase);
                m4793a(sQLiteDatabase);
            } catch (Exception e) {
                Crashlytics.m7882a(new HandledException(e.getMessage()));
                throw new RuntimeException("Ops");
            }
        }
    }

    private static String m4792a() {
        return "CREATE VIEW chats_history_view AS SELECT message_table._id AS _id, message_table.message_id AS message_id, message_table.jabber_id AS jabber_id, message_table.contact_username AS username, message_table.text AS message_text, message_table.type AS message_type, message_table.message_type AS message_message_type, MAX(message_table.timestamp) AS message_timestamp, message_table.status AS message_status, message_table.is_new_message AS message_unread, message_table.am_i_sender AS message_outgoing, SUM(message_table.is_new_message) AS badge, phone_contact_table._id AS _id_contact, phone_contact_table.type AS contact_type, phone_contact_table.username AS contact_username, phone_contact_table.is_registered AS contact_is_registered, phone_contact_table.contact_id AS local_contact_id, phone_contact_table.name AS local_name, phone_contact_table.photo_uri AS local_photo_uri, phone_contact_table.vcard_nickname AS remote_nickname, phone_contact_table.vcard_avatar AS remote_photo_token FROM message_table LEFT JOIN ( SELECT * FROM phone_contact_table GROUP BY username) phone_contact_table ON username=contact_username GROUP BY jabber_id ORDER BY message_timestamp DESC";
    }

    private static String m4795b() {
        return "CREATE VIEW chats_history_view AS SELECT O.*, phone_contact_table._id AS _id_contact, phone_contact_table.type AS contact_type, phone_contact_table.username AS contact_username, phone_contact_table.is_registered AS contact_is_registered, phone_contact_table.contact_id AS local_contact_id, phone_contact_table.name AS local_name, phone_contact_table.photo_uri AS local_photo_uri, phone_contact_table.vcard_nickname AS remote_nickname, phone_contact_table.vcard_avatar AS remote_photo_token FROM normal_conversation_table O LEFT JOIN ( SELECT * FROM phone_contact_table GROUP BY username) phone_contact_table ON O.username=contact_username ORDER BY O.time_stamp DESC";
    }

    private static void m4797c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP VIEW IF EXISTS chats_history_view");
    }
}
