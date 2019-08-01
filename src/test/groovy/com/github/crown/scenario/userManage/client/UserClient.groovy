package com.github.crown.scenario.userManage.client

import com.github.crown.util.login.LoginClient
import groovy.json.JsonSlurper

class UserClient {
    LoginClient loginClient
    JsonSlurper jsonSlurper

    UserClient() {
        loginClient = new LoginClient()
        jsonSlurper = new JsonSlurper()
    }

    def addUser(def addUserRoleName, def body) {
        loginClient.clientWithSpecialRole(addUserRoleName)
                .body(body)
                .when()
                .post('/users')
                .then()
                .assertThat()
    }

    def editUser(def editUserRoleName, def uid, def body) {
        loginClient.clientWithSpecialRole(editUserRoleName)
                .body(body)
                .pathParam("userId", uid)
                .when()
                .put("/users/{userId}")
                .then().assertThat()
    }

    def searchUser(def searchUserRoleName, loginName = "", nickName = "", status = "") {
        def response = loginClient.clientWithSpecialRole(searchUserRoleName)
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
        def response = loginClient.clientWithSpecialRole(searchUserRoleName)
                .queryParam("limit", (String) limit)
                .when()
                .get("/users?cursor=1")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        jsonSlurper.parseText(response)
    }

    def resetUserPassword(def resetPasswordRoleName, def uid) {
        loginClient.clientWithSpecialRole(resetPasswordRoleName)
                .pathParam("uid", uid)
                .when()
                .put("users/{uid}/password")
                .then().assertThat().statusCode(200)
    }

    def updateUserStatus(def updateUserStatusRoleName, def uid, def statusCode) {
        def body = [:]
        body.put("status", statusCode == 0 ? 1 : 0)
        loginClient.clientWithSpecialRole(updateUserStatusRoleName)
                .pathParam("uid", uid)
                .body(body)
                .when()
                .put("users/{uid}/status")
                .then().assertThat().statusCode(200)
    }
}
