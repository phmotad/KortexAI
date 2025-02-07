package com.kortexai.framework

import com.kortexai.framework.agent.Agent
import com.kortexai.framework.chain.LLMChain
import com.kortexai.framework.memory.Memory

/**
 * Classe que orquestra a execução combinando um agente, uma chain e a memória.
 */
class Executor(
    private val agent: Agent,
    private val chain: LLMChain,
    private val memory: Memory
) {
    suspend fun execute(prompt: String): String {
        val agentResponse = agent.performTask(prompt)
        val chainResponse = chain.process(prompt)
        return "$agentResponse\n$chainResponse"
    }
}
