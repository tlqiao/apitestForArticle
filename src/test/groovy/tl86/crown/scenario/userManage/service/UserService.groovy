package tl86.crown.scenario.userManage.service

import tl86.crown.scenario.userManage.repository.UserRepository

class UserService {
    UserRepository userRepository

    UserService() {
        userRepository = new UserRepository()
    }
    boolean ifUserExist(def loginName) {
        userRepository.getUserInfoByUserName(loginName) ? true : false
    }

    void addDefaultUser(def loginName){
        if(!ifUserExist(loginName)) {
            userRepository.insertSysUserTable(loginName,loginName,loginName,"test@163.com","18181971234",0)
            userRepository.insertSysUserRoleTable(userRepository.getUserInfoByUserName(loginName).uid,userRepository.getRoleInfoByRoleName('sysManage'))
        }
    }
    def generateUniqueLoginName() {
        boolean  ifContinue
        def loginName
        ifContinue=true
        while(ifContinue) {
            loginName="name"+new Random().nextInt()
            if(!ifUserExist(loginName)) {
               ifContinue=false
            }
        }
        loginName
    }
}
