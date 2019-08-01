package com.github.crown.scenario.userManage.testCase

import org.junit.experimental.categories.Category
import spock.lang.Specification
import com.github.crown.scenario.userManage.client.UserClient
import com.github.crown.scenario.userManage.service.UserService
import com.github.crown.testsuite.CrownTest

import static org.assertj.core.api.Assertions.assertThat
@Category([CrownTest])
class SearchUserTest extends Specification {
    UserClient userClient
    UserService userService

    def setup() {
        userClient = new UserClient()
        userService = new UserService()
    }

    def "should search user successfully"() {
        given: "no given"
        when: "search user"
        def response = userClient.searchUser(searchUserRoleName, loginName, nickName, status)
        then: "should get all valid user"
        assertThat(response.result.total).isEqualTo(userService.getUserNumbersForSearch(loginName, nickName, status))
        where:
        searchUserRoleName | loginName | nickName | status
        "systemManager"    | "name"    | ""       | ""
        "systemManager"    | ""        | "abc"    | ""
        "systemManager"    | ""        | ""       | 0
        "systemManager"    | ""        | ""       | 1
        "systemManager"    | "crown"   | "crown"  | 0
        "systemManager"    | ""        | ""       | ""
    }

    def "should search user with limit successfully"() {
        given: "no given"
        when: "search user with limit 20"
        def response = userClient.searchUserWithLimit(searchUserRoleName,limit)
        then: "should get valid user"
        assertThat(response.result.size).isEqualTo(limit)
        where:
        searchUserRoleName|limit
        "systemManager"|20
    }
}
