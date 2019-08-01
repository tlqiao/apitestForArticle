package com.github.crown.util

import groovy.sql.Sql

class CrownDataSource {
    Sql crownDB
    ConfigParse configParse
    def configs

    CrownDataSource() {
        configParse = new ConfigParse()
        configs = configParse.getGlobalConfig()
    }

    Sql getCrownDB() {
        if (!crownDB) {
            def mysqlDB = [
                    driver  : 'com.mysql.jdbc.Driver',
                    url     : configs.local.db.url,
                    user    : configs.local.db.user,
                    password: configs.local.db.password
            ]
            crownDB = Sql.newInstance(mysqlDB.url, mysqlDB.user, mysqlDB.password, mysqlDB.driver)
        }
        crownDB
    }
}

