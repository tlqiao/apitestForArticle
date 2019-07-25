package tl86.crown.scenario.userManage.client

import tl86.crown.util.login.LoginClient

import static io.restassured.RestAssured.given

class UserClient {
    LoginClient globalClient

    UserClient() {
        globalClient = new LoginClient()
    }

    def addUser(def body) {
        given()
                .baseUri("http://127.0.0.1:8088")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjA4MzI1NzEsInVpZCI6MSwiaWF0IjoxNTYwNzcyNTcxfQ.y_-nIRIlZ0fQwcFFIOvrkh-42cLMMqUChN4P8zaupvA6294falOBph40TQ_splK_1KZvcbGrRvOZbREVWQ6cog")
                .body(body)
                .when()
                .post('/users')
                .then()
                .assertThat().statusCode(201)
    }

    def addUserTwo(def roleName, def body) {
        globalClient.clientWithSpecialRole(roleName)
                    .body(body)
                    .when()
                    .post('/users')
                    .then()
                    .assertThat()
    }
}
