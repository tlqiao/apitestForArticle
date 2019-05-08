package tl86.fourCourse.db

class DataRepository extends DataSource{
    def getUserInfo() {
        def userInfo = springBootDB.rows(ConstantSql.getUserInfo)
        userInfo ? userInfo : ''
    }

    def getAddressByUserName(userName) {
        def address = springBootDB.firstRow(ConstantSql.getAddressInfoByUserName, [userName])
        address ? address : ''
    }

    def addUser(userName,age) {
        springBootDB.execute(ConstantSql.addUser,[userName,age])
    }

    def getUser(userName) {
        springBootDB.firstRow(ConstantSql.getUser,[userName])
    }

    def updateAddress(userName,age) {
        springBootDB.execute(ConstantSql.updateAge,[age,userName])
    }
}
