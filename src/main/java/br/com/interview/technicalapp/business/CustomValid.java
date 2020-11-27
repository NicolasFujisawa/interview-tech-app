package br.com.interview.technicalapp.business;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The interface Custom valid.
 */
@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface CustomValid {

    /**
     * Message string.
     *
     * @return the string
     */
    String message() default "Fez algo não permitido {value}";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default { };

    /**
     * Value long.
     *
     * @return the long
     */
    long value();

    /**
     * Pay load class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payLoad() default { };

    /**
     * The interface List.
     */
    @Documented
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @interface List {
        /**
         * Value custom valid [ ].
         *
         * @return the custom valid [ ]
         */
        CustomValid[] value();
    }
}
