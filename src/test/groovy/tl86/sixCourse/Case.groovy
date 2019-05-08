package tl86.sixCourse

import spock.lang.Specification

class Case extends Specification {
    ResumeClient resumeClient
    ResumeService resumeService

    def setup() {
        resumeClient = new ResumeClient()
        resumeService = new ResumeService()
    }

    def "get person from different country"() {
        given: "no given"
        when: "call the get resume api"
        def res = resumeClient.getResumeDetails()
        then: "println  out the person name from different country"
        println resumeService.getPersonByCountry(res, country)
        where:
        country | placeHolder
        "China" | ""
        "USA" | ""
    }

    def "get contact from resume"() {
        given: "no given"
        when: "call the get resume api"
        def res = resumeClient.getResumeDetails()
        then: "println  out the resume from different country"
        resumeService.getContactPhone(res)
    }
}
