package app.messaging.stickers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class NoInternetConnection extends Fragment {
    NoInternetConnectionComminucator f3900a;

    /* renamed from: app.messaging.stickers.NoInternetConnection.1 */
    class C03631 implements OnClickListener {
        final /* synthetic */ NoInternetConnection f3899a;

        C03631(NoInternetConnection noInternetConnection) {
            this.f3899a = noInternetConnection;
        }

        public void onClick(View view) {
            this.f3899a.f3900a.m6430a();
        }
    }

    public interface NoInternetConnectionComminucator {
        void m6430a();
    }

    public void m6431a(NoInternetConnectionComminucator noInternetConnectionComminucator) {
        this.f3900a = noInternetConnectionComminucator;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903183, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(2131755494).setOnClickListener(new C03631(this));
    }
}
