package com.github.fiveCourse

class AddUserBody {
    def mainContact=[:]
    def ifAddMainContact
    def backupContact=[:]
    def ifAddBackupContact
    def backGround=[:]
    def ifAddBackGround
    def otherInfo
    def ifAddOtherInfo
    UserClient userClient

    AddUserBody() {
        userClient = new UserClient()
    }

    def addMainContact(city, street, phone) {
        this.ifAddMainContact = true
        this.mainContact.city = city
        this.mainContact.street = street
        this.mainContact.phone = phone
        this
    }

    def addBackupContact(city, street, phone) {
        this.ifAddBackupContact = true
        this.backupContact.city = city
        this.backupContact.street = street
        this.backupContact.phone = phone
        this
    }

    def addBackGround(degree, school, date) {
        this.ifAddBackGround = true
        this.backGround.degree = degree
        this.backGround.school = school
        this.backGround.date = date
        this
    }

    def addOtherInfo(otherInfo) {
        this.ifAddOtherInfo = true
        this.otherInfo = otherInfo
        this
    }

    def generateBody() {
      new JsonTemplateService().getAddUserRequestBody(this)
    }
}
