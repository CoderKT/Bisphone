package app.messaging.selector;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import app.Main;
import app.database.provider.ConversationGroupProvider;
import app.messaging.GroupMessagingActivity;
import app.messaging.selector.SelectRecipientActivity.ForwardHistoryType;

public class SelectGroupFragment extends Fragment implements LoaderCallbacks<Cursor> {
    ListView f3869a;
    View f3870b;
    String f3871c;
    private SelectGroupAdapter f3872d;

    /* renamed from: app.messaging.selector.SelectGroupFragment.1 */
    class C03601 implements OnItemClickListener {
        final /* synthetic */ SelectGroupFragment f3868a;

        C03601(SelectGroupFragment selectGroupFragment) {
            this.f3868a = selectGroupFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            Intent intent = new Intent(this.f3868a.getActivity(), GroupMessagingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("extra_group_jid", viewHolder.f3858c);
            bundle.putString("forward_recipients_id", viewHolder.f3858c);
            if (this.f3868a.getArguments() != null) {
                if (this.f3868a.getArguments().containsKey("share_text_extra")) {
                    bundle.putString("share_text_extra", this.f3868a.f3871c);
                } else if (this.f3868a.getArguments().containsKey("extra_is_forward_mode")) {
                    bundle.putInt("multiple_forward_message_type", ForwardHistoryType.group.ordinal());
                    Intent intent2 = this.f3868a.getActivity().getIntent();
                    intent2.putExtra("multiple_forward_message", bundle);
                    this.f3868a.getActivity().setResult(-1, intent2);
                    this.f3868a.getActivity().finish();
                    return;
                } else if (this.f3868a.getArguments().containsKey("share_image_extra")) {
                    bundle.putString("share_image_extra", this.f3868a.getArguments().getString("share_image_extra"));
                } else if (this.f3868a.getArguments().containsKey("share_video_extra")) {
                    bundle.putString("share_video_extra", this.f3868a.getArguments().getString("share_video_extra"));
                }
            }
            intent.putExtras(bundle);
            intent.setFlags(67108864);
            this.f3868a.startActivity(intent);
            this.f3868a.getActivity().finish();
        }
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m6412a(loader, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903148, viewGroup, false);
        this.f3872d = new SelectGroupAdapter(getActivity(), null);
        return inflate;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f3869a = (ListView) view.findViewById(2131755421);
        this.f3870b = view.findViewById(2131755422);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        try {
            if (getArguments() != null) {
                this.f3871c = getArguments().getString("share_text_extra");
            }
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
        this.f3869a.setOnItemClickListener(new C03601(this));
        this.f3869a.setAdapter(this.f3872d);
        getActivity().getLoaderManager().initLoader(165421328, null, this);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i != 165421328) {
            return null;
        }
        return new CursorLoader(getActivity(), ConversationGroupProvider.f2368a, null, "group_state='JOINED'", null, "message_time_stamp DESC");
    }

    public void m6412a(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            this.f3870b.setVisibility(4);
        }
        this.f3872d.changeCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.f3872d.changeCursor(null);
    }
}
