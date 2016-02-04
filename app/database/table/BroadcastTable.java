package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class BroadcastTable {
    public static void m4780a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        m4781b(sQLiteDatabase, i, i2);
    }

    public static void m4781b(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 21 && i2 >= 21) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS broadcast_table");
        }
    }
}
