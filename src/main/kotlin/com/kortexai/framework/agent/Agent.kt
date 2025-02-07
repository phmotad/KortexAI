package com.kortexai.framework.agent

import com.kortexai.framework.llm.LLMClient
import com.kortexai.framework.memory.Memory

/**
 * Classe que representa um agente inteligente.
 * @param name Nome do agente
 * @param llmClient Cliente LLM para geração de respostas
 * @param memory Instância de Memory para armazenamento de contexto (opcional)
 */
open class Agent(
    protected val name: String,
    protected val llmClient: LLMClient,
    protected val memory: Memory? = null
) {
    /**
     * Executa uma tarefa utilizando o LLM e armazena o resultado na memória.
     */
    open suspend fun performTask(task: String): String {
        val response = llmClient.generateResponse(task)
        memory?.save("last_response", response)
        return "Agente $name diz: $response"
    }

    /**
     * Executa uma tarefa complexa que pode envolver múltiplas etapas.
     */
    open suspend fun performComplexTask(tasks: List<String>): List<String> {
        return tasks.map { performTask(it) }
    }
}
