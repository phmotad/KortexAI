package com.kortexai.framework.llm

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class DeepSeekClient(private val apiKey: String) : LLMClient {
    private val client = HttpClient(CIO)
    private val baseUrl = "https://api.deepseek.com/v1/chat/completions"

    override suspend fun generateResponse(prompt: String, model: String?, temperature: Double?): String {
        val response = client.post(baseUrl) {
            headers {
                append("Authorization", "Bearer $apiKey")
                append("Content-Type", "application/json")
            }
            setBody(buildJsonObject {
                put("model", model ?: "deepseek-chat")
                put("messages", buildJsonArray {
                    add(buildJsonObject {
                        put("role", "user")
                        put("content", prompt)
                    })
                })
                put("temperature", temperature ?: 0.7)
            }.toString())
        }
        
        val jsonResponse = Json.parseToJsonElement(response.bodyAsText())
        return jsonResponse.jsonObject["choices"]?.jsonArray?.get(0)
            ?.jsonObject?.get("message")?.jsonObject?.get("content")?.jsonPrimitive?.content
            ?: "Sem resposta da API"
    }
}
