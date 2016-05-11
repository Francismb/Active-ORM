package org.activeorm.orm.relationships;

import org.activeorm.orm.ActiveRecord;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class HasOne<T extends ActiveRecord> extends Relationship<T> {

    protected HasOne(Class owner) {
        super(owner);
    }

    public T get() {
        return null;
    }

}

