package app.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.xmpp.JabberId;

public class GroupHistoryDatabaseView {
    public static void m4813a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(m4812a());
    }

    public static void m4814a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4815b(sQLiteDatabase);
            m4813a(sQLiteDatabase);
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM groups_history_view", null);
            if (rawQuery != null) {
                if (rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
                    rawQuery.close();
                    m4815b(sQLiteDatabase);
                }
                do {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("group_invitees"));
                    String string2 = rawQuery.getString(rawQuery.getColumnIndex("group_admins"));
                    String string3 = rawQuery.getString(rawQuery.getColumnIndex("group_members"));
                    String string4 = rawQuery.getString(rawQuery.getColumnIndex("group_jid"));
                    if (JabberId.m7386a(string4) != null) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("group_jid", rawQuery.getString(rawQuery.getColumnIndex("group_jid")));
                        contentValues.put("avatar_token", rawQuery.getString(rawQuery.getColumnIndex("group_photo_token")));
                        contentValues.put("is_mute", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("group_is_mute"))));
                        contentValues.put("message_id", rawQuery.getString(rawQuery.getColumnIndex("message_id")));
                        contentValues.put("message_body", rawQuery.getString(rawQuery.getColumnIndex("message_text")));
                        contentValues.put("message_username", rawQuery.getString(rawQuery.getColumnIndex("message_username")));
                        contentValues.put("name", rawQuery.getString(rawQuery.getColumnIndex("group_name")));
                        contentValues.put("group_state", rawQuery.getString(rawQuery.getColumnIndex("group_state")));
                        contentValues.put("time_stamp", rawQuery.getString(rawQuery.getColumnIndex("group_timestamp")));
                        contentValues.put("message_time_stamp", rawQuery.getString(rawQuery.getColumnIndex("message_timestamp")));
                        contentValues.put("unread_count", rawQuery.getString(rawQuery.getColumnIndex("badge")));
                        sQLiteDatabase.insert("group_conversation_table", null, contentValues);
                        if (string != null && string.length() > 0) {
                            for (String str : string.split(",")) {
                                if (!(str == null || str.length() == 0)) {
                                    ContentValues contentValues2 = new ContentValues(3);
                                    contentValues2.put("group_jid", string4);
                                    contentValues2.put("user_status", Integer.valueOf(1));
                                    contentValues2.put("contact_username", str);
                                    sQLiteDatabase.insert("groups_users_table", null, contentValues2);
                                }
                            }
                        }
                        if (string2 != null && string2.length() > 0) {
                            ContentValues contentValues3 = new ContentValues(3);
                            contentValues3.put("group_jid", string4);
                            contentValues3.put("user_status", Integer.valueOf(2));
                            contentValues3.put("contact_username", string2);
                            sQLiteDatabase.insert("groups_users_table", null, contentValues3);
                        }
                        if (string3 != null && string3.length() > 0) {
                            for (String str2 : string3.split(",")) {
                                if (!(str2 == null || str2.length() == 0)) {
                                    ContentValues contentValues4 = new ContentValues(3);
                                    contentValues4.put("group_jid", string4);
                                    contentValues4.put("user_status", Integer.valueOf(3));
                                    contentValues4.put("contact_username", str2);
                                    sQLiteDatabase.insert("groups_users_table", null, contentValues4);
                                }
                            }
                        }
                    }
                } while (rawQuery.moveToNext());
                rawQuery.close();
                m4815b(sQLiteDatabase);
            }
        }
    }

    private static String m4812a() {
        return "CREATE VIEW IF NOT EXISTS groups_history_view AS SELECT group_table._id AS _id, group_table.group_jid AS group_jid, group_table.password AS group_password, group_table.group_state AS group_state, group_table.name AS group_name, group_table.avatar_token AS group_photo_token, group_table.members AS group_members, group_table.invitees AS group_invitees, group_table.admins AS group_admins, group_table.time_stamp AS group_timestamp, group_table.is_mute AS group_is_mute, message_table._id AS _id_message, message_table.message_id AS message_id, message_table.jabber_id AS message_jid, message_table.contact_username AS message_username, message_table.text AS message_text, message_table.type AS message_type, MAX (message_table.timestamp) AS message_timestamp, message_table.status AS message_status, message_table.is_new_message AS message_unread, message_table.am_i_sender AS message_outgoing, SUM (message_table.is_new_message) AS badge FROM group_table LEFT JOIN message_table ON group_jid=message_jid AND message_status!='" + DeliveryStatus.DRAFT + "'" + " WHERE " + "group_state" + "!='" + "IGNORED" + "'" + " GROUP BY " + "group_jid" + " ORDER BY " + "group_timestamp" + " DESC";
    }

    private static void m4815b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP VIEW IF EXISTS groups_history_view");
    }
}
