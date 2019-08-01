package com.github.crown.scenario.userManage.service

import org.apache.commons.codec.digest.Md5Crypt
import com.github.crown.scenario.userManage.repository.UserRepository

class UserService {
    UserRepository userRepository

    UserService() {
        userRepository = new UserRepository()
    }

    boolean ifUserExist(def loginName) {
        userRepository.getUserInfoByUserName(loginName) ? true : false
    }

    def getEncryptPassword(password, loginName) {
        Md5Crypt.apr1Crypt(password, loginName)
    }

    void addUser(def loginName, def password = "abc123", def roleName = "systemManager") {
        if (!ifUserExist(loginName)) {
            userRepository.insertSysUserTable(loginName, loginName, getEncryptPassword(password, loginName), "test@163.com", "18181971234", 0)
            userRepository.insertSysUserRoleTable(userRepository.getUserInfoByUserName(loginName).uid, userRepository.getRoleInfoByRoleName(roleName).id)
        }
    }

    void deleteUser(def loginName) {
        userRepository.deleteUserRoleTableByUserName(loginName)
        userRepository.deleteUserTableByUserName(loginName)
    }

    def generateUniqueLoginName() {
        boolean ifContinue
        def loginName
        ifContinue = true
        while (ifContinue) {
            loginName = "name" + new Random().nextInt()
            if (!ifUserExist(loginName)) {
                ifContinue = false
            }
        }
        loginName
    }

    def generateUserRoleList(roleName) {
        def roleList = []
        roleList[0] = (userRepository.getRoleInfoByRoleName(roleName)).id
        roleList
    }

    def getUserNumbersForSearch(loginName = "", nickName = "", status = "") {
        userRepository.searchUserCount(convertSearchKeyWord(loginName), convertSearchKeyWord(nickName), convertSearchKeyWord(status))

    }

    private def convertSearchKeyWord(searchKeyWord) {
        searchKeyWord + '%'
    }
}
