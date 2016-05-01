package org.activerecord;

import org.jactiverecord.mapping.ActiveRecord;
import org.jactiverecord.mapping.annotations.Column;
import org.jactiverecord.mapping.annotations.PrimaryKey;
import org.jactiverecord.mapping.annotations.Table;

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
