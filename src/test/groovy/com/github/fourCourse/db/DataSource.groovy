package com.github.fourCourse.db

import groovy.sql.Sql

class DataSource {
    Sql sql
    Sql getSql() {
        if (!sql) {
            def mysqlDB = [
                    driver  : 'com.mysql.jdbc.Driver',
                    url     : 'jdbc:mysql://127.0.0.1:3306/apitestdb',
                    user    : 'root',
                    password: 'root12345'
            ]
            sql = Sql.newInstance(mysqlDB.url, mysqlDB.user, mysqlDB.password, mysqlDB.driver)
        }
        sql
    }
}
