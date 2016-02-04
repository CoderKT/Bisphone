package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class StickerTable {
    public static void m4842a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sticker_table (_id INTEGER PRIMARY KEY, pack_id INTEGER NOT NULL);");
    }

    public static void m4843a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 6 && i2 >= 6) {
            m4842a(sQLiteDatabase);
        }
    }
}
