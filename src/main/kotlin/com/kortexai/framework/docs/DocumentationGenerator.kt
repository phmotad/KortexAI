package com.kortexai.framework.docs

class DocumentationGenerator {
    fun generateDocs() {
        val docGen = KDocGenerator()
        docGen.processSourceFiles("src/main/kotlin")
        docGen.generateMarkdown("docs/api")
        docGen.generateHTML("docs/html")
    }
}
