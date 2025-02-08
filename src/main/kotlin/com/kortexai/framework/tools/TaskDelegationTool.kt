package com.kortexai.framework.tools

/**
 * Ferramenta para delegar tarefas entre agentes
 */
class TaskDelegationTool : Tool {
    override fun execute(input: String): String {
        return "Tarefa '$input' delegada com sucesso"
    }
}
