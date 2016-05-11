package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class Relationship<T extends ActiveRecord> {

    /**
     * The ActiveRecord that this relationship links to.
     */
    public final Class<T> type;

    /**
     * The {@link AttributeMapping} of this {@link Relationship}
     */
    public final AttributeMapping attribute;

    protected Relationship(final AttributeMapping attribute) {
        this.attribute = attribute;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
