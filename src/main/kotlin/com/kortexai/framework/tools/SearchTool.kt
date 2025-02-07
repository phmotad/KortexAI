package com.kortexai.framework.tools

class SearchTool : Tool {
    private val dataStore = listOf("Kotlin", "Java", "Python", "JavaScript", "C++")

    override fun execute(input: String): String {
        val results = dataStore.filter { it.contains(input, ignoreCase = true) }
        return if (results.isNotEmpty()) {
            "Resultados encontrados: ${results.joinToString(", ")}."
        } else {
            "Nenhum resultado encontrado para: \"$input\"."
        }
    }
}