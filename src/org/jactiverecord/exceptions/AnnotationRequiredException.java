package org.jactiverecord.exceptions;

/**
 * Created by Francis on 12/04/16.
 * Project Jactive-Record.
 *
 * A exception which is thrown when an annotation is required.
 */
public class AnnotationRequiredException extends RuntimeException {
    public AnnotationRequiredException(final String message) {
        super(message);
    }
}
