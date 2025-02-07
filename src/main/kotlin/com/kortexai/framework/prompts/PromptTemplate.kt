package com.kortexai.framework.prompts

class PromptTemplate(private val template: String) {
    fun format(variables: Map<String, String>): String {
        var result = template
        variables.forEach { (key, value) ->
            result = result.replace("{$key}", value)
        }
        return result
    }
}
