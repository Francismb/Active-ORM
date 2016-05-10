package org.activeorm;

import org.activeorm.mapping.ActiveRecord;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Table;
import org.activeorm.mapping.relationships.BelongsTo;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
@Table(name = "pies")
public class Pie extends ActiveRecord {

    @PrimaryKey
    @Column(name = "id")
    public int id;

    @Column(name = "user_id")
    public BelongsTo<User> user;
}
