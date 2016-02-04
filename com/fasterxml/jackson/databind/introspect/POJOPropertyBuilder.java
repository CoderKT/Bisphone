package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected Linked<AnnotatedParameter> _ctorParameters;
    protected Linked<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected Linked<AnnotatedMethod> _getters;
    protected final PropertyName _internalName;
    protected final PropertyName _name;
    protected Linked<AnnotatedMethod> _setters;

    interface WithMember<T> {
        T withMember(AnnotatedMember annotatedMember);
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.1 */
    class C06291 implements WithMember<Class<?>[]> {
        C06291() {
        }

        public Class<?>[] withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.findViews(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.2 */
    class C06302 implements WithMember<ReferenceProperty> {
        C06302() {
        }

        public ReferenceProperty withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.3 */
    class C06313 implements WithMember<Boolean> {
        C06313() {
        }

        public Boolean withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.4 */
    class C06324 implements WithMember<Boolean> {
        C06324() {
        }

        public Boolean withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.hasRequiredMarker(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.5 */
    class C06335 implements WithMember<String> {
        C06335() {
        }

        public String withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.findPropertyDescription(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.6 */
    class C06346 implements WithMember<Integer> {
        C06346() {
        }

        public Integer withMember(AnnotatedMember annotatedMember) {
            return POJOPropertyBuilder.this._annotationIntrospector.findPropertyIndex(annotatedMember);
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.7 */
    class C06357 implements WithMember<ObjectIdInfo> {
        C06357() {
        }

        public ObjectIdInfo withMember(AnnotatedMember annotatedMember) {
            ObjectIdInfo findObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(annotatedMember);
            if (findObjectIdInfo != null) {
                return POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(annotatedMember, findObjectIdInfo);
            }
            return findObjectIdInfo;
        }
    }

    final class Linked<T> {
        public final boolean isMarkedIgnored;
        public final boolean isNameExplicit;
        public final boolean isVisible;
        public final PropertyName name;
        public final Linked<T> next;
        public final T value;

        public Linked(T t, Linked<T> linked, PropertyName propertyName, boolean z, boolean z2, boolean z3) {
            PropertyName propertyName2;
            this.value = t;
            this.next = linked;
            if (propertyName == null || propertyName.isEmpty()) {
                propertyName2 = null;
            } else {
                propertyName2 = propertyName;
            }
            this.name = propertyName2;
            if (z) {
                if (this.name == null) {
                    throw new IllegalArgumentException("Can not pass true for 'explName' if name is null/empty");
                } else if (!propertyName.hasSimpleName()) {
                    z = false;
                }
            }
            this.isNameExplicit = z;
            this.isVisible = z2;
            this.isMarkedIgnored = z3;
        }

        public Linked<T> withoutNext() {
            return this.next == null ? this : new Linked(this.value, null, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withValue(T t) {
            if (t == this.value) {
                return this;
            }
            return new Linked(t, this.next, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withNext(Linked<T> linked) {
            if (linked == this.next) {
                return this;
            }
            return new Linked(this.value, linked, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withoutIgnored() {
            if (this.isMarkedIgnored) {
                return this.next == null ? null : this.next.withoutIgnored();
            } else {
                if (this.next != null) {
                    Linked withoutIgnored = this.next.withoutIgnored();
                    if (withoutIgnored != this.next) {
                        return withNext(withoutIgnored);
                    }
                }
                return this;
            }
        }

        public Linked<T> withoutNonVisible() {
            Linked<T> withoutNonVisible = this.next == null ? null : this.next.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }

        private Linked<T> append(Linked<T> linked) {
            if (this.next == null) {
                return withNext(linked);
            }
            return withNext(this.next.append(linked));
        }

        public Linked<T> trimByVisibility() {
            if (this.next == null) {
                return this;
            }
            Linked<T> trimByVisibility = this.next.trimByVisibility();
            if (this.name != null) {
                if (trimByVisibility.name == null) {
                    return withNext(null);
                }
                return withNext(trimByVisibility);
            } else if (trimByVisibility.name != null) {
                return trimByVisibility;
            } else {
                if (this.isVisible == trimByVisibility.isVisible) {
                    return withNext(trimByVisibility);
                }
                return this.isVisible ? withNext(null) : trimByVisibility;
            }
        }

        public String toString() {
            String str = this.value.toString() + "[visible=" + this.isVisible + ",ignore=" + this.isMarkedIgnored + ",explicitName=" + this.isNameExplicit + "]";
            if (this.next != null) {
                return str + ", " + this.next.toString();
            }
            return str;
        }
    }

    public POJOPropertyBuilder(PropertyName propertyName, AnnotationIntrospector annotationIntrospector, boolean z) {
        this(propertyName, propertyName, annotationIntrospector, z);
    }

    protected POJOPropertyBuilder(PropertyName propertyName, PropertyName propertyName2, AnnotationIntrospector annotationIntrospector, boolean z) {
        this._internalName = propertyName;
        this._name = propertyName2;
        this._annotationIntrospector = annotationIntrospector;
        this._forSerialization = z;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, PropertyName propertyName) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = propertyName;
        this._annotationIntrospector = pOJOPropertyBuilder._annotationIntrospector;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
        this._forSerialization = pOJOPropertyBuilder._forSerialization;
    }

    public POJOPropertyBuilder withName(PropertyName propertyName) {
        return new POJOPropertyBuilder(this, propertyName);
    }

    public POJOPropertyBuilder withSimpleName(String str) {
        PropertyName withSimpleName = this._name.withSimpleName(str);
        return withSimpleName == this._name ? this : new POJOPropertyBuilder(this, withSimpleName);
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    public String getName() {
        return this._name == null ? null : this._name.getSimpleName();
    }

    public PropertyName getFullName() {
        return this._name;
    }

    public String getInternalName() {
        return this._internalName.getSimpleName();
    }

    public PropertyName getWrapperName() {
        Annotated primaryMember = getPrimaryMember();
        return (primaryMember == null || this._annotationIntrospector == null) ? null : this._annotationIntrospector.findWrapperName(primaryMember);
    }

    public boolean isExplicitlyIncluded() {
        return _anyExplicits(this._fields) || _anyExplicits(this._getters) || _anyExplicits(this._setters) || _anyExplicits(this._ctorParameters);
    }

    public boolean isExplicitlyNamed() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean hasGetter() {
        return this._getters != null;
    }

    public boolean hasSetter() {
        return this._setters != null;
    }

    public boolean hasField() {
        return this._fields != null;
    }

    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    public boolean couldDeserialize() {
        return (this._ctorParameters == null && this._setters == null && this._fields == null) ? false : true;
    }

    public AnnotatedMethod getGetter() {
        Linked linked = this._getters;
        if (linked == null) {
            return null;
        }
        Linked linked2 = linked.next;
        if (linked2 == null) {
            return (AnnotatedMethod) linked.value;
        }
        while (linked2 != null) {
            Linked linked3;
            Class declaringClass = ((AnnotatedMethod) linked.value).getDeclaringClass();
            Class declaringClass2 = ((AnnotatedMethod) linked2.value).getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    linked3 = linked2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                    linked3 = linked;
                }
                linked2 = linked2.next;
                linked = linked3;
            }
            int _getterPriority = _getterPriority((AnnotatedMethod) linked2.value);
            int _getterPriority2 = _getterPriority((AnnotatedMethod) linked.value);
            if (_getterPriority != _getterPriority2) {
                if (_getterPriority < _getterPriority2) {
                    linked3 = linked2;
                } else {
                    linked3 = linked;
                }
                linked2 = linked2.next;
                linked = linked3;
            } else {
                throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod) linked.value).getFullName() + " vs " + ((AnnotatedMethod) linked2.value).getFullName());
            }
        }
        this._getters = linked.withoutNext();
        return (AnnotatedMethod) linked.value;
    }

    public AnnotatedMethod getSetter() {
        Linked linked = this._setters;
        if (linked == null) {
            return null;
        }
        Linked linked2 = linked.next;
        if (linked2 == null) {
            return (AnnotatedMethod) linked.value;
        }
        while (linked2 != null) {
            Linked linked3;
            Class declaringClass = ((AnnotatedMethod) linked.value).getDeclaringClass();
            Class declaringClass2 = ((AnnotatedMethod) linked2.value).getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    linked3 = linked2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                    linked3 = linked;
                }
                linked2 = linked2.next;
                linked = linked3;
            }
            int _setterPriority = _setterPriority((AnnotatedMethod) linked2.value);
            int _setterPriority2 = _setterPriority((AnnotatedMethod) linked.value);
            if (_setterPriority != _setterPriority2) {
                if (_setterPriority < _setterPriority2) {
                    linked3 = linked2;
                } else {
                    linked3 = linked;
                }
                linked2 = linked2.next;
                linked = linked3;
            } else {
                throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod) linked.value).getFullName() + " vs " + ((AnnotatedMethod) linked2.value).getFullName());
            }
        }
        this._setters = linked.withoutNext();
        return (AnnotatedMethod) linked.value;
    }

    public AnnotatedField getField() {
        if (this._fields == null) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) this._fields.value;
        Linked linked = this._fields.next;
        AnnotatedField annotatedField2 = annotatedField;
        while (linked != null) {
            annotatedField = (AnnotatedField) linked.value;
            Class declaringClass = annotatedField2.getDeclaringClass();
            Class declaringClass2 = annotatedField.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedField = annotatedField2;
                    }
                }
                linked = linked.next;
                annotatedField2 = annotatedField;
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField2.getFullName() + " vs " + annotatedField.getFullName());
        }
        return annotatedField2;
    }

    public AnnotatedParameter getConstructorParameter() {
        if (this._ctorParameters == null) {
            return null;
        }
        Linked linked = this._ctorParameters;
        while (!(((AnnotatedParameter) linked.value).getOwner() instanceof AnnotatedConstructor)) {
            Linked linked2 = linked.next;
            if (linked2 == null) {
                return (AnnotatedParameter) this._ctorParameters.value;
            }
            linked = linked2;
        }
        return (AnnotatedParameter) linked.value;
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMember getter = getGetter();
        if (getter == null) {
            return getField();
        }
        return getter;
    }

    public AnnotatedMember getMutator() {
        AnnotatedMember constructorParameter = getConstructorParameter();
        if (constructorParameter != null) {
            return constructorParameter;
        }
        constructorParameter = getSetter();
        if (constructorParameter == null) {
            return getField();
        }
        return constructorParameter;
    }

    public AnnotatedMember getNonConstructorMutator() {
        AnnotatedMember setter = getSetter();
        if (setter == null) {
            return getField();
        }
        return setter;
    }

    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        return getMutator();
    }

    protected int _getterPriority(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        if (name.startsWith("get") && name.length() > 3) {
            return 1;
        }
        if (!name.startsWith("is") || name.length() <= 2) {
            return 3;
        }
        return 2;
    }

    protected int _setterPriority(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        if (!name.startsWith(RSMSet.ELEMENT) || name.length() <= 3) {
            return 2;
        }
        return 1;
    }

    public Class<?>[] findViews() {
        return (Class[]) fromMemberAnnotations(new C06291());
    }

    public ReferenceProperty findReferenceType() {
        return (ReferenceProperty) fromMemberAnnotations(new C06302());
    }

    public boolean isTypeId() {
        Boolean bool = (Boolean) fromMemberAnnotations(new C06313());
        return bool != null && bool.booleanValue();
    }

    public PropertyMetadata getMetadata() {
        Boolean _findRequired = _findRequired();
        String _findDescription = _findDescription();
        Integer _findIndex = _findIndex();
        if (_findRequired == null && _findIndex == null) {
            return _findDescription == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : PropertyMetadata.STD_REQUIRED_OR_OPTIONAL.withDescription(_findDescription);
        } else {
            return PropertyMetadata.construct(_findRequired.booleanValue(), _findDescription, _findIndex);
        }
    }

    protected Boolean _findRequired() {
        return (Boolean) fromMemberAnnotations(new C06324());
    }

    protected String _findDescription() {
        return (String) fromMemberAnnotations(new C06335());
    }

    protected Integer _findIndex() {
        return (Integer) fromMemberAnnotations(new C06346());
    }

    public ObjectIdInfo findObjectIdInfo() {
        return (ObjectIdInfo) fromMemberAnnotations(new C06357());
    }

    public void addField(AnnotatedField annotatedField, PropertyName propertyName, boolean z, boolean z2, boolean z3) {
        this._fields = new Linked(annotatedField, this._fields, propertyName, z, z2, z3);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, PropertyName propertyName, boolean z, boolean z2, boolean z3) {
        this._ctorParameters = new Linked(annotatedParameter, this._ctorParameters, propertyName, z, z2, z3);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, PropertyName propertyName, boolean z, boolean z2, boolean z3) {
        this._getters = new Linked(annotatedMethod, this._getters, propertyName, z, z2, z3);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, PropertyName propertyName, boolean z, boolean z2, boolean z3) {
        this._setters = new Linked(annotatedMethod, this._setters, propertyName, z, z2, z3);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    private static <T> Linked<T> merge(Linked<T> linked, Linked<T> linked2) {
        if (linked == null) {
            return linked2;
        }
        if (linked2 == null) {
            return linked;
        }
        return linked.append(linked2);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    public void removeNonVisible(boolean z) {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (z || this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            if (this._getters != null) {
                this._getters = this._getters.withValue(((AnnotatedMethod) this._getters.value).withAnnotations(_mergeAnnotations(0, this._getters, this._fields, this._ctorParameters, this._setters)));
            } else if (this._fields != null) {
                this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, this._fields, this._ctorParameters, this._setters)));
            }
        } else if (this._ctorParameters != null) {
            this._ctorParameters = this._ctorParameters.withValue(((AnnotatedParameter) this._ctorParameters.value).withAnnotations(_mergeAnnotations(0, this._ctorParameters, this._setters, this._fields, this._getters)));
        } else if (this._setters != null) {
            this._setters = this._setters.withValue(((AnnotatedMethod) this._setters.value).withAnnotations(_mergeAnnotations(0, this._setters, this._fields, this._getters)));
        } else if (this._fields != null) {
            this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, this._fields, this._getters)));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, Linked<? extends AnnotatedMember>... linkedArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) linkedArr[i].value).getAllAnnotations();
        for (int i2 = i + 1; i2 < linkedArr.length; i2++) {
            if (linkedArr[i2] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i2, linkedArr));
            }
        }
        return allAnnotations;
    }

    private <T> Linked<T> _removeIgnored(Linked<T> linked) {
        return linked == null ? linked : linked.withoutIgnored();
    }

    private <T> Linked<T> _removeNonVisible(Linked<T> linked) {
        return linked == null ? linked : linked.withoutNonVisible();
    }

    private <T> Linked<T> _trimByVisibility(Linked<T> linked) {
        return linked == null ? linked : linked.trimByVisibility();
    }

    private <T> boolean _anyExplicits(Linked<T> linked) {
        while (linked != null) {
            if (linked.name != null && linked.name.hasSimpleName()) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyExplicitNames(Linked<T> linked) {
        while (linked != null) {
            if (linked.name != null && linked.isNameExplicit) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(Linked<T> linked) {
        while (linked != null) {
            if (linked.isVisible) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    private <T> boolean _anyIgnorals(Linked<T> linked) {
        while (linked != null) {
            if (linked.isMarkedIgnored) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public Set<PropertyName> findExplicitNames() {
        Set<PropertyName> _findExplicitNames = _findExplicitNames(this._ctorParameters, _findExplicitNames(this._setters, _findExplicitNames(this._getters, _findExplicitNames(this._fields, null))));
        if (_findExplicitNames == null) {
            return Collections.emptySet();
        }
        return _findExplicitNames;
    }

    public Collection<POJOPropertyBuilder> explode(Collection<PropertyName> collection) {
        Object hashMap = new HashMap();
        _explode(collection, hashMap, this._fields);
        _explode(collection, hashMap, this._getters);
        _explode(collection, hashMap, this._setters);
        _explode(collection, hashMap, this._ctorParameters);
        return hashMap.values();
    }

    private void _explode(Collection<PropertyName> collection, Map<PropertyName, POJOPropertyBuilder> map, Linked<?> linked) {
        for (Linked linked2 = linked; linked2 != null; linked2 = linked2.next) {
            PropertyName propertyName = linked2.name;
            if (linked2.isNameExplicit && propertyName != null) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) map.get(propertyName);
                if (pOJOPropertyBuilder == null) {
                    pOJOPropertyBuilder = new POJOPropertyBuilder(this._internalName, propertyName, this._annotationIntrospector, this._forSerialization);
                    map.put(propertyName, pOJOPropertyBuilder);
                }
                if (linked == this._fields) {
                    pOJOPropertyBuilder._fields = linked2.withNext(pOJOPropertyBuilder._fields);
                } else if (linked == this._getters) {
                    pOJOPropertyBuilder._getters = linked2.withNext(pOJOPropertyBuilder._getters);
                } else if (linked == this._setters) {
                    pOJOPropertyBuilder._setters = linked2.withNext(pOJOPropertyBuilder._setters);
                } else if (linked == this._ctorParameters) {
                    pOJOPropertyBuilder._ctorParameters = linked2.withNext(pOJOPropertyBuilder._ctorParameters);
                } else {
                    throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
                }
            } else if (linked2.isVisible) {
                throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name '" + this._name + "'): found multiple explicit names: " + collection + ", but also implicit accessor: " + linked2);
            }
        }
    }

    private Set<PropertyName> _findExplicitNames(Linked<? extends AnnotatedMember> linked, Set<PropertyName> set) {
        Set<PropertyName> set2 = set;
        while (linked != null) {
            if (linked.isNameExplicit && linked.name != null) {
                if (set2 == null) {
                    set2 = new HashSet();
                }
                set2.add(linked.name);
            }
            linked = linked.next;
        }
        return set2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    protected <T> T fromMemberAnnotations(WithMember<T> withMember) {
        T t = null;
        if (this._annotationIntrospector == null) {
            return null;
        }
        if (!this._forSerialization) {
            if (this._ctorParameters != null) {
                t = withMember.withMember((AnnotatedMember) this._ctorParameters.value);
            }
            if (t == null && this._setters != null) {
                t = withMember.withMember((AnnotatedMember) this._setters.value);
            }
        } else if (this._getters != null) {
            t = withMember.withMember((AnnotatedMember) this._getters.value);
        }
        if (t != null || this._fields == null) {
            return t;
        }
        return withMember.withMember((AnnotatedMember) this._fields.value);
    }
}
