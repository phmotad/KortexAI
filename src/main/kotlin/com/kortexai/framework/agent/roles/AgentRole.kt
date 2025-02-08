package com.kortexai.framework.agent.roles

import com.kortexai.framework.tools.Tool
import com.kortexai.framework.tools.TaskDelegationTool
import com.kortexai.framework.tools.ProgressMonitoringTool
import com.kortexai.framework.tools.ResultConsolidationTool

/**
 * Define o papel de um agente no framework KortexAI.
 * Cada agente pode ter múltiplos papéis com diferentes responsabilidades.
 */
class AgentRole(
    val name: String,
    val description: String,
    val tools: List<Tool> = listOf(
        TaskDelegationTool(),
        ProgressMonitoringTool(),
        ResultConsolidationTool()
    )
) {
    /**
     * Executa uma tarefa específica do papel do agente.
     * @param task A tarefa a ser executada
     * @return Resultado da execução
     */
    fun performTask(task: String): String {
        return tools.joinToString("\n") { tool ->
            "Executando ${tool::class.simpleName}: ${tool.execute(task)}"
        }
    }
}
