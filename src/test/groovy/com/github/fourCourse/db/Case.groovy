package com.github.fourCourse.db

import org.junit.Assert
import spock.lang.Specification

class Case extends Specification {
    DataRepository dataRepository

    void setup() {
        dataRepository = new DataRepository()
    }

    def "should get user info successfully"() {
        given: "no given"
        when: "query user table to get info"
        def userInfo = dataRepository.getUserInfo()
        then: "should get user info"
        userInfo.each { it -> println it.username + ":" + it.age + ":" + it.create_date }
    }

    def "should get user address successfully"() {
        given: "no given"
        when: "query user and address table"
        def addressInfo = dataRepository.getAddressByUserName(userName)
        then: "should get correct user address info"
        Assert.assertEquals(addressInfo.address, address)
        where:
        userName | address
        "TOM"    | "chengdu"
        "DONE"   | "beijing"
        "ECHO"   | "shanghai"
        "MARY"   | "hangzhou"
    }

    def "should add user successfully"() {
        given: "no given"
        when: "add user"
        dataRepository.addUser(userName, age)
        then: "should get added user successfully"
        Assert.assertEquals(dataRepository.getUser(userName).username, userName)
        where:
        userName | age
        "Dave"   | 88
    }

    def "should update address successfully"() {
        given: "no given"
        when: "update user's address"
        dataRepository.updateAddress(userName, age)
        then: "should update address successfully"
        Assert.assertEquals(dataRepository.getUser(userName).age, age)
        where:
        userName | age
        "MARY"   | 55
    }
}
