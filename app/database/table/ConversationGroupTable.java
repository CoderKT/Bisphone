package app.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import app.common.entity.ConversationGroupEntity.GroupInfoState;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;

public class ConversationGroupTable {
    public static void m4802a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS group_conversation_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT, group_jid TEXT NOT NULL, group_state TEXT NOT NULL, group_type INTEGER, group_description TEXT, group_info INTEGER, name TEXT NOT NULL, avatar_token TEXT, message_body TEXT, message_username TEXT, is_mute INTEGER, unread_count INTEGER,message_time_stamp UNSIGNED BIG INT,last_seen_timestamp UNSIGNED BIG INT,unread_position INT,time_stamp UNSIGNED BIG INT,group_admin TEXT,extra_data TEXT);");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS conversation_jid_index ON group_conversation_table (group_jid)");
    }

    public static void m4804b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS group_conversation_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT, group_jid TEXT NOT NULL, group_state TEXT NOT NULL, name TEXT NOT NULL, avatar_token TEXT, message_body TEXT, message_username TEXT, is_mute INTEGER, unread_count INTEGER,message_time_stamp UNSIGNED BIG INT,time_stamp UNSIGNED BIG INT);");
    }

    public static void m4803a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18 && i2 >= 18) {
            m4804b(sQLiteDatabase);
        }
        if (i < 20 && i2 >= 20) {
            m4806d(sQLiteDatabase);
            m4805c(sQLiteDatabase);
        }
        if (i < 21 && i2 >= 21) {
            m4808f(sQLiteDatabase);
        }
    }

    private static void m4805c(SQLiteDatabase sQLiteDatabase) {
        String str = "ALTER TABLE group_conversation_table ADD COLUMN group_type INTEGER";
        String str2 = "ALTER TABLE group_conversation_table ADD COLUMN group_description TEXT";
        String str3 = "ALTER TABLE group_conversation_table ADD COLUMN group_info INTEGER";
        String str4 = "ALTER TABLE group_conversation_table ADD COLUMN group_admin TEXT";
        String str5 = "ALTER TABLE group_conversation_table ADD COLUMN extra_data TEXT";
        String str6 = "UPDATE group_conversation_table SET is_mute = ( CASE WHEN is_mute =0 THEN '" + NotificationState.sound.ordinal() + "' WHEN " + "is_mute" + " =1 THEN '" + NotificationState.disable.ordinal() + "' ELSE '" + NotificationState.sound.ordinal() + "' END )";
        try {
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.execSQL(str2);
            sQLiteDatabase.execSQL(str3);
            sQLiteDatabase.execSQL(str4);
            sQLiteDatabase.execSQL(str5);
        } catch (SQLiteException e) {
        }
        sQLiteDatabase.execSQL(str6);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_type", Integer.valueOf(Type.unmoderated.ordinal()));
        contentValues.put("group_info", Integer.valueOf(GroupInfoState.NONE.ordinal()));
        contentValues.put("extra_data", "");
        sQLiteDatabase.update("group_conversation_table", contentValues, null, null);
    }

    private static void m4806d(SQLiteDatabase sQLiteDatabase) {
        m4807e(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS conversation_jid_index ON group_conversation_table (group_jid)");
    }

    private static void m4807e(SQLiteDatabase sQLiteDatabase) {
        String str;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT t2._id FROM group_conversation_table  AS t2 WHERE t2.group_jid IN ( SELECT t1.group_jid FROM group_conversation_table AS t1 GROUP BY t1.group_jid HAVING count(t1.group_jid) > 1) AND t2._id NOT IN (SELECT t1._id FROM group_conversation_table AS t1 group by t1.group_jid HAVING count(t1.group_jid) > 1 )", null);
        if (rawQuery == null || rawQuery.getCount() <= 0 || !rawQuery.moveToFirst()) {
            str = null;
        } else {
            str = "";
            do {
                str = str + "_id='" + rawQuery.getLong(rawQuery.getColumnIndex("_id")) + "'";
                if (!rawQuery.isLast()) {
                    str = str + " OR ";
                }
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        if (str != null) {
            sQLiteDatabase.delete("group_conversation_table", str, null);
        }
    }

    private static void m4808f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE group_conversation_table ADD COLUMN last_seen_timestamp UNSIGNED BIG INT;");
        } catch (SQLiteException e) {
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE group_conversation_table ADD COLUMN unread_position UNSIGNED BIG INT;");
        } catch (SQLiteException e2) {
        }
        sQLiteDatabase.execSQL("UPDATE group_conversation_table SET unread_position=unread_count;");
    }
}
