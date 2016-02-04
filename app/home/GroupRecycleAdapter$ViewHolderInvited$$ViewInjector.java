package app.home;

import android.view.View;
import android.widget.Button;
import app.home.GroupRecycleAdapter$ViewHolderInvited$$ViewInjector$GroupRecycleAdapter$ViewHolderInvited$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;

public class GroupRecycleAdapter$ViewHolderInvited$$ViewInjector<T extends ViewHolderInvited> extends GroupRecycleAdapter$ViewHolder$$ViewInjector<T> {

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderInvited$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderInvited f2965a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderInvited$$ViewInjector f2966b;

        ViewInjector(GroupRecycleAdapter$ViewHolderInvited$$ViewInjector groupRecycleAdapter$ViewHolderInvited$$ViewInjector, ViewHolderInvited viewHolderInvited) {
            this.f2966b = groupRecycleAdapter$ViewHolderInvited$$ViewInjector;
            this.f2965a = viewHolderInvited;
        }

        public void m5399a(View view) {
            this.f2965a.m5407z();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderInvited$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderInvited f2967a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderInvited$$ViewInjector f2968b;

        ViewInjector(GroupRecycleAdapter$ViewHolderInvited$$ViewInjector groupRecycleAdapter$ViewHolderInvited$$ViewInjector, ViewHolderInvited viewHolderInvited) {
            this.f2968b = groupRecycleAdapter$ViewHolderInvited$$ViewInjector;
            this.f2967a = viewHolderInvited;
        }

        public void m5400a(View view) {
            this.f2967a.m5403A();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderInvited$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderInvited f2969a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderInvited$$ViewInjector f2970b;

        ViewInjector(GroupRecycleAdapter$ViewHolderInvited$$ViewInjector groupRecycleAdapter$ViewHolderInvited$$ViewInjector, ViewHolderInvited viewHolderInvited) {
            this.f2970b = groupRecycleAdapter$ViewHolderInvited$$ViewInjector;
            this.f2969a = viewHolderInvited;
        }

        public void m5401a(View view) {
            this.f2969a.m5404B();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderInvited$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderInvited f2971a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderInvited$$ViewInjector f2972b;

        ViewInjector(GroupRecycleAdapter$ViewHolderInvited$$ViewInjector groupRecycleAdapter$ViewHolderInvited$$ViewInjector, ViewHolderInvited viewHolderInvited) {
            this.f2972b = groupRecycleAdapter$ViewHolderInvited$$ViewInjector;
            this.f2971a = viewHolderInvited;
        }

        public void m5402a(View view) {
            this.f2971a.m5406y();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (ViewHolder) t, obj);
        View view = (View) finder.m7732a(obj, 2131755604, "field 'acceptButton' and method 'onAcceptClicked'");
        t.f2980y = (Button) finder.m7731a(view, 2131755604, "field 'acceptButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755603, "field 'ignoreButton' and method 'onIgnoreClicked'");
        t.f2981z = (Button) finder.m7731a(view, 2131755603, "field 'ignoreButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755602, "field 'blockButton' and method 'onBlockClicked'");
        t.f2978A = (Button) finder.m7731a(view, 2131755602, "field 'blockButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755592, "method 'onRowClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        super.reset((ViewHolder) t);
        t.f2980y = null;
        t.f2981z = null;
        t.f2978A = null;
    }
}
