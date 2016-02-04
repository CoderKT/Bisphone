package app.messaging.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import se.emilsjolander.stickylistheaders.C1128R;

public class ExtraKeyboardFragment extends Fragment {
    private static ExtraKeyboardFragment f3561b;
    GridView f3562a;
    private RecycleFragmentsListener f3563c;
    private View f3564d;
    private String[] f3565e;
    private final int[] f3566f;

    /* renamed from: app.messaging.fragments.ExtraKeyboardFragment.1 */
    class C03101 implements OnItemClickListener {
        final /* synthetic */ ExtraKeyboardFragment f3554a;

        C03101(ExtraKeyboardFragment extraKeyboardFragment) {
            this.f3554a = extraKeyboardFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f3554a.f3563c.m5701F();
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3554a.f3563c.m5702G();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f3554a.f3563c.m5703H();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f3554a.f3563c.m5704I();
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f3554a.f3563c.m5705J();
                default:
            }
        }
    }

    class ExtraItemViewHolder {
        ImageView f3555a;
        TextView f3556b;
        final /* synthetic */ ExtraKeyboardFragment f3557c;

        private ExtraItemViewHolder(ExtraKeyboardFragment extraKeyboardFragment) {
            this.f3557c = extraKeyboardFragment;
        }
    }

    class ExtraMenuGridAdapter extends BaseAdapter {
        final /* synthetic */ ExtraKeyboardFragment f3558a;
        private Context f3559b;
        private LayoutInflater f3560c;

        public ExtraMenuGridAdapter(ExtraKeyboardFragment extraKeyboardFragment, Context context) {
            this.f3558a = extraKeyboardFragment;
            this.f3559b = context;
            this.f3560c = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return 5;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ExtraItemViewHolder extraItemViewHolder;
            if (view == null) {
                ExtraItemViewHolder extraItemViewHolder2 = new ExtraItemViewHolder(null);
                view = this.f3560c.inflate(2130903219, viewGroup, false);
                extraItemViewHolder2.f3555a = (ImageView) view.findViewById(2131755576);
                extraItemViewHolder2.f3556b = (TextView) view.findViewById(2131755577);
                view.setTag(extraItemViewHolder2);
                extraItemViewHolder = extraItemViewHolder2;
            } else {
                extraItemViewHolder = (ExtraItemViewHolder) view.getTag();
            }
            extraItemViewHolder.f3555a.setImageResource(this.f3558a.f3566f[i]);
            extraItemViewHolder.f3556b.setText(this.f3558a.f3565e[i]);
            return view;
        }
    }

    public ExtraKeyboardFragment() {
        this.f3566f = new int[]{2130837853, 2130837854, 2130837858, 2130837855, 2130837856};
    }

    public static synchronized ExtraKeyboardFragment m6149a() {
        ExtraKeyboardFragment extraKeyboardFragment;
        synchronized (ExtraKeyboardFragment.class) {
            if (f3561b == null) {
                f3561b = new ExtraKeyboardFragment();
            }
            extraKeyboardFragment = f3561b;
        }
        return extraKeyboardFragment;
    }

    public static void m6147M() {
        if (f3561b != null) {
            f3561b = null;
        }
    }

    public View m6153a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3564d = layoutInflater.inflate(2130903135, viewGroup, false);
        this.f3565e = m229j().getStringArray(2131361792);
        this.f3562a = (GridView) this.f3564d.findViewById(2131755378);
        this.f3562a.setAdapter(new ExtraMenuGridAdapter(this, m227i()));
        m6148N();
        return this.f3564d;
    }

    public void m6154a(Activity activity) {
        super.m188a(activity);
        try {
            this.f3563c = (RecycleFragmentsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement RecycleFragmentsListener");
        }
    }

    public void m6155b() {
        super.m204b();
        this.f3563c = null;
    }

    private void m6148N() {
        this.f3562a.setOnItemClickListener(new C03101(this));
    }

    public void m6156e(Bundle bundle) {
    }
}
