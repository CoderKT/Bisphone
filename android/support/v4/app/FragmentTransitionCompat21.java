package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class FragmentTransitionCompat21 {

    public interface ViewRetriever {
        View m107a();
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.1 */
    final class C00081 extends EpicenterCallback {
        final /* synthetic */ Rect f226a;

        C00081(Rect rect) {
            this.f226a = rect;
        }

        public Rect onGetEpicenter(Transition transition) {
            return this.f226a;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.2 */
    final class C00092 implements OnPreDrawListener {
        final /* synthetic */ View f227a;
        final /* synthetic */ Transition f228b;
        final /* synthetic */ View f229c;
        final /* synthetic */ ViewRetriever f230d;
        final /* synthetic */ Map f231e;
        final /* synthetic */ Map f232f;
        final /* synthetic */ ArrayList f233g;

        C00092(View view, Transition transition, View view2, ViewRetriever viewRetriever, Map map, Map map2, ArrayList arrayList) {
            this.f227a = view;
            this.f228b = transition;
            this.f229c = view2;
            this.f230d = viewRetriever;
            this.f231e = map;
            this.f232f = map2;
            this.f233g = arrayList;
        }

        public boolean onPreDraw() {
            this.f227a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f228b != null) {
                this.f228b.removeTarget(this.f229c);
            }
            View a = this.f230d.m107a();
            if (a != null) {
                if (!this.f231e.isEmpty()) {
                    FragmentTransitionCompat21.m471a(this.f232f, a);
                    this.f232f.keySet().retainAll(this.f231e.values());
                    for (Entry entry : this.f231e.entrySet()) {
                        View view = (View) this.f232f.get((String) entry.getValue());
                        if (view != null) {
                            view.setTransitionName((String) entry.getKey());
                        }
                    }
                }
                if (this.f228b != null) {
                    FragmentTransitionCompat21.m478b(this.f233g, a);
                    this.f233g.removeAll(this.f232f.values());
                    this.f233g.add(this.f229c);
                    FragmentTransitionCompat21.m477b(this.f228b, this.f233g);
                }
            }
            return true;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.3 */
    final class C00103 extends EpicenterCallback {
        final /* synthetic */ EpicenterView f234a;
        private Rect f235b;

        C00103(EpicenterView epicenterView) {
            this.f234a = epicenterView;
        }

        public Rect onGetEpicenter(Transition transition) {
            if (this.f235b == null && this.f234a.f247a != null) {
                this.f235b = FragmentTransitionCompat21.m479c(this.f234a.f247a);
            }
            return this.f235b;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.4 */
    final class C00114 implements OnPreDrawListener {
        final /* synthetic */ View f236a;
        final /* synthetic */ Transition f237b;
        final /* synthetic */ ArrayList f238c;
        final /* synthetic */ Transition f239d;
        final /* synthetic */ ArrayList f240e;
        final /* synthetic */ Transition f241f;
        final /* synthetic */ ArrayList f242g;
        final /* synthetic */ Map f243h;
        final /* synthetic */ ArrayList f244i;
        final /* synthetic */ Transition f245j;
        final /* synthetic */ View f246k;

        C00114(View view, Transition transition, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4, View view2) {
            this.f236a = view;
            this.f237b = transition;
            this.f238c = arrayList;
            this.f239d = transition2;
            this.f240e = arrayList2;
            this.f241f = transition3;
            this.f242g = arrayList3;
            this.f243h = map;
            this.f244i = arrayList4;
            this.f245j = transition4;
            this.f246k = view2;
        }

        public boolean onPreDraw() {
            this.f236a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f237b != null) {
                FragmentTransitionCompat21.m468a(this.f237b, this.f238c);
            }
            if (this.f239d != null) {
                FragmentTransitionCompat21.m468a(this.f239d, this.f240e);
            }
            if (this.f241f != null) {
                FragmentTransitionCompat21.m468a(this.f241f, this.f242g);
            }
            for (Entry entry : this.f243h.entrySet()) {
                ((View) entry.getValue()).setTransitionName((String) entry.getKey());
            }
            int size = this.f244i.size();
            for (int i = 0; i < size; i++) {
                this.f245j.excludeTarget((View) this.f244i.get(i), false);
            }
            this.f245j.excludeTarget(this.f246k, false);
            return true;
        }
    }

    public class EpicenterView {
        public View f247a;
    }

    public static String m460a(View view) {
        return view.getTransitionName();
    }

    public static Object m457a(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return obj;
    }

    public static Object m458a(Object obj, View view, ArrayList<View> arrayList, Map<String, View> map, View view2) {
        if (obj == null) {
            return obj;
        }
        m478b((ArrayList) arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.add(view2);
        m477b((Transition) obj, (ArrayList) arrayList);
        return obj;
    }

    public static void m466a(Object obj, View view, boolean z) {
        ((Transition) obj).excludeTarget(view, z);
    }

    public static void m463a(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    public static void m464a(Object obj, View view) {
        ((Transition) obj).setEpicenterCallback(new C00081(m479c(view)));
    }

    public static Object m476b(Object obj) {
        if (obj == null) {
            return null;
        }
        Transition transition = (Transition) obj;
        if (transition == null) {
            return null;
        }
        Object transitionSet = new TransitionSet();
        transitionSet.addTransition(transition);
        return transitionSet;
    }

    public static void m467a(Object obj, Object obj2, View view, ViewRetriever viewRetriever, View view2, EpicenterView epicenterView, Map<String, String> map, ArrayList<View> arrayList, Map<String, View> map2, Map<String, View> map3, ArrayList<View> arrayList2) {
        if (obj != null || obj2 != null) {
            Transition transition = (Transition) obj;
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (obj2 != null) {
                m465a(obj2, view2, (Map) map2, (ArrayList) arrayList2);
            }
            if (viewRetriever != null) {
                view.getViewTreeObserver().addOnPreDrawListener(new C00092(view, transition, view2, viewRetriever, map, map3, arrayList));
            }
            m461a(transition, epicenterView);
        }
    }

    public static Object m459a(Object obj, Object obj2, Object obj3, boolean z) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition == null || transition2 == null) {
            z = true;
        }
        Object transitionSet;
        if (z) {
            transitionSet = new TransitionSet();
            if (transition != null) {
                transitionSet.addTransition(transition);
            }
            if (transition2 != null) {
                transitionSet.addTransition(transition2);
            }
            if (transition3 == null) {
                return transitionSet;
            }
            transitionSet.addTransition(transition3);
            return transitionSet;
        }
        Transition transition4 = null;
        if (transition2 != null && transition != null) {
            transition4 = new TransitionSet().addTransition(transition2).addTransition(transition).setOrdering(1);
        } else if (transition2 != null) {
            transition4 = transition2;
        } else if (transition != null) {
            transition4 = transition;
        }
        if (transition3 == null) {
            return transition4;
        }
        transitionSet = new TransitionSet();
        if (transition4 != null) {
            transitionSet.addTransition(transition4);
        }
        transitionSet.addTransition(transition3);
        return transitionSet;
    }

    public static void m465a(Object obj, View view, Map<String, View> map, ArrayList<View> arrayList) {
        obj = (TransitionSet) obj;
        arrayList.clear();
        arrayList.addAll(map.values());
        List targets = obj.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            m470a(targets, (View) arrayList.get(i));
        }
        arrayList.add(view);
        m477b(obj, (ArrayList) arrayList);
    }

    private static void m470a(List<View> list, View view) {
        int size = list.size();
        if (!m474a((List) list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!m474a((List) list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean m474a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    private static void m461a(Transition transition, EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback(new C00103(epicenterView));
        }
    }

    private static Rect m479c(View view) {
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        return rect;
    }

    private static void m478b(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                m478b((ArrayList) arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    public static void m471a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    m471a((Map) map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static void m462a(View view, View view2, Object obj, ArrayList<View> arrayList, Object obj2, ArrayList<View> arrayList2, Object obj3, ArrayList<View> arrayList3, Object obj4, ArrayList<View> arrayList4, Map<String, View> map) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        Transition transition4 = (Transition) obj4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new C00114(view, transition, arrayList, transition2, arrayList2, transition3, arrayList3, map, arrayList4, transition4, view2));
        }
    }

    public static void m468a(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        int transitionCount;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            transitionCount = transitionSet.getTransitionCount();
            for (int i = 0; i < transitionCount; i++) {
                m468a(transitionSet.getTransitionAt(i), (ArrayList) arrayList);
            }
        } else if (!m472a(transition)) {
            List targets = transition.getTargets();
            if (targets != null && targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
                for (transitionCount = arrayList.size() - 1; transitionCount >= 0; transitionCount--) {
                    transition.removeTarget((View) arrayList.get(transitionCount));
                }
            }
        }
    }

    public static void m477b(Object obj, ArrayList<View> arrayList) {
        int i = 0;
        Transition transition = (Transition) obj;
        int transitionCount;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                m477b(transitionSet.getTransitionAt(i), (ArrayList) arrayList);
                i++;
            }
        } else if (!m472a(transition) && m473a(transition.getTargets())) {
            int size = arrayList.size();
            for (transitionCount = 0; transitionCount < size; transitionCount++) {
                transition.addTarget((View) arrayList.get(transitionCount));
            }
        }
    }

    private static boolean m472a(Transition transition) {
        return (m473a(transition.getTargetIds()) && m473a(transition.getTargetNames()) && m473a(transition.getTargetTypes())) ? false : true;
    }

    private static boolean m473a(List list) {
        return list == null || list.isEmpty();
    }
}
