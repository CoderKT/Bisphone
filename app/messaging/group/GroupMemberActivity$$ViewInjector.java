package app.messaging.group;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import app.messaging.group.GroupMemberActivity$$ViewInjector$GroupMemberActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class GroupMemberActivity$$ViewInjector<T extends GroupMemberActivity> implements Injector<T> {

    /* renamed from: app.messaging.group.GroupMemberActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupMemberActivity f3636a;
        final /* synthetic */ GroupMemberActivity$$ViewInjector f3637b;

        ViewInjector(GroupMemberActivity$$ViewInjector groupMemberActivity$$ViewInjector, GroupMemberActivity groupMemberActivity) {
            this.f3637b = groupMemberActivity$$ViewInjector;
            this.f3636a = groupMemberActivity;
        }

        public void m6251a(View view) {
            this.f3636a.m6278j();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f3658o = (ExpandableListView) finder.m7731a((View) finder.m7732a(obj, 2131755179, "field 'expandableListView'"), 2131755179, "field 'expandableListView'");
        View view = (View) finder.m7732a(obj, 2131755180, "field 'inviteButton' and method 'onInviteClick'");
        t.f3659p = (Button) finder.m7731a(view, 2131755180, "field 'inviteButton'");
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3658o = null;
        t.f3659p = null;
    }
}
