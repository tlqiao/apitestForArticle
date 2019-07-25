package tl86.crown.testdata.service

import org.apache.commons.codec.digest.Md5Crypt
import tl86.crown.scenario.userManage.repository.UserRepository
import tl86.crown.scenario.userManage.service.UserService
import tl86.crown.util.login.LoginService

class InitGlobalUserService {
    UserRepository userRepository
    UserService userService
    LoginService globalService

    InitGlobalUserService() {
        userRepository = new UserRepository()
        userService = new UserService()
        globalService = new LoginService()
    }

    private void addGlobalUsers() {
        def allUserInfo = globalService.getAllUserInfo()
        allUserInfo.each {
            if (!userService.ifUserExist(it.userName)) {
                userRepository.insertSysUserTable(it.userName, it.userName, Md5Crypt.apr1Crypt(it.userName, it.userName), "test@163.com", "18181971234", 0)
                userRepository.insertSysUserRoleTable(userRepository.getUserInfoByUserName(it.userName).uid,userRepository.getRoleInfoByRoleName(it.roleName).id)
            }
        }
    }

    private void initGlobalRole() {
        def allRoleMenuInfo = globalService.getAllRoleMenuInfo()
        allRoleMenuInfo.each {
            if (!ifRoleExist(it.roleName)) {
                userRepository.insertSysRoleTable(it.roleName)
            }
        }
    }

    private boolean ifRoleExist(roleName) {
        userRepository.getRoleInfoByRoleName(roleName) ? true : false
    }

    private void initRoleMenuId() {
        def allRoleMenuInfo = globalService.getAllRoleMenuInfo()
        def roleId
        allRoleMenuInfo.each {
            roleId = userRepository.getRoleInfoByRoleName(it.roleName).id
            parseMenuIdList(it.menuIdList).each { menuId -> menuId
                if (!ifRoleIdMenuIdExist(roleId, menuId)) {
                    userRepository.insertSysRoleMenuTable(roleId, menuId)
                }
            }
        }
    }

    private def parseMenuIdList(def menuIdListString) {
       def parseString=menuIdListString.substring(1,menuIdListString.size()-1)
        parseString.split(",")
    }

    private boolean ifRoleIdMenuIdExist(roleId, menuId) {
        userRepository.getRoleMenuRel(roleId, menuId) ? true : false
    }

    void initGlobalUser() {
        initGlobalRole()
        initRoleMenuId()
        addGlobalUsers()
    }
}
