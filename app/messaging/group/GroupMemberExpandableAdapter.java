package app.messaging.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.database.datasource.ContactDataSource;
import app.xmpp.packet.VcardInfoEntity;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupMemberExpandableAdapter extends BaseExpandableListAdapter implements OnClickListener {
    private List<ArrayList<GroupSettingModel>> f3678a;
    private HashMap<String, VcardInfoEntity> f3679b;
    private Context f3680c;
    private LayoutInflater f3681d;
    private ContactEntity f3682e;
    private int f3683f;
    private int f3684g;
    private int f3685h;
    private int f3686i;
    private int f3687j;
    private int f3688k;
    private boolean f3689l;

    class ChildViewHolder {
        TextView f3670a;
        RoundedImageView f3671b;
        ImageView f3672c;
        TextView f3673d;
        View f3674e;
        int f3675f;
        int f3676g;
        final /* synthetic */ GroupMemberExpandableAdapter f3677h;

        ChildViewHolder(GroupMemberExpandableAdapter groupMemberExpandableAdapter) {
            this.f3677h = groupMemberExpandableAdapter;
        }
    }

    public GroupMemberExpandableAdapter(Context context, List<ArrayList<GroupSettingModel>> list, int i) {
        this.f3685h = 0;
        this.f3686i = 1;
        this.f3687j = 2;
        this.f3688k = 3;
        this.f3689l = false;
        this.f3680c = context;
        this.f3681d = (LayoutInflater) context.getSystemService("layout_inflater");
        if (i == 0) {
            i = 150;
        }
        this.f3683f = i;
        m6282a((List) list);
        this.f3679b = new HashMap();
        this.f3682e = ContactDataSource.m4553a().m4581g();
    }

    public int getGroupCount() {
        return this.f3678a.size() + 1;
    }

    public int getChildrenCount(int i) {
        if (i >= this.f3678a.size()) {
            return 0;
        }
        return ((ArrayList) this.f3678a.get(i)).size();
    }

    public Object getGroup(int i) {
        return this.f3678a.get(i);
    }

    public Object getChild(int i, int i2) {
        return ((ArrayList) this.f3678a.get(i)).get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) ((i * 10) + i2);
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        CharSequence charSequence;
        int i2 = 0;
        if (view == null) {
            view = this.f3681d.inflate(2130903224, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(2131755589);
        TextView textView2 = (TextView) view.findViewById(2131755590);
        String str = "";
        view.findViewById(2131755591).setVisibility(8);
        if (i == this.f3685h) {
            textView.setText(this.f3680c.getString(2131296458));
            charSequence = str;
        } else if (i == this.f3686i) {
            textView.setText(this.f3680c.getString(2131296484));
            charSequence = "(" + ((ArrayList) this.f3678a.get(this.f3686i)).size() + ")";
        } else if (i == this.f3687j) {
            textView.setText(this.f3680c.getString(2131296481));
            charSequence = "(" + ((ArrayList) this.f3678a.get(this.f3687j)).size() + ")";
        } else if (i == this.f3688k) {
            textView.setText(this.f3680c.getString(2131296480));
            charSequence = "(" + this.f3684g + ")";
            view.findViewById(2131755591).setVisibility(0);
        } else {
            Object obj = str;
        }
        if (i == 0) {
            int i3 = 0;
            while (i2 < this.f3688k) {
                i3 += ((ArrayList) this.f3678a.get(i2)).size();
                i2++;
            }
            charSequence = "(" + (this.f3684g + i3) + "/" + this.f3683f + ")";
        }
        textView2.setText(charSequence);
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view == null) {
            view = this.f3681d.inflate(2130903229, viewGroup, false);
            ChildViewHolder childViewHolder2 = new ChildViewHolder(this);
            childViewHolder2.f3670a = (TextView) view.findViewById(2131755117);
            childViewHolder2.f3671b = (RoundedImageView) view.findViewById(2131755116);
            childViewHolder2.f3672c = (ImageView) view.findViewById(2131755606);
            childViewHolder2.f3673d = (TextView) view.findViewById(2131755570);
            childViewHolder2.f3674e = view.findViewById(2131755605);
            view.setTag(childViewHolder2);
            childViewHolder = childViewHolder2;
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        childViewHolder.f3676g = i2;
        childViewHolder.f3675f = i;
        if (this.f3679b.containsKey(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b())) {
            ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6336b(((VcardInfoEntity) this.f3679b.get(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b())).m7530a());
            ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6333a(((VcardInfoEntity) this.f3679b.get(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b())).m7531b());
            this.f3679b.remove(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b());
        }
        ImageView imageView = childViewHolder.f3672c;
        int i3 = (this.f3689l && i == this.f3686i && !((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6342h()) ? 0 : 8;
        imageView.setVisibility(i3);
        childViewHolder.f3671b.setVisibility(0);
        if (((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6339e()) {
            CustomImageLoader.m4009a().m4020a(childViewHolder.f3671b, this.f3682e.m4212o(), null, 2130837592);
        } else {
            CustomImageLoader.m4009a().m4020a(childViewHolder.f3671b, ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6332a(), ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6338d(), 2130837592);
        }
        if (((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6340f() == TYPE.SELF) {
            childViewHolder.f3670a.setText(this.f3680c.getString(2131296560));
            childViewHolder.f3673d.setText("+" + ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b());
        } else if (((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6340f() == TYPE.LOCAL) {
            childViewHolder.f3670a.setText(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6337c());
            childViewHolder.f3673d.setText("+" + ((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6335b());
        } else {
            childViewHolder.f3673d.setText("");
            if (((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6341g() != null) {
                childViewHolder.f3670a.setText(((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6341g());
            } else {
                childViewHolder.f3670a.setText(this.f3680c.getResources().getString(2131296307));
            }
        }
        if (((GroupSettingModel) ((ArrayList) this.f3678a.get(i)).get(i2)).m6342h()) {
            childViewHolder.f3670a.setTextColor(this.f3680c.getResources().getColor(2131689515));
            childViewHolder.f3673d.setTextColor(this.f3680c.getResources().getColor(2131689515));
        } else {
            childViewHolder.f3670a.setTextColor(this.f3680c.getResources().getColor(2131689627));
            childViewHolder.f3673d.setTextColor(this.f3680c.getResources().getColor(2131689627));
        }
        if (i == this.f3687j) {
            childViewHolder.f3674e.setVisibility(0);
        } else {
            childViewHolder.f3674e.setVisibility(8);
        }
        childViewHolder.f3674e.setOnClickListener(this);
        childViewHolder.f3672c.setOnClickListener(this);
        childViewHolder.f3674e.setTag(childViewHolder);
        childViewHolder.f3672c.setTag(childViewHolder);
        view.setOnClickListener(this);
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void m6280a(int i) {
        this.f3684g = i;
    }

    public void m6282a(List<ArrayList<GroupSettingModel>> list) {
        m6279b(list);
        this.f3678a = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!((ArrayList) list.get(i)).isEmpty()) {
                this.f3678a.add(list.get(i));
            }
        }
    }

    private void m6279b(List<ArrayList<GroupSettingModel>> list) {
        int i;
        int i2 = 0;
        int i3 = -1;
        if (((ArrayList) list.get(0)).isEmpty()) {
            i = -1;
            i2 = -1;
        } else {
            i = 0;
        }
        this.f3685h = i;
        if (((ArrayList) list.get(1)).isEmpty()) {
            i = -1;
        } else {
            i = i2 + 1;
            i2 = i;
        }
        this.f3686i = i;
        if (!((ArrayList) list.get(2)).isEmpty()) {
            i3 = i2 + 1;
            i2 = i3;
        }
        this.f3687j = i3;
        this.f3688k = i2 + 1;
    }

    public void m6283a(boolean z) {
        this.f3689l = z;
    }

    public boolean m6284a() {
        return this.f3689l;
    }

    public void m6281a(HashMap<String, VcardInfoEntity> hashMap) {
        this.f3679b = hashMap;
    }

    public int m6285b() {
        return this.f3688k;
    }

    public int m6286c() {
        return this.f3687j;
    }

    public int m6287d() {
        return this.f3686i;
    }

    public List<ArrayList<GroupSettingModel>> m6288e() {
        return this.f3678a;
    }

    public void onClick(View view) {
        ChildViewHolder childViewHolder = (ChildViewHolder) view.getTag();
        if (!m6284a()) {
            switch (view.getId()) {
                case 2131755605:
                    ((GroupMemberActivity) this.f3680c).m6277c(childViewHolder.f3675f, childViewHolder.f3676g);
                case 2131755606:
                    if (childViewHolder.f3675f != m6285b() && m6284a() && childViewHolder.f3675f == m6287d()) {
                        ((GroupMemberActivity) this.f3680c).m6276b(childViewHolder.f3675f, childViewHolder.f3676g);
                    }
                default:
                    ((GroupMemberActivity) this.f3680c).m6275a(childViewHolder.f3675f, childViewHolder.f3676g);
            }
        } else if (childViewHolder.f3675f == m6287d()) {
            ((GroupMemberActivity) this.f3680c).m6276b(childViewHolder.f3675f, childViewHolder.f3676g);
        }
    }
}
