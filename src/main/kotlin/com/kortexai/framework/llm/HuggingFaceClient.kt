package com.kortexai.framework.llm

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

class HuggingFaceClient(private val apiKey: String) : LLMClient {
    private val client = HttpClient(CIO)
    private val baseUrl = "https://api-inference.huggingface.co/models"

    override suspend fun generateResponse(prompt: String, model: String?, temperature: Double?): String {
        val modelName = model ?: "mistralai/Mistral-7B-Instruct-v0.2"
        val response = client.post("$baseUrl/$modelName") {
            headers {
                append("Authorization", "Bearer $apiKey")
                append("Content-Type", "application/json")
            }
            setBody(buildJsonObject {
                put("inputs", prompt)
                put("parameters", buildJsonObject {
                    put("temperature", temperature ?: 0.7)
                })
            }.toString())
        }
        
        val jsonResponse = Json.parseToJsonElement(response.bodyAsText())
        return jsonResponse.jsonArray.firstOrNull()
            ?.jsonObject?.get("generated_text")?.jsonPrimitive?.content
            ?: "Sem resposta da API"
    }
}
