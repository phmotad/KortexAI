package com.kortexai.framework.db

import io.pinecone.PineconeClient
import io.milvus.client.MilvusClient

interface VectorDatabase {
    suspend fun store(id: String, vector: List<Float>, metadata: Map<String, String>)
    suspend fun search(vector: List<Float>, limit: Int = 10): List<SearchResult>
    suspend fun delete(id: String)
}

class PineconeDB(apiKey: String, environment: String) : VectorDatabase {
    private val client = PineconeClient(apiKey, environment)
    
    override suspend fun store(id: String, vector: List<Float>, metadata: Map<String, String>) {
        client.upsert(id, vector, metadata)
    }
    
    override suspend fun search(vector: List<Float>, limit: Int): List<SearchResult> {
        return client.search(vector, limit)
    }
    
    override suspend fun delete(id: String) {
        client.delete(id)
    }
}
