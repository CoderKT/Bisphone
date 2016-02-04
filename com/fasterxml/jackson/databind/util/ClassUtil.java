package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.NoClass;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public final class ClassUtil {

    class EnumTypeLocator {
        static final EnumTypeLocator instance;
        private final Field enumMapTypeField;
        private final Field enumSetTypeField;

        static {
            instance = new EnumTypeLocator();
        }

        private EnumTypeLocator() {
            this.enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);
            this.enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            if (this.enumSetTypeField != null) {
                return (Class) get(enumSet, this.enumSetTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            if (this.enumMapTypeField != null) {
                return (Class) get(enumMap, this.enumMapTypeField);
            }
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }

        private Object get(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) {
            int i;
            int length;
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    break;
                }
            }
            Field field2 = null;
            if (field2 == null) {
                length = declaredFields.length;
                i = 0;
                Field field3 = field2;
                while (i < length) {
                    field2 = declaredFields[i];
                    if (field2.getType() != cls2) {
                        field2 = field3;
                    } else if (field3 != null) {
                        return null;
                    }
                    i++;
                    field3 = field2;
                }
                field2 = field3;
            }
            if (field2 == null) {
                return field2;
            }
            try {
                field2.setAccessible(true);
                return field2;
            } catch (Throwable th) {
                return field2;
            }
        }
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, new ArrayList(8));
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addSuperTypes(cls, cls2, list, false);
        return list;
    }

    private static void _addSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addSuperTypes : cls.getInterfaces()) {
                _addSuperTypes(_addSuperTypes, cls2, collection, true);
            }
            _addSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    public static String canBeABeanType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (cls.isEnum()) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String isLocalType(Class<?> cls, boolean z) {
        try {
            if (cls.getEnclosingMethod() != null) {
                return "local/anonymous";
            }
            if (!(z || cls.getEnclosingClass() == null || Modifier.isStatic(cls.getModifiers()))) {
                return "non-static member class";
            }
            return null;
        } catch (SecurityException e) {
        } catch (NullPointerException e2) {
        }
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        Class<?> cls2 = null;
        try {
            if (cls.getEnclosingMethod() == null && !Modifier.isStatic(cls.getModifiers())) {
                cls2 = cls.getEnclosingClass();
            }
        } catch (SecurityException e) {
        } catch (NullPointerException e2) {
        }
        return cls2;
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        if (name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.")) {
            return true;
        }
        return false;
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        if (cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public static Class<?> findClass(String str) {
        if (str.indexOf(46) < 0) {
            if ("int".equals(str)) {
                return Integer.TYPE;
            }
            if ("long".equals(str)) {
                return Long.TYPE;
            }
            if ("float".equals(str)) {
                return Float.TYPE;
            }
            if ("double".equals(str)) {
                return Double.TYPE;
            }
            if ("boolean".equals(str)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str)) {
                return Byte.TYPE;
            }
            if ("char".equals(str)) {
                return Character.TYPE;
            }
            if ("short".equals(str)) {
                return Short.TYPE;
            }
            if ("void".equals(str)) {
                return Void.TYPE;
            }
        }
        Throwable th = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str, true, contextClassLoader);
            } catch (Throwable th2) {
                th2 = getRootCause(th2);
            }
        }
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            if (th2 == null) {
                th2 = getRootCause(e);
            }
            if (th2 instanceof RuntimeException) {
                throw ((RuntimeException) th2);
            }
            throw new ClassNotFoundException(th2.getMessage(), th2);
        }
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new IllegalArgumentException(str, th);
        }
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static <T> T createInstance(Class<T> cls, boolean z) {
        Constructor findConstructor = findConstructor(cls, z);
        if (findConstructor == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
        }
        try {
            return findConstructor.newInstance(new Object[0]);
        } catch (Throwable e) {
            unwrapAndThrowAsIAE(e, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                checkAndFixAccess(declaredConstructor);
                return declaredConstructor;
            } else if (Modifier.isPublic(declaredConstructor.getModifiers())) {
                return declaredConstructor;
            } else {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
            }
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Throwable e2) {
            unwrapAndThrowAsIAE(e2, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e2.getMessage());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return Byte.valueOf((byte) 0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf((short) 0);
        }
        if (cls == Character.TYPE) {
            return Character.valueOf('\u0000');
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static void checkAndFixAccess(Member member) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            if (!accessibleObject.isAccessible()) {
                throw new IllegalArgumentException("Can not access " + member + " (from class " + member.getDeclaringClass().getName() + "; failed to set access: " + e.getMessage());
            }
        }
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        if (enumSet.isEmpty()) {
            return EnumTypeLocator.instance.enumTypeFor((EnumSet) enumSet);
        }
        return findEnumType((Enum) enumSet.iterator().next());
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        if (enumMap.isEmpty()) {
            return EnumTypeLocator.instance.enumTypeFor((EnumMap) enumMap);
        }
        return findEnumType((Enum) enumMap.keySet().iterator().next());
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<? extends Enum<?>> cls = enumR.getClass();
        if (cls.getSuperclass() != Enum.class) {
            return cls.getSuperclass();
        }
        return cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        if (cls.getSuperclass() != Enum.class) {
            return cls.getSuperclass();
        }
        return cls;
    }

    public static boolean isJacksonStdImpl(Object obj) {
        return obj != null && isJacksonStdImpl(obj.getClass());
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }

    public static final boolean isBogusClass(Class<?> cls) {
        return cls == Void.class || cls == Void.TYPE || cls == NoClass.class;
    }
}
