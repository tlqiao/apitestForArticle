package tl86.fiveCourse

import org.junit.Assert
import spock.lang.Specification
import tl86.fourCourse.file.FileService

class Case extends Specification {
    FileService fileService
    UserClient userClient

    def setup() {
        fileService = new FileService()
        userClient = new UserClient()
    }

    def "should add user successfully"() {
        given: "no given"
        when: "call the add user api"
        def file = fileService.createFile("./src/test/resources/tl86/body/addUser.json")
        then: "get the correct response"
        Assert.assertEquals(userClient.addUserWithFile(file), "add user successfully")
    }

    def "should add user with only inputting main contact successfully"() {
        given: "generate request body"
        def body = new AddUserBody()
                .addMainContact(city, street, phone)
                .generateBody()
        when: "call add user api"
        def response = userClient.addUserWithString(body)
        then: "should get correct response"
        Assert.assertEquals("assert add user api response correct", response, "add user successfully")
        where:
        city      | street          | phone
        "chengdu" | "qingyi-street" | 11223344556
    }

    def "should add user with inputting main and backup contact successfully"() {
        given: "generate request body"
        def body = new AddUserBody()
                .addMainContact(mainCity, mainStreet, mainPhone)
                .addBackupContact(backupCity, backupStreet, backupPhone)
                .generateBody()
        when: "call add user api"
        def response = userClient.addUserWithString(body)
        then: "should get correct response"
        Assert.assertEquals("assert add user api response correct", response, "add user successfully")
        where:
        mainCity  | mainStreet   | mainPhone   | backupCity | backupStreet | backupPhone
        "chengdu" | "one-street" | 11223344556 | "beijing"  | "two-street" | 00112233445
    }

    def "should add user with inputting contacts and background successfully"() {
        given: "generate request body"
        def body = new AddUserBody()
                .addMainContact(mainCity, mainStreet, mainPhone)
                .addBackupContact(backupCity, backupStreet, backupPhone)
                .addBackGround(degree, school, date)
                .generateBody()
        when: "call add user api"
        def response = userClient.addUserWithString(body)
        then: "should get correct response"
        Assert.assertEquals("assert add user api response correct", response, "add user successfully")
        where:
        mainCity  | mainStreet   | mainPhone   | backupCity | backupStreet | backupPhone | degree   | school    | date
        "chengdu" | "one-street" | 11223344556 | "beijing"  | "two-street" | 00112233445 | "doctor" | "qinghua" | "2019-07"
    }
}
