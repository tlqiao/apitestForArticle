package tl86.eightCourse

import tl86.fourCourse.file.FileService
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
        configs =getConfigs("src/test/resources/tl86/config/config.yml")
        configs
    }
}
