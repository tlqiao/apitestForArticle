package com.github.secondCourse

import spock.lang.Specification

import static io.restassured.RestAssured.given

class ThirdDemo extends Specification{
    def "should call get user by name and age api successfully"() {
        given: "no given"
        when: "call mock api api"
        given().baseUri("http://localhost:9090")
               .queryParam("name","zhangshang")
               .queryParam("age",18)
                .when()
                .get("api/getUserByNameAndAge")
                .then()
                .assertThat().statusCode(200)
        then: "no then"
    }
}
