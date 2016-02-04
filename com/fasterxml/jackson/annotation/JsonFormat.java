package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {

    public enum Shape {
        ANY,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN;

        public boolean isNumeric() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }
    }

    public class Value {
        private TimeZone _timezone;
        private final Locale locale;
        private final String pattern;
        private final Shape shape;
        private final String timezoneStr;

        public Value() {
            this("", Shape.ANY, "", "");
        }

        public Value(JsonFormat jsonFormat) {
            this(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
        }

        public Value(String str, Shape shape, String str2, String str3) {
            Locale locale = (str2 == null || str2.length() == 0 || "##default".equals(str2)) ? null : new Locale(str2);
            String str4 = (str3 == null || str3.length() == 0 || "##default".equals(str3)) ? null : str3;
            this(str, shape, locale, str4, null);
        }

        public Value(String str, Shape shape, Locale locale, String str2, TimeZone timeZone) {
            this.pattern = str;
            this.shape = shape;
            this.locale = locale;
            this._timezone = timeZone;
            this.timezoneStr = str2;
        }

        public String getPattern() {
            return this.pattern;
        }

        public Shape getShape() {
            return this.shape;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public TimeZone getTimeZone() {
            TimeZone timeZone = this._timezone;
            if (timeZone != null) {
                return timeZone;
            }
            if (this.timezoneStr == null) {
                return null;
            }
            timeZone = TimeZone.getTimeZone(this.timezoneStr);
            this._timezone = timeZone;
            return timeZone;
        }

        public boolean hasPattern() {
            return this.pattern != null && this.pattern.length() > 0;
        }

        public boolean hasLocale() {
            return this.locale != null;
        }
    }

    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";
}
