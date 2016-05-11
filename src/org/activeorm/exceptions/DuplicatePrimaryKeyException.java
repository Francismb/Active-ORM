package org.activeorm.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 *
 * A exception which is thown when there are multiple {@link org.activeorm.mapping.annotations.PrimaryKey} fields.
 */
public class DuplicatePrimaryKeyException extends RuntimeException {
    public DuplicatePrimaryKeyException(final String message) {
        super(message);
    }
}
