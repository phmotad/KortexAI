package com.kortexai.framework.llm

/**
 * Interface base para clientes de modelos de linguagem (LLM).
 * Define o contrato básico para geração de respostas.
 */
interface LLMClient {
    suspend fun generateResponse(prompt: String, model: String? = null, temperature: Double? = null): String
}
