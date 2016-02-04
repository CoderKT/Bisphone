package butterknife;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ButterKnife {
    static final Map<Class<?>, Injector<Object>> f5239a;
    static final Injector<Object> f5240b;
    private static boolean f5241c;

    public interface Injector<T> {
        void inject(Finder finder, T t, Object obj);

        void reset(T t);
    }

    /* renamed from: butterknife.ButterKnife.1 */
    final class C05821 implements Injector<Object> {
        C05821() {
        }

        public void inject(Finder finder, Object obj, Object obj2) {
        }

        public void reset(Object obj) {
        }
    }

    public enum Finder {
        VIEW {
            protected View m7735a(Object obj, int i) {
                return ((View) obj).findViewById(i);
            }

            protected Context m7734a(Object obj) {
                return ((View) obj).getContext();
            }
        },
        ACTIVITY {
            protected View m7737a(Object obj, int i) {
                return ((Activity) obj).findViewById(i);
            }

            protected Context m7736a(Object obj) {
                return (Activity) obj;
            }
        },
        DIALOG {
            protected View m7739a(Object obj, int i) {
                return ((Dialog) obj).findViewById(i);
            }

            protected Context m7738a(Object obj) {
                return ((Dialog) obj).getContext();
            }
        };

        protected abstract Context m7729a(Object obj);

        protected abstract View m7730a(Object obj, int i);

        public <T> T m7732a(Object obj, int i, String str) {
            T b = m7733b(obj, i, str);
            if (b != null) {
                return b;
            }
            throw new IllegalStateException("Required view '" + m7729a(obj).getResources().getResourceEntryName(i) + "' with ID " + i + " for " + str + " was not found. If this view is optional add '@Optional' annotation.");
        }

        public <T> T m7733b(Object obj, int i, String str) {
            return m7731a(m7730a(obj, i), i, str);
        }

        public <T> T m7731a(View view, int i, String str) {
            return view;
        }
    }

    static {
        f5241c = false;
        f5239a = new LinkedHashMap();
        f5240b = new C05821();
    }

    public static void m7741a(Activity activity) {
        m7745a(activity, activity, Finder.ACTIVITY);
    }

    public static void m7742a(Dialog dialog) {
        m7745a(dialog, dialog, Finder.DIALOG);
    }

    public static void m7744a(Object obj, View view) {
        m7745a(obj, view, Finder.VIEW);
    }

    public static void m7743a(Object obj) {
        Class cls = obj.getClass();
        try {
            if (f5241c) {
                Log.d("ButterKnife", "Looking up view injector for " + cls.getName());
            }
            Injector a = m7740a(cls);
            if (a != null) {
                a.reset(obj);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Unable to reset views for " + obj, e2);
        }
    }

    static void m7745a(Object obj, Object obj2, Finder finder) {
        Class cls = obj.getClass();
        try {
            if (f5241c) {
                Log.d("ButterKnife", "Looking up view injector for " + cls.getName());
            }
            Injector a = m7740a(cls);
            if (a != null) {
                a.inject(finder, obj, obj2);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Unable to inject views for " + obj, e2);
        }
    }

    private static Injector<Object> m7740a(Class<?> cls) {
        Injector<Object> injector = (Injector) f5239a.get(cls);
        if (injector == null) {
            String name = cls.getName();
            if (name.startsWith("android.") || name.startsWith("java.")) {
                if (f5241c) {
                    Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
                }
                return f5240b;
            }
            try {
                injector = (Injector) Class.forName(name + "$$ViewInjector").newInstance();
                if (f5241c) {
                    Log.d("ButterKnife", "HIT: Class loaded injection class.");
                }
            } catch (ClassNotFoundException e) {
                if (f5241c) {
                    Log.d("ButterKnife", "Not found. Trying superclass " + cls.getSuperclass().getName());
                }
                injector = m7740a(cls.getSuperclass());
            }
            f5239a.put(cls, injector);
            return injector;
        } else if (!f5241c) {
            return injector;
        } else {
            Log.d("ButterKnife", "HIT: Cached in injector map.");
            return injector;
        }
    }
}
