package app.database.table;

import android.database.sqlite.SQLiteDatabase;
import app.common.entity.ContactEntity.TYPE;
import app.database.datasource.ContactDataSource;

public class ContactTable {

    /* renamed from: app.database.table.ContactTable.1 */
    final class C01381 implements Runnable {
        C01381() {
        }

        public void run() {
            try {
                Thread.sleep(5000);
                ContactDataSource.m4553a().m4575d();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m4798a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS phone_contact_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id LONG, contact_data_table_id TEXT, name TEXT, phone_number TEXT, username TEXT, version INTEGER, status TEXT, is_registered INTEGER, is_favorite INTEGER, vcard_nickname TEXT, vCard_checked_time TEXT, recently_joined TEXT, type TEXT, vcard_avatar TEXT, last_seen TEXT, background_json TEXT, photo_uri TEXT);");
        sQLiteDatabase.execSQL("CREATE INDEX index_contact_id ON phone_contact_table(contact_id);");
        sQLiteDatabase.execSQL("CREATE INDEX index_contact_name ON phone_contact_table(name);");
        sQLiteDatabase.execSQL("CREATE INDEX index_username ON phone_contact_table(username);");
    }

    public static void m4799a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 3 && i2 >= 3) {
            m4801c(sQLiteDatabase);
        }
        if (i < 5 && i2 >= 5) {
            sQLiteDatabase.execSQL("ALTER TABLE phone_contact_table ADD COLUMN vcard_nickname TEXT;");
            sQLiteDatabase.execSQL("ALTER TABLE phone_contact_table ADD COLUMN photo_uri TEXT;");
        }
        if (i < 9 && i2 >= 9) {
            m4800b(sQLiteDatabase);
            new Thread(new C01381()).start();
        }
        if (i < 18 && i2 >= 18) {
            sQLiteDatabase.execSQL("DELETE FROM phone_contact_table WHERE type = '" + TYPE.REMOTE + "' AND " + "_id" + " NOT IN " + "( SELECT MIN(" + "_id" + ") FROM " + "phone_contact_table" + " GROUP BY " + "username" + " HAVING " + "type" + "= '" + TYPE.REMOTE + "')");
        }
        if (i < 20 && i2 >= 20) {
            sQLiteDatabase.execSQL("DELETE FROM phone_contact_table WHERE type = '" + TYPE.REMOTE + "' AND " + "_id" + " NOT IN " + "( SELECT MIN(" + "_id" + ") FROM " + "phone_contact_table" + " GROUP BY " + "username" + " HAVING " + "type" + "= '" + TYPE.REMOTE + "')");
        }
    }

    private static void m4800b(SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        String[] strArr = new String[4];
        strArr[0] = "ALTER TABLE phone_contact_table RENAME TO temp_table_name;";
        strArr[1] = "CREATE TABLE phone_contact_table (" + "_id integer primary key autoincrement, contact_id LONG, name TEXT, username TEXT, phone_number, version INTEGER, is_registered INTEGER, is_favorite INTEGER, vcard_nickname TEXT, contact_data_table_id INTEGER, vcard_avatar TEXT ,status TEXT, last_seen TEXT, photo_uri TEXT, recently_joined TEXT, vCard_checked_time TEXT, background_json TEXT, type TEXT );" + ");";
        strArr[2] = "INSERT INTO phone_contact_table (" + "_id, contact_id, name, username, phone_number, version, is_registered, is_favorite, vcard_nickname, contact_data_table_id, vcard_avatar, status" + ") " + "SELECT " + "_id, contact_id, name, username, phone_number, version, is_registered, is_favorite, vcard_nickname, contact_data_table_id, vcard_avatar, status" + " " + "FROM " + "temp_table_name" + ";";
        strArr[3] = "DROP TABLE temp_table_name;";
        int length = strArr.length;
        while (i < length) {
            sQLiteDatabase.execSQL(strArr[i]);
            i++;
        }
        sQLiteDatabase.execSQL("UPDATE phone_contact_table SET recently_joined = 0, vCard_checked_time= 0 , type = '" + TYPE.LOCAL.toString() + "'");
    }

    private static void m4801c(SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        String[] strArr = new String[4];
        strArr[0] = "ALTER TABLE phone_contact_table RENAME TO temp_table_name;";
        strArr[1] = "CREATE TABLE phone_contact_table (" + "_id integer primary key autoincrement, contact_id TEXT, name TEXT, username TEXT, version TEXT, status TEXT, is_registered TEXT, is_favorite TEXT" + ");";
        strArr[2] = "INSERT INTO phone_contact_table (" + "_id, contact_id, name, username, version, status, phone_number, contact_data_table_id, is_registered, is_favorite" + ") " + "SELECT " + "_id, contact_id, name, username, version, status, phone_number, contact_data_table_id, is_bello_user, is_favorite" + " " + "FROM " + "temp_table_name" + ";";
        strArr[3] = "DROP TABLE temp_table_name;";
        int length = strArr.length;
        while (i < length) {
            sQLiteDatabase.execSQL(strArr[i]);
            i++;
        }
    }
}
