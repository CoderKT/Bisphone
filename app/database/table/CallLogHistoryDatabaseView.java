package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class CallLogHistoryDatabaseView {
    public static void m4784a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW call_log_history_view AS SELECT * FROM (SELECT _id AS _id, username AS username, type AS call_type, call_out AS call_out, duration AS call_duration, timestamp AS call_timestamp, credit AS call_credit FROM call_log_table) LEFT JOIN (SELECT _id AS _id_contact, type AS contact_type, username AS contact_username, is_registered AS contact_is_registered, contact_id AS local_contact_id, name AS local_name, photo_uri AS local_photo_uri, vcard_nickname AS remote_nickname, vcard_avatar AS remote_photo_token FROM phone_contact_table GROUP BY username) ON username=contact_username");
    }

    public static void m4785a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 12 && i2 >= 12) {
            m4786b(sQLiteDatabase);
            m4784a(sQLiteDatabase);
        }
    }

    private static void m4786b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP VIEW IF EXISTS call_log_history_view");
    }
}
