package app.messaging.group;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import app.messaging.group.GroupSettingActivity$$ViewInjector$GroupSettingActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class GroupSettingActivity$$ViewInjector<T extends GroupSettingActivity> implements Injector<T> {

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3690a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3691b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3691b = groupSettingActivity$$ViewInjector;
            this.f3690a = groupSettingActivity;
        }

        public void m6289a(View view) {
            this.f3690a.m6326j();
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3692a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3693b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3693b = groupSettingActivity$$ViewInjector;
            this.f3692a = groupSettingActivity;
        }

        public void m6290a(View view) {
            this.f3692a.m6329m();
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3694a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3695b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3695b = groupSettingActivity$$ViewInjector;
            this.f3694a = groupSettingActivity;
        }

        public void m6291a(View view) {
            this.f3694a.m6330n();
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3696a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3697b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3697b = groupSettingActivity$$ViewInjector;
            this.f3696a = groupSettingActivity;
        }

        public void m6292a(View view) {
            this.f3696a.m6327k();
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3698a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3699b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3699b = groupSettingActivity$$ViewInjector;
            this.f3698a = groupSettingActivity;
        }

        public void m6293a(View view) {
            this.f3698a.m6328l();
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity$.ViewInjector.6 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ GroupSettingActivity f3700a;
        final /* synthetic */ GroupSettingActivity$$ViewInjector f3701b;

        ViewInjector(GroupSettingActivity$$ViewInjector groupSettingActivity$$ViewInjector, GroupSettingActivity groupSettingActivity) {
            this.f3701b = groupSettingActivity$$ViewInjector;
            this.f3700a = groupSettingActivity;
        }

        public void m6294a(View view) {
            this.f3700a.m6331o();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755181, "field 'groupAvatar' and method 'onGroupAvatarClicked'");
        t.f3721o = (ImageView) finder.m7731a(view, 2131755181, "field 'groupAvatar'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3722p = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755182, "field 'groupNameEditText'"), 2131755182, "field 'groupNameEditText'");
        t.f3723q = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755184, "field 'groupDescriptionEditText'"), 2131755184, "field 'groupDescriptionEditText'");
        t.f3724r = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755185, "field 'groupTypeTextView'"), 2131755185, "field 'groupTypeTextView'");
        t.f3725s = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755186, "field 'groupTypeDescriptionTextView'"), 2131755186, "field 'groupTypeDescriptionTextView'");
        t.f3726t = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755183, "field 'descriptionCounterTextView'"), 2131755183, "field 'descriptionCounterTextView'");
        view = (View) finder.m7732a(obj, 2131755188, "field 'notificationTextView' and method 'onNotificationTextViewClicked'");
        t.f3727u = (TextView) finder.m7731a(view, 2131755188, "field 'notificationTextView'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3728v = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755109, "field 'avatarEditTextView'"), 2131755109, "field 'avatarEditTextView'");
        view = (View) finder.m7732a(obj, 2131755191, "field 'destroyGroupButton' and method 'onLeaveAndDeleteClicked'");
        t.f3729w = (Button) finder.m7731a(view, 2131755191, "field 'destroyGroupButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755192, "field 'groupAvatarFullSize' and method 'onFullSizeImageClicked'");
        t.f3730x = (ImageView) finder.m7731a(view, 2131755192, "field 'groupAvatarFullSize'");
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755189, "method 'onGroupMemberClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755190, "method 'onDestroyGroupClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3721o = null;
        t.f3722p = null;
        t.f3723q = null;
        t.f3724r = null;
        t.f3725s = null;
        t.f3726t = null;
        t.f3727u = null;
        t.f3728v = null;
        t.f3729w = null;
        t.f3730x = null;
    }
}
