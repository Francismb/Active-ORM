package org.activeorm.mapping.relationships;

import org.activeorm.mapping.ActiveRecord;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class HasOne<T extends ActiveRecord> extends Relationship<T> {

    public T get() {
        return null;
    }

}

