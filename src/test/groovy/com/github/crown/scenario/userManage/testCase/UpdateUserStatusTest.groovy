package com.github.crown.scenario.userManage.testCase

import org.junit.experimental.categories.Category
import spock.lang.Specification
import com.github.crown.scenario.userManage.client.UserClient
import com.github.crown.scenario.userManage.repository.UserRepository
import com.github.crown.scenario.userManage.service.UserService
import com.github.crown.testsuite.CrownTest
import static org.assertj.core.api.Assertions.assertThat

@Category([CrownTest])
class UpdateUserStatusTest extends Specification {
    UserClient userClient
    UserService userService
    UserRepository userRepository
    def testUserLoginName = "statusTest"

    def setup() {
        userClient = new UserClient()
        userService = new UserService()
        userRepository = new UserRepository()
    }

    def clean() {
        userService.deleteUser(testUserLoginName)
    }

    def "should update user status successfully"() {
        given: "add a test user"
        userService.addUser(testUserLoginName)
        when: "update user status"
        def userInfo = userRepository.getUserInfoByUserName(testUserLoginName)
        userClient.updateUserStatus(updateStatusUserRoleName, userInfo.uid, userInfo.status)
        then: "should update user status successfully"
        assertThat(userInfo.status).isNotEqualTo(userRepository.getUserInfoByUserName(testUserLoginName).status)
        where:
        updateStatusUserRoleName | placeHolder
        "systemManager"          | ""
    }
}
