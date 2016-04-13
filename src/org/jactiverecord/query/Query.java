package org.jactiverecord.query;

import org.jactiverecord.ActiveRecord;

/**
 * Created by Francis on 10/04/16.
 * Project Jactive-Record.
 */
public class Query<T extends ActiveRecord> {

    private final Class<T> activeRecord;

    private Query(final Class<T> activeRecord) {
        this.activeRecord = activeRecord;
    }

    public static <T extends ActiveRecord> Query<T> build(final Class<T> activeRecord) {
        return new Query<T>(activeRecord);
    }

    public T first() {
        return null;
    }
}
