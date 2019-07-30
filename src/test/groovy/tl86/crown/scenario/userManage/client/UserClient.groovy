package tl86.crown.scenario.userManage.client

import groovy.json.JsonSlurper
import tl86.crown.util.login.LoginClient

class UserClient {
    LoginClient globalClient
    JsonSlurper jsonSlurper

    UserClient() {
        globalClient = new LoginClient()
        jsonSlurper = new JsonSlurper()
    }

    def addUser(def addUserRoleName, def body) {
        globalClient.clientWithSpecialRole(addUserRoleName)
                .body(body)
                .when()
                .post('/users')
                .then()
                .assertThat()
    }

    def editUser(def editUserRoleName, def uid, def body) {
        globalClient.clientWithSpecialRole(editUserRoleName)
                .body(body)
                .pathParam("userId", uid)
                .when()
                .put("/users/{userId}")
                .then().assertThat()
    }

    def searchUser(def searchUserRoleName, loginName = "", nickName = "", status = "") {
        def response = globalClient.clientWithSpecialRole(searchUserRoleName)
                .queryParam("loginName", (String) loginName)
                .queryParam("nickname", (String) nickName)
                .queryParam("status", (String) status)
                .when()
                .get("/users?cursor=1&limit=10")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        jsonSlurper.parseText(response)
    }

    def searchUserWithLimit(def searchUserRoleName, def limit) {
        def response = globalClient.clientWithSpecialRole(searchUserRoleName)
                .queryParam("limit", (String) limit)
                .when()
                .get("/users?cursor=1")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        jsonSlurper.parseText(response)
    }

    def resetUserPassword(def resetPasswordRoleName, def uid) {
        globalClient.clientWithSpecialRole(resetPasswordRoleName)
                .pathParam("uid", uid)
                .when()
                .put("users/{uid}/password")
                .then().assertThat().statusCode(200)
    }

    def updateUserStatus(def updateUserStatusRoleName, def uid, def statusCode) {
        def body = [:]
        body.put("status", statusCode == 0 ? 1 : 0)
        globalClient.clientWithSpecialRole(updateUserStatusRoleName)
                .pathParam("uid", uid)
                .body(body)
                .when()
                .put("users/{uid}/status")
                .then().assertThat().statusCode(200)
    }
}
