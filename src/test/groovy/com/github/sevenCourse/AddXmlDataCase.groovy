package com.github.sevenCourse

import org.junit.Assert
import spock.lang.Specification

class AddXmlDataCase extends Specification {
    XmlTemplateService xmlTemplateService
    XmlClient xmlClient

    def setup() {
        xmlTemplateService = new XmlTemplateService()
        xmlClient = new XmlClient()
    }

    def "should add xml data successfully"() {
        given: "generate add xml data api request body"
        def reqBody = new AddXmlDataBody()
                .setBookName("bookName-shanguo")
                .setPrice(20)
                .setAuthor("luoguanzhong")
                .getAddXmlDataBody()

        when: "call add xml data api"
        def res = xmlClient.addXmlData(reqBody)
        then: "should get correct response"
        Assert.assertEquals(res, "add xml data successfully")
    }

    def "should add xml data with invalid bookName failed"() {
        given: "generate add xml data api request body"
        def reqBody = new AddXmlDataBody()
                .setBookName("shanguo")
                .setPrice(20)
                .setAuthor("luoguanzhong")
                .getAddXmlDataBody()

        when: "call add xml data api"
        xmlClient.addXmlData(reqBody, 404)
        then: "no then"
    }
}
