package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class HasOne<T extends ActiveRecord> extends Relationship<T> {

    protected HasOne(final AttributeMapping attribute) {
        super(attribute);
    }

    public T get() {
        return null;
    }

}

