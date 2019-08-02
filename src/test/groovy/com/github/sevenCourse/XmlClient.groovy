package com.github.sevenCourse

import static io.restassured.RestAssured.given

class XmlClient {
    def addXmlData(reqBody,expectCode=200) {

        def res = given().baseUri("http://localhost:9090")
                .header("Content-Type", "application/xml;charset=utf-8")
                .body(reqBody)
                .when()
                .post("/api/addXmlData/xml")
                .then().assertThat().statusCode(expectCode)
                .extract().response().getBody().asString()
        res

    }

    def getXmlData() {
        def res = given().baseUri("http://localhost:9090")
                .when()
                .get("/api/getXmlData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        res
    }
}
