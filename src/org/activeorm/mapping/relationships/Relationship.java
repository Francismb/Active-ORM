package org.activeorm.mapping.relationships;

import org.activeorm.ActiveRecord;
import org.activeorm.mapping.AttributeMapping;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
public abstract class Relationship<T extends ActiveRecord> {

    /**
     * The ActiveRecord that this relationship links to,
     * in other words the type of result this relationship returns.
     */
    public final Class<T> type;

    /**
     * The {@link AttributeMapping} of this {@link Relationship}
     */
    public final AttributeMapping attribute;

    /**
     * Constructs a new {@link Relationship}
     *
     * @param attribute the attribute that the relationship is bound to.
     */
    protected Relationship(final AttributeMapping attribute) {
        this.attribute = attribute;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Saves the active records associated with this relationship.
     *
     * @return true if successfully saved else false.
     */
    public abstract boolean save();

    /**
     * Destroys the active record associated with this relationship.
     *
     * @return true if successfully destroyed else false.
     */
    public abstract boolean destroy();

    /**
     * Destroys the active record associated with this relationship
     * and all relationships associated with that active record.
     *
     * @return true if successfully destroyed all else false.
     */
    public abstract boolean destroyAll();

    /**
     * Set the field value to this object.
     */
    public void bind() {
        attribute.field.setValue(this);
    }
}
