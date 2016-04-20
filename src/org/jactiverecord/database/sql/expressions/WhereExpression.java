package org.jactiverecord.database.sql.expressions;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 *
 * {@link WhereExpression} contains the relevant information for an WHERE sql expression
 */
public class WhereExpression {

    /**
     * The column name used on the left side of the expression
     */
    public final String column;

    /**
     * The type of comparator, equal, larger then etc.
     */
    public final String operator;

    /**
     * The parameter which is used on the right side of the expression
     */
    public final String parameter;

    /**
     * Constructs a new {@link WhereExpression}
     * @param column the column name
     * @param operator the operator type
     * @param parameter the parameter
     */
    public WhereExpression(final String column, final String operator, final String parameter) {
        this.column = column;
        this.operator = operator;
        this.parameter = parameter;
    }
}
