package github.com.crown.testdata.service

import github.com.crown.scenario.userManage.repository.UserRepository
import github.com.crown.scenario.userManage.service.UserService
import github.com.crown.util.CrownFileService

class GlobalUserService {
    UserRepository userRepository
    UserService userService
    CrownFileService crownFileService

    GlobalUserService() {
        userRepository = new UserRepository()
        userService = new UserService()
        crownFileService = new CrownFileService()
    }

    private def getAllUserInfo() {
        crownFileService.getCsvFileContent("src/test/resources/github/crown/data/user.csv", ",")
    }

    private def getAllRoleMenuInfo() {
        crownFileService.getCsvFileContent("src/test/resources/github/crown/data/role.csv", ";")
    }

    def getUserInfoByRole(roleName) {
        getAllUserInfo().find { it -> it.roleName == roleName }
    }

    private void addGlobalUsers() {
        def allUserInfo = getAllUserInfo()
        allUserInfo.each {
            userService.addUser(it.userName, it.password, it.roleName)
        }
    }

    private void initGlobalRole() {
        def allRoleMenuInfo = getAllRoleMenuInfo()
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
        def allRoleMenuInfo = getAllRoleMenuInfo()
        def roleId
        allRoleMenuInfo.each {
            roleId = userRepository.getRoleInfoByRoleName(it.roleName).id
            parseMenuIdList(it.menuIdList).each { menuId ->
                menuId
                if (!ifRoleIdMenuIdExist(roleId, menuId)) {
                    userRepository.insertSysRoleMenuTable(roleId, menuId)
                }
            }
        }
    }

    private def parseMenuIdList(def menuIdListString) {
        def parseString = menuIdListString.substring(1, menuIdListString.size() - 1)
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
