package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;
import org.activeorm.query.Query;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class HasOne<T extends ActiveRecord> extends Relationship<T> {

    /**
     * The cached value of this relationship.
     */
    private T value;

    /**
     * Constructs a new {@link HasOne}.
     *
     * @param attribute the
     */
    public HasOne(final AttributeMapping attribute) {
        super(attribute);
    }

    /**
     * Gets the value of this relationship.
     *
     * @return the ActiveRecord that is associated with this relationship.
     */
    public T get() {
        if (value != null) {
            return value;
        }
        return this.value = Query.build(type).where(attribute.column.name()).equalTo(attribute.field.getValue()).first();
    }

    /**
     * Sets the value of this relationship.
     *
     * @param value the value of this relationship.
     */
    public void set(final T value) {
        this.value = value;
    }

    @Override
    public boolean save() {
        if (value == null) {
            return false;
        }
        return value.save();
    }

    @Override
    public boolean destroy() {
        if (value == null) {
            value = get();
        }
        return value.destroy();
    }

    @Override
    public boolean destroyAll() {
        if (value == null) {
            value = get();
        }
        return value.destroyAll();
    }

}

