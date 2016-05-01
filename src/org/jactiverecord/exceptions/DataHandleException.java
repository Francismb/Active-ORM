package org.jactiverecord.exceptions;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class DataHandleException extends RuntimeException {
    public DataHandleException(final int index, final String type) {
        super("Could not handle " + type + " at index " + index);
    }
}
