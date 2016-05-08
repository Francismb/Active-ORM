
![alt text](https://raw.githubusercontent.com/frazboyz/Active-ORM/development/logo.png "Logo")

[![Build Status](https://travis-ci.org/frazboyz/Active-ORM.svg?branch=master)](https://travis-ci.org/frazboyz/Active-ORM)

ActiveORM, it is a object relational mapping which follows the active record pattern.

It supports the major databases(SQLite, PostgreSQL, MySQL and H2).
It also provides a query module so you can easily query for objects in an object orientated manner.

## Use
This will be a quick tutorial on how to use ActiveORM, if you run in to any issues while using ActiveORM
feel free to create an issue on this Github page.

#### Configuration
This framework was built to be as simple as possible so the configuration has been kept to a very minimum.<br/>
You will need to create a configuration file for the database you are using(H2Database, SQLite, PostgreSQL, MySQL).<br/>
The configuration file needs to be in a yaml format.<br/>
```yaml
connector: postgre
name: testing
username: development
password: supersecretpasswordthatnooneknows
```
To load the configuration and initialize the Database object simply call
```java
Database.fromYaml("your configuration file here.yml");
```

#### ActiveRecord or Model
These classes are used to create a object which is related to a table in your database.<br/>
This is a small example of a ActiveRecord implementation.<br/>
```java
@Table(name = "users")
public class User extends ActiveRecord {

    @PrimaryKey
    @Column(name = "id")
    public int id;

    @Column(name = "username")
    public String name;

    @Column(name = "password")
    public String password;

}
```

#### Query
The Query object allows you to create a query to get ActiveRecords and Models from the
database easily but also delete them from the database.<br/>
This is a small example of how to use the Query object.<br/>
```java
// Queries the database for the first record that matches
// the conditions and then converts it to a User object
final User user Query.build(User.class).where("username").equalTo("Tom").first();
System.out.printLn(user.name);
```