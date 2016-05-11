package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.query.Query;

import java.util.List;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class HasMany<T extends ActiveRecord> extends Relationship<T> {

    protected HasMany(Class owner) {
        super(owner);
    }

    public Query<T> query() {
        return Query.build(type);
    }

    public List<T> results() {
        return Query.build(type).results();
    }
}

