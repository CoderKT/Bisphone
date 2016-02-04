package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final List<Class<?>> _superTypes;

    static {
        NO_ANNOTATION_MAPS = new AnnotationMap[0];
    }

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver, AnnotationMap annotationMap) {
        this._creatorsResolved = false;
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
        this._classAnnotations = annotationMap;
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, ClassUtil.findSuperTypes(cls, null), annotationIntrospector, mixInResolver, null);
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver, null);
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public String getName() {
        return this._class.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.get(cls);
    }

    public Type getGenericType() {
        return this._class;
    }

    public Class<?> getRawType() {
        return this._class;
    }

    protected AnnotationMap getAllAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public Annotations getAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(str, clsArr);
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    private void resolveClassAnnotations() {
        this._classAnnotations = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            if (this._primaryMixIn != null) {
                _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
            }
            _addAnnotationsIfNotPresent(this._classAnnotations, this._class.getDeclaredAnnotations());
            for (Class cls : this._superTypes) {
                _addClassMixIns(this._classAnnotations, cls);
                _addAnnotationsIfNotPresent(this._classAnnotations, cls.getDeclaredAnnotations());
            }
            _addClassMixIns(this._classAnnotations, Object.class);
        }
    }

    private void resolveCreators() {
        int i;
        int size;
        int i2 = 0;
        Constructor[] declaredConstructors = this._class.getDeclaredConstructors();
        List list = null;
        for (Constructor constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 0) {
                this._defaultConstructor = _constructConstructor(constructor, true);
            } else {
                if (list == null) {
                    list = new ArrayList(Math.max(10, declaredConstructors.length));
                }
                list.add(_constructConstructor(constructor, false));
            }
        }
        if (list == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = list;
        }
        if (!(this._primaryMixIn == null || (this._defaultConstructor == null && this._constructors.isEmpty()))) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.hasIgnoreMarker(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                size = this._constructors.size();
                while (true) {
                    i = size - 1;
                    if (i < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember) this._constructors.get(i))) {
                        this._constructors.remove(i);
                        size = i;
                    } else {
                        size = i;
                    }
                }
            }
        }
        Method[] declaredMethods = this._class.getDeclaredMethods();
        int length = declaredMethods.length;
        list = null;
        while (i2 < length) {
            Method method = declaredMethods[i2];
            if (Modifier.isStatic(method.getModifiers())) {
                if (list == null) {
                    list = new ArrayList(8);
                }
                list.add(_constructCreatorMethod(method));
            }
            i2++;
        }
        if (list != null) {
            this._creatorMethods = list;
            if (this._primaryMixIn != null) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null) {
                size = this._creatorMethods.size();
                while (true) {
                    i2 = size - 1;
                    if (i2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember) this._creatorMethods.get(i2))) {
                        this._creatorMethods.remove(i2);
                        size = i2;
                    } else {
                        size = i2;
                    }
                }
            }
        } else {
            this._creatorMethods = Collections.emptyList();
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (Class cls : this._superTypes) {
            Class cls2;
            _addMemberMethods(cls2, this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(cls2), annotatedMethodMap);
        }
        if (this._mixInResolver != null) {
            cls2 = this._mixInResolver.findMixInClassFor(Object.class);
            if (cls2 != null) {
                _addMethodMixIns(this._class, this._memberMethods, cls2, annotatedMethodMap);
            }
        }
        if (this._annotationIntrospector != null && !annotatedMethodMap.isEmpty()) {
            Iterator it = annotatedMethodMap.iterator();
            while (it.hasNext()) {
                AnnotatedMethod annotatedMethod = (AnnotatedMethod) it.next();
                try {
                    Method declaredMethod = Object.class.getDeclaredMethod(annotatedMethod.getName(), annotatedMethod.getRawParameterTypes());
                    if (declaredMethod != null) {
                        AnnotatedMethod _constructMethod = _constructMethod(declaredMethod);
                        _addMixOvers(annotatedMethod.getAnnotated(), _constructMethod, false);
                        this._memberMethods.add(_constructMethod);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private void resolveFields() {
        Map _findFields = _findFields(this._class, null);
        if (_findFields == null || _findFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        this._fields = new ArrayList(_findFields.size());
        this._fields.addAll(_findFields.values());
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        if (this._mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, this._mixInResolver.findMixInClassFor(cls));
        }
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            _addAnnotationsIfNotPresent(annotationMap, cls2.getDeclaredAnnotations());
            for (Class declaredAnnotations : ClassUtil.findSuperTypes(cls2, cls)) {
                _addAnnotationsIfNotPresent(annotationMap, declaredAnnotations.getDeclaredAnnotations());
            }
        }
    }

    protected void _addConstructorMixIns(Class<?> cls) {
        int size = this._constructors == null ? 0 : this._constructors.size();
        MemberKey[] memberKeyArr = null;
        for (Constructor constructor : cls.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length != 0) {
                MemberKey[] memberKeyArr2;
                if (memberKeyArr == null) {
                    memberKeyArr2 = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr2[i] = new MemberKey(((AnnotatedConstructor) this._constructors.get(i)).getAnnotated());
                    }
                } else {
                    memberKeyArr2 = memberKeyArr;
                }
                MemberKey memberKey = new MemberKey(constructor);
                for (int i2 = 0; i2 < size; i2++) {
                    if (memberKey.equals(memberKeyArr2[i2])) {
                        _addMixOvers(constructor, (AnnotatedConstructor) this._constructors.get(i2), true);
                        memberKeyArr = memberKeyArr2;
                        break;
                    }
                }
                memberKeyArr = memberKeyArr2;
            } else if (this._defaultConstructor != null) {
                _addMixOvers(constructor, this._defaultConstructor, false);
            }
        }
    }

    protected void _addFactoryMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr = null;
        int size = this._creatorMethods.size();
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                MemberKey[] memberKeyArr2;
                if (memberKeyArr == null) {
                    memberKeyArr2 = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr2[i] = new MemberKey(((AnnotatedMethod) this._creatorMethods.get(i)).getAnnotated());
                    }
                } else {
                    memberKeyArr2 = memberKeyArr;
                }
                MemberKey memberKey = new MemberKey(method);
                for (int i2 = 0; i2 < size; i2++) {
                    if (memberKey.equals(memberKeyArr2[i2])) {
                        _addMixOvers(method, (AnnotatedMethod) this._creatorMethods.get(i2), true);
                        memberKeyArr = memberKeyArr2;
                        break;
                    }
                }
                memberKeyArr = memberKeyArr2;
            }
        }
    }

    protected void _addMemberMethods(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(cls, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find == null) {
                        find = _constructMethod(method);
                        annotatedMethodMap.add(find);
                        AnnotatedMethod remove = annotatedMethodMap2.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), find, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    protected void _addMethodMixIns(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        List<Class> arrayList = new ArrayList();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class declaredMethods : arrayList) {
            for (Method method : declaredMethods.getDeclaredMethods()) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find != null) {
                        _addMixUnders(method, find);
                    } else {
                        find = annotatedMethodMap2.find(method);
                        if (find != null) {
                            _addMixUnders(method, find);
                        } else {
                            annotatedMethodMap2.add(_constructMethod(method));
                        }
                    }
                }
            }
        }
    }

    protected Map<String, AnnotatedField> _findFields(Class<?> cls, Map<String, AnnotatedField> map) {
        Class superclass = cls.getSuperclass();
        if (superclass == null) {
            return map;
        }
        Map<String, AnnotatedField> _findFields = _findFields(superclass, map);
        Map<String, AnnotatedField> map2 = _findFields;
        for (Field field : cls.getDeclaredFields()) {
            if (_isIncludableField(field)) {
                if (map2 == null) {
                    map2 = new LinkedHashMap();
                }
                map2.put(field.getName(), _constructField(field));
            }
        }
        if (this._mixInResolver == null) {
            return map2;
        }
        Class findMixInClassFor = this._mixInResolver.findMixInClassFor(cls);
        if (findMixInClassFor == null) {
            return map2;
        }
        _addFieldMixIns(superclass, findMixInClassFor, map2);
        return map2;
    }

    protected void _addFieldMixIns(Class<?> cls, Class<?> cls2, Map<String, AnnotatedField> map) {
        List<Class> arrayList = new ArrayList();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class declaredFields : arrayList) {
            for (Field field : declaredFields.getDeclaredFields()) {
                if (_isIncludableField(field)) {
                    AnnotatedField annotatedField = (AnnotatedField) map.get(field.getName());
                    if (annotatedField != null) {
                        _addOrOverrideAnnotations(annotatedField, field.getDeclaredAnnotations());
                    }
                }
            }
        }
    }

    protected AnnotatedMethod _constructMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), null);
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), null);
    }

    protected AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        if (z) {
            return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), null);
        }
        AnnotationMap[] _collectRelevantAnnotations;
        Object parameterAnnotations = constructor.getParameterAnnotations();
        int length = constructor.getParameterTypes().length;
        if (length != parameterAnnotations.length) {
            Object obj;
            Class declaringClass = constructor.getDeclaringClass();
            if (declaringClass.isEnum() && length == parameterAnnotations.length + 2) {
                obj = new Annotation[(parameterAnnotations.length + 2)][];
                System.arraycopy(parameterAnnotations, 0, obj, 2, parameterAnnotations.length);
                _collectRelevantAnnotations = _collectRelevantAnnotations((Annotation[][]) obj);
            } else if (declaringClass.isMemberClass() && length == parameterAnnotations.length + 1) {
                Annotation[][] annotationArr = new Annotation[(parameterAnnotations.length + 1)][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 1, parameterAnnotations.length);
                _collectRelevantAnnotations = _collectRelevantAnnotations(annotationArr);
            } else {
                obj = parameterAnnotations;
                _collectRelevantAnnotations = null;
            }
            if (_collectRelevantAnnotations == null) {
                throw new IllegalStateException("Internal error: constructor for " + constructor.getDeclaringClass().getName() + " has mismatch: " + length + " parameters; " + obj.length + " sets of annotations");
            }
        }
        _collectRelevantAnnotations = _collectRelevantAnnotations((Annotation[][]) parameterAnnotations);
        return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), _collectRelevantAnnotations);
    }

    protected AnnotatedMethod _constructCreatorMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length));
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    protected AnnotatedField _constructField(Field field) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedField(field, _emptyAnnotationMap());
        }
        return new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    protected boolean _isIncludableMemberMethod(Method method) {
        if (Modifier.isStatic(method.getModifiers()) || method.isSynthetic() || method.isBridge() || method.getParameterTypes().length > 2) {
            return false;
        }
        return true;
    }

    private boolean _isIncludableField(Field field) {
        if (field.isSynthetic()) {
            return false;
        }
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
            return false;
        }
        return true;
    }

    protected AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    protected AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap = new AnnotationMap();
        _addAnnotationsIfNotPresent(annotationMap, annotationArr);
        return annotationMap;
    }

    private void _addAnnotationsIfNotPresent(AnnotationMap annotationMap, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List list = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (r0 == null) {
                        list = new LinkedList();
                    }
                    list.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotationMap.addIfNotPresent(annotation);
                }
            }
            if (r0 != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : r0) {
                    _addAnnotationsIfNotPresent(annotationMap, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List list = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (r0 == null) {
                        list = new LinkedList();
                    }
                    list.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addIfNotPresent(annotation);
                }
            }
            if (r0 != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : r0) {
                    _addAnnotationsIfNotPresent(annotatedMember, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            List list = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (r0 == null) {
                        list = new LinkedList();
                    }
                    list.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addOrOverride(annotation);
                }
            }
            if (r0 != null) {
                for (Annotation[] _addOrOverrideAnnotations : r0) {
                    _addOrOverrideAnnotations(annotatedMember, _addOrOverrideAnnotations);
                }
            }
        }
    }

    protected void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        _addOrOverrideAnnotations(annotatedConstructor, constructor.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    protected void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        _addOrOverrideAnnotations(annotatedMethod, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    protected void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        _addAnnotationsIfNotPresent((AnnotatedMember) annotatedMethod, method.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation annotation) {
        return this._annotationIntrospector != null && this._annotationIntrospector.isAnnotationBundle(annotation);
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
