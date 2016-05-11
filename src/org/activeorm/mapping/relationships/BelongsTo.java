package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class BelongsTo<T extends ActiveRecord> extends Relationship<T> {

    protected BelongsTo(Class owner) {
        super(owner);
    }

    public T get() {
        return null;
    }

}
