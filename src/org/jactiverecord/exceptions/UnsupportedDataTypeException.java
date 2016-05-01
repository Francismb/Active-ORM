package org.jactiverecord.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class UnsupportedDataTypeException extends RuntimeException {
    public UnsupportedDataTypeException(final String datatype) {
        super("Unsupported data type " + datatype);
    }
}
