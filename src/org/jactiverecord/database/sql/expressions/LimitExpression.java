package org.jactiverecord.database.sql.expressions;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * {@link LimitExpression} contains the relevant information for an LIMIT sql expression
 */
public class LimitExpression {

    /**
     * The limit of results
     */
    public final int limit;

    /**
     * Constructs a new {@link LimitExpression}
     * @param limit the limit of results
     */
    public LimitExpression(final int limit) {
        this.limit = limit;
    }

}
