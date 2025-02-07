package com.kortexai.framework.agent.roles

enum class AgentRole {
    MANAGER,
    SPECIALIST,
    RESEARCHER,
    CRITIC,
    EXECUTOR
}

interface RoleCapabilities {
    fun getPromptTemplate(): String
    fun getToolset(): List<Tool>
}

class ManagerRole : RoleCapabilities {
    override fun getPromptTemplate(): String = """
        Como gerente, sua função é:
        1. Analisar a tarefa principal
        2. Dividir em subtarefas
        3. Delegar para especialistas
        4. Monitorar progresso
        5. Consolidar resultados
        
        Tarefa atual: {task}
    """.trimIndent()
    
    override fun getToolset(): List<Tool> = listOf(
        TaskDelegationTool(),
        ProgressMonitoringTool(),
        ResultConsolidationTool()
    )
}
