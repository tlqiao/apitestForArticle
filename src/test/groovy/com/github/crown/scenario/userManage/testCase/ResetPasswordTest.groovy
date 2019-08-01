package com.github.crown.scenario.userManage.testCase

import org.junit.experimental.categories.Category
import spock.lang.Specification
import com.github.crown.scenario.userManage.client.UserClient
import com.github.crown.scenario.userManage.repository.UserRepository
import com.github.crown.scenario.userManage.service.UserService
import com.github.crown.testsuite.CrownTest

import static org.assertj.core.api.Assertions.assertThat

@Category([CrownTest])
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
        assertThat(userRepository.getUserInfoByUserName(loginName).password).isEqualTo(userService.getEncryptPassword(loginName, loginName))
        where:
        password | resetPasswordRoleName
        "abc456" | "systemManager"
        "abc456" | "userManager"
        "abc456" | "roleManager"
    }
}
