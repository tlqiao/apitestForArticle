package tl86.crown.util.login

import tl86.crown.util.CrownFileService

class LoginService {
    CrownFileService crownFileService

    LoginService() {
        crownFileService = new CrownFileService()
    }

    def getAllUserInfo() {
        crownFileService.getCsvFileContent("src/test/resources/tl86/crown/data/user.csv", ",")
    }

    def getAllRoleMenuInfo() {
        crownFileService.getCsvFileContent("src/test/resources/tl86/crown/data/role.csv",";")
    }

    def getUserInfoByRole(roleName) {
        getAllUserInfo().find { it -> it.roleName == roleName }
    }

    def getUserInfoByUserName(userName) {
        getAllUserInfo().find {it -> it.userName ==userName}
    }

    def convertPasswordOfUser(def userName) {

    }
}
