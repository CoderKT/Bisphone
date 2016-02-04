package app.home;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import app.Main;
import app.database.datasource.HistoryChannelDataSource;
import app.database.provider.ChannelProvider;
import app.events.HomeTabsBadgeUpdateEvent;
import app.messaging.channel.ChannelCategoryActivity;
import app.profile.MoreInfoActivity;
import butterknife.ButterKnife;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import de.greenrobot.event.EventBus;

public class ChannelFragment extends Fragment implements LoaderCallbacks<Cursor> {
    ListView f2844a;
    ViewGroup f2845b;
    ProgressBar f2846c;
    MenuItem f2847d;
    View f2848e;
    private ChannelAdapter f2849f;

    /* renamed from: app.home.ChannelFragment.1 */
    class C01981 implements OnClickListener {
        final /* synthetic */ ChannelFragment f2841a;

        C01981(ChannelFragment channelFragment) {
            this.f2841a = channelFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.home.ChannelFragment.2 */
    class C01992 implements OnClickListener {
        final /* synthetic */ ChannelFragment f2842a;

        C01992(ChannelFragment channelFragment) {
            this.f2842a = channelFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            new markAllMessageAsRead(this.f2842a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    class markAllMessageAsRead extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ChannelFragment f2843a;

        markAllMessageAsRead(ChannelFragment channelFragment) {
            this.f2843a = channelFragment;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5284a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5285a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void m5284a(Void... voidArr) {
            if (this.f2843a.f2849f.getCursor() != null) {
                HistoryChannelDataSource.m4677c(this.f2843a.getActivity());
            }
            return null;
        }

        protected void m5285a(Void voidR) {
            super.onPostExecute(voidR);
        }
    }

    public ChannelFragment() {
        this.f2847d = null;
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m5290a(loader, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903130, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        this.f2848e = getActivity().getLayoutInflater().inflate(2130903125, this.f2845b, false);
        ((ViewGroup) this.f2844a.getParent()).addView(this.f2848e);
        m5288a(8);
        m5289b(0);
        this.f2849f = new ChannelAdapter(getActivity(), null);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        this.f2844a.setOnScrollListener(new PauseOnScrollListener(ImageLoader.m11076a(), false, true));
        this.f2844a.setAdapter(this.f2849f);
        getActivity().getLoaderManager().initLoader(8800, null, this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820544, menu);
        this.f2847d = menu.findItem(2131755628);
        if (this.f2849f.getCursor() == null || this.f2849f.getCursor().getCount() != 0) {
            this.f2847d.setVisible(true);
        } else {
            this.f2847d.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755627:
                startActivity(new Intent(Main.f1927b, ChannelCategoryActivity.class));
                break;
            case 2131755628:
                m5287a();
                break;
            case 2131755629:
                startActivity(new Intent(Main.f1927b, MoreInfoActivity.class));
                break;
        }
        return true;
    }

    private void m5287a() {
        new Builder(getActivity(), 2131558537).m1980a(getString(2131296558)).m1986b(getString(2131296557)).m1975a(17039379, new C01992(this)).m1985b(17039369, new C01981(this)).m1989c(2130837730).m1992c();
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i != 8800) {
            return null;
        }
        return new CursorLoader(getActivity(), ChannelProvider.f2360a, null, null, null, "timestamp DESC");
    }

    public void m5290a(Loader<Cursor> loader, Cursor cursor) {
        int i = 1;
        m5289b(8);
        if (cursor != null) {
            if (this.f2847d != null) {
                this.f2847d.setVisible(cursor.getCount() != 0);
            }
            if (cursor.isClosed()) {
                getLoaderManager().restartLoader(8800, null, this);
            } else if (loader.getId() == 8800) {
                int i2;
                if (cursor.moveToFirst()) {
                    do {
                        i2 = cursor.getInt(cursor.getColumnIndexOrThrow("unread_count")) > 0 ? 1 : 0;
                        if (i2 != 0) {
                            break;
                        }
                    } while (cursor.moveToNext());
                } else {
                    i2 = 0;
                }
                EventBus a = EventBus.m12779a();
                if (i2 == 0) {
                    i = 0;
                }
                a.m12795d(new HomeTabsBadgeUpdateEvent(3, i));
                this.f2849f.swapCursor(cursor);
            }
        }
        this.f2844a.setEmptyView(this.f2848e);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        m5288a(8);
        m5289b(0);
        if (loader.getId() == 8800 && this.f2849f != null) {
            this.f2849f.changeCursor(null);
        }
    }

    private void m5288a(int i) {
        if (this.f2848e != null) {
            this.f2848e.setVisibility(i);
        }
    }

    private void m5289b(int i) {
        if (this.f2846c != null) {
            this.f2846c.setVisibility(i);
        }
    }
}
