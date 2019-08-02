package com.github.sevenCourse

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

class XmlTemplateService {
    VelocityEngine velocityEngine = new VelocityEngine()
    VelocityContext velocityContext = new VelocityContext()
    StringWriter stringWriter = new StringWriter()

    def getAddXmlDataBody(addXmlData) {
        velocityContext.put("addXmlData", addXmlData)
        velocityEngine.getTemplate("src/test/resources/com/github/body/template/addXmlData.vm").merge(velocityContext, stringWriter)
        stringWriter.toString()
    }
}

