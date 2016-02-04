package io.fabric.sdk.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

class FabricContext extends ContextWrapper {
    private final String f8168a;
    private final String f8169b;

    public FabricContext(Context context, String str, String str2) {
        super(context);
        this.f8169b = str;
        this.f8168a = str2;
    }

    public File getDatabasePath(String str) {
        File file = new File(super.getDatabasePath(str).getParentFile(), this.f8168a);
        file.mkdirs();
        return new File(file, str);
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), cursorFactory);
    }

    @TargetApi(11)
    public SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str).getPath(), cursorFactory, databaseErrorHandler);
    }

    public File getFilesDir() {
        return new File(super.getFilesDir(), this.f8168a);
    }

    @TargetApi(8)
    public File getExternalFilesDir(String str) {
        return new File(super.getExternalFilesDir(str), this.f8168a);
    }

    public File getCacheDir() {
        return new File(super.getCacheDir(), this.f8168a);
    }

    @TargetApi(8)
    public File getExternalCacheDir() {
        return new File(super.getExternalCacheDir(), this.f8168a);
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        return super.getSharedPreferences(this.f8169b + ":" + str, i);
    }
}
