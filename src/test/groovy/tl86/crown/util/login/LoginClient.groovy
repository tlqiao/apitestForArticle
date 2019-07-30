package tl86.crown.util.login

import tl86.crown.testdata.service.GlobalUserService
import tl86.crown.util.ConfigParse

import static io.restassured.RestAssured.given

class LoginClient {
    GlobalUserService globalUserService
    ConfigParse configParse

    LoginClient() {
        globalUserService = new GlobalUserService()
        configParse = new ConfigParse()
    }

    def clientWithSpecialRole(roleName) {
        def userInfo = globalUserService.getUserInfoByRole(roleName)
        setDefaultUriAndContentType()
                .header("Authorization", getTokenByUser(userInfo.userName, userInfo.password))
    }

    private def getTokenByUser(userName, password) {
        HashMap userMap = new HashMap()
        userMap.put("loginName", userName)
        userMap.put("password", password)
        def token = setDefaultUriAndContentType()
                .body(userMap)
                .post("/account/token")
                .then().assertThat().statusCode(200)
                .extract().response().path("result.token")
        token = "Bearer " + token
        token
    }

    private def setDefaultUriAndContentType(contentType = "application/json") {
        given().baseUri((String) configParse.getGlobalConfig().baseUrl)
                .header("Content-Type", contentType)
    }
}
