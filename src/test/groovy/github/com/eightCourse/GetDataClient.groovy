package github.com.eightCourse

import org.junit.Test

import static io.restassured.RestAssured.given

class GetDataClient {
    ConfigParser configParser
    TestDataService userTestData
    def configs
    def users

    GetDataClient() {
        configParser = new ConfigParser()
        userTestData = new TestDataService()
        configs = configParser.getGlobalConfig()
        users = userTestData.getUserDataByRole("ForGetDataApi")
    }

    void getData() {
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic("apiUsername", "apiPassword")
                .when()
                .get("/api/getData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        println res
    }

    void getData2() {
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic(users.username, userTestData.getPasswordByUserName(users.username))
                .when()
                .get("/api/getData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        println res
    }

    @Test()
    void callGetData() {
        getData()
    }

    @Test()
    void callGetData2() {
        getData2()
    }
}
