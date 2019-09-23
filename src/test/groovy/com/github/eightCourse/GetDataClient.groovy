package com.github.eightCourse

import org.junit.Test

import static io.restassured.RestAssured.given

class GetDataClient {
    ConfigParser configParser
    TestDataService testDataService
    def configs
    def users

    GetDataClient() {
        configParser = new ConfigParser()
        testDataService = new TestDataService()
        configs = configParser.getGlobalConfig()
        users = testDataService.getUserDataByRole("ForGetDataApi")
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

    void getDataWithCsvUser() {
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic(users.username, users.password)
                .when()
                .get("/api/getData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        println res
    }

    void getDataWithEncryptPassword() {
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic(users.username, testDataService.getPasswordByUserName(users.username))
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
    void callGetDataWithCsvUser() {
        getDataWithCsvUser()
    }

    @Test()
    void callGetDataWithEncryptPassword() {
        getDataWithEncryptPassword()
    }
}
