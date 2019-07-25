package tl86.crown.testdata

import org.junit.Test
import tl86.crown.testdata.service.InitGlobalUserService

class InitTestData {
    InitGlobalUserService initGlobalUserService

    InitTestData() {
        initGlobalUserService = new InitGlobalUserService()
    }

    @Test()
    void InitGlobalTestData() {
        initGlobalUserService.initGlobalUser()
    }
}
