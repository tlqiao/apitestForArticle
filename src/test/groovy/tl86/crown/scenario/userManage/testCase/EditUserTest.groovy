package tl86.crown.scenario.userManage.testCase

import spock.lang.Specification
import tl86.crown.scenario.userManage.client.UserClient
import tl86.crown.scenario.userManage.repository.UserRepository
import tl86.crown.scenario.userManage.requestBody.UserBody
import tl86.crown.scenario.userManage.service.UserService
import tl86.crown.testsuite.CrownTest

import static org.assertj.core.api.Assertions.assertThat

@Category(CrownTest)
class EditUserTest extends Specification {
    UserService userService
    UserRepository userRepository
    UserClient userClient
    def loginName="userForEdit"

    def setup() {
        userService = new UserService()
        userRepository = new UserRepository()
        userClient = new UserClient()
    }

    def cleanup() {
        userService.deleteUser(loginName)
    }

    def "should update user details successfully"() {
        given: "create a exist user"
        userService.addUser(loginName,"abc123","systemManager")
        def userInfo=userRepository.getUserInfoByUserName(loginName)
        and: "generate edit user body"
        def reqBody = new UserBody()
                .setUserIdForEditUser(userInfo.uid)
                .setUserLoginName(loginName)
                .setUserNickName(newNickName)
                .setUerPhone(newPhone)
                .setUserEmail(newEmail)
                .setRoleIdList(userService.generateUserRoleList("roleManager"))
                .getAddUserBody()
        when: "edit user details"
        userClient.editUser(updateUserRoleName,userInfo.uid,reqBody)
                  .statusCode(200)
        then: "should edit successfully"
        def newUserInfo=userRepository.getUserInfoByUserName(loginName)
        assertThat(newUserInfo.nickName).isEqualTo(newNickName)
        assertThat(newUserInfo.phone).isEqualTo(newPhone)
        assertThat(newUserInfo.email).isEqualTo(newEmail)
        assertThat(userRepository.getUserRole(loginName).role_id).isEqualTo(userRepository.getRoleInfoByRoleName("roleManager").id)
        where:
        updateUserRoleName|newNickName|newPhone|newEmail
        "systemManager"| "newNickName"|"18181991122"|"newEmail@163.com"
    }
}
