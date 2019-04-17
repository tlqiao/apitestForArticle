package tl86

import spock.lang.Specification

import static io.restassured.RestAssured.given

class FirstDemo extends Specification {

    def "should call mock api successfully"() {
        given: "no given"
        when: "call mock api api"
        given().baseUri("http://localhost:9090")
                .when()
                .get("api/getUserDetails")
                .then()
                .assertThat().statusCode(200)
        then: "no then"
    }
}
