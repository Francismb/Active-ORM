package org.jactiverecord.database.sql.adapters;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 */
public class OrderAdapter {

    protected final String column;
    protected final String direction;

    public OrderAdapter(final String column, final String direction) {
        this.column = column;
        this.direction = direction;
    }
}
