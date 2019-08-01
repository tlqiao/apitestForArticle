package github.com.crown.testdata

import org.junit.Test
import github.com.crown.testdata.service.GlobalUserService

class InitTestData {
    GlobalUserService globalUserService

    InitTestData() {
       globalUserService = new GlobalUserService()
    }

    @Test()
    void InitGlobalTestData() {
        globalUserService.initGlobalUser()
    }
}
