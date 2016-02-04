package app.messaging.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmilyMaper {
    public static HashMap<String, SmilyMaper> f3545a;
    public static List<SmilyMaper> f3546b;
    private String f3547c;
    private int f3548d;

    public static HashMap<String, SmilyMaper> m6131a() {
        if (f3545a == null || f3546b == null) {
            f3545a = new HashMap();
            f3546b = new ArrayList();
            m6133d();
            m6132c();
        }
        return f3545a;
    }

    public SmilyMaper(String str, int i) {
        this.f3547c = str;
        this.f3548d = i;
    }

    private static void m6132c() {
        f3546b.add(new SmilyMaper("(teeth)", 2130837914));
        f3546b.add(new SmilyMaper("(silly)", 2130837909));
        f3546b.add(new SmilyMaper("(happy)", 2130837895));
        f3546b.add(new SmilyMaper("(mad)", 2130837901));
        f3546b.add(new SmilyMaper("(happyshy)", 2130837899));
        f3546b.add(new SmilyMaper("(ninja)", 2130837903));
        f3546b.add(new SmilyMaper("(depressed)", 2130837892));
        f3546b.add(new SmilyMaper("(not_sure)", 2130837904));
        f3546b.add(new SmilyMaper("(confused)", 2130837889));
        f3546b.add(new SmilyMaper("(sad)", 2130837906));
        f3546b.add(new SmilyMaper("(inlove)", 2130837900));
        f3546b.add(new SmilyMaper("(mwah)", 2130837902));
        f3546b.add(new SmilyMaper("(yumi)", 2130837918));
        f3546b.add(new SmilyMaper("(tongue)", 2130837915));
        f3546b.add(new SmilyMaper("(dizzy)", 2130837893));
        f3546b.add(new SmilyMaper("(wink)", 2130837916));
        f3546b.add(new SmilyMaper("(happy3)", 2130837897));
        f3546b.add(new SmilyMaper("(wink2)", 2130837917));
        f3546b.add(new SmilyMaper("(ohno)", 2130837905));
        f3546b.add(new SmilyMaper("(eek)", 2130837894));
        f3546b.add(new SmilyMaper("(smilely)", 2130837910));
        f3546b.add(new SmilyMaper("(happy2)", 2130837896));
        f3546b.add(new SmilyMaper("(scream)", 2130837908));
        f3546b.add(new SmilyMaper("(happycry)", 2130837898));
        f3546b.add(new SmilyMaper("(teary)", 2130837912));
        f3546b.add(new SmilyMaper("(teary2)", 2130837913));
        f3546b.add(new SmilyMaper("(sadshy)", 2130837907));
        f3546b.add(new SmilyMaper("(cry)", 2130837890));
        f3546b.add(new SmilyMaper("(surprised)", 2130837911));
        f3546b.add(new SmilyMaper("(dead)", 2130837891));
    }

    private static void m6133d() {
        f3545a.put("(teeth)", new SmilyMaper("(teeth)", 2130837914));
        f3545a.put("(silly)", new SmilyMaper("(silly)", 2130837909));
        f3545a.put("(happy)", new SmilyMaper("(happy)", 2130837895));
        f3545a.put("(mad)", new SmilyMaper("(mad)", 2130837901));
        f3545a.put("(happyshy)", new SmilyMaper("(happyshy)", 2130837899));
        f3545a.put("(ninja)", new SmilyMaper("(ninja)", 2130837903));
        f3545a.put("(depressed)", new SmilyMaper("(depressed)", 2130837892));
        f3545a.put("(not_sure)", new SmilyMaper("(not_sure)", 2130837904));
        f3545a.put("(confused)", new SmilyMaper("(confused)", 2130837889));
        f3545a.put("(sad)", new SmilyMaper("(sad)", 2130837906));
        f3545a.put("(inlove)", new SmilyMaper("(inlove)", 2130837900));
        f3545a.put("(mwah)", new SmilyMaper("(mwah)", 2130837902));
        f3545a.put("(yumi)", new SmilyMaper("(yumi)", 2130837918));
        f3545a.put("(tongue)", new SmilyMaper("(tongue)", 2130837915));
        f3545a.put("(dizzy)", new SmilyMaper("(dizzy)", 2130837893));
        f3545a.put("(wink)", new SmilyMaper("(wink)", 2130837916));
        f3545a.put("(happy3)", new SmilyMaper("(happy3)", 2130837897));
        f3545a.put("(wink2)", new SmilyMaper("(wink2)", 2130837917));
        f3545a.put("(ohno)", new SmilyMaper("(ohno)", 2130837905));
        f3545a.put("(eek)", new SmilyMaper("(eek)", 2130837894));
        f3545a.put("(smilely)", new SmilyMaper("(smilely)", 2130837910));
        f3545a.put("(happy2)", new SmilyMaper("(happy2)", 2130837896));
        f3545a.put("(scream)", new SmilyMaper("(scream)", 2130837908));
        f3545a.put("(happycry)", new SmilyMaper("(happycry)", 2130837898));
        f3545a.put("(teary)", new SmilyMaper("(teary)", 2130837912));
        f3545a.put("(teary2)", new SmilyMaper("(teary2)", 2130837913));
        f3545a.put("(sadshy)", new SmilyMaper("(sadshy)", 2130837907));
        f3545a.put("(cry)", new SmilyMaper("(cry)", 2130837890));
        f3545a.put("(surprised)", new SmilyMaper("(surprised)", 2130837911));
        f3545a.put("(dead)", new SmilyMaper("(dead)", 2130837891));
        f3545a.put("(shy)", new SmilyMaper("(shy)", 2130837899));
        f3545a.put("(crazy)", new SmilyMaper("(crazy)", 2130837905));
        f3545a.put("(moa)", new SmilyMaper("(moa)", 2130837902));
        f3545a.put(":)", new SmilyMaper(":)", 2130837895));
        f3545a.put(":-)", new SmilyMaper(":-)", 2130837895));
        f3545a.put(":D", new SmilyMaper(":D", 2130837914));
        f3545a.put(":d", new SmilyMaper(":d", 2130837914));
        f3545a.put(":-D", new SmilyMaper(":-D", 2130837914));
        f3545a.put(":-d", new SmilyMaper(":-d", 2130837914));
        f3545a.put(";)", new SmilyMaper(";)", 2130837917));
        f3545a.put(";-)", new SmilyMaper(";-)", 2130837917));
        f3545a.put(":*", new SmilyMaper(":*", 2130837902));
        f3545a.put(":-*", new SmilyMaper(":-*", 2130837902));
        f3545a.put(":O", new SmilyMaper(":O", 2130837911));
        f3545a.put(":-O", new SmilyMaper(":-O", 2130837911));
        f3545a.put(":o", new SmilyMaper(":o", 2130837911));
        f3545a.put(":-o", new SmilyMaper(":-o", 2130837911));
        f3545a.put(":-p", new SmilyMaper(":-p", 2130837915));
        f3545a.put(":-P", new SmilyMaper(":-P", 2130837915));
        f3545a.put(":p", new SmilyMaper(":p", 2130837915));
        f3545a.put(":P", new SmilyMaper(":P", 2130837915));
        f3545a.put(":-V", new SmilyMaper(":-V", 2130837904));
        f3545a.put(":-v", new SmilyMaper(":-v", 2130837904));
        f3545a.put(":v", new SmilyMaper(":v", 2130837904));
        f3545a.put(":V", new SmilyMaper(":V", 2130837904));
        f3545a.put("^_^", new SmilyMaper("^_^", 2130837910));
        f3545a.put(":(", new SmilyMaper(":(", 2130837906));
        f3545a.put(":-(", new SmilyMaper(":-(", 2130837906));
        f3545a.put(":x", new SmilyMaper(":x", 2130837900));
        f3545a.put(":-x", new SmilyMaper(":-x", 2130837900));
        f3545a.put(":x", new SmilyMaper(":x", 2130837900));
        f3545a.put(":X", new SmilyMaper(":X", 2130837900));
    }

    public Drawable m6134a(Context context) {
        return context.getResources().getDrawable(this.f3548d);
    }

    public String m6135b() {
        return this.f3547c;
    }
}
