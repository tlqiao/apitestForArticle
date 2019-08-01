package github.com.eightCourse

import github.com.fourCourse.file.FileService
class ConfigParser {
    FileService fileService

    ConfigParser() {
        fileService = new FileService()
    }

    def getConfigs(String configFilePath) {
        def configs = fileService.getConfigs(configFilePath)
        def sysEnv = System.getenv("ACTIVE_ENV")
        def active = sysEnv ? sysEnv : configs.active
        configs.putAll(configs.get(active))
        configs
    }

    def getGlobalConfig() {
        def configs
        configs =getConfigs("src/test/resources/github/config/config.yml")
        configs
    }
}
