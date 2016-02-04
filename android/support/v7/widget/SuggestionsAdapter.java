package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.C0057R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements OnClickListener {
    private final SearchManager f1789j;
    private final SearchView f1790k;
    private final SearchableInfo f1791l;
    private final Context f1792m;
    private final WeakHashMap<String, ConstantState> f1793n;
    private final int f1794o;
    private boolean f1795p;
    private int f1796q;
    private ColorStateList f1797r;
    private int f1798s;
    private int f1799t;
    private int f1800u;
    private int f1801v;
    private int f1802w;
    private int f1803x;

    final class ChildViewCache {
        public final TextView f1784a;
        public final TextView f1785b;
        public final ImageView f1786c;
        public final ImageView f1787d;
        public final ImageView f1788e;

        public ChildViewCache(View view) {
            this.f1784a = (TextView) view.findViewById(16908308);
            this.f1785b = (TextView) view.findViewById(16908309);
            this.f1786c = (ImageView) view.findViewById(16908295);
            this.f1787d = (ImageView) view.findViewById(16908296);
            this.f1788e = (ImageView) view.findViewById(C0057R.id.edit_query);
        }
    }

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f1795p = false;
        this.f1796q = 1;
        this.f1798s = -1;
        this.f1799t = -1;
        this.f1800u = -1;
        this.f1801v = -1;
        this.f1802w = -1;
        this.f1803x = -1;
        this.f1789j = (SearchManager) this.d.getSystemService("search");
        this.f1790k = searchView;
        this.f1791l = searchableInfo;
        this.f1794o = searchView.getSuggestionCommitIconResId();
        this.f1792m = context;
        this.f1793n = weakHashMap;
    }

    public void m3719a(int i) {
        this.f1796q = i;
    }

    public boolean hasStableIds() {
        return false;
    }

    public Cursor m3716a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f1790k.getVisibility() != 0 || this.f1790k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor a = m3715a(this.f1791l, charSequence2, 50);
            if (a != null) {
                a.getCount();
                return a;
            }
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m3711d(m1723a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        m3711d(m1723a());
    }

    private void m3711d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    public void m3720a(Cursor cursor) {
        if (this.f1795p) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.m1727a(cursor);
            if (cursor != null) {
                this.f1798s = cursor.getColumnIndex("suggest_text_1");
                this.f1799t = cursor.getColumnIndex("suggest_text_2");
                this.f1800u = cursor.getColumnIndex("suggest_text_2_url");
                this.f1801v = cursor.getColumnIndex("suggest_icon_1");
                this.f1802w = cursor.getColumnIndex("suggest_icon_2");
                this.f1803x = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Throwable e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public View m3718a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.m1835a(context, cursor, viewGroup);
        a.setTag(new ChildViewCache(a));
        ((ImageView) a.findViewById(C0057R.id.edit_query)).setImageResource(this.f1794o);
        return a;
    }

    public void m3721a(View view, Context context, Cursor cursor) {
        ChildViewCache childViewCache = (ChildViewCache) view.getTag();
        int i;
        if (this.f1803x != -1) {
            i = cursor.getInt(this.f1803x);
        } else {
            i = 0;
        }
        if (childViewCache.f1784a != null) {
            m3705a(childViewCache.f1784a, m3702a(cursor, this.f1798s));
        }
        if (childViewCache.f1785b != null) {
            CharSequence a = m3702a(cursor, this.f1800u);
            if (a != null) {
                a = m3710b(a);
            } else {
                a = m3702a(cursor, this.f1799t);
            }
            if (TextUtils.isEmpty(a)) {
                if (childViewCache.f1784a != null) {
                    childViewCache.f1784a.setSingleLine(false);
                    childViewCache.f1784a.setMaxLines(2);
                }
            } else if (childViewCache.f1784a != null) {
                childViewCache.f1784a.setSingleLine(true);
                childViewCache.f1784a.setMaxLines(1);
            }
            m3705a(childViewCache.f1785b, a);
        }
        if (childViewCache.f1786c != null) {
            m3704a(childViewCache.f1786c, m3712e(cursor), 4);
        }
        if (childViewCache.f1787d != null) {
            m3704a(childViewCache.f1787d, m3713f(cursor), 8);
        }
        if (this.f1796q == 2 || (this.f1796q == 1 && (r1 & 1) != 0)) {
            childViewCache.f1788e.setVisibility(0);
            childViewCache.f1788e.setTag(childViewCache.f1784a.getText());
            childViewCache.f1788e.setOnClickListener(this);
            return;
        }
        childViewCache.f1788e.setVisibility(8);
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1790k.m3695a((CharSequence) tag);
        }
    }

    private CharSequence m3710b(CharSequence charSequence) {
        if (this.f1797r == null) {
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(C0057R.attr.textColorSearchUrl, typedValue, true);
            this.f1797r = this.d.getResources().getColorStateList(typedValue.resourceId);
        }
        CharSequence spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f1797r, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void m3705a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private Drawable m3712e(Cursor cursor) {
        if (this.f1801v == -1) {
            return null;
        }
        Drawable a = m3701a(cursor.getString(this.f1801v));
        return a == null ? m3714g(cursor) : a;
    }

    private Drawable m3713f(Cursor cursor) {
        if (this.f1802w == -1) {
            return null;
        }
        return m3701a(cursor.getString(this.f1802w));
    }

    private void m3704a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public CharSequence m3722c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String a = m3703a(cursor, "suggest_intent_query");
        if (a != null) {
            return a;
        }
        if (this.f1791l.shouldRewriteQueryFromData()) {
            a = m3703a(cursor, "suggest_intent_data");
            if (a != null) {
                return a;
            }
        }
        if (!this.f1791l.shouldRewriteQueryFromText()) {
            return null;
        }
        a = m3703a(cursor, "suggest_text_1");
        if (a != null) {
            return a;
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View a = m3718a(this.d, this.c, viewGroup);
            if (a != null) {
                ((ChildViewCache) a.getTag()).f1784a.setText(e.toString());
            }
            return a;
        }
    }

    private Drawable m3701a(String str) {
        Drawable b;
        if (str == null || str.length() == 0 || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f1792m.getPackageName() + "/" + parseInt;
            b = m3709b(str2);
            if (b != null) {
                return b;
            }
            b = ContextCompat.m98a(this.f1792m, parseInt);
            m3706a(str2, b);
            return b;
        } catch (NumberFormatException e) {
            b = m3709b(str);
            if (b != null) {
                return b;
            }
            b = m3708b(Uri.parse(str));
            m3706a(str, b);
            return b;
        } catch (NotFoundException e2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    private Drawable m3708b(Uri uri) {
        InputStream openInputStream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return m3717a(uri);
            }
            openInputStream = this.f1792m.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
                return createFromStream;
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                return createFromStream;
            }
        } catch (NotFoundException e2) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                openInputStream.close();
            } catch (Throwable e4) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e4);
            }
        }
    }

    private Drawable m3709b(String str) {
        ConstantState constantState = (ConstantState) this.f1793n.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private void m3706a(String str, Drawable drawable) {
        if (drawable != null) {
            this.f1793n.put(str, drawable.getConstantState());
        }
    }

    private Drawable m3714g(Cursor cursor) {
        Drawable a = m3700a(this.f1791l.getSearchActivity());
        return a != null ? a : this.d.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable m3700a(ComponentName componentName) {
        Object obj = null;
        String flattenToShortString = componentName.flattenToShortString();
        if (this.f1793n.containsKey(flattenToShortString)) {
            ConstantState constantState = (ConstantState) this.f1793n.get(flattenToShortString);
            return constantState == null ? null : constantState.newDrawable(this.f1792m.getResources());
        } else {
            Drawable b = m3707b(componentName);
            if (b != null) {
                obj = b.getConstantState();
            }
            this.f1793n.put(flattenToShortString, obj);
            return b;
        }
    }

    private Drawable m3707b(ComponentName componentName) {
        PackageManager packageManager = this.d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    public static String m3703a(Cursor cursor, String str) {
        return m3702a(cursor, cursor.getColumnIndex(str));
    }

    private static String m3702a(Cursor cursor, int i) {
        String str = null;
        if (i != -1) {
            try {
                str = cursor.getString(i);
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            }
        }
        return str;
    }

    Drawable m3717a(Uri uri) {
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.d.getPackageManager().getResourcesForApplication(authority);
            List pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    size = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size == 2) {
                size = resourcesForApplication.getIdentifier((String) pathSegments.get(1), (String) pathSegments.get(0), authority);
            } else {
                throw new FileNotFoundException("More than two path segments: " + uri);
            }
            if (size != 0) {
                return resourcesForApplication.getDrawable(size);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (NameNotFoundException e2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    Cursor m3715a(SearchableInfo searchableInfo, String str, int i) {
        if (searchableInfo == null) {
            return null;
        }
        String suggestAuthority = searchableInfo.getSuggestAuthority();
        if (suggestAuthority == null) {
            return null;
        }
        String[] strArr;
        Builder fragment = new Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
            strArr = null;
        }
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }
}
