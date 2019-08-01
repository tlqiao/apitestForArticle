package tl86.crown.scenario.userManage.testCase

import org.junit.Assert
import org.junit.experimental.categories.Category
import spock.lang.Specification
import tl86.crown.scenario.userManage.client.UserClient
import tl86.crown.scenario.userManage.repository.UserRepository
import tl86.crown.scenario.userManage.requestBody.UserBody
import tl86.crown.scenario.userManage.service.UserService
import tl86.crown.testsuite.CrownTest

@Category([CrownTest])
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
        userService.deleteUser(loginName)
    }

    def "should add user successfully when filling all required information"() {
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        given: "generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(201)
        then: "should add user in db successfully"
        Assert.assertTrue(userService.ifUserExist(loginName))
        where:
        addUserRoleName | addedUserRoleName
        "systemManager" | "roleManager"
        "userManager"   | "roleManager"
        "roleManager"   | "roleManager"
    }

    def "should add user failed when not filling all required information"() {
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        given: "generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName(nickName)
                .setUerPhone(phone)
                .setUserEmail(email)
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        nickName   | phone         | email          | addedUserRoleName | addUserRoleName
        ""         | "18181971234" | "test@163.com" | "systemManager"   | "systemManager"
        "nickName" | ""            | "test@163.com" | "systemManager"   | "systemManager"
//        "nickName" | "18181971234" | ""             | "systemManager"  | "systemManager"

    }

    def "should add user failed when not filling roleList"() {
        given: "generate add user api request body"
        loginName = ""
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList("")
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        addUserRoleName | placeHolder
        "systemManager" | ""
    }

    def "should add user failed when not filling loginName"() {
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        given: "generate add user api request body"
        loginName = ""
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        addUserRoleName | addedUserRoleName
        "systemManager" | "roleManager"
    }

    def "should add user failed with exist user account"() {
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        given: "generate add user api request body"
        loginName = "userForTest"
        userService.addUser(loginName)
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUerPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(400)
        then: "no user added"
        where:
        addUserRoleName | addedUserRoleName
        "systemManager" | "systemManager"
    }
}
