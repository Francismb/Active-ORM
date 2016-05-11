package org.activeorm.mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Francis on 9/04/16.
 * Project Active-ORM.
 *
 * The {@link Column} annotation is required for every
 * field that you want mapped to the database.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    public String name();

}
