package com.kortexai.framework.vectorstore

import kotlinx.serialization.Serializable

@Serializable
data class Document(
    val content: String,
    val metadata: Map<String, String> = mapOf()
)

@Serializable
data class Vector(
    val embedding: List<Float>,
    val document: Document
)

interface VectorStore {
    suspend fun addDocuments(documents: List<Document>)
    suspend fun similaritySearch(query: String, k: Int = 4): List<Document>
}

class InMemoryVectorStore : VectorStore {
    private val vectors = mutableListOf<Vector>()
    
    override suspend fun addDocuments(documents: List<Document>) {
        // Implementação básica - substituir por real embedding
        documents.forEach { doc ->
            vectors.add(Vector(generateEmbedding(doc.content), doc))
        }
    }
    
    override suspend fun similaritySearch(query: String, k: Int): List<Document> {
        val queryEmbedding = generateEmbedding(query)
        return vectors
            .sortedBy { cosineSimilarity(queryEmbedding, it.embedding) }
            .take(k)
            .map { it.document }
    }
    
    private fun generateEmbedding(text: String): List<Float> {
        // TODO: Implementar real embedding usando modelo
        return text.map { it.code.toFloat() }
    }
    
    private fun cosineSimilarity(v1: List<Float>, v2: List<Float>): Float {
        val dotProduct = v1.zip(v2).sumOf { it.first * it.second }
        val norm1 = sqrt(v1.sumOf { it * it })
        val norm2 = sqrt(v2.sumOf { it * it })
        return dotProduct / (norm1 * norm2)
    }
}
