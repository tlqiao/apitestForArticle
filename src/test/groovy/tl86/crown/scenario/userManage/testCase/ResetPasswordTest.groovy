package tl86.crown.scenario.userManage.testCase

import spock.lang.Specification
import tl86.crown.scenario.userManage.client.UserClient
import tl86.crown.scenario.userManage.repository.UserRepository
import tl86.crown.scenario.userManage.service.UserService
import tl86.crown.testsuite.CrownTest

import static org.assertj.core.api.Assertions.assertThat

@Category(CrownTest)
class ResetPasswordTest extends Specification {
    UserService userService
    UserClient userClient
    UserRepository userRepository
    def loginName = "userForReset"

    def setup() {
        userService = new UserService()
        userClient = new UserClient()
        userRepository = new UserRepository()
    }

    def cleanup() {
        userService.deleteUser(loginName)
    }

    def "should reset user password successfully"() {
        given: "create a user with password"
        userService.addUser(loginName, password)
        when: "reset user password"
        def uid = userRepository.getUserInfoByUserName(loginName).uid
        userClient.resetUserPassword(resetPasswordRoleName, uid)
        then: "should user's password is equal to loginName"
        assertThat(userRepository.getUserInfoByUserName(loginName).password).isEqualTo(userService.getEncytptPassword(loginName, loginName))
        where:
        password | resetPasswordRoleName
        "abc456" | "systemManager"
    }
}
