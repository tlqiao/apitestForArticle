package tl86.crown.util.login

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import tl86.crown.util.ConfigParse

import static io.restassured.RestAssured.given

class LoginClient {
    LoginService globalService
    ConfigParse configParse

    LoginClient() {
        globalService = new LoginService()
        configParse = new ConfigParse()
    }
    def clientWithSpecialRole(roleName) {
        def userInfo = globalService.getUserInfoByRole(roleName)
                setDefaultUriAndContentType()
                .header("Authorization", getTokenByUser(userInfo.userName, userInfo.userName))
    }

    private def getTokenByUser(userName, password) {
        HashMap userMap = new HashMap()
        userMap.put("loginName", userName)
        userMap.put("password", password)
                def token =setDefaultUriAndContentType()
                .body(userMap)
                .post("/account/token")
                .then().assertThat().statusCode(200)
                .extract().response().path("result.token")
        token="Bearer " + token
        token
    }

    private def setDefaultUriAndContentType(contentType = "application/json") {
        given().baseUri((String)configParse.getGlobalConfig().baseUrl)
                .header("Content-Type", contentType)
    }
}
