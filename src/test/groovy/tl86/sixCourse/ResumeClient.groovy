package tl86.sixCourse

import static io.restassured.RestAssured.given

class ResumeClient {
   def getResumeDetails(){
        def res = given().baseUri("http://localhost:9090")
                .when()
                .get("/api/getResume")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        res
    }
}
