package com.github.fourCourse.db

class DataRepository extends DataSourceNew{
    def getUserInfo() {
        def userInfo = sql.rows(ConstantSql.getUserInfo)
        userInfo ? userInfo : ''
    }

    def getAddressByUserName(userName) {
        def address = sql.firstRow(ConstantSql.getAddressInfoByUserName, [userName])
        address ? address : ''
    }

    def addUser(userName,age) {
        sql.execute(ConstantSql.addUser,[userName,age])
    }

    def getUser(userName) {
        sql.firstRow(ConstantSql.getUser,[userName])
    }

    def updateAddress(userName,age) {
        sql.execute(ConstantSql.updateAge,[age,userName])
    }
}
