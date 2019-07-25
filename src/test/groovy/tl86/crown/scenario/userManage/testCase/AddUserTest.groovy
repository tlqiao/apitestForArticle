package tl86.crown.scenario.userManage.testCase

import org.junit.Assert
import spock.lang.Specification
import tl86.crown.scenario.userManage.client.UserClient
import tl86.crown.scenario.userManage.repository.UserRepository
import tl86.crown.scenario.userManage.requestBody.AddUserBody
import tl86.crown.scenario.userManage.service.UserService

class AddUserTest extends Specification {
    UserClient userClient
    UserService userService
    UserRepository userRepository
    def loginName = ""

    def setup() {
        userClient = new UserClient()
        userService = new UserService()
        userRepository = new UserRepository()
    }

    def cleanup() {
        userRepository.deleteUserByUserName(loginName)
    }

    def "should add user successfully when filling all required information"() {
        given: "generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        def body = new AddUserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUserTwo(roleName, body)
                .statusCode(201)
        then: "should add user in db successfully"
        Assert.assertTrue(userService.ifUserExist(loginName))
        where:
        roleName       | roleIdList
        "systemManager"| ["8"]
        "userManager"| ["8"]
        "roleManager"| ["8"]
    }

    def "should add user failed when not filling all required information"() {
        given: "generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        def body = new AddUserBody()
                .setUserLoginName(loginName)
                .setUserNickName(nickName)
                .setUerPhone(phone)
                .setUserEmail(email)
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUserTwo(roleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        nickName   | phone         | email          | roleIdList | roleName
        ""         | "18181971234" | "test@163.com" | ["8"]      | "systemManager"
        "nickName" | ""            | "test@163.com" | ["8"]      | "systemManager"
        "nickName" | "18181971234" | ""             | ["8"]      | "systemManager"
        "nickName" | "18181971234" | "test@163.com" | ""         | "systemManager"
    }
    def "should add user failed when not filling loginName"() {
        given: "generate add user api request body"
        loginName = ""
        def body = new AddUserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUserTwo(roleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        roleName|roleIdList
        "systemManager"|["8"]
    }

    def "should add user failed with exist user account"() {
        given: "generate add user api request body"
        loginName = "userForTest"
        userService.addDefaultUser(loginName)
        def body = new AddUserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUserTwo(roleName, body)
                .statusCode(400)
        then: "no user added"
        where:
        roleName|roleIdList
        "systemManager"|["8"]
    }
}
