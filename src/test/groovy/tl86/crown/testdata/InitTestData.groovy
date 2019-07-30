package tl86.crown.testdata

import org.junit.Test
import tl86.crown.testdata.service.GlobalUserService

class InitTestData {
    GlobalUserService initGlobalUserService

    InitTestData() {
        initGlobalUserService = new GlobalUserService()
    }

    @Test()
    void InitGlobalTestData() {
        initGlobalUserService.initGlobalUser()
    }
}
