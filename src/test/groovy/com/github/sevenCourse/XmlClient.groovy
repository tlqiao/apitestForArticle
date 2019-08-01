package com.github.sevenCourse

import static io.restassured.RestAssured.given

class XmlClient {
    def getXmlData() {
        def res = given().baseUri("http://localhost:9090")
                .when()
                .get("/api/getXmlData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        res
    }
}
