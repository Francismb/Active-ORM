package org.jactiverecord.database.sql.adapters;

/**
 * Created by Francis on 13/04/16.
 * Project Jactive-Record.
 */
public class ConditionAdapter {

    protected final String column;
    protected final String operator;
    protected final String value;

    public ConditionAdapter(final String column, final String operator, final String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }
}
