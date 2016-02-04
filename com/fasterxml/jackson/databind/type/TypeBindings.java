package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeBindings {
    private static final JavaType[] NO_TYPES;
    public static final JavaType UNBOUND;
    protected Map<String, JavaType> _bindings;
    protected final Class<?> _contextClass;
    protected final JavaType _contextType;
    private final TypeBindings _parentBindings;
    protected HashSet<String> _placeholders;
    protected final TypeFactory _typeFactory;

    static {
        NO_TYPES = new JavaType[0];
        UNBOUND = new SimpleType(Object.class);
    }

    public TypeBindings(TypeFactory typeFactory, Class<?> cls) {
        this(typeFactory, null, cls, null);
    }

    public TypeBindings(TypeFactory typeFactory, JavaType javaType) {
        this(typeFactory, null, javaType.getRawClass(), javaType);
    }

    public TypeBindings childInstance() {
        return new TypeBindings(this._typeFactory, this, this._contextClass, this._contextType);
    }

    private TypeBindings(TypeFactory typeFactory, TypeBindings typeBindings, Class<?> cls, JavaType javaType) {
        this._typeFactory = typeFactory;
        this._parentBindings = typeBindings;
        this._contextClass = cls;
        this._contextType = javaType;
    }

    public JavaType resolveType(Type type) {
        return this._typeFactory._constructType(type, this);
    }

    public JavaType findType(String str) {
        if (this._bindings == null) {
            _resolve();
        }
        JavaType javaType = (JavaType) this._bindings.get(str);
        if (javaType != null) {
            return javaType;
        }
        if (this._placeholders != null && this._placeholders.contains(str)) {
            return UNBOUND;
        }
        if (this._parentBindings != null) {
            return this._parentBindings.findType(str);
        }
        if (this._contextClass != null && this._contextClass.getEnclosingClass() != null && !Modifier.isStatic(this._contextClass.getModifiers())) {
            return UNBOUND;
        }
        String name;
        if (this._contextClass != null) {
            name = this._contextClass.getName();
        } else if (this._contextType != null) {
            name = this._contextType.toString();
        } else {
            name = "UNKNOWN";
        }
        throw new IllegalArgumentException("Type variable '" + str + "' can not be resolved (with context of class " + name + ")");
    }

    public void addBinding(String str, JavaType javaType) {
        if (this._bindings == null || this._bindings.size() == 0) {
            this._bindings = new LinkedHashMap();
        }
        this._bindings.put(str, javaType);
    }

    public JavaType[] typesAsArray() {
        if (this._bindings == null) {
            _resolve();
        }
        if (this._bindings.size() == 0) {
            return NO_TYPES;
        }
        return (JavaType[]) this._bindings.values().toArray(new JavaType[this._bindings.size()]);
    }

    protected void _resolve() {
        _resolveBindings(this._contextClass);
        if (this._contextType != null) {
            int containedTypeCount = this._contextType.containedTypeCount();
            if (containedTypeCount > 0) {
                for (int i = 0; i < containedTypeCount; i++) {
                    addBinding(this._contextType.containedTypeName(i), this._contextType.containedType(i));
                }
            }
        }
        if (this._bindings == null) {
            this._bindings = Collections.emptyMap();
        }
    }

    public void _addPlaceholder(String str) {
        if (this._placeholders == null) {
            this._placeholders = new HashSet();
        }
        this._placeholders.add(str);
    }

    protected void _resolveBindings(Type type) {
        int i = 0;
        if (type != null) {
            Class cls;
            int i2;
            TypeVariable[] typeParameters;
            String name;
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    cls = (Class) parameterizedType.getRawType();
                    typeParameters = cls.getTypeParameters();
                    if (typeParameters.length != actualTypeArguments.length) {
                        throw new IllegalArgumentException("Strange parametrized type (in class " + cls.getName() + "): number of type arguments != number of type parameters (" + actualTypeArguments.length + " vs " + typeParameters.length + ")");
                    }
                    int length = actualTypeArguments.length;
                    for (int i3 = 0; i3 < length; i3++) {
                        name = typeParameters[i3].getName();
                        if (this._bindings == null) {
                            this._bindings = new LinkedHashMap();
                        } else if (this._bindings.containsKey(name)) {
                        }
                        _addPlaceholder(name);
                        this._bindings.put(name, this._typeFactory._constructType(actualTypeArguments[i3], this));
                    }
                }
                cls = (Class) parameterizedType.getRawType();
            } else if (type instanceof Class) {
                Class cls2 = (Class) type;
                cls = cls2.getDeclaringClass();
                if (!(cls == null || cls.isAssignableFrom(cls2))) {
                    _resolveBindings(cls2.getDeclaringClass());
                }
                typeParameters = cls2.getTypeParameters();
                if (typeParameters != null && typeParameters.length > 0) {
                    JavaType[] javaTypeArr = null;
                    if (this._contextType != null && cls2.isAssignableFrom(this._contextType.getRawClass())) {
                        javaTypeArr = this._typeFactory.findTypeParameters(this._contextType, cls2);
                    }
                    i2 = 0;
                    while (i2 < typeParameters.length) {
                        TypeVariable typeVariable = typeParameters[i2];
                        name = typeVariable.getName();
                        Type type2 = typeVariable.getBounds()[0];
                        if (type2 != null) {
                            if (this._bindings == null) {
                                this._bindings = new LinkedHashMap();
                            } else if (this._bindings.containsKey(name)) {
                            }
                            _addPlaceholder(name);
                            if (javaTypeArr == null || javaTypeArr.length <= i2) {
                                this._bindings.put(name, this._typeFactory._constructType(type2, this));
                            } else {
                                this._bindings.put(name, javaTypeArr[i2]);
                            }
                        }
                        i2++;
                    }
                }
                cls = cls2;
            } else {
                return;
            }
            _resolveBindings(cls.getGenericSuperclass());
            Type[] genericInterfaces = cls.getGenericInterfaces();
            i2 = genericInterfaces.length;
            while (i < i2) {
                _resolveBindings(genericInterfaces[i]);
                i++;
            }
        }
    }

    public String toString() {
        if (this._bindings == null) {
            _resolve();
        }
        StringBuilder stringBuilder = new StringBuilder("[TypeBindings for ");
        if (this._contextType != null) {
            stringBuilder.append(this._contextType.toString());
        } else {
            stringBuilder.append(this._contextClass.getName());
        }
        stringBuilder.append(": ").append(this._bindings).append("]");
        return stringBuilder.toString();
    }
}
