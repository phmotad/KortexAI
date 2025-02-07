package com.kortexai.framework.llm

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class ClaudeClient(private val apiKey: String) : LLMClient {
    private val client = HttpClient(CIO)
    private val baseUrl = "https://api.anthropic.com/v1/messages"

    override suspend fun generateResponse(prompt: String, model: String?, temperature: Double?): String {
        val response = client.post(baseUrl) {
            headers {
                append("x-api-key", apiKey)
                append("anthropic-version", "2023-06-01")
                append("Content-Type", "application/json")
            }
            setBody(buildJsonObject {
                put("model", model ?: "claude-3-opus-20240229")
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
        return jsonResponse.jsonObject["content"]?.jsonArray?.get(0)
            ?.jsonObject?.get("text")?.jsonPrimitive?.content
            ?: "Sem resposta da API"
    }
}
