package com.kortexai.framework.workflow

class Workflow(
    private val steps: List<WorkflowStep>,
    private val agents: Map<String, Agent>
) {
    suspend fun execute(input: String): String {
        var currentInput = input
        steps.forEach { step ->
            val agent = agents[step.agentId] ?: throw IllegalStateException("Agente não encontrado")
            currentInput = agent.performTask(step.generatePrompt(currentInput))
        }
        return currentInput
    }
}

data class WorkflowStep(
    val agentId: String,
    val promptTemplate: String
) {
    fun generatePrompt(input: String): String = 
        promptTemplate.replace("{input}", input)
}
