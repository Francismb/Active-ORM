package org.activeorm;

import org.activeorm.orm.ActiveRecord;
import org.activeorm.orm.annotations.Column;
import org.activeorm.orm.annotations.PrimaryKey;
import org.activeorm.orm.annotations.Table;
import org.activeorm.orm.relationships.BelongsTo;

/**
 * Created by Francis on 11/05/16.
 * Project Active-ORM.
 */
@Table(name = "pies")
public class Pie extends ActiveRecord {

    @PrimaryKey
    @Column(name = "id")
    public int id;

    //@Column(name = "user_id")
    //public BelongsTo<User> user;

}
