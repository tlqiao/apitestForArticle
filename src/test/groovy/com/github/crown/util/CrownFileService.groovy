package com.github.crown.util

import com.xlson.groovycsv.CsvParser
import groovy.json.JsonSlurper
import org.yaml.snakeyaml.Yaml

class CrownFileService {
    JsonSlurper jsonSlurper
    XmlSlurper xmlSlurper
    CsvParser csvParser

    CrownFileService() {
        jsonSlurper = new JsonSlurper()
        xmlSlurper = new XmlSlurper()
        xmlSlurper.setFeature("http://apache.org/xml/features/disallow-doctype-decl",false)
        csvParser = new CsvParser()
    }
    def createFile(path) {
        new File(path)
    }

    private def yml(String text) {
        new Yaml().load(text)
    }

    def getConfigs(String ymlFilePath) {
        def configs = yml(createFile(ymlFilePath).text)
        configs
    }
    def getCollectionFromXMLFile(String xmlFilePath) {
        xmlSlurper.parse(createFile(xmlFilePath))
    }
    def getCollectionFromJsonFile(String jsonFilePath) {
        jsonSlurper.parse(createFile(jsonFilePath))
    }
    def getCsvFileContent(String csvFilePath, separator) {
        csvParser.parse(new FileReader(createFile(csvFilePath)), separator: separator)
    }
}
