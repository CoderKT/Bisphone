package app.messaging.group;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import app.messaging.group.NewGroupMembersActivity$$ViewInjector$NewGroupMembersActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class NewGroupMembersActivity$$ViewInjector<T extends NewGroupMembersActivity> implements Injector<T> {

    /* renamed from: app.messaging.group.NewGroupMembersActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ NewGroupMembersActivity f3742a;
        final /* synthetic */ NewGroupMembersActivity$$ViewInjector f3743b;

        ViewInjector(NewGroupMembersActivity$$ViewInjector newGroupMembersActivity$$ViewInjector, NewGroupMembersActivity newGroupMembersActivity) {
            this.f3743b = newGroupMembersActivity$$ViewInjector;
            this.f3742a = newGroupMembersActivity;
        }

        public void m6343a(View view) {
            this.f3742a.m6349j();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f3763o = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755220, "field 'mListView'"), 2131755220, "field 'mListView'");
        View view = (View) finder.m7732a(obj, 2131755219, "field 'addMembersButton' and method 'onAddMembersClicked'");
        t.f3764p = (ImageButton) finder.m7731a(view, 2131755219, "field 'addMembersButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3765q = (AutoCompleteTextView) finder.m7731a((View) finder.m7732a(obj, 2131755218, "field 'addMembersAutoComplete'"), 2131755218, "field 'addMembersAutoComplete'");
    }

    public void reset(T t) {
        t.f3763o = null;
        t.f3764p = null;
        t.f3765q = null;
    }
}
