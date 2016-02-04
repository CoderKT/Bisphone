package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class StickerPackTable {
    public static void m4840a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sticker_packs_table (_id INTEGER PRIMARY KEY, name TEXT , layout TEXT , status BOOLEAN , visible BOOLEAN , downloaded BOOLEAN , position INTEGER );");
    }

    public static void m4841a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 6 && i2 >= 6) {
            m4840a(sQLiteDatabase);
        }
        if (i < 7 && i2 >= 7) {
            sQLiteDatabase.execSQL("ALTER TABLE sticker_packs_table RENAME TO temp_table_name;");
            m4840a(sQLiteDatabase);
            sQLiteDatabase.execSQL("INSERT INTO sticker_packs_table (" + "_id, name, layout, status, visible, position" + ") " + "SELECT " + "_id, name, layout, status, visible, position" + " " + "FROM " + "temp_table_name" + ";");
            sQLiteDatabase.execSQL("UPDATE sticker_packs_table SET downloaded = 1");
            sQLiteDatabase.execSQL("DROP TABLE temp_table_name;");
        }
    }
}
