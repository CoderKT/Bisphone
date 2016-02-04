package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int f636j;
    private int f637k;
    private LayoutInflater f638l;

    public ResourceCursorAdapter(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f637k = i;
        this.f636j = i;
        this.f638l = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View m1835a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f638l.inflate(this.f636j, viewGroup, false);
    }

    public View m1836b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f638l.inflate(this.f637k, viewGroup, false);
    }
}
