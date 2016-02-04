package app.database.table;

import android.database.sqlite.SQLiteDatabase;

public class CallLogTable {
    public static void m4787a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS call_log_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, type TEXT NOT NULL, call_out BOOLEAN NOT NULL, duration INTEGER NOT NULL, timestamp UNSIGNED BIG INT NOT NULL, credit REAL);");
    }

    public static void m4788a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 11 && i2 >= 11) {
            m4787a(sQLiteDatabase);
        }
    }
}
