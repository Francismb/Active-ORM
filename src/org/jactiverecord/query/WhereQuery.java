package org.jactiverecord.query;

import org.jactiverecord.mapping.ActiveRecord;

/**
 * Created by Francis on 1/05/16.
 * Project Jactive-Record.
 */
public class WhereQuery<T extends ActiveRecord> extends Query<T> {

    protected final String column;
    protected String operator;
    protected Object value;

    protected WhereQuery(final String column, final Query<T> query) {
        super(query);
        this.column = column;
    }

    public Query<T> equalTo(final Object value) {
        this.operator = "=";
        this.value = value;
        return this;
    }

    public Query<T> greaterThan(final Object value) {
        this.operator = ">";
        this.value = value;
        return this;
    }

    public Query<T> lesserThan(final Object value) {
        this.operator = "<";
        this.value = value;
        return this;
    }

}
