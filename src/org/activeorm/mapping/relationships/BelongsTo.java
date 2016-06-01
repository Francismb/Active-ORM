package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public class BelongsTo<T extends ActiveRecord> extends Relationship<T> {

    /**
     * The cached value of this relationship.
     */
    private T value;

    /**
     * Constructs a new {@link BelongsTo}.
     *
     * @param attribute
     */
    public BelongsTo(final AttributeMapping attribute) {
        super(attribute);
    }

    /**
     * Gets the value of this relationship.
     *
     * @return the value of this relationship.
     */
    public T get() {
        if (value != null) {
            return value;
        }
        return null;
    }

    public void set(final T value) {
        attribute.field.setValue(value.getId());
        this.value = value;
    }

    public boolean save() {
        if (value == null) {
            return false;
        }
        return value.save();
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    public boolean destroyAll() {
        return false;
    }

}
