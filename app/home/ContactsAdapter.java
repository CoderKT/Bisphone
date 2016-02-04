package app.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.profile.ProfileModel;
import app.profile.ProfileViewer;
import app.signin.LogoutReceiver;
import app.util.DeviceUtils;
import butterknife.ButterKnife;
import java.util.HashMap;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class ContactsAdapter extends CursorAdapter implements SectionIndexer, StickyListHeadersAdapter {
    private LayoutInflater f2920a;
    private Context f2921b;
    private AlphabetIndexer f2922c;
    private RowOnClickListener f2923d;
    private int f2924e;
    private HashMap<Integer, Boolean> f2925f;
    private boolean f2926g;
    private boolean f2927h;
    private String f2928i;

    /* renamed from: app.home.ContactsAdapter.1 */
    class C02071 implements OnClickListener {
        final /* synthetic */ ContactsAdapter f2903a;

        C02071(ContactsAdapter contactsAdapter) {
            this.f2903a = contactsAdapter;
        }

        public void onClick(View view) {
            if (this.f2903a.f2923d != null) {
                this.f2903a.f2923d.m5368a(view);
            }
        }
    }

    /* renamed from: app.home.ContactsAdapter.2 */
    class C02082 implements OnClickListener {
        final /* synthetic */ String f2904a;
        final /* synthetic */ String f2905b;
        final /* synthetic */ ViewHolder f2906c;
        final /* synthetic */ ContactsAdapter f2907d;

        C02082(ContactsAdapter contactsAdapter, String str, String str2, ViewHolder viewHolder) {
            this.f2907d = contactsAdapter;
            this.f2904a = str;
            this.f2905b = str2;
            this.f2906c = viewHolder;
        }

        public void onClick(View view) {
            Parcelable profileModel = new ProfileModel(this.f2904a, this.f2905b, this.f2906c.f2911d, this.f2906c.f2913f, this.f2906c.f2912e, this.f2906c.f2914g, this.f2906c.f2915h, this.f2906c.f2916i);
            Intent intent = new Intent(this.f2907d.f2921b, ProfileViewer.class);
            intent.putExtra(DataPacketExtension.ELEMENT, profileModel);
            this.f2907d.f2921b.startActivity(intent);
            if (this.f2907d.f2928i != null && this.f2907d.f2928i.length() > 0) {
                this.f2907d.f2927h = true;
            }
        }
    }

    interface RowOnClickListener {
        void m5368a(View view);
    }

    class ViewHolder {
        TextView f2908a;
        ImageView f2909b;
        ImageView f2910c;
        String f2911d;
        String f2912e;
        String f2913f;
        TYPE f2914g;
        long f2915h;
        String f2916i;
        final /* synthetic */ ContactsAdapter f2917j;

        ViewHolder(ContactsAdapter contactsAdapter, View view) {
            this.f2917j = contactsAdapter;
            ButterKnife.m7744a(this, view);
        }
    }

    class ViewHolderHeader {
        TextView f2918a;
        final /* synthetic */ ContactsAdapter f2919b;

        ViewHolderHeader(ContactsAdapter contactsAdapter, View view) {
            this.f2919b = contactsAdapter;
            ButterKnife.m7744a(this, view);
        }
    }

    public void m5381a(boolean z) {
        this.f2926g = z;
    }

    public ContactsAdapter(Context context, Cursor cursor) {
        super(context, cursor, 2);
        this.f2924e = -1;
        this.f2925f = new HashMap();
        this.f2921b = context;
        this.f2920a = LayoutInflater.from(context);
        if (cursor != null) {
            m5379a(cursor);
        }
        if (!DeviceUtils.m7013b(context)) {
            context.sendBroadcast(new Intent(context, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
        }
    }

    public String m5377a(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor == null || cursor.getCount() < i) {
            return "";
        }
        return cursor.getString(cursor.getColumnIndex("name")) == null ? "(No Name)" : cursor.getString(cursor.getColumnIndex("name"));
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.f2920a.inflate(2130903216, viewGroup, false);
        inflate.setTag(new ViewHolder(this, inflate));
        inflate.setOnClickListener(new C02071(this));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.f2908a.setText(cursor.getString(cursor.getColumnIndex("name")));
        viewHolder.f2911d = cursor.getString(1);
        viewHolder.f2912e = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        viewHolder.f2913f = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        viewHolder.f2916i = cursor.getString(cursor.getColumnIndex("vcard_nickname"));
        viewHolder.f2914g = ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("type")));
        viewHolder.f2915h = cursor.getLong(cursor.getColumnIndex("recently_joined"));
        String string = cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar"));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("photo_uri"));
        if (cursor.getInt(cursor.getColumnIndex("is_registered")) < 1 || !this.f2926g) {
            viewHolder.f2910c.setVisibility(4);
        } else {
            viewHolder.f2910c.setVisibility(0);
        }
        CustomImageLoader.m4009a().m4020a(viewHolder.f2909b, string, string2, 2130837592);
        view.setOnClickListener(new C02082(this, string, string2, viewHolder));
    }

    protected void onContentChanged() {
        super.onContentChanged();
    }

    public Cursor swapCursor(Cursor cursor) {
        m5379a(cursor);
        return super.swapCursor(cursor);
    }

    public void m5379a(Cursor cursor) {
        if (this.f2922c == null) {
            this.f2922c = new AlphabetIndexer(cursor, 3, this.f2921b.getString(2131296333));
        } else {
            this.f2922c.setCursor(cursor);
        }
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        if (this.f2922c == null || getCursor() == null) {
            return i2;
        }
        try {
            return this.f2922c.getSectionForPosition(i);
        } catch (CursorIndexOutOfBoundsException e) {
            return i2;
        } catch (NullPointerException e2) {
            if (i != 0) {
                return i - 1;
            }
            return i2;
        }
    }

    public int getPositionForSection(int i) {
        return this.f2922c.getPositionForSection(i);
    }

    public Object[] getSections() {
        return this.f2922c == null ? null : this.f2922c.getSections();
    }

    public View m5375a(int i, View view, ViewGroup viewGroup) {
        CharSequence charSequence;
        if (view == null) {
            view = this.f2920a.inflate(2130903117, viewGroup, false);
            view.setTag(new ViewHolderHeader(this, view));
        }
        ViewHolderHeader viewHolderHeader = (ViewHolderHeader) view.getTag();
        try {
            Integer.parseInt(Character.toUpperCase(m5377a(i).charAt(0)) + "");
            charSequence = "#";
        } catch (Exception e) {
            charSequence = Character.toUpperCase(m5377a(i).charAt(0)) + "";
        }
        if (m5377a(i).equals("(No Name)")) {
            charSequence = "(No Name)";
        }
        if (i < this.f2924e) {
            charSequence = this.f2921b.getString(2131296666);
        }
        viewHolderHeader.f2918a.setText(charSequence);
        return view;
    }

    public long m5382b(int i) {
        if (i < this.f2924e) {
            return 1;
        }
        return (long) Character.toUpperCase(m5377a(i).substring(0, 1).charAt(0));
    }

    public void m5380a(RowOnClickListener rowOnClickListener) {
        this.f2923d = rowOnClickListener;
    }

    public void m5385c(int i) {
        this.f2924e = i;
    }

    public String m5378a(String str) {
        this.f2928i = str;
        return str;
    }

    public String m5376a() {
        return this.f2928i;
    }

    public boolean m5384b() {
        return this.f2927h;
    }

    public void m5383b(boolean z) {
        this.f2927h = z;
    }
}
