package com.github.fourCourse.db

class ConstantSql {
    static final getUserInfo="select * from user"
    static final getAddressInfoByUserName ="select a.username,a.age,b.address from user a,address b where a.id=b.userId and a.username=?"
    static final addUser = "insert into user (username,age,create_date) values (?,?,'1019-10-11:11:12:13')"
    static final getUser="select * from user where username=?"
    static final updateAge="update user set age=? where username=? "
}
