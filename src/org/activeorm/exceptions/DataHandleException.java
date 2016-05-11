package org.activeorm.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 *
 * This exception is thrown when a data type cant be taken from
 * a jdbc {@link java.sql.ResultSet} or a data type cant be
 * inserted into a {@link java.sql.Statement}.
 */
public class DataHandleException extends RuntimeException {
    public DataHandleException(final int index, final String type) {
        super("Could not handle " + type + " at index " + index);
    }
}
