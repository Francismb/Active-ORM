package org.jactiverecord.database.sql.expressions;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * {@link WhereExpression} contains the relevant information for an ORDER sql expression
 */
public class OrderExpression {

    /**
     * The column to order by
     */
    protected final String column;

    /**
     * The directory to order in ASC or DESC
     */
    protected final String direction;

    /**
     * Constructs a new {@link OrderExpression}
     * @param column the column to order by
     * @param direction the direct to order, if null defaults to ASC
     */
    public OrderExpression(final String column, final String direction) {
        this.column = column;
        this.direction = direction == null ? "ASC" : direction;
    }
}
