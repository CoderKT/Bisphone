package app.messaging.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import app.Main;
import app.common.entity.StickerPackEntity;
import app.messaging.stickers.StickerLayoutParser;
import app.messaging.stickers.StickerManager;
import java.io.Reader;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class StickerFragment extends Fragment {
    private View f3576a;
    private ScrollView f3577b;
    private ProgressBar f3578c;
    private StickerPareser f3579d;
    private int f3580e;

    class StickerPareser extends AsyncTask<Void, Void, ViewGroup> {
        StickerPackEntity f3574a;
        final /* synthetic */ StickerFragment f3575b;

        private StickerPareser(StickerFragment stickerFragment) {
            this.f3575b = stickerFragment;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6173a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6174a((ViewGroup) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f3575b.f3578c.setVisibility(0);
            this.f3574a = (StickerPackEntity) StickerManager.m6491b().m6499d().get(this.f3575b.f3580e);
        }

        protected ViewGroup m6173a(Void... voidArr) {
            ViewGroup viewGroup = null;
            StickerManager.m6491b().m6493a(this.f3575b.f3580e);
            Reader stringReader = new StringReader(this.f3574a.m4488c());
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(stringReader);
                viewGroup = new StickerLayoutParser(this.f3575b.m227i()).m6478a(newPullParser);
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return viewGroup;
        }

        protected void m6174a(ViewGroup viewGroup) {
            super.onPostExecute(viewGroup);
            this.f3575b.f3578c.setVisibility(4);
            if (viewGroup != null) {
                this.f3575b.m6177a(this.f3575b.f3577b);
                this.f3575b.f3577b.removeAllViewsInLayout();
                this.f3575b.f3577b.addView(viewGroup);
            }
        }
    }

    public static StickerFragment m6180b(int i) {
        StickerFragment stickerFragment = new StickerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("packID", i);
        stickerFragment.m224g(bundle);
        return stickerFragment;
    }

    public void m6183a(Bundle bundle) {
        super.m195a(bundle);
        this.f3580e = m223g().getInt("packID", 100);
        this.f3579d = new StickerPareser();
    }

    public View m6182a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3576a = layoutInflater.inflate(2130903150, viewGroup, false);
        this.f3577b = (ScrollView) this.f3576a.findViewById(2131755428);
        this.f3578c = (ProgressBar) this.f3576a.findViewById(2131755429);
        m6176a();
        return this.f3576a;
    }

    public void m6184b() {
        super.m204b();
        m6177a(this.f3577b);
    }

    public void m6186e(boolean z) {
        super.m219e(z);
        if (m237p() == null || !z) {
            Main.f1926a.m5677a("fragmetn unvisible " + this.f3580e);
            m6177a(this.f3577b);
            return;
        }
        Main.f1926a.m5677a("fragmetn visible " + this.f3580e);
        m6176a();
    }

    private void m6176a() {
        if (!this.f3579d.getStatus().equals(Status.RUNNING)) {
            this.f3579d = new StickerPareser();
            this.f3579d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void m6177a(View view) {
        if ((view instanceof ImageView) && (((ImageView) view).getDrawable() instanceof BitmapDrawable)) {
            ((ImageView) view).setImageDrawable(null);
            this.f3577b.removeView(view);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                m6177a(((ViewGroup) view).getChildAt(i));
            }
        }
    }

    public void m6185e(Bundle bundle) {
    }
}
