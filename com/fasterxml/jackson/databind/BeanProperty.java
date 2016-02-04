package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;

public interface BeanProperty {

    public class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final PropertyMetadata _metadata;
        protected final PropertyName _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, Annotations annotations, AnnotatedMember annotatedMember, PropertyMetadata propertyMetadata) {
            this._name = propertyName;
            this._type = javaType;
            this._wrapperName = propertyName2;
            this._metadata = propertyMetadata;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        public Std withType(JavaType javaType) {
            return new Std(this._name, javaType, this._wrapperName, this._contextAnnotations, this._member, this._metadata);
        }

        public JavaType getType() {
            return this._type;
        }

        public PropertyName getWrapperName() {
            return this._wrapperName;
        }

        public AnnotatedMember getMember() {
            return this._member;
        }
    }

    AnnotatedMember getMember();

    JavaType getType();
}
