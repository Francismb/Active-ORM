package org.activeorm;

import org.activeorm.mapping.ActiveRecord;
import org.activeorm.mapping.annotations.Column;
import org.activeorm.mapping.annotations.PrimaryKey;
import org.activeorm.mapping.annotations.Table;

/**
 * Created by Francis on 10/04/16.
 * Project Jactive-Record.
 */
@Table(name = "users")
public class User extends ActiveRecord {

    @PrimaryKey
    @Column(name = "user_id")
    public int id;

    @Column(name = "username")
    public String name;

    @Column(name = "password")
    public String password;

}
