package com.kortexai.framework.crew

import com.kortexai.framework.agent.Agent
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Objeto singleton que gerencia uma equipe de agentes.
 */
object Crew {
    private val agents = mutableListOf<Agent>()

    /**
     * Adiciona um agente à equipe.
     */
    fun addAgent(agent: Agent) {
        agents.add(agent)
    }

    /**
     * Executa uma tarefa em todos os agentes da equipe.
     */
    suspend fun executeTask(task: String): List<String> = coroutineScope {
        agents.map { agent ->
            async { agent.performTask(task) }
        }.map { it.await() }
    }

    /**
     * Executa múltiplas tarefas em todos os agentes da equipe.
     */
    suspend fun executeMultipleTasks(tasks: List<String>): List<List<String>> = coroutineScope {
        agents.map { agent ->
            async { agent.performComplexTask(tasks) }
        }.map { it.await() }
    }

    /**
     * Executa uma tarefa priorizada (placeholder para lógica de priorização).
     */
    suspend fun executePrioritizedTask(task: String, priority: Int): List<String> =
        executeTask(task)
}
