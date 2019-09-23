package com.github.fiveCourse

import static io.restassured.RestAssured.given

class UserClient {
    def addUserWithFile(File file) {
        def res = given().baseUri("http://localhost:9090").log().all()
                .when()
                .body(file)
                .post("/api/addUserDetails")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        res
    }

    def addUserWithString(String body) {
        def res = given().baseUri("http://localhost:9090").log().all()
                .when()
                .body(body)
                .post("/api/addUserDetails")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        res
    }
}
