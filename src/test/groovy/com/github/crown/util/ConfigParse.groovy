package com.github.crown.util

class ConfigParse {
    CrownFileService crownFileService

    ConfigParse() {
        crownFileService = new CrownFileService()
    }

    private def getConfigs(String configFilePath) {
        def configs = crownFileService.getConfigs(configFilePath)
        def sysEnv = System.getenv("ACTIVE_ENV")
        def active = sysEnv ? sysEnv : configs.active
        configs.putAll(configs.get(active))
        configs
    }

    private def decryptConfig(Map<String,Object> configs,secretKey) {
        configs.each {
            it ->
                if (it.getValue() instanceof String) {
                    if (it.getValue().startsWith("[ENC]")) {
                        it.setValue Secret.decrypt(secretKey, it.getValue().replace("[ENC]",""))
                    }
                } else if (it.getValue() instanceof Map) {
                    decryptConfig (it.getValue(),secretKey)
                }
        }
        configs
    }

    def getGlobalConfig() {
        def configs
        configs = getConfigs("src/test/resources/com/github/crown/config/crownConfig.yml")
        decryptConfig(configs,configs.decryptKey)
    }
}
