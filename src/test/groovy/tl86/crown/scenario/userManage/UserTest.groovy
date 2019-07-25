package tl86.crown.scenario.userManage

import org.junit.Test
import tl86.crown.testdata.service.InitGlobalUserService

class UserTest {
    InitGlobalUserService initGlobalUserService

    @Test()
    void initGlobalUserTest() {
        initGlobalUserService = new InitGlobalUserService()
        initGlobalUserService.initGlobalUser()
    }
}
