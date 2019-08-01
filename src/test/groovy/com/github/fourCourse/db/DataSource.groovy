package com.github.fourCourse.db

import groovy.sql.Sql
import com.github.fourCourse.file.FileService

class DataSource {
    Sql springBootDB
    FileService fileService
    def configs

    DataSource() {
        fileService = new FileService()
        configs = fileService.getConfigs('./src/test/resources/github/config/config.yml')
    }

    Sql getSpringBootDB() {
        if (!springBootDB) {
            def mysqlDB = [
                    driver  : 'com.mysql.jdbc.Driver',
                    url     : configs.dev.db.url,
                    user    : configs.dev.db.user,
                    password: configs.dev.db.password
            ]
            springBootDB = Sql.newInstance(mysqlDB.url, mysqlDB.user, mysqlDB.password, mysqlDB.driver)
        }
        springBootDB
    }
}
