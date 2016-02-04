package app.util;

import android.os.AsyncTask;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import se.emilsjolander.stickylistheaders.C1128R;

public class ThumbnailUtil extends AsyncTask<Void, Void, Void> {
    String f4620a;
    long f4621b;
    int f4622c;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m7067a((Void[]) objArr);
    }

    public ThumbnailUtil(String str, long j, int i) {
        this.f4620a = str;
        this.f4621b = j;
        this.f4622c = i;
    }

    protected Void m7067a(Void... voidArr) {
        String d = FileUtil.m7028d(this.f4620a);
        if (d != null) {
            switch (this.f4622c) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    HistoryNormalMessageDataSource.m4720a().m4736a(this.f4621b, d);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    HistoryGroupDataSource.m4691a().m4709a(this.f4621b, d);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    HistoryChannelDataSource.m4667a().m4683a(this.f4621b, d);
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}
