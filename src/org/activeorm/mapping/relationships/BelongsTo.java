package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class BelongsTo<T extends ActiveRecord> extends Relationship<T> {

    private T cache;

    public BelongsTo(final AttributeMapping attribute) {
        super(attribute);
    }

    public T get() {
        if (cache != null) {
            return cache;
        }
        return null;
    }

    public boolean save() {
        if (cache == null) {
            return false;
        }
        return cache.save();
    }

    public boolean bind() {
        return false;
    }

}
