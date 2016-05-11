package org.activeorm.mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Francis on 9/04/16.
 * Project Jactive-Record.
 *
 * If the {@link PrimaryKey} annotation is present on
 * a field it determines that the field is the primary key.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
}
