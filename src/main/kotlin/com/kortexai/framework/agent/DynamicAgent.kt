package com.kortexai.framework.agent

import com.kortexai.framework.llm.LLMClient
import com.kortexai.framework.memory.Memory

/**
 * Implementação de um agente dinâmico que estende Agent.
 */
open class DynamicAgent(
    name: String,
    llmClient: LLMClient,
    memory: Memory? = null
) : Agent(name, llmClient, memory) {
    // Lógica adicional, se necessário.
}
