package com.github.sevenCourse

class AddXmlDataBody {
    def bookName
    def price
    def author
    XmlTemplateService xmlTemplateService

    AddXmlDataBody() {
        xmlTemplateService = new XmlTemplateService()
    }

    def setBookName(bookName) {
        this.bookName = bookName
        this
    }
    def setPrice(price){
        this.price=price
        this
    }

    def setAuthor(author){
        this.author=author
        this
    }

    def getAddXmlDataBody() {
        xmlTemplateService.getAddXmlDataBody(this)
    }
}
