package org.activerecord;

import org.jactiverecord.orm.ActiveRecord;
import org.jactiverecord.orm.annotations.Column;

/**
 * Created by Francis on 10/04/16.
 * Project Jactive-Record.
 */
public class UserModel extends ActiveRecord {

    @Column(name = "username")
    public String name;
}
