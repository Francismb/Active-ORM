package org.activeorm.exceptions;

/**
 * Created by Francis on 5/12/2016.
 * Project Active-ORM
 *
 * This exception is thrown when a {@link org.activeorm.ActiveRecord}
 * was unable to saved.
 */
public class UnableToSaveException extends RuntimeException {
    public UnableToSaveException(final String message) {
        super(message);
    }
}
