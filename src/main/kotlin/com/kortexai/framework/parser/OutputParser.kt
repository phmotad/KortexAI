package com.kortexai.framework.parser

import kotlinx.serialization.json.*

interface OutputParser<T> {
    fun parse(output: String): T
}

class JsonOutputParser<T>(
    private val serializer: KSerializer<T>
) : OutputParser<T> {
    override fun parse(output: String): T {
        return Json.decodeFromString(serializer, output)
    }
}

class StructuredOutputParser : OutputParser<Map<String, String>> {
    override fun parse(output: String): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val lines = output.split("\n")
        
        lines.forEach { line ->
            val parts = line.split(":", limit = 2)
            if (parts.size == 2) {
                result[parts[0].trim()] = parts[1].trim()
            }
        }
        
        return result
    }
}
