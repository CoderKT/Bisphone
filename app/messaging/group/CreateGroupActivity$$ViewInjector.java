package app.messaging.group;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import app.messaging.group.CreateGroupActivity$$ViewInjector$CreateGroupActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.makeramen.roundedimageview.RoundedImageView;

public class CreateGroupActivity$$ViewInjector<T extends CreateGroupActivity> implements Injector<T> {

    /* renamed from: app.messaging.group.CreateGroupActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CreateGroupActivity f3594a;
        final /* synthetic */ CreateGroupActivity$$ViewInjector f3595b;

        ViewInjector(CreateGroupActivity$$ViewInjector createGroupActivity$$ViewInjector, CreateGroupActivity createGroupActivity) {
            this.f3595b = createGroupActivity$$ViewInjector;
            this.f3594a = createGroupActivity;
        }

        public void m6205a(View view) {
            this.f3594a.m6243j();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CreateGroupActivity f3596a;
        final /* synthetic */ CreateGroupActivity$$ViewInjector f3597b;

        ViewInjector(CreateGroupActivity$$ViewInjector createGroupActivity$$ViewInjector, CreateGroupActivity createGroupActivity) {
            this.f3597b = createGroupActivity$$ViewInjector;
            this.f3596a = createGroupActivity;
        }

        public void m6206a(View view) {
            this.f3596a.m6244k();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CreateGroupActivity f3598a;
        final /* synthetic */ CreateGroupActivity$$ViewInjector f3599b;

        ViewInjector(CreateGroupActivity$$ViewInjector createGroupActivity$$ViewInjector, CreateGroupActivity createGroupActivity) {
            this.f3599b = createGroupActivity$$ViewInjector;
            this.f3598a = createGroupActivity;
        }

        public void m6207a(View view) {
            this.f3598a.m6245l();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CreateGroupActivity f3600a;
        final /* synthetic */ CreateGroupActivity$$ViewInjector f3601b;

        ViewInjector(CreateGroupActivity$$ViewInjector createGroupActivity$$ViewInjector, CreateGroupActivity createGroupActivity) {
            this.f3601b = createGroupActivity$$ViewInjector;
            this.f3600a = createGroupActivity;
        }

        public void m6208a(View view) {
            this.f3600a.m6246m();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f3621o = (ViewFlipper) finder.m7731a((View) finder.m7732a(obj, 2131755162, "field 'viewFlipper'"), 2131755162, "field 'viewFlipper'");
        View view = (View) finder.m7732a(obj, 2131755163, "field 'groupAvatar' and method 'onGroupAvatarClicked'");
        t.f3622p = (RoundedImageView) finder.m7731a(view, 2131755163, "field 'groupAvatar'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3623q = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755164, "field 'groupNameEditText'"), 2131755164, "field 'groupNameEditText'");
        t.f3624r = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755166, "field 'descriptionEditText'"), 2131755166, "field 'descriptionEditText'");
        t.f3625s = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755165, "field 'descriptionCounterTextView'"), 2131755165, "field 'descriptionCounterTextView'");
        t.f3626t = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755168, "field 'moderatedImageView'"), 2131755168, "field 'moderatedImageView'");
        t.f3627u = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755170, "field 'semiModeratedImageView'"), 2131755170, "field 'semiModeratedImageView'");
        t.f3628v = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755172, "field 'unmoderatedImageView'"), 2131755172, "field 'unmoderatedImageView'");
        t.f3629w = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755173, "field 'typeDescriptionTextView'"), 2131755173, "field 'typeDescriptionTextView'");
        ((View) finder.m7732a(obj, 2131755167, "method 'onModeratedLayout'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755169, "method 'onSemiModeratedLayout'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755171, "method 'onUnmoderatedLayout'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3621o = null;
        t.f3622p = null;
        t.f3623q = null;
        t.f3624r = null;
        t.f3625s = null;
        t.f3626t = null;
        t.f3627u = null;
        t.f3628v = null;
        t.f3629w = null;
    }
}
