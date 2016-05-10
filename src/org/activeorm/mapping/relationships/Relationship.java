package org.activeorm.mapping.relationships;

import org.activeorm.mapping.ActiveRecord;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class Relationship<T extends ActiveRecord> {

    protected final Class<T> type;

    protected Relationship() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
