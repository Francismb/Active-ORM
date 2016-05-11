package org.activeorm.query;

import org.activeorm.ActiveRecord;

/**
 * Created by Francis on 1/05/16.
 * Project Active-ORM.
 */
public class OrderQuery<T extends ActiveRecord> {

    /**
     * The column name used in the order statement.
     */
    protected final String column;

    /**
     * The direction to order the results in.
     */
    protected String direction;

    /**
     * The query to return once you have finished
     * building the where query.
     */
    private final Query<T> query;

    /**
     * Constructs a new {@link OrderQuery}.
     *
     * @param column The column name used in the order statement.
     * @param query the query that this {@link WhereQuery} returns.
     */
    protected OrderQuery(final String column, final Query<T> query) {
        this.column = column;
        this.query = query;
    }

    /**
     * Sets the direction to order the results in to ascending.
     * @return the {@link Query}
     */
    public Query<T> ascending() {
        this.direction = "ASC";
        return query;
    }

    /**
     * Sets the direction to order the results in to descending.
     * @return the {@link Query}
     */
    public Query<T> descending() {
        this.direction = "DESC";
        return query;
    }
}
