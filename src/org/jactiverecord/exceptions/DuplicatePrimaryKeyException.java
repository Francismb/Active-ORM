package org.jactiverecord.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 *
 * A exception which is thown when there are multiple {@link org.jactiverecord.mapping.annotations.PrimaryKey} fields.
 */
public class DuplicatePrimaryKeyException extends RuntimeException {
    public DuplicatePrimaryKeyException(final String message) {
        super(message);
    }
}
