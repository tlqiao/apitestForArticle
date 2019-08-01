package com.github.fourCourse.file

import spock.lang.Specification

class Case extends Specification {
    FileService fileService

    def setup() {
        fileService = new FileService()
    }

    def "should create and read txt file successfully"() {
        given: "create txt file"
        def file = fileService.createFile("./src/test/resources/github/data/test.txt")
        when: "write some content to the file"
        // 支持通过<<写入文件内容
        file << "name,age,address\n"
        file << "Tom,100,chengdu\n"
        then: "print file content"
        // 读取txt文件内容
        def lines = file.readLines()
        lines.each{println it}
        and: "delete file"
        file.delete()
    }

    def "should read yml file successfully"() {
        given: "no given"
        when: "get the csv data "
        def configs = fileService.getConfigs('./src/test/resources/github/config/config.yml')
        then: "print data"
        println configs.stable.db.url
        println configs.active
    }

    def "should read csv file successfully"() {
        given: "no given"
        when: "get the csv file data"
        def csvContent = fileService.getCsvFileContent('./src/test/resources/github/data/test.csv', ',')
        then: "println the data"
        csvContent.each{ it -> println it.name + ":" + it.age + ":" + it.address }
    }

    def "should read json file successfully"() {
        given: ""
        when: "get json file data"
        def jsonContent = fileService.getCollectionFromJsonFile('./src/test/resources/github/data/test.json')
        then: "println the data"
        println jsonContent.pipelineName
        println jsonContent.sonar.coverage
        def stage = jsonContent.stages.find { it -> it.name == "stage2" }
        println stage.id
        println stage.duration
    }

    def "should read xml file successfully"() {
        given: ""
        when: "get json file data"
        def xmlContent = fileService.getCollectionFromXMLFile('./src/test/resources/github/data/test.xml')
        then: "println the data"
        xmlContent.person.each{ println it }
        println xmlContent.person.find{ it -> it.name == "DAVE" }.age
    }
}
