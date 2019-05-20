package tl86.eightCourse

import tl86.fourCourse.file.FileService

class TestDataService {
    FileService fileService

    TestDataService() {
        fileService = new FileService()
    }

    def getUserFileData() {
        def userData = fileService.getCsvFileContent("src/test/resources/tl86/data/user.csv", ",")
        userData
    }

    def getUserDataByRole(roleName) {
        getUserFileData().find { it -> it.rolename == roleName }
    }

    def getPasswordByUserName(userName) {
       Secret.decrypt( "apiTestStudy",(String)getUserFileData().find{it -> it.username == userName}.password)
    }
}
