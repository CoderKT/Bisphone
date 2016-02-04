package app.voip;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import app.Main;
import app.database.datasource.ContactDataSource;
import app.signin.CountrySelectActivity;
import app.util.CallUtil;
import app.util.ClipboardUtil;
import app.util.CountryMapperUtil;
import app.util.DialogsUtil;
import app.util.SharedPreferencesUtil;
import app.xmpp.NetworkConnectivityChangeReceiver;
import butterknife.ButterKnife;
import org.jivesoftware.smack.packet.Message;
import se.emilsjolander.stickylistheaders.C1128R;

public class ContactKeyPadFragment extends Fragment {
    EditText f4769a;
    EditText f4770b;
    View f4771c;
    TextView f4772d;
    View f4773e;
    ListView f4774f;
    View f4775g;
    boolean f4776h;

    /* renamed from: app.voip.ContactKeyPadFragment.1 */
    class C05141 implements OnTouchListener {
        final /* synthetic */ ContactKeyPadFragment f4763a;

        C05141(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4763a = contactKeyPadFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean onTouchEvent = view.onTouchEvent(motionEvent);
            switch (motionEvent.getAction()) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    Layout layout = ((EditText) view).getLayout();
                    float x = motionEvent.getX() + ((float) this.f4763a.f4769a.getScrollX());
                    int offsetForHorizontal = layout.getOffsetForHorizontal(0, x);
                    if (offsetForHorizontal > 0) {
                        if (x > layout.getLineMax(0)) {
                            this.f4763a.f4769a.setSelection(offsetForHorizontal);
                        } else {
                            this.f4763a.f4769a.setSelection(offsetForHorizontal - 1);
                        }
                    }
                    return true;
                default:
                    return onTouchEvent;
            }
        }
    }

    /* renamed from: app.voip.ContactKeyPadFragment.2 */
    class C05152 implements TextWatcher {
        final /* synthetic */ ContactKeyPadFragment f4764a;

        C05152(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4764a = contactKeyPadFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence == null || charSequence.toString().length() == 0) {
                this.f4764a.f4771c.setVisibility(4);
            } else {
                this.f4764a.m7167a(charSequence.toString());
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.voip.ContactKeyPadFragment.3 */
    class C05163 implements AnimatorListener {
        final /* synthetic */ ContactKeyPadFragment f4765a;

        C05163(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4765a = contactKeyPadFragment;
        }

        public void onAnimationStart(Animator animator) {
            this.f4765a.f4774f.setVisibility(8);
            this.f4765a.f4775g.setVisibility(8);
        }

        public void onAnimationEnd(Animator animator) {
            this.f4765a.m7167a(this.f4765a.f4769a.getText().toString());
            int e = ContactDataSource.m4553a().m4577e(this.f4765a.f4770b.getText().toString().substring(1, this.f4765a.f4770b.getText().toString().length()) + this.f4765a.f4769a.getText().toString());
            if (e > 0) {
                this.f4765a.f4771c.setVisibility(0);
                this.f4765a.f4772d.setText(e + " matches");
                return;
            }
            this.f4765a.f4771c.setVisibility(4);
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    /* renamed from: app.voip.ContactKeyPadFragment.4 */
    class C05174 implements AnimatorListener {
        final /* synthetic */ ContactKeyPadFragment f4766a;

        C05174(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4766a = contactKeyPadFragment;
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            this.f4766a.f4771c.setVisibility(8);
            this.f4766a.f4775g.setVisibility(0);
            this.f4766a.f4774f.setVisibility(0);
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    /* renamed from: app.voip.ContactKeyPadFragment.5 */
    class C05185 implements OnMenuItemClickListener {
        final /* synthetic */ ContactKeyPadFragment f4767a;

        C05185(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4767a = contactKeyPadFragment;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getTitle().toString().equals(this.f4767a.getResources().getString(2131296404))) {
                ClipboardUtil.m7007a(this.f4767a.getActivity(), this.f4767a.f4769a.getText().toString());
            } else {
                CharSequence a = ClipboardUtil.m7006a(this.f4767a.getActivity());
                if (a != null) {
                    this.f4767a.f4769a.getText().insert(this.f4767a.f4769a.getSelectionStart(), a);
                }
            }
            return true;
        }
    }

    /* renamed from: app.voip.ContactKeyPadFragment.6 */
    class C05196 implements OnItemClickListener {
        final /* synthetic */ ContactKeyPadFragment f4768a;

        C05196(ContactKeyPadFragment contactKeyPadFragment) {
            this.f4768a = contactKeyPadFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            this.f4768a.f4769a.setText(viewHolder.f4780d.substring(this.f4768a.f4770b.getText().toString().length() - 1, viewHolder.f4780d.length()));
            this.f4768a.f4769a.setSelection(this.f4768a.f4769a.getText().toString().length());
            this.f4768a.m7187q();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903133, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.m7744a(this, view);
        this.f4769a.setOnTouchListener(new C05141(this));
        this.f4769a.setTextIsSelectable(true);
        this.f4769a.addTextChangedListener(new C05152(this));
        CharSequence a = SharedPreferencesUtil.m7052a(getResources().getString(2131296745));
        if (a == null) {
            a = "";
        }
        EditText editText = this.f4770b;
        if (a.length() <= 0) {
            a = "+98";
        }
        editText.setText(a);
    }

    private void m7167a(String str) {
        if (str.length() == 0) {
            this.f4771c.setVisibility(4);
            return;
        }
        int e = ContactDataSource.m4553a().m4577e(this.f4770b.getText().toString().substring(1, this.f4770b.getText().toString().length()) + str);
        if (str.trim().length() == 0) {
            e = 0;
        }
        if (e > 0) {
            this.f4771c.setVisibility(0);
            this.f4772d.setText(e + " Matches");
            return;
        }
        this.f4771c.setVisibility(4);
    }

    public void m7171a() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "0");
    }

    public void m7172b() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "1");
    }

    public void m7173c() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "2");
    }

    public void m7174d() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "3");
    }

    public void m7175e() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "4");
    }

    public void m7176f() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "5");
    }

    public void m7177g() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "6");
    }

    public void m7178h() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "7");
    }

    public void m7179i() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "8");
    }

    public void m7180j() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "9");
    }

    public void m7181k() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "*");
    }

    public void m7182l() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "#");
    }

    public void m7183m() {
        Intent intent = new Intent(getActivity(), CountrySelectActivity.class);
        intent.putExtra("country", this.f4770b.getText().toString());
        startActivityForResult(intent, 9562);
    }

    public void m7184n() {
        Intent intent = new Intent(getActivity(), CountrySelectActivity.class);
        intent.putExtra("country", this.f4770b.getText().toString());
        startActivityForResult(intent, 9562);
    }

    public void m7185o() {
        getActivity().finish();
    }

    public void m7186p() {
        int i = 0;
        if (this.f4769a.getText().toString().trim().length() == 0 && this.f4776h) {
            m7187q();
        } else if (this.f4769a.getText().toString().trim().length() != 0) {
            int selectionStart = this.f4769a.getSelectionStart();
            this.f4769a.setText(this.f4769a.getText().toString().substring(0, this.f4769a.getSelectionStart() > 0 ? this.f4769a.getSelectionStart() - 1 : 0) + this.f4769a.getText().toString().substring(this.f4769a.getSelectionStart(), this.f4769a.getText().toString().length()));
            EditText editText = this.f4769a;
            if (selectionStart > 0) {
                i = selectionStart - 1;
            }
            editText.setSelection(i);
            if (this.f4776h) {
                m7170x();
            }
        }
    }

    public void m7187q() {
        this.f4776h = false;
        this.f4773e.setVisibility(0);
        this.f4773e.animate().translationY(0.0f).setListener(new C05163(this));
    }

    public void m7188r() {
        if (this.f4769a.getText().toString().length() != 0) {
            String obj = this.f4769a.getText().toString();
            while (obj.startsWith("0")) {
                obj = obj.substring(1);
            }
            if (obj.length() == 0) {
                return;
            }
            if (NetworkConnectivityChangeReceiver.m7394a(getActivity())) {
                CallUtil.m7005a(this.f4770b.getText().toString().substring(1, this.f4770b.getText().toString().length()) + obj, getActivity(), true);
            } else {
                DialogsUtil.m7014a(getActivity());
            }
        }
    }

    public boolean m7189s() {
        m7169w();
        return true;
    }

    public boolean m7190t() {
        this.f4769a.getText().insert(this.f4769a.getSelectionStart(), "+");
        return true;
    }

    public boolean m7191u() {
        this.f4769a.setText("");
        if (this.f4776h) {
            m7187q();
        }
        return true;
    }

    public void m7192v() {
        this.f4776h = true;
        this.f4773e.setVisibility(0);
        m7170x();
        this.f4773e.animate().translationY((float) this.f4773e.getHeight()).setListener(new C05174(this));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        getActivity();
        if (i2 == -1 && i == 9562 && intent.hasExtra(Message.ELEMENT)) {
            m7168b(intent.getStringExtra(Message.ELEMENT));
            if (this.f4776h) {
                m7170x();
            } else {
                m7167a(this.f4769a.getText().toString());
            }
        }
    }

    private void m7168b(String str) {
        this.f4770b.setText("+" + CountryMapperUtil.m7009b(Main.f1927b, str));
    }

    private void m7169w() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), this.f4769a);
        Menu menu = popupMenu.getMenu();
        menu.add(getResources().getString(2131296404));
        menu.add(getResources().getString(2131296639));
        popupMenu.getMenuInflater().inflate(2131820582, menu);
        popupMenu.setOnMenuItemClickListener(new C05185(this));
        popupMenu.show();
    }

    private void m7170x() {
        Cursor f = ContactDataSource.m4553a().m4579f(this.f4770b.getText().toString().substring(1, this.f4770b.getText().toString().length()) + this.f4769a.getText().toString());
        this.f4774f.setAdapter(null);
        this.f4774f.setAdapter(new ContactKeyPadMatchAdapter(getActivity(), f));
        this.f4774f.setOnItemClickListener(new C05196(this));
    }
}
