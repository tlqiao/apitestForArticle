package com.github.secondCourse

import spock.lang.Specification

import static io.restassured.RestAssured.given

class SecondDemo extends Specification {

    def "should get user details by user name successfully"() {
        given: "no given"
        when: "call get user by name api"
        given().log().all()
                .baseUri("http://localhost:9090/")
                .when()
                .pathParam("userName",userName)
                .get("api/getUserByName/{userName}")
                .then()
                .log().all()
                .assertThat().statusCode(200)
        then: "no then"
        where:
        userName|placeHolder
        "tom"|""
        "dave"|""
    }
}

