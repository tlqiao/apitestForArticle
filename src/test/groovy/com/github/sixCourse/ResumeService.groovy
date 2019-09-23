package com.github.sixCourse

import groovy.json.JsonSlurper

class ResumeService {
    JsonSlurper jsonSlurper

    ResumeService() {
        jsonSlurper = new JsonSlurper()
    }

    def getPersonByCountry(String res, country) {
        def resumeDetails = jsonSlurper.parseText(res)
        resumeDetails.birthPlace.country == country ? resumeDetails.name: "no person"
    }

    def getContactPhone(String res) {
        def resumeDetails = jsonSlurper.parseText(res)
        resumeDetails.contacts.size() > 0 ? resumeDetails.contacts[0].phone : "no contact"
    }

    void printWorkingDetails(String res) {
        def resumeDetails = jsonSlurper.parseText(res)
        if (resumeDetails.working.workingProject.size() > 0) {
            resumeDetails.working.workingProject.each { it ->
                    println "--projectName:${it.projectName}--jobTitle:${it.jobTitle}--responseibility:${it.responsibility}" }
        }
    }

    void printIfPersonWithSpecialSkill(String res, language) {
        def resumeDetails = jsonSlurper.parseText(res)
        if (resumeDetails.skills.tech.size() > 0) {
            def programmingSkill = resumeDetails.skills.tech.find { it -> it.language == language }
            println "--programmingSkill:${programmingSkill.language}--level:${programmingSkill.level}"
        }
    }
}
