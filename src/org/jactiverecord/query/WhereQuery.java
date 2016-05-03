package org.jactiverecord.query;

import org.jactiverecord.mapping.ActiveRecord;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class WhereQuery<T extends ActiveRecord> {

    /**
     * The column name used in the where statement.
     */
    protected final String column;

    /**
     * The operator used in the where statement.
     */
    protected String operator;

    /**
     * The value which is placed on the right side
     * of the where statement.
     */
    protected Object value;

    /**
     * The query to return once you have finished
     * building the where query.
     */
    private final Query<T> query;

    /**
     * Constructs a new {@link WhereQuery}.
     *
     * @param column the column name used in the where statement.
     * @param query the query that this {@link WhereQuery} returns.
     */
    protected WhereQuery(final String column, final Query<T> query) {
        this.column = column;
        this.query = query;
    }

    /**
     * @param value the value for this where statement to be equal to.
     * @return the {@link Query} object.
     */
    public Query<T> equalTo(final Object value) {
        this.operator = "=";
        this.value = value;
        return query;
    }

    /**
     * @param value the value for this where statement to be greater than.
     * @return the {@link Query} object.
     */
    public Query<T> greaterThan(final Object value) {
        this.operator = ">";
        this.value = value;
        return query;
    }

    /**
     * @param value the value for this where statement to be lesser than.
     * @return the {@link Query} object.
     */
    public Query<T> lesserThan(final Object value) {
        this.operator = "<";
        this.value = value;
        return query;
    }

}
