package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TypeFactory implements Serializable {
    protected static final SimpleType CORE_TYPE_BOOL;
    protected static final SimpleType CORE_TYPE_INT;
    protected static final SimpleType CORE_TYPE_LONG;
    protected static final SimpleType CORE_TYPE_STRING;
    private static final JavaType[] NO_TYPES;
    protected static final TypeFactory instance;
    protected transient HierarchicType _cachedArrayListType;
    protected transient HierarchicType _cachedHashMapType;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LRUMap<ClassKey, JavaType> _typeCache;

    static {
        NO_TYPES = new JavaType[0];
        instance = new TypeFactory();
        CORE_TYPE_STRING = new SimpleType(String.class);
        CORE_TYPE_BOOL = new SimpleType(Boolean.TYPE);
        CORE_TYPE_INT = new SimpleType(Integer.TYPE);
        CORE_TYPE_LONG = new SimpleType(Long.TYPE);
    }

    private TypeFactory() {
        this._typeCache = new LRUMap(16, 100);
        this._parser = new TypeParser(this);
        this._modifiers = null;
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        if (javaType.getRawClass() == cls) {
            return javaType;
        }
        if (!(javaType instanceof SimpleType) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return javaType.narrowBy(cls);
        }
        if (javaType.getRawClass().isAssignableFrom(cls)) {
            JavaType _fromClass = _fromClass(cls, new TypeBindings(this, javaType.getRawClass()));
            Object valueHandler = javaType.getValueHandler();
            if (valueHandler != null) {
                _fromClass = _fromClass.withValueHandler(valueHandler);
            }
            valueHandler = javaType.getTypeHandler();
            if (valueHandler != null) {
                _fromClass = _fromClass.withTypeHandler(valueHandler);
            }
            return _fromClass;
        }
        throw new IllegalArgumentException("Class " + cls.getClass().getName() + " not subtype of " + javaType);
    }

    public JavaType constructFromCanonical(String str) {
        return this._parser.parse(str);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass != cls) {
            return findTypeParameters(rawClass, cls, new TypeBindings(this, javaType));
        }
        int containedTypeCount = javaType.containedTypeCount();
        if (containedTypeCount == 0) {
            return null;
        }
        JavaType[] javaTypeArr = new JavaType[containedTypeCount];
        for (int i = 0; i < containedTypeCount; i++) {
            javaTypeArr[i] = javaType.containedType(i);
        }
        return javaTypeArr;
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(cls, cls2, new TypeBindings(this, (Class) cls));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        HierarchicType _findSuperTypeChain = _findSuperTypeChain(cls, cls2);
        if (_findSuperTypeChain == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not a subtype of " + cls2.getName());
        }
        while (_findSuperTypeChain.getSuperType() != null) {
            _findSuperTypeChain = _findSuperTypeChain.getSuperType();
            Class rawClass = _findSuperTypeChain.getRawClass();
            TypeBindings typeBindings2 = new TypeBindings(this, rawClass);
            if (_findSuperTypeChain.isGeneric()) {
                Type[] actualTypeArguments = _findSuperTypeChain.asGeneric().getActualTypeArguments();
                TypeVariable[] typeParameters = rawClass.getTypeParameters();
                int length = actualTypeArguments.length;
                for (int i = 0; i < length; i++) {
                    typeBindings2.addBinding(typeParameters[i].getName(), _constructType(actualTypeArguments[i], typeBindings));
                }
            }
            typeBindings = typeBindings2;
        }
        if (_findSuperTypeChain.isGeneric()) {
            return typeBindings.typesAsArray();
        }
        return null;
    }

    public JavaType constructType(Type type) {
        return _constructType(type, null);
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    protected JavaType _constructType(Type type, TypeBindings typeBindings) {
        JavaType _fromClass;
        if (type instanceof Class) {
            _fromClass = _fromClass((Class) type, typeBindings);
        } else if (type instanceof ParameterizedType) {
            _fromClass = _fromParamType((ParameterizedType) type, typeBindings);
        } else if (type instanceof JavaType) {
            return (JavaType) type;
        } else {
            if (type instanceof GenericArrayType) {
                _fromClass = _fromArrayType((GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                _fromClass = _fromVariable((TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                _fromClass = _fromWildcard((WildcardType) type, typeBindings);
            } else {
                throw new IllegalArgumentException("Unrecognized Type: " + (type == null ? "[null]" : type.toString()));
            }
        }
        if (!(this._modifiers == null || r0.isContainerType())) {
            TypeModifier[] typeModifierArr = this._modifiers;
            int length = typeModifierArr.length;
            int i = 0;
            while (i < length) {
                JavaType modifyType = typeModifierArr[i].modifyType(_fromClass, type, typeBindings, this);
                i++;
                _fromClass = modifyType;
            }
        }
        return _fromClass;
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return CollectionType.construct(cls, constructType(cls2));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        return CollectionType.construct(cls, javaType);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return MapType.construct(cls, javaType, javaType2);
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType(cls2), constructType(cls3));
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length != javaTypeArr.length) {
            throw new IllegalArgumentException("Parameter type mismatch for " + cls.getName() + ": expected " + typeParameters.length + " parameters, was given " + javaTypeArr.length);
        }
        String[] strArr = new String[typeParameters.length];
        int length = typeParameters.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        return new SimpleType(cls, strArr, javaTypeArr, null, null, false);
    }

    public JavaType uncheckedSimpleType(Class<?> cls) {
        return new SimpleType(cls);
    }

    protected JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        if (cls == String.class) {
            return CORE_TYPE_STRING;
        }
        if (cls == Boolean.TYPE) {
            return CORE_TYPE_BOOL;
        }
        if (cls == Integer.TYPE) {
            return CORE_TYPE_INT;
        }
        if (cls == Long.TYPE) {
            return CORE_TYPE_LONG;
        }
        ClassKey classKey = new ClassKey(cls);
        JavaType javaType = (JavaType) this._typeCache.get(classKey);
        if (javaType != null) {
            return javaType;
        }
        if (cls.isArray()) {
            javaType = ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        } else if (cls.isEnum()) {
            javaType = new SimpleType(cls);
        } else if (Map.class.isAssignableFrom(cls)) {
            javaType = _mapType(cls);
        } else if (Collection.class.isAssignableFrom(cls)) {
            javaType = _collectionType(cls);
        } else {
            javaType = new SimpleType(cls);
        }
        this._typeCache.put(classKey, javaType);
        return javaType;
    }

    protected JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (list.size() <= 0) {
                return _mapType(cls);
            }
            return MapType.construct(cls, (JavaType) list.get(0), list.size() >= 2 ? (JavaType) list.get(1) : _unknownType());
        } else if (Collection.class.isAssignableFrom(cls)) {
            if (list.size() >= 1) {
                return CollectionType.construct(cls, (JavaType) list.get(0));
            }
            return _collectionType(cls);
        } else if (list.size() == 0) {
            return new SimpleType(cls);
        } else {
            return constructSimpleType(cls, (JavaType[]) list.toArray(new JavaType[list.size()]));
        }
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        JavaType[] javaTypeArr;
        Class cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _constructType(actualTypeArguments[i], typeBindings);
            }
        }
        if (Map.class.isAssignableFrom(cls)) {
            javaTypeArr = findTypeParameters(constructSimpleType(cls, javaTypeArr), Map.class);
            if (javaTypeArr.length == 2) {
                return MapType.construct(cls, javaTypeArr[0], javaTypeArr[1]);
            }
            throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + cls.getName() + " (found " + javaTypeArr.length + ")");
        } else if (Collection.class.isAssignableFrom(cls)) {
            javaTypeArr = findTypeParameters(constructSimpleType(cls, javaTypeArr), Collection.class);
            if (javaTypeArr.length == 1) {
                return CollectionType.construct(cls, javaTypeArr[0]);
            }
            throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + cls.getName() + " (found " + javaTypeArr.length + ")");
        } else if (length == 0) {
            return new SimpleType(cls);
        } else {
            return constructSimpleType(cls, javaTypeArr);
        }
    }

    protected JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings), null, null);
    }

    protected JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        if (typeBindings == null) {
            return _unknownType();
        }
        String name = typeVariable.getName();
        JavaType findType = typeBindings.findType(name);
        if (findType != null) {
            return findType;
        }
        Type[] bounds = typeVariable.getBounds();
        typeBindings._addPlaceholder(name);
        return _constructType(bounds[0], typeBindings);
    }

    protected JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    private JavaType _mapType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters((Class) cls, Map.class);
        if (findTypeParameters == null) {
            return MapType.construct(cls, _unknownType(), _unknownType());
        }
        if (findTypeParameters.length == 2) {
            return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
        }
        throw new IllegalArgumentException("Strange Map type " + cls.getName() + ": can not determine type parameters");
    }

    private JavaType _collectionType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters((Class) cls, Collection.class);
        if (findTypeParameters == null) {
            return CollectionType.construct(cls, _unknownType());
        }
        if (findTypeParameters.length == 1) {
            return CollectionType.construct(cls, findTypeParameters[0]);
        }
        throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": can not determine type parameters");
    }

    protected JavaType _unknownType() {
        return new SimpleType(Object.class);
    }

    protected HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        if (cls2.isInterface()) {
            return _findSuperInterfaceChain(cls, cls2);
        }
        return _findSuperClassChain(cls, cls2);
    }

    protected HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return hierarchicType;
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass != null) {
            HierarchicType _findSuperClassChain = _findSuperClassChain(genericSuperclass, cls);
            if (_findSuperClassChain != null) {
                _findSuperClassChain.setSubType(hierarchicType);
                hierarchicType.setSuperType(_findSuperClassChain);
                return hierarchicType;
            }
        }
        return null;
    }

    protected HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return new HierarchicType(type);
        }
        if (rawClass == HashMap.class && cls == Map.class) {
            return _hashMapSuperInterfaceChain(hierarchicType);
        }
        if (rawClass == ArrayList.class && cls == List.class) {
            return _arrayListSuperInterfaceChain(hierarchicType);
        }
        return _doFindSuperInterfaceChain(hierarchicType, cls);
    }

    protected HierarchicType _doFindSuperInterfaceChain(HierarchicType hierarchicType, Class<?> cls) {
        Class rawClass = hierarchicType.getRawClass();
        Type[] genericInterfaces = rawClass.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type _findSuperInterfaceChain : genericInterfaces) {
                HierarchicType _findSuperInterfaceChain2 = _findSuperInterfaceChain(_findSuperInterfaceChain, cls);
                if (_findSuperInterfaceChain2 != null) {
                    _findSuperInterfaceChain2.setSubType(hierarchicType);
                    hierarchicType.setSuperType(_findSuperInterfaceChain2);
                    return hierarchicType;
                }
            }
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass != null) {
            HierarchicType _findSuperInterfaceChain3 = _findSuperInterfaceChain(genericSuperclass, cls);
            if (_findSuperInterfaceChain3 != null) {
                _findSuperInterfaceChain3.setSubType(hierarchicType);
                hierarchicType.setSuperType(_findSuperInterfaceChain3);
                return hierarchicType;
            }
        }
        return null;
    }

    protected synchronized HierarchicType _hashMapSuperInterfaceChain(HierarchicType hierarchicType) {
        HierarchicType deepCloneWithoutSubtype;
        if (this._cachedHashMapType == null) {
            deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, Map.class);
            this._cachedHashMapType = deepCloneWithoutSubtype.getSuperType();
        }
        deepCloneWithoutSubtype = this._cachedHashMapType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype);
        deepCloneWithoutSubtype.setSubType(hierarchicType);
        return hierarchicType;
    }

    protected synchronized HierarchicType _arrayListSuperInterfaceChain(HierarchicType hierarchicType) {
        HierarchicType deepCloneWithoutSubtype;
        if (this._cachedArrayListType == null) {
            deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, List.class);
            this._cachedArrayListType = deepCloneWithoutSubtype.getSuperType();
        }
        deepCloneWithoutSubtype = this._cachedArrayListType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype);
        deepCloneWithoutSubtype.setSubType(hierarchicType);
        return hierarchicType;
    }
}
