package app.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SettingContentLanguageAdapter extends BaseAdapter implements OnClickListener, Filterable {
    Context f4362a;
    String[] f4363b;
    List<String> f4364c;
    LayoutInflater f4365d;
    HashMap<String, Integer> f4366e;

    /* renamed from: app.setting.SettingContentLanguageAdapter.1 */
    class C04581 extends Filter {
        final /* synthetic */ SettingContentLanguageAdapter f4357a;

        C04581(SettingContentLanguageAdapter settingContentLanguageAdapter) {
            this.f4357a = settingContentLanguageAdapter;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            List a = this.f4357a.m6790a(charSequence);
            FilterResults filterResults = new FilterResults();
            filterResults.values = a;
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            this.f4357a.f4364c = (List) filterResults.values;
            this.f4357a.notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView f4358a;
        CheckBox f4359b;
        int f4360c;
        final /* synthetic */ SettingContentLanguageAdapter f4361d;

        ViewHolder(SettingContentLanguageAdapter settingContentLanguageAdapter) {
            this.f4361d = settingContentLanguageAdapter;
        }
    }

    public HashMap<String, Integer> m6791a() {
        return this.f4366e;
    }

    public SettingContentLanguageAdapter(Context context, String[] strArr, HashMap<String, Integer> hashMap) {
        this.f4362a = context;
        this.f4366e = hashMap;
        this.f4363b = strArr;
        this.f4364c = Arrays.asList(strArr);
        this.f4365d = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.f4364c == null ? 0 : this.f4364c.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f4365d.inflate(2130903234, viewGroup, false);
            viewHolder = new ViewHolder(this);
            viewHolder.f4359b = (CheckBox) view.findViewById(2131755618);
            viewHolder.f4358a = (TextView) view.findViewById(2131755617);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f4360c = i;
        viewHolder.f4358a.setText((CharSequence) this.f4364c.get(i));
        if (this.f4366e.get(this.f4364c.get(i)) == null) {
            viewHolder.f4359b.setChecked(false);
        } else {
            viewHolder.f4359b.setChecked(true);
        }
        view.setOnClickListener(this);
        return view;
    }

    private List<String> m6790a(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            this.f4364c = Arrays.asList(this.f4363b);
            return this.f4364c;
        }
        this.f4364c = new ArrayList();
        for (int i = 0; i < this.f4363b.length; i++) {
            if (this.f4363b[i].toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                this.f4364c.add(this.f4363b[i]);
            }
        }
        return this.f4364c;
    }

    public void onClick(View view) {
        int i = ((ViewHolder) view.getTag()).f4360c;
        if (this.f4366e.get(this.f4364c.get(i)) == null) {
            this.f4366e.put(this.f4364c.get(i), Integer.valueOf(i));
        } else {
            this.f4366e.remove(this.f4364c.get(i));
        }
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return new C04581(this);
    }
}
