package org.jactiverecord.query;

import org.jactiverecord.mapping.ActiveRecord;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class OrderQuery<T extends ActiveRecord> extends Query<T> {

    protected final String column;
    protected String direction;

    protected OrderQuery(final String column, final Query<T> query) {
        super(query);
        this.column = column;
    }

    public Query<T> ascending() {
        this.direction = "ASC";
        return this;
    }

    public Query<T> descending() {
        this.direction = "DESC";
        return this;
    }
}
