package app.signin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;

public class IntroductionFragment extends Fragment {
    Button f4455a;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903138, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        return inflate;
    }

    void m6847a() {
        getFragmentManager().beginTransaction().replace(2131755221, new SignInFragment()).commit();
    }

    public void onSaveInstanceState(Bundle bundle) {
    }
}
