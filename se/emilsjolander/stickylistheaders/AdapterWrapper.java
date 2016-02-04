package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import java.util.LinkedList;
import java.util.List;

class AdapterWrapper extends BaseAdapter implements StickyListHeadersAdapter {
    StickyListHeadersAdapter f8649a;
    private final List<View> f8650b;
    private final Context f8651c;
    private Drawable f8652d;
    private int f8653e;
    private OnHeaderClickListener f8654f;
    private DataSetObserver f8655g;

    /* renamed from: se.emilsjolander.stickylistheaders.AdapterWrapper.1 */
    class C11261 extends DataSetObserver {
        final /* synthetic */ AdapterWrapper f8646a;

        C11261(AdapterWrapper adapterWrapper) {
            this.f8646a = adapterWrapper;
        }

        public void onInvalidated() {
            this.f8646a.f8650b.clear();
            super.notifyDataSetInvalidated();
        }

        public void onChanged() {
            super.notifyDataSetChanged();
        }
    }

    /* renamed from: se.emilsjolander.stickylistheaders.AdapterWrapper.2 */
    class C11272 implements OnClickListener {
        final /* synthetic */ int f8647a;
        final /* synthetic */ AdapterWrapper f8648b;

        C11272(AdapterWrapper adapterWrapper, int i) {
            this.f8648b = adapterWrapper;
            this.f8647a = i;
        }

        public void onClick(View view) {
            if (this.f8648b.f8654f != null) {
                this.f8648b.f8654f.m13462a(view, this.f8647a, this.f8648b.f8649a.m5370b(this.f8647a));
            }
        }
    }

    interface OnHeaderClickListener {
        void m13462a(View view, int i, long j);
    }

    public /* synthetic */ View getView(int i, View view, ViewGroup viewGroup) {
        return m13475b(i, view, viewGroup);
    }

    AdapterWrapper(Context context, StickyListHeadersAdapter stickyListHeadersAdapter) {
        this.f8650b = new LinkedList();
        this.f8655g = new C11261(this);
        this.f8651c = context;
        this.f8649a = stickyListHeadersAdapter;
        stickyListHeadersAdapter.registerDataSetObserver(this.f8655g);
    }

    void m13472a(Drawable drawable, int i) {
        this.f8652d = drawable;
        this.f8653e = i;
        notifyDataSetChanged();
    }

    public boolean areAllItemsEnabled() {
        return this.f8649a.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.f8649a.isEnabled(i);
    }

    public int getCount() {
        return this.f8649a.getCount();
    }

    public Object getItem(int i) {
        return this.f8649a.getItem(i);
    }

    public long getItemId(int i) {
        return this.f8649a.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.f8649a.hasStableIds();
    }

    public int getItemViewType(int i) {
        return this.f8649a.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.f8649a.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.f8649a.isEmpty();
    }

    private void m13466a(WrapperView wrapperView) {
        View view = wrapperView.f8659d;
        if (view != null) {
            view.setVisibility(0);
            this.f8650b.add(view);
        }
    }

    private View m13464a(WrapperView wrapperView, int i) {
        View a = this.f8649a.m5369a(i, wrapperView.f8659d == null ? m13463a() : wrapperView.f8659d, wrapperView);
        if (a == null) {
            throw new NullPointerException("Header view must not be null.");
        }
        a.setClickable(true);
        a.setOnClickListener(new C11272(this, i));
        return a;
    }

    private View m13463a() {
        if (this.f8650b.size() > 0) {
            return (View) this.f8650b.remove(0);
        }
        return null;
    }

    private boolean m13467a(int i) {
        return i != 0 && this.f8649a.m5370b(i) == this.f8649a.m5370b(i - 1);
    }

    public WrapperView m13475b(int i, View view, ViewGroup viewGroup) {
        WrapperView wrapperView = view == null ? new WrapperView(this.f8651c) : (WrapperView) view;
        View view2 = this.f8649a.getView(i, wrapperView.f8656a, viewGroup);
        View view3 = null;
        if (m13467a(i)) {
            m13466a(wrapperView);
        } else {
            view3 = m13464a(wrapperView, i);
        }
        if ((view2 instanceof Checkable) && !(wrapperView instanceof CheckableWrapperView)) {
            view = new CheckableWrapperView(this.f8651c);
        } else if (!(view2 instanceof Checkable) && (wrapperView instanceof CheckableWrapperView)) {
            view = new WrapperView(this.f8651c);
        }
        view.m13476a(view2, view3, this.f8652d, this.f8653e);
        return view;
    }

    public void m13473a(OnHeaderClickListener onHeaderClickListener) {
        this.f8654f = onHeaderClickListener;
    }

    public boolean equals(Object obj) {
        return this.f8649a.equals(obj);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return ((BaseAdapter) this.f8649a).getDropDownView(i, view, viewGroup);
    }

    public int hashCode() {
        return this.f8649a.hashCode();
    }

    public void notifyDataSetChanged() {
        ((BaseAdapter) this.f8649a).notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated() {
        ((BaseAdapter) this.f8649a).notifyDataSetInvalidated();
    }

    public String toString() {
        return this.f8649a.toString();
    }

    public View m13471a(int i, View view, ViewGroup viewGroup) {
        return this.f8649a.m5369a(i, view, viewGroup);
    }

    public long m13474b(int i) {
        return this.f8649a.m5370b(i);
    }
}
