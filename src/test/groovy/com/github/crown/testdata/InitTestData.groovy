package com.github.crown.testdata

import org.junit.Test

class InitTestData {
    com.github.crown.testdata.service.GlobalUserService globalUserService

    InitTestData() {
       globalUserService = new com.github.crown.testdata.service.GlobalUserService()
    }

    @Test()
    void InitGlobalTestData() {
        globalUserService.initGlobalUser()
    }
}
