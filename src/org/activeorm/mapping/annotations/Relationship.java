package org.activeorm.mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Relationship {

    public Type relationship();

    public enum Type {
        BELONGS_TO, HAS_ONE, HAS_MANY
    }
}
