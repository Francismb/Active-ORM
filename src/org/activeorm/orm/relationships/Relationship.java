package org.activeorm.orm.relationships;

import org.activeorm.orm.ActiveRecord;
import org.activeorm.orm.annotations.Column;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class Relationship<T extends ActiveRecord> {

    protected final Class<T> type;
    protected final Class owner;
    //protected final Column column;

    protected Relationship(final Class owner) {
        this.owner = owner;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
