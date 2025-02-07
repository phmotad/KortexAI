package com.kortexai.framework.llm

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class OllamaClient(private val baseUrl: String = "http://localhost:11434") : LLMClient {
    private val client = HttpClient(CIO)

    override suspend fun generateResponse(prompt: String, model: String?, temperature: Double?): String {
        val response = client.post("$baseUrl/api/generate") {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", model ?: "mistral")
                put("prompt", prompt)
                put("temperature", temperature ?: 0.7)
            }.toString())
        }
        
        val jsonResponse = Json.parseToJsonElement(response.bodyAsText())
        return jsonResponse.jsonObject["response"]?.jsonPrimitive?.content ?: "Sem resposta da API"
    }
}
