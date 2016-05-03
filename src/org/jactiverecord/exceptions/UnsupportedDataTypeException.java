package org.jactiverecord.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 *
 * This exception is thrown when the database does not support
 * the data type or you are using a data type that is not supported
 * by the database.
 */
public class UnsupportedDataTypeException extends RuntimeException {
    public UnsupportedDataTypeException(final String datatype) {
        super("Unsupported data type " + datatype);
    }
}
