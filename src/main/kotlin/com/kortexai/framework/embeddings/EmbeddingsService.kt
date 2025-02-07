package com.kortexai.framework.embeddings

interface EmbeddingsService {
    suspend fun getEmbeddings(text: String): List<Float>
}

class OpenAIEmbeddings(private val apiKey: String) : EmbeddingsService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    
    override suspend fun getEmbeddings(text: String): List<Float> {
        val response = client.post("https://api.openai.com/v1/embeddings") {
            headers {
                append("Authorization", "Bearer $apiKey")
            }
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "input" to text,
                "model" to "text-embedding-ada-002"
            ))
        }
        
        return Json.decodeFromString<EmbeddingResponse>(response.bodyAsText()).data.first().embedding
    }
}
