package app.home;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import app.home.GroupRecycleAdapter$ViewHolderJoined$$ViewInjector$GroupRecycleAdapter$ViewHolderJoined$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;

public class GroupRecycleAdapter$ViewHolderJoined$$ViewInjector<T extends ViewHolderJoined> extends GroupRecycleAdapter$ViewHolder$$ViewInjector<T> {

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderJoined$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderJoined f2982a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderJoined$$ViewInjector f2983b;

        ViewInjector(GroupRecycleAdapter$ViewHolderJoined$$ViewInjector groupRecycleAdapter$ViewHolderJoined$$ViewInjector, ViewHolderJoined viewHolderJoined) {
            this.f2983b = groupRecycleAdapter$ViewHolderJoined$$ViewInjector;
            this.f2982a = viewHolderJoined;
        }

        public void m5408a(View view) {
            this.f2982a.m5411A();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderJoined$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderJoined f2984a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderJoined$$ViewInjector f2985b;

        ViewInjector(GroupRecycleAdapter$ViewHolderJoined$$ViewInjector groupRecycleAdapter$ViewHolderJoined$$ViewInjector, ViewHolderJoined viewHolderJoined) {
            this.f2985b = groupRecycleAdapter$ViewHolderJoined$$ViewInjector;
            this.f2984a = viewHolderJoined;
        }

        public void m5409a(View view) {
            this.f2984a.m5412B();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderJoined$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolderJoined f2986a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderJoined$$ViewInjector f2987b;

        ViewInjector(GroupRecycleAdapter$ViewHolderJoined$$ViewInjector groupRecycleAdapter$ViewHolderJoined$$ViewInjector, ViewHolderJoined viewHolderJoined) {
            this.f2987b = groupRecycleAdapter$ViewHolderJoined$$ViewInjector;
            this.f2986a = viewHolderJoined;
        }

        public void m5410a(View view) {
            this.f2986a.m5414y();
        }
    }

    /* renamed from: app.home.GroupRecycleAdapter$ViewHolderJoined$.ViewInjector.4 */
    class ViewInjector implements OnLongClickListener {
        final /* synthetic */ ViewHolderJoined f2988a;
        final /* synthetic */ GroupRecycleAdapter$ViewHolderJoined$$ViewInjector f2989b;

        ViewInjector(GroupRecycleAdapter$ViewHolderJoined$$ViewInjector groupRecycleAdapter$ViewHolderJoined$$ViewInjector, ViewHolderJoined viewHolderJoined) {
            this.f2989b = groupRecycleAdapter$ViewHolderJoined$$ViewInjector;
            this.f2988a = viewHolderJoined;
        }

        public boolean onLongClick(View view) {
            return this.f2988a.m5415z();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (ViewHolder) t, obj);
        t.f3001y = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755596, "field 'memberAvatar'"), 2131755596, "field 'memberAvatar'");
        t.f3002z = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755598, "field 'date'"), 2131755598, "field 'date'");
        t.f2997A = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755599, "field 'badge'"), 2131755599, "field 'badge'");
        View view = (View) finder.m7732a(obj, 2131755557, "field 'cancelButton' and method 'onCancelClicked'");
        t.f2998B = (Button) finder.m7731a(view, 2131755557, "field 'cancelButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755601, "field 'leaveButton' and method 'onLeaveClicked'");
        t.f2999C = (Button) finder.m7731a(view, 2131755601, "field 'leaveButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755592, "method 'onRowClicked' and method 'onRowLongClicked'");
        view.setOnClickListener(new ViewInjector(this, t));
        view.setOnLongClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        super.reset((ViewHolder) t);
        t.f3001y = null;
        t.f3002z = null;
        t.f2997A = null;
        t.f2998B = null;
        t.f2999C = null;
    }
}
