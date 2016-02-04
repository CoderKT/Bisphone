package app.messaging.emoji;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import app.util.PixelConverter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiHandlerCode {
    public static EmojiHandlerCode f3542a;
    public int f3543b;
    public int f3544c;

    public class KeyDetail {
        final /* synthetic */ EmojiHandlerCode f3538a;
        private int f3539b;
        private int f3540c;
        private String f3541d;

        public KeyDetail(EmojiHandlerCode emojiHandlerCode, int i, int i2, String str) {
            this.f3538a = emojiHandlerCode;
            this.f3539b = i;
            this.f3540c = i2;
            this.f3541d = str;
        }
    }

    public EmojiHandlerCode() {
        this.f3543b = 25;
        this.f3544c = 15;
    }

    public static EmojiHandlerCode m6125a() {
        if (f3542a == null) {
            f3542a = new EmojiHandlerCode();
        }
        return f3542a;
    }

    public SpannableStringBuilder m6128a(SmilyMaper smilyMaper, int i, Context context) {
        Rect rect = new Rect(0, (-((int) PixelConverter.m7048a((float) i, context))) / 4, (int) PixelConverter.m7048a((float) i, context), (((int) PixelConverter.m7048a((float) i, context)) / 4) * 3);
        Drawable a = smilyMaper.m6134a(context);
        a.setBounds(rect);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(smilyMaper.m6135b());
        spannableStringBuilder.setSpan(new ImageSpan(a, 0), spannableStringBuilder.length() - smilyMaper.m6135b().length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public void m6130a(String str, View view, int i, Context context) {
        if (str != null) {
            CharSequence a = m6127a(context, i, str);
            if (view instanceof TextView) {
                ((TextView) view).setText(a);
            } else if (view instanceof EditText) {
                ((EditText) view).setText(a);
            }
        }
    }

    public ArrayList<KeyDetail> m6129a(String str) {
        ArrayList<KeyDetail> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(m6126b()).matcher(str);
        while (matcher.find()) {
            arrayList.add(new KeyDetail(this, matcher.start(), matcher.end(), matcher.group()));
        }
        return arrayList;
    }

    private String m6126b() {
        String str = "";
        for (Entry key : SmilyMaper.m6131a().entrySet()) {
            str = str + ((String) key.getKey()) + "|";
        }
        String replace = str.replace("(", "\\(").replace(")", "\\)").replace("*", "\\*").replace("^", "\\^");
        return replace.substring(0, replace.length() - 1);
    }

    public SpannableStringBuilder m6127a(Context context, int i, String str) {
        ArrayList a = m6129a(str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        for (int size = a.size() - 1; size >= 0; size--) {
            Rect rect = new Rect(0, (-((int) PixelConverter.m7048a((float) i, context))) / 4, (int) PixelConverter.m7048a((float) i, context), (((int) PixelConverter.m7048a((float) i, context)) / 4) * 3);
            Drawable a2 = ((SmilyMaper) SmilyMaper.m6131a().get(((KeyDetail) a.get(size)).f3541d)).m6134a(context);
            a2.setBounds(rect);
            spannableStringBuilder.setSpan(new ImageSpan(a2, 0), ((KeyDetail) a.get(size)).f3539b, ((KeyDetail) a.get(size)).f3540c, 33);
        }
        return spannableStringBuilder;
    }
}
