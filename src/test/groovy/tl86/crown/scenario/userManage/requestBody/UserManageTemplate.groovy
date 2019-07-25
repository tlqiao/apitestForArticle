package tl86.crown.scenario.userManage.requestBody

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

class UserManageTemplate {
    VelocityEngine velocityEngine = new VelocityEngine()
    VelocityContext velocityContext = new VelocityContext()
    StringWriter stringWriter = new StringWriter()

    def getAddUserRequestBody(AddUserBody addUserBody) {
        velocityContext.put("userInfo",addUserBody.userInfo)
        velocityContext.put("roleIdList",addUserBody.roleIdList)
        velocityEngine.getTemplate("src/test/resources/tl86/crown/body/userManage/addUserBodyTemplate.json").merge(velocityContext,stringWriter)
        stringWriter.toString()
    }
}
